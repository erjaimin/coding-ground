package dicegame.framework;

/**
 * This class represents a Dice
 */
public class Dice implements Comparable<Dice>{

	private int faceValue;
	
	/**
	 * creates an instance of {@link Dice}
	 */
	public Dice(){
		super();
	}
	
	/**
	 * creates an instance of {@link Dice}
	 * @param faceValue of dice
	 */
	public Dice(int faceValue) {
		this.setFaceValue(faceValue);
	}

	/**
	 * @return the faceValue
	 */
	public int getFaceValue() {
		return faceValue;
	}

	/**
	 * @param faceValue the faceValue to set
	 */
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

	@Override
	public int compareTo(Dice other) {
		if(other != null){
			return other.getFaceValue() - this.getFaceValue();
		}
		throw new IllegalArgumentException("De to compare can't be empty");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + faceValue;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dice other = (Dice) obj;
		if (faceValue != other.faceValue)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "De [faceValue=" + faceValue + "]";
	}
	
}
