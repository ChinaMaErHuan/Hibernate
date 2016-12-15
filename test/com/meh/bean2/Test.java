/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean2
 * Test.java
 * 创建人:maerhuan 
 * 时间：2016年11月14日-下午3:20:07 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean2;



import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Test {
	@org.junit.Test
	public void test3(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Content content = new Content();
		content.setCreateTime(new Date());
		content.setName("hahaha");
		content.setUpdateTime(new Date());
		content.setIsDelete(1);
		session.save(content);
		transaction.commit();
		session.close();
		factory.close();
	}
	
	@org.junit.Test
	public void test4(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Course course = new Course();
		course.setCourseName("kecheng");
		course.setIsDelete(0);
		course.setPrice(20.0f);
		course.setCreateTime(new Date());
		course.setUpdateTime(new Date());
		session.save(course);
		transaction.commit();
		session.close();
		factory.close();
	}
}
