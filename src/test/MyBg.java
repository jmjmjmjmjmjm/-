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
		imgAddr.add("����ŷ�̹���/img/1.png");
		imgAddr.add("����ŷ�̹���/img/2.png");
		imgAddr.add("����ŷ�̹���/img/3.png");
		
		// ��Ʈ
		icBg = new ImageIcon(imgAddr.get(0));
		�̹�������������(100, 100);
		
		laBg = new JLabel(icBg);
		laBg.setSize(50,50);
		
		add(laBg);
	}
	
	public void �̹�������(int num) {
		icBg = new ImageIcon(imgAddr.get(num));
		laBg.setIcon(icBg);
	}
	
	public void �̹�������������(int width, int height) {
		Image img = icBg.getImage();
		Image changeImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icBg = new ImageIcon(changeImg);
	}
}
