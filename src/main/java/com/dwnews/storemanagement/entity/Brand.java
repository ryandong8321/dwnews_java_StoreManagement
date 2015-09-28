package com.dwnews.storemanagement.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name = "brand", catalog = "dwnews_store_management")
public class Brand implements Serializable{

	/*
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "brand_id", unique = true, nullable = false)
	private Integer id;
	
	/*
	 * 品牌名称
	 */
	@Column(name = "brand_name", nullable = false, length=32)
	private String brandName;
	
	/*
	 * 备注
	 */
	@Column(name = "brand_memo", nullable = true, length=128)
	private String brandMemo;
	
	@OneToMany(mappedBy="brands")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Items> items;
	
	@ManyToMany(mappedBy="brands")
	private List<Providers> providers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandMemo() {
		return brandMemo;
	}

	public void setBrandMemo(String brandMemo) {
		this.brandMemo = brandMemo;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public List<Providers> getProviders() {
		return providers;
	}

	public void setProviders(List<Providers> providers) {
		this.providers = providers;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", brandName=" + brandName + ", brandMemo=" + brandMemo + ", items=" + items
				+ ", providers=" + providers + "]";
	}
}
