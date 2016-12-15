/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean2
 * Person.java
 * 创建人:maerhuan 
 * 时间：2016年11月14日-下午4:31:19 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean2;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import java.sql.Blob;
@Entity
@Table(name="tz_person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	/*主键*/
	private Integer id;
	/*余额*/
	private Double money;
	/*分数*/
	private Float score;
	/*标志*/
	private Boolean mark;
	/*名字*/
	private String name;
	/*头像*/
	private byte[] imagecode;
	private Blob imagecode2;
	/*文章*/
	private String content;
	/*创建时间*/
	private Date createTime;
	/*更新时间*/
	private Date updateTime;
	/*生日*/
	private Date birthday;
	/*视频时长*/
	private Date videoTime;
	public Person(){
		
	}
	public Person(Integer id){
		this.id = id;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="money",columnDefinition="double(10,2) default '0.00'")
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	@Column(name="score",columnDefinition="float(10,2) default '0.00'")
	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	@Column(name="mark")
	public Boolean getMark() {
		return mark;
	}

	public void setMark(Boolean mark) {
		this.mark = mark;
	}
	@Column(name="name",length=100,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="image_code")
	public byte[] getImagecode() {
		return imagecode;
	}

	public void setImagecode(byte[] imagecode) {
		this.imagecode = imagecode;
	}
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="image_code2")
	public Blob getImagecode2() {
		return imagecode2;
	}

	public void setImagecode2(Blob blob) {
		this.imagecode2 = blob;
	}
	@Lob//longtext 大文本内容
	@Basic(fetch=FetchType.LAZY)
	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",columnDefinition="TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="update_time",columnDefinition="date")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="birthday")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Temporal(TemporalType.TIME)
	@Column(name="video_time")
	public Date getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(Date videoTime) {
		this.videoTime = videoTime;
	}

	/**
	 * 这里有必要说明 当你的数据库表字段要使用到日期的时候 :
	 * 例如： Date update_time; 
	 * 写明注解的时候 默认就是datetime类型的
	 * 如果你要指定其他类型的话： 
	 * 1.如果要指明timestamp类型的话 必须要配合使用：
	 * 
	 * @Temporal(TemporalType.TIMESTAMP)
	 * @Column(name="create_time",columnDefinition="TIMESTAMP NULL DEFAULTCURRENT_TIMESTAMP")来使用
	 *   2.如果是time或者date类型的话
	 * @Temporal(TemporalType.TIME)和columndefinition随便一种都可以使用
	 * 
	 * 
	 */
	
	// @Transient
	// private String[] friends;
	// @Transient
	// private List<String> banks = new ArrayList<String>(0);
	// @Transient
	// private Set<Integer> numbers = new HashSet<Integer>(0);
	// @Transient
	// private Map<String, Integer> map = new HashMap<String, Integer>();
	//
	// // 排序类集合和map
	// @Transient
	// private SortedSet<Integer> sortNumbers = new TreeSet<Integer>();
	// @Transient
	// private Map<String, Integer> map2 = new TreeMap<String, Integer>();

	// 如果一个自定被@Transient 或者定义为static成员是不会同步表.
	



	private String mart;
	@Transient
	public String getMart() {
		return mart;
	}
	public void setMart(String mart) {
		this.mart = mart;
	}

}
