/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise.Variabili_Fitness_Migliori;

import classi_condivise.VariabilGlobali;
import static classi_condivise.VariabilGlobali.NUM_INDIVIDUO;

/**
 *
 * @author sixty
 */
public class Variabili_Individuo {
    public double[] arrDouble;          // array double parametri
    public double[] arrDoubleVel;          // array double parametri
    public double fitness;          // array double parametri
    
    public double[] arrDoublePosMiglioreLocale;          // array double parametri
    public double fitnessLocaleMigliore;          // array double parametri
    
    // per algo bat
    public double probabilitaRandomWalk;          // array double parametri
    

    public Variabili_Individuo(VariabilGlobali variabilGlobali) {
        this.arrDouble = new double[VariabilGlobali.DIM_ARR_DOUBLE];
        this.arrDoubleVel = new double[VariabilGlobali.DIM_ARR_DOUBLE];
        this.arrDoublePosMiglioreLocale=new double[VariabilGlobali.DIM_ARR_DOUBLE];
        this.fitness=0.0;
        this.fitnessLocaleMigliore=0.0;
        for(int i=0;i<variabilGlobali.DIM_ARR_DOUBLE;i++){
            this.arrDoubleVel[i]=0.0;
            this.arrDoublePosMiglioreLocale[i]=0.0;
        }
        this.probabilitaRandomWalk=VariabilGlobali.probabilitaRandomWalk_start;
       
        
    }
    
    // Metodo per creare una copia profonda di Variabili_Individuo
    public Variabili_Individuo copiaVariabiliIndividuo(VariabilGlobali variabilGlobali) {
        Variabili_Individuo copia = new Variabili_Individuo(variabilGlobali);
        copia.arrDouble=this.arrDouble.clone();
        copia.arrDoubleVel=this.arrDoubleVel.clone();
        copia.fitness=this.fitness;
        copia.arrDoublePosMiglioreLocale=this.arrDoublePosMiglioreLocale.clone();
        copia.fitnessLocaleMigliore=this.fitnessLocaleMigliore;
        
        return copia;
    }
}
