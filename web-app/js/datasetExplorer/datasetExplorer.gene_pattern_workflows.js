/** 
 * Function to run the survival analysis asynchronously
 */
function showSurvivalAnalysis() {	
	if((!isSubsetEmpty(1) && GLOBAL.CurrentSubsetIDs[1] == null) || (!isSubsetEmpty(2) && GLOBAL.CurrentSubsetIDs[2] == null))
	{
		runAllQueries(showSurvivalAnalysis);
		return;
	}
	
	Ext.Ajax.request({						
		url: pageInfo.basePath+"/asyncJob/createnewjob",
		method: 'POST',
		success: function(result, request){
			RunSurvivalAnalysis(result, GLOBAL.CurrentSubsetIDs[1], GLOBAL.CurrentSubsetIDs[2],
					getQuerySummary(1), getQuerySummary(2));
		},
		failure: function(result, request){
			Ext.Msg.alert('Status', 'Unable to create the heatmap job.');
		},
		timeout: '1800000',
		params: {jobType:  "Survival"}
	});
}

function genePatternReplacement() {
	Ext.Msg.alert('Work In Progress', 'Gene Pattern replacement')
}

//Once, we get a job created by GPController, we run the survival analysis
function RunSurvivalAnalysis(result, result_instance_id1, result_instance_id2,
		querySummary1, querySummary2)	{
	var jobNameInfo = Ext.util.JSON.decode(result.responseText);					 
	var jobName = jobNameInfo.jobName;

	genePatternReplacement();
	showJobStatusWindow(result);	
	document.getElementById("gplogin").src = pageInfo.basePath + '/analysis/gplogin';   // log into GenePattern
	Ext.Ajax.request(
		{						
			url: pageInfo.basePath+"/genePattern/runsurvivalanalysis",
			method: 'POST',
			timeout: '1800000',
			params: {result_instance_id1: result_instance_id1,
				result_instance_id2:  result_instance_id2,
				querySummary1: querySummary1,
				querySummary2: querySummary2,
				jobName: jobName
			}
	});
	checkJobStatus(jobName);
}

function showSNPViewerSelection() {
	
	if((!isSubsetEmpty(1) && GLOBAL.CurrentSubsetIDs[1] == null) ||
			   (!isSubsetEmpty(2) && GLOBAL.CurrentSubsetIDs[2] == null))
	{
		runAllQueries(showSNPViewerSelection);
		return;
	}

	//genePatternReplacement();
	var win = new Ext.Window({
		id: 'showSNPViewerSelection',
		title: 'SNPViewer',
		layout:'fit',
		width:600,
		height:400,
		closable: false,
		plain: true,
		modal: true,
		border:false,
		buttons: [
		          {
		        	  id: 'showSNPViewerSelectionOKButton',
		        	  text: 'OK',
		        	  handler: function(){
		        	  if(Ext.get('snpViewChroms')==null)
		        	  {
		        		  win.close();
		        		  return;
		        	  }
		        	  var ob=Ext.get('snpViewChroms').dom;
		        	  var selected = new Array();
		        	  for (var i = 0; i < ob.options.length; i++)
		        		  if (ob.options[i].selected)
		        			  selected.push(ob.options[i].value);
		        	  GLOBAL.CurrentChroms=selected.join(',');
		        	  getSNPViewer();
		        	  win.close();}
		          }
		          ,{
		        	  text: 'Cancel',
		        	  handler: function(){
		        	  win.close();}
		          }],
		resizable: false,
		autoLoad: {
			url: pageInfo.basePath+'/analysis/showSNPViewerSelection',
			scripts: true,
			nocache:true,
			discardUrl:true,
			method:'POST',
			params: {result_instance_id1: GLOBAL.CurrentSubsetIDs[1],
				result_instance_id2: GLOBAL.CurrentSubsetIDs[2]}
		},
		tools:[{
			id:'help',
			qtip:'Click for context sensitive help',
		    handler: function(event, toolEl, panel){
		   	D2H_ShowHelp("1360",helpURL,"wndExternal",CTXT_DISPLAY_FULLHELP );
		    }
		}]
	});
	//  }
	win.show(viewport);
}

function getSNPViewer() {

	// before Ajax call, log into genepattern:
	genePatternLogin();
	var selectedGenesElt = Ext.get("selectedGenesSNPViewer");
	var selectedGenesEltValue = selectedGenesElt.dom.value;
	var selectedGeneStr = "";
	if (selectedGenesEltValue && selectedGenesEltValue.length != 0) {
		selectedGeneStr = selectedGenesEltValue;
	}
	
	var geneAndIdListElt = Ext.get("selectedGenesAndIdSNPViewer");
	var geneAndIdListEltValue = geneAndIdListElt.dom.value;
	var geneAndIdListStr = "";
	if (geneAndIdListElt && geneAndIdListEltValue.length != 0) {
		geneAndIdListStr = geneAndIdListEltValue;
	}
	
	var selectedSNPsElt = Ext.get("selectedSNPs");
	var selectedSNPsEltValue = selectedSNPsElt.dom.value;
	var selectedSNPsStr = "";
	if (selectedSNPsElt && selectedSNPsEltValue.length != 0) {
		selectedSNPsStr = selectedSNPsEltValue;
	}
	//genePatternReplacement();
	Ext.Ajax.request(
	{
		url: pageInfo.basePath+"/analysis/showSNPViewer",
		method: 'POST',
		success: function(result, request){
			//getSNPViewerComplete(result);
		},
		failure: function(result, request){
			//getSNPViewerComplete(result);
		},
		timeout: '1800000',
		params: { result_instance_id1:  GLOBAL.CurrentSubsetIDs[1],
			result_instance_id2:  GLOBAL.CurrentSubsetIDs[2],
			chroms: GLOBAL.CurrentChroms,
			genes: selectedGeneStr,
			geneAndIdList: geneAndIdListStr,
			snps: selectedSNPsStr}
	});
	
	showWorkflowStatusWindow();
}

function showIgvSelection() {
	
	if((!isSubsetEmpty(1) && GLOBAL.CurrentSubsetIDs[1] == null) ||
			   (!isSubsetEmpty(2) && GLOBAL.CurrentSubsetIDs[2] == null))
	{
		runAllQueries(showIgvSelection);
		return;
	}

	//genePatternReplacement();
	var win = new Ext.Window({
	id: 'showIgvSelection',
		title: 'IGV',
		layout:'fit',
		width:600,
		height:400,
		closable: false,
		plain: true,
		modal: true,
		border:false,
		buttons: [
		          {
		        	  id: 'showIgvSelectionOKButton',
		        	  text: 'OK',
		        	  handler: function(){
		        	  if(Ext.get('igvChroms')==null)
		        	  {
		        		  win.close();
		        		  return;
		        	  }
		        	  var ob=Ext.get('igvChroms').dom;
		        	  var selected = new Array();
		        	  for (var i = 0; i < ob.options.length; i++)
		        		  if (ob.options[i].selected)
		        			  selected.push(ob.options[i].value);
		        	  GLOBAL.CurrentChroms=selected.join(',');
		        	  getIgv();
		        	  win.close();}
		          }
		          ,{
		        	  text: 'Cancel',
		        	  handler: function(){
		        	  win.close();}
		          }],
		resizable: false,
		autoLoad: {
			url: pageInfo.basePath+'/analysis/showIgvSelection',
			scripts: true,
			nocache:true,
			discardUrl:true,
			method:'POST',
			params: {result_instance_id1: GLOBAL.CurrentSubsetIDs[1],
				result_instance_id2: GLOBAL.CurrentSubsetIDs[2]}
		},
		tools:[{
			id:'help',
			qtip:'Click for context sensitive help',
		    handler: function(event, toolEl, panel){
		   	D2H_ShowHelp("1427",helpURL,"wndExternal",CTXT_DISPLAY_FULLHELP );
		    }
		}]
	});
	//  }
	win.show(viewport);
}

function getIgv() {

	// before Ajax call, log into genepattern:
	genePatternLogin();
	var selectedGenesElt = Ext.get("selectedGenesIgv");
	var selectedGenesEltValue = selectedGenesElt.dom.value;
	var selectedGeneStr = "";
	if (selectedGenesEltValue && selectedGenesEltValue.length != 0) {
		selectedGeneStr = selectedGenesEltValue;
	}
	
	var geneAndIdListElt = Ext.get("selectedGenesAndIdIgv");
	var geneAndIdListEltValue = geneAndIdListElt.dom.value;
	var geneAndIdListStr = "";
	if (geneAndIdListElt && geneAndIdListEltValue.length != 0) {
		geneAndIdListStr = geneAndIdListEltValue;
	}
	
	var selectedSNPsElt = Ext.get("selectedSNPsIgv");
	var selectedSNPsEltValue = selectedSNPsElt.dom.value;
	var selectedSNPsStr = "";
	if (selectedSNPsElt && selectedSNPsEltValue.length != 0) {
		selectedSNPsStr = selectedSNPsEltValue;
	}
	
	//genePatternReplacement();
	Ext.Ajax.request(
	{
		url: pageInfo.basePath+"/analysis/showIgv",
		method: 'POST',
		success: function(result, request){
			//getSNPViewerComplete(result);
		},
		failure: function(result, request){
			//getSNPViewerComplete(result);
		},
		timeout: '1800000',
		params: { result_instance_id1:  GLOBAL.CurrentSubsetIDs[1],
			result_instance_id2:  GLOBAL.CurrentSubsetIDs[2],
			chroms: GLOBAL.CurrentChroms,
			genes: selectedGeneStr,
			geneAndIdList: geneAndIdListStr,
			snps: selectedSNPsStr}
	});
	
	showWorkflowStatusWindow();
}


function showPlinkSelection() {
	
	if((!isSubsetEmpty(1) && GLOBAL.CurrentSubsetIDs[1] == null) ||
			   (!isSubsetEmpty(2) && GLOBAL.CurrentSubsetIDs[2] == null))
	{
		runAllQueries(showIgvSelection);
		return;
	}

	//genePatternReplacement();
	var win = new Ext.Window({
		id: 'showPlinkSelection',
		title: 'PLINK',
		layout:'fit',
		width:450,
		height:400,
		closable: false,
		plain: true,
		modal: true,
		border:false,
		buttons: [
		          {
		        	  id: 'showPlinkSelectionOKButton',
		        	  text: 'OK',
		        	  handler: function(){
		        	  if(Ext.get('plinkChroms')==null)
		        	  {
		        		  win.close();
		        		  return;
		        	  }
		        	  var ob=Ext.get('plinkChroms').dom;
		        	  var selected = new Array();
		        	  for (var i = 0; i < ob.options.length; i++)
		        		  if (ob.options[i].selected)
		        			  selected.push(ob.options[i].value);
		        	  GLOBAL.CurrentChroms=selected.join(',');
		        	  getPlink();
		        	  win.close();}
		          }
		          ,{
		        	  text: 'Cancel',
		        	  handler: function(){
		        	  win.close();}
		          }],
		resizable: false,
		autoLoad: {
			url: pageInfo.basePath+'/analysis/showPlinkSelection',
			scripts: true,
			nocache:true,
			discardUrl:true,
			method:'POST',
			params: {result_instance_id1: GLOBAL.CurrentSubsetIDs[1],
				result_instance_id2: GLOBAL.CurrentSubsetIDs[2]}
		},
		tools:[{
			id:'help',
			qtip:'Click for context sensitive help',
		    handler: function(event, toolEl, panel){
		    // 1360 needs to be changed for PLINK
		   	D2H_ShowHelp("1360",helpURL,"wndExternal",CTXT_DISPLAY_FULLHELP );
		    }
		}]
	});
	//  }
	win.show(viewport);
}


function getPlink() {

	// before Ajax call, log into genepattern:
	//genePatternLogin();
	
	/*
	var selectedGenesElt = Ext.get("selectedGenesPlink");
	var selectedGenesEltValue = selectedGenesElt.dom.value;
	var selectedGeneStr = "";
	if (selectedGenesEltValue && selectedGenesEltValue.length != 0) {
		selectedGeneStr = selectedGenesEltValue;
	}
	
	var geneAndIdListElt = Ext.get("selectedGenesAndIdPlink");
	var geneAndIdListEltValue = geneAndIdListElt.dom.value;
	var geneAndIdListStr = "";
	if (geneAndIdListElt && geneAndIdListEltValue.length != 0) {
		geneAndIdListStr = geneAndIdListEltValue;
	}
	*/
	
	/*
	var selectedSNPsElt = Ext.get("selectedSNPsPlink");
	var selectedSNPsEltValue = selectedSNPsElt.dom.value;
	var selectedSNPsStr = "";
	if (selectedSNPsElt && selectedSNPsEltValue.length != 0) {
		selectedSNPsStr = selectedSNPsEltValue;
	}
	*/
	
	//genePatternReplacement();
	/*Ext.Ajax.request(
	{
		url: pageInfo.basePath+"/analysis/showPlink",
		method: 'POST',
		success: function(result, request){
			//getSNPViewerComplete(result);
		},
		failure: function(result, request){
			//getSNPViewerComplete(result);
		},
		timeout: '1800000',
		params: { result_instance_id1:  GLOBAL.CurrentSubsetIDs[1],
			result_instance_id2:  GLOBAL.CurrentSubsetIDs[2],
			chroms: GLOBAL.CurrentChroms //,
			//genes: selectedGeneStr,
			//geneAndIdList: geneAndIdListStr//,
			//snps: selectedSNPsStr
			}
	});
	
	showWorkflowStatusWindow();*/
}

function showGwasSelection() {
	
	if((!isSubsetEmpty(1) && GLOBAL.CurrentSubsetIDs[1] == null) ||
			   (!isSubsetEmpty(2) && GLOBAL.CurrentSubsetIDs[2] == null))
	{
		runAllQueries(showGwasSelection);
		return;
	}

	//genePatternReplacement();
	var win = new Ext.Window({
		id: 'showGwasSelection',
		title: 'Genome-Wide Association Study',
		layout:'fit',
		width:600,
		height:400,
		closable: false,
		plain: true,
		modal: true,
		border:false,
		buttons: [
		          {
		        	  id: 'showGwasSelectionOKButton',
		        	  text: 'OK',
		        	  handler: function(){
		        	  if(Ext.get('gwasChroms')==null)
		        	  {
		        		  win.close();
		        		  return;
		        	  }
		        	  var ob=Ext.get('gwasChroms').dom;
		        	  var selected = new Array();
		        	  for (var i = 0; i < ob.options.length; i++)
		        		  if (ob.options[i].selected)
		        			  selected.push(ob.options[i].value);
		        	  GLOBAL.CurrentChroms=selected.join(',');
		        	  showGwas();
		        	  win.close();}
		          }
		          ,{
		        	  text: 'Cancel',
		        	  handler: function(){
		        	  win.close();}
		          }],
		resizable: false,
		autoLoad: {
			url: pageInfo.basePath+'/genePattern/showGwasSelection',
			scripts: true,
			nocache:true,
			discardUrl:true,
			method:'POST',
			params: {result_instance_id1: GLOBAL.CurrentSubsetIDs[1],
				result_instance_id2: GLOBAL.CurrentSubsetIDs[2]}
		},
		tools:[{
			id:'help',
			qtip:'Click for context sensitive help',
		    handler: function(event, toolEl, panel){
		    // 1360 needs to be changed for PLINK
		   	D2H_ShowHelp("1360",helpURL,"wndExternal",CTXT_DISPLAY_FULLHELP );
		    }
		}]
	});
	//  }
	win.show(viewport);
}

/** 
 * Function to run the GWAS asynchronously
 */
function showGwas() {	
	if((!isSubsetEmpty(1) && GLOBAL.CurrentSubsetIDs[1] == null) || (!isSubsetEmpty(2) && GLOBAL.CurrentSubsetIDs[2] == null))
	{
		runAllQueries(showGwas);
		return;
	}
	
	genePatternReplacement();
	/*Ext.Ajax.request({						
		url: pageInfo.basePath+"/asyncJob/createnewjob",
		method: 'POST',
		success: function(result, request){
			runGwas(result, GLOBAL.CurrentSubsetIDs[1], GLOBAL.CurrentSubsetIDs[2],
					getQuerySummary(1), getQuerySummary(2));
		},
		failure: function(result, request){
			Ext.Msg.alert('Status', 'Unable to create the heatmap job.');
		},
		timeout: '1800000',
		params: {jobType:  "GWAS"}
	});*/
}

// After we get a job created by GPController, we run GWAS
function runGwas(result, result_instance_id1, result_instance_id2,
		querySummary1, querySummary2)	{
	var jobNameInfo = Ext.util.JSON.decode(result.responseText);					 
	var jobName = jobNameInfo.jobName;

	genePatternReplacement();
	/*showJobStatusWindow(result);	

	Ext.Ajax.request(
	{						
		url: pageInfo.basePath+"/genePattern/runGwas",
		method: 'POST',
		timeout: '1800000',
		params: {result_instance_id1: result_instance_id1,
			result_instance_id2:  result_instance_id2,
			querySummary1: querySummary1,
			querySummary2: querySummary2,
			chroms: GLOBAL.CurrentChroms,
			jobName: jobName
		}
	});
	checkJobStatus(jobName);*/
}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//START: Advanced Heatmap Workflow methods
//Called from Run Workflow button in the Heatmap Validation window 
//////////////////////////////////////////////////////////////////////////////////////////////////////
//Once, we get a job created by GPController, we run the heatmap
function RunHeatMap(result, setid1, setid2, pathway, datatype, analysis,
					resulttype, nclusters, timepoints1, timepoints2, sample1,
					sample2, rbmPanels1, rbmPanels2)	{
	var jobNameInfo = Ext.util.JSON.decode(result.responseText);					 
	var jobName = jobNameInfo.jobName;

	//genePatternReplacement();
	showJobStatusWindow(result);	
	genePatternLogin();
	Ext.Ajax.request(
		{						
			url: pageInfo.basePath+"/genePattern/runheatmap",
			method: 'POST',
			timeout: '1800000',
			params: {result_instance_id1:  setid1,
				result_instance_id2:  setid2,
				pathway_name:  pathway,
				datatype:  datatype,
				analysis:  analysis,
				resulttype: resulttype,
				nclusters: nclusters,
				timepoints1: timepoints1,
				timepoints2: timepoints2,
				sample1: sample1,
				sample2: sample2,
				rbmPanels1: rbmPanels1,
				rbmPanels2: rbmPanels2,
				jobName: jobName
			}
	});
	checkJobStatus(jobName);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////
//END: Advanced Heatmap Workflow methods
//////////////////////////////////////////////////////////////////////////////////////////////////////

//This is the new popup window for Survival Analysis. 
function showSurvivalAnalysisWindow(results)	{
	var resultWin = window.open('', 'Survival_Analysis_View_' + (new Date()).getTime(), 
		'width=600,height=800,scrollbars=yes,resizable=yes,location=no,toolbar=no,status=no,menubar=no,directories=no');
	resultWin.document.write(results);
}

//This is the new popup window for GWAS. 
function showGwasWindow(results)	{
	var resultWin = window.open('', 'Gwas_View_' + (new Date()).getTime());
	resultWin.document.write(results);
}

//This is the new popup window for the Haploview
function showHaploViewWindow(results)	{
	var win = new Ext.Window({
		id: 'showHaploView',
		title: 'Haploview',
		layout:'fit',
		width:800,
		height:550,
		closable: true,
		plain: false,
		modal: false,
		border:true,
		maximizable:true,								
		resizable: true,
		html: results
	});
	win.show(viewport);						
}

//Called to run the Haploviewer
function getHaploview()
{
	Ext.Ajax.request({						
		url: pageInfo.basePath+"/asyncJob/createnewjob",
		method: 'POST',
		success: function(result, request){
			RunHaploViewer(result, GLOBAL.CurrentSubsetIDs[1], GLOBAL.CurrentSubsetIDs[2], GLOBAL.CurrentGenes);
		},
		failure: function(result, request){
			Ext.Msg.alert('Status', 'Unable to create the heatmap job.');
		},
		timeout: '1800000',
		params: {jobType:  "Haplo"}
	});	
}

function RunHaploViewer(result, result_instance_id1, result_instance_id2, genes)
{
	var jobNameInfo = Ext.util.JSON.decode(result.responseText);					 
	var jobName = jobNameInfo.jobName;

	//genePatternReplacement();
	showJobStatusWindow(result);	
	document.getElementById("gplogin").src = pageInfo.basePath + '/analysis/gplogin';   // log into GenePattern
	Ext.Ajax.request(
		{						
			url: pageInfo.basePath+"/genePattern/runhaploviewer",
			method: 'POST',
			timeout: '1800000',
			params: {result_instance_id1: result_instance_id1,
				result_instance_id2:  result_instance_id2,
				genes: genes,
				jobName: jobName
			}
	});
	checkJobStatus(jobName);
}
