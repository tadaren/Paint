package pen;

import main.PenManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RainbowPen extends DefaultPen {

	private float[] colorBuffer = {0, 1, 1};

	@Override
	public void init(){
		colorBuffer = new float[]{0, 1, 1};
	}

	@Override
	public void mouseDragged(MouseEvent e, Graphics2D g){
		colorBuffer[0] += 0.01;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.getHSBColor(colorBuffer[0], colorBuffer[1], colorBuffer[2]));
		g.setStroke(PenManager.getInstance().getStroke());
		g.drawLine(getPointBuffer().x, getPointBuffer().y, e.getX(), e.getY());
		setPointBuffer(e.getPoint());
	}

	@Override
	public String toString(){
		return "虹色";
	}
}
