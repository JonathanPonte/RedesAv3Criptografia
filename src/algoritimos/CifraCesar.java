package algoritimos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CifraCesar {
	public Color[][] obterMatrizColor(String caminho) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(caminho));
		
		Integer largura = bufferedImage.getWidth();
		Integer altura = bufferedImage.getHeight();
		
		Color[][] pixel = new Color[largura][altura];
		for(int i = 0 ; i < largura; i++) {
			for(int j = 0; j < altura; j++) {
				Integer cor = bufferedImage.getRGB(i, j);
				Integer r = (cor & 0x00ff0000) >> 16;
				Integer g = (cor & 0x0000ff00) >> 8;
				Integer b = cor & 0x000000ff;
				pixel[i][j] = new Color(r, g, b);
			}
		}
		
		return pixel;
	}
}
