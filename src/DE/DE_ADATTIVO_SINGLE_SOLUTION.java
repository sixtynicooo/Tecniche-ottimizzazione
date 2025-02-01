/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DE;

import classi_condivise.VariabilGlobali;
import classi_condivise.random;
import classi_condivise.utility;

/**
 *
 * @author sixty
 */
public class DE_ADATTIVO_SINGLE_SOLUTION {

    // variabili globali
    static VariabilGlobali variabilGlobali;
    static DE.INDIVIDUO_DE_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static DE.INDIVIDUO_DE_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui

    static utility utilita = new utility();
    static random rand = new random();

    public DE_ADATTIVO_SINGLE_SOLUTION(VariabilGlobali variabilGlobali) {
        DE_BASE_SINGLE_SOLUTION.variabilGlobali = new VariabilGlobali();
        DE_BASE_SINGLE_SOLUTION.variabilGlobali = variabilGlobali;
    }

    public void run() {
        inizializza();
        generazioni();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new DE.INDIVIDUO_DE_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
        globalFitnessMIgliore = new DE.INDIVIDUO_DE_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new DE.INDIVIDUO_DE_SINGLE_SOLUTION(variabilGlobali);
            listaIndividui[i].calcoloFitness();
            //listaIndividui[i].stampa();
        }
        globalFitnessMIgliore.calcoloFitness();

        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore);
        }

    }

    private void generazioni() {

        boolean migliorato = false;
        int indiceMovimenti = 0;
        int[] indiciIndividui = new int[variabilGlobali.NUM_INDIVIDUO];
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            indiciIndividui[i] = i;
        }
        utilita.mescolaArray(indiciIndividui);

        DE.INDIVIDUO_DE_SINGLE_SOLUTION mutante;    // lista individui
        mutante = new DE.INDIVIDUO_DE_SINGLE_SOLUTION(variabilGlobali);

        double fattoreQAdattivo = 0;
        for (long iter = 0; iter < variabilGlobali.ITERAZIONI; iter++) {
            fattoreQAdattivo = indiceMovimenti / variabilGlobali.STAZIONARIETA;
            variabilGlobali.DE_q = utilita.verificaIntervalloDouble(
                    variabilGlobali.DE_q+fattoreQAdattivo, 
                    variabilGlobali.DE_MIN_Q_ARR_DOUBLE, 
                    variabilGlobali.DE_MAX_Q_ARR_DOUBLE);
            for (int individuo = 0; individuo + 3 < variabilGlobali.NUM_INDIVIDUO; individuo += 4) {

                int genitorePrimario = indiciIndividui[individuo];
                int mutante1 = indiciIndividui[individuo + 1];
                int mutante2 = indiciIndividui[individuo + 2];
                int mutante3 = indiciIndividui[individuo + 3];

                // operatore mutazione
                mutazione(mutante1, mutante2, mutante3, mutante);
                // operatore crossower
                //crossower(mutante, genitorePrimario);
                // operatore selezione
                if (selezione(mutante, genitorePrimario)) {
                    indiceMovimenti = 0;
                    variabilGlobali.DE_q=variabilGlobali.DE_MIN_Q_ARR_DOUBLE;
                    System.out.print("N iterazione "+iter);
                    globalFitnessMIgliore.stampa(iter,globalFitnessMIgliore.variabili_Individuo.fitness,"DE Adattivo.txt");
                    migliorato = true;
                }

            }
            // aggiorno migliore soluzione
            if (indiceMovimenti > variabilGlobali.STAZIONARIETA) {
                System.out.println("Uscito per stazionarieta " + iter);
                break;
            }
            // semigliorato resetto

            indiceMovimenti++;
            utilita.mescolaArray(indiciIndividui);
        }
    }

    private void mutazione(int mutante1, int mutante2, int mutante3, INDIVIDUO_DE_SINGLE_SOLUTION mutante) {
        for (int d = 0; d < variabilGlobali.DIM_ARR_DOUBLE; d++) {
            mutante.variabili_Individuo.arrDouble[d] = listaIndividui[mutante1].variabili_Individuo.arrDouble[d]
                    + variabilGlobali.DE_q * (listaIndividui[mutante2].variabili_Individuo.arrDouble[d]
                    - listaIndividui[mutante3].variabili_Individuo.arrDouble[d]);
            mutante.variabili_Individuo.arrDouble[d] = utilita.verificaIntervalloDouble(mutante.variabili_Individuo.arrDouble[d], variabilGlobali.ARR_DOUBLE_MIN[d], variabilGlobali.ARR_DOUBLE_MAX[d]);

        }
    }

    private void crossower(INDIVIDUO_DE_SINGLE_SOLUTION mutante, int genitorePrimario) {
        for (int d = 0; d < variabilGlobali.DIM_ARR_DOUBLE; d++) {
            // se il numero casuale è < lascio la variabile della mutazione
            if (rand.generateRandomDouble(0, 1) > variabilGlobali.DE_PROBABILITA_CROSSOWER) {
                mutante.variabili_Individuo.arrDouble[d] = listaIndividui[genitorePrimario].variabili_Individuo.arrDouble[d];
            }

        }
    }

    private boolean selezione(INDIVIDUO_DE_SINGLE_SOLUTION mutante, int genitorePrimario) {
        boolean migliorato = false;
        // Calcola la fitness del mutante
        mutante.calcoloFitness();
        // Confronta la fitness del mutante con il genitore
        if ((variabilGlobali.problemaMassimizzareMinimizzare && mutante.getFitness() > listaIndividui[genitorePrimario].getFitness())
                || (!variabilGlobali.problemaMassimizzareMinimizzare && mutante.getFitness() < listaIndividui[genitorePrimario].getFitness())) {
            // Sostituisci il genitore con il mutante se la fitness è migliore
            listaIndividui[genitorePrimario].variabili_Individuo = mutante.copiare();
            migliorato = listaIndividui[genitorePrimario].aggiornaFitnessGlobale(globalFitnessMIgliore);
        }

        return migliorato;
    }

}
