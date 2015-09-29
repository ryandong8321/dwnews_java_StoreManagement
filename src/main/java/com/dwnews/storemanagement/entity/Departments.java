package com.dwnews.storemanagement.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

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

	@Override
	public String toString() {
		return "Departments [id=" + id + ", departmentName=" + departmentName + ", parentId=" + parentId
				+ ", leaderName=" + leaderName + ", createTime=" + createTime + "]";
	}
}
