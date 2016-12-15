/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.manytomany.single
 * StudentMantToMany.java
 * 创建人:maerhuan 
 * 时间：2016年11月21日-下午11:15:50 
 * 2016潭州教育公司-版权所有
 */
package com.meh.manytomany.single;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 课程表
 * 
 * StudentMantToMany 创建人:maerhuan 时间：2016年11月21日-下午11:16:28
 * 
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "tz_manytomany_course")
public class CourseManyToMany implements Serializable{
	
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
	@Column(name="course_name",nullable=false,length=80)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
