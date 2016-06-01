<html>
<head>
<meta name='layout' content='none' />
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title" id="myModalLabel2">
			<strong> ${usr.username}
			</strong> User Profile
		</h4>
	</div>
	<div class="modal-body">
		<div class="row">


			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#record" aria-controls="record" role="tab" data-toggle="tab">Record</a></li>
				<li role="presentation"><a href="#details" aria-controls="details" role="tab" data-toggle="tab">Details</a></li>
				<li role="presentation"><a href="#roles" aria-controls="roles" role="tab" data-toggle="tab">Roles</a></li>
				<li role="presentation"><a href="#history" aria-controls="history" role="tab" data-toggle="tab">History</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="record">
					<table class="table">
						<g:each in="${usr.getProperties().sort()}">
							<g:if test="${it.key!='description'}">
								<tr>
									<td>
										${it.key}
									</td>
									<td>
										${it.value}
									</td>
								</tr>
							</g:if>
						</g:each>
					</table>
				</div>
				<div role="tabpanel" class="tab-pane" id="details">
					<table class="table">
						<g:each in="${grails.converters.JSON.parse(usr.description).sort()}">
							<tr>
								<td>
									${it.key}
								</td>
								<td>
									${it.value}
								</td>
							</tr>
						</g:each>
					</table>
				</div>
				<div role="tabpanel" class="tab-pane" id="roles">
					<table class="table">
						<g:each in="${edu.hms.transmart.security.Role.list()}">
							<tr>
								<td></td>
								<td><input type="checkbox" ${usr.authorities.contains(it)?'checked':''} disabled /> ${it.authority}</td>
							</tr>
						</g:each>
					</table>
				</div>
				<div role="tabpanel" class="tab-pane" id="history">
					<br />Not yet implemented!
				</div>
			</div>

		</div>
	</div>

	<div class="modal-footer">
		<button type="button" onClick="window.location='${resource()}/admin/deleteuser?id=${usr.id}';" class="btn btn-danger">Delete</button>
	</div>
</body>
</html>