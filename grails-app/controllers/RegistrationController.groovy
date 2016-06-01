import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

import transmart.UserManagerService
import us.monoid.web.Resty
import edu.hms.transmart.security.AuthUser
import edu.hms.transmart.security.Role
import grails.converters.JSON
import grails.util.Holders

class RegistrationController {
	def mail
	def userManager
	def logoutHandlers
	def springSecurityService

	def index() {

		if (session['credentials'] == null) {
			session["error_message"] ="Could not find credentials for registration."
			redirect(controller: 'login', model: [msg: 'Could not find credentials. Please try to login again'])
		}

		def creds = session["credentials"]
		log.info("Credentials: "+creds)

		def user = [:]
		try {
			def cu = new UserManagerService(creds.username)
			user = cu.getCurrentUser()
			
			log.info("Userdetails: "+user)
		} catch (Exception e) {
			log.error(e)
		}

		log.info("Registration starting for "+creds.username)

		// After saving the credentials, logout from the session
		if (user.level<1 && user.firstname) {
			log.info("User has already registered as "+user.firstname+" "+user.lastname)

			Authentication auth = org.springframework.security.core.context.SecurityContextHolder.context.authentication
			if (auth) {
				logoutHandlers.each  { handler->
					handler.logout(request,response,auth)
				}
			}
			log.info("User is now logged out, and redirect to NOTYET page.")
			redirect(action: 'notyet');
		}
		model: [user: user]
	}

	// Registration confirmation routine.
	//
	// Steps: Validate reCaptcha, via Google
	//			Find user, based on hidden variable on registration form
	//			Update user record with registration form information
	//			Set the basic (Level1) roles for the user
	//			Send notification e-mail to admin
	//			Send confirmation e-mail to user
	//			Set authentication in SpringSecurity
	//			Redirect to initial page
	//
	def confirm = {
		// Validate some of the form fields
		if (params.'g-recaptcha-response' == null || params.'g-recaptcha-response' == '') {
			new AccessLog(
					username: params.username?:params.email?:'unknown',
					event:"ERROR",
					eventmessage: "Did not receive reCaptcha information from registration confirmation form. Details:"+params.toString(),
					accesstime:new Date()
					).save()
			throw new RuntimeException('Captcha information is not received. Cannot validate other information until it is provided.')
		}
		// Verification parameters, per Googly's information
		// https://developers.google.com/recaptcha/docs/verify
		def r = new Resty()
		// Send a POST request to
		def confirmation = r.json(grailsApplication.config.edu.harvard.transmart.captcha.verifyurl,
				r.form(
				r.data('secret', grailsApplication.config.edu.harvard.transmart.captcha.secret),
				r.data('response', params.'g-recaptcha-response'?:'n/a')
				)
				);
		if (confirmation.success) {
			new AccessLog(
					username: params.username?:params.email?:'unknown',
					event:"captcha_verify-INFO",
					eventmessage: "Registration process has been allowed, per reCAPTCHAverification from "+request.remoteHost,
					accesstime:new Date()
					).save()
		} else {
			// If Google does not return a success message, log the error response and throw
			// description exception back to the user
			new AccessLog(username: params.username?:params.email?:'unknown',
			event:"captcha_verify-ERROR",
			eventmessage: confirmation.toObject().toString()+" from host "+request.remoteHost,
			accesstime:new Date()
			).save()
			throw new RuntimeException('Captcha verification step has failed.')
		}

		log.info("Searching for user account:"+params.username)
		def person = AuthUser.findByUsername(params.username)
		if (person == null) {
			log.error("The registration information for username:"+params.username?:"N/A"+" and e-mail:"+params.email?:"N/A"+" could not be recorded.")
			throw new RuntimeException('The username '+params.username+' was not authenticated previously. Cannot record registration information.')
		}

		try {
			// Find the user, based on the e-mail used to log-in for registration
			person.name = person.userRealName = (params.firstname?:'')+' '+(params.lastname?:'')
			person.email = params.email

			// Save the credentials information with the user record, in the database
			def user_description = [:] << params
			user_description.access_token = session['credentials'].access_token
			user_description.id_token = session['credentials'].id_token
			user_description.connection = session['credentials'].connection
			user_description.picture = session['credentials'].picture?:''
			user_description.remove('g-recaptcha-response')

			person.description = user_description as JSON

			def status = person.save(flush: true, failOnError: true)
			log.info "Saved new user registration information for "+params.email
		}
		catch (Exception e) {
			log.error "Could not save record:"+e.class+"/"+e.getMessage()
			throw new RuntimeException("Could not save record. "+e.class+"/"+e.getMessage())
		}


		// Send notification to admin that a user has completed the sign-up form
		def mail_params = [
			to: grailsApplication.config.edu.harvard.transmart.email.support,
			subject: "Registration Request",
			body: g.render(template: "email_signup", model:[
				person: JSON.parse(person.description)
			]),
		]
		EmailSenderJob.triggerNow(mail_params)
		log.debug "Sent `Registration Request` e-mail to administrator(s)"

		new AccessLog(
				username: params.username?:params.email?:'unknown',
				event:"user_registration-INFO",
				eventmessage: "New user ${params.email} has been registered",
				accesstime:new Date()
				).save()


		// Send registration confirmation e-mail to the user, once the form has been submitted.
		mail_params = [
			to: person.email,
			subject: "Registration Confirmation",
			body: g.render(template: "email_thankyou", model:[
				person: person
			]),
		]
		EmailSenderJob.triggerNow(mail_params)
		log.debug "Sent `Registration Confirmation` e-mail to user"
		new AccessLog(
				username: params.email,
				event:"user_registration-INFO",
				eventmessage: "Confirmation e-mails for ${params.email} has been sent",
				accesstime:new Date()
				).save()

		if (Holders.config.edu.harvard.transmart.access?.level1 && Holders.config.edu.harvard.transmart.access.level1.equalsIgnoreCase('auto')) {
			// If configuration is set to auto-approve, go ahead and assign the basic roles.
			Role.findByAuthority("ROLE_STUDY_OWNER").addToPeople(person);
			Role.findByAuthority("ROLE_PUBLIC_USER").addToPeople(person);
			log.debug "Assigned basic, Level1 access to new user"

			// If configuration is set to auto-approve, after filling out the registration form
			// the user will be redirected to the default internal page of the applications.
			springSecurityService.reauthenticate(params.username)
			log.info("Automated approval is set. User "+params.username+" has roles assigned and logged into the app.")

			redirect(uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl)
		} else {
			log.info("Automated approval is NOT set. User "+params.username+" needs to wait for administrator to approve.")
			redirect(action: 'thankyou')
		}
	}

	def thankyou = {
		Authentication auth = org.springframework.security.core.context.SecurityContextHolder.context.authentication
		if (auth) {
			logoutHandlers.each  { handler->
				handler.logout(request,response,auth)
			}
		}
		render(view: 'thankyou')
	}

	def cannotregister = {  def details = [msg: session["error_message"]]; render details as JSON  }

	def notyet = {
		Authentication auth = org.springframework.security.core.context.SecurityContextHolder.context.authentication
		if (auth) {
			logoutHandlers.each  { handler->
				handler.logout(request,response,auth)
			}
		}
	}
}
