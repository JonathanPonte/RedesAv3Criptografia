package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algoritimos.CifraCesar;
import algoritimos.CifraXOR;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class principal extends JFrame {

	private JPanel contentPane;
	private BufferedImage img = null;
	private String pasta = null;
	private boolean foiClicado = false;
	private JTextField chave;
	private BufferedImage novaImgCrip = null;
	private int chaveAleatoria = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		setTitle("Trabalho Redes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JLabel mural = new JLabel();
		mural.setHorizontalAlignment(SwingConstants.CENTER);
		mural.setBounds(98, 69, 704, 414);
		contentPane.add(mural);
		setLocationRelativeTo(null);
		setResizable(false);

		chave = new JTextField();
		chave.setBounds(198, 523, 57, 19);
		contentPane.add(chave);
		chave.setColumns(10);
		chave.setVisible(false);

		JButton btnNewButton = new JButton("Cifra de cesar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CifraCesar cifraCesar = new CifraCesar();
				try {

					// fazer logica para poder incriptar novamente somente depois de decriptografar
					// imagem anterior

					if (pasta != null) {
						if (!foiClicado) {
							Random random = new Random();
							chaveAleatoria = random.nextInt(255);
							foiClicado = true;
							novaImgCrip = converParaImagem(cifraCesar.criptografar(pasta, chaveAleatoria),
									img.getWidth(), img.getHeight());
							mural.setIcon(new ImageIcon(novaImgCrip));
						} else {
							foiClicado = false;
							novaImgCrip = converParaImagem(cifraCesar.desCriptografar(novaImgCrip, chaveAleatoria),
									img.getWidth(), img.getHeight());
							mural.setIcon(new ImageIcon(novaImgCrip));
						}

					}

				} catch (IOException e1) {
					System.out.println("deu ruim no cesar");
				}
			}
		});
		btnNewButton.setBounds(36, 520, 151, 25);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cifra de XOR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CifraXOR cifraXOR = new CifraXOR();

				try {
					if (pasta != null) {
						if (!foiClicado) {
							foiClicado = true;
							novaImgCrip = converParaImagem(cifraXOR.criptografar(pasta, "10111101"), img.getWidth(),
									img.getHeight());
							mural.setIcon(new ImageIcon(novaImgCrip));
						} else {
							foiClicado = false;
							novaImgCrip = converParaImagem(cifraXOR.desCriptografar(novaImgCrip, "10111101"),
									img.getWidth(), img.getHeight());
							mural.setIcon(new ImageIcon(novaImgCrip));
						}

					}
				} catch (IOException e) {
					System.out.println("deu ruim XOR");
				}

			}
		});
		btnNewButton_1.setBounds(410, 520, 114, 25);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("3");
		btnNewButton_2.setBounds(743, 520, 114, 25);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Adicionar imagem");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValor = fileChooser.showOpenDialog(fileChooser);
				
				// String pasta = null;
				
				if (returnValor == JFileChooser.APPROVE_OPTION) {
					pasta = fileChooser.getSelectedFile().getAbsolutePath();
					try {
						img = ImageIO.read(new File(pasta));
					} catch (Exception e2) {
						// TODO: handle exception
					}
					Image novaImg = null;
					if (img.getHeight() > 800) {
						novaImg = img.getScaledInstance(800, img.getWidth(), Image.SCALE_DEFAULT);
					}

					if (img.getWidth() > 500) {
						novaImg = img.getScaledInstance(img.getHeight(), 400, Image.SCALE_DEFAULT);
					}

					if (novaImg == null) {
						mural.setIcon(new ImageIcon(img));
					} else {
						mural.setIcon(new ImageIcon(novaImg));
					}

				} else {
					System.out.println("Deu ruim");
				}

			}
		});
		btnNewButton_3.setBounds(36, 12, 165, 25);
		contentPane.add(btnNewButton_3);

		JLabel lblChave = new JLabel("Chave");
		lblChave.setBounds(198, 505, 66, 15);
		contentPane.add(lblChave);
		lblChave.setVisible(false);
	}

	public BufferedImage converParaImagem(Color[][] pixel, int largura, int altura) {
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
