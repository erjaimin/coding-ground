package org.mcgill.ccs2_505.assignment02.inventory;
public class InventoryEntry {
	private Product product;
	private int quantity;
	
	public InventoryEntry(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getID() {
		return product.getID();
	}

	public boolean equals(Object o) {
		if(o instanceof InventoryEntry) {
			InventoryEntry inventoryEntry = (InventoryEntry) o;
			return this.product.equals(inventoryEntry.product)
					&& (this.quantity == inventoryEntry.quantity);
		}
		return false;
	}
	
	/**
	 * ASSIGNMENT02: Modified for use in saving to disk
	 */
	public String toString() {
		String ret = "";
		String extra = "";
		if(product instanceof Book) {
			ret += "BOOK";
			extra = ((Book) product).getAuthor();
		}
		else if(product instanceof CompactDisc) {
			ret += "COMPACTDISC";
			extra = ((CompactDisc) product).getArtist();
		}
		
		ret += "," + product.getID();
		ret += "," + product.getPrice();
		ret += "," + getQuantity();
		ret += "," + extra;
		return ret;
	}
	
	/**
	 * ASSIGNMENT02
	 * @return The price of the product in the InventoryEntry
	 */
	public double getPrice() {
		return product.getPrice();
	}
	
	/**
	 * ASSIGNMENT02
	 * @return the instance type as a string
	 */
	public String getProductType() {
		if(product instanceof Book) {
			return "BOOK";
		}
		else if(product instanceof CompactDisc) {
			return "COMPACTDISC";
		}
		return "INVALID TYPE";
	}
	
	/**
	 * ASSIGNMENT02
	 * @return the name of the author if type is BOOK. Null otherwise
	 */
	public String getAuthor() {
		if(product instanceof Book) {
			return ((Book) product).getAuthor();
		}
		return null;
	}
	
	/**
	 * ASSIGNMENT02
	 * @return the name of the author if type is BOOK. Null otherwise
	 */
	public String getArtist() {
		if(product instanceof CompactDisc) {
			return ((CompactDisc) product).getArtist();
		}
		return null;
	}
}
