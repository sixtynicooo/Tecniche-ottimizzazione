/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise.Variabili_Fitness_Migliori;

import classi_condivise.VariabilGlobali;

/**
 *
 * @author sixty
 */
public class Variabili_Individuo {
     public double[] arrDoublePos;          // array double parametri
    public double[] arrDoubleVel;          // array double parametri
    public double fitness;          // array double parametri

    public double[] arrDoublePosMiglioreLocale;          // array double parametri
    public double fitnessLocaleMigliore;          // array double parametri
   
   

    public Variabili_Individuo(VariabilGlobali variabilGlobali) {
        this.arrDoublePos = new double[VariabilGlobali.DIM_ARR_DOUBLE];
        this.arrDoubleVel = new double[VariabilGlobali.DIM_ARR_DOUBLE];
        this.arrDoublePosMiglioreLocale=new double[VariabilGlobali.DIM_ARR_DOUBLE];
       
        
    }
    
}
