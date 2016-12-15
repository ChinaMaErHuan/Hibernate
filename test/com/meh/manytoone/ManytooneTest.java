/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.manytoone
 * ManytooneTest.java
 * 创建人:maerhuan 
 * 时间：2016年11月18日-下午10:24:15 
 * 2016潭州教育公司-版权所有
 */
package com.meh.manytoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class ManytooneTest {
	@Test
	public void handle(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(registry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		MAddress address = new MAddress();
		address.setName("山西");
		MPerson person = new MPerson();
		person.setAge(23);
		person.setName("马二欢");
		person.setAddress(address);
		session.save(person);
		transaction.commit();
		session.close();
		factory.close();
	}
}
