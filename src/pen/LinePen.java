package pen;

import main.Layer;
import main.PenManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LinePen extends Pen {

	protected Point pointBuffer;

	@Override
	public void init(){
		pointBuffer = null;
	}

	@Override
	public void mouseClicked(MouseEvent e, Layer layer, Graphics2D g1){
		Graphics2D g = layer.getGraphics2D();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(PenManager.getInstance().getStroke());
		g.setColor(PenManager.getInstance().getColor());
		if(pointBuffer == null){
			pointBuffer = e.getPoint();
		}else{
			g.drawLine(e.getX(), e.getY(), pointBuffer.x, pointBuffer.y);
			pointBuffer = null;
		}
	}

	@Override
	public String toString(){
		return "直線";
	}
}
