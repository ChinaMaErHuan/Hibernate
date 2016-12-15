package com.meh.hql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "tz_hql_classroom")
public class ClassRoom implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String className;
	private String departmentName;
	private List<StudentHql> students = new ArrayList<StudentHql>(0);

	public ClassRoom(){
		
	}
	
	public ClassRoom(Integer id){
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="class_name")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name="department_name")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="classRoom")
	@OrderBy("studentCode desc")
	public List<StudentHql> getStudents() {
		return students;
	}

	public void setStudents(List<StudentHql> students) {
		this.students = students;
	}
}
