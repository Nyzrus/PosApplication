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

	/*
	 * tableView.jsp contains several buttons with different values which specify operations
	 * to be performed in the TableViewServlet.
	 * 
	 * Operation selection is performed by a switch statement which specifies
	 * action according to the value of the chosen button.
	 * 
	 * If specific tables are chosen, the database is queried for existing orders corresponding
	 * to that table so the information can be passed to tableOrder.jsp for display.
	 * 
	 * Other buttons include the ability to view all orders on a single page or to logout
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonChosen = request.getParameter("table");
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("tableOrder.jsp");
		switch (buttonChosen) {
			case "Table 1":  
				request.getSession().setAttribute("tableSpecificOrder", getOrdersForTable(1));
				request.getSession().setAttribute("tableNumber", "1");
				dispatcher.forward(request, response);
				break;
			case "Table 2":  
				request.getSession().setAttribute("tableSpecificOrder", getOrdersForTable(2));
				request.getSession().setAttribute("tableNumber", "2");
				dispatcher.forward(request, response);
				break;
			case "Table 3":  
				request.getSession().setAttribute("tableSpecificOrder", getOrdersForTable(3));
				request.getSession().setAttribute("tableNumber", "3");
				dispatcher.forward(request, response);
			case "Table 4":
				request.getSession().setAttribute("tableSpecificOrder", getOrdersForTable(4));
				request.getSession().setAttribute("tableNumber", "4");
				dispatcher.forward(request, response);
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
	/*
	 * Though there is a degree of processing in this method, I kept it in the Servlet(View)
	 * layer because it prints to the View
	 * 
	 */
	public void printOrderTable(HttpServletResponse response) throws IOException{
		OrderService os = new OrderService();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Orders by table number</b><br><br>");
		List<FoodDTO> tableList = os.getAllOrders();
		for(int i = 1; i < 5;i++){
			out.println("Orders at table "+ i);
			out.println("<br><ul>");
			for(int j = 0; j < tableList.size(); j ++){
				if(tableList.get(j).getTableNumber()==i){
					out.println("<li>"+tableList.get(j).getFoodName()+"</li>");
				}
			
			}
			out.print("</ul><br>");
		}
		
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
