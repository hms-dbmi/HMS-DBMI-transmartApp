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
        <title>User Account</title>
    </head>
    <body>
		
		<br />
		
		<div id="userOptions" style='font: 12px tahoma,arial,helvetica;margin: 10px;'>
		
			<span style='font-size: 18px; font-weight: bold;'>Your password has expired! Please enter a new one below.</span>
			
			<br />
			<br />
			
			<div style='color:red;font-weight:bold;font-size: 18px;'>${flash.message}</div>
			
			<br />
			
			<g:form action='updateExpiredPassword'>
				
				Passwords must meet the following requirements set forth by the <b><i>Harvard University Information Security (HUIT) Policy</i></b>. Please see the following link for more information <a href='http://security.harvard.edu/book/information-security-policy'>HUIT</a>

				<br />

				- Minimum 10 Characters, Maximum 64 Characters<br />
				- Cannot match your previous password<br />
				- Cannot be the same as your username<br />
				- Cannot be a common password or English word.<br />
				- Must contain at least one character from 3 of the following groups (Uppercase Letters, Lowercase Letters, Numbers, Special Characters !@#$%)<br />

				<br />
				
				<b>Current User</b> : ${currentUser} <br />
				<b>Current Password</b> : <g:passwordField name='currentPassword' value=""/><br />
				<b>New Password</b> &nbsp;&nbsp;&nbsp; : <g:passwordField name='newPassword' value=""/><br />
				<br />
				<input type='submit' value='Update Password' />
			</g:form>
		</div>
     </body>
</html>