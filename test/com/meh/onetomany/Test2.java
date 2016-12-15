package com.meh.onetomany;

import java.util.ArrayList;
import java.util.List;





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import com.meh.onetomany.double2.CityOneToMany;
import com.meh.onetomany.double2.PersonOneToMany;

public class Test2 {
	@Test
	public void handle2(){
		
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(registry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		CityOneToMany cityOneToMany = new CityOneToMany();
		cityOneToMany.setName("上海");
		
		//创建学生
		List<PersonOneToMany> personOneToManies = new ArrayList<PersonOneToMany>();
//		personOneToManies.add(new PersonOneToMany("大米同学",cityOneToMany));
//		personOneToManies.add(new PersonOneToMany("张三同学",cityOneToMany));
//		personOneToManies.add(new PersonOneToMany("keke同学",cityOneToMany));
//		personOneToManies.add(new PersonOneToMany("李四同学",cityOneToMany));
		PersonOneToMany personOneToMany = new PersonOneToMany();
		personOneToMany.setName("大米同学11111");
		personOneToMany.setCity(cityOneToMany);//设置城市id
		personOneToManies.add(personOneToMany);
		personOneToMany = new PersonOneToMany();
		personOneToMany.setName("张三同学1111");
		personOneToMany.setCity(cityOneToMany);//设置城市id
		personOneToManies.add(personOneToMany);
		personOneToMany = new PersonOneToMany();
		personOneToMany.setName("keke同学1111");
		personOneToMany.setCity(cityOneToMany);//设置城市id
		personOneToManies.add(personOneToMany);
		personOneToMany = new PersonOneToMany();
		personOneToMany.setName("李四同学1111");
		personOneToMany.setCity(cityOneToMany);//设置城市id
		personOneToManies.add(personOneToMany);
		
		//建立关联关系
		cityOneToMany.setPersons(personOneToManies);
		
		//
		PersonOneToMany person3 = new PersonOneToMany();
		person3.setName("马儿欢");
		
		CityOneToMany city2 = new CityOneToMany();
		city2.setName("北京");
		//city2.getPersons().add(person3);//这么做 在prson中城市的外键是null
		//人关联城市
		person3.setCity(city2);
		session.save(person3);
		//session.save(city2);
		session.save(cityOneToMany);
		transaction.commit();
		session.close();
		factory.close();
	}
	@Test
	public void handle22(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();	
		//方式1
		String hql = "from PersonOneToMany where city.id = 1 ";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(6);
		List<PersonOneToMany> personOneToManies = query.list();
		for (PersonOneToMany personOneToMany : personOneToManies) {
			System.out.println(personOneToMany.getName());
		}
//		CityOneToMany cityOneToMany = (CityOneToMany)session.get(CityOneToMany.class,1);
//		List<PersonOneToMany> personOneToManies2 = cityOneToMany.getPersons();
//		for (PersonOneToMany personOneToMany : personOneToManies2) {
//			System.out.println(personOneToMany.getName());
//		}
		session.close();
		sessionFactory.close();
	}
}
