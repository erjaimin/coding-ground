package com.invoices.mvc;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountingServlet
 */
public class CountingServlet extends HttpServlet{
	private int counter;
	private Object lock = new Object();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		counter = 0;
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String hobby = request.getParameter("hobby");
		ArrayList names = (ArrayList) request.getAttribute("names");
		response.getWriter().println("<html><body> The people who like "+hobby+" are: <br/>");
		for(Object name : names){
			response.getWriter().println(name +"<br/>");
		}
		response.getWriter().println("</body></html>");
	}	
}
