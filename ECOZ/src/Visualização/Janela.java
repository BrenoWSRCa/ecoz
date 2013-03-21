package Visualização;

import Controle.ControladorDeElevador;
import Modelo.Ações;
import Modelo.Direção;
import Modelo.IElevador;
import Modelo.Prédio;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class Janela extends JFrame implements Observer {

    Tela tela;
    Modelo.IElevador elevador;
    Animacao elevador_anim;
    Imagem predio;

    public Janela(String nome) {
        super(nome);
        this.setSize(Constantes.LARGURA_DA_JANELA, Constantes.ALTURA_DA_JANELA);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        tela = new Tela();
        predio = new Imagem(Constantes.PREDIO, 0, 0);
        predio.setSize(Constantes.LARGURA_DA_JANELA, Constantes.ALTURA_DA_JANELA);
        elevador_anim = new Animacao(Constantes.ELEVADOR, 4, 51, 370, 316);
        tela.adiciona_Desenhavel(predio);
        tela.adiciona_Desenhavel(elevador_anim);
        String andares[] = {"Térreo", "Primeiro", "Segundo", "Terceiro"};
        Prédio predio = new Prédio(andares);
        elevador = new Modelo.Elevador(predio);
        elevador.addObserver(this);
        new ControladorDeElevador(elevador, predio, "Teste").start();
        this.add(tela);
        tela.repaint();
    }

    public void subir_elevador(int indice) { // indice = quantidade de andares
        int i = Constantes.DISTANCIA_ANDARES / Constantes.VEL_DE_SUBIR_ANDAR,
                y = this.tela.pega_Desenhavel(indice).y - Constantes.DISTANCIA_ANDARES;

        while (i > 0) {
            this.tela.repaint();
            this.tela.pega_Desenhavel(indice).y -= Constantes.VEL_DE_SUBIR_ANDAR;
            i--;
            try {
                Thread.currentThread().sleep(Constantes.ATUALIZACAO_DE_QUADRO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.tela.pega_Desenhavel(indice).y = y;
        this.tela.repaint();
    }

    public void desce_elevador(int indice) { // indice = quantidade de andares
        int i = Constantes.DISTANCIA_ANDARES / Constantes.VEL_DE_DECER_ANDAR,
                y = this.tela.pega_Desenhavel(indice).y + Constantes.DISTANCIA_ANDARES;

        while (i > 0) {
            this.tela.repaint();
            this.tela.pega_Desenhavel(indice).y += Constantes.VEL_DE_DECER_ANDAR;
            i--;
            try {
                Thread.currentThread().sleep(Constantes.ATUALIZACAO_DE_QUADRO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.tela.pega_Desenhavel(indice).y = y;
        this.tela.repaint();
    }

    public void embarca_elevador(Animacao elevador) {
        int delta = Constantes.TEMPO_DE_EMBARQUE / Constantes.NUM_DE_QUADROS_EMBARQUE;
        int i = 0;
        elevador.escolhe_quadro(Constantes.QUADRO_EMBARQUE);
        this.tela.repaint();
        while (i < Constantes.NUM_DE_QUADROS_EMBARQUE - 1) {
            try {
                Thread.currentThread().sleep(delta);
            } catch (InterruptedException e) {
            }
            elevador.adianta_quadro();
            this.tela.repaint();
            System.out.format("Abrindo porta %d", i);
            this.tela.repaint();
            i++;
        }
    }

    public void desembarca_elevador(Animacao elevador) {
        int delta = Constantes.TEMPO_DE_DESEMBARQUE / Constantes.NUM_DE_QUADROS_DESEMBARQUE;
        int i = 0;
        elevador.escolhe_quadro(Constantes.QUADRO_DESEMBARQUE);
        this.tela.repaint();

        while (i < Constantes.NUM_DE_QUADROS_DESEMBARQUE - 1) {
            try {
                Thread.currentThread().sleep(delta);
            } catch (InterruptedException e) {
            }
            elevador.volta_quadro();
            this.tela.repaint();
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Janela janela = new Janela("ECOZ");
        /*while (true) {
            for (int i = 0; i < 2; i++) {
                janela.subir_elevador(1);
                janela.embarca_elevador(janela.elevador_anim);
                janela.sobe_elevador(1);
                janela.desembarca_elevador(janela.elevador_anim);
                Thread.currentThread().sleep(500);
            }
            for (int i = 0; i < 4; i++) {
                janela.desce_elevador(1);
                Thread.currentThread().sleep(500);
            }
        }*/
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        IElevador elev = (IElevador) arg0;

        switch (Ações.descobreAção(arg1)) {
            case SOBE:
                subir_elevador(1);
                break;
            case DESCE:
                desce_elevador(1);
                break;
            case EMBARQUE:
                embarca_elevador(elevador_anim);
        }
    }
}
