/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PSO;

import classi_condivise.VariabilGlobali;
import classi_condivise.random;

/**
 *
 * @author sixty
 */
public class PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION {
 // variabili globali
    static VariabilGlobali  variabilGlobali;

    static classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui
    
    static random rand = new random();


    // Costruttore della classe PSO_Base
    public PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION(VariabilGlobali  variabilGlobali) {
        PSO_Base_SINGLE_SOLUTION.variabilGlobali=new VariabilGlobali();
        PSO_Base_SINGLE_SOLUTION.variabilGlobali=variabilGlobali;

    }


    public void run() {
        inizializza();
        movimenti();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
           globalFitnessMIgliore= new classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
            listaIndividui[i].calcoloFitnessPos();
        }
        globalFitnessMIgliore.calcoloFitnessPos();
        
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore);
        }
    }

    private void movimenti() {
        // mantengo double perchè fattoreStazionarieta fa un rapporto e il numeratore deve essere il tipo corretto double
        double indiceMovimenti=0;
        boolean migliorato=false;
        double fattoreStazionarieta = 0;
        for (long  movimento = 0; movimento < variabilGlobali.ITERAZIONI; movimento++) {
             for (int inerzia = 0; inerzia < variabilGlobali.DIM_ARR_DOUBLE; inerzia++) {
                fattoreStazionarieta = indiceMovimenti / variabilGlobali.STAZIONARIETA;
                // Calcolo dell'inerzia
                variabilGlobali.w_ARR_DOUBLE[inerzia] = variabilGlobali.w_ARR_DOUBLE_MAX[inerzia] - (variabilGlobali.w_ARR_DOUBLE_MAX[inerzia] - variabilGlobali.w_ARR_DOUBLE_MIN[inerzia]) * fattoreStazionarieta;
            }
            // Aggiorna la velocità
            for(int individuo=0;individuo<variabilGlobali.NUM_INDIVIDUO;individuo++){
                listaIndividui[individuo].aggiornaVelocitaPosizione(globalFitnessMIgliore);
                //System.out.println("movimento "+movimento+" fitness "+ listaIndividui[individuo].fitness);
            }
            // aggiorna globale
            for(int individuo=0;individuo<variabilGlobali.NUM_INDIVIDUO && !migliorato;individuo++){
                migliorato=listaIndividui[individuo].aggiornaFitnessGlobale(globalFitnessMIgliore);
            }
            // semigliorato resetto
            if(migliorato){
                indiceMovimenti=0;
                migliorato=false;
            }
            if(indiceMovimenti>variabilGlobali.STAZIONARIETA){
                System.out.println("Uscito per stazionarieta");
                break;
            }
            indiceMovimenti++;
            
        }
        globalFitnessMIgliore.stampa();
       
        

    }

}
