package org.mcgill.ccs2_505.assignment02.listeners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.mcgill.ccs2_505.assignment02.inventory.Book;
import org.mcgill.ccs2_505.assignment02.inventory.CompactDisc;
import org.mcgill.ccs2_505.assignment02.inventory.Inventory;
import org.mcgill.ccs2_505.assignment02.inventory.InventoryEntry;
import org.mcgill.ccs2_505.assignment02.inventory.Product;

/**
 * This class looks for a file and reads inventory details and also writes 
 * updated information to the file
 *
 */
public class InventoryListener implements ServletContextListener{
	
	/**
	 * data members
	 */
	private Inventory inventory;
	private static final String DELIMETER = ",";
	private static final String BOOK_IDENTIFIER = "BOOK";
	private static final String DISC_IDENTIFIER = "COMPACTDISC";
	private static final int NO_OF_FIELDS = 5;
	private String inventoryFileName;

	/**
	 * This method called upon startup and loads inventory
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		inventory = new Inventory();
		ServletContext context = servletContextEvent.getServletContext();
		// get the file configured in deployment descriptor
		inventoryFileName = context.getInitParameter("inventory-file");
		InputStream resourceAsStream = null;
		BufferedReader reader=null; 
		String line = null;
		try {
			resourceAsStream = context.getResourceAsStream(inventoryFileName);
			reader = new BufferedReader(new InputStreamReader(resourceAsStream));
			// read until EOF
			while((line = reader.readLine())!=null){
				String[] fields = line.split(DELIMETER);
				addInventoryEntry(fields);
			}
			// set inventory information to context to utilize in other servlets
			context.setAttribute("inventory", inventory);
			context.log("the inventory has been loaded");
		} catch (IOException | NumberFormatException e) {
			System.err.println(e.getMessage());
		} finally {
			// free up resources
			if(resourceAsStream != null){
				try {
					resourceAsStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This methods parses the fields and creates inventory 
	 * entry based on the product type
	 * 
	 * @param fields
	 */
	private void addInventoryEntry(String[] fields) {
		if(fields.length == NO_OF_FIELDS){
			String productType = fields[0];
			String productId = fields[1];
			double productPrice = Double.parseDouble(fields[2]);
			int quantity = Integer.parseInt(fields[3]);
			String authorArtist = fields[4];
			if(BOOK_IDENTIFIER.equals(productType)){
				Book book = new Book(productId, productPrice, authorArtist);
				inventory.add(new InventoryEntry(book, quantity));
			}else if(DISC_IDENTIFIER.equals(productType)){
				CompactDisc disc = new CompactDisc(productId, productPrice, authorArtist);
				inventory.add(new InventoryEntry(disc, quantity));
			}
		}	
	}
	
	/**
	 * This method called upon shutdown/restart and stores inventory
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		String realPath = servletContext.getRealPath(inventoryFileName);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(realPath));
			Inventory inventoryMap = (Inventory) servletContext.getAttribute("inventory");
			for(InventoryEntry entry : inventoryMap.getInventory().values()){
				StringBuilder sb = new StringBuilder();
				Product product = entry.getProduct();
				// add file row based on the product type
				if (product instanceof Book) {
					Book book = (Book) product;
					sb.append(BOOK_IDENTIFIER).append(DELIMETER).append(book.getId()).append(DELIMETER)
							.append(book.getPrice()).append(DELIMETER).append(entry.getQuantity()).append(DELIMETER)
							.append(book.getAuthor());
				} else if (product instanceof CompactDisc) {
					CompactDisc disc = (CompactDisc) product;
					sb.append(DISC_IDENTIFIER).append(DELIMETER).append(disc.getId()).append(DELIMETER)
							.append(disc.getPrice()).append(DELIMETER).append(entry.getQuantity()).append(DELIMETER)
							.append(disc.getArtist());
				}
				writer.write(sb.toString());
				writer.newLine();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			// free up resources
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}