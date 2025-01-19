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
    
    public double BAT_A;
   
   

    public Variabili_Individuo(VariabilGlobali variabilGlobali) {
        this.arrDouble = new double[VariabilGlobali.DIM_ARR_DOUBLE];
        this.arrDoubleVel = new double[VariabilGlobali.DIM_ARR_DOUBLE];
        this.arrDoublePosMiglioreLocale=new double[VariabilGlobali.DIM_ARR_DOUBLE];
        this.BAT_A=variabilGlobali.A_MAX;
       
        
    }
    
    // Metodo per creare una copia profonda di Variabili_Individuo
    public Variabili_Individuo copiaVariabiliIndividuo(VariabilGlobali variabilGlobali) {
        Variabili_Individuo copia = new Variabili_Individuo(variabilGlobali);
        copia.arrDouble=this.arrDouble.clone();
        copia.arrDoubleVel=this.arrDoubleVel.clone();
        copia.fitness=this.fitness;
        copia.arrDoublePosMiglioreLocale=this.arrDoublePosMiglioreLocale.clone();
        copia.fitnessLocaleMigliore=this.fitnessLocaleMigliore;
        copia.BAT_A=this.BAT_A;
        
        return copia;
    }
       // Metodo per creare una copia posizione e fitness con A
    public Variabili_Individuo copiaVariabiliIndividuoBat(VariabilGlobali variabilGlobali) {
        Variabili_Individuo copia = new Variabili_Individuo(variabilGlobali);
        copia.arrDouble=this.arrDouble.clone();
        copia.fitness=this.fitness;
        copia.BAT_A=this.BAT_A;
        
        return copia;
    }

    public void aggiornaBatA(double alfa) {
        BAT_A*=alfa;
    }

   
}
