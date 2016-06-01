<!--
  tranSMART - translational medicine data mart
  
  Copyright 2008-2012 Janssen Research & Development, LLC.
  
  This product includes software developed at Janssen Research & Development, LLC.
  
  This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
  as published by the Free Software  * Foundation, either version 3 of the License, or (at your option) any later version, along with the following terms:
  1.	You may convey a work based on this program in accordance with section 5, provided that you retain the above notices.
  2.	You may convey verbatim copies of this program code as you receive it, in any medium, provided that you retain the above notices.
  
  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS    * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
  
 
-->

<div id="idletimeout">
	You will be logged off in <span><!-- countdown place holder --></span>&nbsp;seconds due to inactivity. 
	<a id="idletimeout-resume" href="#">Click here to continue using this web page</a>.
</div>

<style type="text/css">
#idletimeout { background:#CC5100; border:3px solid #FF6500; color:#fff; font-family:arial, sans-serif; text-align:center; font-size:12px; padding:10px; position:relative; top:0px; left:0; right:0; z-index:100000; display:none; }
#idletimeout a { color:#fff; font-weight:bold }
#idletimeout span { font-weight:bold }
</style>

<script type="text/javascript" src="${resource(dir:'js/jQuery', file:'jquery.idletimeout.js')}"></script>
<script type="text/javascript" src="${resource(dir:'js/jQuery', file:'jquery.idletimer.js')}"></script>

<script type="text/javascript">

jQuery.noConflict();

jQuery(document).ready(function() {
	
	var logoutURL = "${createLink([controller:'login', action: 'forceAuth'])}";
	var heartbeatURL = "${createLink([controller:'userLanding', action:'checkHeartBeat'])}";

	jQuery.idleTimeout('#idletimeout', '#idletimeout a', {
		idleAfter: 300,
		pollingInterval: 120,
		warningLength: 60,
		keepAliveURL: heartbeatURL,
		serverResponseEquals: 'OK',
		onTimeout: function(){
			jQuery(this).slideUp();
			window.location = logoutURL;
		},
		onIdle: function(){
			jQuery(this).slideDown(); // show the warning bar
		},
		onCountdown: function( counter ){
			jQuery(this).find("span").html( counter ); // update the counter
		},
		onResume: function(){
			jQuery(this).slideUp(); // hide the warning bar
		}
	});
	
	});


</script>

<table id="commonHeaderMenu" class="menuDetail" width="100%" style="border-bottom: 2px solid #ddd;">
	<tr>
		<th style="text-align: left;">
			<!-- menu links -->
			<table class="menuDetail" style="width: auto;">
		    	<tr>
		    		<g:if test="${grailsApplication.config.com.recomdata.hideSearch!='true'}">
	   					<g:if test="${'search'==app}"><th class="menuVisited">Search</th></g:if>
		   				<g:else><th class="menuLink"><g:link controller="search">Search</g:link></th></g:else>
		   			</g:if>

			       	<g:if test="${'datasetExplorer'==app}"><th class="menuVisited">Dataset Explorer</th></g:if>
	       			<g:else><th class="menuLink"><g:link controller="secure">Dataset Explorer</g:link></th></g:else>
	       			
	       			<g:if test="${grailsApplication.config.com.recomdata.hideSampleExplorer!='true'}">
		   				<g:if test="${'sampleexplorer'==app}"><th class="menuVisited">Sample Explorer</th></g:if>
			   			<g:else><th class="menuLink"><g:link controller="sampleExplorer">Sample Explorer</g:link></th></g:else>	   
		   			</g:if>   	
		   			
	       			<g:if test="${grailsApplication.config.com.recomdata.hideClinicalNoteExplorer!='true'}">
		   				<g:if test="${'clinicalnoteexplorer'==app}"><th class="menuVisited">Clinical Note Explorer</th></g:if>
			   			<g:else><th class="menuLink"><g:link controller="clinicalNoteExplorer">Clinical Note Explorer</g:link></th></g:else>	   
		   			</g:if>   			   			

	       			<g:if test="${grailsApplication.config.com.recomdata.hideSequenceVariantExplorer!='true'}">
		   				<g:if test="${'sequencevariantexplorer'==app}"><th class="menuVisited">Sequence Variant Explorer (Joe/Tim)</th></g:if>
			   			<g:else><th class="menuLink"><g:link controller="sequenceVariantExplorer" params="[explorerType: 'sequenceVariantExplorer']">Sequence Variant Explorer (Joe/Tim)</g:link></th></g:else>	   
		   			</g:if> 

	       			<g:if test="${grailsApplication.config.com.recomdata.hideSequenceVariantExplorer!='true'}">
		   				<g:if test="${'sequencevariantexplorer_ALLY'==app}"><th class="menuVisited">Sequence Variant Explorer (Ally)</th></g:if>
			   			<g:else><th class="menuLink"><g:link controller="sequenceVariantExplorer" params="[explorerType: 'sequenceVariantExplorer_ALLY']">Sequence Variant Explorer (Ally)</g:link></th></g:else>	   
		   			</g:if> 
		   			
		   			<g:if test="${grailsApplication.config.com.recomdata.hideGeneSignature!='true'}">		
	   					<g:if test="${'genesignature'==app}"><th class="menuVisited">Gene Signature/Lists</th></g:if>
		   				<g:else><th class="menuLink"><g:link controller="geneSignature">Gene Signature/Lists</g:link></th></g:else>
		   			</g:if>
		   			
		      		<sec:ifAnyGranted roles="ROLE_ADMIN">
	   					<g:if test="${'accesslog'==app}"><th class="menuVisited">Admin</th></g:if>
		   				<g:else><th class="menuLink"><g:link controller="accessLog">Admin</g:link></th></g:else>
		       		</sec:ifAnyGranted>
		       	<tr>
		 	</table>
		</th> 		 
	</tr>
</table>