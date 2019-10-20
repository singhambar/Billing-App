/**
 * 
 */
package com.singhambar.beans;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Entity
@Table(name = "BILLED_PRODUCT")
public class BilledProduct extends BeanId implements Serializable {

	private static final long serialVersionUID = -941414578213547463L;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "BILL_ID")
	private Bill billId;

	@NotNull
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product productId;

	@NotNull
	@PositiveOrZero
	@Column(name = "DISCOUNT")
	private Integer discount = 0;

	@NotNull
	@PositiveOrZero
	@Column(name = "UNITS")
	private Integer units = 1;

	private transient Long lineTotal = 0L;

	/**
	 * @return the billId
	 */
	public Bill getBillId() {
		return billId;
	}

	/**
	 * @param billId
	 *            the billId to set
	 */
	public void setBillId(Bill billId) {
		this.billId = billId;
	}

	/**
	 * @return the productId
	 */
	public Product getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Product productId) {
		this.productId = productId;
	}

	/**
	 * @return the discount
	 */
	public Integer getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	/**
	 * @return the units
	 */
	public Integer getUnits() {
		return units;
	}

	/**
	 * @param units
	 *            the units to set
	 */
	public void setUnits(Integer units) {
		this.units = units;
	}

	/**
	 * @return the lineTotal
	 */
	public Long getLineTotal() {
		return lineTotal;
	}

	/**
	 * @param lineTotal
	 *            the lineTotal to set
	 */
	public void setLineTotal(Long lineTotal) {
		this.lineTotal = lineTotal;
	}

}
