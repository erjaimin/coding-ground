package com.mvc.view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.main.ImageUtil;
import com.mvc.model.ImageModel;

public class ImageView extends JPanel implements Observer{

	private static final long serialVersionUID = 7039659890399712325L;
	
	private ImageModel model;
	
	public ImageView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	@Override
	public void update(Observable obv, Object obj) {
		if(obv != null && obv instanceof ImageModel){
			this.model = (ImageModel)obv;
			
		}
		repaint();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(model != null){
        	Image scaledImage = ImageUtil.getScaledImage(model.getInputFile(), this.getWidth(), this.getHeight()/4, true);
        	g.drawImage(scaledImage, 0, this.getHeight()/3, this); // see javadoc for more info on the parameters            
        }
    }

}
