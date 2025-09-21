/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BAT;





import static BAT.INDIVIDUO_BAT_SINGLE_SOLUTION.rand;
import classi_condivise.Fitness;
import classi_condivise.VariabilGlobali;
import classi_condivise.Variabili_Fitness_Migliori.Variabili_Individuo;
import classi_condivise.random;
import classi_condivise.utility;

/**
 *
 * @author sixty
 */
public class BAT_BASE_SINGLE_SOLUTION{
   // variabili globali
    VariabilGlobali  variabilGlobali;

    static BAT.INDIVIDUO_BAT_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static BAT.INDIVIDUO_BAT_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui
    


    static random rand = new random();
    static Fitness fitnessClass=new Fitness();
    static utility utilita=new utility();
    // Costruttore della classe PSO_Base
    public BAT_BASE_SINGLE_SOLUTION() {
                // resetto soluzioni txt
        utilita.resetFile("BAT.txt");
        variabilGlobali=new VariabilGlobali();

    }


    public void run() {
        inizializza();
        movimenti();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new BAT.INDIVIDUO_BAT_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
           globalFitnessMIgliore= new BAT.INDIVIDUO_BAT_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new BAT.INDIVIDUO_BAT_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
            listaIndividui[i].calcoloFitnessPos( variabilGlobali);
        }
        globalFitnessMIgliore.calcoloFitnessPos(variabilGlobali);
        
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore,variabilGlobali);
        }
    }

    private void movimenti() {
        int indiceMovimenti=0;
        boolean migliorato=false;
        double BAT_A=variabilGlobali.A_MAX;
        for (long  movimento = 0; movimento < variabilGlobali.ITERAZIONI; movimento++) {
            // Aggiorna la velocità
            BAT_A*=variabilGlobali.ALFA_BAT;
            for(int individuo=0;individuo<variabilGlobali.NUM_INDIVIDUO;individuo++){
                listaIndividui[individuo].aggiornaVelocitaPosizione(globalFitnessMIgliore,variabilGlobali);
                INDIVIDUO_BAT_SINGLE_SOLUTION tmpBat=new INDIVIDUO_BAT_SINGLE_SOLUTION(variabilGlobali);
                for (int d = 0; d < variabilGlobali.DIM_ARR_DOUBLE; d++) {
                    // prima salvo qui così non viene sovrascritto nel pipistrello principale
                    tmpBat.variabili_Individuo.arrDouble[d]=listaIndividui[individuo].variabili_Individuo.arrDouble[d]+( rand.generateRandomDouble(-1, 1)*BAT_A);
                    
                    // 
                }
                tmpBat.calcoloFitnessPos(variabilGlobali);
                if(utilita.verificaMiglioramento(variabilGlobali.problemaMassimizzareMinimizzare, tmpBat.getFitness(), listaIndividui[individuo].getFitness())){
                    listaIndividui[individuo].variabili_Individuo=tmpBat.variabili_Individuo.copiaVariabiliIndividuo(variabilGlobali);
                }
                
                
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
                globalFitnessMIgliore.stampa(movimento,"BAT.txt",variabilGlobali);
                indiceMovimenti=0;
                migliorato=false;
                // rimeetto al massimo le A
                 BAT_A=variabilGlobali.A_MAX;
                
            }
            if(indiceMovimenti>variabilGlobali.STAZIONARIETA){
                System.out.println("Uscito per stazionarieta");
                break;
            }
            indiceMovimenti++;
            
        }
       
        

    }

}