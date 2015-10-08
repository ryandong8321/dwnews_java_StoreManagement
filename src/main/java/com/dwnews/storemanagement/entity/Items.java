package com.dwnews.storemanagement.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name = "items", catalog = "dwnews_store_management")
@DynamicInsert(true)
public class Items implements Serializable{
	
	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	private Integer id;
	
	/**
	 * 产品名称
	 */
	@Column(name = "item_name", nullable = false, length=32)
	private String itemName;
	
	/**
	 * 产品单位
	 */
	@Column(name = "item_unit", nullable = true, length=4)
	private String itemUnit;

	/**
	 * 产品价格
	 */
	@Column(name = "item_price", nullable = false)
	private Double itemPrice;
	
	/**
	 * 产品二维码
	 */
	@Column(name = "item_bar_code", nullable = false, length=128)
	private String itemBarCode;

	/**
	 * 产品库存
	 */
	@Column(name = "item_store_count", nullable = false)
	private Integer itemStoreCount;
	
	/**
	 * 产品图片位置
	 */
	@Column(name = "item_photo", nullable = true, length=128)
	private String itemPhoto;
	
	/**
	 * 产品规格
	 */
	@Column(name = "item_standard", nullable = true, length=64)
	private String itemStandard;
	
	/**
	 * 产品品牌
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_brand_id")
	private Brand brand;
	
	/**
	 * 产品类别
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_category_id")
	private Categories category;
	
	/**
	 * 产品供货商
	 */
	@ManyToMany
	@JoinTable(name="providers_items_relationship", 
		joinColumns={ @JoinColumn(name="relationship_items_id") }, inverseJoinColumns={ @JoinColumn(name="relationship_provider_id") })
	private List<Providers> providers;
	
	/**
	 * 产品所在订单
	 */
	@OneToMany(mappedBy="billItem")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<ItemsInputOutput> bills;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemBarCode() {
		return itemBarCode;
	}

	public void setItemBarCode(String itemBarCode) {
		this.itemBarCode = itemBarCode;
	}

	public Integer getItemStoreCount() {
		return itemStoreCount;
	}

	public void setItemStoreCount(Integer itemStoreCount) {
		this.itemStoreCount = itemStoreCount;
	}

	public String getItemPhoto() {
		return itemPhoto;
	}

	public void setItemPhoto(String itemPhoto) {
		this.itemPhoto = itemPhoto;
	}

	public String getItemStandard() {
		return itemStandard;
	}

	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public List<Providers> getProviders() {
		return providers;
	}

	public void setProviders(List<Providers> providers) {
		this.providers = providers;
	}

	public List<ItemsInputOutput> getBills() {
		return bills;
	}

	public void setBills(List<ItemsInputOutput> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", itemName=" + itemName + ", itemUnit=" + itemUnit + ", itemPrice=" + itemPrice
				+ ", itemBarCode=" + itemBarCode + ", itemStoreCount=" + itemStoreCount + ", itemPhoto=" + itemPhoto
				+ ", itemStandard=" + itemStandard + "]";
	}
}
