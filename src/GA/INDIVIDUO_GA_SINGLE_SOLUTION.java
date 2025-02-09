/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GA;

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
public class INDIVIDUO_GA_SINGLE_SOLUTION implements utility.FitnessEntity {
    Variabili_Individuo variabili_Individuo;

    
    static random rand = new random();
    static Fitness fitnessClass=new Fitness();
    static utility utilita=new utility();
    
    
    public INDIVIDUO_GA_SINGLE_SOLUTION(VariabilGlobali variabilGlobali) {
        
        this.variabili_Individuo=new Variabili_Individuo(variabilGlobali);
        // inizializzo ARR_DOUBLE
        for (int i = 0; i < variabilGlobali.DIM_ARR_DOUBLE; i++) {
            this.variabili_Individuo.arrDouble[i] = rand.generateRandomDouble(variabilGlobali.ARR_DOUBLE_MIN[i], variabilGlobali.ARR_DOUBLE_MAX[i]);
        }
    }
    
        public void calcoloFitness(VariabilGlobali variabilGlobali) {
    // Calcolo del fitness attuale
    this.variabili_Individuo.fitness = fitnessClass.fitness(this.variabili_Individuo, variabilGlobali);

    // Verifica miglioramento locale in base al tipo di problema
    utilita.verificaMiglioramentoLocale(this.variabili_Individuo,variabilGlobali.problemaMassimizzareMinimizzare);
}

    public boolean aggiornaFitnessGlobale(INDIVIDUO_GA_SINGLE_SOLUTION globalFitnessMIgliore,VariabilGlobali variabilGlobali) {
        return utilita.aggiornaFitnessGlobale(globalFitnessMIgliore,this.variabili_Individuo,variabilGlobali.problemaMassimizzareMinimizzare);
       
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
     public void stampa(long generation,String fileName,VariabilGlobali variabilGlobali) {
        double x =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 0);
        double y =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 1);
         System.out.println(" fitness "+ this.variabili_Individuo.fitness+" x "+x+" y "+y);
        // gestioneParametri.stampaArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0);
         utilita.scriviSuFile(generation, this.variabili_Individuo,fileName);
    }



}
