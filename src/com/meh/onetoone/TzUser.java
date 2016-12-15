package com.meh.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tz_user_onetoone")
/**
 * 总结一下 一对一有三种实现方式 
 * 第一种：
 * 有一张表的一个主键是另一张表的主键  通过主键来维护关系 详情参照test数据库中的 tz_student 和tz_school查看
 * 第二种是最常见的：
 * 两张表的主键都是各自的主键 只不过是 其中一个表的主键被当作是另一个表的外键了 来维护关系 tz_user_onetoone tz_user_onetoone_children
 * 第三种是最另类的 ：
 * 通过中间表来维护关系 tz_tab_user_onetoone 作为参照可以查看
 * 
 * TzUser
 * 创建人:maerhuan 
 * 时间：2016年11月21日-下午11:40:32 
 * @version 1.0.0
 *
 */
public class TzUser implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	/*子类*/
	private TzUserChildren userChildren;
	
	//单向的一对一取决于你操作的对象是谁，在配置在操作对象里面
	//双向
	// 在业务开发一个表中可能会几十个列，甚至100多个列
	// 查询性能，维护成本都加大
	// 拆表。拆成两个表

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//双向里面一定是用mappedBy,一般都是配置主表子对象中，
	//单向里面用mappedBy没意义
	//@JoinColumn注解定义了联接列(join column). 该注解和@Column注解有点类似, 
	//但是多了一个名为referencedColumnName的参数. 该参数定义了所关联目标实体中的联接列. 
	//注意,当referencedColumnName关联到非主键列的时候, 关联的目标类必须实现Serializable, 
	//还要注意的是所映射的属性对应单个列(否则映射无效).如果在主体没有声明@JoinColumn,系统自动进行处理： 
	//在主表(owner table)中将创建联接列, 列名为：主体的关联属性名＋下划线＋被关联端的主键列名. 
	//在上面这个例子中是userChildren_id

 
	//mappedBy的意思是交给谁来管理    mappedBy="user"要和从表中你包含的主表的属性一致
	@OneToOne(targetEntity=TzUserChildren.class,cascade=CascadeType.ALL,mappedBy="user")
	public TzUserChildren getUserChildren() {
		return userChildren;
	}
	
	public void setUserChildren(TzUserChildren userChildren) {
		this.userChildren = userChildren;
	}
	
}