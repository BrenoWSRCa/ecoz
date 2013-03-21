/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author breno
 */
public enum Ações {
    EMBARQUE,
    DESEMBARQUE,
    SOBE,
    DESCE,
    ABRIR_PORTA,
    FECHAR_PORTA,
    DESLIGA;
    
    public static Ações descobreAção(Object obj){
        assert (obj instanceof Ações);
        return (Ações) obj;
        
    }
};
