package ca.etsmtl.simulation.models;

/**
 * represents the inputs coming into factory/warehouse
 *
 */
public class Entree {
	String type;
	int quantite;

	/**
	 * constructor
	 * @param type
	 */
	public Entree(String type) {
		this.type = type;
	}
	
	/**
	 * constructor
	 * @param type
	 * @param quantite
	 */
	public Entree(String type, int quantite) {
		this.type = type;
		this.quantite = quantite;
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
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entree [type=" + type + ", quantite=" + quantite + "]";
	}
}
