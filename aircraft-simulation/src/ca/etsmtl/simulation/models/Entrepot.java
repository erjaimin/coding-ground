package ca.etsmtl.simulation.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * represents {@link Entrepot} and related operations
 *
 */
public class Entrepot extends BaseUsine {

	private static List<Observer> observers = new ArrayList<>();
	private Entree currEntree = new Entree("avion");

	/**
	 * constructor
	 */
	public Entrepot() {
	}
	
	/**
	 * constructor
	 * @param type Entrepot
	 */
	public Entrepot(String type) {
		super(type);
	}

	/**
	 * constructor
	 * @param warehouse
	 * @param id
	 * @param x
	 * @param y
	 */
	public Entrepot(BaseUsine warehouse, String id, int x, int y) {
 		super(warehouse, id, x, y);
	}
	
	/**
	 * adds observer to {@link Entrepot}
	 * @param ob
	 */
	public void addObserver(Observer ob) {
		getObservers().add(ob);
	}
	
	/**
	 * notify all {@link Entrepot} observers
	 */
	public void notifyObservers() {
		System.out.println("Entrepot maxed out! notifying usines...");
		getObservers().stream().forEach(ob -> ob.update(null, null));
	}

	/**
	 * @return the observers
	 */
	public List<Observer> getObservers() {
		return observers;
	}

	/**
	 * @return the currEntree
	 */
	public Entree getCurrEntree() {
		return currEntree;
	}

	/**
	 * @param currEntree the currEntree to set
	 */
	public void setCurrEntree(Entree currEntree) {
		if(currEntree.getQuantite() >= entree.get(0).getQuantite()) {
			notifyObservers();
		}
		this.currEntree = currEntree;
	}

}
