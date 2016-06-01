<html>
<head>
<title>Application Settings</title>
<meta name='layout' content='bootstrap' />
</head>
<body>
	<g:render template="/layouts/navbar_admin" />

	<div class="container well" style="opacity: .9, color: black">
		<g:if test="${flash.message!=null}">
			<div class="alert alert-info alert-dismissable">
				<a class="panel-close close" data-dismiss="alert">×</a> <i class="fa fa-coffee"></i>
				${flash.message?:'No Message'}
			</div>
		</g:if>

		<h2>Application Settings</h2>

		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<td width="10px"></td>
						<th>Name</th>
						<th>Value</th>
						<th class="pull-right">Action</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${keys}" var="keyname">
						<tr>
							<td width="10px"><input type="checkbox" name="delete_property" value="${keyname}" /></td>
							<th width="20%">
								${keyname}
							</th>
							<td><input type="text" style="padding: 5px; width: 100%" value="${props[keyname]}" id="fld${keyname}" /></td>
							<td><a class="btn btn-success pull-right" id="btn${keyname}" data-loading-text="Saving...">Save</a></td>
						</tr>
					</g:each>
					<tr>
						<td></td>
						<th><input type="text" name="newkey" id="newkey" style="padding: 5px; width: 100%" /></th>
						<td><input style="padding: 5px; width: 100%" type="text" name="newvalue" id="newvalue" /></td>
						<td><a class="btn btn-info pull-right" id="btnAddKVPair">Add</a></td>
					</tr>
					<tr>
						<td><a class="btn btn-danger pull-right">Del</a></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<content tag="javascript"> <script>
		$(document)
				.ready(
						function() {
							$('#tblUserList').DataTable({
								"bLengthChange" : false,
								"info" : false
							});

							$('a.btn-success')
									.on(
											'click',
											function() {
												var $btn = $(this).button(
														'loading');
												console.log('about to save');

												$
														.ajax(
																{
																	url : "properties?method=set&name="
																			+ $(
																					this)
																					.attr(
																							'id')
																					.substring(
																							3)
																			+ "&value="
																			+ $(
																					'#fld'
																							+ $(
																									this)
																									.attr(
																											'id')
																									.substring(
																											3))
																					.val(),
																//beforeSend : function(xhr) {
																//    if (localStorage.getItem('userToken')) {
																//        xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('userToken'));
																//    }
																//}
																})
														.done(function(data) {
															console.log(data);
														});

												$btn.button('reset');
												console.log('changing label');
												$btn.val("Saved.");
											});

							$('a.btn-danger')
									.on(
											'click',
											function(e) {
												var $btn = $(this).button(
														'loading');
												$(
														'input[type=checkbox]:checked')
														.each(
																function(i, e) {

																	$
																			.ajax(
																					{
																						url : "properties?method=del&name="
																								+ $(
																										this)
																										.val(),
																					//beforeSend : function(xhr) {
																					//    if (localStorage.getItem('userToken')) {
																					//        xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('userToken'));
																					//    }
																					//}
																					})
																			.done(
																					function(
																							data) {
																						console
																								.log(data);
																					});

																	console
																			.log($(
																					this)
																					.val());
																	$(this)
																			.parent()
																			.parent()
																			.hide();
																});
												$btn.button('reset');
											});
						});
	</script> </content>

</body>
</html>
