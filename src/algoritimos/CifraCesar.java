package algoritimos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CifraCesar {

	public Color[][] criptografar(String caminho, int chaveCrip, int incrip) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(caminho));

		Integer largura = bufferedImage.getWidth();
		Integer altura = bufferedImage.getHeight();

		Color[][] pixel = new Color[largura][altura];
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				Integer cor = bufferedImage.getRGB(i, j);
				Integer r = (cor & 0xff0000) >> 16;
				Integer g = (cor & 0xff00) >> 8;
				Integer b = cor & 0xff;

				pixel[i][j] = cifraCesar(r, g, b, chaveCrip);

			}
		}

		return pixel;
	}
	
	
	public Color[][] desCriptografar(BufferedImage img, int chaveCrip, int incrip) throws IOException {
		
		Integer largura = img.getWidth();
		Integer altura = img.getHeight();

		Color[][] pixel = new Color[largura][altura];
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				Integer cor = img.getRGB(i, j);
				Integer r = (cor & 0xff0000) >> 16;
				Integer g = (cor & 0xff00) >> 8;
				Integer b = cor & 0xff;

				pixel[i][j] = desCriptografar(r, g, b, chaveCrip);

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

	public Color desCriptografar(Integer r, Integer g, Integer b, int chavCrip) {
		
		r = r - chavCrip;
		g = g - chavCrip;
		b = b - chavCrip;

		if (r < 0) {
			r = r + 256;
		}

		if (g < 0) {
			g = g + 256;
		}

		if (b < 0) {
			b = b + 256;
		}

		return new Color(r, g, b);
	}



}
