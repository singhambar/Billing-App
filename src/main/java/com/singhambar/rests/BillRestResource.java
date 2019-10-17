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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.singhambar.app.configs.BeanFactory;
import com.singhambar.app.mixins.UserMixin;
import com.singhambar.app.utilities.AppUtils;
import com.singhambar.beans.Bill;
import com.singhambar.services.BillService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Path("/bill")
public class BillRestResource extends AbstractRESTResource {

	BillService<Bill, Long> billService = null;

	@SuppressWarnings("unchecked")
	@Override
	public BillService<Bill, Long> getBean() {
		if (billService == null)
			billService = BeanFactory.getBean("billService", BillService.class);
		return billService;
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER, STAFF, COMPANY })
	public Response createEntity(UriInfo ui, HttpHeaders hh, String data) throws Exception {
		StringWriter writer = new StringWriter();
		Bill bill = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			bill = mapper.readValue(data, Bill.class);
			getBean().createEntity(bill);
			mapper.writeValue(writer, bill);
		} catch (Exception e) {
			getLogger().error("Error while creating bill", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response updateEntity(UriInfo ui, HttpHeaders hh, String id, String data) throws Exception {
		StringWriter writer = new StringWriter();
		Bill bill = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			bill = getBean().getEntity(Long.parseLong(id));
			ObjectReader updater = mapper.readerForUpdating(bill);
			bill = updater.readValue(data);
			bill = getBean().updateEntity(bill);
			mapper.writeValue(writer, bill);
		} catch (Exception e) {
			getLogger().error("Error while updating bill", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response getEntity(UriInfo ui, HttpHeaders hh, String id) throws Exception {
		StringWriter writer = new StringWriter();
		Bill bill = null;
		try {
			ObjectMapper mapper = AppUtils.getMapper();
			bill = getBean().getEntity(Long.parseLong(id));
			mapper.addMixIn(Bill.class, UserMixin.class);
			mapper.writeValue(writer, bill);
		} catch (Exception e) {
			getLogger().error("Error while fetching bill", e);
			throw e;
		}
		return Response.ok(writer.toString()).build();
	}

	@Override
	@RolesAllowed({ ADMIN, OWNER })
	public Response getEntities(UriInfo ui, HttpHeaders hh) throws Exception {
		StringWriter writer = new StringWriter();
		Page<Bill> bills = null;
		try {
			MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
			int size = queryParams.containsKey(LIMIT) ? Integer.parseInt(queryParams.getFirst(LIMIT)) : PAGE_SIZE;
			int pageNo = queryParams.containsKey(PAGE) ? Integer.parseInt(queryParams.getFirst(PAGE)) : PAGE_NO;
			String property = queryParams.containsKey(SORT) ? queryParams.getFirst(SORT) : DEFAULT_PROPERTY;
			Direction direction = queryParams.containsKey(DIR) ? Direction.fromString(queryParams.getFirst(DIR))
					: Direction.DESC;
			ObjectMapper mapper = AppUtils.getMapper();
			bills = getBean().getEntities(PageRequest.of(pageNo - 1, size, direction, property));
			Map<String, Object> map = new HashMap<String, Object>(2);
			getLogger().info("Converting user into JSON ...");
			map.put("Bills", bills.getContent());
			map.put(TOTAL_COUNT, bills.getTotalElements());
			mapper.writeValue(writer, map);
			getLogger().info("Converted successfully into JSON ...");
		} catch (Exception e) {
			getLogger().error("Error while updating bill", e);
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
			getLogger().error("Error while deleting bill", e);
			throw e;
		}
		return Response.ok().status(Status.ACCEPTED).build();
	}

}
