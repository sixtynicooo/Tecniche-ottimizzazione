/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import PSO.PSO_Base;
import PSO.PSO_INERZIA_ADATTIVA;

/**
 *
 * @author sixty
 */
public class AlgoritmiOttimizzazione {

    // variabili globali
    // uguali per ogni algoritmo
    static int NUM_INDIVIDUO = 1000;        // Numero di particelle
    static long ITERAZIONI = 1000000000;         // generazioni o movimento 
    static long STAZIONARIETA = 1000000000/10;         // generazioni o movimento 

    static int DIM_ARR_DOUBLE = 2;          // array double parametri
    static double[] ARR_DOUBLE;          // array double parametri
    static double[] ARR_DOUBLE_MIN;          // array double parametri
    static double[] ARR_DOUBLE_MAX;          // array double parametri

    // spazio variabili
    // parametri PSO 
    static double w = 0.5;// Inerzia
    static double c1 = 2;              // Parametro cognitivo
    static double c2 = 2;              // Parametro sociale

// Parametri PARTICELLA_PSO_INERZIA_ADATTIVA: ora ogni parametro ARR_DOUBLE ha l'inerzia personalizzata
    static double[] w_ARR_DOUBLE;    // Array dell'inerzia per ogni parametro
    static double[] w_ARR_DOUBLE_MIN;    // Array dell'inerzia per ogni parametro
    static double[] w_ARR_DOUBLE_MAX;    // Array dell'inerzia per ogni parametro

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // sistemo ARR_DOUBLE
        ARR_DOUBLE = new double[DIM_ARR_DOUBLE];
        ARR_DOUBLE_MIN = new double[]{-1000000000, -1000000000};
        ARR_DOUBLE_MAX = new double[]{1000000000, 1000000000};
        // w adattivo
        w_ARR_DOUBLE = new double[DIM_ARR_DOUBLE];      // Array dell'inerzia per ogni parametro
        w_ARR_DOUBLE_MIN = new double[]{0.1, 0.1};    // Array dell'inerzia per ogni parametro
        w_ARR_DOUBLE_MAX = new double[]{1, 1};    // Array dell'inerzia per ogni parametro

        PSO.PSO_Base pso = new PSO_Base(NUM_INDIVIDUO, ITERAZIONI,STAZIONARIETA, DIM_ARR_DOUBLE, ARR_DOUBLE, ARR_DOUBLE_MIN, ARR_DOUBLE_MAX,w, c1, c2);
        pso.run();
        
        
//        PSO.PSO_INERZIA_ADATTIVA psoAdattivo = new PSO.PSO_INERZIA_ADATTIVA(NUM_INDIVIDUO,
//                ITERAZIONI, STAZIONARIETA, DIM_ARR_DOUBLE,
//                ARR_DOUBLE, ARR_DOUBLE_MIN, ARR_DOUBLE_MAX, w_ARR_DOUBLE, w_ARR_DOUBLE_MIN, w_ARR_DOUBLE_MAX, c1, c2);
//        psoAdattivo.run();

    }

}
