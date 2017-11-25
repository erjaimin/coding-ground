package org.mcgill.ccs2_505.assignment02.inventory;

import java.util.HashMap;

/**
 * This class represent inventory and stores related information
 *
 */
public class Inventory {
	private HashMap<String, InventoryEntry> inventory;
	
	public Inventory() {
		inventory = new HashMap<String, InventoryEntry>();
	}
	
	/**
	 * Adds the Product to the inventory.
	 * If the product exists in the inventory, add one to the entry in the inventory
	 * Otherwise, create an entry and add it to the inventory
	 * 
	 * @param product Represents a Product object. Assume it and all its fields are non-null
	 */
	public void add(Product product) {
		InventoryEntry entry = inventory.get(product.getId());
		if(entry != null) {
			entry.setQuantity(entry.getQuantity() + 1);
		}
		else {
			inventory.put(product.getId(), new InventoryEntry(product, 1));
		}
	}
	
	/**
	 * @return the inventory
	 */
	public HashMap<String, InventoryEntry> getInventory() {
		return inventory;
	}

	/**
	 * Adds an already defined entry to the inventory
	 * If the entry exists in the inventory, add the parameter's quantity to the inventory
	 * Otherwise, add the entry to the inventory
	 * 
	 * @param item Represents an inventory entry. Assume it and all its fields are non-null
	 */
	public void add(InventoryEntry entry) {
		InventoryEntry entryInv = inventory.get(entry.getId());
		if(entryInv != null) {
			entryInv.setQuantity(entryInv.getQuantity() + entry.getQuantity());
		}
		else {
			inventory.put(entry.getId(), entry);
		}
	}
	
	/**
	 * Removes a product from the inventory given its ID number.
	 * If the quantity > 1, decreases its quantity by 1.
	 * If the quantity = 1, removes the entry from the inventory 
	 * If the entry is not found, does nothing.
	 * 
	 * @param id the unique ID of the entry's product 
	 */
	public void remove(String id) {
		InventoryEntry entryInv = inventory.get(id);
		if(entryInv != null) {
			entryInv.setQuantity(entryInv.getQuantity() -1);
		}
		else {
			inventory.remove(id);
		}
	}
}
