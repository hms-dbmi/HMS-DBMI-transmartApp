<!DOCTYPE html>
<html lang="en">
<head>
<meta name="layout" content="bootstrap" />
<title>Error Notification (DEBUG)</title>
<link rel="stylesheet" href="${resource(dir: '/assets/css', file:'cover.css')}" />
</head>
<body>
	<div class="site-wrapper" style="min-height:0; height: 0">
		<div class="site-wrapper-inner">
			<div class="cover-container well" style="background-color: crimson; opacity: .9">
				<div class="masthead clearfix" style="background-color: orange">
					<g:render template="/layouts/navbar_nomenu" />
				</div>

				<div class="inner cover" >
					<h1 class="cover-heading">Something went wrong!</h1>
					<p class="lead">Your latest request could not be completed. We apologize for any inconvenience this might be causing.</p>
					<p class="lead">
						<b>${excptn}</b>
					</p>
					<p>Information about this mistake has been logged and support team has been notified.</p>
					<br />
					<a class="btn btn-info" href="${resource(controller: 'datasetExplorer')}">Return &raquo;</a>
				</div>

				<div class="mastfoot">
					<div class="inner">
						<p>
							A <a href="http://getbootstrap.com">HMS-DBMI</a> site, maintained by <a href="https://twitter.com/mdo">Avillach Lab</a>.
						</p>
					</div>
				</div>

			</div>

		</div>

	</div>
</body>
</html>
