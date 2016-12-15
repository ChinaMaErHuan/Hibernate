/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.onetomany
 * PersononeTomany.java
 * 创建人:maerhuan 
 * 时间：2016年11月21日-下午2:28:20 
 * 2016潭州教育公司-版权所有
 */
package com.meh.onetomany.double2;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tz_onetomany_person")
public class PersonOneToMany implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private CityOneToMany city;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	@Column(name="name",length=80,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//manytoone默认的是eager
	//onetomany默认的是lazy
	@ManyToOne(targetEntity=CityOneToMany.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="city_id")
	public CityOneToMany getCity() {
		return city;
	}

	public void setCity(CityOneToMany city) {
		this.city = city;
	}
}
