package com.singhambar.rests;

import static com.singhambar.app.utilities.AppConstants.ADMIN;
import static com.singhambar.app.utilities.AppConstants.COMPANY;
import static com.singhambar.app.utilities.AppConstants.OWNER;
import static com.singhambar.app.utilities.AppConstants.STAFF;

import java.io.StringWriter;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singhambar.app.configs.BeanFactory;
import com.singhambar.app.mixins.UserMixin;
import com.singhambar.app.utilities.AppUtils;
import com.singhambar.beans.Product;
import com.singhambar.services.ProductService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Path("/product")
public class ProductRestResource extends AbstractRESTResource {

	ProductService<Product, Long> productService = null;

	@SuppressWarnings("unchecked")
	@Override
	public ProductService<Product, Long> getBean() {
		if (productService == null)
			productService = BeanFactory.getBean("productService", ProductService.class);
		return productService;
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER, STAFF, COMPANY })
	public Response createEntity(@Context UriInfo ui, @Context HttpHeaders hh, String data) throws Exception {
		StringWriter writer = new StringWriter();
		Product product = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			product = mapper.readValue(data, Product.class);
			getBean().createEntity(product);
			mapper.addMixIn(Product.class, UserMixin.class);
			mapper.writeValue(writer, product);
		} catch (Exception e) {
			getLogger().error("Error while creating product", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response updateEntity(UriInfo ui, HttpHeaders hh, String id, String data) throws Exception {
		StringWriter writer = new StringWriter();
		Product product = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			product = getBean().getEntity(Long.parseLong(id));
			product = mapper.updateValue(product, data);
			mapper.addMixIn(Product.class, UserMixin.class);
			product = getBean().updateEntity(product);
			mapper.writeValue(writer, product);
		} catch (Exception e) {
			getLogger().error("Error while updating product", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response getEntity(UriInfo ui, HttpHeaders hh, String id) throws Exception {
		StringWriter writer = new StringWriter();
		Product product = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			product = getBean().getEntity(Long.parseLong(id));
			mapper.addMixIn(Product.class, UserMixin.class);
			mapper.writeValue(writer, product);
		} catch (Exception e) {
			getLogger().error("Error while fetching product", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response getEntities(UriInfo ui, HttpHeaders hh) throws Exception {
		StringWriter writer = new StringWriter();
		List<Product> product = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			product = getBean().getEntities();
			mapper.addMixIn(Product.class, UserMixin.class);
			mapper.writeValue(writer, product);
		} catch (Exception e) {
			getLogger().error("Error while updating product", e);
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
			getLogger().error("Error while deleting product", e);
			throw e;
		}
		return Response.ok().status(Status.ACCEPTED).build();
	}

}
