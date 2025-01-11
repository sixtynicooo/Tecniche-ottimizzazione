/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GA;

import classi_condivise.VariabilGlobali;
import classi_condivise.random;
import classi_condivise.utility;

/**
 *
 * @author sixty
 */
public class GA_BASE_SINGLE_SOLUTION {
    // variabili globali
    static VariabilGlobali  variabilGlobali;
     static GA.INDIVIDUO_GA_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static GA.INDIVIDUO_GA_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui

    public GA_BASE_SINGLE_SOLUTION(VariabilGlobali variabilGlobali) {
        GA_BASE_SINGLE_SOLUTION.variabilGlobali=new VariabilGlobali();
        GA_BASE_SINGLE_SOLUTION.variabilGlobali=variabilGlobali;
    }
    public void run() {
        inizializza();
        movimenti();
    }

        private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new GA.INDIVIDUO_GA_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
        globalFitnessMIgliore= new GA.INDIVIDUO_GA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new GA.INDIVIDUO_GA_SINGLE_SOLUTION(variabilGlobali);
            listaIndividui[i].calcoloFitness();
            System.out.print(i+" ");
            listaIndividui[i].stampa();
        }
        globalFitnessMIgliore.calcoloFitness();
        globalFitnessMIgliore.stampa();
        
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore);
        }
        globalFitnessMIgliore.stampa();
    }

    private void movimenti() {
        
    }

}
