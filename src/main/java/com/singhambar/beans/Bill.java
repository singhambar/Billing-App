/**
 * 
 */
package com.singhambar.beans;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Entity
@Table(name = "BILL")
public class Bill extends BeanId implements Serializable {

	private static final long serialVersionUID = 3219766019199279724L;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 128)
	@Column(name = "NAME", unique = true)
	private String name;

	@Size(max = 512)
	@Column(name = "ADDRESS")
	private String address;
	
	@Size(max = 15)
	@Column(name = "CONTACT_NO")
	private String contactNo;

	@NotNull
	@Column(name = "INVOICE_NO", unique = true)
	private Long invoiceNo;

	@Size(max = 20)
	@Column(name = "GSTIN")
	private Long gstin;

	@NotNull
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BILL_DATE")
	private Date billDate;

	@NotNull
	@PositiveOrZero
	@Column(name = "TOTAL_AMOUNT")
	private Long totalAmount;

	@PositiveOrZero
	@Column(name = "DISCOUNT_AMOUNT")
	private Long discountAmount = 0L;

	@Column(name = "PAYMENT_TYPE")
	private Integer paymentType = 1;

	@Size(max = 2096)
	@Column(name = "EXTRA_DETAILS")
	private String extraDetails;

	@OneToMany(fetch = EAGER, mappedBy = "billId", cascade = ALL, orphanRemoval = true)
	private Set<BilledProduct> billedProducts;

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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the invoiceNo
	 */
	public Long getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(Long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the gstin
	 */
	public Long getGstin() {
		return gstin;
	}

	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(Long gstin) {
		this.gstin = gstin;
	}

	/**
	 * @return the billDate
	 */
	public Date getBillDate() {
		return billDate;
	}

	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	/**
	 * @return the totalAmount
	 */
	public Long getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the discountAmount
	 */
	public Long getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(Long discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the paymentType
	 */
	public Integer getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
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
	 * @return the billedProducts
	 */
	public Set<BilledProduct> getBilledProducts() {
		return billedProducts;
	}

	/**
	 * @param billedProducts the billedProducts to set
	 */
	public void setBilledProducts(Set<BilledProduct> billedProducts) {
		this.billedProducts = billedProducts;
	}
	
	

}
