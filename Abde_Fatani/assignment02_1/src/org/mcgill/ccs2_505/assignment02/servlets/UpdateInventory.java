package org.mcgill.ccs2_505.assignment02.servlets;

import java.io.IOException;

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
 * Servlet implementation class UpdateInventory
 */
public class UpdateInventory extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "errorMsg";
       
	private String bookId;
	private String bookQty;
	private String bookPrice;
	private String bookAuthor;
	private String discId;
	private String discQty;
	private String discPrice;
	private String discArtist;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInventory() {
        super();
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = request.getServletContext();
		Inventory inventory = (Inventory) servletContext.getAttribute("inventory");
		if(inventory != null){
			bookId = request.getParameter("bookId");
			bookQty = request.getParameter("bookQty");
			bookPrice = request.getParameter("bookPrice");
			bookAuthor = request.getParameter("bookAuthor");
			discId = request.getParameter("discId");
			discQty = request.getParameter("discQty");
			discPrice = request.getParameter("discPrice");
			discArtist = request.getParameter("discArtist");
			boolean bookInfoEmpty = checkForEmptyInputs(bookId, bookQty, bookPrice, bookAuthor);
			boolean discInfoEmpty = checkForEmptyInputs(discId, discQty, discPrice, discArtist);
			if(bookInfoEmpty && discInfoEmpty){
				request.setAttribute(ERROR_MESSAGE, "Please provide inputs for the fields.");
			}else{
				if(!bookInfoEmpty){
					addBookEntry(request, inventory);
				}
				if(!discInfoEmpty){
					addDiscEntry(request, inventory);
				}
			}
			servletContext.setAttribute("inventory", inventory);
			servletContext.log("the inventory has been updated");
		}else{
			request.setAttribute(ERROR_MESSAGE, "Inventory doesn't exist.");
		}
		request.getRequestDispatcher("displayInventory.do").forward(request, response);
	}

	/**
	 * Add the disc entry in inventory if valid, otherwise sets error message
	 * @param request
	 * @param inventory
	 */
	private void addDiscEntry(HttpServletRequest request, Inventory inventory) {
		if(validatePriceQty(discQty, discPrice)){
			CompactDisc disc = new CompactDisc(discId, Double.parseDouble(discPrice), discArtist);
			inventory.add(new InventoryEntry(disc, Integer.parseInt(discQty)));
		}else{
			request.setAttribute(ERROR_MESSAGE, "Please enter valid compact disc price and/or quantity.");
		}
	}

	/**
	 * Add the book entry in inventory if valid, otherwise sets error message
	 * @param request
	 * @param inventory
	 */
	private void addBookEntry(HttpServletRequest request, Inventory inventory) {
		if(validatePriceQty(bookQty, bookPrice)){
			Book book = new Book(bookId, Double.parseDouble(bookPrice), bookAuthor);
			inventory.add(new InventoryEntry(book, Integer.parseInt(bookQty)));
		}else{
			request.setAttribute(ERROR_MESSAGE, "Please enter valid book price and/or quantity.");
		}
	}

	/**
	 * Validates the price and product quantity
	 * @param qty
	 * @param price
	 * @return true if valid otherwise returns false
	 */
	private boolean validatePriceQty(String qty, String price) {
		try{
			Integer.parseInt(qty);
			Double.parseDouble(price);
		}catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Check for empty form inputs
	 * @param uid
	 * @param qty
	 * @param price
	 * @param authorArtist
	 * @return true if any of the form fields are empty, otherwise returns false
	 */
	private boolean checkForEmptyInputs(String uid, String qty, String price, String authorArtist) {
		return uid.isEmpty() || qty.isEmpty() || price.isEmpty() || authorArtist.isEmpty();
	}
}
