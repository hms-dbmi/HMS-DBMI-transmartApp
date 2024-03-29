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

<html>
<head>
	<title>${grailsApplication.config.com.recomdata.searchtool.appTitle}</title>
		<link rel="stylesheet" href="${resource(dir:'js',file:'ext/resources/css/ext-all.css')}"></link>
		<link rel="stylesheet" href="${resource(dir:'js',file:'ext/resources/css/xtheme-gray.css')}"></link>
	<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
	<g:javascript library="prototype" />
	<script type="text/javascript" src="${resource(dir:'js', file:'toggle.js')}"></script>
	<script type="text/javascript">

	function refreshParent(newurl){
     parent.window.close();
	 if(parent!=null && parent.window.opener!=null && !parent.window.opener.closed){
		parent.window.opener.location =newurl;
		}
	}

	</script>
</head>
<body>
	<g:waitIndicator divId="summary_loading" />
	<div id="summary" style="display: block;">	
		<p class="Title">
			<span class="Title"></span>
		</p>
		<div id="SummaryHeader">
			<span class="SummaryHeader">Available Pathways</span>
		</div>
		<div class="paginateButtons" style="width: 100%;">
			<span style="font-size:12px;color:#000000;">Results for </span>
			<select class="jubselect" name="datasource" id="datasource" style="width:240px;"
					onChange="${remoteFunction(action:'listAllPathways', 
											   before:'toggleVisible(\'summary_loading\'); toggleVisible(\'summary\');', 
									           onComplete:'toggleVisible(\'summary_loading\'); toggleVisible(\'summary\');',
									           update:'summary', params:'\'datasource=\'+this.value')}" >
			<g:each in="${datasources}" var="source" status="i">
				<option value="${source}" ${(selecteddatasource == null && i == 0) || (selecteddatasource == source) ? "selected" : ""}>${source}</option>		
			</g:each>
			</select>
			&nbsp;&nbsp;
			<g:remoteAlphaPaginate update="summary" controller="searchHelp" action="listAllPathways" params="[datasource:selecteddatasource]" />
		</div>
		<table class="trborderbottom" style="width:100%;">
		<g:each in="${pathways}" var="pathway">
			<tr style="border-bottom:1px solid #CCCCCC;">
				<td>${createKeywordSearchLink(popup:true, jsfunction:"refreshParent", keyword:pathway)}</td>
			</tr>
		</g:each>
		</table>
	</div>
</body>
</html>