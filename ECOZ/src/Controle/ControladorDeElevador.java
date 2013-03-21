package Controle;

import Modelo.Prédio;
import Modelo.IElevador;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorDeElevador extends Thread{
	private IElevador elevador;
        private Prédio predio;
	private boolean[] andaresChamados;
	
	public ControladorDeElevador(IElevador elevador, Prédio predio, String nome){
		super(nome);
                this.predio = predio;
		andaresChamados = new boolean[predio.quantAndares()];
		this.elevador = elevador;
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
                Logger.getLogger(ControladorDeElevador.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
	//}
}
