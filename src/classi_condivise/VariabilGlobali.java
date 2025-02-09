/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;

import static classi_condivise.Fitness.gestioneParametri;

/**
 *
 * @author sixty
 */
public class VariabilGlobali {

    public static gestioneArray1DMultidimensionali gestioneArray1D;

    public static int NUM_INDIVIDUO = 100;        // Numero di particelle
    public static long ITERAZIONI = 20000000;         // generazioni o movimento 
    public static long STAZIONARIETA = ITERAZIONI / 10;         // generazioni o movimento 
    public static boolean problemaMassimizzareMinimizzare; // false= minimizzo, true=massimizzo

    // struttura per gestire array multidimensionali usando 
//    public static // Dimensioni delle strutture (matrici, array, cubi)
//            int[][] listaStrutturaDati = {
//                {2, 2}, // Matrice 2x2
//                {3, 5}, // Matrice 3x5
//                {5, 10}, // Matrice 5x10
//                {10}, // Array 1D (10 elementi)
//                {3, 3, 3},// Cubo 3x3x3
//                {2, 2, 2}, // Cubo 2x2x2
//                {2} // array 1
//            };
    public static // Dimensioni delle strutture (matrici, array, cubi)
            int[][] listaStrutturaDati = {
                {2}, // array 1
                {10}
            };
    // utile per iterare e calcolare indici

    public static int N_STRUTTURE_DATI = listaStrutturaDati.length;
    // [indice struttura dati][start,end]
    public int[][] offsets;

    public static int DIM_ARR_DOUBLE;          // array double parametri
    public static double[] ARR_DOUBLE_STRUTTURA_DATI_MIN;          // array double STRUTTURA_DATI
    public static double[] ARR_DOUBLE_STRUTTURA_DATI_MAX;          // array double STRUTTURA_DATI

    public static double[] ARR_DOUBLE_MIN;          // array double parametri
    public static double[] ARR_DOUBLE_MAX;          // array double parametri

    // spazio variabili
    // parametri PSO 
    public static double w = 0.3;// Inerzia
    public static double c1 = 2;              // Parametro cognitivo
    public static double c2 = 2;              // Parametro sociale

// Parametri PARTICELLA_PSO_INERZIA_ADATTIVA: ora ogni parametro ARR_DOUBLE ha l'inerzia personalizzata
    public static double w_ARR_DOUBLE_MIN = 0.01;    // Array dell'inerzia per ogni parametro
    public static double w_ARR_DOUBLE_MAX = 0.5;    // Array dell'inerzia per ogni parametro
    public static double w_Riduzione = 0.99;    // ad ogni ciclo diminuisce w

    // Parametri GA
    public static double GA_PROBABILITA_CROSSOWER = 0.7;
    public static double GA_PROBABILITA_MUTAZIONE = 0.1;
    public static double GA_PROBABILITA_MUTAZIONE_ULTRARRARA = 0.00000001;
    public static double GA_MUTAZIONE = 0.15;
    public static double GA_MUTAZIONE_ULTRARARA = 0.5;

    // parametri DE
    public static double DE_q = 0.9;
    // al momento non uso i limti
    public static double DE_MIN_Q_ARR_DOUBLE = 0.4;
    public static double DE_MAX_Q_ARR_DOUBLE = 0.5;
    public static double DE_PROBABILITA_CROSSOWER = 0.5;
    public static double DE_PROBABILITA_SELEZIONE = 0.5;

    // PARAMETRI BAT
    // modificando frequenza e aMax e alfabet funziona bene
    public static double FREQUENZA_MIN = 0;
    public static double FREQUENZA_MAX = 1;
    // gestita a livello individuo, serve per diminuire gradualmente il valore
    public static double A_MIN = 0;
    public static double A_MAX = 1000000000;
    public static double alfaBat = 0.9999;

    // parametri lucciole 
    // si potrebbe fare una versione adattiva di BETACASUALE
    // gamma da 0.01 a 100
    public static double GAMMA = 10;
    public static double ALFALucciola_MIN = 0.00001;
    public static double ALFALucciola_MAX = 10;
    public static double ALFALucciola = ALFALucciola_MAX;
    public static double BETACASUALE = 1;
    public static double THETA = 0.99; //da 0.95 a 0.97 riduce ALFALucciola

    public VariabilGlobali() {
        gestioneArray1D = new gestioneArray1DMultidimensionali();
        // false=minimizzare, true=massimizzare
        problemaMassimizzareMinimizzare = false;
        // sistemo ARR_DOUBLE
        DIM_ARR_DOUBLE = gestioneArray1D.calcolaDimensioneTotale(listaStrutturaDati);
        double min = -1000000000;
        double max = 1000000000;
        offsets = gestioneArray1D.calcolaOffset(listaStrutturaDati);
        // al momento ho scelto di usare numeri costanti per ogni struttura dati
        ARR_DOUBLE_STRUTTURA_DATI_MIN = new double[]{min, -10};
        ARR_DOUBLE_STRUTTURA_DATI_MAX = new double[]{max, 10};
        ARR_DOUBLE_MIN = new double[DIM_ARR_DOUBLE];
        ARR_DOUBLE_MAX = new double[DIM_ARR_DOUBLE];
        // popolo gli array come ho deciso di fare
        for (int n_struttura = 0; n_struttura < listaStrutturaDati.length; n_struttura++) {
            for (int indice = 0; indice < listaStrutturaDati[n_struttura][0]; indice++) {
                gestioneArray1D.setArray1D(ARR_DOUBLE_MIN, offsets, listaStrutturaDati, n_struttura, indice, ARR_DOUBLE_STRUTTURA_DATI_MIN[n_struttura]);
                gestioneArray1D.setArray1D(ARR_DOUBLE_MAX, offsets, listaStrutturaDati, n_struttura, indice, ARR_DOUBLE_STRUTTURA_DATI_MAX[n_struttura]);
            }
        }

//        for ( strutturaDati = 0; strutturaDati < listaStrutturaDati.length; strutturaDati++) {
//            System.out.println("Struttura "+strutturaDati);
//            for (int indice = offsets[strutturaDati][0]; indice < offsets[strutturaDati][1]; indice++) {
//            }
//           
//        }
    }
}
