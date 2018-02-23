package ca.etsmtl.simulation;

/**
 *  The sale is made at fixed intervals; for example after fixed amount of time has elapsed. 
 *
 */
public class IntervalSale implements Strategy {

	@Override
	public boolean executeStrategy(int timePeriod) {
		
		if(timePeriod % Constants.INTERVAL_SALE == 0)
			return true;
		return false;
	}

}
