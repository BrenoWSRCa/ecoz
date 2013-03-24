package Visualização;

import java.awt.Graphics2D;

public abstract class Desenhavel {
	protected int x,y, largura = 1, altura = 1;
	
	public abstract void desenhar(Graphics2D g);

        public abstract void setSize(int width, int height);
        
        public int retornaAltura(){return altura;}
        
        public int retornaLargura() { return largura;}
}
