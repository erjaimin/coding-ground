package com.mvc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.mvc.model.ImageModel;
import com.mvc.model.PerspectiveModel;

public class SaveCommand extends Command{

	private File file;
	private ImageModel imgModel;
	private PerspectiveModel topPerspectiveModel;
	private PerspectiveModel bottomPerspectiveModel;
	private ObjectOutputStream oos;
	
	public SaveCommand(ImageModel imgModel, PerspectiveModel topPerspectiveModel,
			PerspectiveModel bottomPerspectiveModel) {
		this.imgModel = imgModel;
		this.topPerspectiveModel = topPerspectiveModel;
		this.bottomPerspectiveModel = bottomPerspectiveModel;
	}


	@Override
	public void execute() {
		if(file != null){
			List<Object> woi=new ArrayList<>();
			try {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				woi.add(imgModel);
				woi.add(topPerspectiveModel);
				woi.add(bottomPerspectiveModel);
				oos.writeObject(woi);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}


	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}


	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}


	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
