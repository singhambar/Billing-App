/**
 * 
 */
package com.singhambar.app.mixins;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.singhambar.beans.BilledProduct;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public abstract class BillMixin {

	@JsonManagedReference
	public abstract Set<BilledProduct> getBilledProducts();
}
