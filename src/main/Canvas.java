package main;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel{
	private BufferedImage overLayerImage;	// 描画イメージの上に描画するイメージを保持する
	private LayerManager layer = new LayerManager();

	public Canvas(){
		this.setBackground(new Color(111,111,111));
		overLayerImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_4BYTE_ABGR);
	}

	// 描画領域を初期化する
	public void clearCanvas(){
		layer.getCurrentLayer().clear();
	}

	public void setListener(MouseInputListener listener){
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}

	public BufferedImage getImage(){
		return layer.getCurrentLayer().getImage();
	}
	public Layer getLayer(){
		return layer.getCurrentLayer();
	}
	public BufferedImage getOverLayerImage(){
		return overLayerImage;
	}
	public BufferedImage getPerfectImage(){
		return layer.getImage();
	}
	public int getLayerCount(){
		return layer.getLayerCount();
	}

	public void generate(int width, int height){
		layer.addLayer(width, height, Color.WHITE);
	}
	public void setImage(BufferedImage image){
		layer.addImage(image);
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		BufferedImage buf = layer.getImage();
		if(buf == null){
			return;
		}
		g.drawImage(buf, 0, 0, buf.getWidth(), buf.getHeight(), this);
		g.drawImage(overLayerImage, 0, 0 , overLayerImage.getWidth(), overLayerImage.getHeight(), this);
	}

}
