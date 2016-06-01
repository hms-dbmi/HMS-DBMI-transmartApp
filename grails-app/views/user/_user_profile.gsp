<h3 class="page-header">
	${user.username}
</h3>
<div class="row">
	<g:form class="form-horizontal" role="form" controller="user" action="" method="post">
		<div class="col-sm-5 personal-info">
			<div class="form-group">
				<label class="col-lg-3 control-label">First name:</label>
				<div class="col-lg-8">
					<input class="form-control" name="firstname" value="${user.firstname}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Last name:</label>
				<div class="col-lg-8">
					<input class="form-control" name="lastname" value="${user.lastname}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Phone:</label>
				<div class="col-lg-8">
					<input class="form-control" name="phone" value="${user.phone}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">User Type:</label>

				<div class="col-lg-8">
					<g:render template="/layouts/frmcomponent_select_usertype" />
				</div>
			</div>


			<div class="form-group">
				<label class="col-lg-3 control-label" for="fldSurvey">Survey:</label>
				<div class="col-lg-8">
					<div class="checkbox">
						<label><input type="checkbox" name="is_funded_grant" ${((user.is_funded_grant!=null && user.is_funded_grant=='on')?'checked':'')}>
							Is this request related to a funded grant? </label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" name="is_grant_proposal" ${((user.is_grant_proposal!=null && user.is_grant_proposal=='on')?'checked':'')}>
							Will the data be used to support a grant proposal?
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" name="is_personal_use" ${((user.is_personal_use!=null && user.is_personal_use=='on')?'checked':'')}>
							Will the data be used for teaching a class, or for personal use?
						</label>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-7 personal-info">
			<div class="form-group">
				<label class="col-lg-3 control-label">Institution:</label>
				<div class="col-lg-8">
					<input class="form-control" name="institution" value="${user.institution}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Professional Title:</label>
				<div class="col-lg-8">
					<input class="form-control" name="title" value="${user.title}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Degree:</label>
				<div class="col-lg-8">
					<input class="form-control" name="degree" value="${user.degree}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Disease:</label>
				<div class="col-lg-8">
					<input class="form-control" name="disease" value="${user.disease}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label" for="about">Research Goal:</label>
				<div class="col-lg-8">
					<textarea name="about" id="about" rows="10" class="form-control" maxlength="600">${user.about}</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label"></label>
				<div class="col-md-8">
					<input class="btn btn-primary" value="Save Changes" type="submit" /> <span></span>
				</div>
			</div>
		</div>
	</g:form>
</div>
