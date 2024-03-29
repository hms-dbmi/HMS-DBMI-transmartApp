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

<%@ page language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<title>Sequence Variant Explorer</title>
	
	<!-- Include Ext and app-specific scripts: -->
	<script type="text/javascript" src="${resource(dir:'js/sarissa', file:'sarissa.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js/sarissa', file: 'sarissa_ieemu_xpath.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js/javeline', file: 'javeline_xpath.js')}"></script>
	<g:javascript library="prototype" />
	<script type="text/javascript" src="${resource(dir:'js', file:'ext/adapter/ext/ext-base.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'browserDetect.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'ext/ext-all-debug.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'searchcombobox.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'picklist.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'sampleExplorerMenu.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'solrQuery.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js/jQuery', file:'jquery-1.7.1.min.js')}"></script>
	<script>jQuery.noConflict();</script>
	<script type="text/javascript" src="${resource(dir:'js', file:'myJobs.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'advancedWorkflowFunctions.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js/datasetExplorer', file:'i2b2common.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'sampleExplorer/sampleExplorer.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'sampleExplorer/sampleExplorer.utilities.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js/ext-ux', file:'miframe.js')}"></script>
	<link rel="stylesheet" type="text/css" href="${resource(dir:'js/ext/resources/css', file:'ext-all.css')}">
	<link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'sampleExplorer.css')}">	
	<link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'chartservlet.css')}">	
	<link rel="stylesheet" type="text/css" href="${resource(dir:'js/ext/resources/css', file:'xtheme-gray.css')}">
	<script type="text/javascript" src="${resource(dir:'js', file:'utilitiesMenu.js')}"></script>
	<link rel="stylesheet" href="${resource(dir:'css', file:'main.css')}"></link>
</head>		
	
	<body>
		
<script type="text/javascript">
	Ext.BLANK_IMAGE_URL = "${resource(dir:'js', file:'ext/resources/images/default/s.gif')}";

	//set ajax to 600*1000 milliseconds
	Ext.Ajax.timeout = 1800000;

	// this overrides the above
	Ext.Updater.defaults.timeout = 1800000;

	var pageInfo = {
		basePath :"${request.getContextPath()}"
	}

	var sampleRequestType = "${sampleRequestType}"
	var currentResultInstanceId = "${result_instance_id}"
		
	/******************************************************************************/
	//Global Variables
	GLOBAL = {
	  Version : '1.0',
	  SearchJSON: {},
	  resultDataSet: {},
	  resultGridPanel: '',
	  columnMap: ${columnData},
	  CurrentTimepoints: new Array(),
	  CurrentSamples: new Array(),
	  CurrentPlatforms: new Array(),
	  CurrentGpls: new Array(),
	  CurrentTissues: new Array(),
	  CurrentRbmpanels: new Array(),
	  Explorer: "SAMPLE",
	  resulttype: 'applet',
	  subsetTabs: 1,
      HelpURL: '${grailsApplication.config.com.recomdata.searchtool.adminHelpURL}',
      ContactUs: '${grailsApplication.config.com.recomdata.searchtool.contactUs}',
      basePath: pageInfo.basePath,
      AppTitle: '${grailsApplication.config.com.recomdata.searchtool.appTitle}',
      resultsGridHeight : ${grailsApplication.config.sampleExplorer.resultsGridHeight},
      resultsGridWidth : ${grailsApplication.config.sampleExplorer.resultsGridWidth},
      BuildVersion: 'Build Version: <g:meta name="environment.BUILD_NUMBER"/> - <g:meta name="environment.BUILD_ID"/>',
      explorerType: 'sequenceVariantExplorer',
      selectorDivIdCounter : 0
	};
	// initialize browser version variables; see http://www.quirksmode.org/js/detect.html
	BrowserDetect.init();
	if (BrowserDetect.browser == "Explorer"){

	    if(BrowserDetect.version < 7) {
			GLOBAL.resulttype = 'image';
		}
	}

	
	
</script>	

<div id="header-div">
	<g:if test="${explorerType == 'sequenceVariantExplorer'}">
		<g:render template="/layouts/commonheader" model="['app':'sequencevariantexplorer']" />
	</g:if>
	<g:else>
		<g:render template="/layouts/commonheader" model="['app':'sequencevariantexplorer_ALLY']" />
	</g:else>
</div>

<div id="main"></div>
<g:form name="exportdsform" controller="export" action="exportDataset"/>
<g:form name="exportgridform" controller="chart" action="exportGrid" />

	<span id="visualizerSpan0"></span> <!-- place applet tag here -->
	<span id="visualizerSpan1"></span> <!-- place applet tag here -->

</body>
</html>