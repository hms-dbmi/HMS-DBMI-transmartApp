import search.GeneSignature
import edu.hms.transmart.security.AuthUser
import edu.hms.transmart.security.Role
import grails.converters.JSON
import grails.util.Holders

class AdminController {

	def springSecurityService

	def settings = {
		def propertyMap = transmart.PropertyService.getPropertiesMap()
		def sortedkeys = propertyMap.keySet().sort()

		model : [props: propertyMap, keys: sortedkeys]
	}

	def userlist = {
		def loggedInUserName = springSecurityService.getPrincipal().username
		def alertList = []

		if (params.state) {
			// Admin is changing state of a user
			AuthUser user = AuthUser.get(params.id)

			def ldescription = 'Unknown'

			Role.findAll().each { it.removeFromPeople(user) }
			switch (params.state) {
				case "level0":
					ldescription = 'No Access'
					break;
				case "level1":
					ldescription = 'Open Access'
					Role.findByAuthority("ROLE_STUDY_OWNER").addToPeople(user);
					Role.findByAuthority("ROLE_PUBLIC_USER").addToPeople(user);
					break;
				case "level2":
					ldescription = 'Download Access'
					Role.findByAuthority("ROLE_DATASET_EXPLORER_ADMIN").addToPeople(user)
					Role.findByAuthority("ROLE_PUBLIC_USER").addToPeople(user)
					break;
				case "admin":
					ldescription = 'Full Access'
					Role.findByAuthority("ROLE_ADMIN").addToPeople(user)
					Role.findByAuthority("ROLE_PUBLIC_USER").addToPeople(user)
					break;
				default:
					break;
			}

			def alertMsg = "User <b>${user.username}</b> has been granted <b>${params.state}</b> access."
			// Display in the logfile
			log.info alertMsg
			// Add event to the AccessLog table
			new AccessLog(username: loggedInUserName, event:"GrantAccess", eventmessage: alertMsg, accesstime:new Date()).save()
			// Display on the screen.
			alertList.add(alertMsg)

			// Notify user, that access has been granted.
			def params = [
				to: user.email,
				subject: "Access Granted",
				body: g.render(template: "/notification/email_accessgranted", model:[
					levelname: params.state,
					leveldescription: ldescription
				]),
			]
			EmailSenderJob.triggerNow(params)

			redirect(action:"userlist", alerts: alertList)

		} else {
			new AccessLog(username: loggedInUserName, event:"View userlist", accesstime:new Date()).save()
		}

		model: [users: AuthUser.list(), alerts: alertList]
	}

	def userprofile = {
		def usr = AuthUser.get(params.id)
		model: [id: usr.id ]
	}

	// Delete a selected user account.
	def deleteuser = {

		new AccessLog(username: springSecurityService.getPrincipal().username, event:"User Account Deleted", accesstime:new Date()).save()

		def person = AuthUser.get(params.id)

		if (person) {
			def userName = person.username
			def authPrincipal = springSecurityService.getPrincipal()
			if (!(authPrincipal instanceof String) && authPrincipal.username == userName) {
				flash.message = "You can not delete yourself, please login as another admin and try again"
			}
			else {
				log.info("Deleting ${person.username} from the roles")
				Role.findAll().each {it.removeFromPeople(person)}
				log.info("Deleting ${person.username} from secure access list")
				AuthUserSecureAccess.findAllByAuthUser(person).each {it.delete()}
				log.info("Deleting the gene signatures created by ${person.username}")
				GeneSignature.findAllByCreatedByAuthUser(person).each {it.delete()}
				log.info("Finally, deleting ${person.username}")
				person.delete()

				def msg = "$person.userRealName has been deleted."
				flash.message = msg
				new AccessLog(username: userName, event:"User Deleted",
				eventmessage: msg,
				accesstime:new Date()).save()
			}
		}
		else {
			flash.message = "User not found with id $params.id"
		}
		redirect(action: "userlist")
	}

	// View a selected user's profile information
	def profileview = {
		def usr = AuthUser.get(params.id)
		model: [usr: usr, usrdetails: usr as JSON]
	}

	def testmail = {
		def params = [
			to: params.to?:Holders.config.edu.harvard.transmart.email.support,
			subject: "Test Email",
			body: g.render(template: params.template?:'/notification/email_accessgranted', model:[
				levelname: 'Level9',
				leveldescription: 'Imaginary Level'
			]),
		]
		EmailSenderJob.triggerNow(params)
		render "SUCCESS?"
	}

}

