package jumpking;

import java.awt.Label;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bg extends JPanel {
	Vector<String> v;
	JLabel bgla;
	public ImageIcon img;

	private int i = 1;

	int y = 3030;
	int yy = 1824;

	public Bg() { // �⺻�� �ʼ���
		v = new Vector<>();
		v.add("����ŷ�̹���/��/map1.jpg");
		v.add("����ŷ�̹���/��/map2.jpg");
		v.add("����ŷ�̹���/��/map3.jpg");

		img = new ImageIcon(v.get(i));
		bgla = new JLabel(img);
		add(bgla);
	}

	public void bgchange() { // ���� �����ϱ� ���� �Լ�
		y = y - (y / 3);
		yy = yy - (yy / 3);
	}

}