/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author sixty
 * L'idea è stampare con un altro  thread così da non essere lenti 
 */
import java.io.*;
import java.util.concurrent.*;

public class loggerAsync {
    // salvo tutte le stringhe per print
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static ExecutorService executor = null;
    
    
    
    // Costruttore: avvia il thread di stampa asincrona
    public loggerAsync() {
        executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                while (true) {
                    String msg = queue.take();
                    if (msg.equals("STOP")) break;
                    System.out.println(msg);
                }
            } catch (InterruptedException ignored) {
            }
        });
    }
    public void add(String log){
        queue.offer(log);
    }

    public void close() throws InterruptedException {
        queue.offer("STOP");
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
