package jumpking;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class 점프어플 extends JFrame {

	private 점프어플 jumpApp = this;
	
	private static final String TAG = "점프앱태그";

	private 맵 panel =new 맵();

	public 점프어플() {
		init(); // new 오브젝트를
		setting(); // JFrame 기본세팅
//		batch(); // 화면 구성
//		listener(); // 리스너(이벤트)
	}

	public void setting() { // 맵,크기,등 세팅
		setTitle("Jump King");
		setSize(1800, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setLayout(null);
		setVisible(true);
	}

	public void init() { // 오브젝트들
		

	}

	public void batch() { // 화면구성

	}

	public void listener() { // 리스너 (부딫히는거)

	}

	public static void main(String[] args) {
		new 점프어플();
	}

}
