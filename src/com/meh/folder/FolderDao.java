package com.meh.folder;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.meh.folder.Folder;
import com.meh.folder.Resources;
import com.meh.session.HibernateSessionFactory;

/**
 * 
 * 查询所有的文件夹信息
 * FolderDao
 * 创建人:keke
 * 时间：2015年4月19日-上午12:08:18 
 * @version 1.0.0
 *
 */
public class FolderDao {

	
	/**
	 * 查询根目录
	 * 方法名：findRoot
	 * 创建人：xuchengfei 
	 * 时间：2015年4月19日-上午12:11:33 
	 * @return List<Folder>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Folder> findRoot(){
		Session session = HibernateSessionFactory.getSession();
		List<Folder> folders = session.createQuery("from Folder where parentId = 1  and isDelete = 0").list();
		HibernateSessionFactory.closeSession();
		return folders;
	}
	
	
	/**
	 *
	 * 方法名：findChildren
	 * 创建人：xuchengfei 时间：2015年4月19日-上午12:40:09 
	 * @param parentId 父id
	 * @return List<Folder>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Folder> findChildren(Integer parentId){
		Session session = HibernateSessionFactory.getSession();
		List<Folder> folders = session.createQuery("from Folder where parentId = ? and isDelete = 0 order by createTime desc").setInteger(0, parentId).list();
		HibernateSessionFactory.closeSession();
		return folders;
	}
	
	/**
	 * 保存文件夹
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年4月19日-上午1:36:38 
	 * @param folder void
	 * @exception 
	 * @since  1.0.0
	 */
	public Folder saveFolder(Folder folder){
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(folder);
			transaction.commit();
			return folder;
		} catch (Exception e) {
			return null;
		} finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * 删除文件夹
	 * 方法名：delete
	 * 创建人：xuchengfei 时间：2015年4月19日-上午1:40:10 
	 * @param id
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean deleteFolder(Integer id){
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction transaction = session.beginTransaction();
			Folder folder = (Folder) session.load(Folder.class, id);
			session.delete(folder);
			transaction.commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * 删除文件夹
	 * 方法名：delete
	 * 创建人：xuchengfei 时间：2015年4月19日-上午1:40:10 
	 * @param id
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean deleteResources(Integer id){
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction transaction = session.beginTransaction();
			Resources resources = (Resources) session.load(Resources.class, id);
			session.delete(resources);
			transaction.commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * 
	 * 方法名：findResources
	 * 创建人：xuchengfei 
	 * 时间：2015年4月19日-上午1:07:48 
	 * @param folderId
	 * @return List<Resources>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Resources> findResources(Integer folderId){
		Session session = HibernateSessionFactory.getSession();
		List<Resources> folders = session.createQuery("from Resources where folderId = ?  and isDelete = 0").setInteger(0, folderId).list();
		HibernateSessionFactory.closeSession();
		return folders;
	}
	
//	@Test
//	public void handler() throws JSONException{
//		List<Folder> folders = new FolderDao().findRoot();
//		System.out.println(JSONUtil.serialize(folders));
//		List<Resources> resources =  new FolderDao().findResources(2);
//		for (Resources resources2 : resources) {
//			System.out.println(resources2.getName());
//		}
//	}
}
