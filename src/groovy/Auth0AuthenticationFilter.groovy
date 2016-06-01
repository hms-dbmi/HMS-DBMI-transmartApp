import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.web.filter.GenericFilterBean

/**
 * Part of the security filter chain for auth0-based authentication.
 */
class Auth0AuthenticationFilter extends GenericFilterBean implements ApplicationEventPublisherAware {

	String filterProcessesUrl
	AuthenticationManager authenticationManager
	def customProvider

	def springSecurityService
	SessionAuthenticationStrategy sessionAuthenticationStrategy
	AuthenticationSuccessHandler authenticationSuccessHandler
	AuthenticationFailureHandler authenticationFailureHandler
	ApplicationEventPublisher applicationEventPublisher

	/**
	 * If the incoming request URI contains a JWT token,
	 * the filter commences math-based authentication.
	 * @param req
	 * @param resp
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)  throws IOException,
	ServletException {
		HttpServletRequest request = (HttpServletRequest) req
		HttpServletResponse response = (HttpServletResponse) resp

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			def auth_header = request.getHeader("Authorization")
			def token = null
			if (auth_header != null) {
				token = auth_header.split(" ")[1]
				if (token != null) {
					log.debug("Authorization token is found.")

					Auth0AuthenticationToken authtoken = new Auth0AuthenticationToken(token)
					
					logger.info("Authenticated:"+authtoken)
					SecurityContextHolder.getContext().setAuthentication(authtoken)
					log.info("JWT authenticated")
				} else {
					log.warn("No token specified in the Authorization header.")
				}
			} else {
				log.debug("No Authorization header in request.")
			}
		}
		chain.doFilter(request, response)
	}

	/**
	 * Builds an Authentication object and passes it to the AuthenticationManager
	 * to attempt to authenticate the user.
	 * @param request
	 * @return
	 * @throws AuthenticationException
	 */
	public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
		log.debug("attemptAuthentication()")

		// Create new AuthenticationToken out of the Authorization header
		Auth0AuthenticationToken token = new Auth0AuthenticationToken('mytoken')

		try {
			Authentication a = this.getAuthenticationManager().authenticate(token)
		} catch (Exception e) {
			log.error("Could not create new Authentication object. "+e.getMessage())
			return null
		}
	}

	/**
	 * Puts the populated Authentication instance in the SecurityContextHolder
	 * at which point the user is authenticated.
	 * @param request
	 * @param response
	 * @param authentication
	 */
	private void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) {
		log.debug("successfulAuthentication()")

		// When a populated Authentication object is placed in the SecurityContextHolder,
		// the user is authenticated.

		SecurityContextHolder.getContext().setAuthentication(authentication)
		log.info("User is authenticated.")

		applicationEventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authentication, this.getClass()))

		authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication)
	}

	/**
	 * Clears the SecurityContextHolder handles unsuccessful authentication.
	 * @param request
	 * @param response
	 * @param failed
	 */
	private void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response,
			Exception failed) {
		log.debug("unsuccessfulAuthentication()")
		SecurityContextHolder.clearContext()
		log.info("Cleared security context, due to failed authentication."+failed.getMessage())
		authenticationFailureHandler.onAuthenticationFailure(request,
				response,
				failed)
	}
}