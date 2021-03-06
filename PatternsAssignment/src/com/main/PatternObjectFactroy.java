package com.main;


import javax.swing.JPanel;
import com.mvc.controller.ZoomCommand;
import com.mvc.model.PerspectiveModel;
import com.mvc.view.ImageView;
import com.mvc.view.PerspectiveView;

public class PatternObjectFactroy {

	public static JPanel getJpanel(String viewName){
		if(viewName.equals(Constants.IMAGE_VIEW)){
			return new ImageView();
		}else if(viewName.equals(Constants.TOP_VIEW) || viewName.equals(Constants.BOTTOM_VIEW)){
			return new PerspectiveView();
		}
		return null;
	}
	
	public static ZoomCommand getActionCommand(PerspectiveModel topPerspectiveModel, int notches) {
		return new ZoomCommand(topPerspectiveModel);
	}
}
