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

<div style="background-color:white;height:100%;font: 12px tahoma,verdana,helvetica;text-align:center;">

	<br />
	<br />

	<font style="font: 14px tahoma,verdana,helvetica;color:#800080;font-weight:bold;">Search Filters</font>

	<br />
	<br />

	<table style="width:100%;text-align:left;padding:0;">
			<g:each var="term" in="${termsMap}" >
				<tr>					
					<td style="vertical-align:top;">	
						<g:if test="${term.value['type'] == 'numeric'}">
							<g:render template="categoryRange" model="[termName:term.key, termDisplayName:term.value.displayName]" />
						</g:if>
						<g:else>
							<g:render template="categoryListWithCheckBoxes" model="[termName:term.key,termDisplayName:term.value.displayName,termList:term.value.counts,JSONData:JSONData]" />
						</g:else>					
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>	
			</g:each>		
	</table>	
</div>