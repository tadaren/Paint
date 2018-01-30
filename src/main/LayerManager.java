package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// 複数のLayerを管理するクラス
public class LayerManager {
	private DefaultListModel<Layer> layerList = new DefaultListModel<>();	// Layerリスト
	private JList<Layer> layerJList = new JList<>(layerList);				// LayerリストによるJList
	private int layerCount = 0;		// 生成されたLayerの数
	private int width;				// Layerのサイズ
	private int height;
	private JFrame frame;			// Layer操作用のWindow

	public LayerManager(){
		createSettingWindow();
		JMenu layerMenu = new JMenu("Layer");
		JMenuItem visibleLayerList = new JMenuItem("LayerWindow");
		visibleLayerList.addActionListener(e -> frame.setVisible(true));
		layerMenu.add(visibleLayerList);
	}

	// Windowを生成
	private void createSettingWindow(){
		frame = new JFrame("Layer");
		frame.setBounds(1000, 0, 330, 500);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.add(panel);
		JScrollPane sp = new JScrollPane();
		panel.add(sp);

		sp.getViewport().add(layerJList);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		panel.add(buttonPanel);

		// Layer操作ボタン
		JButton up = new JButton("Up");	// 現在のLayerを上に移動させる
		up.addActionListener(e -> {
			if(layerList.getSize() <= 1)
				return;
			try{
				Layer buf = layerJList.getSelectedValue();
				int i = layerJList.getSelectedIndex();
				layerList.set(i, layerList.get(i+1));
				layerList.set(i+1, buf);
			}catch(ArrayIndexOutOfBoundsException e1){
//				e1.printStackTrace();
			}
		});
		buttonPanel.add(up);
		JButton down = new JButton("Down");	// 現在のLayerを下に移動させる
		down.addActionListener(e -> {
			if(layerList.getSize() <= 1)
				return;
			try{
				Layer buf = layerJList.getSelectedValue();
				int i = layerJList.getSelectedIndex();
				layerList.set(i, layerList.get(i-1));
				layerList.set(i-1, buf);
			}catch(ArrayIndexOutOfBoundsException e1){
//				e1.printStackTrace();
			}
		});
		buttonPanel.add(down);
		JButton delete = new JButton("Delete");	// 現在のLayerを削除
		delete.addActionListener(e -> {
			removeLayer(layerJList.getSelectedIndex());
		});
		buttonPanel.add(delete);
		JButton add = new JButton("Add");		// 新たにLayerを作成
		add.addActionListener(e -> {
			addLayer(width, height, new Color(0, 0, 0, 0));
		});
		buttonPanel.add(add);

		frame.setVisible(true);
	}

	// Layerを追加
	public void addLayer(int width, int height, Color defaultColor){
		if(layerList.getSize() == 0){
			this.width = width;
			this.height = height;
		}
		Layer newLayer = new Layer(width, height, defaultColor, String.valueOf(layerCount++));
		layerList.addElement(newLayer);
		layerJList.setSelectedValue(newLayer, true);
		frame.repaint();
	}
	// 引数の画像によるLayerを追加(最初のLayerのみ)
	public void addImage(BufferedImage image){
		if(layerList.getSize() != 0){
			return;
		}
		if(layerList.getSize() == 0){
			this.width = image.getWidth();
			this.height = image.getHeight();
		}
		Layer newLayer = new Layer(image, new Color(0,0,0,0), String.valueOf(layerCount++));
		layerList.addElement(newLayer);
		layerJList.setSelectedValue(newLayer, true);
		frame.repaint();
	}
	// 指定インデックスのLayerを削除
	public void removeLayer(int index){
		layerList.remove(index);
		MainManager.getInstance().repaint();
	}
	// 現在選択されているLayerを返す
	public Layer getCurrentLayer(){
		try{
			return layerJList.getSelectedValue();
		}catch(Exception e){
			return null;
		}
	}
	// 現在あるLayerの数を返す
	public int getLayerCount(){
		return layerCount;
	}
	// 全てのLayerを重ねたBufferedImageを返す
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
