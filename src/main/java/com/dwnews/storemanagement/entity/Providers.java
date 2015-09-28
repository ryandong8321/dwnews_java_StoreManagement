package com.dwnews.storemanagement.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "providers", catalog = "dwnews_store_management")
public class Providers implements Serializable{

	/*
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "provider_id", unique = true, nullable = false)
	private Integer id;
	
	/*
	 * 供应商名称
	 */
	@Column(name = "provider_name", nullable = false, length=128)
	private String providerName;
	
	/*
	 * 供应商联系人
	 */
	@Column(name = "provider_contact_name", nullable = true, length=8)
	private String contactName;
	
	/*
	 * 供应商联系人电话
	 */
	@Column(name = "provider_contact_phone", nullable = true, length=16)
	private String contactPhoneNumber;
	
	@ManyToMany
	@JoinTable(name="providers_brand_relationship", 
		joinColumns={ @JoinColumn(name="relationship_provider_id") }, inverseJoinColumns={ @JoinColumn(name="relationship_brand_id") })
	private List<Brand> brands;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	@Override
	public String toString() {
		return "Providers [id=" + id + ", providerName=" + providerName + ", contactName=" + contactName
				+ ", contactPhoneNumber=" + contactPhoneNumber + ", brands=" + brands + "]";
	}
	
}
