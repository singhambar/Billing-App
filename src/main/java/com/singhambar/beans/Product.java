/**
 * 
 */
package com.singhambar.beans;

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
public class Product extends BeanId {

	@Column(name = "NAME", nullable = false, length = 64)
	private String name;

	@Column(name = "DESCRIPTUION", length = 512)
	private String description;

	@Column(name = "VENDOR", length = 128)
	private String vendor;

	@Column(name = "COST_PRICE", nullable = false)
	private Long costPrice;

	@Column(name = "SELLING_PRICE", nullable = false)
	private Long sellingPrice;

	@Column(name = "GST", nullable = false)
	private Integer gst;

	@Column(name = "QUANTITY", nullable = false, length = 64)
	private Integer quantity = 0;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

}
