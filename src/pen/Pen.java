package pen;

import java.awt.*;
import java.awt.event.MouseEvent;

abstract public class Pen {
	// マウスイベントが呼ばれると呼ばれる
	public void mouseClicked(MouseEvent e, Graphics2D g){}
	public void mousePressed(MouseEvent e, Graphics2D g){}
	public void mouseReleased(MouseEvent e, Graphics2D g){}
	public void mouseMoved(MouseEvent e, Graphics2D g){}
	public void mouseDragged(MouseEvent e, Graphics2D g){}
	public void mouseEntered(MouseEvent e, Graphics2D g){}
	public void mouseExited(MouseEvent e, Graphics2D g){}

	// ペンが切り替わった時に初期化が必要な処理を記述
	abstract public void init();

	// ペン選択ボックスに表示される文字列
	@Override
	abstract public String toString();
}
