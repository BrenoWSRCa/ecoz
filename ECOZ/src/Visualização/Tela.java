package Visualização;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class Tela extends JPanel {
	
	private List<Desenhavel> listaDeImagens;
        float escala_largura, escala_altura;

    public Tela() {
    	listaDeImagens = new LinkedList<Desenhavel>();
        this.escala_largura = 1;
        this.escala_altura = 1;
    	this.setSize(600, 500);
    }
 
    public void adiciona_Desenhavel(Desenhavel d){
        listaDeImagens.add(d);
    }
    
    public void adiciona_Imagem(String i, int x, int y){
        listaDeImagens.add(new Imagem(i, x, y));
    }
    
    public void adiciona_Animacao(String i, int quadros, int largura, int x, int y){
        listaDeImagens.add(new Animacao(i,quadros, x, y));
    }
    
    Imagem[] vetor_de_Imagens(){
    	return listaDeImagens.toArray(new Imagem[0]);
    }
    
    public void remove_Imagem(int indice){
    	if(indice >= 0 && indice < listaDeImagens.size()){
    		listaDeImagens.remove(indice);
    	}
    	System.out.format("Tentando remover imagem de posição inválida de vetor de imagens!");
    }
    
    public Desenhavel pega_Desenhavel(int indice){
    	if(indice >= 0 && indice < listaDeImagens.size()){
    		return listaDeImagens.get(indice);
    	}
    	System.out.format("Tentando pegar imagem de posição inválida de vetor de imagens!");
    	return null;
    }
    public void escala(float escala_largura, float escala_altura){
        this.escala_altura = escala_altura;
        this.escala_largura = escala_largura;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(escala_largura, escala_altura);
        for(Desenhavel i: listaDeImagens)
            i.desenhar(g2d);
    }
    
    
}