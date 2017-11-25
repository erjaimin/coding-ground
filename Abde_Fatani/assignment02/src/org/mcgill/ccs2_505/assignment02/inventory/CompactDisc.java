package org.mcgill.ccs2_505.assignment02.inventory;

/**
 * This class represent the details of a {@link CompactDisc}
 *
 */
public class CompactDisc extends Product {
	private String artist;
	
	public CompactDisc(String id, double price, String artist) {
		super(id, price);
		this.artist = artist;
	}	

	public String getArtist() {
		return artist;
	}

	@Override
	public String toString() {
		return "CD " + super.toString() + ", artist=" + artist;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof CompactDisc) {
			CompactDisc other = (CompactDisc) o;
			return super.equals(other)
					&& (this.artist.equals(other.artist));
		}
		return false;
	}	

}