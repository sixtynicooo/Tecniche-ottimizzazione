/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;

import classi_condivise.Variabili_Fitness_Migliori.Variabili_Individuo;

/**
 *
 * @author sixty
 */
public class Fitness {

    public static gestioneArray1DMultidimensionali gestioneParametri = new gestioneArray1DMultidimensionali();

    /**
     * Calcola il valore della funzione Banana di Rosenbrock.
     *
     * La funzione è definita come: f(x, y) = (1 - x)^2 + 100 * (y - x^2)^2
     *
     * funzioni test
     * https://it.wikipedia.org/wiki/Funzione_test_(ottimizzazione)
     *
     *
     * @param x La prima variabile.
     * @param y La seconda variabile.
     * @return Il valore della funzione per i dati x e y.
     */
    // funzione banana f(1,1)=0
//    public double fitness(Variabili_Individuo variabili_Individuo, VariabilGlobali variabilGlobali) {
//        double x =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 0);
//        double y =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 1);
//        return Math.pow(1 - x, 2)
//                + 100 * Math.pow(y - x * x, 2);
//    }
    //funzione sferica f(0,0)=0
//    public double fitness(Variabili_Individuo variabili_Individuo, VariabilGlobali variabilGlobali) {
//         double x =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 0);
//        double y =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 1, 1);
//        
//        
//        return Math.pow( x, 2)+
//                 Math.pow(y, 2);
//
////        double results = 0;
////        for (int n_struttura = 0; n_struttura < variabilGlobali.listaStrutturaDati.length; n_struttura++) {
////            for (int indice = 0; indice < variabilGlobali.listaStrutturaDati[n_struttura][0]; indice++) {
////                //System.out.println(gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets, variabilGlobali.listaStrutturaDati, n_struttura, indice));
////                results +=Math.pow(gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets, variabilGlobali.listaStrutturaDati, n_struttura, indice),2) ;
////            }
////        }
////        return results;
//    }
    // f(3,0.5)=0
//    public double fitness(Variabili_Individuo variabili_Individuo, VariabilGlobali variabilGlobali) {
//    // Assumiamo che ARR_DOUBLE_POS[0] corrisponda a x e ARR_DOUBLE_POS[1] corrisponda a y
//     double x =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 0);
//        double y =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 1);
//    
//    // Calcolo della funzione di Beale
//    double term1 = 1.5 - x + x * y;
//    double term2 = 2.25 - x + x * Math.pow(y, 2);
//    double term3 = 2.625 - x + x * Math.pow(y, 3);
//    
//    return Math.pow(term1, 2) + Math.pow(term2, 2) + Math.pow(term3, 2);
//}
    // f(0,0)=12 massimo
//    public double fitness(Variabili_Individuo variabili_Individuo, VariabilGlobali variabilGlobali) {
//        double x =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 0, 0);
//        double y =gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets,variabilGlobali.listaStrutturaDati, 1, 1);
//        return 12 - (Math.pow(x, 2) + Math.pow(y, 2)) / 12;
//    }
    // minimizzare Funzione di Binh e Korn con vincoli
        public double fitness(Variabili_Individuo variabili_Individuo, VariabilGlobali variabilGlobali) {
        double x = gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets, variabilGlobali.listaStrutturaDati, 0, 0);
        double y = gestioneParametri.getArray1D(variabili_Individuo.arrDouble, variabilGlobali.offsets, variabilGlobali.listaStrutturaDati, 0, 1);

        double f1 = 4 * Math.pow(x, 2) + 4 * Math.pow(y, 2);
        double f2 = 4 * Math.pow(x - 5, 2) + 4 * Math.pow(y - 5, 2);
        double f = f1 + f2;
        boolean vincolo1 = Math.pow(x - 5, 2) + Math.pow(y, 2) <= 25;
        boolean vincolo2 = Math.pow(x - 8, 2) + Math.pow(y, +3) > 7.7;

        boolean booleanvincolo = vincolo1 && vincolo2;
        // se booleanvincolo non vera allora penalità
        f = booleanvincolo ? f : f + 10000;
        return f;
    }
}
