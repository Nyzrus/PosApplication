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

/*
 * All database actions in the application are run through the DatabaseAccess class.
 * 
 * SessionFactory creation is expensive, so I use a static SessionFactory variable for maximal scope
 * reference. Individual methods use individual methods, which are less expensive, coupled
 * with proper exception-handling techniques and data rollback measures to preserve data in
 * database.
 * 
 */


public class DatabaseAccess {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public DatabaseAccess(){
	
	}
	
	public void initializeDatabase(){
	}
	
	//Checks database for duplicate Users
	//Called from AddUserServlet -> AddUserService.formCheck()
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
	
	//adds user to USER_TABLE for login purposes
	//called by AddUserServlet.class -> LoginService.addUser() -> DatabaseAccess.addUser() 
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
	
	//returns UserDTO object by username
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
	
	//checks if provided username and password from login.jsp corresponds to user in database
	//called from LoginServlet -> LoginService.authenticate() -> DatabaseAccess.userInfoAuthenticate()
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
	
	//adds an OrderDTO object to the database
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
	
	//returns all orders from all tables
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
	
	//returns all orders from specific table
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
	
	//deletes all orders from specific table
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
	
	//updates table according to Save Changes button from tableOrder.jsp
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
