package com.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

	public static Image getScaledImage(File file, int width, int height, boolean convert) {
		if (file != null) {
			BufferedImage bufImage = readImage(file);
			if (convert)
				return getScaledImage(bufImage, width, height);
			else
				return bufImage;
		}
		return null;
	}

	public static BufferedImage readImage(File selectedFile) {
		BufferedImage bufImage = null;
		if(selectedFile != null){
			try {
				bufImage = ImageIO.read(selectedFile);
			} catch (IOException e) {
				System.out.println("Error reading the input file");
				System.err.println(e.getMessage());
			}
		}
		return bufImage;
	}
	
	public static BufferedImage getScaledImage(BufferedImage bufImage, int width, int height){
		if(bufImage != null){
			BufferedImage tmp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(bufImage, 0, 0, width, height, null);
            g2.dispose();
            return tmp;
		}
		return null;
	}
	
	public static ImageIcon getIcon(BufferedImage image){
		return new ImageIcon(image);
	}
	
	public static BufferedImage getImage(ImageIcon icon){
		Image img = icon.getImage();
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}
