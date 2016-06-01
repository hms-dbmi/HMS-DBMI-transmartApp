<!DOCTYPE html>
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
<meta name='layout' content='none' />
<title>
	${grailsApplication.config.com.recomdata.searchtool.appTitle}
</title>
<g:if test="${!(grailsApplication.config.edu.harvard.transmart.googleanalytics.tracking.isEmpty())}">
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	  ga('create', '${grailsApplication.config.edu.harvard.transmart.googleanalytics.tracking}', 'auto');
	  ga('send', 'pageview');
	
	</script>
</g:if>
<script>
/**
 * http://stackoverflow.com/questions/19999388/check-if-user-is-using-ie-with-jquery
 *
 * detect IE
 * returns version of IE or false, if browser is not Internet Explorer
 */
function detectIE() {
    var ua = window.navigator.userAgent;

    var msie = ua.indexOf('MSIE ');
    if (msie > 0) {
        // IE 10 or older => return version number
        return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
    }

    var trident = ua.indexOf('Trident/');
    if (trident > 0) {
        // IE 11 => return version number
        var rv = ua.indexOf('rv:');
        return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
    }

    var edge = ua.indexOf('Edge/');
    if (edge > 0) {
       // Edge (IE 12+) => return version number
       return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
    }

    // other browser
    return false;
}

//If we detect IE, Hide the login form.
window.onload = function() {
    if(detectIE())
        {
    		document.getElementById("bannerRow").innerHTML="<td style='border: 2px solid black;padding: 30px;'><font style='color:lightred;font-family:cambria;font-size:16px;font-weight:bold;'>We've detected that you are attempting to use Internet Explorer to browse i2b2/tranSMART. <br />Please use Safari, Firefox or Chrome instead.</font></td>"
    		document.getElementById("loginWidgetRow").innerHTML=""
        }
}

</script>
</head>
<body>

	<div align="center" style="clear: both; margin-left: auto; margin-right: auto; margin-top: 20px; text-align: center">
		<table style="width: auto; border: 0px; text-align: center; margin: auto;" align="center">
			<tr>
				<td style="text-align: center; vertical-align: middle; margin-left: -40px;"><img
					src="${resource(dir:'images',file:grailsApplication.config.com.recomdata.searchtool.largeLogo)}" alt="Transmart" /></td>
			</tr>
			<tr id="bannerRow">
				<td>
					<g:if test="${!(grailsApplication.config.edu.harvard.transmart.bannermessage.isEmpty())}">
						
					</g:if> <g:if test="${flash.message}">
						<div class="message">
							${flash.message}
						</div>
					</g:if> &nbsp;</td>
			</tr>

			<tr id="loginWidgetRow">
				<td colspan=2 valign="middle" style="text-align: center; vertical-align: middle; border: 1px; font-size: 11px" nowrap="nowrap">
					<div id="frmAuth0Login">embeded area</div><%--
					<script src="//cdn.auth0.com/js/lock-8.min.js"></script> <script>
		  			  var lock = new Auth0Lock( '${grailsApplication.config.edu.harvard.transmart.auth0.client_id}', '${grailsApplication.config.edu.harvard.transmart.auth0.domain}' );
		  			  
		  			  lock.show({
		  			      "container": "frmAuth0Login",
		  			      "icon": "//dbmi.hms.harvard.edu/profiles/hmssf/themes/custom/hms_bootstrap/logo.png",
		  			      dict: {
			  			      // More: https://github.com/auth0/lock/blob/master/i18n/en.json
			  			      signin: {
				  			      title:'Please enter your e-mail. You will be redirected to your authentication provider.',
				  			    footerText: 'Please enter your e-mail. You will be redirected to your authentication provider.'
                              }
		  			       },
		  			      // See remembering or not information for Auht0 here: https://ask.auth0.com/t/rememberlastlogin-and-sso/618
		  			      rememberLastLogin: false,
		  			      sso: false,
		  			      callbackURL: "${resource(absolute: true)}${grailsApplication.config.edu.harvard.transmart.auth0.callback}",
										responseType : "code",
										disableSignupAction : true,
										disableResetAction : true,
										socialBigButtons : true,
										"authParams" : {
											"scope" : "openid profile"
										}
									});
						</script>
					--%><style>
#frmAuth0Login {
	width: 280px;
	margin: 40px auto;
	padding: 10px;
	border-width: 1px;
}

body .a0-iconlist {
	position: relative;
	top: 140%
}

div.a0-input-box {
	position: relative;
	top: -46%
}

                    body #a0-lock.a0-theme-default .a0-zocial.a0-eracommons {
                        background-color: purple; /* #cc3399; */
                    }

                    body #a0-lock.a0-theme-default .a0-zocial.a0-hms {
                        background-color: crimson;
                    }

                    body #a0-lock.a0-theme-default .a0-zocial.a0-eracommons img {
                       width: 32px;
                        hight: 32px;
                        padding-top: 8px;
                        padding-bottom: 9px;
                        padding-left: 3px;
                        padding-right: 3px;
                        vertical-align: middle;
                        text-transform: none;
                    }

                    body #a0-lock.a0-theme-default .a0-zocial.a0-hms {
                        content: "+";
                        margin-top: 6px;
                        background: lightcrimson url('${resource(dir: '/images', file: 'hmslogo.png')}');
                    }

                    body #a0-lock .a0-panel::after, #a0-lock .a0-panel::before {
                        content: none;
                        display: table;
                    }
                    
                    body #a0-lock.a0-theme-default .a0-panel .a0-icon-container .a0-image img {
                        height: 0px;
                    }
                    
                    body .a0-panel .a0-separator, 
                    body #a0-lock.a0-theme-default .a0-zocial.a0-block.a0-github,
                    body #a0-lock.a0-theme-default .a0-zocial.a0-block.a0-googleplus,
                    body .a0-panel .a0-emailPassword, body #a0-lock.a0-theme-default .bottom-content { /* */
                        display: none
                    }
                    </style>



				</td>
			</tr>
		</table>
	</div>
	<br />

	<div align="center" style="clear: both; margin-left: auto; margin-right: auto; margin-top: 20px; text-align: center">
		This application has been secured using standards published by the Harvard University Information Technology (HUIT) group. <br /> <a
			href="http://huit.harvard.edu/" target="_blank"><img src="${request.getContextPath()}/images/info_security_logo_rgb.png" width="150px" /></a>
	</div>
    <script
                  src="https://code.jquery.com/jquery-2.2.2.min.js"
                  integrity="sha256-36cp2Co+/62rEAAYHLmRCPIych47CvdM+uTBJwSzWjI="
                  crossorigin="anonymous"></script>

    <script src="//cdn.auth0.com/js/lock-8.min.js"></script>
	<script>
    var lock = new Auth0Lock( '${grailsApplication.config.edu.harvard.transmart.auth0.client_id}', '${grailsApplication.config.edu.harvard.transmart.auth0.domain}' );
    lock.on('signin ready', function() {
        var link = $('<a class="a0-zocial a0-eracommons" href="#"><img src="${resource(dir: '/images', file: 'eralogo.png')}"/><span style="text-transform: none;">eRA Commons or NIH e-mail</span></a>');
        link.appendTo('.a0-iconlist');
        link.on('click', function() {
            lock.getClient().login({connection: 'nih-gov-prod'});
            });
        });
    lock.on('signin ready', function() {
                              var link = $('<a class="a0-zocial a0-googleplus" href="#"><span>LOGIN WITH GOOGLE</span></a>');
                              link.appendTo('.a0-iconlist');
                              link.on('click', function() {
                                  lock.getClient().login({connection: 'google-oauth2'});
                              });
                          });
                          
    lock.on('signin ready', function() {
                              var link = $('<a class="a0-zocial a0-github" href="#"><span>LOGIN WITH GITHUB</span></a>');
                              link.appendTo('.a0-iconlist');
                              link.on('click', function() {
                                  lock.getClient().login({connection: 'github'});
                              });
                          });
    lock.on('signin ready', function() {
                              var link = $('<a class="a0-zocial a0-hms" href="#"><img src="${resource(dir: '/images', file: 'hmslogo.png')}" style="width: 28px; background-color: crimson; padding-top: 4px; padding-bottom: 3px; padding-left: 5px; padding-right: 5px;    vertical-align: middle"/><span style="text-transform: none">Harvard Medical School</span></a>');
                              link.appendTo('.a0-iconlist');
                              link.on('click', function() {
                                  lock.getClient().login({connection: 'hms-it'});
                              });
                          });

    lock.show({
        "container": "frmAuth0Login",
        //"icon": "${resource(dir: 'images', file: 'hmslogo.png')}",
        "dict": {
            // More: https://github.com/auth0/lock/blob/master/i18n/en.json
            signin: {
                title:"${grailsApplication.config.edu.harvard.transmart.bannermessage}",
                footerText: 'Please click one of the buttons above and you will be redirected to your provider\'s website.'
                    }
        },
         // See remembering or not information for Auht0 here: https://ask.auth0.com/t/rememberlastlogin-and-sso/618
        rememberLastLogin: false,
        sso: false,
        callbackURL: "${resource(absolute: true)}${grailsApplication.config.edu.harvard.transmart.auth0.callback}",
        responseType : "code",
        disableSignupAction : true,
        disableResetAction : true,
        socialBigButtons : true,
        connection: [''],
        "authParams" : {
            "scope" : "openid profile"
                }
        });
	</script>
</body>