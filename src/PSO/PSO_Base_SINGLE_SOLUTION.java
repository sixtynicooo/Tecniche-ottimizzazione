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
public class PSO_Base_SINGLE_SOLUTION {
    // variabili globali
    VariabilGlobali  variabilGlobali;

    static PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui
    



    // Costruttore della classe PSO_Base
    public PSO_Base_SINGLE_SOLUTION() {
        variabilGlobali=new VariabilGlobali();

    }


    public void run() {
        inizializza();
        movimenti();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
           globalFitnessMIgliore= new PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
            listaIndividui[i].calcoloFitnessPos(variabilGlobali);
        }
        globalFitnessMIgliore.calcoloFitnessPos(variabilGlobali);
        
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore,variabilGlobali);
        }
    }

    private void movimenti() {
        int indiceMovimenti=0;
        boolean migliorato=false;

        for (long  movimento = 0; movimento < variabilGlobali.ITERAZIONI; movimento++) {
            // Aggiorna la velocità
            for(int individuo=0;individuo<variabilGlobali.NUM_INDIVIDUO;individuo++){
                listaIndividui[individuo].aggiornaVelocitaPosizione(globalFitnessMIgliore,variabilGlobali);
                //System.out.println("movimento "+movimento+" fitness "+ listaIndividui[individuo].fitness);
            }
            // aggiorna globale
            for(int individuo=0;individuo<variabilGlobali.NUM_INDIVIDUO;individuo++){
                if(listaIndividui[individuo].aggiornaFitnessGlobale(globalFitnessMIgliore,variabilGlobali)){
                    migliorato=true;
                }
            }
            // semigliorato resetto
            if(migliorato){
                System.out.print("N iterazione "+movimento);
                globalFitnessMIgliore.stampa(movimento,"PSO.txt");
                indiceMovimenti=0;
                migliorato=false;
            }
            if(indiceMovimenti>variabilGlobali.STAZIONARIETA){
                System.out.println("Uscito per stazionarieta");
                break;
            }
            indiceMovimenti++;
            
        }
        //globalFitnessMIgliore.stampa();
       
        

    }

}
