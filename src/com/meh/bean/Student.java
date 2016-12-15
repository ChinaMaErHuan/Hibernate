/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean
 * Student.java
 * 创建人:maerhuan 
 * 时间：2016年11月13日-下午5:31:58 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.UniqueConstraint;

/*
 * @UniqueConstraint(name = "tz_index_name", columnNames =
 * {"数据库的列名1","数据库的列名2",.....})
 * 
 * 
 * @Column( name="columnName"; (1) boolean unique() default false; (2) boolean
 * nullable() default true; (3) boolean insertable() default true; (4) boolean
 * updatable() default true; (5) String columnDefinition() default ""; (6)
 * String table() default ""; (7) int length() default 255; (8) int precision()
 * default 0; // decimal precision (9) int scale() default 0; // decimal scale
 * (1) name 可选,列名(默认值是属性名) (2) unique 可选,是否在该列上设置唯一约束(默认值false) (3) nullable
 * 可选,是否设置该列的值可以为空(默认值true) (4) insertable 可选,该列是否作为生成的insert语句中的一个列(默认值true)
 * (5) updatable 可选,该列是否作为生成的update语句中的一个列(默认值true) (6) columnDefinition 可选:
 * 为这个特定列覆盖SQL DDL片段 (这可能导致无法在不同数据库间移植) (7) table 可选,定义对应的表(默认为主表) (8) length
 * 可选,列长度(默认值255) (8) precision 可选,列十进制精度(decimal precision)(默认值0) (10) scale
 * 可选,如果列十进制数值范围(decimal scale)可用,在此设置(默认值0)
 */
@Entity
@Table(name="student",indexes={
		@Index(name="tz_address",columnList="address"),
		@Index(name = "tz_address_age", columnList = "address,age"),
		@Index(name = "tz_address_age_name", columnList = "address,money,name", unique = true)
		},
		uniqueConstraints={
		@UniqueConstraint(name = "tz_index_code", columnNames = "code"),
		@UniqueConstraint(name="tz_index_name_age",columnNames={"name","age"})
})
public class Student implements java.io.Serializable{
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer age;
	private String code;
	private String title;
	private Double money;
	private String message;
	private byte[] imageCode;
	private Boolean flag;
	private Integer isDelete;
	private String text;
	private String address;
	
	//嵌入式对象 可以覆盖属性 必须在被嵌入的对象的bean的注解上写@Embeddable
	
	private Teacher teacher;
	
	public Student(){
		
	}
	
	public Student(Integer id){
		this.id = id;
	}
	/**
	 * 使用@Id注解可以将实体bean中的某个属性定义为标识符(identifier). 该属性的值可以通过应用自身进行设置,
	 * 也可以通过Hiberante生成(推荐). 使用 @GeneratedValue注解可以定义该标识符的生成策略: AUTO -
	 * 可以是identity column类型,或者sequence类型或者table类型,取决于不同的底层数据库. TABLE - 使用表保存id值
	 * IDENTITY - identity column SEQUENCE - sequence oracle
	 * 
	 * @return
	 */
	// 主键
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	//上面的唯一约束会失效
	@Column(name="name",length=100,nullable=false, unique=true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="age",length=20)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name="code",length=30)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="title",length=100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="money",nullable=false ,columnDefinition="double(10,2) default '0.00'")
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Column(name="message",length=100)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Lob
	@Column(name="image_code")
	public byte[] getImageCode() {
		return imageCode;
	}
	public void setImageCode(byte[] imageCode) {
		this.imageCode = imageCode;
	}
	@Column(name="flag")
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	@Column(name="is_delete",length=1)
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	//@Lob注解表示属性将被持久化为Blob或者Clob类型, 具体取决于属性的类型, java.sql.Clob, Character[], char[] 和 java.lang.String这些类型的属性都被持久化为Clob类型, 
	//而java.sql.Blob, Byte[], byte[] 和 serializable类型则被持久化为Blob类型.
	@Lob
	//延迟加载
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "text", columnDefinition = "longtext")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Column(name="address",length=50)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Embedded
	 @AttributeOverrides( {
	   @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
	   @AttributeOverride(name = "lastName", column = @Column(name = "last_name")) })
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
