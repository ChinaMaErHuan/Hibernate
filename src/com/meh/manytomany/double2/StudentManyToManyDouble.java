/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.manytomany.single
 * StudentMantToMany.java
 * 创建人:maerhuan 
 * 时间：2016年11月21日-下午11:15:50 
 * 2016潭州教育公司-版权所有
 */
package com.meh.manytomany.double2;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 学生表
 * 
 * StudentMantToMany 创建人:maerhuan 时间：2016年11月21日-下午11:16:28
 * 
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "tz_manytomany_student_double")
public class StudentManyToManyDouble implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Set<CourseManyToManyDouble> courses = new HashSet<CourseManyToManyDouble>(0);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="student_name",nullable=false,length=80)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(targetEntity=CourseManyToManyDouble.class,cascade=CascadeType.ALL)
	@JoinTable(name="tz_manytomany_students_courses_double",
	joinColumns={@JoinColumn(name="student_id")},
	inverseJoinColumns={@JoinColumn(name="course_id")}
	)
	public Set<CourseManyToManyDouble> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseManyToManyDouble> courses) {
		this.courses = courses;
	}
	
	

}
