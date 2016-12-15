package com.meh.onetoone;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class TestHibernate {


	
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
		
		TzUserChildren userChlidren = new TzUserChildren();//持久态
		userChlidren.setAddress("湖南长沙");
		userChlidren.setIdcard("345646465456464");
		userChlidren.setMale("男");
		userChlidren.setIntroduce("我是士大夫士大夫");
		userChlidren.setHobbys("看电影，看书");
		userChlidren.setBirthDay(new Date());
		session.save(userChlidren);//保存自己
		System.out.println("==============="+userChlidren.getId());
		
		user.setUserChildren(userChlidren);
		session.save(user);
		tx.commit();
		session.close();
	}
	
	
	/*实体继承通用属性注解例子 cascade=CascadeType.ALL*/
	@Test
	public void handle3(){
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
		
		TzUserChildren userChlidren = new TzUserChildren();//持久态
		
		userChlidren.setAddress("湖南长沙");
		userChlidren.setIdcard("345646465456464");
		userChlidren.setMale("男");
		userChlidren.setIntroduce("我是士大夫士大夫");
		userChlidren.setHobbys("看电影，看书");
		userChlidren.setBirthDay(new Date());
		user.setUserChildren(userChlidren);
		session.save(user);//insert userchildren insert user
		//update语句
		tx.commit();
		session.close();
	}
	
	@Test
	public void handle2(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		
//		get,nulll 
//		load：延迟加载,异常
		Session session = sessionFactory.openSession();
		//TzUser user = (User)session.get(TzUser.class, 3);
		TzUser user = (TzUser)session.load(TzUser.class, 3);
		System.out.println(user.getUsername());
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		//子类
		System.out.println(user.getUserChildren().getAddress());
		session.close();
	}
	
	@Test
	public void handle03(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TzUser user = (TzUser) session.get(TzUser.class, 5);
		session.delete(user);
		tx.commit();
		session.close();
	}
	
	
	@Test
	public void handle04(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
//		User user = (User) session.get(User.class, 6);//持久态
//		user.setUsername("sdfsdfsdfsdfsdfsd");
//		UserChlidren userChlidren = user.getUserChlidren();//已经是一个持久态了
//		userChlidren.setIntroduce("ddddddddddddddddddddddd");
		
//		User user = new User();//临时态
//		user.setId(6);
//		user.setUsername("333333333333");
//		session.update(user);
		
		tx.commit();
		session.close();
	}
}
