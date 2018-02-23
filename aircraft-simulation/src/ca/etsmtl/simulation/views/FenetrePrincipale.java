package ca.etsmtl.simulation.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import ca.etsmtl.simulation.controllers.SimulationController;
import ca.etsmtl.simulation.controllers.XMLController;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);
	private SimulationController controller;

	public FenetrePrincipale() {
		PanneauPrincipal panneauPrincipal = new PanneauPrincipal();
		MenuFenetre menuFenetre = new MenuFenetre();
		controller = new SimulationController();
		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);

		setVisible(true);

		setLocationRelativeTo(null);

		setResizable(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(XMLController.getUsineMap() != null) {
			controller.handleChangeEvent(evt, this);
		}	
	}
	
	/**
	 * reloads the view 
	 */
	public void refreshView() {
		repaint();
	}
}
