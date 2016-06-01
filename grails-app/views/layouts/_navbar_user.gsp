<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<g:render template="/layouts/navbar_logo" />
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Home <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${resource(dir: '/datasetExplorer', file: 'index')}">Dataset Explorer</a></li>
						<!-- 
						<li><a href="${resource(dir: '/sampleExplorer', file: 'list')}">Sample</a></li>
						<li><a href="${resource(dir: '/clinicalNoteExplorer', file: 'list')}">Clinical Note</a></li>
						<li><a href="${resource(dir: '/sequenceVariantExplorer', file: 'list')}">Sequence Variant</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="${resource(dir: '/geneSignature', file: 'list')}">Gene Signature List</a></li>
						 -->
					</ul></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">${user.firstname} ${user.lastname} <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${resource(dir: '/user')}">Profile</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="${grailsApplication.config.edu.harvard.transmart.instance.userguideurl?:'https://s3.amazonaws.com/hms-dbmi-docs/GRDR_User_Guide.pdf'}" target="_helpWindow">User's Guide</a></li>
						<li role="separator" class="divider"></li>
                        <li><a href="${resource(dir: 'logout', file: 'index')}">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<!--/.nav-collapse -->
	</div>
</nav>