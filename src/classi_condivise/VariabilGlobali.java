/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;

/**
 *
 * @author sixty
 */
public class VariabilGlobali {

    
    public static int NUM_INDIVIDUO = 100;        // Numero di particelle
    public static long ITERAZIONI = 1000000000;         // generazioni o movimento 
    public static long STAZIONARIETA = 1000000000/10;         // generazioni o movimento 
    public static boolean problemaMassimizzareMinimizzare; // false= minimizzo, true=massimizzo

    public static int DIM_ARR_DOUBLE = 2;          // array double parametri
    public static double[] ARR_DOUBLE;          // array double parametri
    public static double[] ARR_DOUBLE_MIN;          // array double parametri
    public static double[] ARR_DOUBLE_MAX;          // array double parametri
    
    public static int DIM_ARR_INTERI = 0;          // array INTERI parametri
    public static int[] ARR_INTERI;          // array INTERI parametri
    public static int[] ARR_INTERI_MIN;          // array INTERI parametri
    public static int[] ARR_INTERI_MAX;          // array INTERI parametri
    
    public static int DIM_ARR_BOOLEAN = 0;          // array boolean parametri
    public static boolean[] ARR_BOOLEAN;          // array boolean parametri
    // spazio variabili
    // parametri PSO 
    public static double w = 0.5;// Inerzia
    public static double c1 = 2;              // Parametro cognitivo
    public static double c2 = 2;              // Parametro sociale

// Parametri PARTICELLA_PSO_INERZIA_ADATTIVA: ora ogni parametro ARR_DOUBLE ha l'inerzia personalizzata
    public static double[] w_ARR_DOUBLE;    // Array dell'inerzia per ogni parametro
    public static double[] w_ARR_DOUBLE_MIN;    // Array dell'inerzia per ogni parametro
    public static double[] w_ARR_DOUBLE_MAX;    // Array dell'inerzia per ogni parametro
    
    public VariabilGlobali() {
        // false=minimizzare, true=massimizzare
        problemaMassimizzareMinimizzare=false;
        // sistemo ARR_DOUBLE
        ARR_DOUBLE = new double[DIM_ARR_DOUBLE];
        ARR_DOUBLE_MIN = new double[]{-100000000, -100000000};
        ARR_DOUBLE_MAX = new double[]{100000000, 100000000};
        // w adattivo
        w_ARR_DOUBLE = new double[DIM_ARR_DOUBLE];      // Array dell'inerzia per ogni parametro
        w_ARR_DOUBLE_MIN = new double[]{0.1, 0.1};    // Array dell'inerzia per ogni parametro
        w_ARR_DOUBLE_MAX = new double[]{1, 1};    // Array dell'inerzia per ogni parametro
        
        
        ARR_INTERI = new int[DIM_ARR_INTERI];          // Array interi
        ARR_INTERI_MIN = new int[DIM_ARR_INTERI];     
        ARR_INTERI_MAX = new int[DIM_ARR_INTERI];  

        DIM_ARR_BOOLEAN = 0;          // Dimensione array booleani
        ARR_BOOLEAN = new boolean[DIM_ARR_BOOLEAN]; // Array booleani
        
    }
}
