package test;

import javax.swing.ImageIcon;

public class Test extends Thread{
	
	MyBg bg;
	
	
	public Test(MyBg bg) {
		this.bg = bg;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			// 3���Ŀ� MyBg�� ��� �ִµ� JPanel�� �̹��� �������� ü����
			bg.�̹�������(1);
			Thread.sleep(3000);
			bg.�̹�������(2);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
