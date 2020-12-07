package jumpking;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// �Һ� �߾ȸԾ �������� getter,setter ����

public class Player extends JLabel {
	public Player player = this; // �÷��̾� ���ؽ�Ʈ
	private final static String TAG = "Player"; // �±�
	private ImageIcon icPlayerLS, icPlayerRS, icPlayerLR, icPlayerRR; // ��,�� �̵� �̹���
	private ImageIcon icJumpR1, icJumpR2, icJumpR3, icJumpR4; // �� ���� �̹���
	private ImageIcon icJumpL1, icJumpL2, icJumpL3, icJumpL4; // �� ���� �̹���
	private int playerX = 100; // ĳ���� �⺻ ���� X��
	private int playerY = 100; // ĳƽ�� �⺻ ���� Y��
	private int start = 0;

	// �¿��̵� Lock
	private boolean moveLock = false; // true�� Lock ���� �������̰��Ѵ�

	// true�϶��� �̵�����
	private boolean isRight = false; // �����̵�
	private boolean isLeft = false; // �����̵�
	private boolean isUp = false; // ���� -�� �̵�
	private boolean isDown = false; // ���� - �Ʒ� �̵�
	private boolean isMoveDown = true; // true�� �÷��̾ �������� false�� �ȶ�������

	private int jumpGauge = 0; // �Ŀ� ���� (����� ��) (���� ������ ���)
	private int jumpGauge_Down = 0; // (�Ʒ��� ������ ���)

	// ����
	private int jumpUpDirection = 0; // (��,��,���ڸ� ������ �������� ����) -1 �¹���, 0 ���ڸ�, 1 �����
	private boolean jumpUpDirectionStay = true; // (���ڸ� ������ ��,�� �̹�������� ����)

	private Thread thDown; // �߷�

	public boolean Gravity = true;

	public Player() {
		init();

		thDown.start();
		setIcon(icPlayerRS);
		setSize(50, 50); // ������� ũ��
		setLocation(playerX, playerY); // �⺻ ������ġ

	}

	void init() {
		// ���� �̹���
		icPlayerRS = new ImageIcon("images/icPlayerRS.png"); // �̵�-������
		icPlayerRR = new ImageIcon("images/icPlayerRR.png"); // �̵�-��
		icJumpR1 = new ImageIcon("images/icJumpR1.png"); // ����1 ���ڸ�
		icJumpR2 = new ImageIcon("images/icJumpR2.png");// ����2 ����
		icJumpR3 = new ImageIcon("images/icJumpR3.png");// ����3 ����
		icJumpR4 = new ImageIcon("images/icJumpR4.png");// ����4 ����

		// ���� �̹���
		icPlayerLS = new ImageIcon("images/icPlayerLS.png");// �̵�- ������
		icPlayerLR = new ImageIcon("images/icPlayerLR.png");// �̵�- ��
		icJumpL1 = new ImageIcon("images/icJumpL1.png");// ����1 ���ڸ�
		icJumpL2 = new ImageIcon("images/icJumpL2.png");// ����2 ����
		icJumpL3 = new ImageIcon("images/icJumpL3.png");// ����3 ����
		icJumpL4 = new ImageIcon("images/icJumpL4.png");// ����4 ����

		// �߷� ������
		thDown = new Thread(new DownMove());

	};

	public void moveRight() {
		Thread rightTh = new Thread(new RightMove()); // ������ �̵� ������
		if (isRight == false) { // moveRight() �ѹ��� �����ϴ� ���ǹ� // moveLeft(),JumpUp(),JumpDown() ����
			System.out.println(TAG + "moveRight()");
			rightTh.start();
		}
		setIcon(icPlayerRS);
	}

	public void moveLeft() {
		Thread leftTh = new Thread(new LeftMove()); // �����̵� ������
		if (isLeft == false) {
			System.out.println(TAG + "moveLeft()");
			leftTh.start();
		}
		setIcon(icPlayerLS);
	}

	public void jumpUp() {
		Thread thJumpUp = new Thread(new ThJumpUp());
		if (isUp == false) {
			System.out.println(TAG + "JumpUp()");
			thJumpUp.start();
		}
	}

	public void jumpDown() {
		Thread thJumpDown = new Thread(new ThJumpDown());
		if (isDown == false) {
			System.out.println(TAG + "JumpDown()");
			thJumpDown.start();
		}
	}

	public void Position() {
		System.out.println(playerX + "aa" + playerY);

	}

	class RightMove implements Runnable { // Leftmove()�� ���Ǻ��
		@Override
		public void run() {
			// ��, �� �̵������ �ε巴�� �ϱ����� �ٽ��ѹ� ����
			isLeft = false; // �����̵� false
			isRight = true; // �������̵� true

			while (isRight == true) {
				try {
					playerX = playerX + 10; // ������ 1ȸ�� �̵��� x�� 10������
					setLocation(playerX, playerY); // ���ο� repaint() ����

					// �����ð����� �̹��� ���� ��,��,������,�����ٿ� ��� ����
					Thread.sleep(10);
					setIcon(icPlayerRR);
					Thread.sleep(10);
					setIcon(icPlayerRS);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	class LeftMove implements Runnable {
		@Override
		public void run() {
			isLeft = true;
			isRight = false;
			while (isLeft == true) {
				try {
					playerX = playerX - 10;
					setLocation(playerX, playerY); // ���ο� repaint() ����
					Thread.sleep(10);
					setIcon(icPlayerLR);
					Thread.sleep(10);
					setIcon(icPlayerLS);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	class DownMove implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					if (start == 0) {// �� �Ȱɸ����¿����� ����
						if (Gravity == false) {
							isDown = false; // �ٿ� ����
							isUp = false; // �� ����
//							isMoveDown = false; // ���� ���� false

							if (jumpUpDirection == 1) { // �� ���� ���� �̹���
								setIcon(icJumpR4);
							} else if (jumpUpDirection == -1) { // �� ���� ���� �̹���
								setIcon(icJumpL4);
							} else if (jumpUpDirectionStay == true) { // �� ���ڸ� ���� ���� �̹���
								setIcon(icJumpR4);
							} else if (jumpUpDirectionStay == false) {// �� ���ڸ� ���� ���� �̹���
								setIcon(icJumpL4);
							}
						} else {
							Gravity = true; // ���� ���̸� true
						}

						if (Gravity == true) {// ���� ���϶� if��
							isRight = false; // �� �̵��Ұ���
							isLeft = false;// �� �̵� �Ұ���
							playerY = playerY + 4; // ������ 1ȸ�� 4��ŭ �ٿ�
							if (jumpUpDirectionStay == true) { // ���������� ���ڸ������� ������ icJumpR2�����ܻ��
								setIcon(icJumpR3);
							} else if (jumpUpDirectionStay == false) { // ���������� ���ڸ������� ������ icJumpR2�����ܻ��
								setIcon(icJumpL3);
							}
						}
					}
					setLocation(playerX, playerY); // ���ο� repaint() ����
					Thread.sleep(20);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ThJumpUp implements Runnable {
		@Override
		public void run() {
			moveLock = true; // ������ ��,���̵� �Ұ���
			isUp = true; // ���� ����
			isRight = false; // �������̵� �Ұ���
			isLeft = false; // ���� �̵� �Ұ���
			start = 1;
			while (isUp == true) {
				setIcon(icJumpR1);
				try {
					if (jumpGauge > -300) { // ��(�Ŀ�)�� 300�̻��̸� ���̻� �����
						jumpGauge = jumpGauge - 5; // �ѹ��� 5�� ����������
					}
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			jumpGauge = jumpGauge + playerY; // ���������� + �÷��̾� ���� ��ġY��

			while (true) {
				try {
					isRight = false; // �� �̵��Ұ���
					isLeft = false;// �� �̵� �Ұ���
					if (jumpUpDirection == 1) { // ���� ������ �������ΰ��� ���ǹ�
						playerX = playerX + 4;
						setIcon(icJumpR2);
					} else if (jumpUpDirection == -1) { // ���� ������ �������ΰ��� ���ǹ�
						playerX = playerX - 4;
						setIcon(icJumpL2);
					} else if (jumpUpDirectionStay == true) { // ���������� ���ڸ������� ������ icJumpR2�����ܻ��
						setIcon(icJumpR2);
					} else if (jumpUpDirectionStay == false) { // ���������� ���ڸ������� ������ icJumpR2�����ܻ��
						setIcon(icJumpL2);
					}
					setLocation(playerX, playerY); // ���ο� repaint() ����
					// ���������� �̵��ϴ� ���ǹ�
					if (jumpGauge + 100 < playerY) {// ���� �б�1 ���� �� �κ�
						playerY = playerY - 5;
					} else if (jumpGauge + 30 < playerY && jumpGauge + 100 >= playerY) { // ���� �б�2 �߾� �κ�
						playerY = playerY - 3;
					} else if (jumpGauge < playerY && jumpGauge + 30 >= playerY) { // ���� �б�3 ���� �� �κ�
						playerY = playerY - 1;
					}

					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (jumpGauge >= playerY) {// �ִ� ���� ���޽� �Լ�����
					jumpGauge_Down = jumpGauge; // Down���� ������ �Ѱ���
					jumpGauge = 0; // ���������� 0���� �ʱ�ȭ

					break;
				}
			}
			jumpDown(); // �ٿ� �޼��� ȣ��
		}
	}

	class ThJumpDown implements Runnable {
		@Override
		public void run() {
			isDown = true; // �ٿ� ���
			while (isDown) {
				setLocation(playerX, playerY); // ���ο� repaint() ����

				try {
					if (Gravity == true) {
						System.out.println("�׽�Ʈ����");
						isRight = false;
						isLeft = false;
						if (jumpUpDirection == 1) { // �����ٿ�� x������
							setIcon(icJumpR3);
							playerX = playerX + 4;
						} else if (jumpUpDirection == -1) { // �������ٿ�� x�� ����
							setIcon(icJumpL3);
							playerX = playerX - 4;
						} else if (jumpUpDirectionStay == true) { // �������� ���ڸ��ٱ�� �̹���
							setIcon(icJumpR3);
						} else if (jumpUpDirectionStay == false) {// �������� ���ڸ��ٱ�� �̹���
							setIcon(icJumpL3);
						}
						if (jumpGauge_Down + 100 < playerY) {// ���� �б�6 ���� �� �κ�
							playerY = playerY + 5;
						} else if (jumpGauge_Down + 30 < playerY && jumpGauge_Down + 100 >= playerY) {// ���� �б�5 �߰�
							playerY = playerY + 3;
						} else if (jumpGauge_Down + 30 >= playerY) { // ���� �б�4 ���� �� �κ�
							playerY = playerY + 1;
						}
					} else if (Gravity == false) {
						System.out.println("�׽�Ʈ");
						isDown = false; // �ٿ� ����
						isUp = false; // �� ����
						if (jumpUpDirection == 1) { // �� ���� ���� �̹���
							setIcon(icJumpR4);
						} else if (jumpUpDirection == -1) { // �� ���� ���� �̹���
							setIcon(icJumpL4);
						} else if (jumpUpDirectionStay == true) { // �� ���ڸ� ���� ���� �̹���
							setIcon(icJumpR4);
						} else if (jumpUpDirectionStay == false) {// �� ���ڸ� ���� ���� �̹���
							setIcon(icJumpL4);
						}
						jumpUpDirection = 0;// �������� �ʱ�ȭ (������ ������ �׳� ���������� �����ذ��)
						moveLock = false; // ������ ������ �ٽ� �̵��Ҽ��ְ� Lock����
						start = 0;
						break;
					}
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ImageIcon getIcPlayerLS() {
		return icPlayerLS;
	}

	public void setIcPlayerLS(ImageIcon icPlayerLS) {
		this.icPlayerLS = icPlayerLS;
	}

	public ImageIcon getIcPlayerRS() {
		return icPlayerRS;
	}

	public void setIcPlayerRS(ImageIcon icPlayerRS) {
		this.icPlayerRS = icPlayerRS;
	}

	public ImageIcon getIcPlayerLR() {
		return icPlayerLR;
	}

	public void setIcPlayerLR(ImageIcon icPlayerLR) {
		this.icPlayerLR = icPlayerLR;
	}

	public ImageIcon getIcPlayerRR() {
		return icPlayerRR;
	}

	public void setIcPlayerRR(ImageIcon icPlayerRR) {
		this.icPlayerRR = icPlayerRR;
	}

	public ImageIcon getIcJumpR1() {
		return icJumpR1;
	}

	public void setIcJumpR1(ImageIcon icJumpR1) {
		this.icJumpR1 = icJumpR1;
	}

	public ImageIcon getIcJumpR2() {
		return icJumpR2;
	}

	public void setIcJumpR2(ImageIcon icJumpR2) {
		this.icJumpR2 = icJumpR2;
	}

	public ImageIcon getIcJumpR3() {
		return icJumpR3;
	}

	public void setIcJumpR3(ImageIcon icJumpR3) {
		this.icJumpR3 = icJumpR3;
	}

	public ImageIcon getIcJumpR4() {
		return icJumpR4;
	}

	public void setIcJumpR4(ImageIcon icJumpR4) {
		this.icJumpR4 = icJumpR4;
	}

	public ImageIcon getIcJumpL1() {
		return icJumpL1;
	}

	public void setIcJumpL1(ImageIcon icJumpL1) {
		this.icJumpL1 = icJumpL1;
	}

	public ImageIcon getIcJumpL2() {
		return icJumpL2;
	}

	public void setIcJumpL2(ImageIcon icJumpL2) {
		this.icJumpL2 = icJumpL2;
	}

	public ImageIcon getIcJumpL3() {
		return icJumpL3;
	}

	public void setIcJumpL3(ImageIcon icJumpL3) {
		this.icJumpL3 = icJumpL3;
	}

	public ImageIcon getIcJumpL4() {
		return icJumpL4;
	}

	public void setIcJumpL4(ImageIcon icJumpL4) {
		this.icJumpL4 = icJumpL4;
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public boolean isMoveLock() {
		return moveLock;
	}

	public void setMoveLock(boolean moveLock) {
		this.moveLock = moveLock;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public boolean isMoveDown() {
		return isMoveDown;
	}

	public void setMoveDown(boolean isMoveDown) {
		this.isMoveDown = isMoveDown;
	}

	public int getJumpGauge() {
		return jumpGauge;
	}

	public void setJumpGauge(int jumpGauge) {
		this.jumpGauge = jumpGauge;
	}

	public int getJumpGauge_Down() {
		return jumpGauge_Down;
	}

	public void setJumpGauge_Down(int jumpGauge_Down) {
		this.jumpGauge_Down = jumpGauge_Down;
	}

	public int getJumpUpDirection() {
		return jumpUpDirection;
	}

	public void setJumpUpDirection(int jumpUpDirection) {
		this.jumpUpDirection = jumpUpDirection;
	}

	public boolean isJumpUpDirectionStay() {
		return jumpUpDirectionStay;
	}

	public void setJumpUpDirectionStay(boolean jumpUpDirectionStay) {
		this.jumpUpDirectionStay = jumpUpDirectionStay;
	}

	public Thread getThDown() {
		return thDown;
	}

	public void setThDown(Thread thDown) {
		this.thDown = thDown;
	}

	public static String getTag() {
		return TAG;
	}

}
