package org.mcgill.ccs2_505.assignment02.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mcgill.ccs2_505.assignment02.inventory.Inventory;
import org.mcgill.ccs2_505.assignment02.inventory.InventoryEntry;

/**
 * Servlet implementation class DisplayInventory
 */
public class DisplayInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Inventory inventory = (Inventory) getServletContext().getAttribute("inventory");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 
		out.println("<html><head><meta charset=\"UTF-8\"><title>Inventory Status</title></head>"
				+ "<body><h1>McGill Bookstore Inventory Status</h1>");
		
		String msg = (String) request.getAttribute("errorMsg"); 
		if(msg != null) {
			out.print("<font color=\"red\"><strong>" + msg + "</strong></font>");
		}
		
		/* Print out the Books */
		out.println("<h2>Books</h2><table border=\"1\">"
				+ "<tr><th>UID</th><th>QTY</th><th>Price</th><th>Author</th></tr>");
		for(String id : inventory.getProductIDs()) {
			InventoryEntry ie = inventory.get(id);
			if(ie.getProductType().equals("BOOK")) {
				out.println("<tr><td>" + id);
				out.println("</td><td>" + ie.getQuantity());
				out.println("</td><td>" + ie.getPrice());
				out.println("</td><td>" + ie.getAuthor());
				out.print("</td></tr>");
			}
			
		}
		out.println("</table>");
		
		
		/* Print out the CompactDiscs */
		out.println("<h2>CompactDiscs</h2><table border=\"1\">"
				+ "<tr><th>UID</th><th>QTY</th><th>Price</th><th>Artist</th></tr>");
		for(String id : inventory.getProductIDs()) {
			InventoryEntry ie = inventory.get(id);
			if(ie.getProductType().equals("COMPACTDISC")) {
				out.println("<tr><td>" + id);
				out.println("</td><td>" + ie.getQuantity());
				out.println("</td><td>" + ie.getPrice());
				out.println("</td><td>" + ie.getArtist());
				out.print("</td></tr>");
			}
		}
		out.println("</table>");
		
		out.println("<p>Back to the <a href=\"" + response.encodeURL("form.html"));
		out.println("\">form</a></body></html>");
	}

}
