function getSubsetSummary_Initial()
{
	//Create the store which will hold the patient/concept combinations.
	GLOBAL.subsetStore = new subsetStore();
	
	//This keeps track of the nodes in the d3js force object.
	GLOBAL.nodes = [];
	//This keeps track of the different focal points within the SVG.
	GLOBAL.loci = [];
	//These are the text tags under the circles.
	GLOBAL.nodeText = [];
	
	//GLOBALs to keep track of the color of circles. 
	GLOBAL.colorIndex = 0;
	GLOBAL.colorIndexMap = {};
	GLOBAL.color = d3.scale.category20();
	
	//Important to know how many concepts were dragged in.
	GLOBAL.maxColonySize = 1;
	
	//This sets the size of the SVG Object.
	GLOBAL.width = 1200,
	GLOBAL.height = 600;
	
	//Leave some room for the legend on the right side of the SVG Object.
	GLOBAL.legendoffset = 600;
	
	//Fires off on each force tick.
	GLOBAL.tick =  function(e) {
		
		  var k = 6 * e.alpha;
		  
		  GLOBAL.nodes.forEach(function(o, i) {
			    o.y += (GLOBAL.loci[o.groupID].y - o.y) * k;
			    o.x += (GLOBAL.loci[o.groupID].x - o.x) * k;
			  });
		
		GLOBAL.node.attr("cx", function(d) { return d.x; })
	      .attr("cy", function(d) { return d.y; })
	}
	
	//This starts the force simulation.
	GLOBAL.start = function() 
	{
		//Remove all the circles and redraw them based on the data in GLOBAL.nodes.
		GLOBAL.node = GLOBAL.svg.selectAll(".node");
		GLOBAL.node = GLOBAL.node.data(GLOBAL.nodes);
		GLOBAL.node.enter().append("circle").attr("class", "node").attr("r", function(d) {return calculateSize(d.patient_count,d);}).style("fill",function(d) { return GLOBAL.color(d.colorIndex); }).call(GLOBAL.force.drag).on("mousedown", function() { d3.event.stopPropagation(); });		  
		GLOBAL.node.exit().remove();
		
		//Repaint all the nodes, the ones that stuck around weren't getting updated.
		GLOBAL.svg.selectAll(".node").attr("r", function(d) {return calculateSize(d.patient_count,d);}).style("fill",function(d) { return GLOBAL.color(d.colorIndex); }).call(GLOBAL.force.drag).on("mousedown", function() { d3.event.stopPropagation(); });
		
		//If the Loci exist, draw a patient count for each one.
		if(GLOBAL.loci && GLOBAL.loci.length > 0)
		{
			GLOBAL.svg.selectAll(".patient_count_label").remove();
			GLOBAL.nodeText = GLOBAL.svg.selectAll(".patient_count_label");
			GLOBAL.nodeText = GLOBAL.nodeText.data(GLOBAL.loci);
			GLOBAL.nodeText.enter().append("text").attr("class", "patient_count_label").text(function(d){return d.patient_count;}).attr("x", function(d){return d.x-10}).attr("y", function(d){return d.y+60});
			GLOBAL.nodeText.exit().remove();
		}
		
		//If we have circles to draw, add the legend corresponding to each.
		if(GLOBAL.subsetStore.getAllColonies().length > 0)
		{			  
			GLOBAL.legend.selectAll('rect').remove();
			GLOBAL.legend.selectAll('text').remove();
			  
			GLOBAL.legend.selectAll('rect').data(GLOBAL.subsetStore.getAllColonies()).enter().append('rect').attr("x", GLOBAL.width - GLOBAL.legendoffset).attr("y", function(d, i){ return i * 50 + 20;}).attr("width", 30).attr("height", 30).style("fill", 
					function(d) 
					{ 
						return GLOBAL.color(GLOBAL.colorIndexMap[d.conceptPath])
					});
			GLOBAL.legend.selectAll("text").data(GLOBAL.subsetStore.getAllColonies()).enter().append('text').attr("x", GLOBAL.width - GLOBAL.legendoffset + 40).attr("y", function(d, i){ return i * 50 + 40;}).attr("class","d3jstextlabel").text(function(d, i) {return d.conceptPath});	
		}
  
		GLOBAL.force.start();
	}
	
	//Initialize the force simulation.
	GLOBAL.force = d3.layout.force()
	.nodes(GLOBAL.nodes)
	.charge(-2000)
	.gravity(0)
	.size([GLOBAL.width, GLOBAL.height])
	.on("tick", GLOBAL.tick);
	
	d3.select("#subsetSummarySVG").remove();
	
	//Initialize the SVG Object.
	GLOBAL.svg = d3.select("#subsetSummaryDiv").append("svg")
	.attr("id", "subsetSummarySVG")
	.attr("width", GLOBAL.width)
	.attr("height", GLOBAL.height)
	.attr("x", 200)
	.attr("y", 200);
	
	//Initialize the legend object.
	GLOBAL.legend = GLOBAL.svg.append("g")
	.attr("class", "legend")
	.attr("x", GLOBAL.width - GLOBAL.legendoffset)
	.attr("y", 50)
	.attr("height", 100)
	.attr("width", 100);
	
	//Start Simulation.
	GLOBAL.start();

	resultsTabPanel.setActiveTab('subsetPanel');
	subsetAnalysisPanel.body.unmask();
	GLOBAL.isSubsetSummaryInitialized = true;
}

//Calculates the size of a circle based on a patient count.
function calculateSize(patientCount, foo)
{
	if(patientCount == 0)
	{
		return 3;
	}
	
	if(!patientCount)
	{
		return 40;
	}
	
	var sizeTotal = ((patientCount / GLOBAL.maxColonySize) * 40) + 5;
	return sizeTotal;
	
}

//When a user drops a new concept into the analysis page we need to get the patient list for that concept.
function buildAnalysisSubsetSummary(droppedNode)
{
	jQuery.ajax(
			{
				url : pageInfo.basePath+"/subsetSummary/subsetNewNode",
				method : 'POST',
				success : function(result, request){buildAnalysisSubsetSummaryComplete(result,droppedNode.attributes.dimcode)},
				failure : function(result, request){alert("Failure!")},
				data : {result_instance_id1 : GLOBAL.CurrentSubsetIDs[1],
						node_in : droppedNode.attributes.dimcode}
			}
	);
}

//When we have the new node's patient list we add them to the store and restart the simulation.
function buildAnalysisSubsetSummaryComplete(results, conceptPath)
{
	//We need to assign this new concept a node color.
	GLOBAL.colorIndexMap[conceptPath] = GLOBAL.colorIndex + 1;
	GLOBAL.colorIndex += 1;
	
	if(results.length > GLOBAL.maxColonySize)
	{
		GLOBAL.maxColonySize = results.length;
	}
	
	//Add the patients that have this concept to our store.
	GLOBAL.subsetStore.addColony(conceptPath, results);
	
	//This creates all the possible concept interactions.
	breedColonies(GLOBAL.subsetStore.getAllColonies());
	
	buildNodesFromColonies(GLOBAL.groups);
	buildLociFromGroups(GLOBAL.groups);
	
	GLOBAL.start();
	
	subsetAnalysisPanel.body.unmask();
	
}

//Iterate over all the colonies and group concepts together where patient numbers overlap.
function breedColonies(colonies)
{
	//Let's first create a group map with a submap of concept and patient counts.
	//[[{concept:'\Autism\Epilepsy\yes',count:10},{concept:'\Autism\Deafness\yes',count:10}],[]]
	
	GLOBAL.groups = [];
	
	var tempGroups = [];
	var resultObject = [];
	
	for (var topLoop = 0; topLoop < colonies.length; topLoop++) { 
		
		tempGroups = [];
		
		if(topLoop==0)
		{
			resultObject = jQuery.extend([], colonies);
		}
		
		for (var middleLoop = 0; middleLoop < colonies.length; middleLoop++) 
		{ 
			for (var bottomLoop = 0; bottomLoop < resultObject.length; bottomLoop++) 
			{ 
				if(colonies[middleLoop].conceptPath != resultObject[bottomLoop].conceptPath)
				{
					if(jQuery.inArray(colonies[middleLoop].conceptPath, resultObject[bottomLoop].conceptPath.split("|")) == -1)
					{
						if(!entryExists(colonies[middleLoop].conceptPath + "|" + resultObject[bottomLoop].conceptPath, tempGroups) && !entryExists(colonies[middleLoop].conceptPath + "|" + resultObject[bottomLoop].conceptPath, resultObject))
						{
							var currentIntersection = getIntersect(colonies[middleLoop].patientList, resultObject[bottomLoop].patientList)
							
							if(currentIntersection.length > 0)
							{
								tempGroups.push(new subsetColony(colonies[middleLoop].conceptPath + "|" + resultObject[bottomLoop].conceptPath,currentIntersection));
							}
						}
					}
				}
				
			};
			  
		};
			
		if(tempGroups.length > 0)
		{
			resultObject = resultObject.concat(tempGroups);
		}

	}
	
	GLOBAL.groups = resultObject;
}

//Find if an entry exists in an array.
function entryExists(newKey, entryMap)
{
	var foundEntry = false;
	
	var mapSet = newKey.split("|");
	
	for (var mapLoop = 0; mapLoop < entryMap.length; mapLoop++)
	{
		var currentItem = entryMap[mapLoop].conceptPath.split("|")
		
		if(currentItem.sort().join(',') === mapSet.sort().join(','))
		{
			foundEntry = true;
		}
		
	}
	
	return foundEntry
	
}

function buildNodesFromColonies(colonies)
{
	GLOBAL.nodes.length = 0; 
	
	for (var groupLoop = 0; groupLoop < colonies.length; groupLoop++) 
	{ 
		var currentPathList = colonies[groupLoop].conceptPath.split("|");
		
		for (var conceptLoop = 0; conceptLoop < currentPathList.length; conceptLoop++) 
		{ 
			
			var nodeNameArray = currentPathList[conceptLoop].split("\\");
			var nodeName = "..\\" + nodeNameArray[nodeNameArray.length - 3] + "\\" + nodeNameArray[nodeNameArray.length - 2];
			
			
			var newNode = {	id : groupLoop + "_" + currentPathList[conceptLoop], 
							patient_count : colonies[groupLoop].patientList.length,
							name : nodeName,
							groupID : groupLoop,
							colorIndex : GLOBAL.colorIndexMap[currentPathList[conceptLoop]]
						};
			
			GLOBAL.nodes.push(newNode);
		}
		
	}
}

function buildLociFromGroups(groups)
{
	GLOBAL.loci.length = 0;
	
	var currentx = 50;
	var currenty = 50;
	
	var xincrement = (GLOBAL.width - GLOBAL.legendoffset) / 3;
	var yincrement = 150;
	
	var rowCounter = 1;
	
	for (var groupLoop = 0; groupLoop < groups.length; groupLoop++) 
	{
		GLOBAL.loci.push({	id: groups[groupLoop].conceptPath, 
							x: currentx, 
							y: currenty, 
							patient_count: groups[groupLoop].patientList.length})
		
		if((rowCounter % 3) != 0)
		{
			currentx += xincrement;
		}
		else
		{
			currentx = 50;
			currenty += yincrement;
		}
		
		rowCounter ++;
		
	}
	
}

//This gets the intersection of two arrays.
//http://www.falsepositives.com/index.php/2009/12/01/javascript-function-to-get-the-intersect-of-2-arrays/
function getIntersect(arr1, arr2) {
    var r = [], o = {}, l = arr2.length, i, v;
    for (i = 0; i < l; i++) {
        o[arr2[i]] = true;
    }
    l = arr1.length;
    for (i = 0; i < l; i++) {
        v = arr1[i];
        if (v in o) {
            r.push(v);
        }
    }
    return r;
}


/******************************/
//CLASSES
/******************************/
function subsetStore () {
    var colonies = []
    
    this.d3force = null
    
    this.addColony = function(conceptPath, patientList) {
    	var newSubsetColony = new subsetColony(conceptPath, patientList);
    	
    	colonies.push(newSubsetColony);
    }
    
    this.getAllColonies = function() {
    	return colonies;
    }
 }

function subsetColony(conceptPath, patientList) {
	this.conceptPath = conceptPath;
	this.patientList = patientList;
}
/******************************/

function clearSubsetSummary()
{

	GLOBAL.nodes = [];
	GLOBAL.loci = [];
	GLOBAL.nodeText = [];	
	
	GLOBAL.subsetStore = new subsetStore();

	d3.select("#subsetSummarySVG").remove();
}

function emptySubsetSummary()
{
	getSubsetSummary_Initial();

}



