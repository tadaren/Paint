package pen;

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
	private String drawString = "";

	private JPanel option;

	public StringPen(){
		option = new JPanel();
		JTextField textField = new JTextField(10);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e){
				change(e);
			}
			@Override
			public void removeUpdate(DocumentEvent e){
				change(e);
			}
			@Override
			public void changedUpdate(DocumentEvent e){
				change(e);
			}
			private void change(DocumentEvent e){
				Document d = e.getDocument();
				try{
					drawString = d.getText(0, d.getLength());
				}catch(BadLocationException e1){
					e1.printStackTrace();
				}
			}
		});
		option.add(textField);
		JScrollBar fontSizeBar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 2, 5, 50);
		fontSizeBar.addAdjustmentListener(e -> fontSize = e.getValue());
		option.add(fontSizeBar);
	}

	@Override
	public void mouseClicked(MouseEvent e, Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(PenManager.getInstance().getColor());
		g.setFont(new Font(fontName, Font.PLAIN, fontSize));
		g.drawString(drawString, e.getX(), e.getY());
	}

	@Override
	public void init(){
		MainManager.getInstance().getOptionPanel().add(option).repaint();
	}

	@Override
	public String toString(){
		return "文字列";
	}
}
