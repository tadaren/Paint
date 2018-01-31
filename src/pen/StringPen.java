package pen;

import main.Layer;
import main.MainManager;
import main.PenManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseEvent;

public class StringPen extends Pen {

	private int fontSize = 30;
	private String fontName = "ヒラギノ角ゴ StdN";
	private String drawString;

	@Override
	public void mouseClicked(MouseEvent e, Layer layer, Graphics2D g1){
		drawString = JOptionPane.showInputDialog("入力する文字列");
		if(drawString == null){
			return;
		}
		Graphics2D g = layer.getGraphics2D();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(PenManager.getInstance().getColor());
		g.setFont(new Font(fontName, Font.PLAIN, fontSize));
		g.drawString(drawString, e.getX(), e.getY());
	}

	@Override
	public void init(){}

	@Override
	public String toString(){
		return "文字列";
	}
}
