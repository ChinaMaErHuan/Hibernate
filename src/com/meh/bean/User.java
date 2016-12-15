package com.meh.bean;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * User<BR>
 * 创建人:潭州学院-keke <BR>
 * 时间：2015年3月5日-下午11:23:24 <BR>
 * 
 * @version 1.0.0
 * 
 */
/**
 * 1:@Table元素包括了一个schema 和一个 catalog属性,如果需要可以指定相应的值.
 * 结合使用@UniqueConstraint注解可以定义表的唯一约束(unique constraint)
 * (对于绑定到单列的唯一约束,请参考@Column注解) 如：
 * 
 * @Table(name = "user", uniqueConstraints = {
 * @UniqueConstraint(name ="account_password", columnNames = {"account",
 *                        "password" }) })
 *                        2:如果一个pojo中没有给属性或者get加上注解的时候，默认的注解是@Basic，
 *                        3:实体bean中所有的非static非transient的属性都可以被持久化,
 *                        除非你将其注解为@Transient.所有没有定义注解的属性等价于在其上面添加了@Basic注解. 通过
 *                        @Basic注解可以声明属性的获取策略(fetch strategy)：
 * 
 * 4:@Formula("(数据库语法和sql语句)")如：@Formula("CONCAT(username,`account`)"),说明用formula注解的字段不需要在数据表中生成对应的字段，它只是一种虚拟的列。
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(name = "account_password", columnNames = { "account",
				"password" }),
		@UniqueConstraint(name = "username", columnNames = { "username" }) })
public class User implements java.io.Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	private Integer id;// 11
	private String username;// 255
	private String password;
	private String account;
	private Integer age;
	private Float money;
	private Integer isDelete;
	private Integer version;
	private static Integer ages;
	private String content;
	private String uuid;
	
	
	@Embedded
	private Teacher teacher;

	public User() {

	}

	public User(Integer id) {
		this.id = id;
	}

	public User(String username, String password, String account,
			Integer isDelete) {
		super();
		this.username = username;
		this.password = password;
		this.account = account;
		this.isDelete = isDelete;
	}
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	 @GenericGenerator(name="systemUUID",strategy="uuid")
	 @GeneratedValue(generator="systemUUID")
	 @Column(name = "uuid", insertable = true, updatable = true, nullable = false)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	@Column(name = "username", length = 50, nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(insertable = false, updatable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "account", length = 50, nullable = false, unique = true)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "isDelete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@javax.persistence.Transient
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "money", precision = 8, scale = 2)
	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	@Version
	@Column(name = "OPTLOCK")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public static Integer getAges() {
		return ages;
	}

	public static void setAges(Integer ages) {
		User.ages = ages;
	}
	
	//@Formula("CONCAT(username,`account`)")
//	@Generated("insert")
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
