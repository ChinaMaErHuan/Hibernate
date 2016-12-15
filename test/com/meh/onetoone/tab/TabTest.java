/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.onetoone.tab
 * TabTest.java
 * 创建人:maerhuan 
 * 时间：2016年11月18日-下午5:03:47 
 * 2016潭州教育公司-版权所有
 */
package com.meh.onetoone.tab;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class TabTest {

	@Test
	public void handle1(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TabUser  user = new TabUser();
		user.setUsername("kekek");
		user.setPassword("123456");
		
		TabUserChildren userChlidren = new TabUserChildren();//持久态
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
}
