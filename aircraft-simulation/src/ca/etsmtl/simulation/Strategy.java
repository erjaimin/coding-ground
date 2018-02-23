package ca.etsmtl.simulation;

/**
 * strategy interface defining action 
 *
 */
public interface Strategy {
	
	/**
	 * run the strategy
	 * @param timePeriod
	 * @return run strategy if true, otherwise don't run
	 */
	boolean executeStrategy(int timePeriod);
}
