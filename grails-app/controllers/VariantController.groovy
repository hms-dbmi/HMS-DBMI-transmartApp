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
 import edu.hms.transmart.security.AuthUser
 
class VariantController {

	def variantService
	def i2b2HelperService
	def springSecurityService
	
    def index = { }
	
	def basicVariantStatistics = {
		
		def result_instance_id1=params.result_instance_id1
		def result_instance_id2=params.result_instance_id2
		
		def resultInstanceArray = []
		
		resultInstanceArray.push(result_instance_id1)
		resultInstanceArray.push(result_instance_id2)

		StringWriter writer1 	= new StringWriter();
		PrintWriter pw1			= new PrintWriter(writer1);

		StringWriter writer2 	= new StringWriter();
		PrintWriter pw2			= new PrintWriter(writer2);
		
		StringWriter writer3 	= new StringWriter();
		PrintWriter pw3			= new PrintWriter(writer3);
		
		if(result_instance_id1){i2b2HelperService.renderQueryDefinition(result_instance_id1,"Query Summary for Subset 1", pw1);}
		if(result_instance_id2){i2b2HelperService.renderQueryDefinition(result_instance_id2, "Query Summary for Subset 2", pw2);}
	
		variantService.renderPatientCountInfoTable(result_instance_id1, result_instance_id2, pw3);
		
		render(template:"basicStatistics", model:[queryDefinition1:writer1.toString(),queryDefinition2:writer2.toString(), patientCountInfoTable:writer3.toString()]);
	}
	
}
