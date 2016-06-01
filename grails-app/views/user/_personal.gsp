<div class="form-section">

	<g:if test="${user.email.indexOf('@')<0 || params.controller=='user'}">
		<!-- E-mail is not really an e-mail, so we need to ask for it. -->
		<div class="col-xs-12">
			<label for="email">E-mail<sup>*</sup></label> <input class="form-control" name="email" required="" type="email" value="${user.email.indexOf('@')>=0 && user.email?user.email:''}" >
		</div>
	</g:if>
	<g:else>
		<!-- E-mail seems to be valid, so we just display it -->
		<div class="col-xs-12">
			<label for="email">E-mail</label>
			<input name="email" type="hidden" value="${user.email}" >
			<p class="form-control-static">
				${user.email}
			</p>
		</div>
	</g:else>
	<div class="col-xs-12">
		<br />
	</div>

	<div class="col-xs-6">
		<label for="firstname">First Name<sup>*</sup></label> <input class="form-control" name="firstname" value="${user.firstname}" required="" type="text">
	</div>

	<div class="col-xs-6">
		<label for="lastname">Last Name<sup>*</sup></label> <input class="form-control" name="lastname" value="${user.lastname}" required="" type="text"><br />
	</div>

	<div class="col-xs-12">
		<label for="street">Street<sup>*</sup></label> <input class="form-control" name="street" value="${user.street}" required="" type="phone"><br />
	</div>

	<div class="col-xs-6">
		<label for="city">City<sup>*</sup></label> <input class="form-control" name="city" value="${user.city}" required="" type="phone">
	</div>

	<div class="col-xs-6">
		<label for="state">State/Province<sup>*</sup></label> <input class="form-control" name="state" value="${user.state}" required="" type="phone">
	</div>

	<div class="col-xs-12">
		<br />
	</div>

	<div class="col-xs-6">
		<label for="zip">ZIP/Postal Code<sup>*</sup></label> <input class="form-control" name="zip" value="${user.zip}" required="" type="text" />
	</div>

	<div class="col-xs-6">
		<label for="country">Country<sup>*</sup></label> <input class="form-control" name="country" value="${user.country}" required="" type="phone">
	</div>

	<div class="col-xs-12">
		<br />
	</div>

	<div class="col-xs-12">
		<label for="phone">Phone<sup>*</sup></label> <input class="form-control" name="phone" value="${user.phone}" required="" type="text">
	</div>

	<div class="col-xs-12">
		<br />
	</div>

	<div class="col-xs-12">
		<label for="fax">Fax</label> <input class="form-control" name="fax" value="${user.fax}" type="text">
	</div>
</div>