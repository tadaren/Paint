package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LayerManager {
//	private ArrayList<Layer> layerList = new ArrayList<>();
	private DefaultListModel<Layer> layerList = new DefaultListModel<>();
	private JList<Layer> layerJList = new JList<>(layerList);
	private int currentLayerIndex = -1;
	private int width;
	private int height;
	private JFrame frame;

	public LayerManager(){
		createSettingWindow();
		JMenu layerMenu = new JMenu("Layer");
		JMenuItem visibleLayerList = new JMenuItem("LayerWindow");
		visibleLayerList.addActionListener(e -> frame.setVisible(true));
		layerMenu.add(visibleLayerList);
	}
	
	private void createSettingWindow(){
		frame = new JFrame("Layer");
		frame.setBounds(700, 0, 330, 500);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.add(panel);
		JScrollPane sp = new JScrollPane();
		panel.add(sp);

//		JList<Layer> layerJList = new JList<>(layerList);
		sp.getViewport().add(layerJList);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		panel.add(buttonPanel);

		JButton up = new JButton("Up");
		buttonPanel.add(up);
		JButton down = new JButton("Down");
		buttonPanel.add(down);
		JButton delete = new JButton("Delete");
		buttonPanel.add(delete);
		JButton add = new JButton("Add");
		buttonPanel.add(add);

		frame.setVisible(true);
	}

	public void addLayer(int width, int height, Color defaultColor){
		if(layerList.getSize() == 0){
			this.width = width;
			this.height = height;
		}
		currentLayerIndex++;
		layerList.addElement(new Layer(width, height, defaultColor));
		frame.repaint();
	}
	public void addImage(BufferedImage image){
		if(layerList.getSize() == 0){
			this.width = image.getWidth();
			this.height = image.getHeight();
		}
		layerList.addElement(new Layer(image, new Color(0,0,0,0)));
		currentLayerIndex++;
		frame.repaint();
	}
	public void removeLayer(int index){
		layerList.remove(index);
	}
	public Layer getCurrentLayer(){
		try{
//			return layerList.get(currentLayerIndex);
			return layerJList.getSelectedValue();
		}catch(Exception e){
			return null;
		}
	}
	public void clearLayer(){
		layerList.clear();
		currentLayerIndex = 0;
	}
	public BufferedImage getImage(){
		try{
			BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g = buf.createGraphics();
			for(Object layer: layerList.toArray()){
				BufferedImage lbuf = ((Layer)layer).getImage();
				g.drawImage(lbuf, 0, 0, null);
			}
			return buf;
		}catch(IllegalArgumentException e){
			return null;
		}
	}

}
