package transmart

import static us.monoid.web.Resty.content

import org.apache.commons.logging.LogFactory
import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

import us.monoid.json.JSONObject
import us.monoid.web.JSONResource
import us.monoid.web.Resty

import com.auth0.Auth0User
import com.auth0.Tokens

import edu.hms.transmart.security.AuthUser
import edu.hms.transmart.security.Role
import grails.converters.JSON
import grails.util.Holders

class UserManagerService {
	def config = Holders.config

	def client_id = config.edu.harvard.transmart.auth0.client_id
	def client_secret= config.edu.harvard.transmart.auth0.client_secret
	def username, email, name, nickname, firstname, lastname, connection, phone, about, picture, access_token, id_token, details
	private static final log = LogFactory.getLog(this)

	JSONResource userInfo;
	Auth0User currentuser;
	
	AuthUser user;

	public UserManagerService() {
	}

	public UserManagerService(String username) {
		log.info("Creating object for "+username)
		this.user = AuthUser.findByUsername(username)
	}

	public String getEmail() {
		return this.email
	}

	public boolean authenticate(String auth0_code, String callbackURL) {

		def resty = new Resty()
		JSONObject json = new JSONObject();
		try {
			json.put("client_id", this.client_id)
			json.put("client_secret",this.client_secret);
			json.put("redirect_uri", callbackURL);
			json.put("grant_type", "authorization_code");
			json.put("code", auth0_code);

			JSONResource tokenInfo = resty.json(getUri("/oauth/token"), content(json));
			Tokens t = new Tokens(tokenInfo.toObject());

			this.access_token = t.getAccessToken();
			this.id_token = t.getIdToken();
		} catch (Exception ex) {
			log.error "Could not authorize the user with Auth0 "+ex.getMessage()
			throw new IllegalStateException("Cannot get Token from Auth0", ex)
		}

		this.userInfo = resty.json(getUri("/userinfo?access_token="+this.access_token));
		this.currentuser = new Auth0User(this.userInfo.toObject())

		this.name = this.currentuser.getName()
		this.nickname = this.currentuser.getNickname()
		this.email = this.currentuser.getEmail()
		this.username = this.currentuser.getEmail()
		this.connection = this.currentuser.getIdentities().get(0).getAt("connection")
		this.picture = this.currentuser.getPicture()
	}

	public static getUserInfoByToken(String token) {
		def resty = new Resty()

		JSONObject json = new JSONObject();
		json.put("id_token", token)

		JSONResource usrinfo = resty.json(
				String.format("https://%s%s",
				Holders.config.edu.harvard.transmart.auth0.domain, '/tokeninfo'
				),
				content(json)
				);
		return usrinfo;
		//return new Auth0User(usrinfo.toObject())
	}

	public String getUserId() {
		return this.currentuser.getUserId();
	}

	public boolean isAdmin() {
		def dbuser = AuthUser.findByUsername(this.username)
		return dbuser.isAdmin();
	}

	public boolean hasRole(Role val) {
		def dbuser = AuthUser.findByUsername(this.username)
		return dbuser.authorities.contains(val)
	}

	public String toJSON() {
		return this.params as JSON
	}

	public void setFirstName(val) {
		if (val) {
			this.firstname = val
		}
	}

	public void setLastName(val) {
		if (val) {
			this.lastname = val
		}
	}

	public void setPhone(val) {
		if (val) {
			this.phone = val
		}
	}

	public void setAbout(val) {
		if (val) {
			this.about = val
		}
	}

	private String getUri(String path) {
		return String.format("https://%s%s",
				"avillachlab.auth0.com", path);
	}

	public static boolean createUser(params) {
		AuthUser person = new AuthUser()
		person.id = (new Date().getTime()/1000)
		person.name = params.firstname?:''+params.lastname?:''
		person.email = params.email
		person.username = params.email
		person.userRealName = params.firstname?:''+params.lastname?:''

		// password is no longer used via the GORM, this is just a placeholder
		person.passwd = "auth0"
		person.emailShow = true
		person.passwordExpired = false

		// field will hold the additional information as a JSON string
		person.description = [
			firstname: params.firstname?:'',
			lastname: params.lastname?:'',
			connection: params.connection?:'UNKNOWN',
			phone: params.phone?:'',
			about: params.about?:''
		] as JSON
		person.enabled = true
		person.authorities = null
		return person.save()
	}

	public static  getCredentials(req) {

		def credentials = [
			username: null
		]

		def resty = new Resty()
		JSONObject json = new JSONObject();
		try {
			def callbackURL = String.format("https://%s%s",
					Holders.config.edu.harvard.transmart.auth0.domain, "/registration/index")

			json.put("client_id", Holders.config.edu.harvard.transmart.auth0.client_id)
			json.put("client_secret",Holders.config.edu.harvard.transmart.auth0.client_secret);

			def cburl = req.scheme+"://"+req.serverName+":"+req.serverPort+req.contextPath
			if ((req.scheme.toLowerCase().equals("http") && req.serverPort == 80) || (req.scheme.toLowerCase().equals("https") && req.serverPort == 443)) {
				cburl = req.scheme+"://"+req.serverName+req.contextPath
			}
			json.put("redirect_uri", cburl);
			json.put("grant_type", "authorization_code");
			json.put("code", req.getParameter("code"));

			def tokeninfoURL = String.format("https://%s%s",
					Holders.config.edu.harvard.transmart.auth0.domain, "/oauth/token")
			JSONResource tokenInfo = resty.json(tokeninfoURL, content(json));

			Tokens t = new Tokens(tokenInfo.toObject());

			//acces_token, might be useful, but it only has a short lifespan
			def atoken = t.getAccessToken()

			// id_token, is a JWT compliant token, might be useful
			def itoken = t.getIdToken()

			def atokenURL = String.format("https://%s%s",
					Holders.config.edu.harvard.transmart.auth0.domain, "/userinfo?access_token="+atoken)

			def userInfo = resty.json(atokenURL);
			log.info "getCredentials() userInfo:"+userInfo.toObject()
			def currentuser = new Auth0User(userInfo.toObject())

			credentials = [
				username: currentuser.getEmail(),
				email: currentuser.getEmail(),
				name: 'unregistered',
				nickname: 'unregistered',
				access_token: atoken,
				id_token: itoken,
				connection: currentuser.getIdentities().get(0).getAt("connection"),
				picture: currentuser.getPicture(),
				level: -1,
			]

			def isExistingUser = AuthUser.findByUsername(credentials.username)
			if (!isExistingUser) {
				AuthUser person = new AuthUser()
				person.id = (new Date().getTime()/1000)
				person.name = credentials.name
				person.email = credentials.email
				person.username = credentials.username
				person.userRealName = credentials.name
				person.uniqueId = userInfo.user_id

				// password is no longer used via the GORM, this is just a placeholder
				person.passwd = "auth0"
				person.emailShow = true
				person.passwordExpired = false

				// field will hold the additional information as a JSON string
				person.description = [
					firstname: '',
					lastname: '',
					connection: credentials.connection,
					phone: '',
					about:'',
					picture:credentials.picture,
				] as JSON
				person.enabled = true
				person.authorities = null
				person.save(flush:true)

				credentials.level = -1
			} else {
				credentials.level = isExistingUser.getLevel()
			}
		} catch (Exception ex) {
			println "getCredentials/"+ex.class+" "+ex.getMessage()
		}
		return credentials
	}

	public getCurrentUser() {
		log.info("Getting currentuser")
		details = JSON.parse(this.user.description)
		details << [ username: this.user.username, email: this.user.email?:this.user.username, level: this.user.getLevel()]
		return details

	}
	
	public setDescription(params) {
		params.remove('action')
		params.remove('_action_Save')
		
		this.user.description = params as JSON
		this.user.save()
	}


	// Update extended details (stored as JSON in the 'description' column)
	public static setDescription(person, params) {
		def details = []

		if (person == null) {
			details << [status: 'ERROR', message : "Could not find username <b>"+params.toString+"</b> in the database."]
			throw new RuntimeException("Could not find username <b>"+params.toString()+"</b> in the database.");
		}

		try {
			person.name = person.userRealName = (params.firstname?:'')+' '+(params.lastname?:'')
			person.description = [
				firstname: params.firstname?:'n/a',
				lastname : params.lastname?:'n/a',
				phone : params.phone?:'n/a',
				title: params.title?:'n/a',
				degree : params.degree?:'n/a',
				email: params.email?:'n/a',
				about: params.about?:'n/a',
				usertype: params.usertype?:'n/a',
				institution : params.institution?:'n/a',
				disease: params.disease?:'n/a',
				is_funded_grant: params.is_funded_grant?:'no',
				is_grant_proposal: params.is_grant_proposal?:'no',
				is_personal_use: params.is_personal_use?:'no'
			] as JSON
			person.save()
			details << [status: 'OK', message : "User record has been updated."]
		} catch (Exception ex) {
			details << [status: 'ERROR', message : ex.getMessage()]
		}

		return details
	}


	// Abstracted steps to set the proper roles for a specific access level for the user
	public static setAccessLevel(person, access_level) {

		if (access_level==1) {
			log.warn "Setting privileges for "+person.username+" to level "+access_level
			Role.findByAuthority("ROLE_STUDY_OWNER").addToPeople(person);
			Role.findByAuthority("ROLE_PUBLIC_USER").addToPeople(person);
		}
	}
}
