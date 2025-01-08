/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PSO;

import classi_condivise.random;

/**
 *
 * @author sixty
 */
public class PSO_Base_SINGLE_SOLUTION {
    // variabili globali
    static boolean problemaMassimizzareMinimizzare; // false= minimizzo, true=massimizzo

    // uguali per ogni algoritmo
    static int NUM_INDIVIDUO;        // Numero di particelle
    static long ITERAZIONI;         // generazioni o movimento 
    static long STAZIONARIETA;         // generazioni o movimento 
    static classi_condivise.PARTICELLA_PSO_Base_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static classi_condivise.PARTICELLA_PSO_Base_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui
    

    static int DIM_ARR_DOUBLE;          // array double parametri
    static double[] ARR_DOUBLE_MIN;          // array double parametri
    static double[] ARR_DOUBLE_MAX;          // array double parametri

    // spazio variabili
    // parametri PSO
    static double w;                // Inerzia
    static double c1;              // Parametro cognitivo
    static double c2;              // Parametro sociale

    // Costruttore della classe PSO_Base
    public PSO_Base_SINGLE_SOLUTION(int NUM_INDIVIDUO, long ITERAZIONI, long STAZIONARIETA, int DIM_ARR_DOUBLE, double[] ARR_DOUBLE, double[] ARR_DOUBLE_MIN, double[] ARR_DOUBLE_MAX, double w, double c1, double c2,boolean problemaMassimizzareMinimizzare) {
        PSO_Base_SINGLE_SOLUTION.NUM_INDIVIDUO = NUM_INDIVIDUO;
        PSO_Base_SINGLE_SOLUTION.ITERAZIONI = ITERAZIONI;
        PSO_Base_SINGLE_SOLUTION.STAZIONARIETA=STAZIONARIETA;
        PSO_Base_SINGLE_SOLUTION.problemaMassimizzareMinimizzare=problemaMassimizzareMinimizzare;
        
        PSO_Base_SINGLE_SOLUTION.DIM_ARR_DOUBLE = DIM_ARR_DOUBLE;
        PSO_Base_SINGLE_SOLUTION.ARR_DOUBLE_MIN = ARR_DOUBLE_MIN;
        PSO_Base_SINGLE_SOLUTION.ARR_DOUBLE_MAX = ARR_DOUBLE_MAX;
        
        
        PSO_Base_SINGLE_SOLUTION.w = w;
        PSO_Base_SINGLE_SOLUTION.c1 = c1;
        PSO_Base_SINGLE_SOLUTION.c2 = c2;

    }


    public void run() {
        inizializza();
        movimenti();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new classi_condivise.PARTICELLA_PSO_Base_SINGLE_SOLUTION[NUM_INDIVIDUO];
           globalFitnessMIgliore= new classi_condivise.PARTICELLA_PSO_Base_SINGLE_SOLUTION(DIM_ARR_DOUBLE, ARR_DOUBLE_MIN, ARR_DOUBLE_MAX,problemaMassimizzareMinimizzare); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new classi_condivise.PARTICELLA_PSO_Base_SINGLE_SOLUTION(DIM_ARR_DOUBLE, ARR_DOUBLE_MIN, ARR_DOUBLE_MAX,problemaMassimizzareMinimizzare); // Crea un nuovo individuo
            listaIndividui[i].calcoloFitnessPos();
        }
        globalFitnessMIgliore.calcoloFitnessPos();
        
        for (int i = 0; i < NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore);
        }
        // inizializza particella globale
        //System.out.println(" fitness "+globalFitnessMIgliore.getFitness()+" x= "+globalFitnessMIgliore.getArrDoublePos()[0]+" y= "+globalFitnessMIgliore.getArrDoublePos()[1]);
    }

    private void movimenti() {
        int indiceMovimenti=0;
        boolean migliorato=false;

        for (long  movimento = 0; movimento < ITERAZIONI; movimento++) {
            // Aggiorna la velocità
            for(int individuo=0;individuo<NUM_INDIVIDUO;individuo++){
                listaIndividui[individuo].aggiornaVelocitaPosizione(DIM_ARR_DOUBLE,w,c1,c2,ARR_DOUBLE_MIN, ARR_DOUBLE_MAX,globalFitnessMIgliore);
                //System.out.println("movimento "+movimento+" fitness "+ listaIndividui[individuo].fitness);
            }
            // aggiorna globale
            for(int individuo=0;individuo<NUM_INDIVIDUO;individuo++){
                migliorato=listaIndividui[individuo].aggiornaFitnessGlobale(globalFitnessMIgliore);
            }
            // semigliorato resetto
            if(migliorato){
                indiceMovimenti=0;
                migliorato=false;
            }
            if(indiceMovimenti>STAZIONARIETA){
                System.out.println("Uscito per stazionarieta");
                break;
            }
            indiceMovimenti++;
            
        }
        globalFitnessMIgliore.stampa();
       
        

    }

}
