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
import javax.persistence.ManyToMany;
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
@Table(name = "tz_manytomany_course_double")
public class CourseManyToManyDouble implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Set<StudentManyToManyDouble> students = new HashSet<StudentManyToManyDouble>(0);
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
	@ManyToMany(targetEntity=StudentManyToManyDouble.class,cascade=CascadeType.ALL,mappedBy="courses")
	public Set<StudentManyToManyDouble> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentManyToManyDouble> students) {
		this.students = students;
	}
//	mappedBy： 
//	1>只有OneToOne，OneToMany，ManyToMany上才有mappedBy属性，ManyToOne不存在该属性； 
//	2>mappedBy标签一定是定义在被拥有方的，他指向拥有方； 
//	3>mappedBy的含义，应该理解为，拥有方能够自动维护跟被拥有方的关系，当然，如果从被拥有方，
//	通过手工强行来维护拥有方的关系也是可以做到的； 
//	4>mappedBy跟joinColumn/JoinTable总是处于互斥的一方，可以理解为正是由于拥有方的关联被拥有方的字段
//	存在，拥有方才拥有了被拥有方。mappedBy这方定义JoinColumn/JoinTable总是失效的，
//	不会建立对应的字段或者表。 
	

}
