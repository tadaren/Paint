package pen;

import main.PenManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Eraser extends DefaultPen {

	@Override
	public void mouseDragged(MouseEvent e, Graphics2D g){
		g.setColor(Color.WHITE);
		g.setStroke(PenManager.getInstance().getStroke());
		g.drawLine(e.getX(), e.getY(), getPointBuffer().x, getPointBuffer().y);
		setPointBuffer(e.getPoint());
	}

	@Override
	public String toString(){
		return "消しゴム";
	}
}
