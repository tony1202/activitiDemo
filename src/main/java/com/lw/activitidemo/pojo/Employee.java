package com.lw.activitidemo.pojo;

import javax.persistence.*;

/**
 * 用户表
 */
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//主键I
	@Column
	private String name;//用户
	@Column
	private String password;//密码
	@Column
	private String email;//电子邮箱
	@Column
	private String role;//角色
	@Column
	private Long manager_id;

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	@Transient
	private Employee manager;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	
}

