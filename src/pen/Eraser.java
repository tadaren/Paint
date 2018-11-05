package pen;

import main.Layer;
import main.PenManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Eraser extends DefaultPen {

	@Override
	public void mouseDragged(MouseEvent e, Layer layer, Graphics2D g1){
		Graphics2D g = layer.getGraphics2D();
		g.setColor(layer.getDefaultColor());
		g.setStroke(PenManager.getInstance().getStroke());
		g.drawLine(e.getX(), e.getY(), getPointBuffer().x, getPointBuffer().y);
		if(layer.getDefaultColor().getRGB() == 0){
			g.setBackground(new Color(0, 0, 0, 0));
			g.clearRect(e.getX()-2, e.getY()-2, Math.abs(getPointBuffer().x-e.getX()+5), Math.abs(getPointBuffer().y-e.getY()+5));
		}
		setPointBuffer(e.getPoint());
	}

	@Override
	public String toString(){
		return "消しゴム";
	}
}
