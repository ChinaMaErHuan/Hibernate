/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.hql
 * SqlTest2.java
 * 创建人:maerhuan 
 * 时间：2016年12月10日-下午4:06:02 
 * 2016潭州教育公司-版权所有
 */
package com.meh.hql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class SqlTest2 {
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
	//根据id查询 返回单条数据的情况
	@Test
	public void handle2(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		String sqlString = "from StudentHql where id = 2";
		Query query = session.createQuery(sqlString);
		StudentHql student = (StudentHql) query.uniqueResult();
		println(student);
		
		session.close();
		sessionFactory.close();
	}
	//你可以用object数组接收 或者直接转换对象
	@Test
	public void handle3(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		String sqlString = "select * from tz_hql_student where id = 2";
		Query query = session.createSQLQuery(sqlString).addEntity(StudentHql.class);
		StudentHql studentHql = (StudentHql) query.uniqueResult();
		println2(studentHql);
		session.close();
		sessionFactory.close();
	}

	// hibernate 框架还有一个好处就是 hibernate.get()或者load方法 在获取单个对象的时候 就不必去写sql语句了
	// 从缓存中去查找
	// 在hibernate中 对单个对象的操作 完全可以不用写sql语句 直接可以通过方法操作
	// session.delete(object);
	// session.update(object);
	// session.save(object);
	// session.load(theClass, id);  具有延迟加载的功能 只有在使用到对象的=具体属性的时候才会去
	// session.get(clazz, id);  立即到数据库中去查找
	//所以在来发中 缓存是有好处的 主键查询最好用load或者get方法
	@Test
	public void handle4(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		StudentHql studentHql = (StudentHql) session.get(StudentHql.class, 2);
		System.out.println(studentHql.getStudentName());
		session.close();
		sessionFactory.close();
	}
	//聚合函数 
	//max min avg sum count 他们都有一个共同点 之返回一个值  Number
	@Test
	public void handle5(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
//		String sqlString = "SELECT AVG(score) from tz_hql_student where 1 = 1";
//		Query query = session.createSQLQuery(sqlString);
		
		String hqlString = "SELECT avg(s.score) from StudentHql as  s ";
		String hqlString2 = "SELECT count(1) from StudentHql as  s ";
		String hqlString3 = "SELECT max(s.score) from StudentHql as  s ";
		String hqlString4 = "SELECT min(s.score) from StudentHql as  s ";
		String hqlString5 = "SELECT sum(s.score) from StudentHql as  s ";
		Query query = session.createQuery(hqlString5);
		Number number = (Number) query.uniqueResult();
		Float float1 = (number==null?0f:number.floatValue());
		System.out.println(float1);
		session.close();
		sessionFactory.close();
	}
	
	private void println(StudentHql student){
		System.out.println(student.getId()+"=="+student.getStudentName()+"===="+student.getScore()+"==="+student.getStudentCode()+"==="+(student.getClassRoom().getId()==null?null:student.getClassRoom().getId()));
	}
	
	private void println2(StudentHql student){
		System.out.println(student.getId()+"=="+student.getStudentName()+"==="+student.getStudentCode()+"===");
	}
}
