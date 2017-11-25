package dicegame.framework;

/**
 * This class represents a Player
 */
public class Player implements Comparable<Player>{
	
	/**
	 * data members
	 */
	private int id;
	private int score;
	
	/**
	 * create an instance of {@link Player}
	 * @param id player id
	 */
	public Player(int id){
		this.id = id;
	}
	
	/**
	 * create an instance of {@link Player}
	 * @param id player id
	 * @param score player score
	 */
	public Player(int id, int score){
		this.id = id;
		this.score = score;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(Player other) {
		if(other != null){
			return other.getScore() - this.getScore();
		}
		throw new IllegalArgumentException("Player to compare can't be empty");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + score;
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
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		if (score != other.score)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", score=" + score + "]";
	}
}
