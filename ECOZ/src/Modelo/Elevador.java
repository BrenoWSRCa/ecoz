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
        this.prédio = predio;
        andarAtual = 0;
        num_pessoas = 0;
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
    public void chama(int i) {
        if ((i - andarAtual) > 0) {
            direçãoAtual = Direção.SUBINDO;
            andarAtual++;
        } else {
            if ((i - andarAtual) < 0) {
                direçãoAtual = Direção.DESCENDO;
                andarAtual--;
            } else {
                direçãoAtual = Direção.PARADO;
            }
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public void abre_porta() {
        setChanged();
        notifyObservers();
    }

    @Override
    public void fecha_porta() {
        setChanged();
        notifyObservers();
    }

    public void desliga() {
        setChanged();
        notifyObservers();
        controlador.interrupt();
    }

    @Override
    public int quant_embarcados() {
        return num_pessoas;
    }

    @Override
    public void incrementa_quant_embarcados() {
        num_pessoas++;
    }

    @Override
    public void decrementa_quant_embarcados() {
        num_pessoas--;
    }
}
