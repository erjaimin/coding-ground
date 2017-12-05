package com.mvc.controller;

import java.awt.Point;

import com.mvc.model.Memento;
import com.mvc.model.PerspectiveModel;

public class TranslateCommand extends Command{

	private PerspectiveModel model;
	private Point location;
	
	public TranslateCommand(PerspectiveModel model) {
		this.model = model;
		this.memento = new Memento();
	}

	@Override
	public void execute() {
		memento.setLocation(new Point(model.getPositionX(), model.getPositionY()));
		model.updateLocation(location);
	}

	/**
	 * @return the model
	 */
	public PerspectiveModel getModel() {
		return model;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	@Override
	public void unexecute() {
		model.updateLocation(memento.getLocation());
	}
}
