package ca.etsmtl.simulation.controllers;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import ca.etsmtl.simulation.models.BaseUsine;
import ca.etsmtl.simulation.models.Chemin;
import ca.etsmtl.simulation.models.Courrier;
import ca.etsmtl.simulation.models.Entree;
import ca.etsmtl.simulation.models.Entrepot;
import ca.etsmtl.simulation.models.Sortie;
import ca.etsmtl.simulation.models.Usine;
import ca.etsmtl.simulation.views.FenetrePrincipale;

/**
 * controls the operation between model and views and handles property change events
 *
 */
public class SimulationController{
	
	/**
	 * represent status of warehouse capacity
	 */
	public static boolean isCapacityReached;
	
	/**
	 * handles the events fired by property 
	 * @param evt
	 * @param fenetrePrincipale 
	 */
	public void handleChangeEvent(PropertyChangeEvent evt, FenetrePrincipale fenetrePrincipale) {
		Collection<BaseUsine> usineList = XMLController.getUsineMap().values();
		fenetrePrincipale.refreshView();
		String propertyName = evt.getPropertyName();
		// take actions according to the fired property
		if("PROCESS".equals(propertyName)) {
			if(!isCapacityReached) {
				// prepare couriers if all required parts are received
				usineList.stream().filter(usine -> usine instanceof Usine).forEach(usine -> {
					String linkId = findLinkNode(usine.getId());
					if(linkId != null && ((Usine)usine).isPartsReady()) {
						prepareCourrier((Usine)usine, Integer.parseInt(linkId));
					}
				});
				// send couriers across the network
				if(XMLController.getCourriers().size() > 0) {
					sendCouriers(fenetrePrincipale);
				}
			}else {
				// check for updated capacity to resume production
				usineList.stream().filter(usine -> usine instanceof Entrepot).forEach(entrepot -> {
					if(((Entrepot)entrepot).getCurrEntree().getQuantite() < entrepot.getEntree().get(0).getQuantite()) {
						isCapacityReached = false;
					}
				});
			}	
		}else if("SALE".equals(propertyName)){
			// sell an aircraft to free up the warehouse
			if(isCapacityReached) {
				usineList.stream().filter(usine -> usine instanceof Entrepot).forEach(entrepot -> {
					Entree entree = ((Entrepot)entrepot).getCurrEntree();
					entree.setQuantite(entree.getQuantite() - 1);
					System.out.println("Aircraft Sold...");
				});
			}
		}
	}

	/**
	 * get list of all courriers to be sent out and send them; if sent adjust the quantities 
	 * @param fenetrePrincipale
	 */
	private void sendCouriers(FenetrePrincipale fenetrePrincipale) {
		Iterator<Courrier> courrierIterator = XMLController.getCourriers().iterator();
		while(courrierIterator.hasNext()) {
			Courrier courrier = courrierIterator.next();
			// check if the package is delivered
			if(courrier.isReached()) {
				Collection<BaseUsine> values = XMLController.getUsineMap().values(); 
				Optional<BaseUsine> findFirst = values.stream().filter(v -> v.getId().equals(courrier.getDestUsineId())).findFirst();
				if(findFirst.isPresent()) {
					BaseUsine baseUsine = findFirst.get();
					// update the quantities
					if(baseUsine instanceof Usine) {
						Optional<Entree> courierEntree = ((Usine)baseUsine).getActualEntree().stream().filter(u -> u.getType().equals(courrier.getType())).findFirst();
						if(courierEntree.isPresent()) {
							courierEntree.get().setQuantite(courierEntree.get().getQuantite() + 1);
						}else {
							((Usine)baseUsine).getActualEntree().add(new Entree(courrier.getType(), 1));
						}
					}else {
						// add an aircraft to warehouse
						Entree currEntree = ((Entrepot)baseUsine).getCurrEntree();
						currEntree.setQuantite(currEntree.getQuantite()+1);
						((Entrepot)baseUsine).setCurrEntree(currEntree);
					}
				}
				courrierIterator.remove();
			}else {
				// move the package so it can be delivered
				courrier.move();
				fenetrePrincipale.refreshView();
			}
		}
	}

	/**
	 * returns the target factory id for the souce factory id
	 * @param id to be found
	 * @return id of destination factory
	 */
	private String findLinkNode(String id) {
		return XMLController.getCheminList().stream().filter(chemin -> chemin.getDe().equals(id))
				.map(Chemin::getVers).findFirst().orElse(null);
	}

	/**
	 * creates a courrier with source and destination details
	 * @param usine
	 * @param linkId
	 */
	private void prepareCourrier(Usine usine, int linkId) {
		Sortie sortie = usine.getSortie();
		BaseUsine linkUsine = XMLController.getUsineMap().get(linkId);
		int x = usine.getX();
		int y = usine.getY();
		Courrier courrier = new Courrier();
		courrier.setType(sortie.getType());
		courrier.setStartX(x);
		courrier.setStartY(y);
		courrier.setDestUsineId(linkUsine.getId());
		courrier.setEndX(linkUsine.getX());
		courrier.setEndY(linkUsine.getY());
		courrier.setCurrentX(x+1);
		courrier.setCurrentY(y+1);
		courrier.setDirection(getDirection(x, y, linkUsine.getX(), linkUsine.getY()));
		List<Courrier> courriers = XMLController.getCourriers();
		courriers.add(courrier);
		XMLController.setCourriers(courriers);
	}

	/**
	 * determines the direction of the package to be sent
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	private int getDirection(int x1, int y1, int x2, int y2) {
		int direction = 0;
		if(x1 == x2) {
			direction = (y2 > y1) ? 90 : 270;
		}else if(y1 == y2) {
			direction = (x2 > x1) ? 0 : 180;
		}else if(x1 > x2) {
			direction = (y2 > y1) ? 135 : 225;
		}else if(x2 > x1) {
			direction = (y2 > y1) ? 45 : 315;
		}
		return direction;
	}

}
