/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.onetoone.tab
 * TabUserChildren.java
 * 创建人:maerhuan 
 * 时间：2016年11月18日-下午4:55:45 
 * 2016潭州教育公司-版权所有
 */
package com.meh.onetoone.tab;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "tz_tab_user_onetoone_children")
public class TabUserChildren implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String address;
	private String idcard;
	private String male;
	private String introduce;
	private String hobbys;
	private Date birthDay;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getHobbys() {
		return hobbys;
	}

	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}