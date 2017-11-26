package org.mcgill.ccs2_505.assignment02.inventory;
/**
 * This abstract class defines what kind of items we can keep track of
 * in our inventory
 * 
 * @author Michael Havas
 *
 */
public abstract class Product {
	protected String id;
	protected double price;
	
	public Product(String id, double price) {
		this.id = id;
		this.price = price;
	}
	
	public String getID() {
		return id;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Product) {
			Product other = (Product) o;
			return other.id.equals(this.id)
					&& other.price == this.price;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Product: id=" + id + ", "
				+ ", price=" + price;
	}
	
}
