package org.surzyn.posApplication.service;

import org.surzyn.posApplication.database.DatabaseAccess;

public class LoginService {

		DatabaseAccess db = new DatabaseAccess();
	
	//should be the database
		public LoginService(){
		}
		
		public void getUser(String username){
			db.getUser(username);
		}
		
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
