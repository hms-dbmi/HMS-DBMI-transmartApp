<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta name="layout" content="none" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- So that mobile will display zoomed in -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- enable media queries for windows phone 8 -->
<meta name="format-detection" content="telephone=no">
<!-- disable auto telephone linking in iOS -->
<title>User Registration</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	-ms-text-size-adjust: 100%;
	-webkit-text-size-adjust: 100%;
}

table {
	border-spacing: 0;
}

table td {
	border-collapse: collapse;
}

.ExternalClass {
	width: 100%;
}

.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font,
	.ExternalClass td, .ExternalClass div {
	line-height: 100%;
}

.ReadMsgBody {
	width: 100%;
	background-color: #ebebeb;
}

table {
	mso-table-lspace: 0pt;
	mso-table-rspace: 0pt;
}

img {
	-ms-interpolation-mode: bicubic;
}

.yshortcuts a {
	border-bottom: none !important;
}

@media screen and (max-width: 599px) {
	.force-row, .container {
		width: 100% !important;
		max-width: 100% !important;
	}
}

@media screen and (max-width: 400px) {
	.container-padding {
		padding-left: 12px !important;
		padding-right: 12px !important;
	}
}

.ios-footer a {
	color: #aaaaaa !important;
	text-decoration: underline;
}
</style>
</head>

<body style="margin: 0; padding: 0;" bgcolor="#F0F0F0" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

	<!-- 100% background wrapper (grey background) -->
	<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0" bgcolor="#F0F0F0">
		<tr>
			<td align="center" valign="top" bgcolor="#F0F0F0" style="background-color: #F0F0F0;"><br> <!-- 600px container (white background) -->
				<table border="0" width="600" cellpadding="0" cellspacing="0" class="container" style="width: 600px; max-width: 600px">
					<tr>
						<td class="container-padding content" align="left"
							style="padding-left: 24px; padding-right: 24px; padding-top: 12px; padding-bottom: 12px; background-color: #ffffff"><img
							src="${grailsApplication.config.edu.harvard.transmart.email.logo}" height="50px" /><br />
							<hr />
							<div class="title" style="font-family: Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 600; color: #374550">New User
								Registration</div> <br />
							<div class="body-text" style="font-family: Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; text-align: left; color: #333333">
								The following user has registered with the <strong>
									${grailsApplication.config.edu.harvard.transmart.instance.name?:'i2b2/tranSMART'}
								</strong> application.<br /> <br />

								<table class="table" width="90%" cellpadding="5px">
									<tbody>
                                        <tr>
                                            <th>Username/Email</th>
                                            <td>
                                                ${person.email}
                                            </td>
                                        </tr>
										<tr>
											<th>Name</th>
											<td>
												${person.firstname} ${person.lastname}
											</td>
										</tr>
										<tr>
											<th>Phone</th>
											<td>
												${person.phone}
											</td>
										</tr>
                                        <tr>
                                            <th>Affiliation</th>
                                            <td>
                                                ${person.institution}
                                            </td>
                                        </tr>
                                         <tr>
                                            <th>User Type</th>
                                            <td>
                                                ${person.usertype}
                                            </td>
                                        </tr>
										<tr>
											<th>Professional Title</th>
											<td>
												${person.title}
											</td>
										</tr>
										<tr>
											<th>Degree</th>
											<td>
												${person.degree}
											</td>
										</tr>
                                        <tr>
                                            <th>Is this request related to a funded grant?</th>
                                            <td>
                                                ${((person.is_funded_grant!=null && person.is_funded_grant=='on')?'Yes':'No')}
                                            </td>
                                        </tr>
                                         <tr>
                                            <th>Will the data be used to support a grant proposal?</th>
                                            <td>
                                                ${((person.is_grant_proposal!=null && person.is_grant_proposal=='on')?'Yes':'No')}
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Will the data be used for teaching a class, or for personal use?</th>
                                            <td>
                                                ${((person.is_personal_use!=null && person.is_personal_use=='on')?'Yes':'No')}
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Disase Interest</th>
                                            <td>
                                                ${person.disease}
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Research Goal</th>
                                            <td>
                                                ${person.about}
                                            </td>
                                        </tr>
                                        <tr>
                                        <td colspan="2" align="center">...</td>
                                        </tr>
                                        <tr>
                                            <th>Server Name</th>
                                            <td>
                                                ${resource(absolute: true)}
                                            </td>
                                        </tr>
                                        

									</tbody>
								</table>
								<br />
								<br />
							</div></td>
					</tr>
					<tr>
						<td class="container-padding footer-text" align="left"
							style="font-family: Helvetica, Arial, sans-serif; font-size: 12px; line-height: 16px; color: #aaaaaa; padding-left: 24px; padding-right: 24px">
							<g:render template="/notification/email_signature" />
						</td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
