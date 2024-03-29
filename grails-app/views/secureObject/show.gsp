<!--
  tranSMART - translational medicine data mart
  
  Copyright 2008-2012 Janssen Research & Development, LLC.
  
  This product includes software developed at Janssen Research & Development, LLC.
  
  This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
  as published by the Free Software  * Foundation, either version 3 of the License, or (at your option) any later version, along with the following terms:
  1.	You may convey a work based on this program in accordance with section 5, provided that you retain the above notices.
  2.	You may convey verbatim copies of this program code as you receive it, in any medium, provided that you retain the above notices.
  
  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS    * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
  
 
-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="admin" />
        <title>Show SecureObject</title>
    </head>
    <body>
        <div class="body">
            <h1>Show SecureObject</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>


                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>

                            <td valign="top" class="value">${fieldValue(bean:secureObjectInstance, field:'id')}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Bio Data Id:</td>

                            <td valign="top" class="value">${fieldValue(bean:secureObjectInstance, field:'bioDataId')}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Data Type:</td>

                            <td valign="top" class="value">${fieldValue(bean:secureObjectInstance, field:'dataType')}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Bio Data Unique Id:</td>

                            <td valign="top" class="value">${fieldValue(bean:secureObjectInstance, field:'bioDataUniqueId')}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Concept Paths:</td>

                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="c" in="${secureObjectInstance.conceptPaths}">
                                    <li><g:link controller="secureObjectPath" action="show" id="${c.id}">${c?.conceptPath?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Display Name:</td>

                            <td valign="top" class="value">${fieldValue(bean:secureObjectInstance, field:'displayName')}</td>

                        </tr>
                    </tr>
						<tr class="prop">
						<td valign="top" class="name">User/Group With Access:</td>
						<td valign="top" class="value">
							<ul>
							<g:each in="${SecureObjectAccess.findAllBySecureObject(secureObjectInstance,[sort:accessLevel])}" var='soa'>
								<g:if test="${soa.principal.type=='GROUP'}">
								<li><g:link controller="userGroup" action="show" id="${soa.principal.id}">${soa.getPrincipalAccessName()}</g:link></li>
								</g:if>
								<g:else>
															<li><g:link controller="authUser" action="show" id="${soa.principal.id}">${soa.getPrincipalAccessName()}</g:link></li>

								</g:else>
							</g:each>
							</ul>
						</td>
					</tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${secureObjectInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
