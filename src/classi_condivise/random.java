/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;
import java.security.SecureRandom;
import java.util.Random;
/**
 *
 * @author sixty
 */
public class random {
    private static final Random random = new Random(); // Sorgente casuale
    
    // Sorgente casuale sicura
    //private static final SecureRandom random = new SecureRandom();
    /**
     * Genera un numero casuale double tra min e max (inclusi min e max).
     *
     * @param min Il valore minimo (incluso).
     * @param max Il valore massimo (incluso).
     * @return Un numero double casuale tra min e max.
     */
    public double generateRandomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble(); // Genera un double tra min e max
    }
        /**
     * Genera un numero casuale intero tra min e max (inclusi min e max).
     *
     * @param min Il valore minimo (incluso).
     * @param max Il valore massimo (incluso).
     * @return Un numero intero casuale tra min e max.
     */
    public int generateRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min; // Genera un intero tra min e max
    }
      /**
     * Genera un valore booleano casuale (true o false).
     *
     * @return true o false in modo casuale.
     */
    public boolean generateRandomBoolean() {
        return random.nextBoolean(); // Genera un booleano casuale
    }
    
}
