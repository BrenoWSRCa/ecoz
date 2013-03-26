package Controle;

import Modelo.Prédio;
import Modelo.IElevador;

public class ControladorDeElevador{

    private IElevador elevador;
    private Prédio predio;
    private boolean[] andaresChamados;

    public ControladorDeElevador(IElevador elevador, Prédio predio, String nome) {
        //super(nome);
        this.predio = predio;
        andaresChamados = new boolean[predio.quantAndares()];
        this.elevador = elevador;
    }

    private void elevadorParaAndar(int andar) {
        while (andar != elevador.qual_andar()) {
            if (andar < elevador.qual_andar()) {
                elevador.desceAndar();
            } else {
                elevador.sobeAndar();
            }
        }
    }

    public void chamaEmbarcar(int andar) {
        elevadorParaAndar(andar);
        //elevador.abre_porta();
        elevador.incrementa_quant_embarcados();
        //elevador.fecha_porta();
    }

    public void chamaDesembarcar(int andar) {
        elevadorParaAndar(andar);
        //elevador.abre_porta();
        elevador.decrementa_quant_embarcados();
        //elevador.fecha_porta();
    }

    public void abrirPorta(){
        elevador.abre_porta();
    }
    
    public void fecharPorta(){
        elevador.fecha_porta();
    }
    
}
