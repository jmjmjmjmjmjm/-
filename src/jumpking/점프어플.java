package jumpking;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class �������� extends JFrame {

	private �������� jumpApp = this;
	
	private static final String TAG = "�������±�";

	private ���� panel =new ����();
	private Stage stage = new Stage();
	

	public ��������() {
		init(); // new ������Ʈ��
		setting(); // JFrame �⺻����
//		batch(); // ȭ�� ����
//		listener(); // ������(�̺�Ʈ)
	}

	public void setting() { // ��,ũ��,�� ����
		setTitle("Jump King");
		setSize(1620, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel); //����
		setContentPane(stage); //���� ȭ�麸�����°�
		setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH); //��üȭ�� ���
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
