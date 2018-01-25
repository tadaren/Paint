package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LayerManager {
	private ArrayList<Layer> layerList = new ArrayList<>();
	private int currentLayerIndex = -1;
	private int width;
	private int height;

	public LayerManager(BufferedImage image){
		addImage(image);
	}
	public LayerManager(int width, int height){
		this.width = width;
		this.height = height;
		addLayer(Color.WHITE);
	}
	public void addLayer(Color defaultColor){
		layerList.add(new Layer(width, height, defaultColor));
		currentLayerIndex++;
	}
	public void addImage(BufferedImage image){
		layerList.add(new Layer(image, new Color(0,0,0,0)));
		currentLayerIndex++;
	}
	public void removeLayer(int index){
		layerList.remove(index);
	}
	public Layer getCurrentLayer(){
		return layerList.get(currentLayerIndex);
	}
	public void clearLayer(){
		layerList.clear();
		currentLayerIndex = 0;
	}
	public BufferedImage getImage(){
		BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = buf.createGraphics();
		for(Layer layer: layerList){
			BufferedImage lbuf = layer.getImage();
			g.drawImage(lbuf, 0, 0, null);
		}
		return buf;
	}

}
