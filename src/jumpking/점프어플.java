package jumpking;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class 점프어플 extends JFrame {
	public static Container c;
	private 점프어플 jumpApp = this;
	Bg bg;
	public Player player; // 플레이어

	private static final String TAG = "점프앱태그";

	private String bitMap; // 비트맵
	private Thread thPixel; // 픽셀검사

//	private 투명도 panel = new 투명도();

	public 점프어플() {

		setting(); // JFrame 기본세팅
		init(); // new 오브젝트를
//		batch(); // 화면 구성
//		listener(); // 리스너(이벤트)
		setVisible(true);
	}

	public void setting() { // 맵,크기,등 세팅
		c = getContentPane();
		bg = new Bg();

		setTitle("Jump King");
		setSize(1620, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setContentPane(panel); // 투명도

		c.add(bg);

		setExtendedState(JFrame.MAXIMIZED_BOTH); // 전체화면 모드
	

	}

	public void init() { // 오브젝트들
		player = new Player();

	}

	public void batch() { // 화면구성
		add(player);
	}

	public void listener() { // 리스너
		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.moveRight();
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.moveLeft();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.moveUp();

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.isRight = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUp = false;
					player.isJump = true;
					player.moveUp2();

				}

			}

		});
	}

	public static void main(String[] args) {
		new 점프어플();
	}

}
