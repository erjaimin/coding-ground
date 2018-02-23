package ca.etsmtl.simulation;

import java.util.Random;
/**
 * The sale is made according to a random probability function.
 *
 */
public class RandomSale implements Strategy {

	private static Random random = new Random();
	
	@Override
	public boolean executeStrategy(int timePeriod) {
		int randomNo = random.nextInt(Constants.RANDOM_SALE);
		if(randomNo > 98)
			return true;
		return false;
	}

}
