/**
 * 
 */
package com.singhambar.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends BeanId implements Serializable {

	private static final long serialVersionUID = 2029738664847525714L;

	@Column(name = "NAME", unique=true, nullable = false, length = 128)
	private String name;

	@Column(name = "VENDOR", length = 128)
	private String vendor;

	@Column(name = "COST_PRICE", nullable = false)
	private Long costPrice;

	@Column(name = "SELLING_PRICE", nullable = false)
	private Long sellingPrice;

	@Column(name = "GST", nullable = false)
	private Integer gst;

	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity = 0;

	@Column(name = "EXTRA_DETAILS", length = 2096)
	private String extraDetails;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE", nullable = false)
	private Date modifiedDate;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the costPrice
	 */
	public Long getCostPrice() {
		return costPrice;
	}

	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(Long costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * @return the sellingPrice
	 */
	public Long getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(Long sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * @return the gst
	 */
	public Integer getGst() {
		return gst;
	}

	/**
	 * @param gst the gst to set
	 */
	public void setGst(Integer gst) {
		this.gst = gst;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the extraDetails
	 */
	public String getExtraDetails() {
		return extraDetails;
	}

	/**
	 * @param extraDetails the extraDetails to set
	 */
	public void setExtraDetails(String extraDetails) {
		this.extraDetails = extraDetails;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


}
