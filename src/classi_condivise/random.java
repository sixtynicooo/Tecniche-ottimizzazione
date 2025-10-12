/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author sixty
 */
public class random {
    /**
     * Genera un numero double casuale tra min e max (inclusi min e max).
     * IMPORTANTE MIN E MAX NON POSSONO ESSERE UGUALI
     * @param min
     * @param max
     * @return 
     */
    public static double generateRandomDouble(double min, double max) {
       return min<max?ThreadLocalRandom.current().nextDouble(min, max):min>max?ThreadLocalRandom.current().nextDouble(max, min):min;
    }

    /**
     * Genera un numero intero casuale tra min e max (inclusi min e max).
     * IMPORTANTE MIN E MAX NON POSSONO ESSERE UGUALI
     * @param min
     * @param max
     * @return 
     */
    public static int generateRandomInt(int min, int max) {
        return min<max?ThreadLocalRandom.current().nextInt(min, max):min>max?ThreadLocalRandom.current().nextInt(max, min):min;
    }

    /**
     * Genera un valore booleano casuale (true o false).
     * @return 
     */
    public static boolean generateRandomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }
    
}
