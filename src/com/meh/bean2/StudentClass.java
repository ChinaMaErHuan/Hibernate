/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean2
 * StudentClass.java
 * 创建人:maerhuan 
 * 时间：2016年11月15日-下午6:44:08 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean2;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="tz_student")
public class StudentClass implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	private List<String> school;
	
	private Set<String> friends;
	
	private Map<String, Float> map;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ElementCollection(targetClass=String.class)
	@CollectionTable(name="tz_school",joinColumns={@JoinColumn(name="student_id",nullable=false,referencedColumnName="id")})
	@Column(name="school_name",nullable=false)
	@OrderColumn(name="list_order")
	public List<String> getSchool() {
		return school;
	}
	public void setSchool(List<String> school) {
		this.school = school;
	}
	
	@ElementCollection(targetClass=String.class)
	@CollectionTable(name="tz_friends",joinColumns={@JoinColumn(name="student_id",nullable=false,referencedColumnName="id")})
	@Column(name="firends_name",nullable=false)
	public Set<String> getFriends() {
		return friends;
	}
	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}
	
	
	@ElementCollection(targetClass=Float.class)
	@CollectionTable(name="tz_students_scores",joinColumns={@JoinColumn(name="students_id",nullable=false,referencedColumnName="id")})
	@MapKeyClass(String.class)
	@MapKeyColumn(name="subject_name")
	@Column(name="score")
	public Map<String, Float> getMap() {
		return map;
	}
	public void setMap(Map<String, Float> map) {
		this.map = map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
