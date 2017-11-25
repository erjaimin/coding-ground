package org.mcgill.ccs2_505.assignment02.inventory;

/**
 * This class represent the details of an product and its quantity
 *
 */
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
	
	public String getId() {
		return product.getId();
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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
}
