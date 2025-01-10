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

    boolean aggiornaFitnessGlobale() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    public interface FitnessEntity {

        double getFitness();

        void setFitness(double fitness);

        void setArrDoublePos(double[] arrDoublePos);

        void stampa();
    }
    // aggiorno globale 
    public <T extends FitnessEntity> boolean aggiornaFitnessGlobale(T globalFitnessMigliore, Variabili_Individuo variabili_Individuo, boolean problemaMassimizzareMinimizzare) {
        boolean migliorato = false;
        if (variabili_Individuo.fitness < globalFitnessMigliore.getFitness()) {
            globalFitnessMigliore.setFitness(variabili_Individuo.fitness);
            globalFitnessMigliore.setArrDoublePos(variabili_Individuo.arrDoublePos.clone());
            globalFitnessMigliore.stampa();
            migliorato = true; // Indica che c'è stato un miglioramento
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
            variabili_Individuo.arrDoublePosMiglioreLocale = variabili_Individuo.arrDoublePos.clone();
        }
        
    }
}
