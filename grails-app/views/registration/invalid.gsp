<html>
<head>
<meta name="layout" content="bootstrap" />
<title>User Registration</title>
</head>
<body>
	<g:render template="/layouts/navbar_nomenu" />

	<div class="top-content">
		<div class="inner-bg">
			<div class="container">
				<div class="row well" style="opacity: .85; color: black;">
					<h1>
						<strong>AVL-DEV</strong> Registration Form
					</h1>
					<div class="description">
						<p>We are unable to process your request. Please try to login again.</p>
					</div>
                    <hr />
                    <div class="error">${msg}</div>
                    <div class="credentials"><code>${cred}</code></div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
