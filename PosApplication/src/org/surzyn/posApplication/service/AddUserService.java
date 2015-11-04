package org.surzyn.posApplication.service;

import org.surzyn.posApplication.database.DatabaseAccess;
import org.surzyn.posApplication.dto.UserDTO;


public class AddUserService {

DatabaseAccess db = new DatabaseAccess();
	
	public boolean formCheck(String username, String firstName, 
							String lastName, String password){
		if((password==null || password.trim()=="") || (firstName==null || firstName.trim()=="")||
				(lastName==null || lastName.trim()=="") || (username==null || username.trim()=="")){
			return false;
			
		}else{
			DatabaseAccess db = new DatabaseAccess();
			//if there is the same username in the database, reject the submission
			if(db.checkDuplicateUser(username)){
				return false;
			}else{
				return true;
			}
			
		}
		
		
	}
	
	public void addUser(UserDTO user1){
		db.addUser(user1);
	}
}
