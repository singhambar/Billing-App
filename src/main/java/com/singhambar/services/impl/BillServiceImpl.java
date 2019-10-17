package com.singhambar.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singhambar.beans.BeanId;
import com.singhambar.beans.Product;
import com.singhambar.repositories.BillRepository;
import com.singhambar.services.BaseService;
import com.singhambar.services.BillService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Service("billService")
public class BillServiceImpl<T extends BeanId, ID extends Serializable> extends BaseService<Product, Long>
		implements BillService<Product, Long> {

	@Autowired
	BillRepository billRepository;

}
