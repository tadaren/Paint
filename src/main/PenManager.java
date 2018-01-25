package main;

import pen.DefaultPen;
import pen.Eraser;
import pen.LinePen;
import pen.Pen;
import pen.RainbowPen;
import pen.StringPen;
import pen.TrianglePen;

import javax.swing.*;
import java.awt.*;

public class PenManager {

	private static PenManager instance;
	private Pen pen;
	private Color color;
	private Stroke stroke;

	private Pen[] penList = {
			new DefaultPen(),
			new LinePen(),
			new TrianglePen(),
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
		penSize.addChangeListener(e -> {
			setStroke(new BasicStroke(((Integer)((JSpinner)e.getSource()).getValue()).intValue(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		});
		penSizeMenu.add(penSize);
		MainManager.getInstance().getOptionMenuBar().add(penSizeMenu);


		// ペン選択
		JComboBox<Pen> penList = new JComboBox<>(getPenList());
		penList.addActionListener(e -> {
			Pen p = (Pen)((JComboBox<?>)e.getSource()).getSelectedItem();
			setPen(p);
		});
//		JMenu penListMenu = new JMenu("Pen");
//		penListMenu.add(penList);
//		penListMenu.add(new JPanel());
//		MainManager.getInstance().getOptionMenuBar().add(penListMenu);
		MainManager.getInstance().getOptionPanel().add(penList);
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
