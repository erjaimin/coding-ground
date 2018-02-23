package ca.etsmtl.simulation;

/**
 * 
 * context class where strategy can be injected dynamically
 */
public class StrategyContext {
	
	private Strategy strategy;

	/**
	 * @return the strategy
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * 
	 * @param timePeriod
	 * @return
	 */
	public boolean executeStrategy(int timePeriod) {
		return strategy.executeStrategy(timePeriod);
	}
}
