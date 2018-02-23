package ca.etsmtl.simulation.models;

import static ca.etsmtl.simulation.Constants.CENTER_ALIGN;

/**
 * represents the package exchanged between factories/warehouse in the simulation
 *
 */
public class Courrier {
	
	private String type;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int currentX;
	private int currentY;
	private int direction;
	private String destUsineId;
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the startX
	 */
	public int getStartX() {
		return startX;
	}
	/**
	 * @param startX the startX to set
	 */
	public void setStartX(int startX) {
		this.startX = startX;
	}
	/**
	 * @return the startY
	 */
	public int getStartY() {
		return startY;
	}
	/**
	 * @param startY the startY to set
	 */
	public void setStartY(int startY) {
		this.startY = startY;
	}
	/**
	 * @return the endX
	 */
	public int getEndX() {
		return endX;
	}
	/**
	 * @param endX the endX to set
	 */
	public void setEndX(int endX) {
		this.endX = endX;
	}
	/**
	 * @return the endY
	 */
	public int getEndY() {
		return endY;
	}
	/**
	 * @param endY the endY to set
	 */
	public void setEndY(int endY) {
		this.endY = endY;
	}
	/**
	 * @return the currentX
	 */
	public int getCurrentX() {
		return currentX;
	}
	/**
	 * @param currentX the currentX to set
	 */
	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}
	/**
	 * @return the currentY
	 */
	public int getCurrentY() {
		return currentY;
	}
	/**
	 * @param currentY the currentY to set
	 */
	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	/**
	 * checks the x and y coordinates of source and destination
	 *  to decide the packge is delivered or not
	 * @return
	 */
	public boolean isReached() {
		boolean reached = false;
		int xOffset = startX  - endX;
		int yOffset = startY  - endY;
		int xRevOffset = endX - startX;
		int yRevOffset = endY - startY;
		if(startX < endX && startY < endY)
		{
			if(xRevOffset < CENTER_ALIGN && yRevOffset < CENTER_ALIGN)
			{
				reached = true;
			}
		}
		else if(startX > endX && startY > endY)
		{
			if(xOffset < CENTER_ALIGN && yOffset < CENTER_ALIGN)
			{
				reached = true;
			}
		}
		else if(startX < endX && startY > endY)
		{
			if(xRevOffset < CENTER_ALIGN && yOffset < CENTER_ALIGN)
			{
				reached = true;
			}
		}
		else if(startX > endX && startY < endY)
		{
			if(xOffset < CENTER_ALIGN && yRevOffset < CENTER_ALIGN)
			{
				reached = true;
			}
		}
		return reached;
	}
	
	/**
	 * moves the package in respective direction based on the direction
	 */
	public void move() {
		startX = currentX;
		startY = currentY;
		boolean flagX = startX != endX;
		boolean flagY = startY != endY;
		if(direction == 0) {
			incrementX(flagX);	
		}else if(direction == 45) {
			incrementX(flagX);
			incrementY(flagY);	
		}else if(direction == 90) {
			incrementY(flagY);	
		}else if(direction == 135) {
			decrementX(flagX);
			incrementY(flagY);	
		}else if(direction == 180) {
			decrementX(flagX);
		}else if(direction == 225) {
			decrementX(flagX);
			decrementY(flagY);	
		}else if(direction == 270) {
			decrementY(flagY);	
		}else if(direction == 315) {
			incrementX(flagX);
			decrementY(flagY);	
		}
	}
	
	/**
	 * forwards package in vertical direction
	 * @param flagY
	 */
	private void incrementY(boolean flagY) {
		if(flagY) {
			currentY = startY + 10;
		}
	}
	
	/**
	 * forwards package in horizontal direction
	 * @param flagX
	 */
	private void incrementX(boolean flagX) {
		if(flagX) {
			currentX = startX + 10;
		}
	}
	/**
	 * backwards package in vertical direction
	 * @param flagY
	 */
	private void decrementY(boolean flagY) {
		if(flagY) {
			currentY = startY - 10;
		}
	}
	/**
	 * backwards package in horizontal direction
	 * @param flagX
	 */
	private void decrementX(boolean flagX) {
		if(flagX) {
			currentX = startX - 10;
		}
	}
	/**
	 * @return the destUsine
	 */
	public String getDestUsineId() {
		return destUsineId;
	}
	/**
	 * @param linkUsine the destUsineId to set
	 */
	public void setDestUsineId(String destUsineId) {
		this.destUsineId = destUsineId;
	}
}
