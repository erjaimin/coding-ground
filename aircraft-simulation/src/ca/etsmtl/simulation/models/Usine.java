package ca.etsmtl.simulation.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ca.etsmtl.simulation.controllers.SimulationController;

/**
 * represents {@link Usine} class and associated behaviors
 *
 */
public class Usine extends BaseUsine implements Observer{
	
	private int intervalProduction;
	private int startTime;
	private List<Entree> actualEntree = new ArrayList<>();
	
	/**
	 * constructor
	 */
	public Usine() {}

	/**
	 * constructor
	 * @param type usine
	 */
	public Usine(String type) {
	    super(type);
    }
	
	/**
	 * constructor
	 * @param usine
	 * @param id
	 * @param x
	 * @param y
	 */
	public Usine(BaseUsine usine, String id, int x, int y) {
		super(usine, id, x, y);
		this.intervalProduction = ((Usine)usine).intervalProduction;
	}

	/**
	 * constructor
	 * @param usine
	 * @param id
	 * @param x
	 * @param y
	 * @param intervalProduction
	 */
    public Usine(BaseUsine usine, String id, int x, int y, int intervalProduction) {
    		this(usine, id, x, y);
    		this.intervalProduction = intervalProduction;
    }

	/**
	 * @return the intervalProduction
	 */
	public int getIntervalProduction() {
		return intervalProduction;
	}

	/**
	 * @param intervalProduction the intervalProduction to set
	 */
	public void setIntervalProduction(int intervalProduction) {
		this.intervalProduction = intervalProduction;
	}
	
	/**
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the actualEntree
	 */
	public List<Entree> getActualEntree() {
		return actualEntree;
	}

	/**
	 * @param actualEntree the actualEntree to set
	 */
	public void setActualEntree(List<Entree> actualEntree) {
		this.actualEntree = actualEntree;
	}
	
	/**
	 * check if production time has elapsed and all parts are ready for delivery
	 * @return true if ready, false if not
	 */
	public boolean isPartsReady() {
		if(getStartTime() >= getIntervalProduction()) {
			setStartTime(0);
			if(isPartsAvailable()) {
				deductPartsQuantity();
				return true;
			}
		}
		setStartTime(getStartTime() + 1);
		return false;
	}
	
	/**
	 * check if all parts are ready for delivery
	 * @return true if ready, false if not
	 */
	private boolean isPartsAvailable() {
		boolean available = true;
		for(Entree e :  entree) {
			int reqQty = e.getQuantite();
			Entree currQty = actualEntree.stream().filter(ae -> ae.getType().equals(e.getType())).findFirst().orElse(null);
			if(currQty == null|| currQty.getQuantite() < reqQty) {
				available = false;
				break;
			}
		}
		return available;
	}

	/**
	 * deducts the parts quantity from available inventory
	 */
	private void deductPartsQuantity() {
		for(Entree entry : entree) {
			int reqQty = entry.getQuantite();
			actualEntree.stream()
				.filter(ae -> ae.getType().equals(entry.getType()))
				.forEach(e -> e.setQuantite(e.getQuantite() - reqQty));
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// stops the production
		SimulationController.isCapacityReached = true;
	}
	
}
