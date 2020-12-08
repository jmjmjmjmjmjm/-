package jumpking;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class �������� extends JFrame {

	private �������� jumpApp = this;

	private static final String TAG = "�������±�";

	private String bitMap; // ��Ʈ��
	private Thread thPixel; // �ȼ��˻�
	public Player player; // �÷��̾�

	private ���� panel = new ����();
	private Stage stage = new Stage();

	private ImageIcon icPlayerLS, icPlayerRS, icPlayerLR, icPlayerRR; // ��,�� �̵� �̹���
	private ImageIcon icJumpR1, icJumpR2, icJumpR3, icJumpR4; // �� ���� �̹���
	private ImageIcon icJumpL1, icJumpL2, icJumpL3, icJumpL4; // �� ���� �̹���

	public ��������() {
		setting(); // JFrame �⺻����
		init(); // new ������Ʈ��
		batch(); // ȭ�� ����
		listener(); // ������(�̺�Ʈ)
		setVisible(true);
	}

	public void setting() { // ��,ũ��,�� ����
		setTitle("Jump King");
		setSize(1620, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setContentPane(panel); // ����
		 setContentPane(stage); //���� ȭ�麸�����°�
		setLayout(null);
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
				if (e.getKeyCode()==KeyEvent.VK_UP) {
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
				}

			}

		});
	}

	public static void main(String[] args) {
		new ��������();
	}

}
