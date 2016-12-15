/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean
 * StudentTest.java
 * 创建人:maerhuan 
 * 时间：2016年11月13日-下午5:52:22 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class StudentTest {
	@Test
	public void test2() throws FileNotFoundException, IOException{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Student student = new Student();
		student.setName("das2s");
		student.setIsDelete(0);
		student.setAddress("长沙");
		student.setCode("425");
		student.setMessage("111");
		student.setIsDelete(0);
		student.setTitle("hh");
		student.setAge(28);
		student.setMoney(0.0);
		student.setMessage("xxxxxx");
		File file = new File("F:/BaiduYunDownload/404.jpg");
		byte[] bytes = new byte[(int)file.length()];
		new FileInputStream(file).read(bytes);
		student.setImageCode(bytes);
		
		session.save(student);
		transaction.commit();
		session.close();//脱管态
		factory.close();
	}
}
