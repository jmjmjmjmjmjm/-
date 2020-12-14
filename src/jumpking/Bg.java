package jumpking;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class Bg extends JPanel {
	static Vector<String> v;
	static JLabel bgla;
	static ImageIcon img;
	static �������� jumapp;
	public Bg() { // �⺻�� �ʼ���
		v = new Vector<>();
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // ��ü �ػ�
		v.add("����ŷ�̹���/��/1.png");
		v.add("����ŷ�̹���/��/2.png");
		v.add("����ŷ�̹���/��/3.png");
		v.add("����ŷ�̹���/��/4.png");
		v.add("����ŷ�̹���/��/5.png");
		img = new ImageIcon(v.get(0));
		�̹�������������(res.width + 500, res.height);
		bgla = new JLabel(img);
		add(bgla);
	}

	static void �̹�������(int num) { // �ʺ���
		img = new ImageIcon(v.get(num));
		bgla.setIcon(img);

	}

	public void �̹�������������(int width, int height) {
		Image img2 = img.getImage();
		Image changeImg = img2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		img = new ImageIcon(changeImg);
	}

}