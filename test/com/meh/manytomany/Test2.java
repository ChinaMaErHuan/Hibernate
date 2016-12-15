/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.manytomany
 * Test2.java
 * 创建人:maerhuan 
 * 时间：2016年11月22日-上午12:23:20 
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

import com.meh.manytomany.double2.StudentManyToManyDouble;
import com.meh.manytomany.double2.CourseManyToManyDouble;

public class Test2 {
	//双向多对多
		@Test
		public void handle11(){
			
			Configuration configuration = new Configuration().configure();
			ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory factory = configuration.buildSessionFactory(registry);
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			CourseManyToManyDouble course = new CourseManyToManyDouble();
			course.setName("语文");
			
			CourseManyToManyDouble course2 = new CourseManyToManyDouble();
			course2.setName("化学");
			
			StudentManyToManyDouble student = new StudentManyToManyDouble();
			student.setName("张三");
			//student.getCourses().add(course);
			//student.getCourses().add(course2);
			
			StudentManyToManyDouble student2 = new StudentManyToManyDouble();
			student2.setName("李四");
			//student2.getCourses().add(course);
			//student2.getCourses().add(course2);
			
			
			
			
			//科目关联学生
//			course.getStudents().add(student);
//			course.getStudents().add(student2);
//			
//			course2.getStudents().add(student);
//			course2.getStudents().add(student2);
//			session.save(course);
//			session.save(course2);
//			
			
			
			//学生关联科目
//			student.getCourses().add(course);
//			student.getCourses().add(course2);
//			student2.getCourses().add(course);
//			student2.getCourses().add(course2);
//			session.save(student);
//			session.save(student2);
			
			transaction.commit();
			session.close();
			factory.close();
		}
}
