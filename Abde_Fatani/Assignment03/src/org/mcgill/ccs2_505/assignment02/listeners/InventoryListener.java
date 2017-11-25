package org.mcgill.ccs2_505.assignment02.listeners;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.mcgill.ccs2_505.assignment02.inventory.Book;
import org.mcgill.ccs2_505.assignment02.inventory.CompactDisc;
import org.mcgill.ccs2_505.assignment02.inventory.Inventory;
import org.mcgill.ccs2_505.assignment02.inventory.InventoryEntry;


@WebListener
public class InventoryListener implements ServletContextListener {
	private Inventory inventory;
    
	public InventoryListener() {
        this.inventory = new Inventory();
    }

	/**
	 * Read the inventory-file context parameter
	 * Creates InventoryEntry objects
	 * Adds IE objects to the inventory
	 */
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext sc = arg0.getServletContext();
        String path = sc.getInitParameter("inventory-file");
        Scanner scan = null;
        try {
        	scan = new Scanner(new File(sc.getRealPath(path)));
        	int lineCounter = 0;
        	while(scan.hasNext()) {
        		try {
        			InventoryEntry ie = parseLine(scan.nextLine());
        			inventory.add(ie);
        		}
        		catch(Exception e) {
        			System.err.println("ERROR: Skipped invalid line " + lineCounter);
        		}
        	}
        }
        catch(Exception e) {
        	System.err.println("ERROR: Unable to open file: " + path);
        }
        finally{
        	if(scan != null) {
        		scan.close();
        	}
        }
        sc.setAttribute("inventory", inventory);
    }

	/**
     * Writes out each inventoryEntry to the file specified in the 
     * inventory-file context parameter
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        ServletContext sc = arg0.getServletContext();
        String path = sc.getInitParameter("inventory-file");
        PrintWriter pw = null;
        try {
        	pw = new PrintWriter(sc.getRealPath(path));
        	for(String id : inventory.getProductIDs()) {
        		/* Modified InventoryEntry's toString to pretty-print */
        		pw.println(inventory.get(id).toString());
        	}
        }
        catch(Exception e) {
        	System.err.println("Could not write to file");
        }
        finally {
        	if(pw != null) {
        		pw.close();
        	}
        }
    }
    
    private InventoryEntry parseLine(String line) throws Exception {
    	InventoryEntry ie = null;
    	String[] fields = line.split(",");
    	if(fields.length != 5) {
    		throw new Exception("Invalid number of entries in line");
    	}
    	/* Extract common fields */
    	String id = fields[1];
    	double price = Double.parseDouble(fields[2]);
    	int qty = Integer.parseInt(fields[3]);
    	
    	switch(fields[0]) {
    	case "BOOK":
    		ie = new InventoryEntry(new Book(id, price, fields[4]), qty); 
    		break;
    	case "COMPACTDISC":
    		ie = new InventoryEntry(new CompactDisc(id, price, fields[4]), qty);
    		break;
    	default:
    		throw new Exception("invalid type");
    	}
    	return ie;
    }
}
