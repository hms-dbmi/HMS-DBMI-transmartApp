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
  

package search

/**
 * domain class for auto refresh materialized view in SearchApp (for gene signature items)
 */
class SearchBioMarkerCorrelFastMV {
	
	// correlation types
	static def GENE_CORREL_TYPE = "GENE"	 
	static def PATHWAY_CORREL_TYPE = "PATHWAY_GENE"
	static def GENE_SIG_CORREL_TYPE = "GENE_SIGNATURE_ITEM"
	 
	Long id
	Long domainObjectId 
	Long assocBioMarkerId
	String correlationType
	Long valueMetric 
	
	static mapping = {
		table 'SEARCH_BIO_MKR_CORREL_FAST_MV'
		version false
		columns {
			id column:'MV_ID'
			domainObjectId column:'DOMAIN_OBJECT_ID'
			assocBioMarkerId column:'ASSO_BIO_MARKER_ID'
			correlationType column:'CORREL_TYPE'
			valueMetric column:'VALUE_METRIC'
		}
	}
	
	static constraints = {
	}
}