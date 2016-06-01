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
<title>Level 2 Access Request</title>

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

img {
	-ms-interpolation-mode: bicubic;
}

.yshortcuts a {
	border-bottom: none !important;
}

@media screen and (max-width: 799px) {
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
			<td align="center" valign="top" bgcolor="#F0F0F0" style="background-color: #F0F0F0;"><br />
				<table border="0" cellpadding="0" cellspacing="0" class="container" style="width: 800px; max-width: 800px">
					<tr>
						<td class="container-padding content" align="left"
							style="padding-left: 24px; padding-right: 24px; padding-top: 12px; padding-bottom: 12px; background-color: #ffffff"><img
							src="${grailsApplication.config.edu.harvard.transmart.email.logo}" height="50px" /> <br />
							<hr />
							<div class="title" style="font-family: Helvetica, Arial, sans-serif; font-size: 26px; font-weight: 600; color: #374550">Level 2 Access
								Request</div> <br>
							<div class="body-text" style="font-family: Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; text-align: left; color: #333333">
								<p class="lead">
									The user <b> ${user.name}
									</b> is requesting authorization for <b>Level 2</b> access on the <b>${grailsApplication.config.edu.harvard.transmart.instance.name?:'i2b2/tranSMART'}</b> instance.
								</p>
								<p>Please find below the details for the request.</p>
								<br />
								<table width="100%" cellpadding="10px">
									<tbody>
                                        <tr style="background-color: #d0d0d0">
                                            <th colspan="2" align="middle">User Details</th>
                                        </tr>
										<tr style="background-color: #f0f0f0">
											<th width="30%">Name</th>
											<td>
												${user.name}
											</td>
										</tr>
										<tr>
											<th>Email</th>
											<td>
												${user.email}
											</td>
										</tr>
										<tr style="background-color: #f0f0f0">
											<th>Phone</th>
											<td>
												${grails.converters.JSON.parse(user.description).phone?:"UNKNOWN"}
											</td>
										</tr>
										<tr>
											<th>User Type</th>
											<td>
												${grails.converters.JSON.parse(user.description).usertype?:"UNKNOWN"}
											</td>
										</tr>
										<tr style="background-color: #f0f0f0">
											<th>Institution</th>
											<td>
												${grails.converters.JSON.parse(user.description).institution?:"UNKNOWN"}
											</td>
										</tr>
										<tr>
											<th>Title</th>
											<td>
												${grails.converters.JSON.parse(user.description).title?:"UNKNOWN"}
											</td>
										</tr>
										<tr style="background-color: #f0f0f0">
											<th>Degree</th>
											<td>
												${grails.converters.JSON.parse(user.description).degree?:"UNKNOWN"}
											</td>
										</tr>
										<tr>
											<th>Disease Interest</th>
											<td>
												${grails.converters.JSON.parse(user.description).disease?:"UNKNOWN"}
											</td>
										</tr>
										<tr style="background-color: #f0f0f0">
											<th>About</th>
											<td>
												${grails.converters.JSON.parse(user.description).about?:"UNKNOWN"}
											</td>
										</tr>
										<tr>
											<th>Survey</th>
											<td>
												<table width="100%">
													<tr>
														<td width="30%">Is funded grant?</td>
														<td><b>
																${grails.converters.JSON.parse(user.description).is_funded_grant?'yes':'no'}
														</b></td>
													</tr>
													<tr>
														<td>Is grant proposal?</td>
														<td><b>
																${grails.converters.JSON.parse(user.description).is_grant_proposal?'yes':'no'}
														</b></td>
													</tr>
													<tr>
														<td>Is personal use?</td>
														<td><b>
																${grails.converters.JSON.parse(user.description).is_personal_use?'yes':'no'}
														</b></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr style="background-color: #d0d0d0">
											<th colspan="2" align="middle">Research</th>
										</tr>
										<tr>
											<th>Title</th>
											<td>${grails.converters.JSON.parse(user.description).research_title?:'UNKNOWN'}</td>
										</tr>
										<tr style="background-color: #f0f0f0">
											<th>Aims</th>
											<td>${grails.converters.JSON.parse(user.description).research_aims?:'UNKNOWN'}</td>
										</tr>
										<tr>
											<th>Description</th>
											<td>${grails.converters.JSON.parse(user.description).research_summary?:'UNKNOWN'}</td>
										</tr>
										<tr style="background-color: #f0f0f0">
											<th>IRB Approval Documentation (file is attached)</th>
											<td>
												${grails.converters.JSON.parse(user.description).research_irbfile?:'UNKNOWN'}
											</td>
										</tr>
									</tbody>
								</table>
								<br />
                                Please forward this information to the approval body and notify the user if additional information is needed.<br />
                                <br />
							</div></td>
					</tr>
					<tr>
						<td class="container-padding footer-text" align="left"
							style="font-family: Helvetica, Arial, sans-serif; font-size: 12px; line-height: 16px; color: #aaaaaa; padding-left: 24px; padding-right: 24px">
							<g:render template="/notification/email_signature" />
						</td>
					</tr>
				</table> <br /> <br /></td>
		</tr>
	</table>

</body>
</html>
