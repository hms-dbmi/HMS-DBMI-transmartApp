/*************************************************************************
 * tranSMART - translational medicine data mart
 *
 * Copyright 2008-2012 Janssen Research & Development, LLC.
 *
 * This product includes software developed at Janssen Research & Development, LLC.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software  * Foundation, either version 3 of the License, or (at your option) any later version, along with the following terms:
 * 1.	You may convey a work based on this program in accordance with section 5, provided that you retain the above notices.
 * 2.	You may convey verbatim copies of this program code as you receive it, in any medium, provided that you retain the above notices.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS    * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 ******************************************************************/
/**
 * $Id: LoginController.groovy 10098 2011-10-19 18:39:32Z mmcduffie $
 * @author $Author: mmcduffie $
 * @version $Revision: 10098 $
 */

import static us.monoid.web.Resty.content

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.rosuda.REngine.Rserve.*
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import transmart.UserManagerService
import us.monoid.json.*
import us.monoid.web.*
import edu.hms.transmart.security.AuthUser

/**
 * Login Controller
 */
class LoginController {

	/**
	 * Dependency injection for the authenticationTrustResolver.
	 */
	def authenticationTrustResolver

	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService
	def userDetailsService

	def logoutHandlers

	/**
	 * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
	 */
	def index = {
		if (springSecurityService.isLoggedIn()) {
			redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
		}
		else {
			redirect action: auth, params: params
		}
	}

	def forceAuth = {

		Authentication auth = SCH.context.authentication

		if (auth) {
			logoutHandlers.each  { handler->
				handler.logout(request,response,auth)
			}
		}

		def loginBannerMessage = grailsApplication.config.loginBannerMessage;

		if(!loginBannerMessage) loginBannerMessage = "Please Login Below."

		render view: 'auth', model: [postUrl: request.contextPath + SpringSecurityUtils.securityConfig.apf.filterProcessesUrl,loginBannerMessage: loginBannerMessage]
	}

	/**
	 * Show the login page.
	 */
	def auth = {
		nocache response

		def guestAutoLogin = grailsApplication.config.com.recomdata.guestAutoLogin;
		boolean guestLoginEnabled = ('true'==guestAutoLogin)
		log.info("enabled guest login")
		
		boolean forcedFormLogin = request.getQueryString() != null
		log.info("User is forcing the form login? : " + forcedFormLogin)

		// if enabled guest and not forced login
		if(guestLoginEnabled && !forcedFormLogin){
			log.info("proceeding with auto guest login")
			def guestuser = grailsApplication.config.com.recomdata.guestUserName;

			UserDetails ud = userDetailsService.loadUserByUsername(guestuser)
			if(ud!=null){
				log.debug("We have found user: ${ud.username}")
				springSecurityService.reauthenticate(ud.username)

				if (ud.authorities == null) {
					log.debug "User does not have roles. Redirecting to the waiting pattern."
					redirect controller: 'registration', action: 'notyet', model: [user: ud]
				} else {
					log.debug "User has roles, meaning has access to the system."
					log.debug "Redirecting to the standard URL."
					redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
				}
			}else{
				log.info("can not find the user:"+guestuser);
			}
		}

		def loginBannerMessage = grailsApplication.config.loginBannerMessage;
		if (springSecurityService.isLoggedIn()) {
			log.debug "User is logged in. Redirecting to URL:"+SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
			redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
		} else	{
			log.debug "User is not logged in. Showing default login view."
			if(!loginBannerMessage) loginBannerMessage = "Please Login Below"
		}
		
		model: [
			loginBannerMessage: loginBannerMessage
		]
	}

	def callback = {
		def credentials = UserManagerService.getCredentials(request)
		session["credentials"] = credentials
		
		if (credentials.username != null && credentials.level > 0) {
			springSecurityService.reauthenticate(credentials.username)
			redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
		} else {
			redirect controller: "registration"
		}
	}

	/**
	 * Show denied page.
	 */
	def denied = {
		if (springSecurityService.isLoggedIn() &&
		authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: full, params: params
		}
	}

	/**
	 * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
	 */
	def full = {
		render view: 'auth', params: params,
		model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication),
			postUrl: request.contextPath + SpringSecurityUtils.securityConfig.apf.filterProcessesUrl]
	}

	/**
	 * Callback after a failed login. Redirects to the auth page with a warning message.
	 */
	def authfail = {
		def username = session[UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		String msg = params.error_message?:'' // If the user was redirected here, we try to use the passed-in error message, otherwise, just use the empty string.

		def exception = session[AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY]
		if (exception) {
			if (exception instanceof AccountExpiredException) {
				msg = SpringSecurityUtils.securityConfig.errors.login.expired
				new AccessLog(username: username, event:"Account Expired",
				eventmessage: msg,
				accesstime:new Date()).save()
			}
			else if (exception instanceof CredentialsExpiredException) {
				msg = SpringSecurityUtils.securityConfig.errors.login.passwordExpired
				new AccessLog(username: username, event:"Password Expired",
				eventmessage: msg,
				accesstime:new Date()).save()
			}
			else if (exception instanceof DisabledException) {
				msg = SpringSecurityUtils.securityConfig.errors.login.disabled
				new AccessLog(username: username, event:"Login Disabled",
				eventmessage: msg,
				accesstime:new Date()).save()
			}
			else if (exception instanceof LockedException) {
				msg = SpringSecurityUtils.securityConfig.errors.login.locked
				new AccessLog(username: username, event:"Login Locked",
				eventmessage: msg,
				accesstime:new Date()).save()
			}
			else if (exception instanceof IllegalStateException) {
				msg = SpringSecurityUtils.securityConfig.errors.login.locked
				new AccessLog(username: username, event:"Too slow to login, please try again",
				eventmessage: msg,
				accesstime:new Date()).save()
			}
			else {
				//The login failed and the account isn't disabled yet. Put a strike against the users account if they aren't over the limit yet.
				def userWhoFailedLogin = AuthUser.findByUsername(username)

				if(userWhoFailedLogin != null && userWhoFailedLogin.loginAttempts > 4)
				{
					userWhoFailedLogin.enabled = false
					userWhoFailedLogin.loginAttempts = 0
					userWhoFailedLogin.save()

					msg = SpringSecurityUtils.securityConfig.errors.login.exceeded
					new AccessLog(username: username, event:"Login Failed, Exceeded Password Attempts",
					eventmessage: msg,
					accesstime:new Date()).save()
				}
				else
				{
					if(userWhoFailedLogin != null)
					{
						userWhoFailedLogin.loginAttempts += 1
						userWhoFailedLogin.save()
					}

					msg = SpringSecurityUtils.securityConfig.errors.login.fail

					new AccessLog(username: username, event:"Login Failed",
					eventmessage: msg,
					accesstime:new Date()).save()
				}
			}
		}
		flash.message = msg
		redirect action: auth, params: params
	}

	/** cache controls */
	private void nocache(response) {
		response.setHeader('Cache-Control', 'no-cache') // HTTP 1.1
		response.addDateHeader('Expires', 0)
		response.setDateHeader('max-age', 0)
		response.setIntHeader ('Expires', -1) //prevents caching at the proxy server
		response.addHeader('cache-Control', 'private') //IE5.x only
	}

}
