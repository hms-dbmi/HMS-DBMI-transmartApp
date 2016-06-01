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

import grails.converters.JSON
 


class SequenceVariantExplorerController {

	def springSecurityService
	def solrService
	def defaultSOLRCore = "sequenceVariantExplorer"
	
	/**
	 * If we hit just the index, we need to log an event and redirect to the list page.
	 */
	def index =
	{
		//Create an event record for this access.
		def al = new AccessLog(username: springSecurityService.getPrincipal().username, event:"SequenceVariantExplorer-Summary", eventmessage:"Sequence Variant Explorer summary page", accesstime:new Date())
		al.save();
		
		session["solrCoreName"] = params.explorerType 
		
		if(!session["solrCoreName"]) session["solrCoreName"] = defaultSOLRCore
				
		redirect(action:'list')
	}
	
	/**
	 * This shows the page that has different groups for each category, and the links to filter with.
	 */
	def showTopLevelListPage =
	{
		//Call the solr service to get a hash that looks like category:[item:count]. We pass in an empty string because we want all the documents in the solr search.
		def termMap = solrService.facetSearch("",verifyFieldList(), session["solrCoreName"])
		def distinctGroups = []
		
		termMap.each()
		{
			currentTerm ->
			
			if(currentTerm.value['group']) distinctGroups.push(currentTerm.value['group'])
		}
		
		distinctGroups = distinctGroups.unique()
		
		println(distinctGroups)
		
		//Render the list of links in their own categories.
		render(template:"searchTopLevel", model:[termsMap:termMap, savedFilters: SaveSolrQuery.findAll(), distinctGroups: distinctGroups.unique()]);
	}
	
	/**
	 * Display all the summary links.
	 */
	def list =
	{
		if(!session["solrCoreName"]) session["solrCoreName"] = defaultSOLRCore
		
		def columnMap = verifyGridFieldList();
		
		render(view: "sequenceVariantExplorer", model:[sampleRequestType:"search", columnData:columnMap as JSON, explorerType : session["solrCoreName"]])
	}
	
	def showMainSearchPage =
	{
		//We need to pass in the top X news stories so we can draw them on the screen.
		def newsUpdates = NewsUpdate.list(max:grailsApplication.config.com.recomdata.solr.maxNewsStories, sort:"updateDate", order:"desc")
		
		render(template:"categorySearch", model:[newsUpdates:newsUpdates]);
	}
	
	/**
	 * Show the box to the west that has the category links with checkboxes.
	 */
	def showWestPanelSearch =
	{
		//Grab the list of fields we will concern ourselves with.
		def solrFieldList = verifyGridFieldList();
		
		//Call the solr service to get a hash that looks like category:[item:count].
		def termMap = solrService.facetSearch(request.JSON.SearchJSON,solrFieldList, session["solrCoreName"])
		
		//Render the list of checkboxes and links based on the items in our search JSON.
		render(template:"categorySearchWithCheckboxes", model:[termsMap:termMap,JSONData:request.JSON.SearchJSON]);
	}
	
	/**
	 * This draws the simple HTML page that has the DIV that gets populated by the ExtJS datagrid.
	 */
	def showDataSetResults =
	{
		Boolean includeCohortInformation = false
		
		def sampleSummary = [:]
		
		if(request.JSON?.showCohortInformation == "TRUE")
		{
			//sampleSummary = sampleService.loadSampleStatisticsObject(request.JSON?.result_instance_id)
			//includeCohortInformation = true
		}
		
		render(template:"dataSetResults", model:[includeCohortInformation : includeCohortInformation, sampleSummary : sampleSummary]);
	}
	
	/**
	 * This method will return a JSON object representing the items that match the users search.
	 */
	def loadSearch =
	{
		//Grab the categories from the form. They might be "All".
		def category = params.query.substring(0, params.query.indexOf(":"))
		
		if(category == "all")
		{
			category = grailsApplication.config[session["solrCoreName"]].textSearchTerms
		}
		else
		{
			def tempCategory = category
			category = []
			category.push(tempCategory)
		}
			
		//Grab the value to search for.
		def values = params.query.substring(params.query.indexOf(":") + 1)
		
		//Get the list of possible results.
		def resultsHash = solrService.suggestTerms(category.join(","),values,grailsApplication.config.com.recomdata.solr.numberOfSuggestions.toString(), session["solrCoreName"])

		//Render the results as JSON.
		render params.callback+"("+(resultsHash as JSON)+")"
	}
	
	/**
	 * This returns a JSON object representing the available solr fields. Used mainly to populate picklists.
	 */
	def loadCategories =
	{
		//Get the field list from session, or retrieve it from Solr.
		def fieldList = verifyFieldList()
		
		//Initialize the map with the all value.
		def categoryMap = [rows:[["value":"all","label":"all"]]]
		
		//We need to put the field list into a format that the pick list expects. Each field gets a label and value entry.
		grailsApplication.config[session["solrCoreName"]].textSearchTerms.each
		{
			def tempMap = [:]
			
			tempMap['value'] = it
			tempMap['label'] = it
			
			categoryMap['rows'].add(tempMap)
		}
	
		render params.callback+"("+(categoryMap as JSON)+")"
	}
	
	/**
	 * This method checks to make sure the list of fields we want to use are in session. If they aren't, it adds them to the session.
	 */
	def verifyFieldList =
	{
		//This field list always has all the fields we want to display.
		//if(!session['fieldList']) session['fieldList'] = loadFieldList()
		
		return loadFieldList()
	}
	
	/**
	 * This will get the list of available fields from the Solr server.
	 */
	def loadFieldList = {
		
		//Pull the field map from the configuration file.
		def resultsList = grailsApplication.config[session["solrCoreName"]].fieldMapping.clone()

		if(!resultsList) throw new Exception("Field Mapping Configuration not set!")
		
		def columnConfigsToRemove = []

		resultsList.columns.each
		{
			currentColumn ->
			if(!currentColumn.mainTerm)
			{
				columnConfigsToRemove.add(currentColumn)
			}
		}
		
		resultsList.columns = resultsList.columns - columnConfigsToRemove
		
		return resultsList
	}
	
	/**
	 * This method checks to make sure the list of fields we want to use are in session. If they aren't, it adds them to the session.
	 */
	def verifyGridFieldList =
	{
		//This field list might get modified later and contains only the fields being display in the gridpanel.
		//if(!session['gridFieldList']) session['gridFieldList'] = loadEntireFieldList()
		
		return loadEntireFieldList()
	}
	
	def loadEntireFieldList = {
		
		//Pull the field map from the configuration file.
		def fullColumnList = grailsApplication.config[session["solrCoreName"]].fieldMapping.clone()
		
		if(!fullColumnList) throw new Exception("Field Mapping Configuration not set!")
		
		return fullColumnList
	}
	
	/**
	 * This will pull a result set from Solr using a query based on the JSON data passed in. Returns results as JSON.
	 */
	def getDataSetResults =
	{
		//Grab the string for the maximum number of result rows to return.
		String solrMaxRows = grailsApplication.config.com.recomdata.solr.maxRows
		
		String selectedResultColumns = ""
		
		if(request.JSON.PanelNumber)
		{
			//In the JSON result there is a list of the columns we expect to get back.
			selectedResultColumns = request.JSON.SearchJSON["GridColumnList" + request.JSON.PanelNumber ].join(",").replace("\"","")

			selectedResultColumns = selectedResultColumns.replace("GridColumnList" + request.JSON.PanelNumber, "")
		}
		else
		{
			//In the JSON result there is a list of the columns we expect to get back.
			selectedResultColumns = request.JSON.SearchJSON.GridColumnList.join(",").replace("\"","")
		}
		
		//This will be the hash to store our results.
		def resultsHash = solrService.pullResultsBasedOnJson(request.JSON.SearchJSON,selectedResultColumns, false, true, session["solrCoreName"])


		render resultsHash as JSON
	}
	
	/**
	 * This will get the list of available fields from the Solr server.
	 */
	def loadGridFieldList = {
		
		//Pull the field map from the configuration file.
		def resultsList = grailsApplication.config[session["solrCoreName"]].fieldMapping.clone()

		if(!resultsList) throw new Exception("Field Mapping Configuration not set!")
		
		def columnConfigsToRemove = []

		resultsList.columns.each
		{
			currentColumn ->
			if(!currentColumn.showInGrid)
			{
				columnConfigsToRemove.add(currentColumn)
			}
		}
		
		resultsList.columns = resultsList.columns - columnConfigsToRemove

		return resultsList
	}
	
	def saveSolrQuery = {
				
		solrService.saveSolrQuery(params.queryName, params.queryContents)
		
		render "Done!"
	}
	
	def retrieveSolrQuery = {
		
		def responseData = ['results' : SaveSolrQuery.findById(params.queryId).queryContents]
		
		render SaveSolrQuery.findById(params.queryId).queryContents
		
	}
	
	
}
