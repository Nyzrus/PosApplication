package org.surzyn.posApplication.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.surzyn.posApplication.service.LoginService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/*
	 * login.jsp sends info to the LoginServlet which compares the 
	 * user-given username and password to the corresponding username/password info
	 * stored in the Hibernate database.
	 * 
	 * These queries are run through LoginService from the Service package 
	 * which accesses the database through DatabaseAccess
	 * 
	 * LoginService's method authenticate() returns true if the username/password 
	 * association is valid and false otherwise
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userId");
		String password = request.getParameter("password");
		LoginService ls = new LoginService();
		
		request.getSession().setAttribute("user", username);
		
		
		if(ls.authenticate(username, password)){			//if user/password combo valid
			response.sendRedirect("tableView.jsp");
			return;
		}else{												//if not valid
			response.sendRedirect("login.jsp");
			return;
		}
	}

}
