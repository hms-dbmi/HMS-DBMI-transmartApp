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
 * $Id: LogoutController.groovy 10098 2011-10-19 18:39:32Z mmcduffie $
 * @author $Author: mmcduffie $
 * @version $Revision: 10098 $
 */

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.Authentication

/**
 * Logout Controller just writes an entry to the log and redirects to the login page (Identity Vault or form based)
 */
class LogoutController {
	def springSecurityService
	def logoutHandlers
	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */	
	def index = {
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"Logout", accesstime:new Date()).save()
		
		log.info("Logging out "+springSecurityService.getPrincipal().username+" from SpringSecurity")
		// Log out of SpringSecurity
		Authentication auth = org.springframework.security.core.context.SecurityContextHolder.context.authentication
		if (auth) {
			logoutHandlers.each  { handler->
				handler.logout(request,response,auth)
			}
		}
		log.info("Logged out of SpringSecurity")
		
		// TO-DO: The protocol/host/port combo needs to be parameterized and not hard-coded here!!!
		def logoutURL = "https://avillachlab.auth0.com/v2/logout?federated&returnTo="+
		resource(absolute: true)+SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
		log.info("logoutURL: "+logoutURL)
		redirect(url: logoutURL)
	}
}