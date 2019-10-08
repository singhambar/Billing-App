/**
 * 
 */
package com.singhambar.app.security;

import static com.singhambar.app.utilities.AppConstants.COOKIE_MAX_AGE;
import static com.singhambar.app.utilities.AppConstants.CURRENT_AUTH_TOKEN_ID;
import static com.singhambar.app.utilities.AppConstants.LOGGED_IN_USER;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.RandomStringUtils;

import com.singhambar.app.configs.BeanFactory;
import com.singhambar.app.utilities.HashGeneratorUtils;
import com.singhambar.beans.AuthToken;
import com.singhambar.beans.User;
import com.singhambar.services.AuthTokenService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	@Context
	UriInfo uriInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Method method = resourceInfo.getResourceMethod();
		try {
			if (!method.isAnnotationPresent(PermitAll.class)) {
				if (method.isAnnotationPresent(DenyAll.class)) {
					requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
							.entity("Access blocked for all users !!").build());
					return;
				}
				validateAndUpdateToken(requestContext);
				AppSecurityContext securityContext = (AppSecurityContext) requestContext.getSecurityContext();

				if (method.isAnnotationPresent(RolesAllowed.class)) {
					RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
					String[] roles = rolesAnnotation.value();
					boolean isAllowed = false;
					for (String role : roles) {
						isAllowed = securityContext.isUserInRole(role);
						if (isAllowed)
							break;
					}
					if (!isAllowed) {
						requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
								.entity("You do not have permission to access this resource.").build());
						return;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			requestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).entity("You do not have permission to access this resource.").build());
			return;
		}
	}

	/**
	 * @param requestContext
	 * @throws Exception
	 * 
	 *             Method to validate token and create securityContext
	 */
	private void validateAndUpdateToken(ContainerRequestContext requestContext) throws Exception {
		HttpSession session = request.getSession();
		SecurityContext securityContext = requestContext.getSecurityContext();
		boolean loggedIn = session != null && session.getAttribute(LOGGED_IN_USER) != null;

		Cookie[] cookies = request.getCookies();

		if (!loggedIn && cookies != null) {
			String accessToken = "";
			String validationKey = "";

			for (Cookie aCookie : cookies) {
				if (aCookie.getName().equals("ACCESS_TOKEN")) {
					accessToken = aCookie.getValue();
				} else if (aCookie.getName().equals("VALIDATION_KEY")) {
					validationKey = aCookie.getValue();
				}
			}

			if (!"".equals(accessToken) && !"".equals(validationKey)) {

				AuthTokenService<AuthToken, Long> tokenService = BeanFactory.getBean("authTokenService",
						AuthTokenService.class);
				AuthToken token = tokenService.findByTokenAndValidator(accessToken, validationKey);

				if (token != null) {
					String hashedValidatorDatabase = token.getValidator();
					String hashedValidatorCookie = HashGeneratorUtils.generateSHA256(validationKey);

					if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
						User user = token.getUser();
						user.setCurrentAuthTokenId(token.getId());
						session.setAttribute(LOGGED_IN_USER, user);

						String newSelector = RandomStringUtils.randomAlphanumeric(12);
						String newRawValidator = RandomStringUtils.randomAlphanumeric(64);

						String newHashedValidator = HashGeneratorUtils.generateSHA256(newRawValidator);

						token.setToken(newSelector);
						token.setValidator(newHashedValidator);
						tokenService.update(token);

						Cookie cookieSelector = new Cookie("ACCESS_TOKEN", newSelector);
						cookieSelector.setMaxAge(COOKIE_MAX_AGE);

						Cookie cookieValidator = new Cookie("VALIDATION_KEY", newRawValidator);
						cookieValidator.setMaxAge(COOKIE_MAX_AGE);

						response.addCookie(cookieSelector);
						response.addCookie(cookieValidator);

					}
				}
			}

		}
		User user = (User) session.getAttribute(LOGGED_IN_USER);
		if (session.getAttribute(CURRENT_AUTH_TOKEN_ID) != null)
			user.setCurrentAuthTokenId((Long) session.getAttribute(CURRENT_AUTH_TOKEN_ID));
		requestContext.setSecurityContext(new AppSecurityContext(user, uriInfo.getRequestUri().getScheme()));
	}

}
