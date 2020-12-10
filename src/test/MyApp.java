package test;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyApp extends JFrame{

	MyApp myApp = this;
	Container c;
	MyBg bg;
	
	public void ¾È´¨() {
		
	}
	
	public MyApp() {
		c = getContentPane();
		bg = new MyBg();
		new Test(bg).start();
		c.add(bg);
		setTitle("Á¦¸ñ");
		setSize(300,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyApp();
	}
}
