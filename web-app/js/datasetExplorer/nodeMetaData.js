function displayMetaData(nodeName, nodeDisplayPath, currentIcon)
{
	$j("#metaDataDiv").load(GLOBAL.basePath + "/nodeMetadata/nlpStats",{nodePath:nodeName, nodeDisplayPath:nodeDisplayPath});

	$j( "#metaDataDiv" ).dialog(
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
	    dialogClass:'metaDataDialog',
	    width:400,
	    height:500,
	    modal:true,
	    position: { my: "left top", at: "right bottom", of: currentIcon },
	    beforeClose: function(event, ui) { $j( "#metaDataDiv" ).empty(); }
    });
}
function displayMetaData1(nodeName, nodeDisplayPath, currentIcon)
{
	$j("#wikiDiv").load(GLOBAL.basePath + "/nodeMetadata/wiki",{nodePath:nodeName, nodeDisplayPath:nodeDisplayPath});

	$j( "#wikiDiv" ).dialog(
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
	    dialogClass:'metaDataDialog',
	    width:400,
	    height:500,
	    modal:true,
	    position: { my: "left top", at: "right bottom", of: currentIcon }
    });
}
function closeMetaData()
{
	$j( "#metaDataDiv" ).dialog('close');
}
function closeMetaData()
{
	$j( "#wikiDiv" ).dialog('close');

	}
function validateMetaData()
{
	
}