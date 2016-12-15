/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.manytoone
 * MAddress.java
 * 创建人:maerhuan 
 * 时间：2016年11月18日-下午10:16:23 
 * 2016潭州教育公司-版权所有
 */
package com.meh.manytoone;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tz_manytoone_address")
public class MAddress implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="address_name",length=100,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
