<div class="form-section">
	<div class="col-xs-12">
		<label for="usertype">How would you best define yourself?<sup>*</sup></label> <%--<select name="usertype" class="form-control" required="">
			<option value="" selected="${user.usertype!=null || user.usertype.isEmpty()?'selected':''}">Please select one of the options ...</option>
			<option value="academia" selected="${user.usertype!=null && user.usertype=='academia'?'selected':''}" >Academia</option>
			<option value="government" selected="${user.usertype!=null && user.usertype=='government'?'selected':''}">Government</option>
			<option value="provider" selected="${user.usertype!=null && user.usertype=='provider'?'selected':''}">HealthCare Provider</option>
			<option value="industry" selected="${user.usertype!=null && user.usertype=='industry'?'selected':''}">Industry (Biotech, Pharma)</option>
			<option value="nonprofit" selected="${user.usertype!=null && user.usertype=='nonprofile'?'selected':''}">Non-Profit Research Organization</option>
			<option value="patient" >Patient</option>
			<option value="patient_parent" >Parent of Patient</option>
			<option value="patient_advocate" >Patient Advocate</option>
			<option value="patient_advocacy_org" >Patient Advocacy Organization</option>
			<option value="vendor" >Vendor (Software Developer)</option>
			<option value="other" >Other</option>
		</select>
		
		--%><g:select name="usertype" class="form-control" from="${['Academia','Government','HealthCare Provider','Industry (Biotech, Pharma)','Non-Profit Research Organization','Patient','Parent of Patient','Patient Advocate','Patient Advocacy Organization','Vendor (Software Developer)','Other']}"/>
	</div>
	<div class="col-xs-12">
		<br />
	</div>

	<div class="col-xs-6">
		<label for="degree">Degree<sup>*</sup></label> <input class="form-control" name="degree" value="${user.degree}" required="" type="text">
	</div>
	<div class="col-xs-6">
		<label for="title">Academic Position (or Title)<sup>*</sup></label> <input class="form-control" name="title" value="${user.title}" required="" type="text">
	</div>
	<div class="col-xs-12">
		<br />
	</div>

	<div class="col-xs-6">
		<label for="institution">Institution<sup>*</sup></label> <input class="form-control" name="institution" value="${user.institution}" required="" type="text">
	</div>
	<div class="col-xs-6">
		<label for="disease">Disease Interest<sup>*</sup></label> <input class="form-control" name="disease" value="${user.disease}" required="" type="text">
	</div>
	<div class="col-xs-12">
		<br />
	</div>

	<div class="col-xs-12">
		<label for="about">About your research<sup>*</sup></label>
		<textarea id="about" name="about" required placeholder="Research goal ..." class="form-control" rows="5" maxLength="600">${user.about}</textarea>
		<small>Please briefly describe why you are requesting access to <b> ${grailsApplication.config.edu.harvard.transmart.instance.name?:'i2b2/tranSMART'}
		</b></small>

	</div>
	<div class="col-xs-12">
		<br />
	</div>
</div>