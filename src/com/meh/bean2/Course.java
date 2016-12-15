package com.meh.bean2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tz_course")
public class Course extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String courseName;
	private float price;

	public Course() {

	}

	public Course(Integer id) {
		super(id);
	}

	@Column(name = "course_name")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "price")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
