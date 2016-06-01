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
 
function loadChildNodes(response,callback,objectThis,node)
{	
	var getChildrenRequest=getONTRequestHeader()+'<ns4:get_children blob="true" max="1000" synonyms="false" hiddens="false">';
	getChildrenRequest=getChildrenRequest+"<parent>"+node.id+"</parent></ns4:get_children>"+getONTRequestFooter();

	objectThis.transId = Ext.Ajax.request({
        url: pageInfo.basePath+"/proxy?url="+GLOBAL.ONTUrl+"getChildren",
        method: 'POST',
        xmlData: getChildrenRequest,  
        success: objectThis.handleResponse,
        failure: objectThis.handleFailure,
        scope: objectThis,
        argument: {callback: callback, node: node},
        timeout: '120000', //2 minutes
        params: { }
    });
}

Ext.ux.OntologyTreeLoader = Ext.extend(Ext.tree.TreeLoader, {

requestData : function(node, callback){
        if(this.fireEvent("beforeload", this, node, callback) !== false)
        {
        	//Probably could use a better indicator for modifier folders....
        	if(node.attributes.iconCls == "modifierfoldericon")
        	{
        		var parentNode = node.id;
        		
            	var getChildrenRequest = getONTRequestHeader();
            	getChildrenRequest += '<ns4:get_modifier_children synonyms="false" hiddens="false">';
            	getChildrenRequest += "	<parent>" + node.attributes.modifierId.replace(/\\.?\\$/,"\\") + "</parent>";
            	getChildrenRequest += "	<applied_path>" + node.attributes.applied_path + "</applied_path>";
            	getChildrenRequest += "	<applied_concept>" + "\\\\" + node.attributes.modifierId.split("\\")[2] + node.attributes.applied_path + "</applied_concept>";
            	getChildrenRequest += "</ns4:get_modifier_children>";
            	getChildrenRequest += getONTRequestFooter();
            
            	var currentObject = this
            	
                this.transId = Ext.Ajax.request({
                    url: pageInfo.basePath+"/proxy?url="+GLOBAL.ONTUrl+"getModifierChildren",
        	        method: 'POST',
        	        xmlData: getChildrenRequest,  
                    success: this.handleResponse,
                    failure: this.handleFailure,
                    argument: {callback:callback, node:node},
                    scope: this,
                    timeout: '120000', //2 minutes
                    params: { }
                });        		
        		
        	}
        	else
        	{
            	var getChildrenRequest=getONTRequestHeader()+'<ns4:get_modifiers synonyms="false" hiddens="false">';
            	getChildrenRequest=getChildrenRequest+"<self>"+node.id+"</self></ns4:get_modifiers>"+getONTRequestFooter();
            
            	var currentObject = this
            	
                this.transId = Ext.Ajax.request({
                    url: pageInfo.basePath+"/proxy?url="+GLOBAL.ONTUrl+"getModifiers",
        	        method: 'POST',
        	        xmlData: getChildrenRequest,  
                    success: this.handleResponse,
                    failure: this.handleFailure,
                    scope: this,
                    argument: {callback: function(response){GLOBAL.updateCounts = true;loadChildNodes(response,callback,currentObject,node);}, node: node},
                    timeout: '120000', //2 minutes
                    params: { }
                });        		
        	}
            
        }else{
            // if the load is cancelled, make sure we notify
            // the node that we are done
            if(typeof callback == "function"){
                callback();
            }
        }
  },

processResponse:function (response, node, callback) {

	node.beginUpdate();

	this.parseXml(response, node);
	if(GLOBAL.updateCounts == true) 
		{
			getChildConceptPatientCounts(node);
			GLOBAL.updateCounts = false;
		}
	node.endUpdate();
	if (typeof callback == "function") {
		callback(this, node);
	}
}, 

parseXml:function (response, node) {
    var Tree = Ext.tree;
    
 var concepts=response.responseXML.selectNodes('//concept');
	
	for(i=0;i<concepts.length;i++)
	  {
   		 var c=getTreeNodeFromXMLNode(concepts[i]);
   		 if(c.attributes.id.indexOf("SECURITY")>-1){continue;}
   		 node.appendChild(c);
   	 }
	
	 var modifiers = response.responseXML.selectNodes('//modifier');
		
	for(i=0;i<modifiers.length;i++)
	  {
   		 var c=getTreeNodeFromXMLNode(modifiers[i]);
   		 if(c.attributes.id.indexOf("SECURITY")>-1){continue;}
   		 
   		 c.id=node.id+c.attributes.text+"\\";
   		 
   		 node.appendChild(c);
   	 }
}



});

function getConceptPatientCount(node)
{
Ext.Ajax.request(
    	    {
    	        url: pageInfo.basePath+"/chart/conceptPatientCount",
    	        method: 'POST',                                       
    	        success: function(result, request){getConceptPatientCountComplete(result, node);},
    	        failure: function(result, request){getConceptPatientCountComplete(result, node);},
    	        timeout: '300000',
    	        params: Ext.urlEncode({charttype:"conceptpatientcount",
    	        		 			   concept_key: node.attributes.id})
    	    });   
}

function getConceptPatientCountComplete(result, node)
{
node.setText(node.text+" <b>("+result.responseText+")</b>");
}

function getChildConceptPatientCounts(node)
{
Ext.Ajax.request(
    	    {
    	        url: pageInfo.basePath+"/chart/childConceptPatientCounts",
    	        method: 'POST',                                       
    	        success: function(result, request){getChildConceptPatientCountsComplete(result, node);},
    	        failure: function(result, request){getChildConceptPatientCountsComplete(result, node);},
    	        timeout: '300000',
    	        params: Ext.urlEncode({charttype:"childconceptpatientcounts",
    	        		 			   concept_key: node.attributes.id,
    	        		 			   concept_level: node.attributes.level})
    	    });   
}

function getChildConceptPatientCountsComplete(result, node)
{
	var mobj			= result.responseText.evalJSON();
	var childaccess		= mobj.accesslevels;
	var childcounts		= mobj.counts;
	var childmetadata	= mobj.metadata;
	var childvalidated  = mobj.validated;
	var children		= node.childNodes;

	node.beginUpdate();
	
	for (var i=0;i<children.size();i++)
	{
	  var key		= children[i].attributes.id;
	  var fullname	= key.substr(key.indexOf("\\",2), key.length);
	  var count		= childcounts[fullname];
	  var access	= childaccess[fullname];
	  var metadata	= childmetadata[fullname];
	  var validated = childvalidated[fullname];
	  var child		= children[i];
	  
	  if(count!=undefined && metadata==undefined)
	  {
		  child.setText(child.text+" ("+count+")");
 child.setText(child.text+" &nbsp;&nbsp;<a href='#' onclick='displayMetaData1(\"" + fullname.replace(/\\/g,"\\\\") + "\", \"" + climbTreeBuildName(node).replace(/\\/g,"\\\\")  + originalNodeText + "\\\\" + "\" ,this);'><img style='height:11px;' src='" + GLOBAL.basePath + "/images/book.png'/></a>");
	  }
	  
	  //If I'm an admin or there is an access level other than locked leave node unlocked.

	  /* commit1- changed to isSuperAdmin (admin, datasetexplorer_admin) to distinguish between isAdmin ((admin, datasetexplorer_admin, study_owner) in i2b2HelperService class */  
	  /* commit2- IsSuperAdmin is only required when there are different studies in a single tranSMART instance, reverting it back to IsAdmin, if enabled all ETL pipelines must load data into table i2b2_secure, and there should be a fix done for modifiers */
	  if(!((access!=undefined && access!='Locked') || GLOBAL.IsAdmin)) 

	  {
	  	//Default node to locked.
	  	child.attributes.access='locked';
	  	child.disable();
	  	child.on('beforeload', function(node){alert("Access to this node has been restricted. Please contact your administrator for access."); return false}); 
	  }
	  
	  if(metadata!=undefined)
	  {
		  var originalNodeText = child.text;
		  
		  //Display custom count columns from metadata table.
		  for (var metadataKey in metadata) 
		  {
			  child.setText(child.text + " (<b>" + metadataKey + "</b>" + metadata[metadataKey] + ")")
		  }

		if(GLOBAL.IsAdmin)

		  child.setText(child.text+" &nbsp;&nbsp;<a href='#' onclick='displayMetaData(\"" + fullname.replace(/\\/g,"\\\\") + "\", \"" + climbTreeBuildName(node).replace(/\\/g,"\\\\")  + originalNodeText + "\\\\" + "\" ,this);'><img style='height:11px;' src='" + GLOBAL.basePath + "/images/summary.png'/></a>");
child.setText(child.text+" &nbsp;&nbsp;<a href='#' onclick='displayMetaData1(\"" + fullname.replace(/\\/g,"\\\\") + "\", \"" + climbTreeBuildName(node).replace(/\\/g,"\\\\")  + originalNodeText + "\\\\" + "\" ,this);'><img style='height:11px;' src='" + GLOBAL.basePath + "/images/book.png'/></a>");
	  }
	  
	  // Set color for validated concepts
	  if(validated!=undefined)
	  {		  
		  child.ui.addClass("validated")	  
	  }	  
	}
	
	node.endUpdate();
}

