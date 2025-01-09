/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import PSO.PSO_Base_SINGLE_SOLUTION;
import PSO.PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION;
import classi_condivise.VariabilGlobali;

/**
 *
 * @author sixty
 */
public class AlgoritmiOttimizzazione {

    // variabili globali
    static VariabilGlobali  variabilGlobali;
   


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlgoritmiOttimizzazione.variabilGlobali=new VariabilGlobali();
      
        
        

        PSO.PSO_Base_SINGLE_SOLUTION pso = new PSO_Base_SINGLE_SOLUTION(variabilGlobali);
        pso.run();
        
        
//        PSO.PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION psoAdattivo = new PSO.PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION(NUM_INDIVIDUO,
//                ITERAZIONI, STAZIONARIETA, DIM_ARR_DOUBLE,
//                ARR_DOUBLE, ARR_DOUBLE_MIN, ARR_DOUBLE_MAX, w_ARR_DOUBLE, w_ARR_DOUBLE_MIN, w_ARR_DOUBLE_MAX, c1, c2,problemaMassimizzareMinimizzare);
//        psoAdattivo.run();

    }

}
