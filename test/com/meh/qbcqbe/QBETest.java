/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.qbcqbe
 * QBETest.java
 * 创建人:maerhuan 
 * 时间：2016年12月11日-下午10:18:15 
 * 2016潭州教育公司-版权所有
 */
package com.meh.qbcqbe;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.sql.JoinType;
import org.junit.Test;

import com.meh.hql.ClassRoom;
import com.meh.hql.StudentHql;

public class QBETest {
	/**
	 * Restrictions.eq 等于=
	 * .alleq()
	 * .gt >
	 * .lt <
	 * .ge >=
	 * .le <=
	 * .between 对听sql的like
	 * .like 对应sql的like
	 * .in  对应sql的in
	 * .and and关系
	 * .or or关系
	 * .sqlRestriction 
	 * Restrictions.sqlRestriction(" score > 80 and  score <100")
	 * 
	 * detachedCriteria.setProjection(Projections.id());
	 * detachedCriteria.setProjection(Projections.count("id"));
	 * detachedCriteria.setProjection(Projections.max("score"));
	 * detachedCriteria.setProjection(Projections.min("score"));
	 * dCriteria.setProjection(Projections.groupProperty(propertyName));
	 */
	@Test
	public void handleBase(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
//		Criteria 和 DetachedCriteria 的主要区别在于创建的形式不一样， Criteria 是在线的，所
//		以它是由 Hibernate Session 进行创建的；而 DetachedCriteria 是离线的，创建时无需
//		Session，DetachedCriteria 提供了 2 个静态方法 forClass(Class) 或 forEntityName(Name)
//		进行DetachedCriteria 实例的创建。 Spring 的框架提供了getHibernateTemplate
//		().findByCriteria(detachedCriteria) 方法可以很方便地根据DetachedCriteria 来返回查询结
//		果。

		//在线查询
		Criteria criteria = session.createCriteria(StudentHql.class);
		//离线查询
		DetachedCriteria dCriteria = DetachedCriteria.forClass(StudentHql.class);
		//设定查询条件
		//code there
		//Criterion criterion = Restrictions.eq(propertyName, value);	
		//dCriteria.add(criterion)
		List list = dCriteria.getExecutableCriteria(session).list();
		//System.out.println(list);
		session.close();
		sessionFactory.close();
	}
	//入门
	@Test
	public void handle(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		DetachedCriteria dCriteria = DetachedCriteria.forClass(StudentHql.class);
		//设定查询条件
		dCriteria.add(Restrictions.eq("studentName", "meh"));
		//code there
		List<StudentHql> list = dCriteria.getExecutableCriteria(session).list();
		System.out.println(list);
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void handle2(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		DetachedCriteria dCriteria = DetachedCriteria.forClass(StudentHql.class);
		//设定查询条件
		dCriteria.add(Restrictions.or(Restrictions.like("studentName", "%meh%"), Restrictions.eq("id", 2)));
		dCriteria.addOrder(Order.desc("score"));
		//code there
		List<StudentHql> list = dCriteria.getExecutableCriteria(session).list();
		System.out.println(list);
		session.close();
		sessionFactory.close();
	}
	//子查询的嵌套
	@Test
	public void handle3(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		DetachedCriteria dCriteria = DetachedCriteria.forClass(ClassRoom.class);
		//设定查询条件
//		String hql = "from ClassRoom c left join c.students s  where s.id = 2";
		dCriteria.createAlias("students", "s",JoinType.LEFT_OUTER_JOIN);
		dCriteria.add(Restrictions.eq("id", 2));
		//code there
		//过滤 去重
		List<ClassRoom> list = dCriteria.setResultTransformer(dCriteria.DISTINCT_ROOT_ENTITY).getExecutableCriteria(session).list();
		System.out.println(list);
		session.close();
		sessionFactory.close();
	}
	//like
	@Test
	public void handle4(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		DetachedCriteria dCriteria = DetachedCriteria.forClass(ClassRoom.class);
		dCriteria.add(Restrictions.like("className", "java"));
		dCriteria.add(Restrictions.like("className", "java", MatchMode.ANYWHERE));

		dCriteria.add(Restrictions.like("className", "%java"));
		dCriteria.add(Restrictions.like("className", "java",MatchMode.START));
		
		dCriteria.add(Restrictions.like("className", "java%"));
		dCriteria.add(Restrictions.like("className", "java", MatchMode.END));
		//code there
		List<ClassRoom> list = dCriteria.getExecutableCriteria(session).list();
		System.out.println(list);
		session.close();
		sessionFactory.close();
	}
}
