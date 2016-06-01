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

import edu.hms.transmart.security.AuthUser
import grails.converters.JSON


class ClinicalDataController {

	def springSecurityService
	def clinicalDataService

	/**
	 * 
	 */
	def retrieveClinicalData = {
		println "Controller()retrieveClinicalData() ...."

		def userAllowedAPIAccess = false

		def user = AuthUser.findByUsername(springSecurityService.getPrincipal().username)
		

		//I don't think the spring security version we have has an implemented way to do this.
		for (role in user.authorities){
			/*if (role.authority.equals("ROLE_API")) {
				userAllowedAPIAccess = true
			}*/
			if (role.authority.equals("ROLE_DATASET_EXPLORER_ADMIN")) {
				userAllowedAPIAccess = true
			}
		}
		
		if(userAllowedAPIAccess)
		{
			String resultInstanceID = request.getParameter("rid");
			String conceptPathsString = request.getParameter("conceptPaths");
			String gatherAllEncounterFacts = request.getParameter("gatherAllEncounterFacts");

			String[] conceptPathsList = []

			if (resultInstanceID != null && resultInstanceID.length()== 0) { resultInstanceID = null;}
			if (conceptPathsString != null && conceptPathsString.length()== 0) { conceptPathsString = null;}
			if (conceptPathsString != null && conceptPathsString.length()== 0 && gatherAllEncounterFacts!="true" && gatherAllEncounterFacts!="false") { render "Invalid gatherAllEncounterFacts flag, please use true or false." }
			if(gatherAllEncounterFacts==null) gatherAllEncounterFacts = false

			def al = new AccessLog(username:springSecurityService.getPrincipal().username, event:"ClinicalDataController-RetrieveClinicalData", eventmessage:"RID:"+resultInstanceID, accesstime:new java.util.Date())

			if(resultInstanceID == null){render "Invalid Result Instance ID."}
			else if(conceptPathsString == null){render "Invalid Concept Path."}
			else{

				//Split out the concept paths into a list from the delimited string.
				conceptPathsList = conceptPathsString.split("\\|")

				def dataToReturn = clinicalDataService.retrieveClinicalData(resultInstanceID,conceptPathsList,gatherAllEncounterFacts?.toBoolean())

				if(!dataToReturn)
				{
					render "No Data found!"
				}
				else
				{
					render dataToReturn as JSON
				}
			}

		}
		else
		{
			render "You do not have permission to access this URL."
		}

	}

	//21653
	//\Public Studies\GSE13168\Subjects\Cell Phenotype\GFP Expressing (Control)\

}
