/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visualização;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author breno
 */
public class Som {

    private InputStream arquivo;
    private AudioStream som;
    private String nomeArquivo;

    public Som(String arquivoDeSom) {
        nomeArquivo = arquivoDeSom;
    }

    public void play() {
        try {
            arquivo = new FileInputStream(nomeArquivo);
        } catch (FileNotFoundException ex) {}
        try {
            som = new AudioStream(arquivo);
        } catch (IOException ex) {}
        AudioPlayer.player.start(som);
    }

    public void stop() {
        AudioPlayer.player.stop(som);
    }
}
