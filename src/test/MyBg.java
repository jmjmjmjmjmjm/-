package test;

import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyBg extends JPanel{
	
	public JLabel laBg;
	public ImageIcon icBg;
	public Vector<String> imgAddr;
	
	public MyBg() {
		imgAddr = new Vector<>();
		imgAddr.add("점프킹이미지/img/1.png");
		imgAddr.add("점프킹이미지/img/2.png");
		imgAddr.add("점프킹이미지/img/3.png");
		
		// 세트
		icBg = new ImageIcon(imgAddr.get(0));
		이미지사이즈조정(100, 100);
		
		laBg = new JLabel(icBg);
		laBg.setSize(50,50);
		
		add(laBg);
	}
	
	public void 이미지변경(int num) {
		icBg = new ImageIcon(imgAddr.get(num));
		laBg.setIcon(icBg);
	}
	
	public void 이미지사이즈조정(int width, int height) {
		Image img = icBg.getImage();
		Image changeImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icBg = new ImageIcon(changeImg);
	}
}
