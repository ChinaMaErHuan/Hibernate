/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.hql
 * HqlTest.java
 * 创建人:maerhuan 
 * 时间：2016年12月8日-下午7:32:25 
 * 2016潭州教育公司-版权所有
 */
package com.meh.hql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.transform.Transformers;
import org.junit.Test;


public class HqlTest {
	/*
	 * HQL:Hibernate Query Language :语法和SQL非常的相似，但是他是纯面向对象的方式实现，
	 * QBC你必须对sql相关的一定要非常的熟悉和了解（left join right join inner join）
	 * 联合:union(all)
	 * QBE:
	 * 
	 * 快速：写hql和表没有任何的关系,和实体有关,
	 * 格式： FROM 实体名称 [as 别名] WHERE [key=value (and/or) key1 =value2 ......] [group by...] [having...] [order by ...][asc/desc]
	 * key:必须是：实体里面的属性名称
	 * 
	 * 执行sql的调用的方法是：session.createSQLQuery(sql); List<Object[]>/Object[]
	 * 执行hql的调用的方式是：session.createQuery(hql);//List<对象>或者对象
	 * hql整表全字段查询
	 * 
	 * **/
	@Test
	public void handle(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		//hql查询单表的方式一：
		//String hqlString = "from StudentHql";
		//String hql = "from StudentHql as s";
		
		//方式二：
		//String hql = "select new StudentHql(s.id,s.studentName,s.studentCode) from StudentHql as s";
		//Query query = session.createQuery(hql);
		
		//以下这种方式返回实体类对象 不必创构造函数 必须要起别名 和bean中的属性要一致
		String hql = "select s.id as id,s.studentName as studentName,s.studentCode as studentCode,s.classRoom as classRoom from StudentHql as s";
		Query query2 = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(StudentHql.class));
		
		List<StudentHql> list = query2.list();
		for (StudentHql studentHql : list) {
			println(studentHql);
		}
		
		session.close();
		sessionFactory.close();
	}
	//返回Map
	//[{classroom=com.meh.hql.ClassRoom@7cc586a8, id=1, studentcode=132, studentname=meh}, {classroom=com.meh.hql.ClassRoom@5fe8b721, id=2, studentcode=789, studentname=meh2}
	@Test
	public void returnMap(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		//方式一：
		String hql = "select new Map(s.id as id,s.studentName as studentname ,s.studentCode as studentcode,s.classRoom as classroom) from StudentHql s";
		Query query = session.createQuery(hql);
		List<Map<String, Object>> maps = query.list();
		
		//方式二:
//		String hql = "select s.id as id,s.studentName as studentname ,s.studentCode as studentcode,s.classRoom as classroom from StudentHql s";
//		Query query = session.createQuery(hql);
//		List<Map<String, Object>> maps = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		
		System.out.println(maps);
		for (Map<String, Object> map : maps) {
			System.out.println(map.get("id"));
			System.out.println(map.get("studentname"));
			System.out.println(map.get("studentcode"));
			ClassRoom room = (ClassRoom)map.get("classroom");
			System.out.println(room.getClassName());
			System.out.println("==================");
		}
		session.close();
		sessionFactory.close();
	}
	
	//返回List 在hql语句中new List  或者 query.setResultTransformer(Transformers.TO_LIST).list()
	@Test
	public void returnList() {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		// Session会话
		Session session = sessionFactory.openSession();
		// 方式一：
//		String hql = "select new List(s.id as id,s.studentName as studentname ,s.studentCode as studentcode,s.classRoom as classroom) from StudentHql s";
//		Query query = session.createQuery(hql);
//		ArrayList<List<Object>> arrayList = (ArrayList<List<Object>>) query.list();
		//[[1, meh, 132, com.meh.hql.ClassRoom@7cc586a8], [2, meh2, 789, com.meh.hql.ClassRoom@5fe8b721], [4, meh4, 465, com.meh.hql.ClassRoom@5fe8b721], [3, meh3, 369, com.meh.hql.ClassRoom@3591009c]
		//System.out.println(arrayList);
		String hql = "select s.id as id,s.studentName as studentname ,s.studentCode as studentcode,s.classRoom as classroom from StudentHql s";
		Query query = session.createQuery(hql);
		ArrayList<List<Object>> arrayList = (ArrayList<List<Object>>) query.setResultTransformer(Transformers.TO_LIST).list();
		
		for (List<Object> list : arrayList) {
			for (Object object : list) {
				System.out.println(String.valueOf(object));
			}
			System.out.println("=================");
		}
		session.close();
		sessionFactory.close();
	}

	
	//联合查询
	@Test
	public void handle2() {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		// Session会话
		Session session = sessionFactory.openSession();
		
		//String sql = "select s.*,c.* from tz_hql_student s inner join tz_hql_classroom c on c.id = s.classroom_id ";
		String hql = "from StudentHql s inner join s.classRoom c";
		Query query2 = session.createQuery(hql);
		List<Object []> list = query2.list();
//		for (Object[] objects : list) {
//			System.out.println(objects[0]);
//		}
//		for (int i = 0; i < list.size(); i++) {
//			Object[] objects = list.get(i);
//			StudentHql student = (StudentHql) objects[0];
//			ClassRoom classRoom = (ClassRoom)objects[1];
//			student.setClassRoom(classRoom);
//			println(student);
//		}
		
		//上面的情况是把俩个对象全部查出来了  那么我只查一种呢？
		String hql2 = "select s from StudentHql s left join s.classRoom c";
		Query query22 = session.createQuery(hql);
		List<StudentHql> students = query22.list();
//		for (StudentHql student : students) {
//			println(student);
//		}
	
		String hql3 = "select c from StudentHql s left join s.classRoom c";
		Query query3 = session.createQuery(hql);
		List<ClassRoom> classRooms = query3.list();
//		for (ClassRoom classRoom : classRooms) {
//			System.out.println(classRoom.getClassName());
//		}
		
		//下面的这条语句和where过滤式无关的 这里的意思就是说 先把id=1的classroom拿出来 再和你的studentHql关联 而我们这条语句里面使用的右链接 所以 会产生六条记录 但是符合条件的学生只有两条 其他都已null填充 
		//永远记住 hql查询是面向对象的
		String hql4 = "select s from StudentHql s right join s.classRoom c with (c.id = 1 AND c.className = 'java') ";
		Query query5 = session.createQuery(hql4);
		System.out.println(query5.list());
		session.close();
		sessionFactory.close();
	}
	//调用存储过程
	@Test
	public void handle3() {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		// Session会话
		Session session = sessionFactory.openSession();

		// String sql =
		// "select s.*,c.* from tz_hql_student s inner join tz_hql_classroom c on c.id = s.classroom_id ";
		String hql = "{call pro_student()}";
		Query query = session.createSQLQuery(hql).addEntity(StudentHql.class);
		List<StudentHql> list = query.list();
		for (StudentHql studentHql : list) {
			println(studentHql);
		}
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void handle4(){
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		ConnectionProvider cp =((SessionFactoryImplementor)sessionFactory).getConnectionProvider();
		// Session会话
		try {
			Connection connection = cp.getConnection();
			java.sql.PreparedStatement statement = connection
					.prepareCall("{CALL pro_student()}");
			// statement.setInt(1, 100);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getInt(4));
				System.out.println("=================");
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		sessionFactory.close();
	}
	
	
	
	
	
	
	private void println(StudentHql student){
		System.out.println(student.getId()+"=="+student.getStudentName()+"==="+student.getStudentCode()+"==="+(student.getClassRoom().getId()==null?null:student.getClassRoom().getId()));
	}
	
	private void println2(StudentHql student){
		System.out.println(student.getId()+"=="+student.getStudentName()+"==="+student.getStudentCode()+"===");
	}
}
