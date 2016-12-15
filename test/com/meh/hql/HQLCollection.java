/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.hql
 * HQLCollection.java
 * 创建人:maerhuan 
 * 时间：2016年12月11日-下午8:43:32 
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

public class HQLCollection {
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
	public void handle(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		String sqlString = "from ClassRoom c where c.students.studentName = ''";
		//上面这种方式是错误的hql不支持这种方式从一的一方去查多的一方
		//必须用关联语句 hql就支持这种方式去关联
		String sqlString2 = "select c from ClassRoom c left join c.students as s where s.studentName = ? ";
		Query query = session.createQuery(sqlString2).setString(0, "meh");
		List<ClassRoom>list = query.list();
		
		String hql = "select DISTINCT c from ClassRoom c left join c.students as s";
		List list2 = session.createQuery(hql).list();
		System.out.println(list2);
		
		
		session.close();
		sessionFactory.close();
		
		
	}
	
	
	
}
