/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.manytomany
 * Test1.java
 * 创建人:maerhuan 
 * 时间：2016年11月20日-下午11:46:52 
 * 2016潭州教育公司-版权所有
 */
package com.meh.manytomany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import com.meh.manytomany.single.CourseManyToMany;
import com.meh.manytomany.single.StudentManyToMany;

public class Test1 {
	//单向多对多
	@Test
	public void handle1(){
		
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(registry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Set<CourseManyToMany> set = new HashSet<CourseManyToMany>();
		
		CourseManyToMany course = new CourseManyToMany();
		course.setName("java程序设计");
		
		CourseManyToMany course2 = new CourseManyToMany();
		course2.setName("c程序设计");
		set.add(course);
		set.add(course2);
		
		StudentManyToMany student = new StudentManyToMany();
		student.setName("马儿欢");
		student.setCourses(set);
		
		StudentManyToMany student2 = new StudentManyToMany();
		student2.setName("马欢");
		student2.setCourses(set);
		
		
		session.save(student);
		session.save(student2);
		transaction.commit();
		session.close();
		factory.close();
	}
	
}
