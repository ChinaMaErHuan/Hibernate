/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.manytoone
 * MPerson.java
 * 创建人:maerhuan 
 * 时间：2016年11月18日-下午10:14:55 
 * 2016潭州教育公司-版权所有
 */
package com.meh.manytoone;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="tz_manytoone_person")
public class MPerson implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	/*名称*/
	private String name;
	/*年龄*/
	private Integer age;
	/*子类*/
	private MAddress address;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",length=100,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="age",length=3)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@ManyToOne(targetEntity=MAddress.class,cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	public MAddress getAddress() {
		return address;
	}
	public void setAddress(MAddress address) {
		this.address = address;
	}

}
