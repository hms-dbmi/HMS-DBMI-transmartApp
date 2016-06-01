<!DOCTYPE html>
<html lang="en">
<head>
<meta name="layout" content="bootstrap" />
<title>User List</title>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/dataTables.bootstrap.min.css" />
</head>

<body>
	<g:render template="/layouts/navbar_admin" />

	<div class="container well" style="opacity: .85, color: black">
		<g:if test="${flash.message!=null}">
			<div class="alert alert-info alert-dismissable">
				<a class="panel-close close" data-dismiss="alert">×</a> <i class="fa fa-coffee"></i>
				${flash.message?:'No Message'}
			</div>
		</g:if>
		<h2>User List</h2>
		<div class="row">
			<div class="col-lg-12">
				<g:if test="alerts">
					<g:each in="${alerts}" var="alert">
						<div class="alert alert-danger alert-dismissable">
							<a class="panel-close close" data-dismiss="alert">×</a> <i class="glyphicon glyphicon-link"></i>
							${alert}
						</div>
					</g:each>
				</g:if>

				<table id="tblUserList" class="table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Affiliation</th>
							<th>Connection</th>
							<th>Last Updated</th>
							<th>Access Level</th>
							<th>More</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${users}" var="usr">
							<tr>
								<td><b> ${grails.converters.JSON.parse(usr.description?:'{"firstname":"", "lastname":""}')?.firstname?:"UNKNOWN"} ${grails.converters.JSON.parse(usr.description?:'{"firstname":"", "lastname":""}')?.lastname?:"UNKNOWN"}</b><br />
									<small> ${usr.email?:'unknown'}</small></td>
								<td>
									${grails.converters.JSON.parse(usr.description?:'{"firstname":"", "lastname":""}').institution?:"UNKNOWN"}
								</td>
								<td>
									${grails.converters.JSON.parse(usr.description).connection?:"no connection data"}
								</td>
								<td>
									${usr.lastUpdated}
								</td>
								<td>
									<div class="btn-group">
										<g:if test="${usr.getLevel() == 0}">
											<a class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Level0 <span class="caret"></span>
											</a>
										</g:if>
										<g:if test="${usr.getLevel() == 1}">
											<a class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Level1 <span class="caret"></span>
											</a>
										</g:if>
										<g:if test="${usr.getLevel() == 2}">
											<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Level2 <span
												class="caret"></span>
											</a>
										</g:if>
										<g:if test="${usr.getLevel() == 99}">
											<a class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Admin <span class="caret"></span>
											</a>
										</g:if>

										<g:if test="${usr.getLevel() < 99 && usr.getLevel() > 2}">
											<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Level${usr.getLevel()} <span
												class="caret"></span>
											</a>
										</g:if>

										<ul class="dropdown-menu">
											<li><a href="?state=level0&id=${usr.id}">Level0</a></li>
											<li><a href="?state=level1&id=${usr.id}">Level1</a></li>
											<li><a href="?state=level2&id=${usr.id}">Level2</a></li>
											<li role="separator" class="divider"></li>
											<li><a href="?state=admin&id=${usr.id}">Admin</a></li>
										</ul>
									</div>

								</td>
								<td><a class="btn" data-toggle="modal" href="${resource()}/admin/profileview?id=${usr.id}" data-target="#userprofile${usr.id}"> <span
										class="glyphicon glyphicon-option-horizontal" aria-hidden="true"></span>
								</a></td>
							</tr>

						</g:each>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- /container -->

	<g:each in="${users}" var="usr">
		<div class="modal fade" id="userprofile${usr.id}" tabindex="-1" role="dialog" aria-labelledby="userprofile${usr.id}">
			<div class="modal-dialog" role="document">
				<div class="modal-content"></div>
			</div>
		</div>
	</g:each>

	<content tag="javascript"> <script>
		$(document).ready(function() {
			$('#tblUserList').DataTable({
				"bLengthChange" : false,
				"info" : false
			});
		});
	</script> </content>
</body>
</html>
