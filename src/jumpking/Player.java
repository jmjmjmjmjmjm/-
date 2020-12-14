package jumpking;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import test.MyBg;

public class Player extends JLabel {
	private Player player = this;
	private ImageIcon right_run, right_down, right, right_charging, right_jump, // 오른쪽
			left_run, left_down, left, left_charging, left_jump, // 왼쪽
			charging, RJ, LJ; // 기모으기

	public boolean isRight = false; // 상태(옆에누르고잇는지)
	public boolean isLeft = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isUp2 = false;
	public boolean isRJ = false;
	public boolean isLJ = false;
	public int x = 500, y = 700; // 현재위치
	public int j = 0;
	public int energy = 0; // 기모으는것
	public int ly = 0; // 왼쪽에서 점프준비인지 오른쪽에서점프준비인지
	public String status = "오른쪽"; // 어느곳을 바라보고있는지
	Bg bg;
	int i = 0; // 맵변경을위한 변수

	public Player(Bg bg) {

		right_run = new ImageIcon("images/오른쪽달리기.png");
		right_down = new ImageIcon("images/오른쪽떨어지기.png");
		right = new ImageIcon("images/오른쪽바라보기.png");
		right_charging = new ImageIcon("images/오른쪽에서 차징.png");
		right_jump = new ImageIcon("images/오른쪽점프.png");
		left_run = new ImageIcon("images/왼쪽달리기.png");
		left_down = new ImageIcon("images/왼쪽떨어지기.png");
		left = new ImageIcon("images/왼쪽바라보기.png");
		left_charging = new ImageIcon("images/왼쪽에서 차징.png");
		left_jump = new ImageIcon("images/왼쪽점프.png");

		charging = new ImageIcon("images/차징풀.png");

		setIcon(right);
		setSize(100, 100);
		setLocation(x, y);
		this.bg = bg; // 배경을 바꾸기위해

	}

	public void moveRight() { // 키보드 오른쪽키

		
		//2번째놈의 isRight == true
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					//1번째놈의 isRight == ture
					isRight = true;
					while (isRight) {
						x = x + 2;

						setLocation(x, y); // 내부에 repaint()가 있음
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
			status = "오른쪽";
		}
		ly = 0;
	}

	public void moveLeft() { // 키보드 왼쪽키

		if (isLeft == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isLeft = true;
					while (isLeft) {
						x = x - 2;

						setLocation(x, y); // 내부에 repaint()가 있음
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
			status = "왼쪽";
		}
		ly = 1;
	}

	public void change(int height) { // 남은 점프범위를 받아서 여기서 실행
		if (i < 4)
			i++;
		Bg.이미지변경(i);// 맵을 바꾸기위해 부름
		for (y = 900; height < 300; height++) {
			try {
				y--;
				setLocation(x, y);
				Thread.sleep(1);
				if (status == "오른쪽") { // 오른쪽 바라볼시 오른쪽으로점프
					setIcon(right_jump);
				} else { // 아니면 왼쪽으로점프
					setIcon(left_jump);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void isUp() { // 키보드 위키

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
									setLocation(x, y + 10);
									setIcon(right_charging);
									status = "오른쪽";
								}
							} else {
								if (energy >= 1) {
									setLocation(x, y + 10);
									setIcon(left_charging);
									status = "왼쪽";
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

	public void isUp2() {
		if (isUp2 == true) { // 키보드 위키 입력후 손을땟을때
			new Thread(new Runnable() {
				@Override
				public void run() {

					System.out.println("점프중");
					if (energy > 3) {

						for (int i = 0; i < 300; i++) {
							isUp = false;
							try {
								if (status == "오른쪽") { // 오른쪽 바라볼시 오른쪽으로점프
									setIcon(right_jump);
								} else {
									setIcon(left_jump);
								}
								y--;
								setLocation(x, y);
								Thread.sleep(1);

								if (y < 0) { // 맵전환을 넘기는 함수
									change(300 - i);
									break;
								}

							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if (status == "오른쪽") {
							setIcon(right);
							energy = 0;
						} else {
							setIcon(left);
							energy = 0;
						}

					}
					if (energy >= 0) {

						for (int i = 0; i < 200; i++) {
							try {
								y--;
								setLocation(x, y);
								if (status == "오른쪽") {
									setIcon(right_jump);
								} else {
									setIcon(left);
								}

								Thread.sleep(1);

								if (y < 0) {
									change(300 - i);
									break;
								}
								energy = -1;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if (status == "오른쪽") {
							setIcon(right);
						} else {
							setIcon(left);
						}

					}
					isUp2 = false;
				}

			}).start();
		}

	}

	public void isRJ() {
		if (isRJ = true) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					if (energy > 3) {
						for (int i = 0; i < 150; i++) {
							x++;
							y = y - 2;
							setLocation(x, y);
						}
					}

				}
			});
		}
	}

	public void isLJ() {
		if (isLJ = true) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					if (energy > 3) {
						for (int i = 0; i < 150; i++) {
							x--;
							y = y - 2;
							setLocation(x, y);
						}
					}

				}
			});
		}
	}

}
