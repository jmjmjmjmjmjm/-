package jumpking;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class �������� extends JFrame {
	public static Container c;
	private �������� jumpApp = this;
	Bg bg;
	public Player player; // �÷��̾�

	private static final String TAG = "�������±�";

	private String bitMap; // ��Ʈ��
	private Thread thPixel; // �ȼ��˻�

//	private ���� panel = new ����();

	public ��������() {

		setting(); // JFrame �⺻����
		init(); // new ������Ʈ��
//		batch(); // ȭ�� ����
//		listener(); // ������(�̺�Ʈ)
		setVisible(true);
	}

	public void setting() { // ��,ũ��,�� ����
		c = getContentPane();
		bg = new Bg();

		setTitle("Jump King");
		setSize(1620, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setContentPane(panel); // ����

		c.add(bg);

		setExtendedState(JFrame.MAXIMIZED_BOTH); // ��üȭ�� ���
	

	}

	public void init() { // ������Ʈ��
		player = new Player();

	}

	public void batch() { // ȭ�鱸��
		add(player);
	}

	public void listener() { // ������
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
		new ��������();
	}

}
