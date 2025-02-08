package Main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import BAT.BAT_BASE_SINGLE_SOLUTION;
import DE.DE_ADATTIVO_SINGLE_SOLUTION;
import DE.DE_BASE_SINGLE_SOLUTION;
import GA.GA_BASE_SINGLE_SOLUTION;
import LUCCIOLE.LUCCIOLA_BASE_SINGLE_SOLUTION;
import PSO.PSO_Base_SINGLE_SOLUTION;
import PSO.PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION;
import classi_condivise.VariabilGlobali;

/**
 *
 * @author sixty
 */
public class AlgoritmiOttimizzazione {

    // variabili globali
    //static VariabilGlobali variabilGlobali;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //AlgoritmiOttimizzazione.variabilGlobali = new VariabilGlobali();

        // PSO funziona bene con variabili continue
        // versione base
//        PSO.PSO_Base_SINGLE_SOLUTION pso = new PSO_Base_SINGLE_SOLUTION();
//        pso.run();
        // valore inerziale cambia nel tempo
//        PSO.PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION psoAdattivo = new PSO.PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION();
//        psoAdattivo.run();
// GA
//          GA.GA_BASE_SINGLE_SOLUTION ga=new GA_BASE_SINGLE_SOLUTION();
//          ga.run();
// DE 
//        DE.DE_BASE_SINGLE_SOLUTION de = new DE_BASE_SINGLE_SOLUTION();
//        de.run();
// DE ADATTIVO
//        DE.DE_ADATTIVO_SINGLE_SOLUTION deAdattivo = new DE_ADATTIVO_SINGLE_SOLUTION();
//        deAdattivo.run();

          //Bat
//          BAT.BAT_BASE_SINGLE_SOLUTION bat=new BAT_BASE_SINGLE_SOLUTION();
//          bat.run();
          // lucciole
          LUCCIOLE.LUCCIOLA_BASE_SINGLE_SOLUTION lucciola=new LUCCIOLA_BASE_SINGLE_SOLUTION();
          lucciola.run();
          
    }

}
