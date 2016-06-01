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
  



class HealthStatusService {
	def dataSource
   static transactional = true
def query_all()
{
	groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
	def rows = sql.rows(" select * from biomart.compute_stats order by number_of_facts desc ")
	return rows
}

def source_cd()
{
	groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
	def rows = sql.rows("select * from biomart.concept_stats order by count_of_concepts desc")
 return rows
}

    def source_fact()
    {
	groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
	def rows = sql.rows(" select * from BIOMART.OBSERVATION_STATS order by nb_observation_fact desc")
	return rows
	}

  
	def query_table_space()
	{
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);
		def rows = sql.rows(""" select df.tablespace_name as TABLESPACE,
totalusedspace/1000 as USED_GB,
(df.totalspace - tu.totalusedspace)/1000 as FREE_GB,
df.totalspace/1000 as TOTAL_GB,
round(100 * ( (df.totalspace - tu.totalusedspace)/ df.totalspace)) as
PCT_FREE
from
(select tablespace_name,
round(sum(bytes) / 1048576) TotalSpace
from dba_data_files 
group by tablespace_name) df,
(select round(sum(bytes)/(1024*1024)) totalusedspace, tablespace_name
from dba_segments 
group by tablespace_name) tu
where df.tablespace_name = tu.tablespace_name 
and TU.TABLESPACE_NAME in 
('BIOMART',
'TRANSMART',
'DEAPP',
'I2B2_DATA',
'SEARCHAPP', 
'INDX')
order by df.tablespace_name
""")
			 return rows
		 
	}
	def healthstats()
	{
	
		def schemaList 		= [:]
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource);

		
		def rows = sql.rows(""" select * from biomart.schema_stats order by GB desc""")
		return rows
	}
}
