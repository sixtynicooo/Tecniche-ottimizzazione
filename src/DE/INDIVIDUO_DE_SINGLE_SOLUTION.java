/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DE;

import classi_condivise.Fitness;
import classi_condivise.VariabilGlobali;
import classi_condivise.Variabili_Fitness_Migliori.Variabili_Individuo;
import classi_condivise.random;
import classi_condivise.utility;

/**
 *
 * @author sixty
 */
public class INDIVIDUO_DE_SINGLE_SOLUTION implements utility.FitnessEntity {

    static VariabilGlobali variabilGlobali;
    Variabili_Individuo variabili_Individuo;

    static random rand = new random();
    static Fitness fitnessClass = new Fitness();
    static utility utilita = new utility();

    public INDIVIDUO_DE_SINGLE_SOLUTION(VariabilGlobali variabilGlobali) {
        INDIVIDUO_DE_SINGLE_SOLUTION.variabilGlobali = new VariabilGlobali();
        INDIVIDUO_DE_SINGLE_SOLUTION.variabilGlobali = variabilGlobali;

        this.variabili_Individuo = new Variabili_Individuo(variabilGlobali);
        // inizializzo ARR_DOUBLE
        for (int i = 0; i < variabilGlobali.DIM_ARR_DOUBLE; i++) {
            this.variabili_Individuo.arrDouble[i] = rand.generateRandomDouble(variabilGlobali.ARR_DOUBLE_MIN[i], variabilGlobali.ARR_DOUBLE_MAX[i]);
        }
    }

    public void calcoloFitness() {
        // Calcolo del fitness attuale
        this.variabili_Individuo.fitness = fitnessClass.fitness(this.variabili_Individuo.arrDouble);

        // Verifica miglioramento locale in base al tipo di problema
        utilita.verificaMiglioramentoLocale(this.variabili_Individuo, variabilGlobali.problemaMassimizzareMinimizzare);
    }

    public boolean aggiornaFitnessGlobale(INDIVIDUO_DE_SINGLE_SOLUTION globalFitnessMIgliore) {
        return utilita.aggiornaFitnessGlobale(globalFitnessMIgliore, this.variabili_Individuo, variabilGlobali.problemaMassimizzareMinimizzare);

    }

    @Override
    public double getFitness() {
        return this.variabili_Individuo.fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.variabili_Individuo.fitness = fitness;
    }

    @Override
    public void setArrDouble(double[] ARR_DOUBLE_POS) {
        this.variabili_Individuo.arrDouble = ARR_DOUBLE_POS;
    }

     @Override
    public void stampa(long generation, double solution,String fileName) {
         System.out.println(" fitness "+ this.variabili_Individuo.fitness+" x= "+this.variabili_Individuo.arrDouble[0]+" y= "+this.variabili_Individuo.arrDouble[1]);
         utilita.scriviSuFile(generation, solution,fileName);
    }
    
    // Metodo clone standard (shallow copy)
    @Override
    public INDIVIDUO_DE_SINGLE_SOLUTION clone() {
        INDIVIDUO_DE_SINGLE_SOLUTION cloned = new INDIVIDUO_DE_SINGLE_SOLUTION(variabilGlobali);
        cloned.variabili_Individuo.arrDouble=variabili_Individuo.arrDouble.clone();
        
        return cloned;
    }

     // Metodo per copiare i dati di variabili_Individuo
    public Variabili_Individuo copiare() {
        return variabili_Individuo.copiaVariabiliIndividuo(variabilGlobali);
    }

}
