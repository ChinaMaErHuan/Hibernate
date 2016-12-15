/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean2
 * BaseEntity.java
 * 创建人:maerhuan 
 * 时间：2016年11月14日-下午3:13:00 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean2;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//不需要再hibernate.cfg.xml中映射
@MappedSuperclass
public class BaseEntity implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer isDelete;
	private Date createTime;
	private Date updateTime;
	
	public BaseEntity(){
		
	}
	public BaseEntity(Integer id){
		this.id=id;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="is_delete",length=1)
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",columnDefinition="TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
