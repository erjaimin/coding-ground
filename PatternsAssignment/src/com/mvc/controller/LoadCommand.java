package com.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import com.main.Constants;
import com.mvc.model.ImageModel;
import com.mvc.model.PerspectiveModel;

public class LoadCommand extends Command{
	
	private File file;
	private ImageModel imgModel;
	private PerspectiveModel topPerspectiveModel;
	private PerspectiveModel bottomPerspectiveModel;
	private ObjectInputStream ois;

	public LoadCommand(ImageModel imgModel, PerspectiveModel topPerspectiveModel,
			PerspectiveModel bottomPerspectiveModel) {
		this.imgModel = imgModel;
		this.topPerspectiveModel = topPerspectiveModel;
		this.bottomPerspectiveModel = bottomPerspectiveModel;
	}

	@Override
	public void execute() {
		if(file != null){
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				List<Object> woi = (List<Object>) ois.readObject();
				for(Object obj : woi){
					if(obj instanceof ImageModel){
						this.imgModel.setInputFile(((ImageModel)obj).getInputFile());
					}else if(obj instanceof PerspectiveModel){
						PerspectiveModel model = (PerspectiveModel)obj;
						if(Constants.TOP_VIEW.equals(model.getView())){
							updateModel(topPerspectiveModel, model);
						}else if(Constants.BOTTOM_VIEW.equals(model.getView())){
							updateModel(bottomPerspectiveModel, model);
						}
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}

	private void updateModel(PerspectiveModel perspectiveModel, PerspectiveModel model) {
		perspectiveModel.setImage(model.getImage());
		perspectiveModel.setWidth(model.getWidth());
		perspectiveModel.setHeight(model.getHeight());
		perspectiveModel.setPositionX(model.getPositionX());
		perspectiveModel.setPositionY(model.getPositionY());
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
