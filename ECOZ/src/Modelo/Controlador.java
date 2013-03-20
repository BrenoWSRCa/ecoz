package Modelo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador extends Thread{
	private IElevador elevador;
	private boolean[] andaresChamados;
	
	public Controlador(IElevador elevador, String nome){
		super(nome);
		andaresChamados = new boolean[Andar.values().length];
		this.elevador = elevador;
	}

	public void fecha_porta() {
		// TODO Auto-generated method stub
		
	}

	public void abre_porta() {
		// TODO Auto-generated method stub
		
	}

	public void chama(Andar andar) {
		elevador.chama(andar);
		
	}
	
	public void run(){
            //while(true){
            try {
                chama(Andar.Segundo);
                Thread.currentThread().sleep(Math.round(Math.random()*101));
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
	//}
}
