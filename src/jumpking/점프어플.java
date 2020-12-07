package jumpking;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jumpking.BubbleApp.BgJumpKing;

public class 점프어플 extends JFrame {

	private 점프어플 jumpApp = this;

	private static final String TAG = "점프앱태그";

	private String bitMap; // 비트맵
	private Thread thPixel; // 픽셀검사
	public Player player; // 플레이어

	private 투명도 panel = new 투명도();
	private Stage stage = new Stage();

	private ImageIcon icPlayerLS, icPlayerRS, icPlayerLR, icPlayerRR; // 좌,우 이동 이미지
	private ImageIcon icJumpR1, icJumpR2, icJumpR3, icJumpR4; // 우 점프 이미지
	private ImageIcon icJumpL1, icJumpL2, icJumpL3, icJumpL4; // 좌 점프 이미지

	public 점프어플() {
		init(); // new 오브젝트를
		setting(); // JFrame 기본세팅
		batch(); // 화면 구성
		listener(); // 리스너(이벤트)
		setVisible(true);
	}

	public void setting() { // 맵,크기,등 세팅
		setTitle("Jump King");
		setSize(1620, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel); // 투명도
		//setContentPane(stage); //실제 화면보여지는것
		setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // 전체화면 모드
	}

	public void init() { // 오브젝트들
		bitMap = "점프킹이미지/맵/투명도.jpg"; // 실제 연산되는 맵
		player = new Player();
		thPixel = new Thread(new PixelCheck(player, bitMap)); // 색깔 연산 스레드
		thPixel.start();
		
	}

	public void batch() { // 화면구성
		add(player);

	}

	public void listener() { // 리스너 (부딫히는거)
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
		new 점프어플();
	}

}
