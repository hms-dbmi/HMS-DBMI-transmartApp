<select name="usertype" class="form-control" required id="fldUserType">
    <option value="" ${user.usertype.is(null) || user.usertype.isEmpty()?'selected':''}>Please select one of the options ...</option>
    <option value="academia" ${user.usertype=='academia'?'selected':''}>Academia</option>
    <option value="government" ${user.usertype=='government'?'selected':''}>Government</option>
    <option value="provider" ${user.usertype=='provider'?'selected':''}>HealthCare Provider</option>
    <option value="industry" ${user.usertype=='industry'?'selected':''}>Industry (Biotech, Pharma)</option>
	<option value="nonprofit" ${user.usertype=='nonprofit'?'selected':''}>Non-Profit Research Organization</option>
    <option value="patient" ${user.usertype=='patient'?'selected':''}>Patient</option>
    <option value="patient_parent" ${user.usertype=='patient_parent'?'selected':''}>Parent of Patient</option>
    <option value="patient_advocate" ${user.usertype=='patient_advocate'?'selected':''}>Patient Advocate</option>
    <option value="patient_advocacy_org" ${user.usertype=='patient_advocacy_org'?'selected':''}>Patient Advocacy Organization</option>
    <option value="vendor" ${user.usertype=='vendor'?'selected':''}>Vendor (Software Developer)</option>
    <option value="other" ${user.usertype=='other'?'selected':''}>Other</option>
</select>
