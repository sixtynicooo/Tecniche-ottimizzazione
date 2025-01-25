/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;

import classi_condivise.Variabili_Fitness_Migliori.Variabili_Individuo;

/**
 *
 * @author sixty
 */
public class utility {

    static random rand = new random();

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

        void setArrDouble(double[] arrDoublePos);

        void stampa();
    }

    /**
     * Verifica se il valore di fitness di un individuo migliora rispetto al
     * valore globale, considerando se il problema è di massimizzazione o
     * minimizzazione.
     *
     * @param problemaMassimizzareMinimizzare true se il problema è di
     * massimizzazione, false per minimizzazione
     * @param fitnessIndividuo il valore di fitness dell'individuo
     * @param fitnessGlobale il valore di fitness globale
     * @return true se il valore dell'individuo migliora rispetto al valore
     * globale, false altrimenti
     */
    public boolean verificaMiglioramento(boolean problemaMassimizzareMinimizzare, double fitnessIndividuo, double fitnessMigliore) {
        if (problemaMassimizzareMinimizzare) {
            return fitnessIndividuo > fitnessMigliore;
        } else {
            return fitnessIndividuo < fitnessMigliore;
        }
    }

    // aggiorno globale 
    public <T extends FitnessEntity> boolean aggiornaFitnessGlobale(
            T globalFitnessMigliore,
            Variabili_Individuo variabili_Individuo,
            boolean problemaMassimizzareMinimizzare) {

        boolean migliorato = false;

        if (verificaMiglioramento(problemaMassimizzareMinimizzare,
                variabili_Individuo.fitness,
                globalFitnessMigliore.getFitness())) {

            globalFitnessMigliore.setFitness(variabili_Individuo.fitness);
            globalFitnessMigliore.setArrDouble(variabili_Individuo.arrDouble.clone());
            migliorato = true;
        }

        return migliorato;
    }

    // Metodo per verificare e aggiornare il miglioramento locale del fitness
    public void verificaMiglioramentoLocale(Variabili_Individuo variabili_Individuo,
            boolean problemaMassimizzareMinimizzare) {
        // Verifica miglioramento locale in base al tipo di problema
        boolean migliorato;

        if (problemaMassimizzareMinimizzare) {
            // Problema di massimizzazione (fitness maggiore è meglio)
            migliorato = variabili_Individuo.fitness > variabili_Individuo.fitnessLocaleMigliore;
        } else {
            // Problema di minimizzazione (fitness minore è meglio)
            migliorato = variabili_Individuo.fitness < variabili_Individuo.fitnessLocaleMigliore;
        }

        // Se è migliorato, aggiorna il fitness locale e la posizione migliore
        if (migliorato) {
            variabili_Individuo.fitnessLocaleMigliore = variabili_Individuo.fitness;
            variabili_Individuo.arrDoublePosMiglioreLocale = variabili_Individuo.arrDouble.clone();
        }

    }

    public static void mescolaArray(int[] array) {

        // Itera sull'array e scambia ogni elemento con un altro elemento casuale
        for (int i = 0; i < array.length; i++) {
            int randomIndex = rand.generateRandomInt(0, array.length - 1); // Ottieni un indice casuale
            // Scambia array[i] con array[randomIndex]
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }
    
    
}
