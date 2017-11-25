package org.mcgill.ccs2_505.assignment02.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mcgill.ccs2_505.assignment02.inventory.Book;
import org.mcgill.ccs2_505.assignment02.inventory.CompactDisc;
import org.mcgill.ccs2_505.assignment02.inventory.Inventory;
import org.mcgill.ccs2_505.assignment02.inventory.InventoryEntry;

/**
 * Servlet implementation class DisplayInventory
 */
public class DisplayInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayInventory() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = request.getServletContext();
		Inventory inventory = (Inventory) servletContext.getAttribute("inventory");
		HashMap<String, InventoryEntry> inventoryMap = inventory.getInventory();
		List<InventoryEntry> books = new ArrayList<>();
		List<InventoryEntry> discs = new ArrayList<>();
		for(InventoryEntry entry : inventoryMap.values()){
			if(entry.getProduct() instanceof Book){
				books.add(entry);
			}
			if(entry.getProduct() instanceof CompactDisc){
				discs.add(entry);
			}
		}
		request.setAttribute("booksList", books);
		request.setAttribute("discsList", discs);
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
}
