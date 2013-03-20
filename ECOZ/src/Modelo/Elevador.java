package Modelo;

import java.util.Observer;

public class Elevador extends IElevador{
	protected Andar andarAtual;
	protected Direção direçãoAtual;
	protected boolean porta; // porta é true quando está aberta e false quando fechada
	
	private Controlador controlador;
	
	public Elevador(){
		super();
		andarAtual = Andar.Térreo;
		direçãoAtual = Direção.PARADO;
		porta = false;//fechada
		controlador = new Controlador(this, "Simulação do Elevador");
		controlador.start();
	}
	
	public Andar qual_andar() {
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
	public void chama(Andar andar) {
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
