package jumpking;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 롬북 잘안먹어서 수동으로 getter,setter 생성

public class Player extends JLabel {
	public Player player = this; // 플레이어 콘텍스트
	private final static String TAG = "Player"; // 태그
	private ImageIcon icPlayerLS, icPlayerRS, icPlayerLR, icPlayerRR; // 좌,우 이동 이미지
	private ImageIcon icJumpR1, icJumpR2, icJumpR3, icJumpR4; // 우 점프 이미지
	private ImageIcon icJumpL1, icJumpL2, icJumpL3, icJumpL4; // 좌 점프 이미지
	private int playerX = 100; // 캐릭터 기본 시작 X축
	private int playerY = 100; // 캐틱터 기본 시작 Y축
	private int start = 0;

	// 좌우이동 Lock
	private boolean moveLock = false; // true시 Lock 으로 못움직이게한다

	// true일때만 이동가능
	private boolean isRight = false; // 우측이동
	private boolean isLeft = false; // 좌측이동
	private boolean isUp = false; // 점프 -위 이동
	private boolean isDown = false; // 점프 - 아래 이동
	private boolean isMoveDown = true; // true시 플레이어가 떨어지고 false시 안떨어진다

	private int jumpGauge = 0; // 파워 변수 (기모우는 것) (위로 점프시 사용)
	private int jumpGauge_Down = 0; // (아래로 점프시 사용)

	// 점프
	private int jumpUpDirection = 0; // (좌,우,제자리 점프시 방향지정 변수) -1 좌방향, 0 제자리, 1 우방향
	private boolean jumpUpDirectionStay = true; // (제자리 점프시 좌,우 이미지저장용 변수)

	private Thread thDown; // 중력

	public boolean Gravity = true;

	public Player() {
		init();

		thDown.start();
		setIcon(icPlayerRS);
		setSize(50, 50); // 버블버블 크기
		setLocation(playerX, playerY); // 기본 시작위치

	}

	void init() {
		// 우측 이미지
		icPlayerRS = new ImageIcon("images/icPlayerRS.png"); // 이동-가만히
		icPlayerRR = new ImageIcon("images/icPlayerRR.png"); // 이동-뜀
		icJumpR1 = new ImageIcon("images/icJumpR1.png"); // 점프1 제자리
		icJumpR2 = new ImageIcon("images/icJumpR2.png");// 점프2 점프
		icJumpR3 = new ImageIcon("images/icJumpR3.png");// 점프3 낙하
		icJumpR4 = new ImageIcon("images/icJumpR4.png");// 점프4 착지

		// 좌측 이미지
		icPlayerLS = new ImageIcon("images/icPlayerLS.png");// 이동- 가만히
		icPlayerLR = new ImageIcon("images/icPlayerLR.png");// 이동- 뜀
		icJumpL1 = new ImageIcon("images/icJumpL1.png");// 점프1 제자리
		icJumpL2 = new ImageIcon("images/icJumpL2.png");// 점프2 점프
		icJumpL3 = new ImageIcon("images/icJumpL3.png");// 점프3 낙하
		icJumpL4 = new ImageIcon("images/icJumpL4.png");// 점프4 착지

		// 중력 쓰레드
		thDown = new Thread(new DownMove());

	};

	public void moveRight() {
		Thread rightTh = new Thread(new RightMove()); // 오른쪽 이동 스레드
		if (isRight == false) { // moveRight() 한번만 실행하는 조건문 // moveLeft(),JumpUp(),JumpDown() 동일
			System.out.println(TAG + "moveRight()");
			rightTh.start();
		}
		setIcon(icPlayerRS);
	}

	public void moveLeft() {
		Thread leftTh = new Thread(new LeftMove()); // 왼쪽이동 스레드
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

	class RightMove implements Runnable { // Leftmove()랑 거의비슷
		@Override
		public void run() {
			// 좌, 우 이동변경시 부드럽게 하기위해 다시한번 설정
			isLeft = false; // 왼쪽이동 false
			isRight = true; // 오른쪽이동 true

			while (isRight == true) {
				try {
					playerX = playerX + 10; // 쓰레드 1회당 이동시 x값 10씩증가
					setLocation(playerX, playerY); // 내부에 repaint() 존재

					// 일정시간마다 이미지 변경 좌,우,점프업,점프다운 모두 동일
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
					setLocation(playerX, playerY); // 내부에 repaint() 존재
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
					if (start == 0) {// 락 안걸린상태에서만 동작
						if (Gravity == false) {
							isDown = false; // 다운 금지
							isUp = false; // 업 금지
//							isMoveDown = false; // 벽돌 위면 false

							if (jumpUpDirection == 1) { // 우 점프 착지 이미지
								setIcon(icJumpR4);
							} else if (jumpUpDirection == -1) { // 좌 점프 착지 이미지
								setIcon(icJumpL4);
							} else if (jumpUpDirectionStay == true) { // 우 제자리 점프 착지 이미지
								setIcon(icJumpR4);
							} else if (jumpUpDirectionStay == false) {// 좌 제자리 점프 착지 이미지
								setIcon(icJumpL4);
							}
						} else {
							Gravity = true; // 벽돌 밖이면 true
						}

						if (Gravity == true) {// 벽돌 밖일때 if문
							isRight = false; // 우 이동불가능
							isLeft = false;// 좌 이동 불가능
							playerY = playerY + 4; // 쓰레드 1회당 4만큼 다운
							if (jumpUpDirectionStay == true) { // 우측을보며 제자리점프시 아이콘 icJumpR2아이콘사용
								setIcon(icJumpR3);
							} else if (jumpUpDirectionStay == false) { // 좌측을보며 제자리점프시 아이콘 icJumpR2아이콘사용
								setIcon(icJumpL3);
							}
						}
					}
					setLocation(playerX, playerY); // 내부에 repaint() 존재
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
			moveLock = true; // 점프시 좌,우이동 불가능
			isUp = true; // 업만 가능
			isRight = false; // 오른쪽이동 불가능
			isLeft = false; // 왼쪽 이동 불가능
			start = 1;
			while (isUp == true) {
				setIcon(icJumpR1);
				try {
					if (jumpGauge > -300) { // 기(파워)가 300이상이면 더이상 못모움
						jumpGauge = jumpGauge - 5; // 한번에 5씩 모을수있음
					}
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			jumpGauge = jumpGauge + playerY; // 점프게이지 + 플레이어 현재 위치Y값

			while (true) {
				try {
					isRight = false; // 우 이동불가능
					isLeft = false;// 좌 이동 불가능
					if (jumpUpDirection == 1) { // 우측 점프시 우측으로가는 조건문
						playerX = playerX + 4;
						setIcon(icJumpR2);
					} else if (jumpUpDirection == -1) { // 좌측 점프시 좌측으로가는 조건문
						playerX = playerX - 4;
						setIcon(icJumpL2);
					} else if (jumpUpDirectionStay == true) { // 우측을보며 제자리점프시 아이콘 icJumpR2아이콘사용
						setIcon(icJumpR2);
					} else if (jumpUpDirectionStay == false) { // 좌측을보며 제자리점프시 아이콘 icJumpR2아이콘사용
						setIcon(icJumpL2);
					}
					setLocation(playerX, playerY); // 내부에 repaint() 존재
					// 포물선으로 이동하는 조건문
					if (jumpGauge + 100 < playerY) {// 점프 분기1 제일 밑 부분
						playerY = playerY - 5;
					} else if (jumpGauge + 30 < playerY && jumpGauge + 100 >= playerY) { // 점프 분기2 중앙 부분
						playerY = playerY - 3;
					} else if (jumpGauge < playerY && jumpGauge + 30 >= playerY) { // 점프 분기3 제일 윗 부분
						playerY = playerY - 1;
					}

					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (jumpGauge >= playerY) {// 최대 높이 도달시 함수종료
					jumpGauge_Down = jumpGauge; // Down에쓸 게이지 넘겨줌
					jumpGauge = 0; // 점프게이지 0으로 초기화

					break;
				}
			}
			jumpDown(); // 다운 메서드 호출
		}
	}

	class ThJumpDown implements Runnable {
		@Override
		public void run() {
			isDown = true; // 다운 사용
			while (isDown) {
				setLocation(playerX, playerY); // 내부에 repaint() 존재

				try {
					if (Gravity == true) {
						System.out.println("테스트시작");
						isRight = false;
						isLeft = false;
						if (jumpUpDirection == 1) { // 우측다운시 x값증가
							setIcon(icJumpR3);
							playerX = playerX + 4;
						} else if (jumpUpDirection == -1) { // 좌측측다운시 x값 감소
							setIcon(icJumpL3);
							playerX = playerX - 4;
						} else if (jumpUpDirectionStay == true) { // 우측보며 제자리뛰기시 이미지
							setIcon(icJumpR3);
						} else if (jumpUpDirectionStay == false) {// 좌측보며 제자리뛰기시 이미지
							setIcon(icJumpL3);
						}
						if (jumpGauge_Down + 100 < playerY) {// 점프 분기6 제일 밑 부분
							playerY = playerY + 5;
						} else if (jumpGauge_Down + 30 < playerY && jumpGauge_Down + 100 >= playerY) {// 점프 분기5 중간
							playerY = playerY + 3;
						} else if (jumpGauge_Down + 30 >= playerY) { // 점프 분기4 제일 윗 부분
							playerY = playerY + 1;
						}
					} else if (Gravity == false) {
						System.out.println("테스트");
						isDown = false; // 다운 금지
						isUp = false; // 업 금지
						if (jumpUpDirection == 1) { // 우 점프 착지 이미지
							setIcon(icJumpR4);
						} else if (jumpUpDirection == -1) { // 좌 점프 착지 이미지
							setIcon(icJumpL4);
						} else if (jumpUpDirectionStay == true) { // 우 제자리 점프 착지 이미지
							setIcon(icJumpR4);
						} else if (jumpUpDirectionStay == false) {// 좌 제자리 점프 착지 이미지
							setIcon(icJumpL4);
						}
						jumpUpDirection = 0;// 점프방향 초기화 (점프가 끝나고 그냥 위로점프시 버그해결용)
						moveLock = false; // 점프가 끝나면 다시 이동할수있게 Lock해제
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
