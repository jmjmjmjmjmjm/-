package jumpking;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import test.MyBg;

public class Player extends JLabel {
	private Player player = this;

	private ImageIcon right_run, right_down, right, right_charging, right_jump, // ������
			left_run, left_down, left, left_charging, left_jump, // ����
			charging, RJ, LJ; // �������

	public boolean isRight = false; // ����(�����������մ���)
	public boolean isLeft = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isUp2 = false;
	public boolean isRJ = false;
	public boolean isLJ = false;
	static public int x = 500, y = 700; // ������ġ
	static public int j = 0;
	public int energy = 0; // ������°�
	public String status = "������"; // ������� �ٶ󺸰��ִ���
	Bg bg;
	int i = 0; // �ʺ��������� ����

	public Player(Bg bg) {

		right_run = new ImageIcon("images/�����ʴ޸���.png");
		right_down = new ImageIcon("images/�����ʶ�������.png");
		right = new ImageIcon("images/�����ʹٶ󺸱�.png");
		right_charging = new ImageIcon("images/�����ʿ��� ��¡.png");
		right_jump = new ImageIcon("images/����������.png");
		left_run = new ImageIcon("images/���ʴ޸���.png");
		left_down = new ImageIcon("images/���ʶ�������.png");
		left = new ImageIcon("images/���ʹٶ󺸱�.png");
		left_charging = new ImageIcon("images/���ʿ��� ��¡.png");
		left_jump = new ImageIcon("images/��������.png");

		charging = new ImageIcon("images/��¡Ǯ.png");

		setIcon(right);
		setSize(100, 100);
		setLocation(x, y);
		this.bg = bg; // ����� �ٲٱ�����

	}

	public void moveRight() { // Ű���� ������Ű

		// 2��°���� isRight == true
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isRight = true;
					while (isRight) {
						x = x + 2;

						setLocation(x, y); // ���ο� repaint()�� ����
						try {
							setIcon(right_run);
							Thread.sleep(6);
							setIcon(right);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}).start();
			status = "������";
		}
	}

	public void moveLeft() { // Ű���� ����Ű

		if (isLeft == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isLeft = true;
					while (isLeft) {
						x = x - 2;
						setLocation(x, y); // ���ο� repaint()�� ����
						try {
							setIcon(left_run);
							Thread.sleep(6);
							setIcon(left);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}

				}
			}).start();
			status = "����";
		}
	}

	public void change(int height) { // ���� ���������� �޾Ƽ� ���⼭ ����
		if (i < 4) {
			i++;
			Bg.�̹�������(i);// ���� �ٲٱ����� �θ�
		}
		for (y = 900; height < 300; height++) {
			try {
				y--;
				setLocation(x, y);
				Thread.sleep(1);
				if (status == "������") { // ������ �ٶ󺼽� ��������������
					setIcon(right_jump);
				} else { // �ƴϸ� ������������
					setIcon(left_jump);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void isUp() { // Ű���� ��Ű

		if (isUp == false) {
			new Thread(new Runnable() {
				public void run() {

					while (isUp) {
						energy++;

						if (energy >= 1000000 && status == "������") {
							setLocation(x, y + 10);
							setIcon(right_charging);
							status = "������";

						}
						if (energy >= 1000000 && status == "����") {
							setLocation(x, y + 10);
							setIcon(left_charging);
							status = "����";
						}

						if (energy >= 30000000) {
							setLocation(x, y + 15);
							setIcon(charging);
							break;
						}

					}

				}
			}).start();

		}

	}

	public void isUp2() {
		if (isUp2 == true) { // Ű���� ��Ű �Է��� ����������
			new Thread(new Runnable() {
				@Override
				public void run() {
					isUp = false;
					System.out.println("������");

					if (isUp == false && isRJ == true) { // �밢���������ϰ��
						isRJ = false;
						if (status == "������") { // ������ �ٶ󺼽� ��������������
							setIcon(right_jump);
						} else {
							setIcon(left_jump);
						}
						for (int i = 0; i < 150; i++) {
							try {
								//���� �΋H���°ų���
								y = y - 2;
								x = x + 2;
								setLocation(x, y);
								Thread.sleep(1);

								if (y < 0) { // ����ȯ�� �ѱ�� �Լ�
									change(300 - i);
									break;
								}

							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						����();
						isUp = true;
					}

					if (isUp == false && isLJ == true) { // �밢�������ϰ��

						isLJ = false;
						for (int i = 0; i < 150; i++) {

							try {
								if (status == "������") { // ������ �ٶ󺼽� ��������������
									setIcon(right_jump);
								} else {
									setIcon(left_jump);
								}
								y = y - 2;
								x = x - 1;
								setLocation(x, y);
								Thread.sleep(1);

								if (y < 0) { // ����ȯ�� �ѱ�� �Լ�
									change(300 - i);
									break;
								}

							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						isUp = true;
						����();
					}
//					if (energy > 30000000) { // �׳������ϰ��
//						for (int i = 0; i < 300; i++) {
//							isUp = false;
//							try {
//								if (status == "������") { // ������ �ٶ󺼽� ��������������
//									setIcon(right_jump);
//								} else {
//									setIcon(left_jump);
//								}
//								y--;
//								setLocation(x, y);
//								Thread.sleep(1);
//
//								if (y < 0) { // ����ȯ�� �ѱ�� �Լ�
//									change(300 - i);
//									break;
//								}
//
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//						}
//						if (status == "������") {
//							setIcon(right);
//							energy = 0;
//						} else {
//							setIcon(left);
//							energy = 0;
//						}
//						����();
//					}

//					if (energy >= 1000000) {
//
//						for (int i = 0; i < 200; i++) {
//							try {
//								y--;
//								setLocation(x, y);
//								if (status == "������") {
//									setIcon(right_jump);
//								} else {
//									setIcon(left);
//								}
//
//								Thread.sleep(1);
//
//								if (y < 0) {
//									change(300 - i);
//									break;
//								}
//								energy = 0;
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//						}
//
//						energy = 0;
//						����();
//					}
					isUp2 = false;

				}

			}).start();
		}

	}

	public void ����() {
		if (status == "������") {
			setIcon(right);
			energy = 0;
			repaint();
		} else {
			setIcon(left);
			energy = 0;
			repaint();
		}
	}
}
