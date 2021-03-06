/* Copyright josivanSilva (Developer); 2018 */
package com.josivansilva.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Common customer entity class.
 * 
 * @author josivan@josivansilva.com
 *
 */
@Entity(name = "CommonCustomer")
@DiscriminatorValue("Common")
public class CommonCustomer extends Customer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "customer_employed")
	private boolean customerEmployed;

	public boolean isCustomerEmployed() {
		return customerEmployed;
	}

	public void setCustomerEmployed(boolean customerEmployed) {
		this.customerEmployed = customerEmployed;
	}	
	
}
