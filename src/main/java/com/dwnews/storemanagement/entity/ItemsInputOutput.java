package com.dwnews.storemanagement.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@SuppressWarnings("serial")
@Entity
@Table(name = "items_input_output", catalog = "dwnews_store_management")
@DynamicInsert(true)
public class ItemsInputOutput implements Serializable{
	
	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bill_id", unique = true, nullable = false)
	private Integer id;
	
	/**
	 * 产品类别
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bill_category_id")
	private Categories itemCategory;
	
	/**
	 * 产品
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bill_item_id")
	private Items billItem;
	
	/**
	 * 操作类别(1:出库操作, 2:入库操作)
	 */
	@Column(name = "bill_operation_category", nullable = false)
	private Integer operationCategory;
	
	/**
	 * 相关部门
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bill_department_id")
	private Departments billDepartment;
	
	/**
	 * 产品二维码
	 */
	@Column(name = "bill_item_bar_code", nullable = false, length=128)
	private String itemBarCode;
	
	/**
	 * 产品库存
	 */
	@Column(name = "bill_count", nullable = false)
	private Integer billCount;
	
	/**
	 * 订单创建时间
	 */
	@Column(name = "bill_operation_time", nullable = false)
	private Date createTime;
	
	/**
	 * 产品二维码
	 */
	@Column(name = "bill_memo", nullable = true, length=128)
	private String billMemo;
	
	/**
	 * 订单审核状态(0:待审批, 1:审批通过, 2:审批不通过)
	 */
	@Column(name = "bill_verify", nullable = false)
	private Integer billVerify;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categories getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(Categories itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Items getBillItem() {
		return billItem;
	}

	public void setBillItem(Items billItem) {
		this.billItem = billItem;
	}

	public Integer getOperationCategory() {
		return operationCategory;
	}

	public void setOperationCategory(Integer operationCategory) {
		this.operationCategory = operationCategory;
	}

	public Departments getBillDepartment() {
		return billDepartment;
	}

	public void setBillDepartment(Departments billDepartment) {
		this.billDepartment = billDepartment;
	}

	public String getItemBarCode() {
		return itemBarCode;
	}

	public void setItemBarCode(String itemBarCode) {
		this.itemBarCode = itemBarCode;
	}

	public Integer getBillCount() {
		return billCount;
	}

	public void setBillCount(Integer billCount) {
		this.billCount = billCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBillMemo() {
		return billMemo;
	}

	public void setBillMemo(String billMemo) {
		this.billMemo = billMemo;
	}

	public Integer getBillVerify() {
		return billVerify;
	}

	public void setBillVerify(Integer billVerify) {
		this.billVerify = billVerify;
	}

	@Override
	public String toString() {
		return "ItemsInputOutput [id=" + id + ", operationCategory=" + operationCategory + ", itemBarCode="
				+ itemBarCode + ", billCount=" + billCount + ", createTime=" + createTime + ", billVerify=" + billVerify
				+ "]";
	}
}
