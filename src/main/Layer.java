package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Layer {
	private BufferedImage image;
	private Color defaultColor;		// 初期設定の色
	private String name;			// Layerの名前

	public Layer(BufferedImage image, Color defaultColor, String name){
		this.image = image;
		this.defaultColor = defaultColor;
		this.name = name;
	}
	public Layer(int width, int height, Color defaultColor, String name){
		this(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR), defaultColor, name);
		Graphics2D g = image.createGraphics();
		g.setColor(defaultColor);
		g.fillRect(0, 0, width, height);
	}

	// Layerを初期設定の色で塗りつぶす
	public void clear(){
		Graphics2D g = image.createGraphics();
		g.setColor(defaultColor);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		if(defaultColor.getRGB() == 0){
			g.setBackground(new Color(0, 0, 0, 0));
			g.clearRect(0, 0, image.getWidth(), image.getHeight());
		}
	}

	// Layerが保持しているBufferedImageを返す
	public BufferedImage getImage(){
		return image;
	}
	// Layerが保持しているBufferedImageのGraphics2Dクラスを返す
	public Graphics2D getGraphics2D(){
		return getImage().createGraphics();
	}
	public Color getDefaultColor(){
		return defaultColor;
	}

	@Override
	public String toString(){
		return "Layer"+name;
	}
}
