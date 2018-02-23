package ca.etsmtl.simulation.models;

/**
 * represents the link between factories
 */
public class Chemin {
	String de;
	String vers;

	/**
	 * constructor
	 * @param de
	 * @param vers
	 */
	public Chemin(String de, String vers) {
		this.de = de;
		this.vers = vers;
	}

	/**
	 * @return the de
	 */
	public String getDe() {
		return de;
	}

	/**
	 * @param de the de to set
	 */
	public void setDe(String de) {
		this.de = de;
	}

	/**
	 * @return the vers
	 */
	public String getVers() {
		return vers;
	}

	/**
	 * @param vers the vers to set
	 */
	public void setVers(String vers) {
		this.vers = vers;
	}
	
}
