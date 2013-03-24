package Visualização;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Imagem extends Desenhavel{
	protected ImageIcon img;
	
	public Imagem(String nome, int x, int y){
		this.img = new ImageIcon(nome);
		this.x = x;
		this.y = y;
                largura = img.getIconWidth();
                altura = img.getIconHeight();
	}
	
	public String toString(){
		return "Img<"+x+", "+y+">";
	}

        public void setSize(int largura, int altura){
            this.largura = largura;
            this.altura = altura;
        }

	@Override
	public void desenhar(Graphics2D g) {
		g.translate(x,y);
		g.drawImage(img.getImage(), 0, 0, largura, altura, null);
	}
	
}
