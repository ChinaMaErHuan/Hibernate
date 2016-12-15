/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean2
 * StudentClassTest.java
 * 创建人:maerhuan 
 * 时间：2016年11月15日-下午6:51:03 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class StudentClassTest {
	@Test
	public void handle1(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> list = new ArrayList<String>();
		list.add("小学");
		list.add("初中");
		list.add("高中");
		StudentClass studentClass = new StudentClass();
		studentClass.setName("吗耳环");
		studentClass.setSchool(list);
		
		Set<String> set = new HashSet<String>();
		set.add("mmm");
		set.add("hhh");
		studentClass.setFriends(set);
		
		Map<String, Float> map = new HashMap<String, Float>();
		map.put("math", 90.0f);
		map.put("pe", 80.0f);
		map.put("yuwen", 70.0f);
		studentClass.setMap(map);
		
		session.save(studentClass);
		transaction.commit();
		session.close();
		factory.close();
	}
}
