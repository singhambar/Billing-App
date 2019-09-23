package com.singhambar.rests;

import static com.singhambar.app.AppConstants.COOKIE_MAX_AGE;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.singhambar.app.AppConstants;
import com.singhambar.app.configs.BeanFactory;
import com.singhambar.app.security.EncrypterAndDecrypter;
import com.singhambar.beans.AuthToken;
import com.singhambar.beans.User;
import com.singhambar.services.UserService;

import javax.ws.rs.core.UriInfo;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Path("/user")
public class UserRestResource extends AbstractRESTResource {

	UserService userService = null;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser() {

		return "Hello Jersey Plain";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response createEntity(@Context UriInfo ui, @Context HttpHeaders hh, String data) {

		Response rs;
		ObjectMapper ob = new ObjectMapper();
		StringWriter writer = new StringWriter();
		User user = null;// new User();
		try {
			user = ob.readValue(data, User.class);

			EncrypterAndDecrypter enc = new EncrypterAndDecrypter();
			System.out.println(enc.encrypt(user.getPassword()));
			user.setPassword((enc.encrypt(user.getPassword())));

			System.out.println(enc.decrypt(user.getPassword()));

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			getBean().createUser(user);
			ob.writeValue(writer, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(writer.toString()).build();

	}

	@Override
	public UserService getBean() {
		if (userService == null) {
			userService = BeanFactory.getBean("userService", UserService.class);
		}
		return userService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@Context UriInfo ui, @Context HttpHeaders hh, String data) {

		User user = null;
		try {
			ObjectMapper ob = new ObjectMapper();
			user = ob.readValue(data, User.class);

			AuthToken newToken = getBean().login(user.getEmailId(), user.getPassword());

			Cookie accessToken = new Cookie("ACCESS_TOKEN", newToken.getToken());
			Cookie validationKey = new Cookie("VALIDATION_KEY", newToken.getValidationKey());
			return Response.ok("User logged in Successfully.")
					.cookie(new NewCookie(accessToken, null, COOKIE_MAX_AGE, false),
							new NewCookie(validationKey, null, COOKIE_MAX_AGE, false))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok("User logged in Unsuccessful !!").cookie().build();

	}

}
