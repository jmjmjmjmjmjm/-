package jumpking;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
	private Player player = this;

	private ImageIcon right_run, right_down, right, right_charging, right_jump, // ������
			left_run, left_down, left, left_charging, left_jump, // ����
			charging; // �������

	public boolean isRight = false; // ����(�����������մ���)
	public boolean isLeft = false;
	public boolean isUp = false;
	public boolean isDown = false;

	public int x = 500, y = 750; // ���ʿ����� ��ǥ
	public int energy = 0; // ������°�
	public int ly = 0; // ���ʿ��� �����غ����� �����ʿ��������غ�����

	public Player() {
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
	}

	public void moveRight() { // Ű���� ������Ű

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
		}
		ly = 0;
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
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					setIcon(left);
				}
			}).start();
		}
		ly = 1;
	}

	public void moveUp() { // Ű���� ��Ű

		if (isUp == false) {
			new Thread(new Runnable() {

				public void run() {
					isUp = true;
					while (isUp) {
						try {
							System.out.println("ddd");
							Thread.sleep(400);
							energy++;

							if (ly == 0) {
								if (energy >= 1) {
									System.out.println(energy);
									setLocation(x, y + 10);
									setIcon(right_charging);
								}
							} else {
								if (energy >= 1) {
									System.out.println(energy);
									setLocation(x, y + 10);
									setIcon(left_charging);
								}
							}
							if (energy >= 3) {
								setLocation(x, y + 15);
								setIcon(charging);

							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					energy = 0;
				}
			}).start();
		}
	}

}
