package Modelo;
import java.util.Observable;

public abstract class IElevador extends Observable{
        public final int MAX_EMBARCADOS = 1;

	public abstract int qual_andar();

	public abstract Direção qual_direção();
	
	public abstract boolean porta_esta_aberta();
        
        public abstract int quant_embarcados();
        
        public abstract void incrementa_quant_embarcados();
        
        public abstract void decrementa_quant_embarcados();
        
        public abstract void sobeAndar();
        
        public abstract void desceAndar();
	
	public abstract void abre_porta();
	
	public abstract void fecha_porta();
	
	public abstract void desliga();
	
}
