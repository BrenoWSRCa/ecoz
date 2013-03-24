package Visualização;

import Controle.ControladorDeElevador;
import Modelo.MensagensElevador;
import Modelo.IElevador;
import Modelo.Prédio;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class Janela extends JFrame implements Observer {

    Tela tela;
    Modelo.IElevador elevador;
    Animacao elevador_anim;
    Imagem prédio_anim;

    public Janela(String nome) {
        super(nome);
        this.setSize(Constantes.LARGURA_DA_JANELA, Constantes.ALTURA_DA_JANELA);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        tela = new Tela();
        prédio_anim = new Imagem(Constantes.PREDIO, 0, 0);

        elevador_anim = new Animacao(Constantes.ELEVADOR, 23, 513, 457);
        
        tela.adiciona_Desenhavel(prédio_anim);
        tela.adiciona_Desenhavel(elevador_anim);
        System.out.println();
        tela.escala(((float)Constantes.LARGURA_DA_JANELA)/prédio_anim.retornaLargura(),
                    ((float)Constantes.ALTURA_DA_JANELA)/prédio_anim.retornaAltura());
        String andares[] = {"Térreo", "Primeiro", "Segundo", "Terceiro"};
        Prédio prédio = new Prédio(andares);
        elevador = new Modelo.Elevador(prédio);
        elevador.addObserver(this);
        new ControladorDeElevador(elevador, prédio, "Teste").start();
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
        System.out.println("Subindo");
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
        System.out.println("Descendo\n");
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
            } catch (InterruptedException e) {}
            elevador.adianta_quadro();
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
        //janela.subir_elevador(1);
        //janela.desce_elevador(1);
        //janela.subir_elevador(1);
        //janela.subir_elevador(1);
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

        switch (MensagensElevador.descobreAção(arg1)) {
            case SOBE:
                subir_elevador(1);
                break;
            case DESCE:
                desce_elevador(1);
                break;
            case EMBARQUE:
                embarca_elevador(elevador_anim);
                break;
            case DESEMBARQUE:
                desembarca_elevador(elevador_anim);
                break;
            case ABRIR_PORTA:
                elev.abre_porta();
                break;
            case FECHAR_PORTA:
                elev.fecha_porta();
                break;
            case DESLIGA:
                elev.desliga();
        }

    }
}
