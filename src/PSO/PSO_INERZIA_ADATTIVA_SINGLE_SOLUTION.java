/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PSO;

import BAT.BAT_BASE_SINGLE_SOLUTION;
import classi_condivise.VariabilGlobali;
import classi_condivise.random;
import classi_condivise.utility;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.loggerAsync;
import utility.utilityWriteFileAsync;

/**
 *
 * @author sixty
 */
public class PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION {
    loggerAsync asyncLogger = new loggerAsync();
    final String nameFile="PSO_Adattiva.txt";
    utilityWriteFileAsync writeFile=new utilityWriteFileAsync(nameFile);
    // variabili globali

    VariabilGlobali variabilGlobali;

    static PSO.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static PSO.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui

    static random rand = new random();
static utility utilita=new utility();
    // Costruttore della classe PSO_Base
    public PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION() {
        // resetto soluzioni txt
        utilita.resetFile(nameFile);
        variabilGlobali = new VariabilGlobali();

    }

    public void run() {
        inizializza();
        movimenti();
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new PSO.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION[variabilGlobali.NUM_INDIVIDUO];
        globalFitnessMIgliore = new PSO.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        // Inizializzazione degli individui
        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new PSO.PARTICELLA_PSO_INERZIA_ADATTIVA_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
            listaIndividui[i].calcoloFitnessPos(variabilGlobali);
             listaIndividui[i].variabili_Individuo.fitnessLocaleMigliore= listaIndividui[i].variabili_Individuo.fitness;
        }
        globalFitnessMIgliore.calcoloFitnessPos( variabilGlobali);

        for (int i = 0; i < variabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore,variabilGlobali);
        }
    }

    private void movimenti() {
        // mantengo double perchè fattoreStazionarieta fa un rapporto e il numeratore deve essere il tipo corretto double
        double indiceMovimenti = 0;
        boolean migliorato = false;
        double fattoreStazionarieta = 0;
        double w_ARR_DOUBLE=variabilGlobali.w_ARR_DOUBLE_MAX;
        for (long movimento = 0; movimento < variabilGlobali.ITERAZIONI; movimento++) {
            w_ARR_DOUBLE*= variabilGlobali.w_Riduzione;
            // Aggiorna la velocità
            for (int individuo = 0; individuo < variabilGlobali.NUM_INDIVIDUO; individuo++) {
                listaIndividui[individuo].aggiornaVelocitaPosizione(globalFitnessMIgliore,variabilGlobali,w_ARR_DOUBLE);
            }
            // aggiorna globale
            for (int individuo = 0; individuo < variabilGlobali.NUM_INDIVIDUO; individuo++) {
                if (listaIndividui[individuo].aggiornaFitnessGlobale(globalFitnessMIgliore,variabilGlobali)) {
                    migliorato = true;
                }
            }
            // semigliorato resetto
            if(migliorato){
                asyncLogger.add("N iterazione " + movimento);
                
                globalFitnessMIgliore.stampa(movimento,nameFile,variabilGlobali,asyncLogger,writeFile);
                indiceMovimenti = 0;
                migliorato = false; 
              //w_ARR_DOUBLE= variabilGlobali.w_ARR_DOUBLE_MAX;
                
            }
            if (indiceMovimenti > variabilGlobali.STAZIONARIETA) {
                asyncLogger.add("Uscito per stazionarieta");
                break;
            }
            indiceMovimenti++;
            if(w_ARR_DOUBLE< variabilGlobali.w_ARR_DOUBLE_MIN){
                w_ARR_DOUBLE= variabilGlobali.w_ARR_DOUBLE_MAX;
        }

        }
        try {
             asyncLogger.close();
         } catch (InterruptedException ex) {
             Logger.getLogger(BAT_BASE_SINGLE_SOLUTION.class.getName()).log(Level.SEVERE, null, ex);
         }
        writeFile.close();
        

    }

}
