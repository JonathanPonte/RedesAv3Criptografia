package algoritimos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CifraCesar {

	public Color[][] obterMatrizColor(String caminho, int chaveCrip, int incrip) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(caminho));

		System.out.println("Primeiro" + bufferedImage.getWidth() + "-" + bufferedImage.getHeight());

		Integer largura = bufferedImage.getWidth();
		Integer altura = bufferedImage.getHeight();

		Color[][] pixel = new Color[largura][altura];
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				Integer cor = bufferedImage.getRGB(i, j);
				Integer r = (cor & 0x00ff0000) >> 16;
				Integer g = (cor & 0x0000ff00) >> 8;
				Integer b = cor & 0x000000ff;
				
				if(incrip == 1) {
					pixel[i][j] = cifraCesar(r, g, b, chaveCrip);
				}else {
					pixel[i][j] = descriptografar(r, g, b, chaveCrip);
				}
				
			}
		}

		return pixel;
	}

	public Color cifraCesar(Integer r, Integer g, Integer b, int chavCrip) {

		r = (r + chavCrip) % 256;
		g = (g + chavCrip) % 256;
		b = (b + chavCrip) % 256;

		return new Color(r, g, b);
	}

	public Color descriptografar(Integer r, Integer g, Integer b, int chavCrip) {

		r = r - chavCrip;
		g = g - chavCrip;
		b = b - chavCrip;

		if (r < 0) {
			r += 256;
		}

		if (g < 0) {
			g += 256;
		}

		if (b < 0) {
			b += 256;
		}

		return new Color(r, g, b);
	}

	public BufferedImage converForImage(Color[][] pixel, int largura, int altura) {
		BufferedImage img = null;
		img = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

		System.out.println(largura + "-" + altura);

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {

				img.setRGB(i, j, pixel[i][j].getRGB());

			}
		}

		return img;
	}

}
