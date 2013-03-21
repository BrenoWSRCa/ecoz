package Modelo;

import Controle.ControladorDeElevador;

public class Elevador extends IElevador {

    protected Prédio prédio;
    protected int andarAtual;
    protected Direção direçãoAtual;
    protected boolean porta; // porta é true quando está aberta e false quando fechada
    private int num_pessoas;
    private ControladorDeElevador controlador;

    public Elevador(Prédio predio) {
        super();
        this.prédio  = predio;
        andarAtual   = 0;
        num_pessoas  = 0;
        direçãoAtual = Direção.PARADO;
        porta = false;//fechada
    }

    @Override
    public int qual_andar() {
        return andarAtual;
    }

    @Override
    public Direção qual_direção() {
        return direçãoAtual;
    }

    @Override
    public boolean porta_esta_aberta() {
        return porta;
    }

    @Override
    public void abre_porta() {
        setChanged();
        notifyObservers(MensagensElevador.ABRIR_PORTA);
    }

    @Override
    public void fecha_porta() {
        setChanged();
        notifyObservers(MensagensElevador.FECHAR_PORTA);
    }

    public void desliga() {
        setChanged();
        notifyObservers(MensagensElevador.DESLIGA);
        controlador.interrupt();
    }

    @Override
    public int quant_embarcados() {
        return num_pessoas;
    }

    @Override
    public void incrementa_quant_embarcados() {
        setChanged();
        num_pessoas++;
        notifyObservers(MensagensElevador.EMBARQUE);
    }

    @Override
    public void decrementa_quant_embarcados() {
        setChanged();
        num_pessoas--;
        notifyObservers(MensagensElevador.DESEMBARQUE);
    }

    @Override
    public void sobeAndar() {
        setChanged();
        this.andarAtual++;
        notifyObservers(MensagensElevador.SOBE);
    }

    @Override
    public void desceAndar() {
        setChanged();
        this.andarAtual--;
        notifyObservers(MensagensElevador.DESCE);
    }
}
