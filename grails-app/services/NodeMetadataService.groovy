import i2b2.Concept

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

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
  
class NodeMetadataService{

	def dataSource
	
	def benchmark = { closure ->
		def start = System.currentTimeMillis()
		closure.call()
		def now = System.currentTimeMillis()
		return (now - start) / 1000
	}
	def retrievelinks()
	{
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		def rows = sql.rows("select * from biomart.web_links order by id asc")
		return rows
	}
	def retrieveNodeStats(parentNode, columnMap)
	{
		def statsList = [:]
		
		def metaDataNodes = NodeMetadata.findAllByParentConceptPath(parentNode)
		
		metaDataNodes.each()
		{
				currentNode -> 
				
				def valueText = currentNode.dataValue;

				if(columnMap[currentNode.dataType])
				{
					if(columnMap[currentNode.dataType]?.fieldType == 'percent') valueText += "%"
					
					if(statsList[currentNode.conceptPath])
					{
						statsList[currentNode.conceptPath].put(columnMap[currentNode.dataType].treeText,valueText)
					}
					else
					{
						statsList[currentNode.conceptPath] = [:]
						statsList[currentNode.conceptPath].put(columnMap[currentNode.dataType].treeText,valueText)
					}
				}
		}
		
		
		return 	statsList;
	}

	def retrieveNodeStatsByConceptPath(currentNode, columnMap)
	{
		def statsList = [:]
		
		def metaDataNodes = NodeMetadata.findAllByConceptPath(currentNode)
		
		metaDataNodes.each()
		{			
			currentMetadataNode -> 			
			if(columnMap[currentMetadataNode.dataType])
			{
				statsList.put(currentMetadataNode.dataType,currentMetadataNode.dataValue)
			}
		}
		
		return 	statsList;
	}

	def retrieveSentenceInformation (conceptCD, sentenceModifier, validModifier, invalidReasonModifier, patientValidModifier, highlightModifier)
	{
		def patientList 		= [:]
		
		def modifiers = [];
		modifiers.push(sentenceModifier,);
		modifiers.push(validModifier);
		modifiers.push(invalidReasonModifier);
		modifiers.push(highlightModifier);

		def facts = retrieveModifierValuesGroupByInstanceNum(conceptCD, modifiers);
		facts.each { instanceNum, instanceNumFacts ->

			def hash = retrieveSentenceInformationHelper (instanceNumFacts, sentenceModifier, validModifier, invalidReasonModifier, highlightModifier);
			
			// Add to patient list
			if(!patientList[hash.PATIENT_NUM])
			{
				patientList[hash.PATIENT_NUM] = [:]
				patientList[hash.PATIENT_NUM].list = []
			}
			patientList[hash.PATIENT_NUM].list.push (hash)
		}
		
		// Check which patients are valid, by PATIENT_VALID modifier
		patientList.each{ patient, hash ->
			// Check if patient is valid
			if (patientValidModifier != null) {
				def value = retrieveModifierValuesByConceptAndPatient (conceptCD, patient, [patientValidModifier])
				if (value != null) {
					def valid = value[patientValidModifier];
					hash.valid = (valid == null || !valid.isInteger())? 1 : (valid.toInteger() > 0)?1:0
				}
			}
			else {
				hash.valid = 1
			}
		}
				
		return 	patientList;		
	}

	
	def retrieveModifierValuesGroupByInstanceNum(conceptCD, modifiers)
	{
		def facts = [:]		
		def placeholders = []		
		def parameters = [conceptCD]
		modifiers.each { modifier -> placeholders << '?'; parameters << modifier }		
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);				
		def rows = sql.rows(""" SELECT OBSF.TVAL_CHAR, OBSF.OBSERVATION_BLOB, OBSF.PATIENT_NUM, OBSF.INSTANCE_NUM, OBSF.ENCOUNTER_NUM, OBSF.MODIFIER_CD, OBSF.CONCEPT_CD
								FROM OBSERVATION_FACT OBSF
								WHERE OBSF.CONCEPT_CD = ?
								AND   OBSF.MODIFIER_CD IN (${placeholders.join(',')})""", parameters);
																
		rows.each {
			row ->			
				def modifier = row.MODIFIER_CD;
				def instanceNum = row.INSTANCE_NUM;				
				if (!facts[instanceNum]) {
					facts[instanceNum] = [:]
				}
				facts[instanceNum][modifier] = row;								
		}
		return 	facts;
	}

	def retrieveModifierValuesGroupByInstanceNumStandardSQL(conceptCD, modifiers)
	{
		def facts = [:]
		def placeholders = []
		Connection connection = dataSource.getConnection();
		modifiers.each { modifier -> placeholders << '?' }
		def SQL_QUERY = """ SELECT OBSF.TVAL_CHAR, OBSF.OBSERVATION_BLOB, OBSF.PATIENT_NUM, OBSF.INSTANCE_NUM, OBSF.ENCOUNTER_NUM, OBSF.MODIFIER_CD, OBSF.CONCEPT_CD
								FROM OBSERVATION_FACT OBSF
								WHERE OBSF.CONCEPT_CD = ?
								AND   OBSF.MODIFIER_CD IN (${placeholders.join(',')})""";
		PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
		statement.setString(1, conceptCD);
		modifiers.eachWithIndex { modifier, i -> statement.setString(i+2, modifier) }
		ResultSet resultSet = statement.executeQuery();		
		while (resultSet.next()) {
			def modifier = resultSet.getString('MODIFIER_CD');
			def instanceNum = resultSet.getString('INSTANCE_NUM');
			def row = [
				'TVAL_CHAR':resultSet.getString('TVAL_CHAR'),
				'OBSERVATION_BLOB' :resultSet.getClob('OBSERVATION_BLOB'),
				'PATIENT_NUM'  :resultSet.getInt('PATIENT_NUM'),
				'ENCOUNTER_NUM'  :resultSet.getInt('ENCOUNTER_NUM'),
				'INSTANCE_NUM'  :instanceNum,
				'MODIFIER_CD'  :modifier,
				'CONCEPT_CD'  :conceptCD
			];
			if (!facts[instanceNum]) {
				facts[instanceNum] = [:]
			}
			facts[instanceNum][modifier] = row;
		}						
		return 	facts;
	}

	
	def retrieveSentenceInformationHelper (instanceNumFacts, sentenceModifier, validModifier, invalidReasonModifier, highlightModifier) {
		def hash = [:]
		
		def sentenceFact = instanceNumFacts[sentenceModifier]
		
		hash.PATIENT_NUM = sentenceFact.PATIENT_NUM
		hash.ENCOUNTER_NUM = sentenceFact.ENCOUNTER_NUM
		hash.INSTANCE_NUM = sentenceFact.INSTANCE_NUM
		
		hash.sentence = sentenceFact?.TVAL_CHAR

		// Highlight
		def highlightWord = instanceNumFacts[highlightModifier]?.TVAL_CHAR
		if (!highlightWord) highlightWord = ""
		hash.highlightWord = highlightWord

		// Validity							
		hash.valid = 1		
		if (validModifier != null) {
			def valid = instanceNumFacts[validModifier]?.TVAL_CHAR
			// If no validation information, treat as valid (since we have no information to indicate otherwise)
			// Otherwise if modifier value is > 0 treat as valid, otherwise invalid
			// (Validity and polarity values are stored in DB as -1/1)
			hash.valid = (valid == null || !valid.isInteger())? 1 : (valid.toInteger() > 0)?1:0			
			if (hash.valid<=0 && invalidReasonModifier != null) {
				def invalidReason = instanceNumFacts[invalidReasonModifier].TVAL_CHAR
				hash.invalidReason = invalidReason
			}
		}

		return hash;
	}
		def retrieveConceptName(currentNode)
	{
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		
		def row= sql.firstRow(" SELECT c_name FROM I2B2METADATA.I2B2 WHERE C_FULLNAME= ?",[currentNode]);
		return row;
		
	}
	def retrieveConceptsWithPositiveModifier (Concept parent, modifier) {
		def concepts = []
		def childFullName = parent.getFullName() + "%"
		def childLevel = parent.getLevel().intValue() + 1
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		def rows = sql.rows("""
				select CD.CONCEPT_PATH from
				(
					select OBSF.CONCEPT_CD from OBSERVATION_FACT OBSF
					join CONCEPT_DIMENSION CD on OBSF.CONCEPT_CD=CD.CONCEPT_CD
					join i2b2metadata.i2b2 I2B2 on I2B2.C_FULLNAME = CD.CONCEPT_PATH
					where I2B2.C_FULLNAME like ? and I2B2.C_HLEVEL=? and OBSF.MODIFIER_CD=? and OBSF.TVAL_CHAR='1'
				) T
				join CONCEPT_DIMENSION CD on T.CONCEPT_CD=CD.CONCEPT_CD
				""",
				[childFullName, childLevel, modifier]);
		rows.each { 
			row ->
			concepts.push(row.CONCEPT_PATH);
		}
		return concepts;
	}

	// Retrieve modifier for concept (encounter_num and patient_num are null, instance_num = 1)
	def retrieveModifierValuesByConcept (conceptCD, modifiers) {
		
		def values 		= [:]
		
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
				
		modifiers.each() {
			
			currentModifier ->
			
			def row = sql.firstRow(""" SELECT TVAL_CHAR, OBSERVATION_BLOB, PATIENT_NUM
									FROM OBSERVATION_FACT OBSF
									WHERE
										OBSF.PATIENT_NUM is null
										AND OBSF.ENCOUNTER_NUM is null
										AND OBSF.INSTANCE_NUM = 1 
										AND OBSF.CONCEPT_CD = ?
										AND OBSF.MODIFIER_CD = ?""", 
									[
										conceptCD,
										currentModifier
									])
			if (row != null) {
				values[currentModifier] = row.TVAL_CHAR
			}
		}
		
		return 	values;
	}
	
	// Retrieve modifier for concept (encounter_num is null)
	def retrieveModifierValuesByConceptAndPatient (conceptCD, patient, modifiers) {
		
		def values 		= [:]
		
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
				
		modifiers.each() {
			
			currentModifier ->
			
			def row = sql.firstRow(""" SELECT TVAL_CHAR, OBSERVATION_BLOB, PATIENT_NUM
										FROM OBSERVATION_FACT OBSF
										WHERE
											OBSF.ENCOUNTER_NUM is null
											AND OBSF.INSTANCE_NUM = 1 
											AND OBSF.CONCEPT_CD = ?
											AND OBSF.PATIENT_NUM = ?
											AND OBSF.MODIFIER_CD = ?""", 
										[
											conceptCD,
											patient,
											currentModifier
										])
			if (row != null) {
				values[currentModifier] = row.TVAL_CHAR
			}
		}		
		return 	values;
	}

	def retrieveConceptCode (concept_path) {
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		def row = sql.firstRow("select CONCEPT_CD from CONCEPT_DIMENSION where CONCEPT_PATH = ?", [concept_path]);
		return row.CONCEPT_CD;
	}

	def retrieveParentPath (String concept_path) {		
		int endOfParent = concept_path.lastIndexOf ("\\", concept_path.size()-2);
		return concept_path.substring (0, endOfParent+1);
	}

	def updatePatientValid (concept_cd) {
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);		
		sql.call("{call i2b2demodata.PATIENT_VALID_FOR_CONCEPT(?)}", [concept_cd]);
	}
		
	def updatePatientCount (concept_cd) {
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		sql.call("{call i2b2demodata.PATIENT_COUNT_FOR_CONCEPT(?)}", [concept_cd]);
	}
	
	def updateModifierValuesByObservationFact (observationFact, modifierName, modifierValue) {
		
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		
		// Update modifier for observation fact
		// If modifier doesn't exist yet - create it,
		// otherwise update value
		
		sql.executeUpdate(""" MERGE INTO OBSERVATION_FACT obsf
USING (SELECT ? ENCOUNTER_NUM, ? PATIENT_NUM, CONCEPT_CD, ? INSTANCE_NUM, ? MODIFIER_CD
from CONCEPT_DIMENSION CD WHERE CD.CONCEPT_PATH = ?
) u
ON (obsf.ENCOUNTER_NUM = u.ENCOUNTER_NUM and obsf.PATIENT_NUM = u.PATIENT_NUM and obsf.CONCEPT_CD = u.CONCEPT_CD and obsf.INSTANCE_NUM = u.INSTANCE_NUM and obsf.MODIFIER_CD = u.MODIFIER_CD) 
WHEN MATCHED THEN UPDATE SET obsf.TVAL_CHAR = ?
WHEN NOT MATCHED THEN INSERT (PROVIDER_ID, ENCOUNTER_NUM, PATIENT_NUM, CONCEPT_CD, INSTANCE_NUM, MODIFIER_CD, VALTYPE_CD, TVAL_CHAR) VALUES (0, u.ENCOUNTER_NUM, u.PATIENT_NUM, u.CONCEPT_CD, u.INSTANCE_NUM, u.MODIFIER_CD, 'T', ?)
""",		
						[
							observationFact.ENCOUNTER_NUM,
							observationFact.PATIENT_NUM,
							observationFact.INSTANCE_NUM,
							modifierName,
							observationFact.CONCEPT_PATH,
							modifierValue,
							modifierValue
						]);
		
	}
	
	def updateModifierValuesByConcept (conceptCD, modifierName, modifierValue) {
		
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		
		
		// Update modifier for concept (encounter_num and patient_num are null)
		// If modifier doesn't exist yet - create it,
		// otherwise update value
		
		sql.executeUpdate(""" MERGE INTO OBSERVATION_FACT obsf
USING (SELECT ? CONCEPT_CD, ? MODIFIER_CD from dual) u
ON (obsf.ENCOUNTER_NUM is null and obsf.PATIENT_NUM is null and obsf.CONCEPT_CD = u.CONCEPT_CD and obsf.INSTANCE_NUM = 1 and obsf.MODIFIER_CD = u.MODIFIER_CD) 
WHEN MATCHED THEN UPDATE SET obsf.TVAL_CHAR = ?
WHEN NOT MATCHED THEN INSERT (PROVIDER_ID, ENCOUNTER_NUM, PATIENT_NUM, CONCEPT_CD, INSTANCE_NUM, MODIFIER_CD, VALTYPE_CD, TVAL_CHAR) VALUES (0, null, null, u.CONCEPT_CD, 1, u.MODIFIER_CD, 'T', ?)
""",		
						[
							conceptCD,
							modifierName,
							modifierValue,
							modifierValue
						]);
		
	}

	def updateNodeMetadata (conceptPath, type, value) {
				
		// Get parent path
		def parentPath = retrieveParentPath(conceptPath);
				
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);		
		
		// Update node metadata 
		// If metadata for concept+type doesn't exist yet - create it,
		// otherwise update value
		
		sql.executeUpdate(""" MERGE INTO NODE_METADATA nomd
USING (SELECT ? CONCEPT_PATH, ? PARENT_CONCEPT_PATH, ? TYPE from dual) u
ON (nomd.CONCEPT_PATH = u.CONCEPT_PATH and nomd.PARENT_CONCEPT_PATH = u.PARENT_CONCEPT_PATH and nomd.TYPE = u.TYPE) 
WHEN MATCHED THEN UPDATE SET nomd.VALUE = ?
WHEN NOT MATCHED THEN INSERT (CONCEPT_PATH, PARENT_CONCEPT_PATH, TYPE, VALUE) VALUES (u.CONCEPT_PATH, u.PARENT_CONCEPT_PATH, u.TYPE, ?)
""",		
						[
							conceptPath,
							parentPath,
							type,
							value,
							value
						]);
		
	}

}
