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
			// 3초후에 MyBg가 들고 있는데 JPanel의 이미지 아이콘을 체인지
			bg.이미지변경(1);
			Thread.sleep(3000);
			bg.이미지변경(2);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
