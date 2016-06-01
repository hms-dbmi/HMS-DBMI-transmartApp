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
  
String.prototype.trim = function() {
	return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
}

Ext.layout.BorderLayout.Region.prototype.getCollapsedEl = Ext.layout.BorderLayout.Region.prototype.getCollapsedEl.createSequence(function()
		{
	if ((this.position == 'north' || this.position == 'south') && ! this.collapsedEl.titleEl)
	{
		this.collapsedEl.titleEl = this.collapsedEl.createChild(
				{
					style : 'color:#15428b;font:11px/15px tahoma,arial,verdana,sans-serif;padding:2px 5px;', cn : this.panel.title
				}
		);
	}
		}
);

var runner = new Ext.util.TaskRunner();

var wfsWindow = null;


function dataSelectionCheckboxChanged(ctl)
{
	if(getSelected(ctl)[0] != undefined)
	{
		// outputSelected(ctl);
		Ext.getCmp("exportStepDataSelectionNextButton").enable();
	}
}

function setDataAssociationAvailableFlag(el, success, response, options) {
	
	if (!success) {
		var dataAssociationPanel = Ext.getCmp('dataAssociationPanel');
		var resultsTabPanel = Ext.getCmp('resultsTabPanel');
		resultsTabPanel.remove(dataAssociationPanel);
		resultsTabPanel.doLayout();
	} else {
		Ext.Ajax.request(
		{
			url : pageInfo.basePath+"/dataAssociation/loadScripts",
				method : 'GET',
				timeout: '600000',
				params :  Ext.urlEncode({}),
				success : function(result, request)
				{
					var exp = result.responseText.evalJSON();
					if (exp.success && exp.files.length > 0)	{
						/*for (var i = 0; i < exp.files.length; i++) {
							var file = exp.files[i]
							if (file.type == 'script') {
								
							}
						}*/
						loadScripts(exp.files);
					}
				},
				failure : function(result, request)
				{
					alert("Unable to process the export: " + result.responseText);
				}
		});
	}
}

function loadScripts(scripts) {
	var handlerData = {
	//data you wish to pass to your success or failure
	//handlers.
	};
	 
	var filesArr = []
	for (var i = 0; i < scripts.length; i++) {
		var file = scripts[i];
		filesArr.push(file.path);
	}
	YAHOO.util.Get.script(filesArr, {
		onSuccess: function(o) {
			//alert("JavaScripts loaded");
		},
		onFailure: function(o) {
			alert("Failed to load Javascript files");
		},
		data:      handlerData
	});
}

Ext.Panel.prototype.setBody = function(html)
{
	var el = this.getEl();
	var domel = el.dom.lastChild.firstChild;
	domel.innerHTML = html;
}

Ext.Panel.prototype.getBody = function(html)
{
	var el = this.getEl();
	var domel = el.dom.lastChild.firstChild;
	return domel.innerHTML;
}


function exportDataSets()
{
	Ext.get("exportdsform").dom.submit();
 }

function hasMultipleTimeSeries()
{
	return true;
}


function closeBrowser()
{
	window.open('http://www.i2b2.org', '_self', '');
	window.close();
}
function showLoginDialog()
{

	loginwin = new Ext.Window(
			{
				id : 'loginWindow',
				title : 'Login',
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

	var txtboxdomain = new Ext.form.TextField(
			{
				fieldLabel : 'Domain',
				id : 'txtFieldDomain',
				name : 'domain'
			}
	);

	var txtboxusername = new Ext.form.TextField(
			{
				fieldLabel : 'Username',
				name : 'username'
			}
	);

	txtboxpassword = new Ext.form.TextField(
			{
				fieldLabel : 'Password',
				name : 'password',
				inputType : 'password'
			}
	);

	loginform = new Ext.FormPanel(
			{
				id : 'loginForm',
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
			items : [txtboxusername, txtboxpassword],
			buttons : [
			           {
			        	   text : 'Login',
			        	   handler : function()
			        	   {
			        	   loginform.el.mask('Logging in...', 'x-mask-loading');
			        	   login(txtboxdomain.getValue(), txtboxusername.getValue(), txtboxpassword.getValue());
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


	if(GLOBAL.AutoLogin)
	{
		login(GLOBAL.Domain, GLOBAL.Username, GLOBAL.Password);
	}
	else
	{
		loginwin.add(loginform);
		loginwin.show(viewport);

		txtboxpassword.getEl().addListener('keypress', function(e)
				{
			if(enterWasPressed(e))
			{
				loginform.el.mask('Logging in...', 'x-mask-loading');
				login(txtboxdomain.getValue(), txtboxusername.getValue(), txtboxpassword.getValue());
			}
				}
		);
	}
}


function login(domain, username, password)
{
	GLOBAL.Domain = domain;
	GLOBAL.Username = username;
	GLOBAL.Password = password;
	getServices();
}

function loginComplete(pmresponse)
{
	// alert(pmXML);
	// oDomDoc = (new DOMParser()).parseFromString(pmXML, "text/xml");
	// oDomDoc = (new DOMParser()).parseFromString("<sending_application>blah</sending_application>", "text/xml");
	if(GLOBAL.Debug)
	{
		alert(pmresponse.responseText);
	}
	oDomDoc = pmresponse.responseXML;
	// if(GLOBAL.Debug){alert(new XMLSerializer().serializeToString(oDomDoc))};
	// oDomDoc.setProperty("SelectionLanguage", "XPath");
	// oDomDoc.setProperty("SelectionNamespaces", "xmlns:tns='http://ws.pm.i2b2.harvard.edu' \
	// xmlns : ns3 = 'http://www.i2b2.org/xsd/hive/msg/version/' xmlns : ns4 = 'http://www.i2b2.org/xsd/cell/pm/1.1/' \
	// xmlns : ns2 = 'http://www.i2b2.org/xsd/hive/msg/1.1/' ")
	var statusNode = oDomDoc.selectSingleNode('//response_header/result_status/status');
	var statusType = statusNode.getAttribute("type");
	var statusText = statusNode.firstChild.nodeValue;
	if(statusType == "ERROR")
	{
		if(loginform.isVisible())
		{
			loginform.el.unmask();
		}
		// Show a dialog using config options :
		Ext.Msg.show(
				{
					title : 'Login error',
					msg : statusText,
					buttons : Ext.Msg.OK,
					fn : function()
					{
					Ext.Msg.hide();
					}
				,
				icon : Ext.MessageBox.ERROR
				}
		);
	}
	else
	{
		// get the urls to the other important cells
		GLOBAL.ONTUrl = oDomDoc.selectSingleNode("//cell_data[@id='ONT']/url").firstChild.nodeValue;
		GLOBAL.CRCUrl = oDomDoc.selectSingleNode("//cell_data[@id='CRC']/url").firstChild.nodeValue;
		if(GLOBAL.Debug)
		{
			alert(GLOBAL.ONTUrl);
			alert(GLOBAL.CRCUrl);
		}
		if(loginform.isVisible())
		{
			loginform.el.unmask();
			loginwin.hide();
		}


		// figure out which project we want
		var projects = oDomDoc.selectNodes('//project');
		// need to show multiple projects and pick the project id
		if(projects.length > 1)
		{
			showProjectDialog(projects);
		}
		else
		{
			projectDialogComplete(projects[0].getAttribute("id"));
		}
	}
	
	// Login GenePattern server. The login process should be completed by the time a user starts GenePattern tasks.
	genePatternLogin();
}



function getPreviousQueriesComplete(response)
{
	// alert(response.responseText);
	// shorthand
	var Tree = Ext.tree;
	// add a tree sorter in folder mode
	// new Tree.TreeSorter(ontTree, {folderSort : true});

	if(GLOBAL.Debug)
	{
		alert(response.responseText);
	}
	// clear the tree
	for(c = prevTreeRoot.childNodes.length - 1; c >= 0;
	c -- )
	{
		prevTreeRoot.childNodes[c].remove();
	}
	// prevTree.render();

	var querymasters = response.responseXML.selectNodes('//query_master');
	for(var c = 0; c < querymasters.length; c ++ )
	{
		var querymasterid = querymasters[c].selectSingleNode('query_master_id').firstChild.nodeValue;
		var name = querymasters[c].selectSingleNode('name').firstChild.nodeValue;
		var userid = querymasters[c].selectSingleNode('user_id').firstChild.nodeValue;
		var groupid = querymasters[c].selectSingleNode('group_id').firstChild.nodeValue;
		var createdate = querymasters[c].selectSingleNode('create_date').firstChild.nodeValue;
		// set the root node
		var prevNode = new Tree.TreeNode(
				{
					text : name,
					draggable : true,
					id : querymasterid,
					qtip : name,
					userid : userid,
					groupid : groupid,
					createdate : createdate,
					leaf : true
				}
		);
		prevNode.addListener('contextmenu', previousQueriesRightClick);
		prevTreeRoot.appendChild(prevNode);
	}
}

function getCategoriesComplete(ontresponse){
	ontTabPanel.add(ontFilterPanel);
	ontFilterTree.dragZone.addToGroup("analysis");
	getSubCategories('navigateTermsPanel', 'Navigate Terms', ontresponse);
	setActiveTab();
}

function setActiveTab(){
	//var activeTab='ontFilterPanel';
	var activeTab='navigateTermsPanel';
	if (GLOBAL.PathToExpand!==''){
		if ((GLOBAL.PathToExpand.indexOf('Across Trials')>-1)&&(GLOBAL.hideAcrossTrialsPanel!='true')){
			activeTab='crossTrialsPanel';
		}else{
			activeTab='navigateTermsPanel';
		}
	}
	ontTabPanel.setActiveTab(activeTab);
}

/*If includeExcludeFlag is
 * -"include": Across Trials is the only concept included
 * -"exclude": Across Trials concept is the only concept excluded 
 */
function createTree(includeExcludeFlag, ontresponse){
	// shorthand
	var Tree = Ext.tree;
	
	var concepts = ontresponse.responseXML.selectNodes('//concept');
	var treeRoot = new Tree.TreeNode(
			{
				text : 'root',
				draggable : false,
				id : 'root',
				qtip : 'root'
			}
	);
	for(var c = 0; c < concepts.length; c ++ )
	{
		var level = concepts[c].selectSingleNode('level').firstChild.nodeValue;
		var key = concepts[c].selectSingleNode('key').firstChild.nodeValue;
		var name = concepts[c].selectSingleNode('name').firstChild.nodeValue;
		var tooltip = concepts[c].selectSingleNode('tooltip').firstChild.nodeValue;
		var dimcode = concepts[c].selectSingleNode('dimcode').firstChild.nodeValue;
		
		if(includeExcludeFlag==="include" && name!=="Across Trials") continue;
		if(includeExcludeFlag==="exclude" && name==="Across Trials") continue;
		// set the root node
		var autoExpand=false;
		if(GLOBAL.PathToExpand.indexOf(key)>-1) autoExpand=true;
		var ontRoot = new Tree.AsyncTreeNode(
				{
					text : name,
					draggable : false,
					id : key,
					qtip : tooltip,
					expanded : autoExpand
				}
		);
		
		treeRoot.appendChild(ontRoot);
		/*****************************************/
		var fullname=key.substr(key.indexOf("\\",2), key.length);
		var access=GLOBAL.InitialSecurity[fullname];

		if((access!=undefined && access!='Locked') || GLOBAL.IsAdmin) //if im an admin or there is an access level other than locked leave node unlocked
		{
			//leave node unlocked must have some read access
		}
		else
		{
			//default node to locked
			//child.setText(child.text+" <b>Locked</b>");
			ontRoot.attributes.access='locked';
			ontRoot.disable();
			ontRoot.on('beforeload', function(node){alert("Access to this node has been restricted. Please contact your administrator for access."); return false});
		}
	}
	return treeRoot;
}

/*
 * the id_in drives which off these tabs is created
 * 
 */
function getSubCategories(id_in, title_in, ontresponse)
{
	// shorthand
	var Tree = Ext.tree;

	var treeRoot;
	
	var showFn;
	
	if (id_in==='crossTrialsPanel'){
		showFn = function(node, e){
			Ext.tree.TreePanel.superclass.onShow.call(this);
			//Ext.get('advancedbutton').dom.style.display='none';
		}
		treeRoot = createTree('include', ontresponse);
	}else{
		showFn = function(node, e){
			Ext.tree.TreePanel.superclass.onShow.call(this);
			//Ext.get('advancedbutton').dom.style.display='';
		}
		treeRoot = createTree('exclude', ontresponse);
	}
		
	var ontTree = new Tree.TreePanel(
			{
				id : id_in,
				title : title_in,
				animate : false,
				autoScroll : true,
				loader : new Ext.ux.OntologyTreeLoader(
						{
							dataUrl : 'none'
						}
				),
				enableDrag : true,
				ddGroup : 'makeQuery',
				containerScroll : true,
				enableDrop : false,
				region : 'center',
				rootVisible : false,
				expanded : true,
				onShow : showFn
			}
	);

	ontTree.on('startdrag', function(panel, node, event)
			{
		Ext.ux.ManagedIFrame.Manager.showShims()

			}
	);

	ontTree.on('enddrag', function(panel, node, event)
			{
		Ext.ux.ManagedIFrame.Manager.hideShims()

			}
	);


	// add a tree sorter in folder mode
	new Tree.TreeSorter(ontTree,
			{
				folderSort : true,
				sortType: function(node) 
						{
							if(node.attributes.tablename == "MODIFIER_DIMENSION" )
							{
								return "A" + node.text
							}
							else
							{
								return "B" + node.text
							}
			    		}
			}
	);
	
	ontTree.setRootNode(treeRoot);

	ontTabPanel.add(ontTree);

	if(GLOBAL.Debug)
	{
		alert(ontresponse.responseText);
	}

	ontTabPanel.doLayout();
	ontTree.dragZone.addToGroup("analysis");
	
}

function ontLoadNode(node)
{
	// shorthand
	var Tree = Ext.tree;
	var child = new Tree.AsyncTreeNode(
			{
				text : 'fake second node',
				draggable : true,
				id : 'key',
				qtip : 'tooltip'
			}
	);
	child.addListener('beforeload', ontLoadNode, this);
	node.appendChild(child);
}

function setupDragAndDrop()
{
	/* Set up the drag and drop for the query panel */
	// var dts = new Array();
	for(var s = 1; s <= GLOBAL.NumOfSubsets; s ++ )
	{
		for(var i = 1; i <= GLOBAL.NumOfQueryCriteriaGroups;
		i ++ )
		{
			var qcd = Ext.get("queryCriteriaDiv" + s.toString() + '_' + i.toString());
			dts = new Ext.dd.DropTarget(qcd,
					{
				ddGroup : 'makeQuery'
					}
			);

			dts.notifyDrop = function(source, e, data)
			{
				if(source.tree.id == "previousQueriesTree")
				{
					getPreviousQueryFromID(data.node.attributes.id);
					return true;
				}
				else
				{
					var x=e.xy[0];
					var y=e.xy[1];

					var concept = null;
					
					if(data.node.attributes.applied_path != null && data.node.attributes.applied_path != "@")
					{															
						showModifierSetValueDialog(data.node, this.el);
					}
					else
					{
						if(data.node.attributes.oktousevalues != "Y")
						{
							concept = createPanelItemNew(this.el, convertNodeToConcept(data.node));
						}
						else
						{
							concept = createPanelItemNew(Ext.get("hiddenDragDiv"), convertNodeToConcept(data.node));
						}
						
						// new hack to show setvalue box
						selectConcept(concept);
						
						if(data.node.attributes.oktousevalues == "Y")
						{
							STATE.Dragging = true;
							STATE.Target = this.el;
							showSetValueDialog();
						}
					}
					
					/*new code to show next row*/
					var panelnumber = Number(this.id.substr(18));
					showCriteriaGroup(panelnumber+1);
					return true;
				}
			}
		}

	}
	
	/* Set up Drag and Drop for the relation query box */
	var qrd = Ext.get("queryCriteriaDiv3_1");
	dts = new Ext.dd.DropTarget(qrd,
			{
		ddGroup : 'makeQuery'
			}
	);
	
	dts.notifyDrop = function(source, e, data)
	{
				if(data.node.attributes.oktousevalues != "Y")
				{
					concept = createPanelItemNew(this.el, convertNodeToConcept(data.node));
				}
				else
				{
					concept = createPanelItemNew(Ext.get("hiddenDragDiv"), convertNodeToConcept(data.node));
				}
				
	}

	/* Set up Drag and Drop for the analysis Panel */
	var qcd = Ext.get(analysisPanel.body);

	dts = new Ext.dd.DropTarget(qcd,
			{
		ddGroup : 'analysis'
			}
	);

	dts.notifyDrop = function(source, e, data)
	{
		// createAnalysisItem(data.node.text, data.node.id);
		// alert("build analsyis graph now!");
		buildAnalysis(data.node, "stats");
		return true;
	}

	/* set up drag and drop for grid */
	var mcd = Ext.get(analysisGridPanel.body);
	dtg = new Ext.dd.DropTarget(mcd,
			{
		ddGroup : 'analysis'
			}
	);

	dtg.notifyDrop = function(source, e, data)
	{
		// createAnalysisItem(data.node.text, data.node.id);
		// alert("build analsyis graph now!");
		buildAnalysis(data.node, "grid");
		return true;
	}
	
}

function getPreviousQueryFromIDComplete(subset, result)
{
	var doc = result.responseXML;
	// alert(result.responseText);
	//resetQuery();  //if i do this now it wipes out the other subset i just loaded need to make it subset specific
	var panels = doc.selectNodes("//panel");
	for(var p = 0; p < panels.length; p ++ )
	{
		var panelnumber = panels[p].selectSingleNode("panel_number").firstChild.nodeValue;
		if (panelnumber=="21") continue;
		showCriteriaGroup(panelnumber); //in case its hidden;
		var panel=document.getElementById("queryCriteriaDiv"+subset+"_"+panelnumber);
		var invert = panels[p].selectSingleNode("invert").firstChild.nodeValue;
		if(invert=="1"){excludeGroup(null, subset, panelnumber);} //set the invert for the panel
		var occurences = panels[p].selectSingleNode("total_item_occurrences").firstChild.nodeValue;
		// TODO : set the invert
		// TODO : set the occurences
		// make the items

		var items = panels[p].selectNodes("item")
		for(var it = 0; it < items.length; it ++ )
		{
			var item = items[it];
			var level = item.selectSingleNode("hlevel").firstChild.nodeValue;
			var name = getValue(item.selectSingleNode("item_name"),"");
			var key = item.selectSingleNode("item_key").firstChild.nodeValue;
			var applied_path = item.selectSingleNode("applied_path").firstChild.nodeValue;
			var tooltip = getValue(item.selectSingleNode("tooltip"),"");
			var itemclass = item.selectSingleNode("class").firstChild.nodeValue;
			//createPanelItem(panelnumber, level, name, key, tooltip, '', '', '');


			/*need all this information for reconstruction but not all is available*/
			var valuetype=getValue(item.selectSingleNode("constrain_by_value/value_type"),"");
			var mode;

			if(valuetype=="FLAG"){mode="highlow";}
			else if(valuetype=="NUMBER"){mode="numeric";}
			else {mode=="novalue";}

			var valuenode=item.selectSingleNode("contrain_by_value");
			var oktousevalues;
			if(valuenode!=null && typeof(valuenode)!=undefined){oktousevalues="Y";}

			var operator=getValue(item.selectSingleNode("constrain_by_value/value_operator"),"");
			var numvalue=getValue(item.selectSingleNode("constrain_by_value/value_constraint"),"");
			var lowvalue;
			var highvalue;
			if(operator=="BETWEEN")
			{
				lowvalue=numvalue.substring(0, numvalue.indexOf("and"));
				highvalue=numvalue.substring(numvalue.indexOf("and")+3);
			}
			else
			{
				lowvalue=numvalue;
			}
			var highlowselect="";
			if(mode=="highlow"){highlowselect=numvalue;}
			var units=getValue(item.selectSingleNode("constrain_by_value/value_unit_of_measure"),"");
			var dimcode="";
			var comment="";
			var normalunits=units;

			var tablename="";
			var value=new Value(mode, operator, highlowselect, lowvalue, highvalue, units);
			var myConcept=new Concept(name, key, level, tooltip, tablename, dimcode, comment, normalunits, oktousevalues, value, applied_path);
			createPanelItemNew(panel, myConcept);
		}
	}
	queryPanel.el.unmask();
}

function createExportItem(name, setid)
{
	if(GLOBAL.exportFirst == undefined) // clear out the body
	{
		exportPanel.body.update("");
		GLOBAL.exportFirst = false;
	}
	var panel = exportPanel.body.dom;
	var li = document.createElement('div');

	li.setAttribute('setid', setid);
	li.setAttribute('setname', name);
	li.className = "conceptUnselected";
	li.style.font = "10pt arial";
	var text = document.createTextNode(name);
	// tooltip
	li.appendChild(text);
	panel.appendChild(li);
	Ext.get(li).addListener('click', conceptClick);
	Ext.get(li).addListener('contextmenu', conceptRightClick);
}



function ontologyRightClick(eventNode, event)
{
	if ( ! this.contextMenuOntology)
	{
		this.contextMenuOntology = new Ext.menu.Menu(
				{
					id : 'contextMenuOntology',
					items : [
					         {
					        	 text : 'Show Definition', handler : function()
					        	 {
					        	 showConceptInfoDialog(eventNode.attributes.id, eventNode.attributes.text, eventNode.attributes.comment);
					        	 }
					         },
					         {
					        	 text : 'Show Node', 
					        	 handler : function()
					        	 {
					        	 	showNode(eventNode.attributes.id);
					        	 }
					         }
					         ]
				}
		);
	}
	var xy = event.getXY();
	this.contextMenuOntology.showAt(xy);
	return false;
}

function previousQueriesRightClick(eventNode, event)
{
	if ( ! this.contextMenuPreviousQueries)
	{
		this.contextMenuPreviousQueries = new Ext.menu.Menu(
				{
					id : 'contextMenuPreviousQueries',
					items : [
					         {
					        	 text : 'Rename', handler : function()
					        	 {
					        	 alert('rename!');
					        	 }
					         }
					         ,
					         {
					        	 text : 'Delete', handler : function()
					        	 {
					        	 alert('delete!');
					        	 }
					         }
					         ,
					         {
					        	 text : 'Query Summary', handler : function()
					        	 {
					        	 showQuerySummaryWindow(eventNode);
					        	 }
					         }
					         ]
				}
		);
	}
	var xy = event.getXY();
	this.contextMenuPreviousQueries.showAt(xy);
	return false;
}

function showNode(key){
	GLOBAL.PathToExpand=key;
	setActiveTab();
	var rootNode = ontTabPanel.getActiveTab().getRootNode();
	//rootNode.collapseChildNodes(true);
	drillDown(rootNode);
}

function drillDown(rootNode){
	for (var i=0; i<rootNode.childNodes.length; i++){
		if(GLOBAL.PathToExpand.indexOf(rootNode.childNodes[i].id)>-1){
			rootNode.childNodes[i].expand();
			rootNode.childNodes[i].ensureVisible();
			drillDown(rootNode.childNodes[i]);
		}
	}
}

function showConceptInfoDialog(conceptKey, conceptid, conceptcomment)
{

	if( ! this.conceptinfowin)
	{
		var link = '<a href="javascript:;"  onclick="return popitup(\'http://www.google.com/search?q='+conceptid+'\')">Search for more information...</a>'
		conceptinfowin = new Ext.Window(
				{
					id : 'showConceptInfoWindow',
					title : 'Show Concept Definition-' + conceptid,
					layout : 'fit',
					width : 600,
					height : 500,
					closable : false,
					plain : true,
					modal : true,
					border : false,
					autoScroll: true,
					buttons : [
					           /* {
            text : 'Search For More Information',
            handler : function()
            {
               popitup('http://www.google.com/search?q=' + conceptid);
            }
         }
         ,*/
					           {
					        	   text : 'Close',
					        	   handler : function()
					        	   {
					        	   conceptinfowin.hide();
					        	   }
					           }
					           ],
					           resizable : false
				}
		);
	}
	//var conceptKeySplits = conceptKey.split('\\');
	//var conceptType = (conceptKeySplits[1]=='')?conceptKeySplits[2]:conceptKeySplits[1];

	conceptinfowin.show(viewport);
	conceptinfowin.header.update("Show Concept Definition-" + conceptid);
	Ext.get(conceptinfowin.body.id).update(conceptcomment);
	//var begin=conceptcomment.indexOf("trial:");
	//if(begin==0)
	//{
		conceptinfowin.load({
			//url: pageInfo.basePath+"/trial/trialDetailByTrialNumber",
			url: pageInfo.basePath+"/ontology/showConceptDefinition",
			//params: {id: conceptcomment.substring(6,conceptcomment.length), conceptType: conceptType}, // or a URL encoded string
			params: {conceptKey:conceptKey}, // or a URL encoded string		
			//callback: yourFunction,
			//scope: yourObject, // optional scope for the callback
			discardUrl: true,
			nocache: true,
			text: "Loading...",
			timeout: 30000,
			scripts: false
		});
	//}

}

function showQuerySummaryWindow(source)
{
	// var query = getCRCQueryRequest();
	if( ! this.querysummarywin)
	{

		querysummarywin = new Ext.Window(
				{
					id : 'showQuerySummaryWindow',
					title : 'Query Summary',
					layout : 'fit',
					width : 500,
					height : 500,
					closable : false,
					plain : true,
					modal : true,
					border : false,
					buttons : [
					           {
					        	   text : 'Done',
					        	   handler : function()
					        	   {
					        	   querysummarywin.hide();
					        	   }
					           }
					           ],
					           resizable : false
				}
		);

		querySummaryPanel = new Ext.Panel(
				{
					id : 'querySummaryPanel',
					region : 'center'
				}
		);
		querysummarywin.add(querySummaryPanel);
	}
	querysummarywin.show(viewport);
	var fakehtml = "<div style='padding:10px;font:12pt arial;width:100%;height:100%;'>\
		< b > Criteria 1 < / b > < br > \
		Trials\\CT0145T03 < br > \
		< b > AND < br > \
		Criteria 2 < / b > < br > \
		Sex\\Female < br > \
		< b > OR < / b > < br > \
		TRIALS\\CT0145T03\\RBM\\Adjusted Values\\IL - 13 - & gt;\
		.75 < br > "



		// querySummaryPanel.setBody("<div style='height:500px;width500px;overflow:auto;'>" + Ext.util.Format.htmlEncode(query) + "</div>");
		// querySummaryPanel.setBody(fakehtml);
		var q1 = getQuerySummary(1);
		var q2 = getQuerySummary(2);
		querySummaryPanel.body.update('<table border="1" height="100%" width="100%"><tr><td width="50%" valign="top" style="padding:10px;"><h2>Subset 1 Criteria</h2>' + q1 + '</td><td valign="top" style="padding:10px;"><h2>Subset 2 Criteria</h2>' + q2 + '</td></tr></table>');
}


function showConceptSearchPopUp(conceptid)
{
	popitup('http://www.google.com/search?q=' + conceptid)
}
function popitup(url)
{
	newwindow = window.open(url, 'name', 'height=500,width=500,toolbar=yes,scrollbars=yes, resizable=yes,');
	if (window.focus)
	{
		newwindow.focus()
	}
	return false;
}






function showExportStepSplitTimeSeries()
{

	if( ! this.exportStepSplitTimeSeries)
	{
		exportStepSplitTimeSeries = new Ext.Window(
				{
					id : 'exportStepSplitTimeSeriesWindow',
					title : 'Export-Split Time Series',
					layout : 'fit',
					width : 400,
					height : 200,
					closable : false,
					plain : true,
					modal : true,
					border : false,
					buttons : [
					           {
					        	   id : 'exportStepSplitTimeSeriesNextButton',
					        	   text : 'Next>',
					        	   disabled : true,
					        	   handler : function()
					        	   {
					        	   exportStepSplitTimeSeries.hide();
					        	   showExportStepDataSelection();
					        	   }
					           }
					           ,
					           {
					        	   text : 'Cancel',
					        	   handler : function()
					        	   {
					        	   exportStepSplitTimeSeries.hide();
					        	   }
					           }
					           ],
					           resizable : false ,
					           autoLoad :
					           {
					url : pageInfo.basePath+'/panels/exportStepSplitTimeSeries.html',
					scripts : true,
					nocache : true,
					discardUrl : true,
					method : 'POST'
					           }
				}
		);
	}

	exportStepSplitTimeSeries.show(viewport);
}

function showExportStepDataSelection()
{
	if( ! this.exportStepDataSelection)
	{
		exportStepDataSelection = new Ext.Window(
				{
					id : 'exportStepDataSelectionWindow',
					title : 'Export-Data Selection',
					layout : 'fit',
					width : 400,
					height : 400,
					closable : false,
					plain : true,
					modal : true,
					border : false,
					buttons : [
					           {
					        	   id : 'exportStepDataSelectionAdvancedButton',
					        	   text : 'Advanced',
					        	   handler : function()
					        	   {
					        	   showExportDataSelectionAdvanced();
					        	   }
					           }
					           ,
					           {
					        	   id : 'exportStepDataSelectionNextButton',
					        	   text : 'Get Data',
					        	   disabled : true,
					        	   handler : function()
					        	   {
					        	   getExportData();
					        	   }
					           }
					           ,
					           {
					        	   text : 'Cancel',
					        	   handler : function()
					        	   {
					        	   exportStepDataSelection.hide();
					        	   }
					           }
					           ],
					           resizable : false,
					           autoLoad :
					           {
					url : pageInfo.basePath+'/panels/exportStepDataSelection.html',
					scripts : true,
					nocache : true,
					discardUrl : true,
					method : 'POST'
					           }
				}
		);
	}
	exportStepDataSelection.show(viewport);

}
function getExportData()
{

	exportStepDataSelection.getEl().mask("Getting Data...");
	setTimeout('exportDataFinished()', 2000)

}
function showExportStepProgress()
{
	if( ! this.exportStepProgress)
	{
		exportStepProgress = new Ext.Window(
				{
					id : 'exportStepProgress',
					title : 'Export-Download File',
					layout : 'fit',
					html : '<br><div style="font:12pt arial;width:100%;height:100%;text-align:center;vertical-align:middle"><a href="export/export.xls">Download File</a></div>',
					width : 400,
					height : 200,
					closable : false,
					plain : true,
					modal : true,
					border : false,
					buttons : [
					           {
					        	   text : 'Done',
					        	   handler : function()
					        	   {
					        	   exportStepProgress.hide();
					        	   }
					           }
					           ],
					           resizable : false
				}
		);
	}
	exportStepProgress.show(viewport);

}
function exportDataFinished()
{
	exportStepDataSelection.getEl().unmask();
	exportStepDataSelection.hide();
	showExportStepProgress();
}

function runAllQueries(callback, panel)
{
	// analysisPanel.body.update("<table border='1' width='100%' height='100%'><tr><td width='50%'><div id='analysisPanelSubset1'></div></td><td><div id='analysisPanelSubset2'></div></td></tr>");
	var subset = 1;
	if(isSubsetEmpty(1) && isSubsetEmpty(2))
	{
		if (null != panel) { 
			panel.body.unmask()
		}
		Ext.Msg.alert('Subsets are empty', 'All subsets are empty. Please select subsets.');
		return;
	}

	// setup the number of subsets that need running
	var subsetstorun = 0;
	for (i = 1; i <= GLOBAL.NumOfSubsets; i = i + 1)
	{
		if( ! isSubsetEmpty(i) && GLOBAL.CurrentSubsetIDs[i] == null)
		{
			subsetstorun ++ ;
		}
	}
	STATE.QueryRequestCounter = subsetstorun;
	/* set the number of requests before callback is fired for runquery complete */

	// iterate through all subsets calling the ones that need to be run
	for (i = 1; i <= GLOBAL.NumOfSubsets; i = i + 1)
	{
		if( ! isSubsetEmpty(i) && GLOBAL.CurrentSubsetIDs[i] == null)
		{
			runQuery(i, callback);
		}

	}
	
	if( ! isSubsetEmpty(3)) {
		GLOBAL.relation_concept = Ext.get(queryCriteriaDiv3_1).dom.childNodes[0].getAttribute("conceptdimcode")
	}
	
}

function runQuery(subset, callback)
{
	if(Ext.get('analysisPanelSubset1') == null)
	{
		// analysisPanel.body.update("<table border='1' width='100%' height='100%'><tr><td width='50%'><div id='analysisPanelSubset1'></div></td><td><div id='analysisPanelSubset2'></div></td></tr>");
	}
	/* if(isSubsetEmpty(subset))
   {
   callback();
   return;
   } */
	var query = getCRCQueryRequest(subset);
	// first subset
	queryPanel.el.mask('Getting subset ' + subset + '...', 'x-mask-loading');
	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/proxy?url=" + GLOBAL.CRCUrl + "request",
				method : 'POST',
				xmlData : query,
				// callback : callback,
				success : function(result, request)
				{
				runQueryComplete(result, subset, callback);
				}
			,
			failure : function(result, request)
			{
				runQueryComplete(result, subset, callback);
			}
			,
			timeout : '600000'
			}
	);

	if(GLOBAL.Debug)
	{
		resultsPanel.setBody("<div style='height:400px;width500px;overflow:auto;'>" + Ext.util.Format.htmlEncode(query) + "</div>");
	}
}

function runQueryComplete(result, subset, callback)
{
	queryPanel.el.unmask();
	if(GLOBAL.Debug)
	{
		resultsPanel.setBody("<div style='height:400px;width500px;overflow:auto;'>" + Ext.util.Format.htmlEncode(result.responseText) + "</div>");
	}
	var numOfPatientsFound = result.responseXML.selectSingleNode("//set_size").firstChild.nodeValue;
	var patientsetid = result.responseXML.selectSingleNode("//result_instance_id").firstChild.nodeValue;
	GLOBAL.CurrentSubsetIDs[subset] = patientsetid;
	
	var patlabel = "patients";
	if(GLOBAL.Config == "jj")
		patlabel = "subjects";

	if(GLOBAL.Debug)
	{
		alert(getCRCpdoRequest(patientsetid, 1, numOfPatientsFound));
	}

	if(STATE.QueryRequestCounter > 0) // I'm in a chain of requests so decrement
	{
		STATE.QueryRequestCounter = -- STATE.QueryRequestCounter;
	}
	if(STATE.QueryRequestCounter == 0)
	{
		callback();
	}
	
}

function getNodeForAnalysis(node)
{
	// if im a value leaf return me
	if(node.attributes.oktousevalues == "Y" && node.attributes.leaf == true)
	{
		return node;
	}
	// if im a concept leaf then recurse with my parent node
	else if(node.attributes.oktousevalues != "Y" && node.attributes.leaf == true)
	{
		return getNodeForAnalysis(node.parentNode);
	}
	else
	{
		return node
	}
	;
	// must be a concept folder so return me
}


function buildAnalysis(nodein, dropType)
{
	if(dropType == 'stats')
	{
		analysisPanel.body.mask("Running analysis...", 'x-mask-loading');
	}
	
	if(dropType == 'grid')
	{
		analysisGridPanel.body.mask("Loading...", 'x-mask-loading');
	}	
	
	var node = nodein // getNodeForAnalysis(nodein);

	if(isSubsetEmpty(1) && isSubsetEmpty(2))
	{
		//TODO: This shouldn't be like this..
	   	analysisPanel.body.unmask();
    	analysisGridPanel.body.unmask();
    	
		alert('Empty subsets found, need a valid subset to analyze!');
		return;
	}
	if((GLOBAL.CurrentSubsetIDs[1] == null && ! isSubsetEmpty(1)) || (GLOBAL.CurrentSubsetIDs[2] == null && ! isSubsetEmpty(2)))
	{
		
		runAllQueries(function()
				{
			buildAnalysis(node);
				}
		);
		return;
	}


	if(dropType == 'grid')
	{
		getAnalysisGridData(node.attributes.id);
	}
	else if(dropType == 'stats')
	{	
		buildAnalysisSummaryStats(node);
	}


}

function buildAnalysisSummaryStats(node)
{
	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/chart/analysis",
				method : 'POST',
				timeout: '600000',
				params :  Ext.urlEncode(
						{
							charttype : "analysis",
							concept_key : node.attributes.id,
							result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],
							result_instance_id2 : GLOBAL.CurrentSubsetIDs[2]
						}
				), // or a URL encoded string
				success : function(result, request)
				{
				buildAnalysisComplete(result);
				}
			,
			failure : function(result, request)
			{
				buildAnalysisComplete(result);
			}
			}
	);
}

function buildAnalysisComplete(result)
{
	var txt = result.responseText;
	updateAnalysisPanel(txt, true);
}

function updateAnalysisPanel(html, insert)
{
	if(insert)
	{
		var body = analysisPanel.body;
		body.insertHtml('afterBegin', html, false);
		body.scrollTo('top', 0, false);
	}
	else
	{
		analysisPanel.body.update(html, false, null);
	}
	
	analysisPanel.body.unmask();
}

function searchByNameComplete(response)
{
	// shorthand
	var length;
	var Tree = Ext.tree;
	searchByNameTree.el.unmask();
	var allkeys="";
	var concepts = response.responseXML.selectNodes('//concept');
	if(concepts != undefined)
	{
		if(concepts.length < GLOBAL.MaxSearchResults)
		{
			length = concepts.length;
		}
		else
		{
			length = GLOBAL.MaxSearchResults;
		}
		for(var c = 0; c < length; c ++ )
		{
			searchByNameTreeRoot.appendChild(getTreeNodeFromXMLNode(concepts[c]));
			var key=concepts[c].selectSingleNode('key').firstChild.nodeValue;
			if(allkeys!="")
			{
				allkeys=allkeys+",";
			}
			allkeys=allkeys+key;
		}
	}
	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/ontology/sectest",
				method : 'POST',
				success : function(result, request)
				{
				//alert(result);
				}
			,
			failure : function(result, request)
			{
				//alert(result);
			}
			,
			timeout : '300000',
			params : Ext.urlEncode(
					{
						keys: allkeys
					}
			) // or a URL encoded string
			}
	);
}


function enterWasPressed(e)
{
	var pK;
	if(e.which)
	{
		pK = e.which;
	}
	if(pK == undefined && window.event)
	{
		pK = window.event.keyCode;
	}
	if(pK == undefined && e.getCharCode)
	{
		pK = e.getCharCode();
	}
	if ( pK == 13)
	{
		return true;
	}
	else return false;
}

function contextMenuPressed(e)
{
	var x = e;
	e.stopEvent();
	return false;
}

function getSelected(opt)
{
	var selected = new Array();
	var index = 0;
	for (var intLoop = 0; intLoop < opt.length;
	intLoop ++ )
	{
		if ((opt[intLoop].selected) ||
				(opt[intLoop].checked))
		{
			index = selected.length;
			selected[index] = new Object;
			selected[index].value = opt[intLoop].value;
			selected[index].index = intLoop;
		}
	}
	return selected;
}

function outputSelected(opt)
{
	var sel = getSelected(opt);
	var strSel = "";
	for (var intLoop = 0; intLoop < sel.length;
	intLoop ++ )
	{
		strSel += sel[intLoop].value + "\n";
	}
	alert("Selected Items:\n" + strSel);
}



function validateheatmapComplete(result)
{
	var mobj=result.responseText.evalJSON();
	GLOBAL.DefaultCohortInfo=mobj;

	//genePatternReplacement();
	showCompareStepPathwaySelection();

}



function jsonToDataTable(jsontext)
{

	var table = eval("(" + jsontext + ")").table;
	var data = new google.visualization.DataTable();

	// convert to Google.DataTable
	// column
	for (var col = 0; col < table.cols.length;
	col ++ )
	{
		data.addColumn('string', table.cols[col].label);
	}
	// row
	for (var row = 0; row < table.rows.length;
	row ++ )
	{
		data.addRow();
		for (var col = 0; col < table.cols.length;
		col ++ )
		{
			data.setCell(row, col, table.rows[row][col].v);
		}
	}

	// var vis_table = new google.visualization.Table(document.getElementById('table_div'));
	// vis_table.draw(data, {showRowNumber : false});
	return data;
}

function clearExportPanel()
{
	// clear the div
	exportPanel.body.update("");
}

/**
 * @return {String} return the value of the radio button that is checked
 * return an empty string if none are checked, or
 * there are no radio buttons
 * @param {} radioObj
 */
function getCheckedValue(radioObj)
{
	if( ! radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i ++ )
	{
		if(radioObj[i].checked)
		{
			return radioObj[i].value;
		}
	}
	return "";
}

// set the radio button with the given value as being checked
//do nothing if there are no radio buttons
//if the given value does not exist, all the radio buttons
//are reset to unchecked
function setCheckedValue(radioObj, newValue)
{
	if( ! radioObj)
		return;
	var radioLength = radioObj.length;
	if(radioLength == undefined)
	{
		radioObj.checked = (radioObj.value == newValue.toString());
		return;
	}
	for(var i = 0; i < radioLength; i ++ )
	{
		radioObj[i].checked = false;
		if(radioObj[i].value == newValue.toString())
		{
			radioObj[i].checked = true;
		}
	}
}
function searchByName()
{
	var matchstrategy = document.getElementById('searchByNameSelect').value;
	var matchterm = document.getElementById('searchByNameInput').value;
	var a=matchterm.trim();
	if(a.length<3)
	{
		alert("Please enter a longer search term");
		return;
	}
	var matchontology = document.getElementById('searchByNameSelectOntology').value;
	var query = getONTgetNameInfoRequest(matchstrategy, matchterm, matchontology);
	searchByNameTree.el.mask('Searching...', 'x-mask-loading');
	for(c = searchByNameTreeRoot.childNodes.length - 1;
	c >= 0;
	c -- )
	{
		searchByNameTreeRoot.childNodes[c].remove();
	}
	searchByNameTree.render();
	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/proxy?url=" + GLOBAL.ONTUrl + "getNameInfo",
				method : 'POST',
				xmlData : query,
				success : function(result, request)
				{
				searchByNameComplete(result);
				}
			,
			failure : function(result, request)
			{
				searchByNameComplete(result);
			}
			,
			timeout : '300000'
			}
	);
}

function getSummaryStatistics()
{
	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/chart/basicStatistics",
				method : 'POST',
				success : function(result, request)
				{
				getSummaryStatisticsComplete(result);
				}
			,
			failure : function(result, request)
			{
				getSummaryStatisticsComplete(result);
			}
			,
			timeout : '300000',
			params : Ext.urlEncode(
					{
						charttype : "basicstatistics",
						concept_key : "",
						result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],

						result_instance_id2 : GLOBAL.CurrentSubsetIDs[2], 
						relation_concept : GLOBAL.relation_concept

					}
			) // or a URL encoded string
			}
	);

	resultsTabPanel.body.mask("Running analysis...", 'x-mask-loading');
}

function buildColumnModel(fields)
{
	var size = fields.size();
	var con = new Array();
	for(var i = 0; i < size; i ++ )
	{
		var c = new Object();
		var f = fields[i];
		c.id = f.name;
		c.dataIndex = f.name;
		c.header = f.header;
		c.tooltip = f.name;
		c.width = f.width;
		c.sortable = f.sortable;
		c.menuDisabled = false;
		con.push(c);
	}

	return new Ext.grid.ColumnModel(con);
}

function getSummaryStatisticsComplete(result, request)
{
	resultsTabPanel.setActiveTab('analysisPanel');
	updateAnalysisPanel(result.responseText, false);
	getExportButtonSecurity();
	resultsTabPanel.body.unmask();
}


function getExportButtonSecurity()
{
	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/export/exportSecurityCheck",
				method : 'POST',
				success : function(result, request)
				{
				getExportButtonSecurityComplete(result);
				}
			,
			failure : function(result, request)
			{
				getExportButtonSecurityComplete(result);
			}
			,
			timeout : '300000',
			params : Ext.urlEncode(
					{
						result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],
						result_instance_id2 : GLOBAL.CurrentSubsetIDs[2]
					}
			) // or a URL encoded string
			}
	);
}

function getExportButtonSecurityComplete(result)
{
	var mobj=result.responseText.evalJSON();
	var canExport=mobj.canExport;
}

function activateTab(tab)
{
	
}

function getSummaryGridData()
{
	gridstore = new Ext.data.JsonStore(
			{
				url : pageInfo.basePath+'/chart/basicGrid',
				root : 'rows',
				fields : ['name', 'url']
			}
	);
	gridstore.on('load', storeLoaded);
	var myparams = Ext.urlEncode(
			{
				charttype : "basicgrid",
				concept_key : "",
				result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],
				result_instance_id2 : GLOBAL.CurrentSubsetIDs[2]
			}
	);
	// or a URL encoded string */

	gridstore.load(
			{
				params : myparams
			}
	);
}

function storeLoaded()
{
	var blah = gridstore;
	var cm = buildColumnModel(gridstore.reader.meta.fields);
	if(window.grid)
	{
		analysisGridPanel.remove(grid);
	}
	grid = new Ext.grid.GridPanel(
			{
				id : 'grid',
				store : gridstore,
				cm : cm,
				viewConfig :
				{
				// forceFit : true
				}
			,
			sm : new Ext.grid.RowSelectionModel(
					{
						singleSelect : true
					}
			),
			layout : 'fit',
			width : 800
			// frame : true,
			// title : 'Framed with Checkbox Selection and Horizontal Scrolling'
			}
	);
	analysisGridPanel.add(grid);
	analysisGridPanel.doLayout();
	analysisGridPanel.body.unmask();
}

function getAnalysisGridData(concept_key)
{
	gridstore = new Ext.data.JsonStore(
			{
				url : pageInfo.basePath+'/chart/analysisGrid',
				root : 'rows',
				fields : ['name', 'url']
			}
	);
	gridstore.on('load', storeLoaded);
	var myparams = Ext.urlEncode(
			{
				charttype : "analysisgrid",
				concept_key : concept_key,
				result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],
				result_instance_id2 : GLOBAL.CurrentSubsetIDs[2]
			}
	);
	// or a URL encoded string */

	gridstore.load(
			{
				params : myparams
			}
	);
}

function getAnalysisPanelContent()
{
	var a = analysisPanel.body;
	return analysisPanel.body.dom.innerHTML;
}

function printPreview(content)
{
	var stylesheet = "<html><head><link rel='stylesheet' type='text/css' href='${resource(dir:'css', file:'chartservlet.css')}'></head><body>";
	var generator = window.open('', 'name', 'height=400,width=500, resizable=yes, scrollbars=yes');
	var printbutton = "<input type='button' value=' Print this page 'onclick='window.print();return false;' />";
	
	generator.document.write(stylesheet + printbutton + content);
	generator.document.close();
	// generator.print();
}

function exportGrid()
{
	viewport.getEl().mask("Getting Data....");
	Ext.get("exportgridform").dom.submit();
	setTimeout('viewport.getEl().unmask();', 10000);
}

function watchForSymbol(options)
{
	var stopAt;

	if ( ! options || ! options.symbol || ! Object.isFunction(options.onSuccess))
	{
		throw "Missing required options";
	}
	options.onTimeout = options.onTimeout || Prototype.K;
	options.timeout = options.timeout || 10;
	stopAt = (new Date()).getTime() + (options.timeout * 1000);
	new PeriodicalExecuter(function(pe)
			{
		if (typeof window[options.symbol] != "undefined")
		{
			pe.stop();
			options.onSuccess(options.symbol);
		}
		else if ((new Date()).getTime() > stopAt)
		{
			pe.stop();
			options.onTimeout(options.symbol);
		}
			}
	, 0.25);
}

function searchByTagBefore()
{
	//ontFilterPanel.el.mask("Searching...");
	var tagterm=document.getElementById("tagterm");
	var tagtype=document.getElementById("tagtype");
	var searchterm = document.getElementById('ontsearchterm').value;
	var a=searchterm.trim();
	if(a.length>0 && a.length<3)
	{
		alert("Please enter a longer search term.");
		return false;
	}
	if(a.length==0 && tagtype.selectedIndex==0)
	{
		alert("Please select a search term.");
		return false;
	}
	if(a.length==0 && tagtype.selectedIndex!=0)
	{
		if(tagterm.selectedIndex==-1)
		{
			alert("Please select a search term.");
			return false;
		}
	}
	for(c = ontFilterTreeRoot.childNodes.length - 1;
	c >= 0;
	c -- )
	{
		ontFilterTreeRoot.childNodes[c].remove();
	}
	ontFilterTree.render();
	viewport.el.mask("Searching...")
	return true;
}
function searchByTagComplete(response)
{
	// shorthand
	var Tree = Ext.tree;
	//ontFilterPanel.el.unmask();
	viewport.el.unmask();
	var robj=response;
	var rtext=robj.resulttext;
	var concepts = robj.concepts;
	// concept = concepts[4];
	// test = concept.selectSingleNode('name').firstChild.nodeValue;
	// alert(response.responseText);
	var length;
	var leaf = false;
	var draggable = false;
	if(concepts != undefined)
	{
		if(concepts.length < GLOBAL.MaxSearchResults)
		{
			length = concepts.length;
		}
		else
		{
			length = GLOBAL.MaxSearchResults;
		}
		for(var c = 0; c < length; c ++ )
		{
			var newnode=getTreeNodeFromJSON(concepts[c])
			ontFilterTreeRoot.appendChild(newnode);
			setTreeNodeSecurity(newnode, concepts[c].access);
		}
		var t=document.getElementById("searchresultstext");
		t.innerHTML=rtext;
	}
}

function showHaploviewGeneSelection()
{
	//genePatternReplacement();
	//if(!this.compareStepPathwaySelection)
	//{
	var win = new Ext.Window({
		id: 'showHaploviewGeneSelection',
		title: 'Haploview-Gene Selection',
		layout:'fit',
		width:250,
		height:250,
		closable: false,
		plain: true,
		modal: true,
		border:false,
		//autoScroll: true,
		buttons: [
		          {
		        	  id: 'haploviewGeneSelectionOKButton',
		        	  text: 'OK',
		        	  handler: function(){
		        	  if(Ext.get('haploviewgenes')==null)
		        	  {
		        		  win.close();
		        		  return;
		        	  }
		        	  var ob=Ext.get('haploviewgenes').dom;
		        	  var selected = new Array();
		        	  for (var i = 0; i < ob.options.length; i++)
		        		  if (ob.options[i].selected)
		        			  selected.push(ob.options[i].value);
		        	  //alert(selected.join(','));
		        	  GLOBAL.CurrentGenes=selected.join(',');
		        	  getHaploview();
		        	  win.close();}
		          }
		          ,{
		        	  text: 'Cancel',
		        	  handler: function(){
		        	  win.close();}
		          }],
		          resizable: false,
		          autoLoad:
		          {
		url: pageInfo.basePath+'/analysis/showHaploviewGeneSelector',
		scripts: true,
		nocache:true,
		discardUrl:true,
		method:'POST',
		params: {result_instance_id1: GLOBAL.CurrentSubsetIDs[1],
		result_instance_id2: GLOBAL.CurrentSubsetIDs[2]}
		          }
	});
	//  }
	win.show(viewport);
}

function genePatternLogin() {
	document.getElementById("gplogin").src = pageInfo.basePath + '/analysis/gplogin';
}

function showWorkflowStatusWindow()
{	
	wfsWindow = new Ext.Window({
		id: 'showWorkflowStatus',
		title: 'Workflow Status',
		layout:'fit',
		width:300,
		height:300,
		closable: false,
		plain: true,
		modal: true,
		border:false,
		//autoScroll: true,
		buttons: [
		         {
		        	  text: 'Cancel Job',
		        	  handler: function(){
		        	  runner.stopAll();
		        	  terminateWorkflow();
		        	  wfsWindow.close();}
		          }],
		          resizable: false,
		          autoLoad:
		          {
					url: pageInfo.basePath+'/asyncJob/showWorkflowStatus',
					scripts: true,
					nocache:true,
					discardUrl:true,
					method:'POST'
		          }
	});
	//  }
	wfsWindow.show(viewport);
	
	var updateStatus = function(){
		Ext.Ajax.request(
				{
					url : pageInfo.basePath+"/asyncJob/checkWorkflowStatus",
					method : 'POST',
					success : function(result, request)
					{
						//alert(result);
						workflowStatusUpdate(result);
					}
				,
				failure : function(result, request)
				{
					//alert(result);
					//saveComparisonComplete(result);
				}
				,
				timeout : '300000'
				}
		);
  	} 
  	
  	var task = {
  	    run: updateStatus,
  	    interval: 4000 //4 second
  	}
 
  	runner.start(task);
  	


}

function terminateWorkflow(){
	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/asyncJob/cancelJob",
				method : 'POST',
				success : function(result, request)
				{
					
				}
			,
			failure : function(result, request)
			{
				//alert(result);
				//saveComparisonComplete(result);
			}
			,
			timeout : '300000'
			}
	);
}
function workflowStatusUpdate(result){
	var response=eval("(" + result.responseText + ")");	
	var inserthtml = response.statusHTML;
	var divele = Ext.fly("divwfstatus");
	if(divele!=null){
		divele.update(inserthtml);
	}
	var status = response.wfstatus;
	if(status =='completed'){
		runner.stopAll();		
		if(divele!=null){
			divele.update("");
		}		
		if(wfsWindow!=null){
			wfsWindow.close();
			wfsWindow =null;
		}		
		//var rpCount = response.rpCount;
		//if(rpCount<=1){}
		// only show it once
		showWorkflowResult(result);
	} 
}

function showWorkflowResult(result)
{
	var response=eval("(" + result.responseText + ")");
	var jobNumber = response.jobNumber;
	var viewerURL = response.viewerURL;
	var altviewerURL = response.altviewerURL;
	var gctURL = response.gctURL;
	var cdtURL = response.cdtURL;
	var gtrURL = response.gtrURL;
	var atrURL = response.atrURL;
	var error = response.error;
	var snpGeneAnnotationPage = response.snpGeneAnnotationPage;

	//Ext.MessageBox.hide();

	if (error != undefined) {
		alert(error);
	} 
	else {
		if (snpGeneAnnotationPage != undefined && snpGeneAnnotationPage.length != 0) {
			showSnpGeneAnnotationPage(snpGeneAnnotationPage);
		}
		runVisualizerFromSpan(viewerURL, altviewerURL);
	}
}

function showSnpGeneAnnotationPage(snpGeneAnnotationPage) {
	var resultWin = window.open('', 'Snp_Gene_Annotation_' + (new Date()).getTime(), 
		'width=600,height=800,scrollbars=yes,resizable=yes,location=no,toolbar=no,status=no,menubar=no,directories=no');
	resultWin.document.write(snpGeneAnnotationPage);
}

function saveComparison()
{

	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/comparison/save",
				method : 'POST',
				success : function(result, request)
				{
				saveComparisonComplete(result);
				}
			,
			failure : function(result, request)
			{
				saveComparisonComplete(result);
			}
			,
			timeout : '600000',
			params : Ext.urlEncode(
					{
						result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],
						result_instance_id2 : GLOBAL.CurrentSubsetIDs[2],
						genes: GLOBAL.CurrentGenes
					}
			) // or a URL encoded string
			}
	);
}

function saveComparisonComplete(result)
{
	var mobj=result.responseText.evalJSON();
	
	//If the window is already open, close it.
	if(this.saveComparisonWindow) saveComparisonWindow.close();
	
	//Draw the window with the link to the comparison.
	saveComparisonWindow = new Ext.Window
	({
        id: 'saveComparisonWindow',
        title: 'Saved Comparison',
        autoScroll:true,
        closable: true,
        resizable: true,
        width: 200,
        html: mobj.link
	});	
	
	//Show the window we just created.
	saveComparisonWindow.show(viewport);	
}

function ontFilterLoaded(el, success, response, options)
{
	if(GLOBAL.preloadStudy != "")
		{
			ontTabPanel.setActiveTab("ontFilterPanel");
			Ext.get("ontsearchterm").dom.value = GLOBAL.preloadStudy;
			Ext.get("ontSearchButton").dom.click();
		}
}
