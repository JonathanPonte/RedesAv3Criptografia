package algoritimos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CifraXOR {

	public Color[][] criptografar(String caminho, String chaveBinaria) throws IOException {
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

				pixel[i][j] = cifraXOR(r, g, b, chaveBinaria);

			}
		}

		return pixel;
	}

	public Color[][] desCriptografar(BufferedImage img,  String chaveBinaria) throws IOException {

		Integer largura = img.getWidth();
		Integer altura = img.getHeight();

		Color[][] pixel = new Color[largura][altura];
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				Integer cor = img.getRGB(i, j);
				Integer r = (cor & 0xff0000) >> 16;
				Integer g = (cor & 0xff00) >> 8;
				Integer b = cor & 0xff;

				pixel[i][j] = cifraXOR(r, g, b, chaveBinaria);

			}
		}

		return pixel;
	}

	public Color cifraXOR(Integer r, Integer g, Integer b, String chavCrip) {

		r = r ^ Integer.parseInt(chavCrip, 2);
		g = g ^ Integer.parseInt(chavCrip, 2);
		b = b ^ Integer.parseInt(chavCrip, 2);

		return new Color(r, g, b);
	}


}
