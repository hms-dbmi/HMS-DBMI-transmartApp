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
  

/**
 * model details class for the create/edit wizard in the gene signautre module
 */
package com.recomdata.genesignature

import com.recomdata.util.ModelDetails
import bio.ConceptCode
import search.GeneSignature

/**
 * @author jspencer
 * @version
 */
public class WizardModelDetails extends ModelDetails{
	// wizard tyes
	static def WIZ_TYPE_CREATE = 0
	static def WIZ_TYPE_EDIT = 1
	static def WIZ_TYPE_CLONE = 2
	
	// default is create
	def wizardType = WIZ_TYPE_CREATE
	 
	// pick lists
	def sources
	def owners
	def species
	def mouseSources
	def tissueTypes
	def expTypes
	def analyticTypes
	def normMethods
	def analysisMethods
	def schemas
	def pValCutoffs
	def foldChgMetrics
	def platforms
	def compounds
	
	// domain class
	def geneSigInst
	
	// id of domain being edited
	def editId
	def cloneId
	
	/**
	 * add an emtpy other ConceptCode item
	 */
	public static void addOtherItem(List<ConceptCode> items, String optionId) {			
		if(optionId==null) optionId="other"
		items.add(new ConceptCode(bioConceptCode: optionId, codeName: "other"))
	}
	
}
