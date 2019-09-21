package com.singhambar.rests;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.singhambar.beans.User;
import com.singhambar.services.UserService;

import javax.ws.rs.core.UriInfo;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Path("/user")
public class UserRestResource extends AbstractRESTResource{

	static ApplicationContext appContext=null;
	static UserService userService=null;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser() {

		return "Hello Jersey Plain";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEntity(@Context UriInfo ui, @Context HttpHeaders hh, String data) {
		
		Response rs;
		if(appContext==null)
		 appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		ObjectMapper ob =new ObjectMapper();
		StringWriter writer = new StringWriter();
		User user =null;//new User();
		try {
			 user=ob.readValue(data, User.class);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		user.setName("Ambar Singh12k12");
//		user.setEmailId("ambar.kumar@adeptia.com");
//		user.setPassword("P@ssw0rd");
		if(userService==null)
		 userService = (UserService) appContext.getBean("userService");
		try {
			userService.createUser(user);
			ob.writeValue(writer, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(writer.toString()).build();
		
	}

	@Override
	public Object getBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
