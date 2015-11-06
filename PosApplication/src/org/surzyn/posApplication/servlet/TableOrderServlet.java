package org.surzyn.posApplication.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.surzyn.posApplication.service.OrderService;


/*
 * 
 * This Servlet corresponds to the TableOrder JSP page
 * 
 * Values are collected from the hidden HTML elements on the TableOrder JSP which include:
 * - An orderString that contains any items needed to be added to the DB
 * - An opCode that designates the operation for the servlet to perform
 * - A tableNumber that tells the servlet for which to perform the operations
 * 
 * In any case, the Servlet redirects the application to the TableView JSP
 * 
 */
@WebServlet("/tableOrder")
public class TableOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderString = request.getParameter("hiddenJSValue");
		String opString = request.getParameter("hiddenReturnValue");
		System.out.println(opString);
		int tableNumber = Integer.valueOf(request.getParameter("hiddenTableValue"));
		
		OrderService os = new OrderService();
		if(opString.equals("clear")){
			os.deleteAllFromTable(tableNumber);
		}
		if(opString.equals("update")){
			os.updateTable(orderString, tableNumber);
		}
		if(!orderString.equals("") && opString.equals("add")){
			os.addOrder(orderString, tableNumber);
		}else{
		}
		response.sendRedirect("tableView.jsp");
		
	}

}
