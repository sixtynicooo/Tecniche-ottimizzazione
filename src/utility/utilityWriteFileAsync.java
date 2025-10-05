/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sixty
 */
public class utilityWriteFileAsync {
     // salvo tutte le stringhe per print
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private String fileName;
    private boolean running=true;
    
     public utilityWriteFileAsync(String fileName) {
        this.fileName = fileName;
        this.resetFile();


        // Avvia il worker asincrono
        executor.submit(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                long lastFlush = System.currentTimeMillis();
                int messagesSinceFlush = 0;

                while (running || !queue.isEmpty()) {
                    String msg = queue.poll(100, TimeUnit.MILLISECONDS);
                    if (msg != null) {
                        writer.write(msg);
                        writer.newLine();
                        messagesSinceFlush++;
                    }

                    long now = System.currentTimeMillis();
                    if (messagesSinceFlush >= 10 || now - lastFlush >= 1000) {
                        writer.flush();
                        messagesSinceFlush = 0;
                        lastFlush = now;
                    }
                }

                // Flush finale
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                Logger.getLogger(utilityWriteFileAsync.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    
    
     /** Aggiunge una riga da scrivere (non blocca mai) */
    public void add(String text) {
        queue.offer(text);
    }

    /** Chiude in modo sicuro il logger, aspettando che finisca di scrivere */
    public void close() {
        running = false;
        executor.shutdown();
        try {
            if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // chiusura forzata se necessario
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
     /**
     * Resetta (svuota) il contenuto di un file dato il suo nome.
     * Se il file non esiste, verrà creato.
     *
     * @param fileName Il nome del file da resettare.
     */
    public void resetFile() {
        try (FileWriter writer = new FileWriter(this.fileName, false)) {
            // Apre il file in modalità sovrascrittura (append=false) e lo svuota
        } catch (IOException e) {
            System.err.println("Errore durante il reset del file: " + e.getMessage());
        }
    }
    
}
