package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SW_CURRENCY_CD database table.
 * 
 */
@Entity
@Table(name="SW_CURRENCY_CD")
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CURRENCY_ID", unique=true, nullable=false)
	private int currencyId;

	@Column(name="COUNTRY", length=100)
	private String country;

	@Column(name="CURRENCY", length=100)
	private String currency;

    public Currency() {
    }

	public int getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}