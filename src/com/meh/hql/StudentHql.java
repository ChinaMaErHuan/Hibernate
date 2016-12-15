package com.meh.hql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tz_hql_student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class StudentHql implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String studentName;
	private String studentCode;
	
	private Float score;
	
	private ClassRoom classRoom;

	public StudentHql() {

	}

	public StudentHql(Integer id) {
		this.id = id;
	}
	

	public StudentHql(Integer id, String studentName, String studentCode,
			ClassRoom classRoom) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.studentCode = studentCode;
		this.classRoom = classRoom;
	}
	
	public StudentHql(Integer id, String studentName, String studentCode) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.studentCode = studentCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	

	@Column(name = "student_name", length = 100)
	public String getStudentName() {
		return studentName;
	}

	

	@Column(name = "student_code", length = 100)
	public String getStudentCode() {
		return studentCode;
	}

	
	@JSON(serialize=false)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "classroom_id")
	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
	
	@Column(name="score",columnDefinition="float(10,2) default '0.00'")
	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	

	
	
	
}
