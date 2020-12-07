package jumpking;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PixelCheck implements Runnable {
	private int playerX, playerY;
	private int red, green, blue, alpha; // RGB,���� ����
	private String pixelColor;
	private String imageUrl; // �̹��� �ּ�
	private Player player; // �÷��̾� ���ؽ�Ʈ ���

	public PixelCheck(Player player, String imageUrl) {
		this.player = player;
		this.imageUrl = imageUrl;
	}

	@Override
	public void run() {
		while (true) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File("����ŷ�̹���/��/����.jpg")); // ���ϰ��
//				int x = image.getWidth(null); // �̹��� ���� ��ü 
//				int y = image.getHeight(null); // �̹��� ���� ��ü

				playerX = player.getX() + 13;
				playerY = player.getY() + 40;

				Color color = new Color(image.getRGB(playerX, playerY)); // �ش���ǥ �÷����

				red = color.getRed(); // ������ ����
				green = color.getGreen();// �ʷϻ� ����
				blue = color.getBlue(); // �Ķ��� ����
				alpha = color.getAlpha(); // ���� ����

//				System.out.println(" X ��ǥ : " +playerX + " Y ��ǥ : " + playerY); //���� �÷��̾��� ��ġ ���
//				System.out.println("���� : "+red+" ���� : "+ green + "�Ķ� : " + blue); // �������
				if (red == 255 && green == 255 && blue == 255) {
					pixelColor = "���";
					player.Gravity = true;
//					System.out.println("���");
				} else if (red == 0 && green == 0 && blue == 0) {
					pixelColor = "������";
					player.Gravity = false;
//					System.out.println("������");
				}
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
