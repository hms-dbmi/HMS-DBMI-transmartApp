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
		<meta name="layout" content="main" />
		<script type="text/javascript" src="${resource(dir:'js', file:'utilitiesMenu.js')}"></script>
		<script type="text/javascript" src="${resource(dir:'js/jQuery', file:'jquery-1.7.1.min.js')}"></script>
		<script type="text/javascript" src="${resource(dir:'js/jQuery', file:'jquery-ui-1.8.17.custom.min.js')}"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
		
		<script type="text/javascript" charset="utf-8">
			Ext.BLANK_IMAGE_URL = "${resource(dir:'js', file:'ext/resources/images/default/s.gif')}";

			// set ajax to 90*1000 milliseconds
			Ext.Ajax.timeout = 180000;

			Ext.onReady(function()
		    {
			    Ext.QuickTips.init();

	            var helpURL = '${grailsApplication.config.com.recomdata.searchtool.adminHelpURL}';
	            var contact = '${grailsApplication.config.com.recomdata.searchtool.contactUs}';
	            var appTitle = '${grailsApplication.config.com.recomdata.searchtool.appTitle}';
	            var buildVer = 'Build Version: <g:meta name="environment.BUILD_NUMBER"/> - <g:meta name="environment.BUILD_ID"/>';
				   
	            var viewport = new Ext.Viewport({
	                layout: "border",
	                items:[new Ext.Panel({                          
                       region: "center",  
                       tbar: createUtilitiesMenu(helpURL, contact, appTitle,'${request.getContextPath()}', buildVer, 'admin-utilities-div', GLOBAL.CurrentUserName), 
                       autoScroll:true,                     
                       contentEl: "page"
                    })]
	            });
	            viewport.doLayout();

	            var pageInfo = {
					basePath :"${request.getContextPath()}"
				}
	        });
		</script>
		
    </head>
    <body>
		<g:render template="/layouts/commonheader" />
		
		<table id="commonHeaderMenu" class="menuDetail" width="100%" style="border-bottom: 2px solid #ddd;">
			<tr>
				<th style="text-align: left;">
					<!-- menu links -->
					<table class="menuDetail" style="width: auto;">
				    	<tr>
							<th class="menuLink"><a href="${grailsApplication.config.com.recomdata.searchtool.contactUs}">Contact Us</a></th>
							<th class="menuLink"><a href="#" onclick="Ext.Msg.alert('${grailsApplication.config.com.recomdata.searchtool.appTitle}', 'Build Version: <g:meta name="environment.BUILD_NUMBER"/> - <g:meta name="environment.BUILD_ID"/>'); ">About</a></th>
							<th class="menuLink"><a href="${createLink([controller:'user', action: 'useraccount'])}">User Account</a></th>
							<th class="menuLink"><a href="${createLink([controller:'login', action: 'forceAuth'])}">Login</a></th>
							<th class="menuLink"><a href="${createLink([controller:'logout', action: 'index'])}">Log Out</a></th>
		
				       	<tr>
				 	</table>
				</th> 		 
			</tr>
		</table>
		
		<br />
		
		<div id="userOptions" style='font: 12px tahoma,arial,helvetica;margin: 10px;'>
		
			<span style='font-size: 18px; font-weight: bold;'>Updating your password</span>
			
			<br />
			
			<div style='color:red;font-weight:bold;'>${flash.message}</div>
			
			<br />
			
			<g:form action='updatePassword'>
				
				<img src="${request.getContextPath()}/images/info_security_logo_rgb.png"  style="height: 100; width: 300;"/> <br />
				
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