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
* $Id: AuthUserDetails.groovy 9178 2011-08-24 13:50:06Z mmcduffie $
* @author $Author: mmcduffie $
* @version $Revision: 9178 $
*/
package com.recomdata.security

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.GenericFilterBean
import org.springframework.security.core.context.SecurityContextHolder;
import edu.hms.transmart.security.AuthUser;


class ChangePasswordFilter extends GenericFilterBean {	
	
	def changePasswordUrl
	def grailsApplication
	
	void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req
		HttpServletResponse response = (HttpServletResponse) res
		String requestURL = request.getRequestURL().toString();
		
		if(SecurityContextHolder.getContext().getAuthentication() != null)
		{
			def userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
			
			if(userDetails != null && userDetails != "anonymousUser")
			{	
				if (userDetails.forcePasswordReset == true) 
				{
					if (!(requestURL.endsWith("expiredPassword") || requestURL.endsWith("updateExpiredPassword") ) )
					{
						response.sendRedirect(this.servletContext.contextPath + changePasswordUrl)
						return;
					}
					
				}
			}
		}
		
		chain.doFilter(request, response)
	}
}