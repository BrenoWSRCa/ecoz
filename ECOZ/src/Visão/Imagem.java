package Visão;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Imagem extends Desenhavel{
	protected ImageIcon img;
	
	public Imagem(String nome, int x, int y){
		this.img = new ImageIcon(nome);
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "Img<"+x+", "+y+">";
	}

	@Override
	public void desenhar(Graphics2D g) {
		g.translate(x,y);
		g.drawImage(img.getImage(), 0, 0, null);
	}
	
}
