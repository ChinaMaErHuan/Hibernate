/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.ehcache
 * TestEhcache.java
 * 创建人:maerhuan 
 * 时间：2016年12月12日-下午11:35:35 
 * 2016潭州教育公司-版权所有
 */
package com.meh.ehcache;

import java.util.List;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import com.meh.hql.StudentHql;

public class TestEhcache {
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
	
	@Test
	public void handle1() throws JSONException{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String sqlString = "from StudentHql";
		//setCacheable()是必须的 前提是在hibernate.cfg.xml中配置 查询缓存 Query或Criteria接口查询时设置其setCacheable(true)： 
		Query query = session.createQuery(sqlString).setCacheable(true);
		List<StudentHql> list = query.list();
		System.out.println(JSONUtil.serialize(list));
		transaction.commit();
		
		System.out.println("==============");
		
		Session session2 = sessionFactory.getCurrentSession();
		Transaction transaction2 = session2.beginTransaction();
		String sqlString2 = "from StudentHql";
		Query query2 = session2.createQuery(sqlString2).setCacheable(true);
		List<StudentHql> list2 = query2.list();
		System.out.println(JSONUtil.serialize(list2));
		transaction2.commit();
		sessionFactory.close();
	}
	
	@Test
	public void handle2() throws JSONException{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		StudentHql student1 = (StudentHql) session.get(StudentHql.class, 1);
		StudentHql student2 = (StudentHql) session.get(StudentHql.class, 1);
		System.out.println(JSONUtil.serialize(student1));
		System.out.println("student1==student2"+":"+(student1==student2));
		transaction.commit();
		
		System.out.println("==============");
		
		Session session2 = sessionFactory.getCurrentSession();
		Transaction transaction2 = session2.beginTransaction();
		StudentHql student3 = (StudentHql) session2.get(StudentHql.class, 1);
		StudentHql student4 = (StudentHql) session2.get(StudentHql.class, 1);
		
		System.out.println("student1==student3"+":"+(student1==student3));//二级缓存中存放的是散装数据 会重新new
		System.out.println("studen4==student3"+":"+(student3==student4));
		transaction2.commit();
		sessionFactory.close();
	}
	
	
	
	
}
