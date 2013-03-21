package Visualização;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Imagem extends Desenhavel{
	protected ImageIcon img;
        private int width, height;
	
	public Imagem(String nome, int x, int y){
		this.img = new ImageIcon(nome);
		this.x = x;
		this.y = y;
                width = img.getIconWidth();
                height = img.getIconHeight();
	}
	
	public String toString(){
		return "Img<"+x+", "+y+">";
	}

        public void setSize(int width, int height){
            this.width = width;
            this.height = height;
        }

	@Override
	public void desenhar(Graphics2D g) {
		g.translate(x,y);
		g.drawImage(img.getImage(), 0, 0, width, height, null);
	}
	
}
