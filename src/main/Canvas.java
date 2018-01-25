package main;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel{
//	private BufferedImage image;	// 描画イメージを保持する
	private LayerManager layer;

	public Canvas(){
		this.setBackground(new Color(111,111,111));
	}

	// 描画領域を初期化する
	public void clearCanvas(){
//		image.getGraphics().fillRect(0, 0, image.getWidth(), image.getHeight());
		layer.getCurrentLayer().clear();
	}

	public void setListener(MouseInputListener listener){
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}

	public BufferedImage getImage(){
//		return image;
		return layer.getCurrentLayer().getImage();
	}
	public void generate(int width, int height){
		layer = new LayerManager(width, height);
		layer.addLayer(Color.WHITE);
	}
	public void setImage(BufferedImage image){
		layer = new LayerManager(image);
	}

//	public void setImage(BufferedImage image){
//		this.image = image;
//		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
//	}
//	public void generateImage(int width, int height){
//		image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
//		clearCanvas();
//	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(layer == null){
			return;
		}
		BufferedImage buf = layer.getImage();
		g.drawImage(buf, 0, 0, buf.getWidth(), buf.getHeight(), this);
	}

}
