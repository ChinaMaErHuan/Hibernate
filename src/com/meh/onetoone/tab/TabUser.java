/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.onetoone.tab
 * TabUser.java
 * 创建人:maerhuan 
 * 时间：2016年11月18日-下午4:55:19 
 * 2016潭州教育公司-版权所有
 */
package com.meh.onetoone.tab;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tz_tab_user_onetoone")
public class TabUser implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private TabUserChildren userChildren;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="username",length=100,nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password",length=100,nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@OneToOne(targetEntity=TabUserChildren.class,cascade=CascadeType.ALL)
	@JoinTable(name="tz_tab_user_chlidren",joinColumns={
			@JoinColumn(name="user_id",referencedColumnName="id",unique=true)		
	},inverseJoinColumns={
			@JoinColumn(name="children_id",referencedColumnName="id",unique=true)
	})
	public TabUserChildren getUserChildren() {
		return userChildren;
	}
	public void setUserChildren(TabUserChildren userChildren) {
		this.userChildren = userChildren;
	}
	
}
