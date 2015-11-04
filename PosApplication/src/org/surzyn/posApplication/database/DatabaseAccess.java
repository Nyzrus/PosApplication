package org.surzyn.posApplication.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.surzyn.posApplication.dto.FoodDTO;
import org.surzyn.posApplication.dto.OrderDTO;
import org.surzyn.posApplication.dto.UserDTO;



public class DatabaseAccess {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public DatabaseAccess(){
		 
	}
	
	public boolean checkDuplicateUser(String username){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from UserDTO where username='"+username+"'");
			UserDTO u = (UserDTO) query.uniqueResult();
			session.getTransaction().commit();
			if(u==null){
				return false;
			}
			return true;
		}catch(HibernateException ex){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return true;
		
	}
	
	public Integer addUser(UserDTO user1){
		Session session = sessionFactory.openSession();
		Integer userId = null;
		try{
			session.beginTransaction();
			userId = (Integer) session.save(user1);
			session.getTransaction().commit();
		}catch(HibernateException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return userId;
	}
	
	public UserDTO getUser(String username){
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("from UserDTO where username='"+username+"'");
			UserDTO u = (UserDTO) query.uniqueResult();
			session.getTransaction().commit();
			System.out.println(u.getPassword());
			return u;
		}catch(HibernateException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	public boolean userInfoAuthenticate(String username, String password){
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("from UserDTO where username='"+username+"'");
			if(query.list().size() > 0){
				UserDTO u = (UserDTO) query.list().get(0);
				session.getTransaction().commit();
				if(password.equals(u.getPassword())){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
			
		}catch(HibernateException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return false;
		
	}
	
	public void addOrder(OrderDTO order1){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.save(order1);
			session.getTransaction().commit();
		}catch(HibernateException ex){
			session.getTransaction().commit();
			ex.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public List<FoodDTO> getAllOrders(){
		Session session = sessionFactory.openSession();
		try{
			List<FoodDTO> foodList = new ArrayList<FoodDTO>();
			session.beginTransaction();
			Query query = session.createQuery("from FoodDTO");
			foodList = (List<FoodDTO>) query.list();
			session.getTransaction().commit();
			return foodList;
		}catch(HibernateException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return null;
		
		
	}
	
	public List<FoodDTO> getOrdersByTable(int tableNumber){
		Session session = sessionFactory.openSession();
		try{
			List<FoodDTO> foodList = new ArrayList<FoodDTO>();
			session.beginTransaction();
			Query query = session.createQuery("from FoodDTO where tablenumber="+tableNumber);
			foodList = (List<FoodDTO>) query.list();
			session.getTransaction().commit();
			return foodList;
		}catch(HibernateException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	public void deleteAllFromTable(int tableNumber){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("delete from FoodDTO where tablenumber="+tableNumber);
			query.executeUpdate();
			session.getTransaction().commit();
		}catch(HibernateException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void updateTable(OrderDTO order1, int tableNumber){
		Session session = sessionFactory.openSession();
		for(int i = 0; i < order1.getFoodList().toArray().length;i++){
			System.out.print(order1.getFoodList().toArray()[i]);
		}
		try{
			session.beginTransaction();
			Query query = session.createQuery("delete from FoodDTO where tablenumber="+tableNumber);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(order1);
			session.getTransaction().commit();
		}catch(HibernateException ex){
			session.getTransaction().commit();
			ex.printStackTrace();
		}finally{
			session.close();
		}
	}
	
}
