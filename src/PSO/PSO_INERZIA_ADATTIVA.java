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
public class PSO_INERZIA_ADATTIVA {

    // variabili globali

    // uguali per ogni algoritmo
    static int NUM_INDIVIDUO;        // Numero di particelle
    static long ITERAZIONI;         // generazioni o movimento 
    static long STAZIONARIETA;         // generazioni o movimento 
    static classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA[] listaIndividui;    // lista individui
    static classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA globalFitnessMIgliore;    // lista individui
    

    static int DIM_ARR_DOUBLE;          // array double parametri
    static double[] ARR_DOUBLE_MIN;          // array double parametri
    static double[] ARR_DOUBLE_MAX;          // array double parametri

    // spazio variabili
    // parametri PSO
    // Parametri PARTICELLA_PSO_INERZIA_ADATTIVA: ora ogni parametro ARR_DOUBLE ha l'inerzia personalizzata
    static double[] w_ARR_DOUBLE;    // Array dell'inerzia per ogni parametro
    static double[] w_ARR_DOUBLE_MIN;    // Array dell'inerzia per ogni parametro
    static double[] w_ARR_DOUBLE_MAX;    // Array dell'inerzia per ogni parametro
    static double c1;              // Parametro cognitivo
    static double c2;              // Parametro sociale
    static random rand = new random();

    
 public  PSO_INERZIA_ADATTIVA(int NUM_INDIVIDUO, long ITERAZIONI, long STAZIONARIETA, 
         int DIM_ARR_DOUBLE, double[] ARR_DOUBLE, double[] ARR_DOUBLE_MIN, 
         double[] ARR_DOUBLE_MAX, double[] w_ARR_DOUBLE, double[] w_ARR_DOUBLE_MIN, 
         double[] w_ARR_DOUBLE_MAX, double c1, double c2) {
        PSO_INERZIA_ADATTIVA.NUM_INDIVIDUO = NUM_INDIVIDUO;
        PSO_INERZIA_ADATTIVA.ITERAZIONI = ITERAZIONI;
        PSO_INERZIA_ADATTIVA.STAZIONARIETA=STAZIONARIETA;
        PSO_INERZIA_ADATTIVA.DIM_ARR_DOUBLE = DIM_ARR_DOUBLE;
        PSO_INERZIA_ADATTIVA.ARR_DOUBLE_MIN = ARR_DOUBLE_MIN;
        PSO_INERZIA_ADATTIVA.ARR_DOUBLE_MAX = ARR_DOUBLE_MAX;
        
        
        PSO_INERZIA_ADATTIVA.w_ARR_DOUBLE=w_ARR_DOUBLE;
        PSO_INERZIA_ADATTIVA.w_ARR_DOUBLE_MIN=w_ARR_DOUBLE_MIN;
        PSO_INERZIA_ADATTIVA.w_ARR_DOUBLE_MAX=w_ARR_DOUBLE_MAX;
        PSO_INERZIA_ADATTIVA.c1 = c1;
        PSO_INERZIA_ADATTIVA.c2 = c2;

    }



    public void run() {
        inizializza();
        movimenti();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA[NUM_INDIVIDUO];
           globalFitnessMIgliore= new classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA(DIM_ARR_DOUBLE, ARR_DOUBLE_MIN, ARR_DOUBLE_MAX); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new classi_condivise.PARTICELLA_PSO_INERZIA_ADATTIVA(DIM_ARR_DOUBLE, ARR_DOUBLE_MIN, ARR_DOUBLE_MAX); // Crea un nuovo individuo
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
        long indiceMovimenti=0;
        boolean migliorato=false;

        for (long  movimento = 0; movimento < ITERAZIONI; movimento++) {
            // Calcola l'inerzia per l'iterazione corrente
            for(int inerzia=0;inerzia<DIM_ARR_DOUBLE;inerzia++){
                
                double fattoreStazionarieta = 1.0 - (double) indiceMovimenti / (double) STAZIONARIETA;

// Genera un numero casuale tra 0 e 1 (moltiplicato per una costante per variare l'intensità del fattore casuale)
double fattoreCasuale = rand.generateRandomDouble(0, 1);  // Restituisce un numero casuale tra 0 e 1

// Puoi anche personalizzare il range del numero casuale. Per esempio, per un range tra 0.5 e 1.5:
double fattoreCasualePersonalizzato = rand.generateRandomDouble(0, 1.5);  // Tra 0.5 e 1.5

// Calcolo dell'inerzia
w_ARR_DOUBLE[inerzia] = w_ARR_DOUBLE_MAX[inerzia] - (w_ARR_DOUBLE_MAX[inerzia] - w_ARR_DOUBLE_MIN[inerzia]) * fattoreStazionarieta * fattoreCasualePersonalizzato;

                
                
            }
            
            // Aggiorna la velocità
            for(int individuo=0;individuo<NUM_INDIVIDUO;individuo++){
                
                listaIndividui[individuo].aggiornaVelocitaPosizione(DIM_ARR_DOUBLE,w_ARR_DOUBLE,c1,c2,ARR_DOUBLE_MIN, ARR_DOUBLE_MAX,globalFitnessMIgliore,indiceMovimenti);
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
