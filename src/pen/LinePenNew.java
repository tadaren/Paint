package pen;

import main.Layer;
import main.PenManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LinePenNew extends LinePen{
	@Override
	public void init(){

	}

	@Override
	public void mouseClicked(MouseEvent e, Layer layer, Graphics2D g1){}

	@Override
	public void mousePressed(MouseEvent e, Layer layer, Graphics2D g1){
		pointBuffer = e.getPoint();
	}

	@Override
	public void mouseDragged(MouseEvent e, Layer layer, Graphics2D g1){
		g1.setBackground(new Color(0,0,0,0));
		g1.clearRect(0,0,1000, 1000);
		g1.setColor(Color.BLACK);
		g1.setStroke(PenManager.getInstance().getTempStroke());
		g1.drawLine(pointBuffer.x, pointBuffer.y, e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e, Layer layer, Graphics2D g1){
		g1.setBackground(new Color(0,0,0,0));
		g1.clearRect(0,0,1000, 1000);
		Graphics2D g = layer.getGraphics2D();
		g.setStroke(PenManager.getInstance().getStroke());
		g.setColor(PenManager.getInstance().getColor());
		g.drawLine(pointBuffer.x, pointBuffer.y, e.getX(), e.getY());
	}

	@Override
	public String toString(){
		return "LinePenNew";
	}
}
