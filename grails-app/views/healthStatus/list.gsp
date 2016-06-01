<!--
  tranSMART - translational medicine data mart
  
  Copyright 2008-2012 Janssen Research & Development, LLC.
  
  This product includes software developed at Janssen Research & Development, LLC.
  
  This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
  as published by the Free Software  * Foundation, either version 3 of the License, or (at your option) any later version, along with the following terms:
  1.	You may convey a work based on this program in accordance with section 5, provided that you retain the above notices.
  2.	You may convey verbatim copies of this program code as you receive it, in any medium, provided that you retain the above notices.
  
  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS    * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
  
 
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="admin" />
		<title>View Health Status</title>
	</head>

	<body>
	<br />
	
	<div class="body">
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			    <font style='font-size:12px;font-weight:bold;'>1. Count of Patients, Concepts and Facts </font>
			
			<div class="list">
				<table>
				<thead>
					<tr>
						<g:sortableColumn property="C_FULLNAME" title="C_FULLNAME" />
						<g:sortableColumn property="DISTINCT_PATIENTS" title="DISTINCT_PATIENTS" />
						<g:sortableColumn property="DISTINCT_CONCEPTS" title="DISTINCT_CONCEPTS" />
						<g:sortableColumn property="NUMBER_OF_FACTS" title="NUMBER_OF_FACTS" />
					
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${query_all}" status="i" var="thisRecord">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td>${thisRecord.C_FULLNAME}</td>
						<td>${thisRecord.DISTINCT_PATIENTS}</td>
						<td>${thisRecord.DISTINCT_CONCEPTS}</td>
						<td>${thisRecord.NUMBER_OF_FACTS}</td>
						
					</tr>
				</g:each>
				</tbody>
				</table>
			</div>
<br /> <br />
    <font style='font-size:12px;font-weight:bold;'>2. Size of Table and its Indexes</font>

	<div class="list">
				<table style="width:200px">
				<thead>
					<tr>
						<g:sortableColumn property="owner" title="OWNER" />
						<g:sortableColumn property="GB" title="GB" />
					
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${healthstats}" status="i" var="thisRecord">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td>${thisRecord.owner}</td>
						<td>${thisRecord.GB}</td>
						
					</tr>
				</g:each>
				</tbody>
				</table>
			</div>
			<br /> <br />
			    <font style='font-size:12px;font-weight:bold;'>3. Size of TableSpace</font>
			
	<div class="list">
				<table style="width:500px">
				<thead>
					<tr>
						<g:sortableColumn property="TABLESPACE" title="TABLESPACE" />
						<g:sortableColumn property="USED_GB" title="USED_GB" />
											<g:sortableColumn property="FREE_GB" title="FREE_GB" />
											<g:sortableColumn property="TOTAL_GB" title="TOTAL_GB" />
																<g:sortableColumn property="PCT_FREE" title="PCT_FREE" />
					
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${query_table_space}" status="i" var="thisRecord">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td>${thisRecord.TABLESPACE}</td>
						<td>${thisRecord.USED_GB}</td>
												<td>${thisRecord.FREE_GB}</td>
												<td>${thisRecord.TOTAL_GB}</td>
												<td>${thisRecord.PCT_FREE}</td>
						
					</tr>
				</g:each>
				</tbody>
				</table>
			</div>
			<br /> <br />
			    <font style='font-size:12px;font-weight:bold;'>4. Concept_Dimension table:</font>
			
				<div class="list">
				<table style="width:500px">
				<thead>
					<tr>
						<g:sortableColumn property="SOURCESYSTEM_CD" title="SOURCESYSTEM_CD" />
						<g:sortableColumn property="COUNT_OF_CONCEPTS" title="COUNT_OF_CONCEPTS" />
										
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${source_cd}" status="i" var="thisRecord">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td>${thisRecord.SOURCESYSTEM_CD}</td>
						<td>${thisRecord.COUNT_OF_CONCEPTS}</td>
						
					</tr>
				</g:each>
				</tbody>
				</table>
			</div>
					<br /> <br />
			    <font style='font-size:12px;font-weight:bold;'>5. Observation_fact table:</font>
			
	
				<div class="list">
				<table style="width:500px">
				<thead>
					<tr>
						<g:sortableColumn property="SOURCESYSTEM_CD" title="SOURCESYSTEM_CD" />
						<g:sortableColumn property="nb_observation_fact" title="NB_OBSERVATION_FACT" />
						<g:sortableColumn property="distinct_patient_num" title="DISTINCT_PATIENT_NUM" />
						<g:sortableColumn property="distint_CONCEPT_CD" title="DISTINCT_CONCEPT_CD" />
						<g:sortableColumn property="min_start_date" title="MIN_START_DATE" />
						<g:sortableColumn property="max_start_date" title="MAX_START_DATE" />
										
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${source_fact}" status="i" var="thisRecord">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td>${thisRecord.SOURCESYSTEM_CD}</td>
						<td>${thisRecord.nb_observation_fact}</td>
												<td>${thisRecord.distinct_patient_num}</td>
												<td>${thisRecord.distint_CONCEPT_CD}</td>
												<td>${thisRecord.min_start_date}</td>
												<td>${thisRecord.max_start_date}</td>
						
					</tr>
				</g:each>
				</tbody>
				</table>
			</div>
				<br /> <br />
		</div>
	
	</body>
</html>