package com.meh.onetomany.single;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tz_customer")
/**
 * 单向的onetomany会生成中间表来维护关系
 * 
 * Customer
 * 创建人:maerhuan 
 * 时间：2016年11月21日-下午11:39:48 
 * @version 1.0.0
 *
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Set<Ticket> tickets = new HashSet<Ticket>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//onetomany对应的inverseJoincolumns unique 默认是true 唯一的  可以理解 一个人可以有多张火车票 但是不能同时拥有相同的火车票 或者 
	//两个人bu能同时买这张火车票
	@OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tz_customer_ticket", joinColumns = { @JoinColumn(name = "customer_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "ticket_id", referencedColumnName = "id",unique=true) })
	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tickets == null) ? 0 : tickets.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tickets == null) {
			if (other.tickets != null)
				return false;
		} else if (!tickets.equals(other.tickets))
			return false;
		return true;
	}



	
	
	

}
