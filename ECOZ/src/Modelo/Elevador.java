package Modelo;

import Controle.Controlador;

public class Elevador extends IElevador{
        protected Predio predio;
        protected int andarAtual;
	protected Direção direçãoAtual;
	protected boolean porta; // porta é true quando está aberta e false quando fechada
	
	private Controlador controlador;
	
	public Elevador(Predio predio){
		super();
                this.predio = predio;
                andarAtual = 0;
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
                direçãoAtual = Direção.SUBINDO;
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
		controlador.fecha_porta();
	}

	public void desliga(){
                setChanged();
                notifyObservers();
		controlador.interrupt();
	}
}
