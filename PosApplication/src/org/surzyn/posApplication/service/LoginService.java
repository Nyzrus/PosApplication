package org.surzyn.posApplication.service;

import org.surzyn.posApplication.database.DatabaseAccess;

public class LoginService {

		DatabaseAccess db = new DatabaseAccess();

		public LoginService(){
		}
		
		//Gets UserDTO by username from the database
		public void getUser(String username){
			db.getUser(username);
		}
		
		//Authenticates parameters passed from JSP to Servlet
		//Checks for empty input first and authenticates info with database
		public boolean authenticate(String username, String password){
			if(password==null || password.trim()==""){
				return false;
			}else{
				if(db.userInfoAuthenticate(username, password)){
					return true;
				}
				return false;
			}
		}
}
