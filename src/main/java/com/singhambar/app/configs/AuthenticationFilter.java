/**
 * 
 */
package com.singhambar.app.configs;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.singhambar.app.HashGeneratorUtils;
import com.singhambar.beans.AuthToken;
import com.singhambar.repositories.AuthTokenRepository;
import com.singhambar.services.AuthTokenService;
import com.singhambar.services.UserService;
import com.singhambar.services.impl.AuthTokenServiceImpl;
import com.singhambar.services.impl.UserServiceImpl;

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
	HttpServletResponse  response;
	
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Method method = resourceInfo.getResourceMethod();
		final MultivaluedMap<String, String> headers = requestContext.getHeaders();
		headers.get("Cookie");
		
		try {
			validateAndUpdateToken(requestContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Access allowed for all
		if (false && !method.isAnnotationPresent(PermitAll.class)) {
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(
						Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build());
				return;
			}

			// Get request headers
			// headers = requestContext.getHeaders();

			// Fetch authorization header
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			// If no authorization information present; block access
			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
						.entity("You cannot access this resource").build());
				return;
			}

			// Get encoded username and password
			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

			// Decode username and password
			String usernameAndPassword = new String("");
			;

			// Split username and password tokens
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();

			// Verifying Username and password
			System.out.println(username);
			System.out.println(password);

			// Verify user access
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				// Is user valid?
				if (!isUserAllowed(username, password, rolesSet)) {
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.entity("You cannot access this resource").build());
					return;
				}
			}
		}
	}

	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
		boolean isAllowed = false;

		// Step 1. Fetch password from database and match with password in argument
		// If both match then get the defined role for user from database and continue;
		// else return isAllowed [false]
		// Access the database and do this part yourself
		// String userRole = userMgr.getUserRole(username);

		if (username.equals("howtodoinjava") && password.equals("password")) {
			String userRole = "ADMIN";

			// Step 2. Verify user role
			if (rolesSet.contains(userRole)) {
				isAllowed = true;
			}
		}
		return isAllowed;
	}
	
	private void validateAndUpdateToken(ContainerRequestContext requestContext) throws Exception {
		final MultivaluedMap<String, String> headers = requestContext.getHeaders();
		headers.get("Cookie");
		
		HttpSession session = request.getSession(false);
		 
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;
		 
		Cookie[] cookies = request.getCookies();
		 
		if (!loggedIn && cookies != null) {
		    // process auto login for remember me feature
		    String selector = "";
		    String rawValidator = "";  
		     
		    for (Cookie aCookie : cookies) {
		        if (aCookie.getName().equals("ACCESS_TOKEN")) {
		            selector = aCookie.getValue();
		        } else if (aCookie.getName().equals("VALIDATION_KEY")) {
		            rawValidator = aCookie.getValue();
		        }
		    }
		     
		    if (!"".equals(selector) && !"".equals(rawValidator)) {
		    	
		    	AuthTokenService tokenService= BeanFactory.getBean("authTokenService", AuthTokenService.class);
		    	AuthToken token= tokenService.findByTokenAndValidator(selector, rawValidator);
		    	
		        if (token != null) {
		            String hashedValidatorDatabase = token.getValidator();
		            String hashedValidatorCookie = HashGeneratorUtils.generateSHA256(rawValidator);
		             
		            if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
		                session = request.getSession();
		                session.setAttribute("loggedCustomer", token.getUserId());
		                loggedIn = true;
		                 
		                // update new token in database
		                String newSelector = RandomStringUtils.randomAlphanumeric(12);
		                String newRawValidator =  RandomStringUtils.randomAlphanumeric(64);
		                 
		                String newHashedValidator = HashGeneratorUtils.generateSHA256(newRawValidator);
		                 
		                token.setToken(newSelector);
		                token.setValidator(newHashedValidator);
		                tokenService.update(token);
		                 
		                // update cookie
		                Cookie cookieSelector = new Cookie("selector", newSelector);
		                cookieSelector.setMaxAge(604800);
		                 
		                Cookie cookieValidator = new Cookie("validator", newRawValidator);
		                cookieValidator.setMaxAge(604800);
		                 
		                response.addCookie(cookieSelector);
		                response.addCookie(cookieValidator);                       
		                 
		            }
		        }
		    }
		     
		}
	}

}
