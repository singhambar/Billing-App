/**
 * 
 */
package com.singhambar.rests;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.singhambar.app.utilities.AppLogger;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public abstract class AbstractRESTResource implements RESTResource {

	@Context
	public SecurityContext securityContext;

	private Logger logger = null;

	@Override
	public Response createEntity(UriInfo ui, HttpHeaders hh, String data) throws Exception {
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@Override
	public Response updateEntity(UriInfo ui, HttpHeaders hh, String id, String data) throws Exception {
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@Override
	public Response getEntity(UriInfo ui, HttpHeaders hh, String id) throws Exception {
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@Override
	public Response getEntities(UriInfo ui, HttpHeaders hh) throws Exception {
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@Override
	public Response deleteEntity(UriInfo ui, HttpHeaders hh, String id) throws Exception {
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@Override
	public Response deleteEntities(UriInfo ui, HttpHeaders hh) throws Exception {
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	/**
	 * Fetches the Logger
	 * 
	 * @return
	 */
	public Logger getLogger() {
		synchronized (this) {
			if (logger == null) {
				logger = AppLogger.getLogger("REST");
			}
			return logger;
		}
	}

}
