package pen;

import main.Layer;
import main.PenManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DefaultPen extends Pen {

	private Point pointBuffer;

	public Point getPointBuffer(){
		return pointBuffer;
	}

	public void setPointBuffer(Point pointBuffer){
		this.pointBuffer = pointBuffer;
	}

	@Override
	public void init(){
		pointBuffer = null;
	}

	@Override
	public void mousePressed(MouseEvent e, Layer layer, Graphics2D g1){
		pointBuffer = e.getPoint();
	}

	@Override
	public void mouseDragged(MouseEvent e, Layer layer, Graphics2D g1){
		Graphics2D g = layer.getGraphics2D();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(PenManager.getInstance().getColor());
		g.setStroke(PenManager.getInstance().getStroke());
		g.drawLine(pointBuffer.x, pointBuffer.y, e.getX(), e.getY());
		pointBuffer = e.getPoint();
	}

	@Override
	public String toString(){
		return "ペン";
	}
}
