/**
 * 
 */
package com.singhambar.rests;

import java.util.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public interface RESTResource {

	public Logger logger = null;

	@GET
	@Path("/{id: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEntity(@Context UriInfo ui, @Context HttpHeaders hh, @PathParam("id") String id) throws Exception;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEntities(@Context UriInfo ui, @Context HttpHeaders hh) throws Exception;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEntity(@Context UriInfo ui, @Context HttpHeaders hh, String data) throws Exception;

	@PUT
	@Path("/{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEntity(@Context UriInfo ui, @Context HttpHeaders hh, @PathParam("id") String id, String data) throws Exception;

	@POST
	@Path("/{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveOrUpdateEntity(@Context UriInfo ui, @Context HttpHeaders hh, @PathParam("id") String id,
			String data) throws Exception;

	@DELETE
	@Path("/{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEntity(@Context UriInfo ui, @Context HttpHeaders hh, @PathParam("id") String id) throws Exception;

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response deleteEntities(@Context UriInfo ui, @Context HttpHeaders hh) throws Exception;
	
	Object getBean();
	
	
}
