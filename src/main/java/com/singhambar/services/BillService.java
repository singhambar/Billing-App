package com.singhambar.services;

import java.io.Serializable;

import com.singhambar.beans.BeanId;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public interface BillService<T extends BeanId, ID extends Serializable> extends SuperService<T, ID> {

}