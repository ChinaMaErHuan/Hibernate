/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.selfyingshe
 * Folder.java
 * 创建人:maerhuan 
 * 时间：2016年12月1日-下午7:33:54 
 * 2016潭州教育公司-版权所有
 */
package com.meh.selfyingshe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tz_folder")
public class Folder implements Serializable {
	private static final long serialVersionUID = 1L;
	/*主键id*/
	private Integer id;
	/*文件夹名称*/
	private String name;
	/*创建时间*/
	private Date createTime;
	/*父文件夹*/
	private Folder parent;
	/*子文件夹*/
	private List<Folder> folders = new ArrayList<Folder>(0);
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",nullable=false,length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",nullable=false,columnDefinition="TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public Folder getParent() {
		return parent;
	}
	public void setParent(Folder parent) {
		this.parent = parent;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="parent")
	public List<Folder> getFolders() {
		return folders;
	} 
	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	} 

}
