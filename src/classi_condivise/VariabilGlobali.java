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
    public static long ITERAZIONI = 50000000;         // generazioni o movimento 
    public static long STAZIONARIETA = ITERAZIONI/10;         // generazioni o movimento 
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
    public static double w_ARR_DOUBLE=0.6;    // Array dell'inerzia per ogni parametro
    public static double w_ARR_DOUBLE_MIN=0.1;    // Array dell'inerzia per ogni parametro
    public static double w_ARR_DOUBLE_MAX=0.8;    // Array dell'inerzia per ogni parametro
    
    
    // Parametri GA
    public static double GA_PROBABILITA_CROSSOWER=0.7;
    public static double GA_PROBABILITA_MUTAZIONE=0.1;
    public static double GA_PROBABILITA_MUTAZIONE_ULTRARRARA=0.00000001;
    public static double GA_MUTAZIONE=0.15;
    public static double GA_MUTAZIONE_ULTRARARA=0.5;
    
    
    // parametri DE
    public static double DE_q=0.4;
    // al momento non uso i limti
    public static double DE_MIN_Q_ARR_DOUBLE=0.4;
    public static double DE_MAX_Q_ARR_DOUBLE=0.5;
    public static double DE_PROBABILITA_CROSSOWER=0.5;
    public static double DE_PROBABILITA_SELEZIONE=0.5;
    
    
    // PARAMETRI BAT
    // modificando frequenza e aMax e alfabet funziona bene
    public static double FREQUENZA_MIN=0;
    public static double FREQUENZA_MAX=1000;
    // gestita a livello individuo
    public static double A_MIN=0;
    public static double A_MAX=1000000;
    public static double alfaBat=0.9999;
    
    
    
    public VariabilGlobali() {
        // false=minimizzare, true=massimizzare
        problemaMassimizzareMinimizzare=false;
        // sistemo ARR_DOUBLE
        ARR_DOUBLE = new double[DIM_ARR_DOUBLE];
        ARR_DOUBLE_MIN = new double[]{-10000 ,-10000};
        ARR_DOUBLE_MAX = new double[]{10000, 10000};
        
        
        ARR_INTERI = new int[DIM_ARR_INTERI];          // Array interi
        ARR_INTERI_MIN = new int[DIM_ARR_INTERI];     
        ARR_INTERI_MAX = new int[DIM_ARR_INTERI];  

        DIM_ARR_BOOLEAN = 0;          // Dimensione array booleani
        ARR_BOOLEAN = new boolean[DIM_ARR_BOOLEAN]; // Array booleani
        
        
    }
}
