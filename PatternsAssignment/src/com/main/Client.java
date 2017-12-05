package com.main;


import java.util.Observer;

import javax.swing.SwingUtilities;
import com.mvc.controller.ModelViewController;
import com.mvc.model.ImageModel;
import com.mvc.model.PerspectiveModel;
import com.mvc.view.MainFrame;


public class Client {
	public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displayJFrame();
            }
        });
    }
    private static void displayJFrame() {
        MainFrame frame = new MainFrame();
        ImageModel imageModel = new ImageModel();
        PerspectiveModel topModel = new PerspectiveModel(Constants.TOP_VIEW);
        PerspectiveModel bottomModel = new PerspectiveModel(Constants.BOTTOM_VIEW);
        imageModel.addObserver((Observer)frame.getMainPane().getLeftComponent());
        topModel.addObserver((Observer)frame.getPerspectivePlane().getLeftComponent());
        bottomModel.addObserver((Observer)frame.getPerspectivePlane().getRightComponent());
        
        ModelViewController contoller = new ModelViewController(frame, imageModel, topModel, bottomModel);
    }
}
