/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LUCCIOLE;

import static LUCCIOLE.LUCCIOLA_BASE_SINGLE_SOLUTION.listaIndividuiCopia;
import PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION;
import classi_condivise.Fitness;
import static classi_condivise.Fitness.gestioneParametri;
import classi_condivise.VariabilGlobali;
import classi_condivise.Variabili_Fitness_Migliori.Variabili_Individuo;
import classi_condivise.random;
import classi_condivise.utility;

/**
 *
 * @author sixty
 */
public class INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION implements utility.FitnessEntity {
    Variabili_Individuo variabili_Individuo;

    static random rand = new random();
    static Fitness fitnessClass = new Fitness();
    static utility utilita = new utility();

    public double[] getArrDoublePos() {
        return this.variabili_Individuo.arrDouble;
    }

    @Override
    public void setArrDouble(double[] ARR_DOUBLE_POS) {
        this.variabili_Individuo.arrDouble = ARR_DOUBLE_POS;
    }

    @Override
    public double getFitness() {
        return this.variabili_Individuo.fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.variabili_Individuo.fitness = fitness;
    }

    public INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION(VariabilGlobali variabilGlobali) {

        this.variabili_Individuo = new Variabili_Individuo(variabilGlobali);
        // inizializzo ARR_DOUBLE
        for (int i = 0; i < variabilGlobali.DIM_ARR_DOUBLE; i++) {
            this.variabili_Individuo.arrDouble[i] = rand.generateRandomDouble(variabilGlobali.ARR_DOUBLE_MIN[i], variabilGlobali.ARR_DOUBLE_MAX[i]);
        }
    }

    public void aggiornaVelocitaPosizione(INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION globalFitnessMIgliore, int individuoCorrente, LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION[] listaIndividuiCopia, double ALFALucciola,VariabilGlobali  variabilGlobali) {

        for (int lucciola = 0; lucciola < variabilGlobali.NUM_INDIVIDUO; lucciola++) {
            if (individuoCorrente != lucciola) {

                double intensitaCorrente;
                double distanza;
                double componenteCasuale;
                for (int d = 0; d < variabilGlobali.DIM_ARR_DOUBLE; d++) {
                    // Componente casuale
                    componenteCasuale = ALFALucciola * (rand.generateRandomDouble(-variabilGlobali.BETACASUALE,variabilGlobali.BETACASUALE));
                    // caso in cui la lucciola corrente è la migliore
                    if (utilita.verificaMiglioramento(variabilGlobali.problemaMassimizzareMinimizzare,
                            this.variabili_Individuo.fitness,
                            listaIndividuiCopia[lucciola].variabili_Individuo.fitness)) {

                        this.variabili_Individuo.arrDouble[d] += ALFALucciola * componenteCasuale;

                        // Verifica dei limiti della variabile
                        this.variabili_Individuo.arrDouble[d] = utilita.verificaIntervalloDouble(this.variabili_Individuo.arrDouble[d],
                                variabilGlobali.ARR_DOUBLE_MIN[d],
                                variabilGlobali.ARR_DOUBLE_MAX[d]);

                    } else {
                        // Calcolo della distanza tra le due lucciole
                        distanza = calcolaDistanza(this.variabili_Individuo.arrDouble[d],
                                listaIndividuiCopia[lucciola].variabili_Individuo.arrDouble[d]);
                        // Calcolo dell'intensità della lucciola corrente
                        intensitaCorrente = this.variabili_Individuo.arrDouble[d]
                                * Math.exp(-variabilGlobali.GAMMA * distanza);

                        // Aggiornamento della posizione della lucciola corrente
                        this.variabili_Individuo.arrDouble[d] = listaIndividuiCopia[lucciola].variabili_Individuo.arrDouble[d] + intensitaCorrente
                                * (listaIndividuiCopia[lucciola].variabili_Individuo.arrDouble[d]
                                - this.variabili_Individuo.arrDouble[d])
                                + componenteCasuale;

                    }
                    // Verifica dei limiti della variabile
                    this.variabili_Individuo.arrDouble[d] = utilita.verificaIntervalloDouble(this.variabili_Individuo.arrDouble[d],
                            variabilGlobali.ARR_DOUBLE_MIN[d],
                            variabilGlobali.ARR_DOUBLE_MAX[d]);

                }

            }
        }

    }

    public void calcoloFitness(VariabilGlobali variabilGlobali) {
        // Calcolo del fitness attuale
        this.variabili_Individuo.fitness = fitnessClass.fitness(this.variabili_Individuo, variabilGlobali);
    }

    public boolean aggiornaFitnessGlobale(INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION globalFitnessMIgliore,VariabilGlobali  variabilGlobali) {
        return utilita.aggiornaFitnessGlobale(globalFitnessMIgliore, this.variabili_Individuo, variabilGlobali.problemaMassimizzareMinimizzare);

    }

    @Override
    public void stampa(long generation,String fileName,VariabilGlobali variabilGlobali) {
        double x =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 0);
        double y =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 1);
         System.out.println(" fitness "+ this.variabili_Individuo.fitness+" x "+x+" y "+y);
        // gestioneParametri.stampaArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0);
         utilita.scriviSuFile(generation, this.variabili_Individuo,fileName);
    }

    // Metodo per calcolare la distanza euclidea tra due lucciole
    private double calcolaDistanza(double a, double b) {
        double somma = 0.0;
        somma += Math.pow(a - b, 2);

        return Math.sqrt(somma);
    }

}
