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


import org.springframework.security.core.Authentication
import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails

import com.recomdata.security.AuthUserDetails

import edu.hms.transmart.security.AuthUser

class Auth0AuthenticationToken implements Authentication, CredentialsContainer {

	Object credentials
	Object username
	Object principal
	Object details
	Collection<GrantedAuthority> authorities = []
	Boolean authenticated = false

	public Auth0AuthenticationToken(String id_token) {
		this.username = transmart.AuthorizationService.getUsername(id_token)

		log.debug("Token for "+this.username+" has been verified.")

		def user = AuthUser.findByUsername(this.username)
		log.info("User details for "+user)

		this.principal = new AuthUserDetails(
				user.username,
				user.passwd,
				user.enabled,
				!user.accountExpired,
				true,
				!user.accountLocked,
				[
					new GrantedAuthorityImpl('ROLE_PUBLIC_USER')
				],
				user.id,
				user.userRealName,
				user.passwordExpired)

		this.credentials = id_token
		this.authenticated = true
		this.authorities = [
			new GrantedAuthorityImpl('ROLE_PUBLIC_USER')
		]
		log.debug("Authentication object has been created and validated.")
	}

	@Override
	boolean isAuthenticated() {
		log.debug("isAuthenticated() "+authenticated)
		return authenticated
	}

	@Override
	void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		log.debug("setAuthenticated() to "+authenticated)
		this.authenticated = authenticated
	}


	@Override
	String getName() {
		return this.username
	}

	@Override
	void eraseCredentials() {
		if (this.credentials instanceof CredentialsContainer) {
			((CredentialsContainer)this.credentials).eraseCredentials();
		}
	}
}