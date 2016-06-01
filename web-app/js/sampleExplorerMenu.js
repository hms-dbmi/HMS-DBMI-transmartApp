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
  

function createMainExplorerWindow(westPanelURL, westPanelJsonData, centerPanelURL, centerPanelJsonData, booleanCreateSearchBox) 
{
	centerMainPanel = new Ext.Panel({
		id : 'centerMainPanel',
		region : 'center',
		layout : 'border'
	});
	
	centerMainPanel.add(createWestPanel(westPanelURL, westPanelJsonData));
	centerMainPanel.add(createCenterPanel(centerPanelURL, centerPanelJsonData, booleanCreateSearchBox));

	viewport = new Ext.Viewport({
		layout : 'border',
		items : [centerMainPanel, createNorthPanel() ]
	});
	
}

function createWestPanel(westPanelURL, westPanelJsonData)
{
	westPanel = new Ext.Panel({
		id : 'westPanel',
		region : 'west',
		autoLoad:
        {
        	url: westPanelURL,
           	method:'POST',
           	jsonData: westPanelJsonData
        },			
		width : 230,
		minwidth : 200,
		split : false,
		border : true,
		layout : 'border'
	});

	ontTabPanel = new Ext.FormPanel(
			{
				id : 'ontPanel',
				region : 'center',
				defaults :
				{
				hideMode : 'offsets'
				}
			,
			collapsible : false,
			height : 600,
			width : 230,
			items : [{}],
			deferredRender : false,
			split : true
			}
	);
	
	westPanel.add(ontTabPanel);

	return westPanel;
}

//Create the north most panel.
function createNorthPanel()
{
    northPanel = new Ext.Panel({
		id : 'northPanel',
		region : 'north',
		tbar : createUtilitiesMenu(GLOBAL.HelpURL, GLOBAL.ContactUs, GLOBAL.AppTitle,GLOBAL.basePath, GLOBAL.BuildVersion, 'utilities-div'),
		split : false,
		border : true,
		contentEl : "header-div"
	});	

	return northPanel;
}

/*
 * This creates the center panel including toolbar, main category selection, result data grids. 
 */
function createCenterPanel(centerPanelURL, centerPanelJsonData, booleanCreateSearchBox)
{
	var centerPanelCallbackFunction = null;
	
	if(booleanCreateSearchBox)
	{
		centerPanelCallbackFunction = createSearchBoxMain
	}
	
	//Draw our toolbar. Some items are hidden till we select an initial category.
	var tb2 = new Ext.Toolbar(
			{
				id : 'maintoolbar',
				title : 'maintoolbar',
				region : 'west',
				split : true,
				layout : 'fit',
				items : [
						
						new Ext.Toolbar.Button({
							id : 'advancedbutton',
							text : 'Advanced Workflow',
							iconCls : 'comparebutton',
							hidden :true,
							menu : createAdvancedSubMenu(),
							handler : function() {

							}
						}),
						new Ext.Toolbar.Button({
							id : 'clearsearchbutton',
							text : 'Clear Search',
							iconCls : 'clearbutton',
							hidden : true,
							handler : clearSearch
						}),
						new Ext.Toolbar.Separator(),
						new Ext.Toolbar.Button({
							id : 'savesearchbutton',
							text : 'Save Search',
							iconCls : 'savebutton',
							hidden : true,
							handler : saveSearch
						}),	
						new Ext.form.TextField({
							id : 'saveSearchQueryName',
							hidden : true
						}),
						'->',
						new Ext.Toolbar.Button(
								{
									id : 'sampleExplorerHelpButton',
									iconCls : 'contextHelpBtn',
									qtip: 'Click for Sample Explorer Help',
									disabled : false,
									handler : function()
									{
									    D2H_ShowHelp("1438",helpURL,"wndExternal",CTXT_DISPLAY_FULLHELP );
									}
								}
						)						
						
						]
			});	
	
	//This is the table panel that holds our "Comparison/Jobs" Tabs.
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
			deferredRender : false,
			activeTab : 0
			}
	);
	
	queryPanel = new Ext.Panel({height:800})
	
	var autoLoadObject = null;
	
	if(centerPanelURL != null)
	{
		autoLoadObject = {
	        	url: centerPanelURL,
	           	method:'POST',
	           	callback: centerPanelCallbackFunction,
	           	jsonData: centerPanelJsonData
	        };
	}

	
	//This is our main "Comparison" tab.
	queryPanel = new Ext.Panel(
			{
				id : 'queryPanel',
				title : 'Comparison',
				region : 'north',
				height : 800,
				autoScroll : true,
				split : true,					
		        autoLoad : autoLoadObject,	
				collapsible : true,
				titleCollapse : false,
				animCollapse : false,
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

	
	//This is our Jobs tab.
	analysisJobsPanel = new Ext.Panel(
			{
				id : 'analysisJobsPanel',
				title : 'Jobs',
				region : 'center',
				split : true,
				height : 500,
				layout : 'fit',
				hidden: GLOBAL.EnableGP!='true',
				autoLoad : getJobsData,
				collapsible : true						
			}
	);			
	
	resultsTabPanel.add(queryPanel);

	centerPanel = new Ext.Panel({
		id : 'centerPanel',
		region : 'center',
		width : 500,
		border : true,
		tbar : tb2	
	});

	centerPanel.add(resultsTabPanel);
	
	return centerPanel;
}

function createSearchBoxMain()
{

	var combo = new Ext.app.SearchComboBox({
		id: "search-combobox",
		renderTo: "search-text",
		searchUrl: pageInfo.basePath+'/' + GLOBAL.explorerType + '/loadSearch',
		submitFn: function(param, text) {
			//When we pick an item from the list, load the menus.
			//The param is Category|Item.
			var splitArray = param.split("|");
			
			toggleMainCategorySelection(splitArray[1],splitArray[0])
		},
		value: "",
		width: 470,
        onSelect: function(record) {
			this.collapse();
			if (record != null) {
				this.submitFn(record.data.id, record.data.keyword);
			}
		},
        listeners: {
			"beforequery": {
				fn: function(queryEvent) {
		            var picklist = Ext.getCmp("categories");
		            if (picklist != null) {
			            var rec = picklist.getSelectedRecord();
						if (rec != null) {
							queryEvent.query = rec.id + ":" + queryEvent.query;
						}
					}
					
				},
				scope: this
			}
        }
	});
	combo.focus();	
	
	function searchOnClick() {
		var combo = Ext.getCmp("search-combobox");
		var param = combo.getSelectedParam();
		if (param != null) {
			combo.submitFn(param, param);
		}
	}

	function postSubmit() {
		var searchcombo = document.getElementById("search-combobox");
		searchcombo.className += " searchcombobox-disabled";
		searchcombo.style.width = "442px";						
	}
	
	var picklist = new Ext.app.PickList({
		id: "categories",
		cls: "categories-gray",
		storeUrl: pageInfo.basePath+'/' + GLOBAL.explorerType + '/loadCategories',
		renderTo: "search-categories",
		label: "Category:&nbsp;",
		disabledClass: "picklist-disabled",
		onSelect: function(record) {
	        var combo = Ext.getCmp("search-combobox");
	        combo.focus();
	        if ((record.id != "all") || (record.id == "all" && combo.getRawValue().length > 0)) {
				combo.doQuery(combo.getRawValue(), true);
	        }
		}
	});		
	
	//We need to initialize the individual auto-complete search boxes too.
	intializeIndividualSearchBoxes(false);
}

function createSearchBoxIndividual(inputBoxName, inputBoxCategory, forceGridUpdate)
{

	var newComboBoxName = "search-combobox-" + inputBoxCategory
	
	var combo = new Ext.app.SearchComboBox({
		id: newComboBoxName,
		renderTo: inputBoxName,
		searchUrl: pageInfo.basePath+'/' + GLOBAL.explorerType + '/loadSearch',
		submitFn: function(param, text) {
			//When we pick an item from the list, load the menus. The param is Category|Item.
			var splitArray = param.split("|");
			
			//If we aren't updating the grid we need to manually add the selected item here.
			if(!forceGridUpdate) addCategorySelectionDiv(inputBoxName, splitArray[1] , inputBoxCategory, false);
			
			updateFilterList(splitArray[1],true,splitArray[0], forceGridUpdate);
			
		},
		value: "",
		width: 200,
        onSelect: function(record) {
			this.collapse();
			if (record != null) {
				this.submitFn(record.data.id, record.data.keyword);
			}
		},
        listeners: {
			"beforequery": {
				fn: function(queryEvent) {
					queryEvent.query = inputBoxCategory + ":" + queryEvent.query;
				},
				scope: this
			}
        }
	});
	
}

function addCategorySelectionDiv(inputBoxName, itemText, itemCategory, gridUpdateOnRemove)
{
	
	GLOBAL.selectorDivIdCounter += 1;
	
	var linkText = "<a class = 'categorySelectionDivRemove' href='#' onclick='removeCategorySelectionDiv(\"search-selected-item-" + GLOBAL.selectorDivIdCounter + "\",\"" + itemCategory + "\",\"" + itemText + "\"," + gridUpdateOnRemove + ");'>X</a>"
	jQuery("#search-selected-" + itemCategory).append("<div id='search-selected-item-" + GLOBAL.selectorDivIdCounter + "' class='categorySelectionDiv'>" + itemText + "&nbsp;&nbsp;&nbsp;&nbsp;" + linkText + "</div>");
	
}

function removeCategorySelectionDiv(divToRemove, category, item, gridUpdateOnRemove)
{
	jQuery("#" + divToRemove).remove();
	
	updateFilterList(item,false,category, gridUpdateOnRemove);

}

function createAdvancedSubMenu()
{
	advmenu = new Ext.menu.Menu(
			{
				id : 'advancedMenu',
				minWidth : 250,
				items : [
						{
							text : 'Heatmap',
							// when checked has a boolean value, it is assumed to be a CheckItem
							handler : function() {
								GLOBAL.HeatmapType = 'Compare';
								//We need to do some work before we can validate. Call our sample explorer heatmap code.
								validateHeatMapsSample(showGeneSelection);
								advancedWorkflowContextHelpId = "1085";
							}
						},
				         {
				        	 text : 'Hierarchical Clustering',
				        	 // when checked has a boolean value, it is assumed to be a CheckItem
				        	 handler : function()
				        	 {
				        	 	GLOBAL.HeatmapType = 'Cluster';
				        	 	validateHeatMapsSample(showGeneSelection);
				        	 	advancedWorkflowContextHelpId="1085";
				        	 }
				         }
				         ,
				         {
				        	 text : 'K-Means Clustering',
				        	 // when checked has a boolean value, it is assumed to be a CheckItem
				        	 handler : function()
				        	 {
				        	 	GLOBAL.HeatmapType = 'KMeans';
				        	 	validateHeatMapsSample(showGeneSelection);
				        	 	advancedWorkflowContextHelpId="1085";
				        	 }
				         }
				         ,

				         {
				        	 text : 'Comparative Marker Selection (Heatmap)',
				        	 // when checked has a boolean value, it is assumed to be a CheckItem
				        	 handler : function()
				        	 {
				        	 	GLOBAL.HeatmapType = 'Select';
				        	 	validateHeatMapsSample(showGeneSelection);
				        	 	advancedWorkflowContextHelpId="1085";
				        	 }
				         }
				         ,
				         '-'
				         ,
				         {
				        	 text : 'Principal Component Analysis',
				        	 // when checked has a boolean value, it is assumed to be a CheckItem
				        	 handler : function()
				        	 {
				        	 	GLOBAL.HeatmapType = 'PCA';
				        	 	validateHeatMapsSample(showGeneSelection);
				        	 	advancedWorkflowContextHelpId="1172";
				        	 }
				         }
				         ,
				         /*
				         '-'
				         ,
				         {
				        	 text : 'Haploview',
				        	 handler : function()	{
				        		 
				        		 //Get the Sample ID List from the subsets.
				        		 validateHeatMapsSample(showHaploviewGeneSelection);

				        		 return;
				        	}
				        }
				        ,
				        */
				        {
				        	 text : 'SNPViewer',
				        	 handler : function()	
				        	 {
				        		GLOBAL.HeatmapType = '';
				        		validateHeatMapsSample(showSNPViewerSelection);
				        	 	return;
				        	 }
				        }
				        ,
				        {
				        	 text : 'Integrative Genome Viewer',
				        	 handler : function()	
				        	 {
				        		GLOBAL.HeatmapType = '';
					        	validateHeatMapsSample(showIgvSelection);
				        	 	return;
				        	 }
				        },
				        {
				        	 text : 'Genome-Wide Association Study',
				        	 handler : function()	{
				        		GLOBAL.HeatmapType = '';				        		
					        	validateHeatMapsSample(showGwasSelection);
				        	 	return;			
				        	 }
				        }	
				]
			});

	return advmenu;

}


