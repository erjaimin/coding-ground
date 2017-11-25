package org.mcgill.ccs2_505.assignment02.inventory;
public class Book extends Product {
	private String author;
	
	public Book(String id, double price, String author) {
		super(id, price);
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		return "Book " + super.toString() + ", author=" + author;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Book) {
			Book other = (Book) o;
			return super.equals(other)
					&& (this.author.equals(other.author));
		}
		return false;
	}	
}
