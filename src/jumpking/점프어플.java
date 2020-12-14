package jumpking;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class 점프어플 extends JFrame {
	public static Container c;
	private 점프어플 jumpApp = this;
	public Bg bg;
	public Player player; // 플레이어

	private static final String TAG = "점프앱태그";

	private String bitMap; // 비트맵
	private Thread thPixel; // 픽셀검사

//	private 투명도 panel = new 투명도();

	public 점프어플() {

		setting(); // JFrame 기본세팅
		init(); // new 오브젝트를
		batch(); // 화면 구성
		listener(); // 리스너(이벤트)
		setVisible(true);
	}

	public void setting() { // 맵,크기,등 세팅
		c = getContentPane();
//		c.setLayout(null);
		bg = new Bg();
		setTitle("Jump King");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setContentPane(panel); // 투명도
		setExtendedState(JFrame.MAXIMIZED_BOTH); // 전체화면 모드
		setContentPane(bg.bgla);
	}

	public void init() { // 오브젝트들
		player = new Player(bg);
	}

	public void batch() { // 화면구성
		add(player);
		
	}

	public void listener() { // 리스너
		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//					player.isRight = true;
					player.moveRight();
					player.isLeft = false;
					player.isUp = false;
					
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

					player.moveLeft();
					player.isRight = false;
					player.isUp = false;
					
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUp();
					player.isUp = true;
					player.isRight = false;
					player.isLeft = false;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.isRight = false;
					player.isLeft = false;
					player.isUp = false;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false;
					player.isRight = false;
					player.isUp = false;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUp = false;
					player.isRight = false;
					player.isLeft = false;
					player.isUp2 = true;
					player.isUp2();
					player.isUp2=false;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.getKeyCode() == KeyEvent.VK_UP) {
					// 대각점프

				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT && e.getKeyCode() == KeyEvent.VK_UP) {

				}

			}

		});
	}

	public static void main(String[] args) {
		new 점프어플();
	}

}
