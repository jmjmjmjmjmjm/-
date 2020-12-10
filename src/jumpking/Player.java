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
	public boolean isJump = false;
	public int x = 500, y = 750; // ������ġ
	public int energy = 0; // ������°�
	public int ly = 0; // ���ʿ��� �����غ����� �����ʿ��������غ�����
	public String status = "������"; // ������� �ٶ󺸰��ִ���

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
			status = "������";
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
			status = "����";
		}
		ly = 1;
	}

	public void change(int height) { // ���� ���������� �޾Ƽ� ���⼭ ����

		new Bg(); // ���� �ٲٱ����� �θ�

		for (int y2 = 900; height < 300; height++) {
			try {
				y2--;
				setLocation(x, y2);
				Thread.sleep(1);
				if (status == "������") { // ������ �ٶ󺼽� ��������������
					setIcon(right_jump);
				} else { // �ƴϸ� ������������
					setIcon(left_jump);
				}
				y = y2;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void moveUp() { // Ű���� ��Ű

		if (isUp == false) {
			new Thread(new Runnable() {

				public void run() {
					isUp = true;
					while (isUp) {
						try {
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

				}
			}).start();
		}

	}

	public void moveUp2() {
		if (isJump == true) { // Ű���� ��Ű �Է��� ����������
			new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println("������");
					if (energy > 3) {
						for (int i = 0; i < 300; i++) {
							try {
								y--;
								setLocation(x, y);
								Thread.sleep(1);
								if (status == "������") { // ������ �ٶ󺼽� ��������������
									setIcon(right_jump);
								} else {
									setIcon(left_jump);
								}

								if (y <= 2) { // ����ȯ�� �ѱ�� �Լ�
									change(300 - i);
									break;
								}

							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if (status == "������") {
							setIcon(right);
						} else {
							setIcon(left);
						}
						energy = 0;
					}
					if (energy >= 1) {
						for (int i = 0; i < 200; i++) {
							try {
								y--;
								setLocation(x, y);
								Thread.sleep(1);
								if (status == "������") {
									setIcon(right_jump);
								} else {
									setIcon(left);
								}
								if (y <= 2) {
									change(300 - i);
									break;
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (status == "������") {
							setIcon(right);
						} else {
							setIcon(left);
						}
						;
						energy = 0;
					}

				}
			}).start();
		}
	}

}
