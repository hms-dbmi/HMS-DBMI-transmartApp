<!DOCTYPE html>
<html lang="en">
<head>
<meta name="layout" content="bootstrap" />
<title>User Profile</title>

<link rel='stylesheet prefetch' href='https://rawgit.com/guillaumepotier/Parsley.js/2.3.11/src/parsley.css'>

</head>
<body>
	<sec:ifAnyGranted roles="ROLE_ADMIN">
		<g:render template="/layouts/navbar_admin" />
	</sec:ifAnyGranted>
	<sec:ifNotGranted roles="ROLE_ADMIN">
		<g:render template="/layouts/navbar_user" />
	</sec:ifNotGranted>

	<div class="container well" style="opacity: .9; color: black">

		<g:if test="${flash.message!=null}">
			<div class="alert alert-info alert-dismissable">
				<a class="panel-close close" data-dismiss="alert">×</a> <i class="fa fa-coffee"></i>
				${flash.message?:'No Message'}
			</div>
		</g:if>

		<ul class="nav nav-tabs" role="tablist">

			<li role="presentation" class="active"><a href="#personal" aria-controls="personal" role="tab" data-toggle="tab">Personal</a></li>
			<li role="presentation"><a href="#professional" aria-controls="professional" role="tab" data-toggle="tab">Professional</a></li><%--

			<li role="presentation"><a href="#accesslevel" aria-controls="accesslevel" role="tab" data-toggle="tab">AccessLevel</a></li>--%>
			<!-- Only show API tab if the user has Level2 access> -->
			<sec:ifAnyGranted roles="ROLE_DATASET_EXPLORER_ADMIN">
				<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">API</a></li>
			</sec:ifAnyGranted>
		</ul>

		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="personal">
				<g:form>
					<br />
					<g:render template="personal" />
					<div class="col-xs-12">
						<br /> <br />
						<g:actionSubmit action="save" class="btn btn-info pull-right" value="Save" />
					</div>
				</g:form>
			</div>


			<div role="tabpanel" class="tab-pane" id="professional">

				<g:form>
					<br />
					<g:render template="professional" />
					<div class="col-xs-12">
						<br /> <br />
						<g:actionSubmit action="save" class="btn btn-info pull-right" value="Save" />
					</div>
				</g:form>
			</div><%--

			<div role="tabpanel" class="tab-pane" id="accesslevel">
				<g:render template="access_level" />
			</div>
--%>
			<sec:ifAnyGranted roles="ROLE_DATASET_EXPLORER_ADMIN">
				<div role="tabpanel" class="tab-pane" id="messages">
					<g:render template="api_token" />
				</div>
			</sec:ifAnyGranted>

		</div>

	</div>

	<content tag="javascript"> <script src="http://parsleyjs.org/dist/parsley.min.js" type="text/javascript"></script> <script>
		/*
		Simple script to limit the size of the available characters
		for the textbox area (the About field). We do not want to allow
		unlimited text entry, because the other data needs space in the 
		database as well.
		 */
		function maxLength(el) {
			var max = el.attributes.maxLength.value;
			el.onkeypress = function(e) {
				if (e.keyCode == 8) {
					return true;
				}
				if (this.value.length >= max - 1) {
					alert("The maximum limit of "
							+ max
							+ " characters have been reached. Please reduce the content in this field.")
					return false;
				}
			};
		}

		maxLength(document.getElementById("about"));
	</script></content>
</body>
</html>
