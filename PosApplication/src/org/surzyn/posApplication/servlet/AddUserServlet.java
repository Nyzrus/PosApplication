package org.surzyn.posApplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.surzyn.posApplication.dto.UserDTO;
import org.surzyn.posApplication.service.AddUserService;


@WebServlet("/add")
public class AddUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		
		AddUserService aus = new AddUserService();
		
		if(aus.formCheck(username, firstName, lastName, password)){
			aus.addUser(new UserDTO(username, firstName, lastName, password));
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("Success");
			out.println("<br><a href='login.jsp'>Back to Login</a>");
		}else{
			System.out.println("nope");
			response.sendRedirect("addUser.jsp");
		}
		
	}
}
