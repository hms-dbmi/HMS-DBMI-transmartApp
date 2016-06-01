/***
* transmart application configuration settings
*/

/* Configuration for search */
// Lucene index location for documentation search - this is a absolute path on your local deployment
com.recomdata.searchengine.index = ""

com.recomdata.searchtool.contactUs="mailto:test@test.com"

// relative context path to dataset explorer url
com.recomdata.searchtool.datasetExplorerURL="/transmart/datasetExplorer"

// absolute path to online help system
com.recomdata.searchtool.adminHelpURL=""

// application title 
com.recomdata.searchtool.appTitle=""

// application logo to be used in the login page
com.recomdata.searchtool.largeLogo="transmartlogo.jpg"

// application logo to be used in the search page
com.recomdata.searchtool.smallLogo="transmartlogosmall.jpg"

com.recomdata.hideSearch='true'



/* Configuration for logins */
com.recomdata.guestAutoLogin='false'
com.recomdata.guestUserName='guest'



/* Configuration for dataset explorer */
com.recomdata.datasetExplorer.pmServiceURL = "http://somehost:9090/i2b2/rest/PMService/"

// turn proxy on if the pm cell deployed on a different server or not through the apache proxy this meets the javascript same origin policy
com.recomdata.datasetExplorer.pmServiceProxy='true'

// deprecated - leave it as false
com.recomdata.datasetExplorer.inforsense='false'

// set to true to enable gene pattern integration 
com.recomdata.datasetExplorer.genePatternEnabled = 'false'

// The tomcat URL that gene pattern is deployed within -usually it's proxyed through apache
com.recomdata.datasetExplorer.genePatternURL=''

// Gene Pattern real URL with port number
com.recomdata.datasetExplorer.genePatternRealURLBehindProxy=''

// default Gene pattern user to start up - each tranSMART user will need a separate user account to be created in Gene Pattern
com.recomdata.datasetExplorer.genePatternUser='biomart'

// temporary image directories for analyses results 
com.recomdata.datasetExplorer.imageTempDir='/images/datasetExplorer'

// Absolute path to PLINK executables
com.recomdata.datasetExplorer.plinkExcutable = '/usr/local/bin/plink'

// Metadata view
com.recomdata.view.studyview='studydetail'


/* Configuration for plugins */

//This is the directory to the R plugins.
com.recomdata.plugins.pluginScriptDirectory = "/Rmodules1_0/"

//This is the main temporary directory, under this should be the folders that get created per job.
com.recomdata.plugins.tempFolderDirectory = "/var/tmp/jobs/"

//Use this to do local development.  It causes the analysis controllers to move the image file before rendering it.
com.recomdata.plugins.transferImageFile = true

//This is the system path where we move the image file to so we can serve it.
com.recomdata.plugins.temporaryImageFolder = "/web-app/images/tempImages/"

//This is the path that we use to render the image.
com.recomdata.plugins.analysisImageURL = "/transmartApp/images/tempImages/"

com.recomdata.transmart.data.export.rScriptDirectory = "dataExportRScripts"

/* configuration for Spring Security Core Plugin */
grails.plugins.springsecurity.userLookup.userDomainClassName = 'edu.hms.transmart.security.AuthUser'
grails.plugins.springsecurity.userLookup.passwordPropertyName = 'passwd'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'edu.hms.transmart.security.AuthUser'
grails.plugins.springsecurity.authority.className = 'Role'
grails.plugins.springsecurity.requestMap.className = 'Requestmap'
grails.plugins.springsecurity.securityConfigType = grails.plugins.springsecurity.SecurityConfigType.Requestmap
grails.plugins.springsecurity.successHandler.defaultTargetUrl = '/userLanding'
grails.plugins.springsecurity.logout.afterLogoutUrl='/'
grails.plugins.springsecurity.password.algorithm = 'SHA'
grails.plugins.springsecurity.errors.login.exceeded='Sorry, your account has been locked after too many login attempts.<br /> Please contact an administrator to have your account enabled again.<br />'

/* configuration for Job Sweep Service */
//Quartz jobs configuration
//start delay for the sweep job
com.recomdata.export.jobs.sweep.startDelay=60000 //d*h*m*s*1000
//repeat interval for the sweep job
com.recomdata.export.jobs.sweep.repeatInterval= 86400000 //d*h*m*s*1000
//specify the age of files to be deleted (in days)
com.recomdata.export.jobs.sweep.fileAge=3

/* Configuration for logs */
log4j = {
  appenders {
                // set up a log file in the standard tomcat area; be sure to use .toString() with ${}
                rollingFile name:'tomcatLog', file:"transmart.log".toString(), maxFileSize:'6080KB', layout:pattern(conversionPattern: '[%p] %d{HH:mm:ss} (%c{5}:%M:%L) | %m%n')
                'null' name:'stacktrace'
        }

        root {
                // change the root logger to my tomcatLog file
                warn 'tomcatLog'
                additivity = true
        }

        // example for sending stacktraces to my tomcatLog file
        debug tomcatLog:'StackTrace'
        debug tomcatLog:'grails.app.task', 'grails.app.controller', 'grails.app.service'
        
    //trace 'org.hibernate.type'
    //debug 'org.hibernate.SQL'
        
}


//**************************
//Node Metadata Configurations
nodemetadata.fieldMapping =  [
				DOCUMENT_COUNT:[header:'Distinct Documents',treeText:'DC:', fieldType:'count'],
				PATIENT_COUNT:[header:'Patient Count',treeText:'PC:', fieldType:'count'],
				PATIENT_FREQUENCY:[header:'Patient Frequency',treeText:'PF:', fieldType:'percent']
			     ]	
nodemetadata.modifierTypeForDetailedUsage = "CUSTOM:SENT:"
//**************************

//**************************
//Validation Parameters
validation.concept_path=""
validation.applied_path=""

//Validation Parameters for Public Clinical Notes
validation.concept_path_public=""
validation.applied_path_public=""

nodemetadata.observationValidModifier="CUSTOM:OBSERVATION_VALID:"
nodemetadata.conceptValidatedModifier="CUSTOM:CONCEPT_VALIDATED:"
nodemetadata.observationInvalidReasonModifier="CUSTOM:OBSERVATION_INVALID_REASON:"
nodemetadata.patientValidModifier="CUSTOM:PATIENT_VALID:"
nodemetadata.highlightModifier="CUSTOM:HIGHLIGHT:"
//**************************



