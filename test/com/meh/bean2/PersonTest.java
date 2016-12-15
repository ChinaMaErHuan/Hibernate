/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.bean2
 * PersonTest.java
 * 创建人:maerhuan 
 * 时间：2016年11月14日-下午4:30:47 
 * 2016潭州教育公司-版权所有
 */
package com.meh.bean2;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;


public class PersonTest {
	@Test
	public void handle() throws FileNotFoundException, IOException, ParseException{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		//
		Person person = new Person();
		person.setName("meh");
		person.setContent("sadfgasfhskdjhkfhsajkdghksdhgkjahdjwthgjshfjklghsdfjkhgkjdshghg");
		
		person.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd").parse("1993-06-24"));
		person.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1993-06-24"));
		
		person.setScore(99.5f);
		person.setMoney(100000.0d);
		
		//保存图片
		File file = new File("F:/BaiduYunDownload/404.jpg");
		byte[] bytes = new byte[(int) file.length()];
		FileInputStream fileInputStream = new FileInputStream(file);
		int read = fileInputStream.read(bytes);
		person.setImagecode(bytes);
		fileInputStream.close();
		//另外一种保存图片
		person.setImagecode2(Hibernate.getLobCreator(session).createBlob(bytes));
		
		session.save(person);
		transaction.commit();
		session.close();
		factory.close();
	}
	//图片是存到了数据库里面了 怎么读取出来？
	@Test
	public void handle2(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		//
		Person person = (Person) session.get(Person.class, 1);
		//byte读取
		try {
			byte[] bytes = person.getImagecode();
			FileOutputStream fileOutputStream = new FileOutputStream("F:/BaiduYunDownload/4042.jpg");
			fileOutputStream.write(bytes);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//blob读取
		try {
			int length = (int) person.getImagecode2().length();
			byte[] bytes = person.getImagecode2().getBytes(1, length);
			FileOutputStream fileOutputStream2 = new FileOutputStream("F:/BaiduYunDownload/4043.jpg");
			fileOutputStream2.write(bytes);
			fileOutputStream2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		session.close();
		factory.close();
	}
}
