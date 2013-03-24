package Visualização;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Animacao extends Desenhavel {
	protected int quadros, i;
	ImageIcon img;
	Animacao(String n, int quadros, int x, int y){
		img = new ImageIcon(n);
		this.x = x;
		this.y = y;
		this.quadros = quadros;
		this.largura = img.getIconWidth()/quadros;
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
		g.setClip(0, 0, largura, img.getIconHeight());
		g.drawImage(img.getImage(), -i*largura, 0, null);

	}

    @Override
    public void setSize(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.largura = this.largura/this.quadros;
    }
}
