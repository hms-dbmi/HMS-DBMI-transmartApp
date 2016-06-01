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
  


import i2b2.Concept

class ConceptService {
	def dataSource;
	
	public Concept getConceptByBaseCode(String baseCode) throws Exception {
		return Concept.findByBaseCode(baseCode);
	}
	
	public List<Concept> getChildrenConcepts(Concept parent) throws Exception {
		if (parent == null || parent.id == null || parent.getLevel() == null) return null;
		def childFullName = parent.getFullName() + "%"
		def childLevel = parent.getLevel().intValue() + 1
		print "child full name: " + childFullName
		print "child level: " + childLevel
		List<Concept> conceptList = 
			Concept.findAll("from Concept as c where c.fullName like :fullNameLike and level = :levelNew",
				[fullNameLike: childFullName, levelNew: 4]);
		return conceptList;
	}
	
	public List<String> getChildrenConceptsJDBC (Concept parent) throws Exception {
		
		def children = []
		
		if (parent == null || parent.id == null || parent.getLevel() == null) return null;
		def childFullName = parent.getFullName() + "%"
		def childLevel = parent.getLevel().intValue() + 1

		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		def rows = sql.rows(""" SELECT C_BASECODE baseCode, C_FULLNAME fullName
							FROM i2b2metadata.i2b2 
							WHERE
							C_FULLNAME like ?
							AND
							C_HLEVEL = ?""", 
			[
				childFullName,
				childLevel
			]
		)		
		return rows
	}
	
}
