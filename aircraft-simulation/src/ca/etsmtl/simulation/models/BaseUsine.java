package ca.etsmtl.simulation.models;

import java.util.ArrayList;
import java.util.List;

/**
 * base class for {@link Usine} and {@link Entrepot}
 *
 */
public class BaseUsine {
	
	String id;
	int x;
	int y;
	String type;
	List<Icone> icones;
	List<Entree> entree;
	Sortie sortie;

	/**
	 * default constructor
	 */
	public BaseUsine() {}

	/**
	 * constructor
	 * @param type usine type
	 */
	public BaseUsine(String type) {
	    this.type = type;
	    this.icones = new ArrayList<Icone>();
	    this.entree = new ArrayList<Entree>();
    }

	/**
	 * constructor
	 * @param usine usine
	 * @param id usine id
	 * @param x position
	 * @param y position
	 */
    public BaseUsine(BaseUsine usine, String id, int x, int y) {
	    	this.id = id;
	    	this.x = x;
	    	this.y = y;
	    this.type = usine.getType();
	    this.icones = usine.getIcones();
	    this.sortie = usine.getSortie();
	    this.entree = usine.getEntree();
    }

    /**
     * adds entree
     * @param entree
     */
    public void addEntree(Entree entree) {
	    this.entree.add(entree);
    }

    /**
     * add icone
     * @param icone
     */
    public void addIcone(Icone icone) {
	    this.icones.add(icone);
    }
	
    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
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
	 * @return the icones
	 */
	public List<Icone> getIcones() {
		return icones;
	}

	/**
	 * @return the entree
	 */
	public List<Entree> getEntree() {
		return entree;
	}

	/**
	 * @return the sortie
	 */
	public Sortie getSortie() {
		return sortie;
	}

	/**
	 * @param sortie the sortie to set
	 */
	public void setSortie(Sortie sortie) {
		this.sortie = sortie;
	}
}
