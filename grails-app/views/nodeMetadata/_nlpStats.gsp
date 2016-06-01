<span class="metaDataDialog nodePathTitle">${nodePath}</span>

<br />
<br />

<font style='font-size:12px;font-weight:bold;'>Frequency Statistics</font>

<br /><br />

<div class="metaDataDialog statInformation" style="width: 340px; min-height: 20px;border: 1px solid black;padding:10px;">
	
	<g:each in="${statistics}" status="i" var="currentStatistic">
	
		${columnMap[currentStatistic.key].header} <b>(${columnMap[currentStatistic.key].treeText.replace(":","")})</b> : <b>${currentStatistic.value}</b> <g:if test="${columnMap[currentStatistic.key].fieldType == 'percent'}">%</g:if>
	
		<br />
	
	</g:each>

</div>

<br />

<g:if test="${conceptValidated == 1}">
<font style='font-size:12px;color:blue;'>Concept has been validated by investigator</font><br/><br/>
</g:if>

<font style='font-size:12px;font-weight:bold;'>Sentences</font><br /><br />

<g:form name="metaDataDialogForm">

	<div class="metaDataDialog statInformation" style="width: 340px; min-height: 20px;border: 1px solid black;padding:10px;">

	<g:hiddenField name="concept" value="${concept}"/>

		<g:set var="counter" value="${0}" />

		<g:each in="${gatheredSentenceUsage}" status="patientIterator" var="currentPatient">
			<g:if test="${patientIterator > 0}">
				<hr /> 
			</g:if>
			
			<font style='font-size:12px;font-weight:bold;'>Patient ${patientIterator+1}</font> 
			
			<g:if test="${currentPatient.value.valid == 1}">
				<font style='color:blue;'>
					Patient automatically included by natural language processing					
				</font>
			</g:if>
			<g:else>
				<font style='color:red;'>
					Patient automatically excluded by natural language processing				
				</font>
			</g:else>
			
			
			<br /><br />
			
			<g:each in="${currentPatient.value.list}" status="sentenceIterator" var="currentSentence">

				<g:hiddenField name="patientNum.${counter}" value="${currentSentence.PATIENT_NUM}"/>
				<g:hiddenField name="encounterNum.${counter}" value="${currentSentence.ENCOUNTER_NUM}"/>
				<g:hiddenField name="instanceNum.${counter}" value="${currentSentence.INSTANCE_NUM}"/>
				<g:hiddenField name="originalValue.${counter}" value="${currentSentence.valid}"/>
			
				<g:set var="displaySentence" value="${currentSentence.sentence.replace(currentSentence.highlightWord,'<b>' + currentSentence.highlightWord + '</b>')}" />
				
				<g:checkBox name="checkbox.${counter}" checked="${currentSentence.valid}" />&nbsp;&nbsp;&nbsp;<b>${sentenceIterator+1}</b>
				
				 -
					
				${displaySentence}
				
				<br/>
				
				<g:if test="${currentSentence.valid == 0}">
					<font style='color:red;'>
						${currentSentence.invalidReason}
					</font>
				</g:if>
				
				<br />
				<br />
				
				<g:set var="counter" value="${counter + 1}" /><br/>				
				
			</g:each>
			
			<br />
						
		</g:each>
		
		<g:hiddenField name="count" value="${counter}" />
		
	</div>
	
	<br />
	
	<div class="metaDataDialog statInformation" style="width: 340px; min-height: 20px;">	
		<g:actionSubmit value="Apply Validation" controller="nodeMetaData" action="saveMetaData"/>
		<input type='button' value='Cancel' onclick="closeMetaData();" />
	</div>
</g:form>

<br /><br />

