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

<div style="background-color:#EEEEEE;height:100%;font: 12px tahoma,verdana,helvetica;text-align:center;">
	<br />
	<br />
	<br />
	<font style="font: 18px tahoma,verdana,helvetica;color:#800080;font-weight:bold;">Select a primary search filter</font>
	<hr style="width:30%;margin-left: auto;margin-right: auto;" />
	<br />
	<br />
	
	<table style="margin-left: auto;margin-right: auto;width:75%;">
		<tr>
			<td style="height:15px;vertical-align:middle;">
				<font style="font-size:150%">Saved filters</font>
			</td>
		</tr>
		<tr>
			<td>
				<br />
				<br />
				<table class = "categoryList" style="width:700px;">
					<g:each var="filter" in="${savedFilters}" status="filterIterator">
					<tr>
						<td>
							<a onClick='loadSearch(${filter.id},toggleMainCategoryFilterComplete);' href="#">${filter.queryName}</a>
						</td>
					</tr>
					</g:each>
				</table>
			</td>
		</tr>
	</table>
	
	<br />
	<br />
	<br />
	
	<table style="margin-left: auto;margin-right: auto;width:75%;">
		<tr>
			<td style="height:15px;vertical-align:middle;">
				<font style="font-size:150%">Multi-Category Filter</font>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>		
		<tr>
			<td>
				<div id="search-categories"></div>
			</td>
		</tr>
		
		<tr>
			<td>
				<div id="search-text"></div>
			</td>
		</tr>
	</table>
	
	
	<br />
	<br />
	<br />
	
	<div style="margin-left: auto;margin-right: auto;width:75%;text-align:left;">
		<font style="font-size:200%">Attribute Filters</font>
		<br />
		<br />
		<input type="button" value="Submit Query" onClick = "toggleGridDisplay()">
	</div>

<br />

<g:each var="group" in="${distinctGroups}" status="iteratorOuter">
	<g:set var="iterator" value="${0}"/>
	<div style="margin-left: auto;margin-right: auto;width:75%;text-align:left;">
		<font style="font-size:150%; font-weight: bold; color: #800080;">${group}</font> 
		<hr style="width:30%;">
	</div>
	
	<br />
	<table style="width:75%;text-align:left;padding:0;margin-left: auto;margin-right: auto;">
			<tr>
				<g:each var="term" in="${termsMap}">
					<g:if test="${term.value['group'] == group }">
					<td style="vertical-align:top;">	
						<g:if test="${term.value['type'] == 'numeric'}">
							<g:render template="categoryRange" model="[termName:term.key, termDisplayName:term.value.displayName]" />
						</g:if>
						<g:elseif test="${term.value['type'] == 'string_autocomplete'}">
							<g:render template="categoryAutocomplete" model="[termName:term.key, termDisplayName:term.value.displayName]" />
						</g:elseif>						
						<g:else>
							<g:render template="categoryListWithCheckBoxes" model="[termName:term.key,termDisplayName:term.value.displayName,termList:term.value.counts,JSONData:JSONData,initialPage:true]" />
						</g:else>
					</td>
						<g:if test="${((iterator+1) % 3) == 0}">
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
						</g:if>
					<g:set var="iterator" value="${iterator + 1 }"/>
					</g:if>
				</g:each>
			</tr>		
	</table>	
	
	<br />
	<br />
</g:each>
</div>