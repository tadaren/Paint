package main;

import pen.Pen;

import javax.swing.*;

public class PenRadioButton extends JRadioButtonMenuItem{
	private Pen pen;

	public PenRadioButton(Pen pen){
		super(pen.toString());
		this.pen = pen;
	}

	public Pen getPen(){
		return pen;
	}
}
