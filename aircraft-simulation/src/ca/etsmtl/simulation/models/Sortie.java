package ca.etsmtl.simulation.models;

/**
 * represents the output factory type for the factory 
 *
 */
public class Sortie {
	String type;

	/**
	 * constructor
	 * @param type
	 */
	public Sortie(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
