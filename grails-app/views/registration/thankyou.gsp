<html>
<head>
<meta name="layout" content="bootstrap" />
<title>Thank You For Registering</title>
</head>
<body>
	<g:render template="/layouts/navbar_nomenu" />
	<div class="top-content">
		<div class="inner-bg">
			<div class="container">
				<div class="row well" style="opacity: .85; color: black;">
					<div class="col-sm-12">
						<div class="description">
						  <h1>Thank you!</h1>
							<p>
								The administrator has been notified, and you will receive an electronic confirmation once your access
								request has been approved.<br />
								<br />
							</p>
						</div>
					</div>
					<div class="col-sm-12">
                        <g:render template="/notification/email_signature" />
                    </div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
