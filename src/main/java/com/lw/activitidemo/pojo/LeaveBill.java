package com.lw.activitidemo.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 请假单
 */
@Table(name = "leaveBill")
public class LeaveBill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//主键ID
	@Column
	private Integer days;// 请假天数
	@Column
	private String content;// 请假内容
	@Column(name = "leaveDate",columnDefinition = "leaveDate")
	private Date leaveDate = new Date();// 请假时间
	@Column
	private String remark;// 备注
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Employee user;// 请假人
	@Column
	private Integer state=0;// 请假单状态 0初始录入,1.开始审批,2为审批完成

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
