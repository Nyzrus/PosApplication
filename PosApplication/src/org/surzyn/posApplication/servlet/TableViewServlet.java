package org.surzyn.posApplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.surzyn.posApplication.dto.FoodDTO;
import org.surzyn.posApplication.dto.OrderDTO;
import org.surzyn.posApplication.service.OrderService;


/**
 * Servlet implementation class TableViewServlet
 */
@WebServlet("/table")
public class TableViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonChosen = request.getParameter("table");
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("tableOrder.jsp");
		switch (buttonChosen) {
			case "Table 1":  
				request.getSession().setAttribute("tableSpecificOrder", getOrdersForTable(1));
				request.getSession().setAttribute("tableNumber", "1");
				dispatcher.forward(request, response);
				//response.sendRedirect("tableOrder.jsp");
				break;
			case "Table 2":  
				request.getSession().setAttribute("tableSpecificOrder", getOrdersForTable(2));
				request.getSession().setAttribute("tableNumber", "2");
				dispatcher.forward(request, response);
				//response.sendRedirect("tableOrder.jsp");
				break;
			case "Table 3":  
				request.getSession().setAttribute("tableSpecificOrder", getOrdersForTable(3));
				request.getSession().setAttribute("tableNumber", "3");
				dispatcher.forward(request, response);
				//response.sendRedirect("tableOrder.jsp");
			case "Table 4":
				request.getSession().setAttribute("tableSpecificOrder", getOrdersForTable(4));
				request.getSession().setAttribute("tableNumber", "4");
				dispatcher.forward(request, response);
				//response.sendRedirect("tableOrder.jsp");
			case "View All Orders":  
				printOrderTable(response);
				break;
			case "Log Out":  
				response.setHeader("Cache-Control","no-cache"); 
				response.setHeader("Cache-Control","no-store");
				response.sendRedirect("login.jsp");
				HttpSession session = request.getSession();
				session.invalidate();
				break;
			default:		
				break;
		}
	}
	
	public void printOrderTable(HttpServletResponse response) throws IOException{
		OrderService os = new OrderService();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Orders by table number");
		List<FoodDTO> tableList = os.getOrdersByTableNumber(2);
		for(int i = 0; i < tableList.size();i++){
			System.out.println(tableList.get(i).getFoodName());
			out.println(tableList.get(i).getFoodName() + " at Table " + tableList.get(i).getTableNumber());
			out.println("<br>");
		}
		out.print("<center><table>");
		
	}
	
	public String getOrdersForTable(int orderid){
		OrderService os = new OrderService();
		List<FoodDTO> foodList = os.getOrdersByTableNumber(orderid);
		String orderString = "";
		try{
			if(foodList.size()!=0){
				System.out.println("check2");
				for(int i = 0; i < foodList.size();i++){
					orderString += foodList.get(i).getFoodName()+",";
				}
			}
		}catch(Exception e){
			System.out.println("exception caught");
		}
		System.out.print(orderString);
		
		return orderString;
	}

}
