package org.surzyn.posApplication.service;

import org.surzyn.posApplication.database.DatabaseAccess;
import org.surzyn.posApplication.dto.UserDTO;


public class AddUserService {

DatabaseAccess db = new DatabaseAccess();
	
	//First, trim's parameter strings and checks for empty input
	//Returns false if formCheck invalidates input
	//Returns true if formCheck validates user input
	//Called by AddUserServlet in the servlet package
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
	//calls DatabaseAccess.addUser() to add user to database
	public void addUser(UserDTO user1){
		db.addUser(user1);
	}
}
