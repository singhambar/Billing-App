/**
 * 
 */
package com.singhambar.app.mixins;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.singhambar.beans.Bill;
import com.singhambar.beans.Product;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public abstract class BilledProductMixin {

	@JsonBackReference
	public abstract Bill getBillId();

	@JsonBackReference
	public abstract Product getProductId();
}
