package jumpking;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class �������� extends JFrame {
	public static Container c;
	private �������� jumpApp = this;
	public Bg bg; // ��������ȭ��
	public Player player; // �÷��̾�
	ColorCheck colorcheck; // ����
	private static final String TAG = "�������±�";

	public ��������() {

		setting(); // JFrame �⺻����
		init(); // new ������Ʈ��
		batch(); // ȭ�� ����
		listener(); // ������(�̺�Ʈ)
		setVisible(true);
	}

	public void setting() { // ��,ũ��,�� ����
		c = getContentPane();
		bg = new Bg();
		setTitle("Jump King");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // ��üȭ�� ���
		setContentPane(bg.bgla);
	}

	public void init() { // ������Ʈ��
		player = new Player(bg);
	}

	public void batch() { // ȭ�鱸��
		colorcheck = new ColorCheck(player);
		add(player);

	}

	public void listener() { // ������
		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//					player.isRight = true;
					player.moveRight();
					player.isLeft = true;
					player.isUp = true;
					player.isRJ = true;

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

					player.moveLeft();
					player.isRight = true;
					player.isUp = true;
					player.isLJ = true; // �밢�� ����ġ����

				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUp();
					player.isUp = true;
					player.isRight = true;
					player.isLeft = true;
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
					player.isUp = true;
					player.isRight = true;
					player.isLeft = true;
					player.isUp2 = true;
					player.isUp2();
					player.isUp2 = true;

				}

			}

		});
	}

	public static void main(String[] args) {
		new ��������();
	}

}
