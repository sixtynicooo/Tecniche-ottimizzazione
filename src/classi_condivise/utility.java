/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;

import static classi_condivise.PARTICELLA_PSO_Base_SINGLE_SOLUTION.problemaMassimizzareMinimizzare;

/**
 *
 * @author sixty
 */
public class utility {

    public double verificaIntervalloDouble(double numero, double min, double max) {
        if (numero < min) {
            return min;
        } else if (numero > max) {
            return max;
        } else {
            return numero;
        }
    }

    public int verificaIntervalloInt(int numero, int min, int max) {
        if (numero < min) {
            return min;
        } else if (numero > max) {
            return max;
        } else {
            return numero;
        }
    }

    

    public interface FitnessEntity {

        double getFitness();

        void setFitness(double fitness);

        void setArrDoublePos(double[] arrDoublePos);

        void stampa();
    }
    // aggiorno globale 
    public <T extends FitnessEntity> boolean aggiornaFitnessGlobale(T globalFitnessMigliore, double fitness, double[] arrDoublePos, boolean problemaMassimizzareMinimizzare) {
        boolean migliorato = false;
        if (fitness < globalFitnessMigliore.getFitness()) {
            globalFitnessMigliore.setFitness(fitness);
            globalFitnessMigliore.setArrDoublePos(arrDoublePos.clone());
            globalFitnessMigliore.stampa();
            migliorato = true; // Indica che c'è stato un miglioramento
        }
        return migliorato;
    }
}
