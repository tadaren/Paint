package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Layer {
	private BufferedImage image;
	private Color defaultColor;

	public Layer(BufferedImage image, Color defaultColor){
		this.image = image;
		this.defaultColor = defaultColor;
	}
	public Layer(int width, int height, Color defaultColor){
		image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		this.defaultColor = defaultColor;
		Graphics2D g = image.createGraphics();
		g.setColor(defaultColor);
		g.fillRect(0, 0, width, height);
	}

	public void clear(){
		Graphics2D g = image.createGraphics();
		g.setColor(defaultColor);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
	}

	public BufferedImage getImage(){
		return image;
	}

	@Override
	public String toString(){
		return "LayerName";
	}
}
