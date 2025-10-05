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
     */
    public static double generateRandomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /**
     * Genera un numero intero casuale tra min e max (inclusi min e max).
     */
    public static int generateRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Genera un valore booleano casuale (true o false).
     */
    public static boolean generateRandomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }
    
}
