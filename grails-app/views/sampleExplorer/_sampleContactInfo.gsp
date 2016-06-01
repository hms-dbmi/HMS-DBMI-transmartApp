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

<g:set var="sectionCounter" value="0" />

<g:each var="contact" in="${allSamplesByContact}">
	
	<div style="font-size:1.5em;text-align:center;padding:10px;">
		<g:if test="${contact.key == 'NO_CONTACT'}">
     		Contact Information unavailable for these samples.
		</g:if>
		<g:else>
			Contact E-Mail : <a href="mailto:${contact.key}?Subject=Sample Information&body=Sample%20IDs%20${contactSampleIdMap[contact.key].join(',')}" target="_top">${contact.key}</a>
		</g:else>
	</div>

	<br />
	
	&nbsp;&nbsp;&nbsp;<a href="#" onclick="toggleContactSampleSection(document.getElementById('sampleSection${sectionCounter}'))">Toggle Sample Information</a>
	
	&nbsp;&nbsp;&nbsp;Sample ID List - <input type="text" value="${contactSampleIdMap[contact.key].join(',')}" />
	
	<div id="divSample${sectionCounter}" class="detailedSampleInformation">
		<div id="sampleSection${sectionCounter}" style="display:none;">
			<g:each var="sample" in="${contact.value}">
				<table class="biobankResults">
				<g:each var="sampleAttribute" in="${sample}">
					<g:if test="${sampleAttribute.key != 'count'}">
					<tr>
						<th>
							${columnPrettyNameMapping[sampleAttribute.key]}
						</th>
						<td style='vertical-align:center;border-color:black;border-width:1px;border-style:solid;padding: 3px;'>
							${sampleAttribute.value}
						</td>
					</tr>
					</g:if>
				</g:each>
				</table>
				
				<br />
				<br />
				
			</g:each>	
		</div>	
	</div>	
	
	<g:set var="sectionCounter" value="${sectionCounter + 1}" />
			
</g:each>



