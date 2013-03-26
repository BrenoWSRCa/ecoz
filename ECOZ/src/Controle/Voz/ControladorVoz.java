/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Voz;

import Controle.ControladorDeElevador;
import Visualização.Som;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

/**
 *
 * @author breno
 */
public class ControladorVoz extends Thread{
    private ControladorDeElevador controlE;
    private Som andares[];
    public ControladorVoz(ControladorDeElevador ctrl){
        controlE = ctrl;
        andares = new Som[4];
        andares[0] = new Som("sons/primeiro andar.wav");
        andares[1] = new Som("sons/segundo andar.wav");
        andares[2] = new Som("sons/terceiro andar.wav");
        andares[3] = new Som("sons/quarto andar.wav");
    }
    
    public void interpreta(String comando){
        System.out.println(comando);
        String componentes[] = comando.split(" ");
        int i;
        for (i = 0; i < componentes.length; i++) {
            if(componentes[i].equalsIgnoreCase("elevador")){
                break;
            }
        }
        boolean abrir = false,
                fechar = false,
                levar = false,
                buscar = false;
        int andar = -1;
        if(++i < componentes.length){
            if(componentes[i].equalsIgnoreCase("emergência")){
                emergência();
                return;
            }
            if( i+1 < componentes.length){
                if(componentes[i].equalsIgnoreCase("abrir")){
                    abrir = true;
                }else if(componentes[i].equalsIgnoreCase("fechar")){
                    fechar = true;
                }else if(componentes[i].equalsIgnoreCase("levar")){
                    levar = true;
                }else if(componentes[i].equalsIgnoreCase("buscar")){
                    buscar = true;
                }
                
                i++;
                if(componentes[i].equalsIgnoreCase("porta")){
                    if(abrir){
                        abrir();
                        return;
                    }
                    if(fechar){
                        fechar();
                        return;
                    }
                }else if(componentes[i].equalsIgnoreCase("primeiro")){
                    andar = 0;
                }else if(componentes[i].equalsIgnoreCase("segundo")){
                    andar = 1;
                }else if(componentes[i].equalsIgnoreCase("terceiro")){
                    andar = 2;
                }else if(componentes[i].equalsIgnoreCase("quarto")){
                    andar = 3;
                }
                
                if(andar != -1){
                    if(buscar){
                        buscar(andar);
                        return;
                    }else{
                        levar(andar);
                        return;
                    }
                }
            }
            Som erro = new Som("sons/desculpe.wav");
            erro.play();
        }
        
    }
    
    @Override
    public void run(){
        ConfigurationManager cm;

        cm = new ConfigurationManager(ControladorVoz.class.getResource("ControladorVoz.config.xml"));

        // allocate the recognizer
        System.out.println("Carregando...");
        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Impossível iniciar microfone.");
            recognizer.deallocate();
            System.exit(1);
        }


        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Pronto!\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestResultNoFiller();
                interpreta(resultText);
            } else {
                System.out.println("Não te entendi");
                Som desculpe = new Som("sons/desculpe.wav");
                desculpe.play();
            }
//            try {
//                Thread.currentThread().sleep(100);
//            } catch (InterruptedException ex) {}
        }
    }

    private void buscar(int andar) {
        controlE.chamaEmbarcar(andar);
        andares[andar].play();
    }

    private void levar(int andar) {
        controlE.chamaDesembarcar(andar);
        andares[andar].play();
    }

    private void fechar() {
        controlE.abrirPorta();
    }

    private void abrir() {
        controlE.abrirPorta();
    }

    private void emergência() {
        Som suporte = new Som("sons/suporte técnico ativado.wav");
        suporte.play();
    }
}
