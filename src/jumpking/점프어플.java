package jumpking;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class �������� extends JFrame {

	private �������� jumpApp = this;
	
	private static final String TAG = "�������±�";

	private �� panel =new ��();

	public ��������() {
		init(); // new ������Ʈ��
		setting(); // JFrame �⺻����
//		batch(); // ȭ�� ����
//		listener(); // ������(�̺�Ʈ)
	}

	public void setting() { // ��,ũ��,�� ����
		setTitle("Jump King");
		setSize(1800, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setLayout(null);
		setVisible(true);
	}

	public void init() { // ������Ʈ��
		

	}

	public void batch() { // ȭ�鱸��

	}

	public void listener() { // ������ (�΋H���°�)

	}

	public static void main(String[] args) {
		new ��������();
	}

}
