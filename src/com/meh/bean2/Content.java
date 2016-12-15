package com.meh.bean2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tz_content")
public class Content extends BaseEntity {

	
	
	private static final long serialVersionUID = 1L;
	private String name;

	public Content() {}

	public Content(Integer id) {
		super(id);
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
