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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tz_onetomany_city")
public class CityOneToMany implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private List<PersonOneToMany> persons = new ArrayList<PersonOneToMany>(0);
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
	//默认就是lazy 不需要一下子就查出来 用到的时候查就okay
	//我们在配置双向的一对多的时候 必须要使用mappedby来让其中一张表维护关系 不维护的话 sql语句会执行多次 浪费资源 一般是交给多的一方来维护关系
	@OneToMany(targetEntity=PersonOneToMany.class,fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy="city")
	public List<PersonOneToMany> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonOneToMany> persons) {
		this.persons = persons;
	}	
}
