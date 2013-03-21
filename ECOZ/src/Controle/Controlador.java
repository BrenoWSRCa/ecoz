package Controle;

import Modelo.Predio;
import Modelo.IElevador;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador extends Thread{
	private IElevador elevador;
        private Predio predio;
	private boolean[] andaresChamados;
	
	public Controlador(IElevador elevador, Predio predio, String nome){
		super(nome);
                this.predio = predio;
		andaresChamados = new boolean[predio.quantAndares()];
		this.elevador = elevador;
	}

	public void fecha_porta() {
		elevador.fecha_porta();
		
	}

	public void abre_porta() {
		elevador.abre_porta();
		
	}

	public void chama(int andar) {
		elevador.chama(andar);
		
	}
	
	public void run(){
            //while(true){
            try {
                chama(new Random().nextInt(predio.quantAndares()));
                Thread.currentThread().sleep(Math.round(Math.random()*101));
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
	//}
}
