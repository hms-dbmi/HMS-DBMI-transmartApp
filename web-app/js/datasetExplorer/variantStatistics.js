function getWESstatistics()
{
	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/variant/basicVariantStatistics",
				method : 'POST',
				success : function(result, request)
				{
					getWESstatisticsComplete(result);
				},
			failure : function(result, request)
			{
				getWESstatisticsComplete(result);
			},
			timeout : '300000',
			params : Ext.urlEncode(
					{
						result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],
						result_instance_id2 : GLOBAL.CurrentSubsetIDs[2]
					}
			),
			disableCaching : true
			}
	);

	resultsTabPanel.body.mask("Running analysis...", 'x-mask-loading');
}

function getWESstatisticsComplete(result, request)
{
	resultsTabPanel.setActiveTab('analysisPanel');
	updateAnalysisPanel(result.responseText, false);
	resultsTabPanel.body.unmask();
}

function buildVariantAnalysis(nodein)
{
	var node = nodein 
	if(isSubsetEmpty(1) && isSubsetEmpty(2))
	{
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

	Ext.Ajax.request(
			{
				url : pageInfo.basePath+"/variant/analysis",
				method : 'POST',
				timeout: '600000',
				params :  Ext.urlEncode(
						{
							concept_key : node.attributes.id,
							result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],
							result_instance_id2 : GLOBAL.CurrentSubsetIDs[2]
						}
				),
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

	resultsTabPanel.body.mask("Running analysis...", 'x-mask-loading');
}