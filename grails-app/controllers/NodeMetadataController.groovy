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

class NodeMetadataController 
{
	def nodeMetadataService
	
	def nlpStats = 
	{	
		def currentNode 	= params.nodePath
		def nodeDisplayPath = params.nodeDisplayPath

		// Get configuration parameters
		def columnMap 		= grailsApplication.config.nodemetadata.fieldMapping.clone()
		def sentenceModifier 	= grailsApplication.config.nodemetadata.modifierTypeForDetailedUsage
		def observationValidModifier = grailsApplication.config.nodemetadata.observationValidModifier
		def observationInvalidReasonModifier = grailsApplication.config.nodemetadata.observationInvalidReasonModifier
		def patientValidModifier = grailsApplication.config.nodemetadata.patientValidModifier
		def highlightModifier = grailsApplication.config.nodemetadata.highlightModifier
		def conceptValidatedModifier = grailsApplication.config.nodemetadata.conceptValidatedModifier
		
		// Get statistics (patient count etc.) - displayed at top of Apply Validation dialog
		def gatheredStatistics 		= nodeMetadataService.retrieveNodeStatsByConceptPath(currentNode, columnMap)
		
		// Get concept code
		def conceptCD = nodeMetadataService.retrieveConceptCode(currentNode);
		
		// Get CONCEPT_VALIDATED modifier value (=1 if concept was previously validated by investigator)
		def conceptValidated = 0;
		def conceptValidatedValue = nodeMetadataService.retrieveModifierValuesByConcept (conceptCD, [conceptValidatedModifier]);
		if (conceptValidatedValue[conceptValidatedModifier] == "1") {
			conceptValidated = 1
		}		
		
		// Get sentence info
		def gatheredSentenceUsage
		def duration = nodeMetadataService.benchmark {		
			gatheredSentenceUsage 	= nodeMetadataService.retrieveSentenceInformation(conceptCD, sentenceModifier, observationValidModifier, observationInvalidReasonModifier, patientValidModifier, highlightModifier)
		}
		println "TOTAL TIME: " + duration
		
		render(template:'nlpStats', model:[concept : currentNode, nodePath:nodeDisplayPath, statistics:gatheredStatistics, columnMap:columnMap, gatheredSentenceUsage:gatheredSentenceUsage, conceptValidated:conceptValidated])
	}
	
	def saveMetaData = {
		
		def observationValidModifier = grailsApplication.config.nodemetadata.observationValidModifier
		def observationInvalidReasonModifier = grailsApplication.config.nodemetadata.observationInvalidReasonModifier
		def conceptValidatedModifier = grailsApplication.config.nodemetadata.conceptValidatedModifier
		
		def concept = params.concept
		
		// Update OBSERVATION_VALID and OBSERVATION_INVALID_REASON modifiers
		def size = Integer.valueOf(params.count);
		for (int i in 0..(size-1)) {
			def hash = [:]
			hash.CONCEPT_PATH = concept
			hash.PATIENT_NUM = params.patientNum."${i}"
			hash.ENCOUNTER_NUM = params.encounterNum."${i}"
			hash.INSTANCE_NUM = params.instanceNum."${i}"
			def originalValue = params.originalValue."${i}"
			def value = params.checkbox."${i}" == null ? "-1" : "1"
			nodeMetadataService.updateModifierValuesByObservationFact(hash,observationValidModifier,value);
			if (originalValue != "0" && value == "-1") {
				// User invalidated - changed from valid to invalid
				nodeMetadataService.updateModifierValuesByObservationFact(hash,observationInvalidReasonModifier,"USER INVALIDATED");
			}						
		}
		
		// Get concept_cd from concept_path
		def concept_cd = nodeMetadataService.retrieveConceptCode(concept);
		
		// Update PATIENT_VALID modifier
		nodeMetadataService.updatePatientValid(concept_cd);
		
		// Update patient counts 
		nodeMetadataService.updatePatientCount(concept_cd);
		
		// Update CONCEPT_VALIDATED modifier 
		nodeMetadataService.updateModifierValuesByConcept(concept_cd, conceptValidatedModifier, "1");
		
		redirect(controller:'datasetExplorer',action:'index');
	}

def wiki =
	{
		def currentNode = params.nodePath
		def conceptName = nodeMetadataService.retrieveConceptName (currentNode)
		//String cName = ${conceptName.c_name}.replaceAll(/[0-9 ] /,"");
		//String c_name=conceptName.collect {"${it.conceptName}"}
		conceptName.c_name = conceptName.c_name.replaceFirst(/^[0-9]/, "")
		conceptName.c_name = conceptName.c_name.replaceFirst(/^[0-9]/, "")
		def links = nodeMetadataService.retrievelinks()
		render (template:'wiki', model:[conceptName : conceptName,links:links])
	}


}

