package main;

import javax.swing.*;

public class Main {
	public static void main(String[] args){
		SwingUtilities.invokeLater(() -> {
			// ウィンドウの設定
			JFrame frame = new JFrame("Paint");
			frame.setBounds(100,100, 800, 800);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			splitPane.setDividerLocation(600);
			frame.add(splitPane);

			// 管理用オブジェクトを生成
			MainManager manager = MainManager.getInstance();
			frame.setJMenuBar(manager.getOptionMenuBar());
			splitPane.setRightComponent(new JScrollPane(manager.getOptionPanel()));
			manager.setPenManager(PenManager.getInstance());

			// キャンバス描画エリアを設定
			JScrollPane canvasArea = new JScrollPane(manager.getCanvas(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			splitPane.setLeftComponent(canvasArea);
			frame.setVisible(true);
		});
	}
}
