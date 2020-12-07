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
	private int red, green, blue, alpha; // RGB,투명도 변수
	private String pixelColor;
	private String imageUrl; // 이미지 주소
	private Player player; // 플레이어 콘텍스트 담기

	public PixelCheck(Player player, String imageUrl) {
		this.player = player;
		this.imageUrl = imageUrl;
	}

	@Override
	public void run() {
		while (true) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File("점프킹이미지/맵/투명도.jpg")); // 파일경로
//				int x = image.getWidth(null); // 이미지 가로 전체 
//				int y = image.getHeight(null); // 이미지 세로 전체

				playerX = player.getX() + 13;
				playerY = player.getY() + 40;

				Color color = new Color(image.getRGB(playerX, playerY)); // 해당좌표 컬러담기

				red = color.getRed(); // 빨강색 저장
				green = color.getGreen();// 초록색 저장
				blue = color.getBlue(); // 파랑색 저장
				alpha = color.getAlpha(); // 투명도 저장

//				System.out.println(" X 좌표 : " +playerX + " Y 좌표 : " + playerY); //현재 플레이어의 위치 출력
//				System.out.println("빨강 : "+red+" 연두 : "+ green + "파랑 : " + blue); // 색깔출력
				if (red == 255 && green == 255 && blue == 255) {
					pixelColor = "흰색";
					player.Gravity = true;
//					System.out.println("흰색");
				} else if (red == 0 && green == 0 && blue == 0) {
					pixelColor = "검은색";
					player.Gravity = false;
//					System.out.println("검은색");
				}
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
