/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;

/**
 *
 * @author sixty
 */
public class Fitness {

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
//    public double fitness(double[] ARR_DOUBLE_POS) {
//        return Math.pow(1 - ARR_DOUBLE_POS[0], 2)
//                + 100 * Math.pow(ARR_DOUBLE_POS[1] - ARR_DOUBLE_POS[0] * ARR_DOUBLE_POS[0], 2);
//    }
    //funzione sferica f(0,0)=0
//    public double fitness(double[] ARR_DOUBLE_POS) {
//        return Math.pow( ARR_DOUBLE_POS[0], 2)+
//                 Math.pow(ARR_DOUBLE_POS[1], 2);
//    }
    // f(3,0.5)=0
//    public double fitness(double[] ARR_DOUBLE_POS) {
//    // Assumiamo che ARR_DOUBLE_POS[0] corrisponda a x e ARR_DOUBLE_POS[1] corrisponda a y
//    double x = ARR_DOUBLE_POS[0];
//    double y = ARR_DOUBLE_POS[1];
//    
//    // Calcolo della funzione di Beale
//    double term1 = 1.5 - x + x * y;
//    double term2 = 2.25 - x + x * Math.pow(y, 2);
//    double term3 = 2.625 - x + x * Math.pow(y, 3);
//    
//    return Math.pow(term1, 2) + Math.pow(term2, 2) + Math.pow(term3, 2);
//}
    // f(0,0)=12 massimo
    public double fitness(double[] ARR_DOUBLE) {
        double x = ARR_DOUBLE[0];
        double y = ARR_DOUBLE[1];
        return 12 - (Math.pow(x, 2) + Math.pow(y, 2)) / 12;
    }

}
