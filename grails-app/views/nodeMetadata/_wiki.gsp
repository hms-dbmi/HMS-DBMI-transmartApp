
<html>
<body>
    <SCRIPT language="JavaScript"> 

    

 </SCRIPT>      
        
<style>

/* unvisited link */
a:link {
    color: #FF0000;
    size:14px;
    family:Brushstroke, fantasy;
    style:italic;
    text-decoration: none;
}

a:visited {
    color: #FF0000;
}
/* mouse over link */
a:hover {
    color: #0000FF;
}



</style>
<div class="metaDataDialog statInformation" style="background-color:#FFE4B5;overflow: auto;max-width: 500px; min-height: 10px;padding:10px;">
	<font style='font-size:18px;font-weight:bold;'>Concept Name:</font>
<font style='font-weight:bold;'>${conceptName.c_name}</font>	
<hr>

   <g:if test="${links}">
<br />
   </g:if>

     
            
        
       <g:each in="${links}" status="i" var="thisRecord">       
       
              
          <a  target="_blank" href="${thisRecord.URL}${conceptName.c_name}" ><font style='font-size:14px;font-weight:bold;'>${i+1}. ${thisRecord.NAME}</font></a>          
            <br />
     
      </g:each>
    </div>
    <div class="metaDataDialog statInformation" style="background-color:#FFE4B5;overflow: auto;max-width: 500px; min-height: 10px;">
	
		<input type='button' value='Close' onclick="closeMetaData();" />	
	
	</div> 
 

    
  
  <br/>
  
   </body>
 </html>