package Visão;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Animacao extends Desenhavel {
	protected int quadros, largura, i;
	ImageIcon img;
	Animacao(String n, int quadros, int largura, int x, int y){
		img = new ImageIcon(n);
		this.x = x;
		this.y = y;
		this.quadros = quadros;
		this.largura = largura;
		i = 0;
	}
	
	void adianta_quadro(){if(i < quadros - 1) i++; else i = 0;}

	void volta_quadro(){if(i > 0) i--; else i = quadros -1;}
	
	void escolhe_quadro(int i){
		if(i>=0 && i < quadros) this.i = i;
	}

	int quadro(){return i;}

	@Override
	public void desenhar(Graphics2D g) {
		g.translate(x, y);
		g.setClip(0, 0, largura, img.getIconWidth());
		g.drawImage(img.getImage(), -i*largura, 0, null);

	}

}
