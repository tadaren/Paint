package pen;

import main.Layer;

import java.awt.*;
import java.awt.event.MouseEvent;

abstract public class Pen {
	// マウスイベントが呼ばれると呼ばれる
	public void mouseClicked(MouseEvent e, Layer layer, Graphics2D g1){}
	public void mousePressed(MouseEvent e, Layer layer, Graphics2D g1){}
	public void mouseReleased(MouseEvent e, Layer layer, Graphics2D g1){}
	public void mouseMoved(MouseEvent e, Layer layer, Graphics2D g1){}
	public void mouseDragged(MouseEvent e, Layer layer, Graphics2D g1){}
	public void mouseEntered(MouseEvent e, Layer layer, Graphics2D g1){}
	public void mouseExited(MouseEvent e, Layer layer, Graphics2D g1){}

	// ペンが切り替わった時に初期化が必要な処理を記述
	abstract public void init();

	// ペン選択ボックスに表示される文字列
	@Override
	abstract public String toString();
}
