package com.singhambar.rests;

import java.io.StringWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singhambar.beans.User;
import com.singhambar.services.UserService;

import javax.ws.rs.core.UriInfo;

@Path("/user")
public class UserRestResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser() {

		return "Hello Jersey Plain";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEntity(@Context UriInfo ui, @Context HttpHeaders hh, String data) {
		
		Response rs;
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ObjectMapper ob =new ObjectMapper();
		StringWriter writer = new StringWriter();
		User user = new User();

		user.setName("Ambar Singh");
		user.setEmailId("ambar.kumar@adeptia.com");
		user.setPassword("P@ssw0rd");
		
		UserService userService = (UserService) appContext.getBean("userService");
		try {
			userService.createUser(user);
			ob.writeValue(writer, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(writer.toString()).build();
		
	}

}
