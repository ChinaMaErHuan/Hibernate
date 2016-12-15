/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean
 * BeanTest.java
 * 创建人:maerhuan 
 * 时间：2016年11月12日-下午12:31:47 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class BeanTest {
	@Test
	public void test1(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		User user = new User();	//瞬时态 与session没有关联
		user.setAge(22);
		user.setUsername("maerhuan");
		user.setUuid("464132");
		user.setAccount("meh");
		session.save(user);//持久态
		//如果一个对象被持久态以后，如果你业务的后方调用持久态的set方法对属性进行修改的时候会发起一条update语句.
		transaction.commit();
		session.close();//脱管态
		factory.close();
	}
}
