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
    static VariabilGlobali variabilGlobali;
    static GA.INDIVIDUO_GA_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static GA.INDIVIDUO_GA_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui

    static utility utilita = new utility();
    static random rand = new random();

    public GA_BASE_SINGLE_SOLUTION(VariabilGlobali variabilGlobali) {
        GA_BASE_SINGLE_SOLUTION.variabilGlobali = new VariabilGlobali();
        GA_BASE_SINGLE_SOLUTION.variabilGlobali = variabilGlobali;
    }

    public void run() {
        inizializza();
        generazioni();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new GA.INDIVIDUO_GA_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
        globalFitnessMIgliore = new GA.INDIVIDUO_GA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new GA.INDIVIDUO_GA_SINGLE_SOLUTION(variabilGlobali);
            listaIndividui[i].calcoloFitness();
            //listaIndividui[i].stampa();
        }
        globalFitnessMIgliore.calcoloFitness();
        //globalFitnessMIgliore.stampa();

        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore);
        }
        //globalFitnessMIgliore.stampa();
    }

    private void generazioni() {
        int indiceMovimenti = 0;
        boolean migliorato1 = false;
        boolean migliorato2 = false;
        int[] indiciIndividui = new int[variabilGlobali.NUM_INDIVIDUO];
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            indiciIndividui[i] = i;
        }
        utilita.mescolaArray(indiciIndividui);

        for (long i = 0; i < variabilGlobali.ITERAZIONI; i++) {
            for (int individuo = 0; individuo + 1 < variabilGlobali.NUM_INDIVIDUO; individuo++) {
                int figlio1 = individuo;
                int figlio2 = individuo + 1;
                // operatore crossower
                if (rand.generateRandomDouble(0, 1) < variabilGlobali.GA_PROBABILITA_CROSSOWER) {
                    crossower(figlio1, figlio2);

                }
                if (rand.generateRandomDouble(0, 1) < variabilGlobali.GA_PROBABILITA_MUTAZIONE) {
                    mutazione(figlio1);
                    mutazione(figlio2);
                }
                if (rand.generateRandomDouble(0, 1) < variabilGlobali.GA_PROBABILITA_MUTAZIONE_ULTRARRARA) {
                    System.out.println("mutazione ultrarara");
                    mutazione_ultrarara(figlio1);
                }
                if (rand.generateRandomDouble(0, 1) < variabilGlobali.GA_PROBABILITA_MUTAZIONE_ULTRARRARA) {
                    System.out.println("mutazione ultrarara");
                    mutazione_ultrarara(figlio2);
                }

                listaIndividui[figlio1].calcoloFitness();
                listaIndividui[figlio2].calcoloFitness();
                
                // aggiorna globale
                migliorato1 = listaIndividui[individuo].aggiornaFitnessGlobale(globalFitnessMIgliore);
                migliorato2 = listaIndividui[individuo+1].aggiornaFitnessGlobale(globalFitnessMIgliore);
                
                if (migliorato1 || migliorato2) {
                System.out.print("generazione " + i);
                globalFitnessMIgliore.stampa(i,globalFitnessMIgliore.variabili_Individuo.fitness,"GA.txt");
                indiceMovimenti = 0;
                migliorato1 = false;
                migliorato2 = false;
            }

            }
            // aggiorno migliore soluzione
            if (indiceMovimenti > variabilGlobali.STAZIONARIETA) {
                System.out.println("Uscito per stazionarieta");
                break;
            }
            // semigliorato resetto
            

            indiceMovimenti++;
            utilita.mescolaArray(indiciIndividui);

        }

    }

    private void crossower(int figlio1, int figlio2) {
        for (int d = 0; d < variabilGlobali.DIM_ARR_DOUBLE; d++) {
            double figlio1_double=listaIndividui[figlio1].variabili_Individuo.arrDouble[d];
            listaIndividui[figlio2].variabili_Individuo.arrDouble[d] = listaIndividui[figlio1].variabili_Individuo.arrDouble[d];
            listaIndividui[figlio1].variabili_Individuo.arrDouble[d] = figlio1_double;
           
        }
    }

    private void mutazione(int figlio) {
        int dimensioneRandomArrDouble = rand.generateRandomInt(0, variabilGlobali.DIM_ARR_DOUBLE - 1);
        if (dimensioneRandomArrDouble >= 0) {
            listaIndividui[figlio].variabili_Individuo.arrDouble[dimensioneRandomArrDouble] = rand.generateRandomDouble(
                    listaIndividui[figlio].variabili_Individuo.arrDouble[dimensioneRandomArrDouble] * (1 - variabilGlobali.GA_PROBABILITA_MUTAZIONE),
                    listaIndividui[figlio].variabili_Individuo.arrDouble[dimensioneRandomArrDouble] * (1 + variabilGlobali.GA_PROBABILITA_MUTAZIONE)
            );
            listaIndividui[figlio].variabili_Individuo.arrDouble[dimensioneRandomArrDouble] = utilita.verificaIntervalloDouble(listaIndividui[figlio].variabili_Individuo.arrDouble[dimensioneRandomArrDouble],
                    variabilGlobali.ARR_DOUBLE_MIN[dimensioneRandomArrDouble],
                    variabilGlobali.ARR_DOUBLE_MAX[dimensioneRandomArrDouble]);

        }

    }

    private void mutazione_ultrarara(int figlio) {
        for (int d = 0; d < variabilGlobali.DIM_ARR_DOUBLE; d++) {
            listaIndividui[figlio].variabili_Individuo.arrDouble[d] = listaIndividui[figlio].variabili_Individuo.arrDouble[d];
             listaIndividui[figlio].variabili_Individuo.arrDouble[d] = rand.generateRandomDouble(
                    listaIndividui[figlio].variabili_Individuo.arrDouble[d] * (1 - variabilGlobali.GA_MUTAZIONE_ULTRARARA),
                    listaIndividui[figlio].variabili_Individuo.arrDouble[d] * (1 + variabilGlobali.GA_MUTAZIONE_ULTRARARA)
            );
            listaIndividui[figlio].variabili_Individuo.arrDouble[d] = utilita.verificaIntervalloDouble(
                    listaIndividui[figlio].variabili_Individuo.arrDouble[d],
                    variabilGlobali.ARR_DOUBLE_MIN[d],
                    variabilGlobali.ARR_DOUBLE_MAX[d]);
           
        }
    }

}
