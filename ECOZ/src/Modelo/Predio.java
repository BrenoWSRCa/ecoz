package Modelo;
public class Predio{
    private String [] andares;
    
    public Predio(String [] andares){
        this.andares = andares;
    };

    public String getAndar(int i){return andares[i];}
    
    public int quantAndares(){return andares.length;}
    
    
}
