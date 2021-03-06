package com.mvc.controller;


import com.mvc.model.Memento;
import com.mvc.model.PerspectiveModel;

public class ZoomCommand extends Command {

	private PerspectiveModel model;
	private int notches;
	private double zoom;
	
	public ZoomCommand(PerspectiveModel model) {
		this.model = model;
		this.memento = new Memento();
	}
	
	public boolean calculateZoom() {
		boolean resize = false;
		zoom = model.getZoom();
		double temp = zoom - (getNotches() * 0.2);
		if (temp != zoom && temp > 0.2 && temp < 1.8) {
            zoom = temp;
            memento.setZoomLevel(zoom);
            model.setZoom(zoom);
            resize = true;
        }
		return resize;
	}
	
	@Override
	public void execute() {
		int newWidth = (int) (model.getImage().getWidth() * zoom);
		int newHeight = (int) (model.getImage().getHeight() * zoom);
		model.updateDimentions(newWidth, newHeight);
	}

	/**
	 * @return the notches
	 */
	public int getNotches() {
		return notches;
	}

	/**
	 * @param notches the notches to set
	 */
	public void setNotches(int notches) {
		this.notches = notches;
	}

	@Override
	public void unexecute() {
		int newWidth = (int) (model.getImage().getWidth() * memento.getZoomLevel());
		int newHeight = (int) (model.getImage().getHeight() * memento.getZoomLevel());
		model.updateDimentions(newWidth, newHeight);
	}

}
