<html>
<head>
<meta name="layout" content="bootstrap" />
<title>User Registration</title>

<link rel='stylesheet prefetch' href='https://rawgit.com/guillaumepotier/Parsley.js/2.3.11/src/parsley.css'>
<style>
.form-section {
	padding-left: 15px;
	border-left: 2px solid #FF851B;
	display: none;
}

.form-section.current {
	display: inherit;
}

.btn-info, .btn-default {
	margin-top: 10px;
}

html.codepen body {
	margin: 1em;
}
</style>
</head>
<body>
	<g:render template="/layouts/navbar_nomenu" />

	<div class="container">
		<div class="row well" style="opacity: .99; color: black;">

			<h1>
				<strong> ${grailsApplication.config.edu.harvard.transmart.instance.name?:'i2b2/tranSMART'}
				</strong> Request for Open Access
			</h1>
			<div class="description">
				<p>
					Although open data access does not require prior permission, authorization or GRDR® review, you are required to <br /> register and provide the
					minimal information as requested in the form below.
				</p>
			</div>
			<hr />

			<div class="bs-callout bs-callout-warning hidden">
				<h4>Oh snap!</h4>
				<p>This form seems to be invalid :(</p>
			</div>

			<div class="bs-callout bs-callout-info hidden">
				<h4>Yay!</h4>
				<p>Everything seems to be ok :)</p>
			</div>

			<form class="demo-form" method="POST" action="${createLink(controller:'registration', action: 'confirm')}">
				<input type="hidden" name="username" value="${user.username}" />
				<!-- Personal Information -->
				<g:render template="/user/personal" />

				<!-- Professional Information -->
                <g:render template="/user/professional" />
                
				<div class="form-section">

					<div class="col-xs-12">
						<div class="checkbox">
							<label> <input type="checkbox" name="is_funded_grant"> Is this request related to a funded grant?
							</label>
						</div>
						<div class="checkbox">
							<label> <input type="checkbox" name="is_grant_proposal"> Will the data be used to support a grant proposal?
							</label>
						</div>
						<div class="checkbox">
							<label> <input type="checkbox" name="is_personal_use"> Will the data be used for teaching a class, or for personal use?
							</label>
						</div>
					</div>
					<div class="col-xs-12">
						<br />
					</div>

					<div class="col-xs-6">
						<label>Verification</label>
						<div class="g-recaptcha" data-sitekey="${grailsApplication.config.edu.harvard.transmart.captcha.sitekey?:'MissingCaptchaSiteKey'}"></div>
					</div>
					<div class="col-xs-12">
						<br />
					</div>

				</div>

				<div class="form-navigation">
					<button type="button" class="previous btn btn-info pull-left">&lt; Previous</button>
					<button type="button" class="next btn btn-info pull-right">Next &gt;</button>
					<button class="btn btn-default pull-right" type="submit">
						<span class="clearfix"></span>Register
					</button>
				</div>
			</form>
		</div>
	</div>

	<content tag="javascript"> <script src='https://www.google.com/recaptcha/api.js'></script> <script
		src="https://rawgit.com/guillaumepotier/Parsley.js/2.3.11/dist/parsley.js" type="text/javascript"></script> <script type="text/javascript">
			$(function() {
				var $sections = $('.form-section');

				function navigateTo(index) {
					// Mark the current section with the class 'current'
					$sections.removeClass('current').eq(index).addClass(
							'current');
					// Show only the navigation buttons that make sense for the current section:
					$('.form-navigation .previous').toggle(index > 0);
					var atTheEnd = index >= $sections.length - 1;
					$('.form-navigation .next').toggle(!atTheEnd);
					$('.form-navigation [type=submit]').toggle(atTheEnd);
				}

				function curIndex() {
					// Return the current index by looking at which section has the class 'current'
					return $sections.index($sections.filter('.current'));
				}

				// Previous button is easy, just go back
				$('.form-navigation .previous').click(function() {
					navigateTo(curIndex() - 1);
				});

				// Next button goes forward iff current block validates
				$('.form-navigation .next').click(function() {
					if ($('.demo-form').parsley().validate({
						group : 'block-' + curIndex()
					}))
						navigateTo(curIndex() + 1);
				});

				// Prepare sections by setting the `data-parsley-group` attribute to 'block-0', 'block-1', etc.
				$sections.each(function(index, section) {
					$(section).find(':input').attr('data-parsley-group',
							'block-' + index);
				});
				navigateTo(0); // Start at the beginning
			});
		</script> </content>
</body>
</html>
