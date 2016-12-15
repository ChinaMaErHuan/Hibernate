package com.meh.onetoone;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "tz_user_onetoone_children")
public class TzUserChildren implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String address;
	private String idcard;
	private String male;
	private String introduce;
	private String hobbys;
	private Date birthDay;
	
	
	
	
	private TzUser user;
	
	//我要查询出生日都是2015年出生的用户
	
	
	
	//在业务开发一个表中可能会几十个列，甚至100多个列
	//查询性能，维护成本都加大
	//拆表。拆成两个表

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

	@OneToOne(targetEntity=TzUser.class)
	@JoinColumn(name="user_fk",unique=true)
	public TzUser getUser() {
		return user;
	}

	public void setUser(TzUser user) {
		this.user = user;
	}
}