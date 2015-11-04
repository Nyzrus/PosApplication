package org.surzyn.posApplication.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.surzyn.posApplication.service.OrderService;


/**
 * Servlet implementation class TableOrderServlet
 */
@WebServlet("/tableOrder")
public class TableOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderString = request.getParameter("hiddenJSValue");
		System.out.println(orderString);
		String opString = request.getParameter("hiddenReturnValue");
		
		int tableNumber = Integer.valueOf(request.getParameter("hiddenTableValue"));
		
		OrderService os = new OrderService();
		if(opString.equals("delete")){
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