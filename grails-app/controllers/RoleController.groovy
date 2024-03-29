import edu.hms.transmart.security.Role;

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
 * $Id: RoleController.groovy 9178 2011-08-24 13:50:06Z mmcduffie $
 * @author $Author: mmcduffie $
 * @version $Revision: 9178 $
 */

/**
 * Authority Controller.
 */
class RoleController {
	
	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']
	
	def springSecurityService
	
	def index = {
		redirect action: list, params: params
	}
	
	/**
	 * Display the list authority page.
	 */
	def list = {
		
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"List Roles", accesstime:new Date() ).save()
		
		if (!params.max) {
			params.max = grailsApplication.config.com.recomdata.search.paginate.max
		}
		[authorityList: Role.list(params)]
	}
	
	/**
	 * Display the show authority page.
	 */
	def show = {
		def authority = Role.get(params.id)
		
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"Show Role", accesstime:new Date(), eventmessage: "Role ID - " + params.id ).save()
		
		if (!authority) {
			flash.message = "Role not found with id $params.id"
			redirect action: list
			return
		}
		
		[authority: authority]
	}
	
	/**
	 * Delete an authority.
	 */
	def delete = {
		def authority = Role.get(params.id)
		
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"Delete Role", accesstime:new Date(), eventmessage: "Role ID - " + params.id ).save()
		
		if (!authority) {
			flash.message = "Role not found with id $params.id"
			redirect action: list
			return
		}
		
		springSecurityService.deleteRole(authority)
		
		flash.message = "Role $params.id deleted."
		redirect action: list
	}
	
	/**
	 * Display the edit authority page.
	 */
	def edit = {
		def authority = Role.get(params.id)
		
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"Edit Role", accesstime:new Date(), eventmessage: "Role ID - " + params.id ).save()
		
		if (!authority) {
			flash.message = "Role not found with id $params.id"
			redirect action: list
			return
		}
		
		[authority: authority]
	}
	
	/**
	 * Authority update action.
	 */
	def update = {
		
		def authority = Role.get(params.id)
		
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"Update Role", accesstime:new Date(), eventmessage: "Role ID - " + params.id ).save()
		
		if (!authority) {
			flash.message = "Role not found with id $params.id"
			redirect action: edit, id: params.id
			return
		}
		
		long version = params.version.toLong()
		if (authority.version > version) {
			authority.errors.rejectValue 'version', 'authority.optimistic.locking.failure',
			'Another user has updated this Role while you were editing.'
			render view: 'edit', model: [authority: authority]
			return
		}
		
		if (springSecurityService.updateRole(authority, params)) {
			springSecurityService.clearCachedRequestmaps()
			redirect action: show, id: authority.id
		}
		else {
			render view: 'edit', model: [authority: authority]
		}
	}
	
	/**
	 * Display the create new authority page.
	 */
	def create = {
		
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"Create Role", accesstime:new Date() ).save()
		
		[authority: new Role()]
	}
	
	/**
	 * Save a new authority.
	 */
	def save = {		
		def role = new Role()
		role.properties = params
		
		new AccessLog(username: springSecurityService.getPrincipal().username, event:"Save Role", accesstime:new Date(), eventmessage: "Role ID - " + params.id ).save()
		
		// authority valdiation
		if(params.authority==null || params.authority=="") {
			flash.message = "Please enter a role name"
			role.authority = params.authority
			role.description = params.description
			return render(view:'create', model:[authority:role])
		}	
		
		// description validation
		if(params.description==null || params.description=="") {
			flash.message = "Please enter a role description"
			role.authority = params.authority
			role.description = params.description
			return render(view:'create', model:[authority:role])
		}
		
		if (role.save()) {
			redirect action: show, id: role.id
		}
		else {
			render view: 'create', model: [authority: role]
		}
	}
}
