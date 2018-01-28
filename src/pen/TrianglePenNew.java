package pen;

import main.Layer;
import main.PenManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TrianglePenNew extends TrianglePen{

	@Override
	public void mouseMoved(MouseEvent e, Layer layer, Graphics2D g1){
		g1.setBackground(new Color(0,0,0,0));
		g1.clearRect(0,0,1000,1000);
		g1.setColor(Color.BLACK);
		g1.setStroke(PenManager.getInstance().getTempStroke());
		switch(pointBuffer.size()){
			case 1: {
				g1.drawLine(pointBuffer.get(0).x, pointBuffer.get(0).y, e.getX(), e.getY());
				break;
			}
			case 2: {
				g1.drawLine(pointBuffer.get(0).x, pointBuffer.get(0).y, e.getX(), e.getY());
				g1.drawLine(pointBuffer.get(1).x, pointBuffer.get(1).y, pointBuffer.get(0).x, pointBuffer.get(0).y);
				g1.drawLine(pointBuffer.get(1).x, pointBuffer.get(1).y, e.getX(), e.getY());
				break;
			}
		}
	}

	@Override
	public String toString(){
		return "TrianglePenNew";
	}
}
