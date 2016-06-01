<table class="subsettable" border="0px"  style="margin:10px">

  <tr>
  	<td align="center">
  		<h1><u>Subset 1</u></h1>
  	</td>
  	<td id="subsetdivider" rowspan="3" valign="center" align="center"  height="100%">
  		<div style="margin:15px;border:1px solid black;background:black;width:1px; height:150px"></div>
  	</td>
 	<td align="center">
 		<h1><u>Subset 2</u></h1>
 	</td>
 </tr>  

<g:each var="outerCounter" in="${(1..20)}">
	<tr id="qcr${outerCounter}">
	<g:each var="innerCounter" in="${(1..2)}">
		<td align="right">
			<g:if test="${outerCounter > 1 }">
				AND&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			</g:if>
			<button id="btnExcludeGroup${innerCounter}_${outerCounter}" style="font:9pt tahoma;" onclick="excludeGroup(this, '${innerCounter}','${outerCounter}')">Exclude</button>
			<button id="btnEncounterGroup${innerCounter}_${outerCounter}" style="font:9pt tahoma;" onclick="linkedPanelToggle(this, '${innerCounter}','${outerCounter}')">Enable Variant Panel</button>
			<button id="clearGroup${innerCounter}_${outerCounter}" style="font:9pt tahoma;" onclick="clearGroup('${innerCounter}','${outerCounter}')">X</button><br>
			<div id='queryCriteriaDiv${innerCounter}_${outerCounter}' class="queryGroupInclude queryGroupAnyTiming"></div>
		</td>		
	</g:each>
	</tr>
</g:each>

	<tr>
		<td id='relationdivider' colspan='3' valign="center" align = "center">
		<div style="margin:15px;border:1px solid black;background:black;width:150px; height:1px"></div>
		</td>
	</tr>
	<tr>
		<td align=center colspan='3'>
			<h1><u>Relation between Subset 1 and Subset 2</u></h1>
		</td>	
	</tr>
	<tr>
		<td align=center colspan = '3'>
			<button id="btnExcludeGroup3_1" style="font:9pt tahoma; display:none" onclick="excludeGroup(this, '3','1')" disabled >Exclude</button>
			<button id="btnEncounterGroup3_1" style="font:9pt tahoma; display:none" onclick="linkedPanelToggle(this, '3','1')" disabled>Enable Variant Panel</button>
			<button id="clearGroup3_1" style="font:9pt tahoma; margin-left:315px" onclick="clearGroup('3','1')" >X</button><br>
			<div id='queryCriteriaDiv3_1' class="queryGroupInclude queryGroupAnyTiming"></div>
		</td>
	</tr>

</table>



<!--    
  <tr id="qcr20"><td align="right">AND&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button id="btnExcludeGroup1_20" style="font:9pt tahoma;" onclick="excludeGroup(this, '1','20')">Exclude</button><button id="clearGroup1_20" style="font:9pt tahoma;" onclick="clearGroup('1','20')">X</button><br>
  <div id='queryCriteriaDiv1_20' class="queryGroupInclude"></div></td>
  <td align="right">AND&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button id="btnExcludeGroup2_20" style="font:9pt tahoma;" onclick="excludeGroup(this, '2','20')">Exclude</button>
  <button id="clearGroup2_20" style="font:9pt tahoma;" onclick="clearGroup('2','20')">X</button><br>
  <div id='queryCriteriaDiv2_20' class="queryGroupInclude"></div>
  
  </td></tr>  
  </table>
 -->
 
  <div id="hiddenDragDiv" style="display:none"></div>
  <script type="text/javascript">
  	setupDragAndDrop();
  	hideCriteriaGroups();
  </script>
 