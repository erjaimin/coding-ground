package com.mvc.model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Observable;

import javax.swing.ImageIcon;

import com.main.ImageUtil;

public class PerspectiveModel extends Observable implements Serializable{
	private static final long serialVersionUID = 3928472212888130575L;
	private ImageIcon image;
	private String view;
	private int width;
	private int height;
	private int positionX;
	private int positionY;
	private double zoom;
	
	public PerspectiveModel(String view) {
		this.setView(view);
	}
	
	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return ImageUtil.getImage(image);
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = ImageUtil.getIcon(image);
		setPositionX(0);setPositionY(0);
		setWidth(image.getWidth());
		setHeight(image.getHeight());
		setZoom(1.0);
		update();
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
	public void updateDimentions(int newWidth, int newHeight) {
		setWidth(newWidth);
		setHeight(newHeight);
		update();
	}
	
	public void updateLocation(Point location) {
		setPositionX(location.x);
		setPositionY(location.y);
		update();
	}

	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}
	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}
	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	/**
	 * @return the view
	 */
	public String getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return the zoom
	 */
	public double getZoom() {
		return zoom;
	}

	/**
	 * @param zoom the zoom to set
	 */
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
}
