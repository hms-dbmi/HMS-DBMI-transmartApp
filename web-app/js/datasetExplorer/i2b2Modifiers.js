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
  


function showModifierSetValueDialog(node, modifierElement)
{
	
	GLOBAL.modifierConcept = node;
	GLOBAL.modifierElement = modifierElement;
	
	var modifiedNode;
	
	//Check our parent, if it's a modifier container than check its parent to find out the node we are modifying.
	if(node.parentNode.attributes.applied_path != null && node.parentNode.attributes.applied_path != "@")
	{
		modifiedNode = node.parentNode.parentNode;
	}
	else
	{
		modifiedNode = node.parentNode;
	}

	GLOBAL.modifierConcept.attributes.modifiedNodePath 	= modifiedNode.attributes.id;
	GLOBAL.modifierConcept.attributes.modifiedNodeId 	= climbTreeBuildName(modifiedNode);
	GLOBAL.modifierConcept.attributes.modifiedNodeLevel = modifiedNode.attributes.level;

	
	var getChildrenRequest = getONTRequestHeader();
	getChildrenRequest += '<ns4:get_modifier_info blob="true" type="core" synonyms="true" hiddens="true">';
	getChildrenRequest += "	<self>" + node.attributes.modifierId.replace(/\\.?\\$/,"\\") + "</self>";
	getChildrenRequest += "	<applied_path>" + node.attributes.applied_path + "</applied_path>";
	getChildrenRequest += "</ns4:get_modifier_info>";
	getChildrenRequest += getONTRequestFooter();
	
	jQuery.ajax(
			{
				url : pageInfo.basePath+"/proxy?url="+GLOBAL.ONTUrl+"getModifierInfo",
				type : 'POST',
				success : function(result, request){showModifierSetValueDialogComplete(result)},
				failure : function(result, request){alert("Failure!")},
				contentType: "text/xml",
				processData : false,
				data : getChildrenRequest
			});

}

function showModifierSetValueDialogComplete(modifierDataInXML)
{
	var draggedModifier = new modifierNode();
	
	draggedModifier.initializeFromXML(modifierDataInXML);

	var modifierAsConcept = convertNodeToConcept(GLOBAL.modifierConcept);
	
	modifierAsConcept.key = GLOBAL.modifierConcept.attributes.modifierId;
	
	if(draggedModifier.needsValuePopup)
	{
		var concept = createPanelItemNew(Ext.get("hiddenDragDiv"), modifierAsConcept);
		selectConcept(concept);
		
		STATE.Dragging = true;
		STATE.Target = GLOBAL.modifierElement;
		
		var newModifierPopupWindow = new modifierInfoScreen()
		newModifierPopupWindow.generateDialog(draggedModifier);
	}
	else
	{
		var concept = createPanelItemNew(GLOBAL.modifierElement, modifierAsConcept);
		selectConcept(concept);
	}

	return true;
	
}

function modifierNode()
{
	var xmlPathToOkToUseValues 	= './/metadataxml/ValueMetadata/Oktousevalues';
	var xmlPathToDataType		= './/metadataxml/ValueMetadata/DataType';
	
	this.oktousevalues 		= null
	this.modifierValueType 	= null
	
	this.needsValuePopup	= false
	
	this.modifierValuesHashmap = {};

	this.initializeFromXML = function(modifierXML)
	{	
		this.oktousevalues = extractXMLValue(modifierXML.selectSingleNode(xmlPathToOkToUseValues), null);

		//Get the Data Type field out of the XML so we know what to expect in the rest of the response.
		this.modifierValueType = extractXMLValue(modifierXML.selectSingleNode(xmlPathToDataType), null);
		
		if(this.oktousevalues != null && this.oktousevalues == "Y")
		{
			this.needsValuePopup = true;
		}
		
		if(this.modifierValueType == 'Enum')
		{
			//If the Data Type is Enum we need to pull the list of values and draw a popup with a select list.
			var modifierEnumValues = modifierXML.selectNodes(".//modifier/metadataxml/ValueMetadata/EnumValues/Val");
			
			this.modifierValuesHashmap = extractEnumsFromXML(modifierEnumValues);
			
		}

	}
	
	function extractEnumsFromXML(enumValues)
	{
		var tempMap = {};
		
		enumValues.each(function(index, value)
		{
			if(index.getAttribute("description") != null)
			{	
				tempMap[index.textContent] = index.getAttribute("description");
			}

		});
		
		return tempMap;
		
	}
	
	function extractXMLValue(node, defaultValue)
	{
		var result = defaultValue;

		if(node!=null && node!=undefined)
		{
				if(node.firstChild!=null && node.firstChild!=undefined)
				{
					result=node.firstChild.nodeValue;
				}
		}

		return result;
	}

}

function getSelectedText(elementId) 
{
	var selectedTextArray = [];
	
    var elt = document.getElementById(elementId);
    
    for (var i = 0; i < elt.length; i++) {
        if (elt.options[i].selected) selectedTextArray.push(elt.options[i].text);
    }

    return selectedTextArray.join(", ");
}

function modifierInfoScreen()
{
	
	this.draggedModifier = null;
	
	//Build the Modifier select list.
	this.generateDialog = function(draggedModifier)
	{
		
		this.draggedModifier = draggedModifier;
		
		//Remove all the modifiers from the dropdown.
		jQuery('#modifierValueList').find('option').remove().end();
		
		//Add new modifiers to dropdown.
		jQuery.each(draggedModifier.modifierValuesHashmap, 
		function(key, value) 
		{
			jQuery('#modifierValueList').append("<option value='" + key + "'>" + value + "</option>");
		});
	
		//Remove click event for closing the dialog and saving options.
		jQuery( "#btnModifierValuesDone" ).unbind("click");
		
		//Recreate click event.
		jQuery( "#btnModifierValuesDone" ).bind("click", function()
		{
			
			var conceptnode = selectedConcept;
			
			var valuetext			= "";
			var conceptshortname 	= extractConceptShortName(conceptnode.getAttribute("modifiednodeid"));
			
			if(jQuery("#chkEnableModifierValues")[0].checked)
			{
				conceptnode.setAttribute('setvaluemode',"Enum");
				
				valuetext = "[" + getSelectedText("modifierValueList") + "]"
				
				conceptnode.setAttribute('conceptsetvaluetext',valuetext);
				
				conceptnode.setAttribute('conceptsetvalueinlist',jQuery("#modifierValueList").val());
				
			}
			else
			{
				conceptnode.setAttribute('setvaluemode',"modifierOnly");
				
				valuetext = "[" + conceptnode.getAttribute('conceptshortname') + "]";
			}
				
			Ext.get(conceptnode.id).update(conceptshortname+" "+valuetext);
	
			var subset=getSubsetFromPanel(conceptnode.parentNode);
	
			invalidateSubset(subset);
			
			if(STATE.Dragging==true)
			{
				STATE.Dragging=false;
				moveSelectedConceptFromHoldingToTarget();
			}
			
			jQuery('#modifierValueDiv').dialog('close');
		}
		);
		
	jQuery( "#modifierValueDiv" ).dialog(
				{
					show: 
						{
							effect: "fade",
							duration: 100
						},
				    hide: 
				    	{
				        	effect: "fade",
				        	duration: 100
				    	},
				    dialogClass:'modifierValueDialog',
				    width:400,
				    height:500,
				    modal:true
			    });
	}
	
	

}





