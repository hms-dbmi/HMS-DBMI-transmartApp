import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceAware
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.SpringSecurityMessageSource
import org.springframework.util.Assert

class Auth0AuthenticationProvider implements AuthenticationProvider,
MessageSourceAware{

	def userDetailsService
	MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor()

	/**
	 * Authenticates the application user and returns a populated
	 * Authentication object if successful.
	 * @param auth
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	Authentication authenticate(Authentication auth) throws AuthenticationException {
		println(this.class.toString()+" authenticate...")

		Assert.isInstanceOf(Auth0AuthenticationToken.class, auth,
				messages.getMessage("Auth0AuthenticationProvider.onlySupports",
				"Only Auth0AuthenticationToken is supported"))

		auth0AuthenticationToken authentication = (Auth0AuthenticationToken) auth

		try {

			// Authenticate the token
			println(this.class.toString()+" working on the token...")
		} catch (AuthenticationException authenticationException) {
			println(this.class.toString()+" failed authenticating the token: "+authenticationException.getMessage())
			throw authenticationException
		}
		println(this.class.toString()+" returning: createSuccess....")
		return createSuccessfulAuthentication(authentication)
	}

	private Authentication createSuccessfulAuthentication(Authentication authentication) {
		println(this.class.toString()+" createSuccessfulAuthentication() setting it to true")
		authentication.setAuthenticated(true)
		return authentication
	}

	/**
	 * Tests whether this provider supports the Authentication
	 * type being passed in.
	 * @param authentication
	 * @return
	 */
	@Override
	boolean supports(Class<? extends Object> authentication) {
		println(this.class.toString()+" Supports?")
		println(this.class.toString()+" AuthenticationClass:"+authentication.class.toString())

		println(this.class.toString()+" supports? "+Auth0AuthenticationToken.class.isAssignableFrom(authentication))
		println(this.class.toString()+" supports? "+UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication))

		return true
	}


	@Override
	void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource)
	}
}
