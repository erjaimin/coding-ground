package ca.etsmtl.simulation.views;

import javax.swing.SwingWorker;

import ca.etsmtl.simulation.IntervalSale;
import ca.etsmtl.simulation.RandomSale;
import ca.etsmtl.simulation.StrategyContext;
import ca.etsmtl.simulation.controllers.XMLController;
/**
 * triggers the simulation and starts various events 
 *
 */
public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 10;
	private static int time;
	private static StrategyContext context = new StrategyContext();
	private static RandomSale randomSale = new RandomSale();
	private static IntervalSale intervalSale = new IntervalSale();
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			++time;
			Thread.sleep(DELAI);
			if(XMLController.getUsineMap() != null && XMLController.getCheminList() != null
					&& XMLController.getUsineMetadonnees() != null) {
				firePropertyChange("PROCESS", null, null);
				if(PanneauStrategie.getStrategyNo() != 0) {
					createStrategy();
					boolean executeStrategy = context.executeStrategy(time);
					if(executeStrategy) {
						firePropertyChange("SALE", null, null);
					}	
				}
			}
		}
		return null;
	}

	/**
	 * sets the strategy based on user menu selection
	 */
	private void createStrategy() {
		if(PanneauStrategie.getStrategyNo() == 1) {
			context.setStrategy(randomSale);
		}else {
			context.setStrategy(intervalSale);
		}
	}

}