package com.singhambar.rests;

import static com.singhambar.app.utilities.AppConstants.ADMIN;
import static com.singhambar.app.utilities.AppConstants.COMPANY;
import static com.singhambar.app.utilities.AppConstants.COOKIE_MAX_AGE;
import static com.singhambar.app.utilities.AppConstants.DEFAULT_PROPERTY;
import static com.singhambar.app.utilities.AppConstants.DIR;
import static com.singhambar.app.utilities.AppConstants.LIMIT;
import static com.singhambar.app.utilities.AppConstants.MODE;
import static com.singhambar.app.utilities.AppConstants.OWNER;
import static com.singhambar.app.utilities.AppConstants.PAGE;
import static com.singhambar.app.utilities.AppConstants.PAGE_NO;
import static com.singhambar.app.utilities.AppConstants.PAGE_SIZE;
import static com.singhambar.app.utilities.AppConstants.SORT;
import static com.singhambar.app.utilities.AppConstants.STAFF;
import static com.singhambar.app.utilities.AppConstants.TOTAL_COUNT;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singhambar.app.configs.BeanFactory;
import com.singhambar.app.mixins.UserMixin;
import com.singhambar.app.utilities.AppUtils;
import com.singhambar.beans.AuthToken;
import com.singhambar.beans.User;
import com.singhambar.services.UserService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Path("/user")
public class UserRestResource extends AbstractRESTResource {

	UserService<User, Long> userService = null;

	@SuppressWarnings("unchecked")
	@Override
	public UserService<User, Long> getBean() {
		if (userService == null)
			userService = BeanFactory.getBean("userService", UserService.class);
		return userService;
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response createEntity(@Context UriInfo ui, @Context HttpHeaders hh, String data) throws Exception {
		StringWriter writer = new StringWriter();
		User user = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			user = mapper.readValue(data, User.class);
			user.setPassword((AppUtils.encrypt(user.getPassword())));
			getBean().createEntity(user);
			mapper.addMixIn(User.class, UserMixin.class);
			mapper.writeValue(writer, user);
		} catch (Exception e) {
			getLogger().error("Error while creating user", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response updateEntity(UriInfo ui, HttpHeaders hh, String id, String data) throws Exception {
		StringWriter writer = new StringWriter();
		User user = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			user = getBean().getEntity(Long.parseLong(id));
			user = mapper.updateValue(user, data);
			mapper.addMixIn(User.class, UserMixin.class);
			user = getBean().updateEntity(user);
			mapper.writeValue(writer, user);
		} catch (Exception e) {
			getLogger().error("Error while updating user", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response getEntity(UriInfo ui, HttpHeaders hh, String id) throws Exception {
		StringWriter writer = new StringWriter();
		User user = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			user = getBean().getEntity(Long.parseLong(id));
			mapper.addMixIn(User.class, UserMixin.class);
			mapper.writeValue(writer, user);
		} catch (Exception e) {
			getLogger().error("Error while fetching user", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response getEntities(UriInfo ui, HttpHeaders hh) throws Exception {
		StringWriter writer = new StringWriter();
		Page<User> users = null;
		try {
			MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
			int size = queryParams.containsKey(LIMIT) ? Integer.parseInt(queryParams.getFirst(LIMIT)) : PAGE_SIZE;
			int pageNo = queryParams.containsKey(PAGE) ? Integer.parseInt(queryParams.getFirst(PAGE)) : PAGE_NO;
			String property = queryParams.containsKey(SORT) ? queryParams.getFirst(SORT) : DEFAULT_PROPERTY;
			Direction direction = queryParams.containsKey(DIR) ? Direction.fromString(queryParams.getFirst(DIR))
					: Direction.DESC;
			ObjectMapper mapper = AppUtils.getMapper();
			users = getBean().getEntities(PageRequest.of(pageNo-1, size, direction, property));
			mapper.addMixIn(User.class, UserMixin.class);
			Map<String, Object> map = new HashMap<String, Object>(2);
			getLogger().info("Converting user into JSON ...");
			map.put("Users", users.getContent());
			map.put(TOTAL_COUNT, users.getTotalElements());
			mapper.writeValue(writer, map);
			getLogger().info("Converted successfully into JSON ...");
		} catch (Exception e) {
			getLogger().error("Error while updating user", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response deleteEntity(UriInfo ui, HttpHeaders hh, String id) throws Exception {
		try {
			getBean().deleteEntity(Long.parseLong(id));
		} catch (Exception e) {
			getLogger().error("Error while deleting user", e);
			throw e;
		}
		return Response.ok().status(Status.ACCEPTED).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	@PermitAll
	public Response login(@Context UriInfo ui, @Context HttpHeaders hh, String data) throws Exception {
		User user = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			user = mapper.readValue(data, User.class);

			AuthToken newToken = getBean().login(user.getEmailId(), user.getPassword());

			Cookie accessToken = new Cookie("ACCESS_TOKEN", newToken.getToken());
			Cookie validationKey = new Cookie("VALIDATION_KEY", newToken.getValidationKey());
			return Response.ok("User logged in Successfully.")
					.cookie(new NewCookie(accessToken, null, COOKIE_MAX_AGE, false),
							new NewCookie(validationKey, null, COOKIE_MAX_AGE, false))
					.build();
		} catch (Exception e) {
			getLogger().error("Error while logging in", e);
			return Response.ok(e.getMessage()).status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/logout")
	@RolesAllowed({ ADMIN, OWNER, STAFF, COMPANY })
	public Response logout(@Context UriInfo ui, @Context HttpHeaders hh, @Context HttpServletRequest request)
			throws Exception {
		try {
			MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
			boolean logoutAll = (queryParams.containsKey(MODE) && queryParams.getFirst(MODE).equalsIgnoreCase("all"));

			User user = (User) securityContext.getUserPrincipal();

			getBean().logout(user, logoutAll);
			HttpSession session = request.getSession();
			session.invalidate();
		} catch (Exception e) {
			getLogger().error("Error while logging out user.", e);
		}
		return Response.ok("User logged out Successfully.")
				.cookie(new NewCookie(new Cookie("ACCESS_TOKEN", null), null, 0, false),
						new NewCookie(new Cookie("VALIDATION_KEY", null), null, 0, false))
				.build();
	}

	@GET
	@Path("/userinfo")
	@RolesAllowed({ ADMIN, OWNER, STAFF, COMPANY })
	public Response getUserInfo(@Context UriInfo ui, @Context HttpHeaders hh) throws Exception {
		StringWriter writer = new StringWriter();
		User user = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			user = (User) securityContext.getUserPrincipal();
			mapper.addMixIn(User.class, UserMixin.class);
			mapper.writeValue(writer, user);
		} catch (Exception e) {
			getLogger().error("Error while fetching user", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

}
