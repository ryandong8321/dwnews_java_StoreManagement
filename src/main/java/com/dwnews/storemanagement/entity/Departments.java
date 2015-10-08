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
@Table(name = "departments", catalog = "dwnews_store_management")
@DynamicInsert(true)
public class Departments implements Serializable{
	
	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "department_id", unique = true, nullable = false)
	private Integer id;

	/**
	 * 部门名称
	 */
	@Column(name = "department_name", nullable = false, length=32)
	private String departmentName;

	/**
	 * 上级部门编号
	 */
	@Column(name = "department_parent_id", nullable = false)
	private Integer parentId;

	/**
	 * 部门领导名称
	 */
	@Column(name = "department_leader_name", length = 32, nullable = true)
	private String leaderName;

	/**
	 * 部门创建时间
	 */
	@Column(name = "department_create_time", nullable = false)
	private Date createTime;
	
	/**
	 * 部门相关订单
	 */
	@OneToMany(mappedBy="billDepartment")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<ItemsInputOutput> bills;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<ItemsInputOutput> getBills() {
		return bills;
	}

	public void setBills(List<ItemsInputOutput> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "Departments [id=" + id + ", departmentName=" + departmentName + ", parentId=" + parentId
				+ ", leaderName=" + leaderName + ", createTime=" + createTime + "]";
	}
}
