/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.hql
 * SQLWhere.java
 * 创建人:maerhuan 
 * 时间：2016年12月10日-下午6:12:08 
 * 2016潭州教育公司-版权所有
 */
package com.meh.hql;

import java.util.List;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class SQLWhere {
	@Test
	public void handleBase(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		String sqlString = "";
		Query query = session.createQuery(sqlString);
		List list = query.list();
		
		session.close();
		sessionFactory.close();
	}
	//运算符：> < != <> = like 
	//逻辑运算符 and, or, not,  not in, in ,between and,is null, is not null ,is empty,is not empty exists,not exists 
	@Test
	public void handle1(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		String sqlString = "from StudentHql where studentName like ?";
		String sqlString2 = "from StudentHql where score  > ?";
		String sqlString3 = "from StudentHql where score  < ?";
		String sqlString4 = "from StudentHql where score <> ?";
		String sqlString5 = "from StudentHql where score = ?";
		
		
		Query query = session.createQuery(sqlString).setString(0, "meh");
		List<StudentHql> list = query.list();
		for (StudentHql studentHql : list) {
			System.out.println(studentHql.getStudentName());
		}
		//逻辑运算符 and, or, not, not, in, between and, is null, is not null,is empty, is not empty exists, 
		//	mysql 不支持is empty	
		//String hql = "from StudentHql where id = 6 or studentName = '宋书毅'";
//		String hql = "from StudentHql where not(id = 6 or studentName = '宋书毅')";
//		String hql = "from StudentHql where score between 78 and 100";//等价于"from Student where score >=78 and score <=100"
//		String hql = "from Student where score >=78 and score <=100";
//		String hql = "from StudentHql where classRoom.id is null";
//		String hql = "from StudentHql where classRoom.id is not null";
//		String hql = "from StudentHql s where s.studentName is empty";
//		String hql = "from StudentHql s where s.studentName is not empty";
//		String hql = "from StudentHql where id in(1,5,6)";
//		String hql = "from StudentHql where id not in(1,5,6)";
//		List<StudentHql> students = session.createQuery(hql).list();
//		for (StudentHql student : students) {
//			println(student);
//		}
		session.close();
		sessionFactory.close();
	}
	@Test
	public void handle5() throws JSONException{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		String sqlString = "from ClassRoom c  where c.students.size>0";
		String sqlString2 = "from ClassRoom c where size(c.students)>0";
		Query query = session.createQuery(sqlString);
		List<ClassRoom> list = query.list();
		System.out.println(JSONUtil.serialize(list));
		
		session.close();
		sessionFactory.close();
	}
	
	
	//总结一下hql查询或者sql查询的：
	/**
	 * 单表操作：
	 * hql:
	 * from Student   -----  List<StudentHql>
	 * select new StudentHql(属性1,属性2)-----  List<StudentHql>
	 * select 属性1,属性2 from Student ------List<Object[]> 可以用setResultTransformser()
	 * sql:
	 * 默认的都是数组 可以用addEntity转换对象 必须查询的是全部属性
	 * 还可以一用setResultTransformer 转换 必须起别名 和bean中的属性保持高度一致
	 * 
	 * 多表操作：
	 * hql:
	 * 	from StudentHql s inner join s.classRoom c ------List<Object []>
	 * 	from Student,Classroom List<Object[]> 放了两个对象的地址 get(0/1)
	 *  select s from Student s ,ClassRoom c ----List<Student>
	 *  select c from Student s ,ClassRoom c ----List<Classroom>
	 *  select c from Student s ,ClassRoom c ------List<Classroom> 
	 */
	//setResultTransformer(Transformers.aliasToBean(StudentHql.class));
	//sqlquery中查询两个表的属性 如果两个表都有id  要起别名
	
	// 返回List 在hql语句中new List  或者 query.setResultTransformer(Transformers.TO_LIST).list()
	
	// 返回Map 在hql语句中new Map  或者 query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list()
	//	new Map  这种方式查询的时候 不指定列的别名的时候 hibernate默认的会给map创建Integer类型的key  
	// hql qbc qbe 一对多的查询是不支持的 意思就是 我们举的例子 都是学生找教室  没有从教室一端去找学生的
}
