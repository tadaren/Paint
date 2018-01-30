package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

// 設定項目とかを管理するクラス
public class MainManager implements MouseInputListener{

	private static MainManager instance;

	private JPanel optionPanel;			// 右端の設定用の色々を表示するパネル
	private JMenuBar optionMenuBar;		// MenuBarのインスタンス
	private Canvas canvas;				// ペイント領域のパネル
	private JFileChooser fileChooser = new JFileChooser();	// 保存とか読み込み用のFileChooser
	private PenManager penManager;		// ペン管理用

	// 開いているファイル
	private File file;
	private String fileType;

	private MainManager(){
		canvas = new Canvas();
		canvas.setListener(this);
		fileChooser.setAcceptAllFileFilterUsed(false);
		initMenuBar();

		optionPanel = new JPanel();
	}
	private void initMenuBar(){
		optionMenuBar = new JMenuBar();
		// ファイル関係のMenu
		JMenu fileMenu = new JMenu("File");
		optionMenuBar.add(fileMenu);

		JMenuItem newMenu = new JMenuItem("New");		// 新規作成のメニュー
		newMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		newMenu.addActionListener(e -> {
			if(canvas.getLayerCount() != 0){
				return;
			}
			canvas.generate(500, 500);
			PenManager.getInstance().getPen().init();
		});
		fileMenu.add(newMenu);

		JMenuItem open = new JMenuItem("Open");	// 読み込みのメニュー
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		open.addActionListener(e -> {
			// 読み込むファイルのフィルターを設定
			FileNameExtensionFilter filter = new FileNameExtensionFilter("画像ファイル(.png, .jpeg, .jpg)", "jpeg", "jpg", "png");
			fileChooser.resetChoosableFileFilters();
			fileChooser.addChoosableFileFilter(filter);
			// ダイアログを開いてファイルが選択されたら
			if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				File selectedFile = fileChooser.getSelectedFile();
				try{
					// キャンバスに読み込んだ画像をセットする
					canvas.setImage(ImageIO.read(selectedFile));
					canvas.repaint();
					file = selectedFile;
					// ファイルの拡張子を切り取って格納
					fileType = file.getPath().substring(file.getPath().lastIndexOf(".")+1);
				}catch(IOException e1){
//					e1.printStackTrace();
				}
			}
		});
		fileMenu.add(open);

		// 名前をつけて保存のメニュー
		JMenu saveAs = new JMenu("Save As");
		JMenuItem pngSave = new JMenuItem("png");		// 保存形式png
		JMenuItem jpegSave = new JMenuItem("jpeg");	// 保存形式jpeg
		saveAs.add(pngSave);
		saveAs.add(jpegSave);
		pngSave.addActionListener(e -> {
			try{
				// ファイルの形式フィルターを設定
				FileNameExtensionFilter filter = new FileNameExtensionFilter("pngファイル(.png)", "png");
				fileChooser.resetChoosableFileFilters();
				fileChooser.addChoosableFileFilter(filter);
				// ダイアログを開いてファイルが選択されたら
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					File selectedFile = fileChooser.getSelectedFile();
					// 拡張子がなければ追加
					if(selectedFile.getPath().endsWith(".png")){
						ImageIO.write(getCanvas().getPerfectImage(), "png", selectedFile);
					}else{
						selectedFile = new File(selectedFile.getPath()+".png");
						ImageIO.write(getCanvas().getPerfectImage(), "png", selectedFile);
					}
					file = selectedFile;
					fileType = "png";
				}
			}catch(IOException e1){
//				e1.printStackTrace();
			}
		});
		jpegSave.addActionListener(e -> {
			try{
				// ファイル形式のフィルターを設定
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpegファイル(.jpeg, .jpg)", "jpeg", "jpg");
				fileChooser.resetChoosableFileFilters();
				fileChooser.addChoosableFileFilter(filter);
				// ダイアログを開いてファイルが選択されたら
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					File selectedFile = fileChooser.getSelectedFile();
					// 拡張子がなければ追加
					if(selectedFile.getPath().endsWith(".jpeg") || selectedFile.getPath().endsWith(".jpg")){
						ImageIO.write(getCanvas().getPerfectImage(), "jpeg", selectedFile);
					}else{
						selectedFile = new File(selectedFile.getPath()+".jpeg");
						ImageIO.write(getCanvas().getPerfectImage(), "jpeg", selectedFile);
					}
					file = selectedFile;
					fileType = "jpeg";
				}
			}catch(IOException | NullPointerException e1){
//				e1.printStackTrace();
			}
		});
		fileMenu.add(saveAs);

		// 上書き保存のメニュー
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		save.addActionListener(e -> {
			if(file == null || fileType == null)
				return;
			try{
				ImageIO.write(getCanvas().getPerfectImage(), fileType, file);
			}catch(IOException e1){
//				e1.printStackTrace();
			}
		});
		fileMenu.add(save);

		// ペイント領域の初期化メニュー
		JMenuItem clearMenu = new JMenuItem("Clear");
		clearMenu.addActionListener(e -> {
			canvas.clearCanvas();
			canvas.repaint();
		});
		fileMenu.add(clearMenu);
	}

	public JMenuBar getOptionMenuBar(){
		return optionMenuBar;
	}
	public JPanel getOptionPanel(){
		return optionPanel;
	}
	public Canvas getCanvas(){
		return canvas;
	}

	public void repaint(){
		canvas.repaint();
	}

	// マウスによるイベントを受けたらペンに描画するためのGraphics2Dクラスとイベントを送信する
	@Override
	public void mouseClicked(MouseEvent e){
		try {
			penManager.getPen().mouseClicked(e, canvas.getLayer(), canvas.getOverLayerImage().createGraphics());
		} catch (NullPointerException e1) {
//			e1.printStackTrace();
		}
		canvas.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e){
		try {
			penManager.getPen().mousePressed(e, canvas.getLayer(), canvas.getOverLayerImage().createGraphics());
		} catch (Exception e1) {
//			e1.printStackTrace();
		}
		canvas.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e){
		try {
			penManager.getPen().mouseReleased(e, canvas.getLayer(), canvas.getOverLayerImage().createGraphics());
		} catch (Exception e1) {
//			e1.printStackTrace();
		}
		canvas.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e){
		try {
			penManager.getPen().mouseEntered(e, canvas.getLayer(), canvas.getOverLayerImage().createGraphics());
		} catch (Exception e1) {
//			e1.printStackTrace();
		}
		canvas.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e){
		try {
			penManager.getPen().mouseExited(e, canvas.getLayer(), canvas.getOverLayerImage().createGraphics());
		} catch (Exception e1) {
//			e1.printStackTrace();
		}
		canvas.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e){
		try {
			penManager.getPen().mouseDragged(e, canvas.getLayer(), canvas.getOverLayerImage().createGraphics());
		} catch (Exception e1) {
//			e1.printStackTrace();
		}
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e){
		try {
			penManager.getPen().mouseMoved(e, canvas.getLayer(), canvas.getOverLayerImage().createGraphics());
		} catch (Exception e1) {
//			e1.printStackTrace();
		}
		canvas.repaint();
	}

	public static MainManager getInstance(){
		if(instance == null){
			instance = new MainManager();
		}
		return instance;
	}

	public void setPenManager(PenManager penManager){
		this.penManager = penManager;
	}
}
