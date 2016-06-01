import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.multipart.commons.CommonsMultipartFile

import edu.hms.transmart.security.AuthUser
import edu.hms.transmart.security.Role
import grails.converters.JSON
import grails.util.Holders


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

class UserController {
	def springSecurityService
	def passwordEncoder
	def userManager

	def index = {
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"OwnProfileView", accesstime:new Date()).save()
		
		def myuser = new transmart.UserManagerService(springSecurityService.getPrincipal().username)
		def user = myuser.getCurrentUser()
		
		if (request.method == 'POST') {
			// Save all form fields
			params.each { user << it }

			// Add flag about access requested (TODO: This should be in a different controller/action)
			user << [is_access_requested:'y']

			def person = AuthUser.findByUsername(user.username)
			person.description =  user as JSON
			person.save()
			log.info("User details for "+person.username+" have been updated.")
		}
		model:[user: user]
	}
	
	def save = {
		def myuser = new transmart.UserManagerService(springSecurityService.getPrincipal().username)
		def user_details = myuser.getCurrentUser()
		
		// Cleanup
		params.remove('_action_Save')
		params.remove('controller')
		// Update user's details from parameters
		params.each{ user_details << it}
		
		myuser.setDescription(user_details)
		redirect(action: 'index')
	}

	def access = {

		user = transmart.UserManagerService(springSecurityService.getPrincipal().username).getCurrentUser()

		if (request.method == 'POST') {
			// Save data from the form submitted.
			user.research_title = params.research_title
			user.research_aims = params.research_aims
			user.research_summary = params.research_summary

			// Save uploaded file, assuming that the correct file type is
			// filtered out by the UI
			def CommonsMultipartFile uploadedFile = params.uploaded_file
			def f = request.getFile('uploaded_file')
			f.transferTo(new File(Holders.config.com.recomdata.plugins.tempFolderDirectory+f.fileItem.name))

			// Save details about the uploaded file
			if (uploadedFile.empty) {

			} else {
				user.research_irbfile = uploadedFile.originalFilename
				user.research_irbfile_type = uploadedFile.contentType
				user.research_irnfile_size = (String)uploadedFile.size
			}

			// Update the user record with the submitted information
			def person = AuthUser.findByUsername(springSecurityService.getPrincipal().username)
			person.description = user as JSON
			person.save()

			def emailBody = g.render(template: "/notification/email_access_request", model:[
				user: person
			])
			def mail_params = [
				to: Holders.config.edu.harvard.transmart.email.support,
				subject: "Level 2 Access Request",
				body: (String) emailBody,
				attachment: 'true',
				filename: f.fileItem.name,
				filepath: Holders.config.com.recomdata.plugins.tempFolderDirectory+f.fileItem.name
			]
			EmailSenderJob.triggerNow(mail_params)

			new AccessLog(
					username: springSecurityService.getPrincipal().username,
					event:"AccessRequestSubmitted",
					accesstime:new Date()
					).save()
		} else {
			log.info("Inquiring about access request.")
			new AccessLog(
					username: springSecurityService.getPrincipal().username,
					event:"AccessRequestInquire",
					accesstime:new Date()
					).save()
		}
		log.info("Finished processing access request.")
		model:[
			user: user,
			roles: Role.list()
		]
	}

	@Deprecated
	def useraccount = {

		def loggedInUserName = springSecurityService.getPrincipal().username

		new AccessLog(username: loggedInUserName, event:"User Account Viewed", accesstime:new Date()).save()

		render(template:"useraccount", model:[currentUser:loggedInUserName, details: session, user: AuthUser.findByUsername(loggedInUserName)])
	}

	def expiredPassword = {

		def loggedInUserName = springSecurityService.getPrincipal().username

		new AccessLog(username: loggedInUserName, event:"User Account Viewed", accesstime:new Date()).save()

		render(template:"useraccount_expired", model:[currentUser:loggedInUserName])
	}

	// Password is no longerg maintained inside the application
	@Deprecated
	def updateExpiredPassword = {
		String username = springSecurityService.getPrincipal().username

		String password = params.currentPassword
		String newPassword = params.newPassword

		if (!username) {
			flash.message = 'Sorry, an error has occurred<br />'
			redirect controller: 'login', action: 'auth'
			return
		}

		AuthUser user = AuthUser.findByUsername(username)

		//Password not found in form.
		if (!newPassword) {
			flash.message = 'Password field empty, please enter a new password.<br />'
			render template:"useraccount_expired", model: [currentUser: username]
			return
		}

		//Current password is wrong.
		if (!passwordEncoder.isPasswordValid(user.passwd, password, null)) {
			flash.message = 'Current password is incorrect.<br />'
			render template:"useraccount_expired", model: [currentUser: username]
			return
		}

		//Password is same as current one.
		if (passwordEncoder.isPasswordValid(user.passwd, newPassword, null /*salt*/)) {
			flash.message = 'Please choose a different password from your current one.<br />'
			render template:"useraccount_expired", model: [currentUser: username]
			return
		}

		//Username and password can't match.
		if (user.username.equals(newPassword)) {
			flash.message = 'Your password cannot match your username.<br />'
			render template:"useraccount_expired", model: [currentUser: username]
			return
		}

		//Password too long/too short.
		if (newPassword.length() < 10 || newPassword.length() > 64)
		{
			flash.message = 'The password was the incorrect length.<br />'
			render template:"useraccount_expired", model: [currentUser: username]
			return
		}

		//Verify password isn't in the dictionary table.
		if(PasswordDictionaryTerm.exists(newPassword))
		{
			flash.message = 'Password found in dictionary, please choose another.<br />'
			render template:"useraccount_expired", model: [currentUser: username]
			return
		}

		//Password doesn't meet complexity.
		def passwordCheckArray = []

		if(newPassword.matches('.*[a-z].*')) passwordCheckArray.push("ALPHA_LOWER")
		if(newPassword.matches('.*[A-Z].*')) passwordCheckArray.push("ALPHA_UPPER")
		if(newPassword.matches('.*[0-9].*')) passwordCheckArray.push("NUMERIC")
		if(newPassword.matches('.*[!@#$%].*')) passwordCheckArray.push("SPECIAL_CHARCTER")

		if (passwordCheckArray.size() < 3)
		{
			flash.message = 'Your password did not meet password complexity requirements.<br />'
			render template:"useraccount_expired", model: [currentUser: username]
			return
		}

		user.passwd = passwordEncoder.encodePassword(newPassword, null)
		user.passwordExpired = false
		user.save() // if you have password constraints check them here

		new AccessLog(username: username, event:"User Password Updated", accesstime:new Date()).save()

		//We need to remove the user so they get the proper authentication form.
		flash.message = 'Password succsefully change, please log in again.<br />'

		SecurityContextHolder.clearContext()

		redirect controller: 'login', action: 'auth'
	}

	// Password is no longer maintained inside the application
	@Deprecated
	def updatePassword = {

		String username = springSecurityService.getPrincipal().username
		String password = params.currentPassword
		String newPassword = params.newPassword

		if (!username) {
			flash.message = 'Sorry, an error has occurred<br />'
			redirect controller: 'login', action: 'auth'
			return
		}

		AuthUser user = AuthUser.findByUsername(username)

		//Password not found in form.
		if (!newPassword) {
			flash.message = 'Password field empty, please enter a new password.<br />'
			render template:"useraccount", model: [currentUser: username]
			return
		}

		//Current password is wrong.
		if (!passwordEncoder.isPasswordValid(user.passwd, password, null)) {
			flash.message = 'Current password is incorrect.<br />'
			render template:"useraccount", model: [currentUser: username]
			return
		}

		//Password is same as current one.
		if (passwordEncoder.isPasswordValid(user.passwd, newPassword, null /*salt*/)) {
			flash.message = 'Please choose a different password from your current one.<br />'
			render template:"useraccount", model: [currentUser: username]
			return
		}

		//Username and password can't match.
		if (user.username.equals(newPassword)) {
			flash.message = 'Your password cannot match your username.<br />'
			render template:"useraccount", model: [currentUser: username]
			return
		}

		//Password too long/too short.
		if (newPassword.length() < 10 || newPassword.length() > 64)
		{
			flash.message = 'The password was the incorrect length.<br />'
			render template:"useraccount", model: [currentUser: username]
			return
		}

		//Verify password isn't in the dictionary table.
		if(PasswordDictionaryTerm.exists(newPassword))
		{
			flash.message = 'Password found in dictionary, please choose another.<br />'
			render template:"useraccount", model: [currentUser: username]
			return
		}

		//Password doesn't meet complexity.
		def passwordCheckArray = []

		if(newPassword.matches('.*[a-z].*')) passwordCheckArray.push("ALPHA_LOWER")
		if(newPassword.matches('.*[A-Z].*')) passwordCheckArray.push("ALPHA_UPPER")
		if(newPassword.matches('.*[0-9].*')) passwordCheckArray.push("NUMERIC")
		if(newPassword.matches('.*[!@#$%].*')) passwordCheckArray.push("SPECIAL_CHARCTER")

		if (passwordCheckArray.size() < 3)
		{
			flash.message = 'Your password did not meet password complexity requirements.<br />'
			render template:"useraccount", model: [currentUser: username]
			return
		}

		user.passwd = passwordEncoder.encodePassword(newPassword, null)
		user.passwordExpired = false
		user.save() // if you have password constraints check them here

		new AccessLog(username: springSecurityService.getPrincipal().username, event:"User Password Updated", accesstime:new Date()).save()

		redirect controller: 'login', action: 'auth'
	}

}