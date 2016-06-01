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
  

import java.text.*;
import com.recomdata.util.ExcelSheet;
import com.recomdata.util.ExcelGenerator;

class HealthStatusController {
	def HealthStatusService
	def index = { redirect(action:list,params:params) }
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	def list = {


		def query_all = HealthStatusService.query_all()
		def healthstats = HealthStatusService.healthstats()
		def query_table_space= HealthStatusService.query_table_space()
		def source_cd = HealthStatusService.source_cd()
		def source_fact = HealthStatusService.source_fact()
		
		if (!params.max) {
			params.max = grailsApplication.config.com.recomdata.admin.paginate.max
		}
	
			render view: 'list', model:[query_all:query_all, healthstats:healthstats,query_table_space:query_table_space,source_cd:source_cd,source_fact:source_fact]
	}
}
