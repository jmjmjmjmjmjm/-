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

	// ���ؽ�Ʈ ����
	private BubbleApp bubbleApp = this; // ���� ���ؽ�Ʈ �����
	// �±�
	private static final String TAG = "JumpKing : ";

	private String bitMap; // ��Ʈ��
	public Player player; // �÷��̾�
	private Thread thPixel; // �ȼ��˻�

	private BgJumpKing bgPanel; // ��׶���

	public BubbleApp() {
		init(); // ���� ��ü����
		setting(); // ���� ����
		batch(); // ��ġ ����
		listener(); // ������ ����
		setVisible(true);
	}

	class BgJumpKing extends JLabel {
		private ImageIcon icon = new ImageIcon("D:/workspace/javawork/Game/images/color.png");
		private Image img = icon.getImage(); // �̹��� ��ü

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// �̹����� �г� ũ��� �����Ͽ� �׸���
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public void init() {
		bgPanel = new BgJumpKing();
		bitMap = "D:/workspace/javawork/Game/images/color.png"; // ���� ����Ǵ� ��
		player = new Player();

//		thPixel = new Thread(new PixelCheck(player, bitMap)); // ���� ���� ������
//		thPixel.start();

	}

	@Override
	public void setting() {
		setTitle("�������");
		setSize(540, 493);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setContentPane(bgPanel); // �⺻ ����Ʈ���� = �� ��׶���
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

				if (player.isMoveLock() == true) { // Move���� �ɷ������� �޼������ȵ�
					return;
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // ������ �̵�
					player.setJumpUpDirectionStay(true); // ���ڸ� ������ ���� ���� ���� (���� �̹��� ������ ���)
					player.moveRight();
//					System.out.println(player.getPlayerX() +" a "+ player.getPlayerY());
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { // �����̵�
					player.setJumpUpDirectionStay(false); // ���ڸ� ������ ���� ���� ���� (���� �̹��� ������ ���)
					player.moveLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_UP && player.isLeft() == true) { // ���� �� ����
					player.setJumpUpDirection(-1);// ���� ���Ⱚ ����
					player.jumpUp();
				} else if (e.getKeyCode() == KeyEvent.VK_UP && player.isRight() == true) { // ���� �� ����
					player.setJumpUpDirection(1); // ���� ���Ⱚ ����
					player.jumpUp();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {// ���ڸ� ����
					player.jumpUp();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) { // ��ư���� ���ߴ� �Լ�
				if (e.getKeyCode() == KeyEvent.VK_UP) // UP��ư �����Է� ����
					player.setUp(false);
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) // ��,���̵� �Ų����� ��ȯ ���ǹ�
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
