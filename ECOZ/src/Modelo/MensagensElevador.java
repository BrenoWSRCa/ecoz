/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author breno
 */
public enum MensagensElevador {
    EMBARQUE,
    DESEMBARQUE,
    SOBE,
    DESCE,
    ABRIR_PORTA,
    FECHAR_PORTA,
    DESLIGA;
    
    public static MensagensElevador descobreAção(Object obj){
        assert (obj instanceof MensagensElevador);
        return (MensagensElevador) obj;
        
    }
};
