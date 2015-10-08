package com.dwnews.storemanagement.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name = "categories", catalog = "dwnews_store_management")
@DynamicInsert(true)
public class Categories implements Serializable{
	
	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	private Integer id;

	/**
	 * 类别名称
	 */
	@Column(name = "category_name", nullable = false, length=32)
	private String categoryName;
	
	/**
	 * 上级类别编号
	 */
	@Column(name = "category_parent_id", nullable = false)
	private Integer parentId;
	
	/**
	 * 备注
	 */
	@Column(name = "category_memo", length = 128)
	private String categoryMemo;
	
	/**
	 * 类别创建时间
	 */
	@Column(name = "category_create_time", nullable = false)
	private Date createTime;
	
	/**
	 * 类别下产品
	 */
	@OneToMany(mappedBy="category")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Items> items;
	
	/**
	 * 订单中产品类别
	 */
	@OneToMany(mappedBy="itemCategory")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<ItemsInputOutput> bills;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCategoryMemo() {
		return categoryMemo;
	}

	public void setCategoryMemo(String categoryMemo) {
		this.categoryMemo = categoryMemo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public List<ItemsInputOutput> getBills() {
		return bills;
	}

	public void setBills(List<ItemsInputOutput> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "Categories [id=" + id + ", categoryName=" + categoryName + ", parentId=" + parentId + ", categoryMemo="
				+ categoryMemo + ", createTime=" + createTime + "]";
	}

}
