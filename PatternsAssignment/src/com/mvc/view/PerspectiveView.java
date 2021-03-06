package com.mvc.view;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.mvc.model.PerspectiveModel;

public class PerspectiveView extends JPanel implements Observer{
	
	private static final long serialVersionUID = -8828879571641152800L;
	private PerspectiveModel model;
	
	public PerspectiveView() {
		super();
	}
	
	@Override
	public void update(Observable obv, Object arg) {
		if(obv != null && obv instanceof PerspectiveModel){
			this.model = (PerspectiveModel)obv;
		}
		repaint(); 
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(model != null){
        	g.drawImage(model.getImage(), model.getPositionX(), model.getPositionY(), model.getWidth(), model.getHeight(), null); // see javadoc for more info on the parameters           
        }	
    }
}
