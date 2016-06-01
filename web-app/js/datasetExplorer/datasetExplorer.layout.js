/*************************************************************************
 * tranSMART - translational medicine data mart
 * 
 * Copyright 2008-2012 Janssen Research & Development, LLC.
 * 
 * This product includes software developed at Janssen Research & Development, LLC.
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software  * Foundation, either version 3 of the License, or (at your option) any later version, along with the following terms:
 * 1.	You may convey a work based on this program in accordance with section 5, provided that you retain the above notices.
 * 2.	You may convey verbatim copies of this program code as you receive it, in any medium, provided that you retain the above notices.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS    * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *
 ******************************************************************/
  
Ext.onReady(function()
		{
	Ext.QuickTips.init();

	//set ajax to 600*1000 milliseconds
	Ext.Ajax.timeout = 1800000;

	// this overrides the above
	Ext.Updater.defaults.timeout = 1800000;


	// create the main regions of the screen
	westPanel = new Ext.Panel(
			{
				id : 'westPanel',
				region : 'west',
				width : 330,
				minwidth : 200,
				split : true,
				border : true,
				layout : 'border'
			}
	);

	/* eastPanel = new Ext.Panel({
   id : 'eastPanel',
   region : 'east',
   width : 330,
   minwidth : 200,
   split : true,
   border : true,
   layout : 'border'}); */
	
	if(GLOBAL.Config != "jj")
	{
		northPanel = new Ext.Panel(
				{
					id : 'northPanel',
					html : '<div style="padding:5px;background:#eee;font:14pt arial"><table><tr><td><img src="/images/i2b2_hive_32.gif"></img></td><td><span style="font:arial 14pt;"><b> i2b2 Web Client</b></span></td></tr></table></div>',
					region : 'north',
					height : 45,
					split : false,
					border : true
				}
		);
	}
	else
	{
		northPanel = new Ext.Panel(
				{
					id : 'northPanel',
					region : 'north',
					height : 30,
					split : false,
					border : true,
					tbar : createUtilitiesMenu(GLOBAL.HelpURL, GLOBAL.ContactUs, GLOBAL.AppTitle,GLOBAL.basePath, GLOBAL.BuildVersion, 'utilities-div',GLOBAL.CurrentUserName),
					contentEl: "header-div"
				}
		);
	}
	qphtml = "<div style='margin: 10px'>Query Criteria<br /><select size='8' id='queryCriteriaSelect1' style='width:400px; height:250px;'></select><br />\
		< button onclick = 'resetQuery()' > Reset < / button > < br / > < div id = 'queryCriteriaDiv1' style = 'font:11pt;width:200px; height:250px; white-space:nowrap;overflow:auto;border:1px solid black' > < / div > < / div > "

		var tb = new Ext.Toolbar(
				{
					id : 'maintoolbar',
					title : 'maintoolbar',
					items : [new Ext.Toolbar.Button(
							{
								id : 'changetool',
								text : 'Switch to subset view',
								iconCls : 'nextbutton',
								disabled : false,
								handler : function()
								{
								window.location.href = "i2b2client.jsp"
								}
							}
					)]
				}
		);

		advmenu = new Ext.menu.Menu(
				{
					id : 'advancedMenu',
					minWidth: 250,
					items : [
					         {
					        	 text : 'Heatmap',
					        	 disabled : true,
					        	 // when checked has a boolean value, it is assumed to be a CheckItem
					        	 handler : function()
					        	 {
					        	 	GLOBAL.HeatmapType = 'Compare';
					        	 	validateHeatmap();
					        	 	advancedWorkflowContextHelpId="1085";
					        	 },
					        	 disabled : GLOBAL.GPURL == "" 
					         }
					         ,
					         {
					        	 text : 'Hierarchical Clustering',
					        	 disabled : true,
					        	 // when checked has a boolean value, it is assumed to be a CheckItem
					        	 handler : function()
					        	 {
					        	 	GLOBAL.HeatmapType = 'Cluster';
					        	 	validateHeatmap();
					        	 	advancedWorkflowContextHelpId="1085";
					        	 },
					        	 disabled : GLOBAL.GPURL == ""
					         }
					         ,
					         {
					        	 text : 'K-Means Clustering',
					        	 disabled : true,
					        	 // when checked has a boolean value, it is assumed to be a CheckItem
					        	 handler : function()
					        	 {
					        	 	GLOBAL.HeatmapType = 'KMeans';
					        	 	validateHeatmap();
					        	 	advancedWorkflowContextHelpId="1085";
					        	 },
					        	 disabled : GLOBAL.GPURL == ""
					         }
					         ,
					         {
					        	 text : 'Comparative Marker Selection (Heatmap)',
					        	 disabled : true,
					        	 // when checked has a boolean value, it is assumed to be a CheckItem
					        	 handler : function()
					        	 {
					        	 	GLOBAL.HeatmapType = 'Select';
					        	 	validateHeatmap();
					        	 	advancedWorkflowContextHelpId="1085";
					        	 },
					        	 disabled : GLOBAL.GPURL == ""
					         }
					         ,
				        	 '-' 
					         ,					         
					         {
					        	 text : 'Principal Component Analysis',
					        	 disabled : true,
					        	 // when checked has a boolean value, it is assumed to be a CheckItem
					        	 handler : function()
					        	 {
					        	 	GLOBAL.HeatmapType = 'PCA';
					        	 	validateHeatmap();
					        	 	advancedWorkflowContextHelpId="1172";
					        	 },
					        	 disabled : GLOBAL.GPURL == ""
					         }
					         ,
				        	 '-'
				        	 ,
					         {
					        	 text : 'Survival Analysis',
					        	 handler : function()
					        	 {
					        	 	if(isSubsetEmpty(1) || isSubsetEmpty(2))
					        	 	{
					        			alert('Survival Analysis needs time point data from both subsets.');
					        		 	return;
					        	 	}
					        	 	else {
					        	 		showSurvivalAnalysis();
					        	 	}
					        	 },
					        	 disabled : GLOBAL.GPURL == ""
					         }
					         ,
				        	 '-'
				        	 ,
					         {
					        	 text : 'Haploview',
					        	 handler : function()	{
					        	 	if(isSubsetEmpty(1) && isSubsetEmpty(2))
					        	 	{
					        			alert('Empty subsets found, need a valid subset to analyze!');
					        		 	return;
					        	 	}
					        	 	if((GLOBAL.CurrentSubsetIDs[1] == null && ! isSubsetEmpty(1)) || (GLOBAL.CurrentSubsetIDs[2] == null && ! isSubsetEmpty(2)))
					        	 	{
					        			runAllQueries(function()	{
					        				showHaploviewGeneSelection();
					        			});
					        	 	} else	{
					        	 		showHaploviewGeneSelection()
					        	 	}
					        	 	return;
					        	}
					        }
					        ,
					        {
					        	 text : 'SNPViewer',
					        	 disabled : true,
					        	 handler : function()	{
					        	 	if(isSubsetEmpty(1) && isSubsetEmpty(2))
					        	 	{
					        			alert('Both dataset is empty. Please choose a valid dataset.');
					        		 	return;
					        	 	}
					        	 	if((GLOBAL.CurrentSubsetIDs[1] == null && ! isSubsetEmpty(1)) || (GLOBAL.CurrentSubsetIDs[2] == null && ! isSubsetEmpty(2)))
					        	 	{
					        			runAllQueries(function()	{
					        				showSNPViewerSelection();
					        			});
					        	 	} else	{
					        	 		showSNPViewerSelection();
					        	 	}
					        	 	return;
					        	},
					        	disabled : GLOBAL.GPURL == ""
					        }
					        ,
					        {
					        	 text : 'Integrative Genome Viewer',
					        	 disabled : true,
					        	 handler : function()	{
					        	 	if(isSubsetEmpty(1) && isSubsetEmpty(2))
					        	 	{
					        			alert('Both dataset is empty. Please choose a valid dataset.');
					        		 	return;
					        	 	}
					        	 	if((GLOBAL.CurrentSubsetIDs[1] == null && ! isSubsetEmpty(1)) || (GLOBAL.CurrentSubsetIDs[2] == null && ! isSubsetEmpty(2)))
					        	 	{
					        			runAllQueries(function()	{
					        				showIgvSelection();
					        			});
					        	 	} else	{
					        	 		showIgvSelection();
					        	 	}
					        	 	return;
					        	},
					        	disabled : GLOBAL.GPURL == ""
					        }
					        ,
					        {
					        	 text : 'PLINK',
					        	 disabled : true,
					        	 handler : function()	{
					        	 	if(isSubsetEmpty(1) && isSubsetEmpty(2))
					        	 	{
					        			alert('Both dataset is empty. Please choose a valid dataset.');
					        		 	return;
					        	 	}
					        	 	if((GLOBAL.CurrentSubsetIDs[1] == null && ! isSubsetEmpty(1)) || (GLOBAL.CurrentSubsetIDs[2] == null && ! isSubsetEmpty(2)))
					        	 	{
					        			runAllQueries(function()	{
					        				showPlinkSelection();
					        			});
					        	 	} else	{
					        	 		showPlinkSelection();
					        	 	}
					        	 	return;
					        	}
					        },
					        {
					        	 text : 'Genome-Wide Association Study',
					        	 handler : function()	{
					        	 	if(isSubsetEmpty(1) || isSubsetEmpty(2))
					        	 	{
					        			alert('Genome-Wide Association Study needs control datasets (normal patients) in subset 1, and case datasets (disease patients) in subset 2.');
					        		 	return;
					        	 	}
					        	 	if((GLOBAL.CurrentSubsetIDs[1] == null && ! isSubsetEmpty(1)) || (GLOBAL.CurrentSubsetIDs[2] == null && ! isSubsetEmpty(2)))
					        	 	{
					        			runAllQueries(function()	{
					        				showGwasSelection();
					        			});
					        	 	} else	{
					        	 		showGwasSelection();
					        	 	}
					        	 	return;
					        	}
					        }					   ]
				}
		);


		var tb2 = new Ext.Toolbar(
				{
					id : 'maintoolbar',
					title : 'maintoolbar',
					items : [new Ext.Toolbar.Button(
							{
								id : 'generatesubsetsbutton',
								text : 'Generate Summary Statistics',
								iconCls : 'runbutton',
								disabled : false,
								handler : function()
								{
								// alert('generate');
								GLOBAL.CurrentSubsetIDs[1] = null;
								GLOBAL.CurrentSubsetIDs[2] = null;
								GLOBAL.currentAnalysisType = "SUMMARY"
									
								runAllQueries(getSummaryStatistics);

								}
							}
					),

					new Ext.Toolbar.Separator(),
					new Ext.Toolbar.Button(
							{
								id : 'generateWESstats',
								text : 'Generate WES Statistics',
								iconCls : 'runbutton',
								disabled : true,
								handler : function()
								{
								// alert('generate');
								GLOBAL.CurrentSubsetIDs[1] = null;
								GLOBAL.CurrentSubsetIDs[2] = null;
								GLOBAL.currentAnalysisType = "VARIANT"
									
								runAllQueries(getWESstatistics);

								}
							}
					),
					
                    new Ext.Toolbar.Separator(),
                    new Ext.Toolbar.Button(
							{
								id : 'exploreSampleInformationButton',
								text : 'Explore Sample Information',
								iconCls : 'samplebutton',
								disabled : true,
								handler : function()
								{
								    GLOBAL.CurrentSubsetIDs[1] = null;
								    GLOBAL.CurrentSubsetIDs[2] = null;

								    generatePatientSampleCohort(launchSampleBrowseWithCohort);

								}
							}
					),					
					
					new Ext.Toolbar.Separator(),
					new Ext.Toolbar.Button(
							{
								id : 'showquerysummarybutton',
								text : 'Summary',
								iconCls : 'summarybutton',
								disabled : false,
								handler : function()
								{
								// alert('clear');
								showQuerySummaryWindow();
								}
							}
					),
					
					new Ext.Toolbar.Separator(),
					new Ext.Toolbar.Button(
							{
								id : 'clearsubsetsbutton',
								text : 'Clear',
								iconCls : 'clearbutton',
								disabled : false,
								handler : function()
								{
								if(confirm("Are you sure you want to clear your current analysis?"))
									{
										clearAnalysisPanel();
										resetQuery();
										clearDataAssociation();	
										clearSubsetSummary();
									}
								}
							}
					),
					'->',
					new Ext.Toolbar.Separator(),
					new Ext.Toolbar.Button(
							{
								id : 'printanalysisbutton',
								text : 'Print',
								iconCls : 'printbutton',
								disabled : false,
								handler : function()
								{
								// alert('print');
								// analysisPanel.iframe.print();
								var text = getAnalysisPanelContent();
								printPreview(text);
								}
							}
					)
					]
				}
		);




		centerMainPanel = new Ext.Panel(
				{
					id : 'centerMainPanel',
					region : 'center',
					// tbar : tb,
					layout : 'border'
				}
		);

		centerPanel = new Ext.Panel(
				{
					id : 'centerPanel',
					region : 'center',
					width : 500,
					minwidth : 150,
					split : true,
					border : true,
					layout : 'fit',
					tbar:tb2
				}
		);

		queryPanel = new Ext.Panel(
				{
					id : 'queryPanel',
					title : 'Comparison',
					region : 'north',
					height : 340,
					autoScroll : true,
					split : true,					
					autoLoad :
					{
						url : pageInfo.basePath+'/panels/subsetPanel.gsp',
						scripts : true,
						nocache : true,
						discardUrl : true,
						method : 'POST'//,
						//callback: loadOntPanel
					},
					collapsible : true,
					titleCollapse : false,
					animCollapse : false,
			        listeners :
					{
						activate : function() {
							GLOBAL.Analysis="Advanced";
						}
					},
					bbar: new Ext.StatusBar({
						// Status bar to show the progress of generating heatmap and other advanced workflows
				        id: 'asyncjob-statusbar',
				        defaultText: 'Ready',
				        defaultIconCls: 'default-icon',
				        text: 'Ready',
				        statusAlign: 'right',
				        iconCls: 'ready-icon',
				        items: [{
				        	xtype: 'button',
				        	id: 'cancjob-button',
				        	text: 'Cancel',
				        	hidden: true
				        }]				        
				    })
				}
		);

		resultsPanel = new Ext.Panel(
				{
					id : 'resultsPanel',
					title : 'Results',
					region : 'center',
					split : true,
					height : 90
				}
		);

		resultsTabPanel = new Ext.TabPanel(
				{
					id : 'resultsTabPanel',
					title : 'Analysis/Results',
					region : 'center',

					defaults :
					{
					hideMode : 'display'
					}
				,
				collapsible : false,
				//height : 300,
				//width : 300,
				deferredRender : false,
				//split : true,
				//tbar : tb2,
				activeTab : 0
				}
		);

		/* Check whether user has permissions to Grid View, Export Job and Data Export tab */
		if (!GLOBAL.IsAdmin)
		{
			analysisGridPanel = new Ext.Panel(
				{
					id : 'analysisGridPanel',
					title : 'Grid View',
					region : 'center',
					disabled:true,
					split : true,
					height : 90,
					layout : 'fit',
					listeners :
                    {
						
                        activate: function(p)
                        {
                       
                        	//If we haven't loaded the subsets yet, do so and load the grid.
                        if (GLOBAL.CurrentSubsetIDs[1] == null && GLOBAL.CurrentSubsetIDs[2] == null)
                            {
                          	  	//analysisGridPanel.body.mask('Getting subsets...', 'x-mask-loading');
                          	  	
                            	//Run the query and get the grid data.
                                runAllQueries(getSummaryGridData, p);          
                            }
                            else if(!GLOBAL.isGridViewLoaded)
                            {
                            	//analysisGridPanel.body.mask('Getting subsets...', 'x-mask-loading');
                            	
                            	//The grid isn't loaded but subsets have been run already, just load the grid.
                            	getSummaryGridData();
                            }

                    	}
                    }
				}
		);
		
		analysisDataExportPanel = new Ext.Panel(
				{
					id : 'analysisDataExportPanel',
					title : 'Data Export',
					region : 'center',
					split : true,
					disabled:true,
					height : 90,
					layout : 'fit',
					listeners :
					{
						
					
				activate : function(p) {
							GLOBAL.CurrentSubsetIDs[1] = null;
							GLOBAL.CurrentSubsetIDs[2] = null;
							
							p.body.mask("Loading...", 'x-mask-loading');
							
							runAllQueries(getDatadata, p);
							
			        	 	return;
			        	 	}
						,
						deactivate: function(){
							
								
								
									}
					},
					collapsible : true						
				}
		);
		
		analysisExportJobsPanel = new Ext.Panel(
				{
					id : 'analysisExportJobsPanel',
					title : 'Export Jobs',
					region : 'center',
					split : true,
					disabled:true,
					height : 90,
					layout : 'fit',
					listeners :
					{
						activate : function(p) {
							getExportJobs(p)
						},
						deactivate: function(){
						}
					},
					collapsible : true						
				}
		);
		}
         else
		{

		analysisGridPanel = new Ext.Panel(
				{
					id : 'analysisGridPanel',
					title : 'Grid View',
					region : 'center',
					split : true,
					height : 90,
					layout : 'fit',
					listeners :
                    {
                        activate: function(p)
                        {
                        	//If we haven't loaded the subsets yet, do so and load the grid.
                            if (GLOBAL.CurrentSubsetIDs[1] == null && GLOBAL.CurrentSubsetIDs[2] == null)
                            {
                          	  	analysisGridPanel.body.mask('Getting subsets...', 'x-mask-loading');
                          	  	
                            	//Run the query and get the grid data.
                                runAllQueries(getSummaryGridData, p);          
                            }
                            else if(!GLOBAL.isGridViewLoaded)
                            {
                            	analysisGridPanel.body.mask('Getting subsets...', 'x-mask-loading');
                            	
                            	//The grid isn't loaded but subsets have been run already, just load the grid.
                            	getSummaryGridData();
                            }

                    	}
                    }
				}
		);
		

		analysisDataExportPanel = new Ext.Panel(
				{
					id : 'analysisDataExportPanel',
					title : 'Data Export',
					region : 'center',
					split : true,
					height : 90,
					layout : 'fit',
					listeners :
					{
						activate : function(p) {
							GLOBAL.CurrentSubsetIDs[1] = null;
							GLOBAL.CurrentSubsetIDs[2] = null;
							p.body.mask("Loading...", 'x-mask-loading');
							runAllQueries(getDatadata, p);
			        	 	return;
						},
						deactivate: function(){
						}
					},
					collapsible : true						
				}
		);
		
		analysisExportJobsPanel = new Ext.Panel(
				{
					id : 'analysisExportJobsPanel',
					title : 'Export Jobs',
					region : 'center',
					split : true,
					height : 90,
					layout : 'fit',
					listeners :
					{
						activate : function(p) {
							getExportJobs(p)
						},
						deactivate: function(){
						}
					},
					collapsible : true						
				}
		);
		}

		analysisPanel = new Ext.Panel(
				{
					id : 'analysisPanel',
					title : 'Results/Analysis',
					region : 'center',
					fitToFrame : true,
					listeners :
					{
					activate : activateTab,
					deactivate: function(){
						
					}
					}
				,
				autoScroll : true,
				html : '<div style="text-align:center;font:12pt arial;width:100%;height:100%;"><table style="width:100%;height:100%;"><tr><td align="center" valign="center">Drag concepts to this panel to view a breakdown of the subset by that concept</td></tr></table></div>',
				split : true,
				closable : false,
				height : 90
				}
		);
		
		subsetAnalysisPanel = new Ext.Panel(
				{
					id : 'subsetPanel',
					title : 'Overlap Diagram',
					region : 'center',
					fitToFrame : true,
					listeners :
					{
					activate : function()
					{
						if(!GLOBAL.isSubsetSummaryInitialized) getSubsetSummary_Initial();
					},
					deactivate: function(){
						
					}
					}
				,
				autoScroll : true,
				html : "<br /><span class='AnalysisHeader' style='padding:10px;font-size: 18px;'>Overlap Diagram &nbsp;&nbsp;&nbsp;<a href='#' style='font-size:14px;' onclick='emptySubsetSummary();'>Clear Summary</a></span><br /><div id='subsetSummaryTitleDiv' style='padding:10px;font: 14px tahoma,arial,helvetica,sans-serif;'>Numbers below represent a count of distinct patients.</div><hr /><div id='subsetSummaryDiv'></div>",
				split : true,
				closable : false,
				height : 90
				}
		);


		
		dataAssociationPanel = new Ext.Panel(
				{
					id : 'dataAssociationPanel',
					title : 'Advanced Workflow',
					region : 'center',
					split : true,
					height : 90,
					layout : 'fit',
					tbar : new Ext.Toolbar({
						id : 'advancedWorkflowToolbar',
						title : 'Advanced Workflow actions',
						items : []
						}),
					autoScroll : true,
					autoLoad:
			        {
			        	url : pageInfo.basePath+'/dataAssociation/defaultPage',
			           	method:'POST',
			           	callback: setDataAssociationAvailableFlag,
			           	evalScripts:true
			        },
			        listeners :
					{
			        	activate : function() {
							GLOBAL.Analysis="dataAssociation";
							renderCohortSummary("cohortSummary");
						}
					},
					collapsible : true
				}
		);
		

		resultsTabPanel.add(queryPanel);
		resultsTabPanel.add(dataAssociationPanel);
		resultsTabPanel.add(analysisPanel);
		resultsTabPanel.add(analysisGridPanel);
		resultsTabPanel.add(analysisDataExportPanel);
		resultsTabPanel.add(analysisExportJobsPanel);
		
		southCenterPanel = new Ext.Panel(
				{
					id : 'southCenterPanel',
					region : 'center',
					layout : 'border',
					split : true,
					tbar : tb2
				}
		);

		exportPanel = new Ext.Panel(
				{
					id : 'exportPanel',
					title : 'Compare/Export',
					region : 'east',
					html : '<div style="text-align:center;font:12pt arial;width:100%;height:100%;"><table style="width:100%;height:100%;"><tr><td align="center" valign="center">Drag subsets to this panel to compare and export them</td></tr></table></div>',
					split : true,
					width : 300,
					height : 90,
					buttons : [
					           {
					        	   text : 'Compare',
					        	   handler : function()
					        	   {
					        	   var subsets = exportPanel.body.dom.childNodes;
					        	   if (subsets.length != 2)
					        	   {
					        		   alert("Must have two subsets!");
					        	   }
					        	   else showCompareStepPathwaySelection();
					        	   }
					           }
					           ,
					           {
					        	   text : 'Export',
					        	   iconCls : 'exportbutton',
					        	   handler : function()
					        	   {
					        	   showExportStepSplitTimeSeries();
					        	   }
					           }
					           ,
					           {
					        	   text : 'Clear',
					        	   iconCls : 'clearbutton',
					        	   handler : function()
					        	   {
					        	   clearExportPanel();
					        	   }
					           }

					           ]
				}
		);

		var treetitle = "Previous Queries";
		if(GLOBAL.Config == 'jj')
			treetitle = "Subsets";

		var Tree = Ext.tree;
		prevTree = new Tree.TreePanel(
				{
					id : 'previousQueriesTree',
					title : treetitle,
					animate : false,
					autoScroll : true,
					// loader : new Ext.ux.OntologyTreeLoader({dataUrl : 'none'}),
					enableDrag : true,
					ddGroup : 'makeQuery',
					containerScroll : true,
					enableDrop : false,
					region : 'south',
					rootVisible : false,
					expanded : true,
					split : true,
					height : 300
				}
		);

		prevTreeRoot = new Tree.TreeNode(
				{
					text : 'root',
					draggable : false,
					id : 'prevroot',
					qtip : 'root'
				}
		);



		prevTree.setRootNode(prevTreeRoot);

		// start filling in each region with the content panels
		// if(GLOBAL.Config == "jj")
		// {
		// southCenterPanel.add(exportPanel);
		// }
		// southCenterPanel.add(resultsPanel);
		// southCenterPanel.add(analysisPanel);
		// southCenterPanel.add(southCenterCenterPanel);


		/*southCenterPanel.add(resultsTabPanel);
         centerPanel.add(queryPanel);*/

		/**********new prototype*********/

		centerPanel.add(resultsTabPanel);
		/********************************/

		//centerPanel.add(southCenterPanel);


		// centerPanel.add(analysisPanel);
		// centerPanel.add(resultsTabPanel);
		
		westPanel.add(createOntPanel());
		//setTimeout("loadOntPanel()", 3000);
		// westPanel.add(prevTree);
		// eastPanel.add(exportPanel);
		centerMainPanel.add(westPanel);
		centerMainPanel.add(centerPanel);

		viewport = new Ext.Viewport(
				{
					layout : 'border',
					items : [centerMainPanel, northPanel]
				}
		);

		Ext.get(document.body).addListener('contextmenu', contextMenuPressed);
		// prevTree.dragZone.addToGroup("export");


		// preload the setvalue dialog
		setvaluePanel = new Ext.Panel(
				{
					id : 'setvaluePanel',
					region : 'north',
					height : 140,
					width : 490,
					split : false,
					autoLoad :
					{
					url : pageInfo.basePath+'/panels/setValueDialog.html',
					scripts : true,
					nocache : true,
					discardUrl : true,
					method : 'POST'
					}
				}
		);

		setvaluechartsPanel1 = new Ext.Panel(
				{
					id : 'setvaluechartsPanel1',
					region : 'center',
					width : 245,
					height : 180,
					split : false
				}
		);

		setvaluechartsPanel2 = new Ext.Panel(
				{
					id : 'setvaluechartsPanel2',
					region : 'east',
					width : 245,
					height : 180,
					split : false
				}
		);

		// preload the setvalue dialog
		if( ! this.setvaluewin)
		{
			setvaluewin = new Ext.Window(
					{
						id : 'setValueWindow',
						title : 'Set Value',
						layout : 'border',
						width : 500,
						height : 240,
						closable : false,
						plain : true,
						modal : true,
						border : false,
						items : [setvaluePanel , setvaluechartsPanel1, setvaluechartsPanel2],
						buttons : [
						           {
						        	   text : 'Show Histogram',
						        	   handler : function()
						        	   {
						        	   showConceptDistributionHistogram();
						        	   }
						           }
						           ,
						           {
						        	   text : 'Show Histogram for subset',
						        	   handler : function()
						        	   {
						        	   var subset;
						        	   if(selectedConcept.parentNode.id == "hiddenDragDiv")
						        	   {
						        		   subset = getSubsetFromPanel(STATE.Target);
						        	   }
						        	   else
						        	   {
						        		   subset = getSubsetFromPanel(selectedConcept.parentNode)
						        	   }

						        	   if(!isSubsetEmpty(subset))
						        	   {
						        		   runQuery(subset, showConceptDistributionHistogramForSubset);
						        	   }
						        	   else alert('Subset is empty!');
						        	   }
						           }
						           ,
						           {
						        	   text : 'OK',
						        	   handler : function()
						        	   {
						        	   var mode = getSelected(document.getElementsByName("setValueMethod"))[0].value;
						        	   var highvalue = document.getElementById("setValueHighValue").value;
						        	   var lowvalue = document.getElementById("setValueLowValue").value;
						        	   var units = document.getElementById("setValueUnits").value;
						        	   var operator = document.getElementById("setValueOperator").value;
						        	   var highlowselect = document.getElementById("setValueHighLowSelect").value;

						        	   // make sure that there is a value set
						        	   if (mode=="numeric" && operator == "BETWEEN" && (highvalue == "" || lowvalue== "")){
						        		   alert('You must specify a low and a high value.');
						        	   } else if (mode=="numeric" && lowvalue == "") {
						        		   alert('You must specify a value.');
						        	   } else {
						        		   setvaluewin.hide();
						        		   setValueDialogComplete(mode, operator, highlowselect, highvalue, lowvalue, units);
						        	   }
						        	   }
						           }
						           ,
						           {
						        	   text : 'Cancel',
						        	   handler : function()
						        	   {
						        	   setvaluewin.hide();
						        	   }
						           }
						           ],
						           resizable : false

					}
			);
			setvaluewin.show();
			setvaluewin.hide();
		}


		showLoginDialog();
		var h=queryPanel.header;
		//alert(h);
		}


);


function createOntPanel()
{
	// make tab panel, search panel, ontTree and combine them
	ontTabPanel = new Ext.TabPanel(
			{
				id : 'ontPanel',
				region : 'center',
				defaults :
				{
				hideMode : 'offsets'
				}
			,
			collapsible : false,
			height : 300,
			width : 300,
			deferredRender : false,
			split : true
			}
	);

	/* ontSearchTermsPanel = new Ext.TabPanel({
   id : 'searchTermsPanel',
   title : 'Find Terms',
   region : 'center',
   deferredRender : false,
   border : true}); */



	ontSearchByCodePanel = new Ext.Panel(
			{
				id : 'searchByCodePanel',
				title : 'Search by Codes',
				region : 'center'
			}
	);


	searchByNamePanel = new Ext.Panel(
			{
				title : 'Search by Names',
				id : 'searchByNamePanel',
				region : 'center',
				height : 500,
				width : 300,
				border : true,
				bodyStyle : 'background:lightgrey;',
				layout : 'border',
				split : true
			}
	);

	// make the ontSerchByNamePanel
	shtml='<table style="font:10pt arial;"><tr><td><select id="searchByNameSelect"><option value="left">Starting with</option><option value="right">Ending with</option>\
		<option value="contains" selected>Containing</option><option value="exact">Exact</option></select>&nbsp;&nbsp;</td<td><input id="searchByNameInput" onkeypress="if(enterWasPressed(event)){searchByName();}" type="text" size="15"></input>&nbsp;</td>\
		<td><button onclick="searchByName()">Find</button></td></tr><tr><td colspan="2">Select Ontology:<select id="searchByNameSelectOntology"></select></td></tr></table>';

		searchByNameForm = new Ext.Panel(
				{
					// title : 'Search by Form',
					id : 'searchByNameForm',
					region : 'north',
					bodyStyle : 'background:#eee;padding: 10px;',
					html : shtml,
					height : 70,
					border : true,
					split : false
				}
		);

		// shorthand
		var Tree = Ext.tree;

		searchByNameTree = new Tree.TreePanel(
				{
					id : 'searchByNameTree',
					// title : 'Search Results',
					animate : false,
					autoScroll : true,
					loader : new Ext.ux.OntologyTreeLoader(
							{
								dataUrl : 'none'
							}
					),
					enableDrag : true,
					// bodyStyle : 'padding 10px;',
					ddGroup : 'makeQuery',
					containerScroll : true,
					enableDrop : false,
					region : 'center',
					rootVisible : false,
					expanded : true,
					split : true,
					border : true,
					height : 400
				}
		);

		searchByNameTreeRoot = new Tree.TreeNode(
				{
					text : 'root',
					draggable : false,
					id : 'root',
					qtip : 'root'
				}
		);
		// add a tree sorter in folder mode
		new Tree.TreeSorter(searchByNameTree,
				{
			folderSort : true
				}
		);

		searchByNameTree.setRootNode(searchByNameTreeRoot);
		searchByNamePanel.add(searchByNameForm);
		searchByNamePanel.add(searchByNameTree);
//		******************************************************************************
//		FILTER PANEL
//		******************************************************************************
		var showFn = function(node, e){
			Ext.tree.TreePanel.superclass.onShow.call(this);
			//Ext.get('advancedbutton').dom.style.display='';
		}
		ontFilterPanel = new Ext.Panel(
				{
					title : 'Search by Subject',
					id : 'ontFilterPanel',
					region : 'center',
					height : 500,
					width : 300,
					border : true,
					bodyStyle : 'background:lightgrey;',
					onShow : showFn,
					layout : 'border'
						//layout: 'table',
						//layoutConfig:{columns:1},
						//split : true
				}
		);

		ontFilterForm = new Ext.Panel(
				{
					title : 'Search',
					id : 'ontFilterForm',
					region : 'north',
					bodyStyle : 'background:#eee;padding: 10px;',
					//html : shtml,
					height : 130,
					border : true,
					split : false,
					//autoScroll: true,
					autoLoad :
					{
					url : pageInfo.basePath+'/ontology/showOntTagFilter',
					scripts : true,
					nocache : true,
					discardUrl : true,
					method : 'POST',
					callback : ontFilterLoaded
					}
				}
		);

		// shorthand
		var Tree = Ext.tree;

		ontFilterTree = new Tree.TreePanel(
				{
					id : 'ontFilterTree',
					// title : 'Search Results',
					animate : false,
					autoScroll : true,
					loader : new Ext.ux.OntologyTreeLoader(
							{
								dataUrl : 'none'
							}
					),
					enableDrag : true,
					// bodyStyle : 'padding 10px;',
					ddGroup : 'makeQuery',
					containerScroll : true,
					enableDrop : false,
					region : 'center',
					rootVisible : false,
					expanded : true,
					//split : true,
					border : true,
					height : 400
				}
		);

		ontFilterTreeRoot = new Tree.TreeNode(
				{
					text : 'root',
					draggable : false,
					id : 'root',
					qtip : 'root'
				}
		);
		// add a tree sorter in folder mode
		new Tree.TreeSorter(ontFilterTree,
				{
			folderSort : true
				}
		);

		ontFilterTree.setRootNode(ontFilterTreeRoot);
		ontFilterPanel.add(ontFilterForm);
		ontFilterPanel.add(ontFilterTree);
		// ontTabPanel.add(ontSearchByCodePanel);

		return ontTabPanel;
}

function showProjectDialog(projects)
{

	// create the array
	Ext.projects = [];

	/* Ext.projects = [
      ['AL', 'Alabama'],
      ['AK', 'Alaska'],
      ['AZ', 'Arizona'],
      ['AR', 'Arkansas'],
      ['CA', 'California'],
      ['CO', 'Colorado'],
      ['CN', 'Connecticut'],
      ['DE', 'Delaware'],
      ['DC', 'District of Columbia'] ]; */

	// populate the array
	for(c = 0; c < projects.length; c ++ )
	{
		var p = projects[c].getAttribute("id");
		var a = [];
		a[0] = p;
		a[1] = p;
		Ext.projects[c] = a;
	}


	projectwin = new Ext.Window(
			{
				id : 'projectWindow',
				title : 'Projects',
				layout : 'fit',
				width : 350,
				height : 140,
				closable : false,
				plain : true,
				modal : true,
				border : false,
				resizable : false
			}
	);

	// simple array store
	var store = new Ext.data.SimpleStore(
			{
				fields : ['id', 'projects'],
				data : Ext.projects
			}
	);



	var drdprojects = new Ext.form.ComboBox(
			{
				id : 'drdproject',
				name : 'drdproject',
				title : 'Projects',
				store : store,
				fieldLabel : 'Projects',
				displayField : 'projects',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : 'Select a project...',
				selectOnFocus : true
			}
	);




	projectform = new Ext.FormPanel(
			{
				id : 'projectForm',
				labelWidth : 75,
				frame : true,
				region : 'center',
				width : 350,
				height : 130,
				defaults :
				{
				width : 230
				}
			,
			defaultType : 'textfield',
			items : [drdprojects],
			buttons : [
			           {
			        	   text : 'Select',
			        	   handler : function()
			        	   {
			        	   projectwin.hide();
			        	   projectDialogComplete(drdprojects.getValue());
			        	   }
			           }
			           ,
			           {
			        	   text : 'Cancel',
			        	   handler : closeBrowser
			           }
			           ]
			}
	);

	projectwin.add(projectform);
	projectwin.show(viewport);
}



function projectDialogComplete(projectid)
{

	// get the project id
	GLOBAL.ProjectID = projectid;
	/* var u = queryPanel.getUpdater();
      while(u.isUpdating())
      {
      alert('waiting');
      } */
	getCategories();
	//getPreviousQueries();
	if(GLOBAL.RestoreComparison)
	{
		getPreviousQueryFromID(1, GLOBAL.RestoreQID1);
		getPreviousQueryFromID(2, GLOBAL.RestoreQID2);
	}
	if((!GLOBAL.Tokens.indexOf("EXPORT")>-1) && (!GLOBAL.IsAdmin))
	{
		//Ext.getCmp("exportbutton").disable();
	}
}