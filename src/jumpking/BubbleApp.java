package jumpking;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BubbleApp extends JFrame implements Initable {

	// 컨텍스트 저장
	private BubbleApp bubbleApp = this; // 버블 컨텍스트 남기기
	// 태그
	private static final String TAG = "JumpKing : ";

	private String bitMap; // 비트맵
	public Player player; // 플레이어
	private Thread thPixel; // 픽셀검사

	private BgJumpKing bgPanel; // 백그라운드

	public BubbleApp() {
		init(); // 생성 객체모음
		setting(); // 셋팅 모음
		batch(); // 배치 모음
		listener(); // 리스너 모음
		setVisible(true);
	}

	class BgJumpKing extends JLabel {
		private ImageIcon icon = new ImageIcon("D:/workspace/javawork/Game/images/color.png");
		private Image img = icon.getImage(); // 이미지 객체

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// 이미지를 패널 크기로 조절하여 그린다
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public void init() {
		bgPanel = new BgJumpKing();
		bitMap = "D:/workspace/javawork/Game/images/color.png"; // 실제 연산되는 맵
		player = new Player();

//		thPixel = new Thread(new PixelCheck(player, bitMap)); // 색깔 연산 스레드
//		thPixel.start();

	}

	@Override
	public void setting() {
		setTitle("버블버블");
		setSize(540, 493);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setContentPane(bgPanel); // 기본 컨텐트페인 = 라벨 백그라운드
	}

	@Override
	public void batch() {
		add(player);
	}

	@Override
	public void listener() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (player.isMoveLock() == true) { // Move락이 걸려있으면 메서드실행안됨
					return;
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 오른쪽 이동
					player.setJumpUpDirectionStay(true); // 제자리 점프시 우측 방향 설정 (우측 이미지 때문에 사용)
					player.moveRight();
//					System.out.println(player.getPlayerX() +" a "+ player.getPlayerY());
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 왼쪽이동
					player.setJumpUpDirectionStay(false); // 제자리 점프시 좌측 방향 설정 (좌측 이미지 때문에 사용)
					player.moveLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_UP && player.isLeft() == true) { // 좌측 위 점프
					player.setJumpUpDirection(-1);// 좌측 방향값 설정
					player.jumpUp();
				} else if (e.getKeyCode() == KeyEvent.VK_UP && player.isRight() == true) { // 우측 위 점프
					player.setJumpUpDirection(1); // 우측 방향값 설정
					player.jumpUp();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {// 제자리 점프
					player.jumpUp();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) { // 버튼땔때 멈추는 함수
				if (e.getKeyCode() == KeyEvent.VK_UP) // UP버튼 연속입력 방지
					player.setUp(false);
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) // 좌,우이동 매끄러운 변환 조건문
					player.setRight(false);
				else if (e.getKeyCode() == KeyEvent.VK_LEFT)
					player.setLeft(false);
			}
		});

	}

	public static void main(String[] args) {
		new BubbleApp();

	}

}
