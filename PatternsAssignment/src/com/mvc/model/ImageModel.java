package com.mvc.model;

import java.io.File;
import java.io.Serializable;
import java.util.Observable;

public class ImageModel extends Observable implements Serializable{
	
	private static final long serialVersionUID = 791795240588450647L;
	private File inputFile;

	/**
	 * @return the inputFile
	 */
	public File getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile the inputFile to set
	 */
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
		setChanged();
		notifyObservers();
	}


	
	
	
}
