package ca.etsmtl.simulation.models;

/**
 * represents factory/warehouse images
 *
 */
public class Icone {

	String type;
	String path;

	/**
	 * constructor
	 * @param type
	 * @param path
	 */
	public Icone(String type, String path) {
		this.type = type;
		this.path = path;
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

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
}
