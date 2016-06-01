
import edu.hms.transmart.security.AuthUser
import grails.converters.JSON

class ApiController {

	def springSecurityService
	
	def userlist = {
		render AuthUser.list() as JSON
	}

	def userprofile = {
		def user = AuthUser.findById(params.id)
		render user  as JSON
	}
	
	def currentuser = {
		log.info("Getting currentuser information for "+springSecurityService.getPrincipal().username)
		def user = AuthUser.findByUsername(springSecurityService.getPrincipal().username)
		render user  as JSON
	}
}

