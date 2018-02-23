package ca.etsmtl.simulation;

import ca.etsmtl.simulation.views.Environnement;
import ca.etsmtl.simulation.views.FenetrePrincipale;

/**
 * main class
 *
 */
public class Simulation {

	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
