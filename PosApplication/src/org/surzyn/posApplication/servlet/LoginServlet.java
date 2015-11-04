package org.surzyn.posApplication.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.surzyn.posApplication.service.LoginService;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userId");
		String password = request.getParameter("password");
		LoginService ls = new LoginService();
		
		request.getSession().setAttribute("user", username);
		
		
		if(ls.authenticate(username, password)){
			
			response.sendRedirect("tableView.jsp");
			return;
		}else{
			response.sendRedirect("login.jsp");
			return;
		}
	}

}
