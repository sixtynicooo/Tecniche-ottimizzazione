/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LUCCIOLE;

import classi_condivise.VariabilGlobali;
import classi_condivise.utility;

/**
 *
 * @author sixty
 */
public class LUCCIOLA_BASE_SINGLE_SOLUTION {
     // variabili globali
    VariabilGlobali  variabilGlobali;

    static LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION[] listaIndividuiCopia ;
    static LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui
    


static utility utilita=new utility();
    // Costruttore della classe PSO_Base
    public LUCCIOLA_BASE_SINGLE_SOLUTION() {
        variabilGlobali=new VariabilGlobali();

    }


    public void run() {
        inizializza();
        movimenti();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
           globalFitnessMIgliore= new LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        listaIndividuiCopia = new LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
        // Inizializzazione degli individui
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
            listaIndividui[i].calcoloFitness(variabilGlobali);
            listaIndividuiCopia[i]=new LUCCIOLE.INDIVIDUO_LUCCIOLA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
            listaIndividuiCopia[i].variabili_Individuo=listaIndividui[i].variabili_Individuo.copiaVariabiliIndividuo(variabilGlobali);
        }
        globalFitnessMIgliore.calcoloFitness(variabilGlobali);
        
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore,variabilGlobali);
        }
        
    }

    private void movimenti() {
        int indiceMovimenti=0;
        boolean migliorato=false;
        double ALFALucciola=variabilGlobali.ALFALucciola_MAX;
        for (long  movimento = 0; movimento < variabilGlobali.ITERAZIONI; movimento++) {
            
            
            // Aggiorna la velocità
            for(int individuo=0;individuo<variabilGlobali.NUM_INDIVIDUO;individuo++){
                listaIndividui[individuo].aggiornaVelocitaPosizione(globalFitnessMIgliore,individuo,listaIndividuiCopia,ALFALucciola,variabilGlobali);
                // Calcolo del fitness aggiornato
                listaIndividui[individuo].calcoloFitness(variabilGlobali);
                //System.out.println("movimento "+movimento+" fitness "+ listaIndividui[individuo].fitness);
            }
            // aggiorna globale
            for(int individuo=0;individuo<variabilGlobali.NUM_INDIVIDUO;individuo++){
                if(listaIndividui[individuo].aggiornaFitnessGlobale(globalFitnessMIgliore,variabilGlobali)){
                    migliorato=true;
                }
            }
            // se migliorato resetto
            if(migliorato){
                System.out.print("N iterazione "+movimento);
                globalFitnessMIgliore.stampa(movimento,"Lucciole.txt",variabilGlobali);
                ALFALucciola=variabilGlobali.ALFALucciola_MAX;
                indiceMovimenti=0;
                migliorato=false;
            }
            if(indiceMovimenti>variabilGlobali.STAZIONARIETA){
                System.out.println("Uscito per stazionarieta");
                break;
            }
            // copia lucciole
            for(int individuo=0;individuo<variabilGlobali.NUM_INDIVIDUO;individuo++){
                listaIndividuiCopia[individuo].variabili_Individuo=listaIndividui[individuo].variabili_Individuo.copiaVariabiliIndividuo(variabilGlobali);
            }
            indiceMovimenti++;
            // diminuisco theta
            ALFALucciola*=variabilGlobali.THETA;
            
            if(ALFALucciola<variabilGlobali.ALFALucciola_MIN){
                
                 
                 ALFALucciola=variabilGlobali.ALFALucciola_MAX;
                
            }
        }
       
        

    }

    
}
