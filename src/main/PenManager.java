package main;

import pen.DefaultPen;
import pen.Eraser;
import pen.LinePen;
import pen.LinePenNew;
import pen.Pen;
import pen.RainbowPen;
import pen.StringPen;
import pen.TrianglePen;
import pen.TrianglePenNew;

import javax.swing.*;
import java.awt.*;

// 描画ペンについて管理するクラス
public class PenManager {

	private static PenManager instance;
	private Pen pen;		// 現在選択されているペン
	private Color color;	// ペンによって描画される色
	private Stroke stroke;	// ペンの太さとか
	private Stroke tempStroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{5.0f}, 0);

	// 使えるペン
	private Pen[] penList = {
			new DefaultPen(),
			new LinePen(),
			new LinePenNew(),
			new TrianglePen(),
			new TrianglePenNew(),
			new RainbowPen(),
			new StringPen(),
			new Eraser()
	};

	private PenManager(){
		this.pen = penList[0];
		this.stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		this.color = Color.BLACK;

		// 色選択メニュー
		JMenu colorMenu = new JMenu("Color");
		JColorChooser colorChooser = new JColorChooser();
		colorChooser.setColor(Color.BLACK);
		colorChooser.getSelectionModel().addChangeListener(e -> setColor(colorChooser.getColor()));
		colorMenu.add(colorChooser);
		MainManager.getInstance().getOptionMenuBar().add(colorMenu);

		// ペンサイズ設定メニュー
		JMenu penSizeMenu = new JMenu("PenSize");
		JSpinner penSize = new JSpinner(new SpinnerNumberModel(5, 1, 100, 1));
		penSize.setPreferredSize(new Dimension(50, 30));
		penSize.addChangeListener(e -> setStroke(new BasicStroke((Integer) ((JSpinner) e.getSource()).getValue(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
		penSizeMenu.add(penSize);
		MainManager.getInstance().getOptionMenuBar().add(penSizeMenu);


		// ペン選択
		JComboBox<Pen> penList = new JComboBox<>(getPenList());
		penList.addActionListener(e -> {
			Pen p = (Pen)((JComboBox<?>)e.getSource()).getSelectedItem();
			setPen(p);
		});
		MainManager.getInstance().getOptionPanel().add(penList);

		// MenuBarでのペン選択
		JMenu penMenu = new JMenu("Pen");
		ButtonGroup g = new ButtonGroup();
		for(Pen pen: getPenList()){
			PenRadioButton p = new PenRadioButton(pen);
			g.add(p);
			penMenu.add(p);
			// Penのボタンを押されたらPenを変更する
			p.addActionListener(e -> {
				setPen(p.getPen());
				penList.setSelectedItem(p.getPen());
			});
			if(pen == getPenList()[0]){
				p.setSelected(true);
			}
		}
		MainManager.getInstance().getOptionMenuBar().add(penMenu);
	}

	public Pen getPen(){
		return pen;
	}
	public Stroke getStroke(){
		return stroke;
	}
	public Color getColor(){
		return color;
	}
	public Pen[] getPenList(){
		return penList;
	}
	public Stroke getTempStroke(){
		return tempStroke;
	}

	public void setPen(Pen pen){
		this.pen = pen;
		this.pen.init();
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void setStroke(Stroke stroke){
		this.stroke = stroke;
	}

	public void setColor(Graphics2D g){
		g.setColor(color);
	}

	public static PenManager getInstance(){
		if(instance == null){
			instance = new PenManager();
		}
		return instance;
	}
}
