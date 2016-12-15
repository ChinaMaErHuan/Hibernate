/**
 * tzdesk系统平台
 * TzHibernate
 * com.meh.hql
 * Test.java
 * 创建人:maerhuan 
 * 时间：2016年12月7日-下午2:29:15 
 * 2016潭州教育公司-版权所有
 */
package com.meh.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.transform.Transformers;

public class SqlTest {
	//sql查询
	@org.junit.Test
	public void handle() throws JSONException{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory factory = configuration.buildSessionFactory(registry);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		String sql = "select * from tz_hql_student;";
		Query query = session.createSQLQuery(sql);
		List<Object[]> students = query.list();
		
		
		
		//System.out.println(students.size());
		//System.out.println(students.get(1));
		for (Object[] objects : students) {
//			System.out.println(objects.length);//9
//			System.out.println(objects[0]);
//			System.out.println(objects[1]);
//			System.out.println(objects[2]);
//			System.out.println(objects[3]);
		}
		
		/**
		 * 如何把对象数组转换为所需要的对象
		 */
//		Object[] objects2 = students.get(0);	
//		System.out.println(objects2[0]);
//		System.out.println(objects2[1]);
//		System.out.println(objects2[2]);
//		System.out.println(objects2[3]);
//		StudentHql student = new StudentHql();
//		student.setId(objects[0]==null?null:Integer.parseInt(String.valueOf( objects[0])));
//		student.setStudentName(objects[1]==null?null:String.valueOf(objects[1]));
//		student.setStudentCode(objects[2]==null?null:String.valueOf(objects[2]));
//		student.setClassRoom(objects[3]==null?null:new ClassRoom(Integer.parseInt(String.valueOf(objects[3]))));
		//System.out.println(JSONUtil.serialize(student));
		
		
		//建立一个list集合 装对象的
		List<StudentHql> studentHqls = new ArrayList<StudentHql>();
		StudentHql student = null;
		for (int i = 0; i < students.size(); i++) {
			Object[] objects = students.get(i);//逐条的去取数据出来
			student = new StudentHql();
			student.setId(objects[0]==null?null:Integer.parseInt(String.valueOf(objects[0])));
			student.setStudentName(objects[1]==null?null:String.valueOf(objects[1]));
			student.setStudentCode(objects[2]==null?null:String.valueOf(objects[2]));
			student.setClassRoom(objects[3]==null?null:new ClassRoom(Integer.parseInt(String.valueOf(objects[3]))));
			studentHqls.add(student);
		}
		//验证一下是否成功地转换为了student对象
		System.out.println(studentHqls.size());
		for (StudentHql studentHql : studentHqls) {
			System.out.println(studentHql.getStudentName());
		}

		transaction.commit();
		session.close();
		factory.close();
	}
	/*
	 * 	Hibernate的sql查询 
	 *  Query ===createSQLQuery(String sql);
	 *  相同的列名用别名。
	 * */
	@org.junit.Test
	public void handle2(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		String sql = "SELECT s.id,c.id as cid ,s.student_name,c.class_name FROM tz_hql_student s LEFT JOIN tz_hql_classroom c ON c.id = s.classroom_id WHERE s.classroom_id IS NOT NULL";
		Query query = session.createSQLQuery(sql);
		List<Object[]> objects = query.list();
		for (Object[] objects2 : objects) {
			for (int i = 0; i < objects2.length; i++) {
				System.out.println(objects2[i]);
			}
			System.out.println("=================");
		}
		session.close();
	}
	//参数传递
	@org.junit.Test
	public void handle3(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		//按照参数位置查询
//		String sql = "select t.id,t.student_code,t.student_name from tz_hql_student t WHERE id = ?";
//		Query query = session.createSQLQuery(sql);
//		query.setInteger(0, 1);
		//按照参数名字查询
		String sql = "select t.id,t.student_code,t.student_name from tz_hql_student t WHERE id =:id";
		Query query = session.createSQLQuery(sql);
		query.setParameter("id", 2);
		Object[] objects =  (Object[]) query.uniqueResult();
		for (Object object : objects) {
			System.out.println(object);
		}
		session.close();
	}
	
	//参数传递  分页查询的方式
	@org.junit.Test
	public void handle4(){
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
		.applySettings( configuration.getProperties())
		.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//Session会话
		Session session = sessionFactory.openSession();
		//1.
//		String sql = "select t.id,t.student_code,t.student_name from tz_hql_student t ";
//		Query query = session.createSQLQuery(sql);
//		query.setFirstResult(0);
//		query.setMaxResults(5);
		
		//2.
		String sql = "select t.id,t.student_code,t.student_name from tz_hql_student t limit ?,?";
		Query query = session.createSQLQuery(sql);
		query.setInteger(0, 0);
		query.setInteger(1, 5);
		List<Object[]> objects =   query.list();
		System.out.println(objects.size());
		session.close();
	}
	
	//模糊查询
	//参数传递  分页查询的方式
		@org.junit.Test
		public void handle5(){
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
			.applySettings( configuration.getProperties())
			.build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			//Session会话
			Session session = sessionFactory.openSession();
			
			String  keyword = "meh";
			String sql = "select t.id,t.student_code,t.student_name from tz_hql_student t where student_name like ?";
			Query query = session.createSQLQuery(sql);
			query.setString(0, "%"+keyword+"%");
//			query.setString(0, keyword+"%");
//			query.setString(0, "%"+keyword);
			List<Object[]> objects =   query.list();
			System.out.println(objects.size());
			session.close();
		}
		
		//自动装配实体 必须是全部属性 addEntity
		@org.junit.Test
		public void handle05(){
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
			.applySettings( configuration.getProperties())
			.build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			//Session会话
			Session session = sessionFactory.openSession();
			String sql = "SELECT * FROM tz_hql_student s where student_code  LIKE ?";
			//自动装配成实体 必须是全部属性
			Query query = session.createSQLQuery(sql).addEntity(StudentHql.class);
			query.setString(0, "%1%");
			List<StudentHql> students = query.list();
			for (StudentHql student : students) {
				println(student);
			}
			session.close();
		}

	// 自动装配实体 不必是全部属性  你如果要使用addScalar这个方法 数据库里面的字段必须和javaBean里面的字段保持高度一致
	@org.junit.Test
	public void handle08() {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		// Session会话
		Session session = sessionFactory.openSession();
		String sql = "SELECT s.id as id,s.student_name as studentName,s.student_code as studentCode  FROM tz_hql_student s where s.student_code  LIKE ?";
		
		//.addScalar("id").addScalar("studentName").addScalar("studentCode")
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StudentHql.class));
		query.setString(0, "%1%");
		List<StudentHql> students = query.list();
		for (StudentHql student : students) {
			println(student);
		}
		session.close();
	}

		@org.junit.Test
		public void handle6() throws JSONException{
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
			.applySettings( configuration.getProperties())
			.build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			//Session会话
			Session session = sessionFactory.openSession();
			String sql = "SELECT c.*,s.* FROM tz_hql_student s LEFT JOIN tz_hql_classroom c ON c.id = s.classroom_id WHERE s.classroom_id IS NOT NULL";
			Query query = session.createSQLQuery(sql).addEntity("s",StudentHql.class).addEntity("c", ClassRoom.class);
			List<Object[]> list = query.list();
			StudentHql studentHql = null;
			List<StudentHql> list2 = new ArrayList<StudentHql>();
			for(int i = 0;i<list.size();i++){
				studentHql = new StudentHql();
				studentHql.setId(((StudentHql) list.get(i)[0]).getId());
				studentHql.setStudentCode(((StudentHql) list.get(i)[0]).getStudentCode());
				studentHql.setClassRoom(((StudentHql) list.get(i)[0]).getClassRoom());
				studentHql.setStudentName(((StudentHql) list.get(i)[0]).getStudentName());
				list2.add(studentHql);
			}
			System.out.println(list2.size());
			for (StudentHql studentHql2 : list2) {
				println(studentHql2);
			}
			session.close();
		}
	
	
	
	
	
	private void println(StudentHql student){
		System.out.println(student.getId()+"=="+student.getStudentName()+"==="+student.getStudentCode()+"==="+student.getClassRoom().getId());
	}
	/**
	 * addEntity 查询的必须是全部属性
	 * 
	 * setResultTransformer(Transformers.aliasToBean(StudentHql.class));不必是全部属性
	 * 
	 */
	
}
