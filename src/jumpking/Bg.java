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

	public Bg() { // 기본적 맵세팅
		v = new Vector<>();
		v.add("점프킹이미지/맵/map1.jpg");
		v.add("점프킹이미지/맵/map2.jpg");
		v.add("점프킹이미지/맵/map3.jpg");

		img = new ImageIcon(v.get(i));
		bgla = new JLabel(img);
		add(bgla);
	}

	public void bgchange() { // 맵을 변경하기 위한 함수
		y = y - (y / 3);
		yy = yy - (yy / 3);
	}

}