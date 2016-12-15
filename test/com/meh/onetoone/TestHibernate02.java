package com.meh.onetoone;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class TestHibernate02 {


	
	/*实体继承通用属性注解例子*/
	@Test
	public void handle(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TzUser user = new TzUser();
		user.setUsername("kekek");
		user.setPassword("123456");
		
		TzUserChildren userChildren = new TzUserChildren();//持久态
		userChildren.setAddress("湖南长沙");
		userChildren.setIdcard("345646465456464");
		userChildren.setMale("男");
		userChildren.setIntroduce("我是士大夫士大夫");
		userChildren.setHobbys("看电影，看书");
		userChildren.setBirthDay(new Date());
		
		//你中有我，我中有你
		userChildren.setUser(user);
		user.setUserChildren(userChildren);
		
		session.save(user);
		tx.commit();
		session.close();
	}
	
}
