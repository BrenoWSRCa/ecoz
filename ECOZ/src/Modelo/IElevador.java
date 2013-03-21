package Modelo;
import java.util.Observable;
import java.util.Observer;

public abstract class IElevador extends Observable{
	
	public abstract int qual_andar();

	public abstract Direção qual_direção();
	
	public abstract boolean porta_esta_aberta();
	
	public abstract void chama(int andar); //chama elevador para este andar
	
	public abstract void abre_porta();
	
	public abstract void fecha_porta();
	
	public abstract void desliga();
        
        public void addObserver(Observer obs){
            super.addObserver(obs);
        };
	
}
