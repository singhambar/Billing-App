package com.singhambar.rests;

import static com.singhambar.app.utilities.AppConstants.ADMIN;
import static com.singhambar.app.utilities.AppConstants.COMPANY;
import static com.singhambar.app.utilities.AppConstants.DEFAULT_PROPERTY;
import static com.singhambar.app.utilities.AppConstants.DIR;
import static com.singhambar.app.utilities.AppConstants.LIMIT;
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

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
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
		Page<Product> products = null;
		try {
			MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
			int size = queryParams.containsKey(LIMIT) ? Integer.parseInt(queryParams.getFirst(LIMIT)) : PAGE_SIZE;
			int pageNo = queryParams.containsKey(PAGE) ? Integer.parseInt(queryParams.getFirst(PAGE)) : PAGE_NO;
			String property = queryParams.containsKey(SORT) ? queryParams.getFirst(SORT) : DEFAULT_PROPERTY;
			Direction direction = queryParams.containsKey(DIR) ? Direction.fromString(queryParams.getFirst(DIR))
					: Direction.DESC;
			ObjectMapper mapper = AppUtils.getMapper();
			products = getBean().getEntities(PageRequest.of(pageNo, size, direction, property));
			Map<String, Object> map = new HashMap<String, Object>(2);
			getLogger().info("Converting user into JSON ...");
			map.put("Products", products.getContent());
			map.put(TOTAL_COUNT, products.getTotalElements());
			mapper.writeValue(writer, map);
			getLogger().info("Converted successfully into JSON ...");
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
