package Modelo;
public class Prédio{
    private String [] andares;
    
    public Prédio(String [] andares){
        this.andares = andares;
    };

    public String getAndar(int i){return andares[i];}
    
    public int quantAndares(){return andares.length;}
    
    
}
