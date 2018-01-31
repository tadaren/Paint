package main;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args){
		SwingUtilities.invokeLater(() -> {
			// ウィンドウの設定
			JFrame frame = new JFrame("Paint");
			frame.setBounds(100,100, 800, 800);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			// 管理用オブジェクトを生成
			MainManager manager = MainManager.getInstance();
			frame.setJMenuBar(manager.getOptionMenuBar());
			frame.add(manager.getOptionPanel(), BorderLayout.EAST);
			manager.setPenManager(PenManager.getInstance());

			// キャンバス描画エリアを設定
			manager.setCanvas(new Canvas());
			JScrollPane canvasArea = new JScrollPane(manager.getCanvas(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			frame.add(canvasArea, BorderLayout.CENTER);
			frame.setVisible(true);
		});
	}
}
