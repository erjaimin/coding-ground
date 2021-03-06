package com.mvc.controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import com.main.ImageUtil;
import com.mvc.model.ImageModel;
import com.mvc.model.PerspectiveModel;
import com.mvc.view.MainFrame;

public class ModelViewController {
	private MainFrame view;
	private ImageModel imgModel;
	private PerspectiveModel topPerspectiveModel;
	private PerspectiveModel bottomPerspectiveModel;
	private SaveCommand saveCommand;
	private LoadCommand loadCommand;

	public ModelViewController(MainFrame view, ImageModel imgModel, PerspectiveModel topPerspectiveModel,
			PerspectiveModel bottomPerspectiveModel) {
		this.view = view;
		this.imgModel = imgModel;
		this.topPerspectiveModel = topPerspectiveModel;
		this.bottomPerspectiveModel = bottomPerspectiveModel;
		this.loadCommand = new LoadCommand(imgModel, topPerspectiveModel, bottomPerspectiveModel);
		setUpListeners();
	}

	private void setUpListeners() {
		view.getOpen().addActionListener(e -> {
			view.openFileDialog();
			File selectedFile = view.getFileChooser().getSelectedFile();
			if(selectedFile != null){
				updateModels(selectedFile);
				CommandManager.getInstance().clear();
				this.saveCommand = new SaveCommand(imgModel, topPerspectiveModel, bottomPerspectiveModel);
			}	
		});

		view.getQuit().addActionListener(e -> System.exit(0));

		Component topPanel = view.getPerspectivePlane().getLeftComponent();
		Component bottomPanel = view.getPerspectivePlane().getRightComponent();
		topPanel.addMouseWheelListener(e -> executeZoomCommand(e, topPerspectiveModel));
		bottomPanel.addMouseWheelListener(e -> executeZoomCommand(e, bottomPerspectiveModel));
		topPanel.addMouseListener(new MouseAdapter() {
			Point startPoint;
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	startPoint = e.getPoint();
		    }
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	executeTranslateCommand(e, topPerspectiveModel, startPoint);
		    }
		});
		
		bottomPanel.addMouseListener(new MouseAdapter() {
			Point startPoint;
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	startPoint = e.getPoint();
		    }
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	executeTranslateCommand(e, bottomPerspectiveModel, startPoint);
		    }
		});
		view.getSave().addActionListener(e -> {
			if(imgModel.getInputFile() != null){
				view.openSaveDialog();
				saveCommand.setFile(view.getFileChooser().getSelectedFile());
				saveCommand.execute();
			}else{
				view.showErrorDialog("Choose image first!");
			}
		});
		view.getLoad().addActionListener(e -> {
			view.openFileDialog();
			loadCommand.setFile(view.getFileChooser().getSelectedFile());
			loadCommand.execute();
		});
		view.getUndo().addActionListener(e -> CommandManager.getInstance().undo());
		view.getRedo().addActionListener(e -> CommandManager.getInstance().redo());
	}

	private void executeZoomCommand(MouseWheelEvent e, PerspectiveModel model) {
		if(imgModel.getInputFile() != null){
			ZoomCommand command = new ZoomCommand(model);
			command.setNotches(e.getWheelRotation());
			if(command.calculateZoom()){
				command.execute();
				CommandManager.getInstance().addToCommandStack(command);
			}	
		}	
	}

	private void executeTranslateCommand(MouseEvent e, PerspectiveModel model, Point startPoint) {
		if(imgModel.getInputFile() != null){
			TranslateCommand command = new TranslateCommand(model);
			int thisX = command.getModel().getPositionX();
	    	int thisY = command.getModel().getPositionY();
	    	int xMoved = e.getX() - startPoint.x;
	        int yMoved = e.getY() - startPoint.y;
	        command.setLocation(new Point(thisX + xMoved, thisY + yMoved));
	        command.execute();
	        CommandManager.getInstance().addToCommandStack(command);
		}    
	}
	
	private void updateModels(File selectedFile) {
		BufferedImage readImage = ImageUtil.readImage(selectedFile);
		imgModel.setInputFile(selectedFile);
		topPerspectiveModel.setImage(readImage);
		bottomPerspectiveModel.setImage(readImage);
	}

}
