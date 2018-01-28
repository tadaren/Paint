package pen;

import main.Layer;
import main.PenManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TrianglePen extends Pen {

	protected ArrayList<Point> pointBuffer = new ArrayList<>();

	@Override
	public void init(){
		pointBuffer.clear();
	}

	@Override
	public void mouseClicked(MouseEvent e, Layer layer, Graphics2D g1){
		Graphics2D g = layer.getGraphics2D();
		if(pointBuffer.size() == 2){
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(PenManager.getInstance().getColor());
			g.setStroke(PenManager.getInstance().getStroke());
			g.drawLine(pointBuffer.get(0).x, pointBuffer.get(0).y, e.getX(), e.getY());
			g.drawLine(pointBuffer.get(1).x, pointBuffer.get(1).y, pointBuffer.get(0).x, pointBuffer.get(0).y);
			g.drawLine(pointBuffer.get(1).x, pointBuffer.get(1).y, e.getX(), e.getY());
			init();
		}else{
			pointBuffer.add(e.getPoint());
		}
	}

	@Override
	public String toString(){
		return "三角";
	}
}
