<h3 class="page-header">Access Level ${user.level}</h3>
<div class="row">
	<div class="col-lg-12">
	
	<g:if test="${user.level==1}">
        <p class="lead">
            Your current access level is <b>Level 1</b>.
        </p>
        <p class="lead">
            This means that you are able to run <i>summary statistics</i> and <i>advanced statistics</i> queries, but you cannot see or download <b><i>patient-level data</i></b>.
        </p>
        <p class="lead">
            The process to request <b>Level 2</b> access is as follows:
        <ul class="lead">
            <li>Ensure that your <b>Profile</b> information is up-to-date.
            </li>
            <li>Fill out the <b>Access Request Form</b>.</li>
        </ul>
        </p>

        <p class="lead">After completing the above steps, a confirmation e-mail will be sent to you and the administrator will be notified of your request.  The administrator will review the form and will coordinate its submission to the GRDR Data Access Committee.</p>
        <br />        

        <a href="${createLink(controller: 'user', action: 'access')}" class="btn btn-info">Level 2 Access Request Form</a>
    </div>
    </g:if>
    <g:if test="${user.level==2}">
        <p class="lead">
            Your current access level is <b>Level2</b>.
        </p>
        <p class="lead">
            This means that you are able to run <i>summary statistics</i> and <i>advanced statistics</i> queries, you can also see <i>patient level data</i>
            and <i>download or print</i> the information presented on the resulting pages.
        </p>
    </div>
    </g:if>
	
</div>