/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PSO;

import BAT.BAT_BASE_SINGLE_SOLUTION;
import classi_condivise.VariabilGlobali;
import classi_condivise.utility;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import utility.loggerAsync;
import utility.utilityWriteFileAsync;

/**
 *
 * @author sixty
 */
public class PSO_Base_SINGLE_SOLUTION {
    loggerAsync asyncLogger = new loggerAsync();
    final String nameFile="PSO.txt";
    utilityWriteFileAsync writeFile=new utilityWriteFileAsync(nameFile);
    

    // variabili globali
    VariabilGlobali variabilGlobali;

    static PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION[] listaIndividui;    // lista individui
    static PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION globalFitnessMIgliore;    // lista individui
    // Costruttore della classe PSO_Base
    public PSO_Base_SINGLE_SOLUTION() {
        // resetto soluzioni txt
        utility.resetFile(nameFile);
        variabilGlobali = new VariabilGlobali();

    }

    public void run() {
        inizializza();
        
        movimenti();
        
    }

    private void inizializza() {
        // Creazione dell'array di individui
        listaIndividui = new PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION[VariabilGlobali.NUM_INDIVIDUO];
        globalFitnessMIgliore = new PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
        // Inizializzazione degli individui
        
        for (int i = 0; i < VariabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i] = new PSO.PARTICELLA_PSO_Base_SINGLE_SOLUTION(variabilGlobali); // Crea un nuovo individuo
            listaIndividui[i].calcoloFitnessPos(variabilGlobali);
            listaIndividui[i].variabili_Individuo.fitnessLocaleMigliore= listaIndividui[i].variabili_Individuo.fitness;
        }
        globalFitnessMIgliore.calcoloFitnessPos(variabilGlobali);

        for (int i = 0; i < VariabilGlobali.NUM_INDIVIDUO; i++) {
            listaIndividui[i].aggiornaFitnessGlobale(globalFitnessMIgliore, variabilGlobali);
        }
    }

    private void movimenti() {
        int indiceMovimenti = 0;
        boolean migliorato = false;

        for (long movimento = 0; movimento < VariabilGlobali.ITERAZIONI; movimento++) {
            // Aggiorna la velocità
            for (int individuo = 0; individuo < VariabilGlobali.NUM_INDIVIDUO; individuo++) {
             listaIndividui[individuo].aggiornaVelocitaPosizione(globalFitnessMIgliore, variabilGlobali);
            }
            // aggiorna globale
            for (int individuo = 0; individuo < VariabilGlobali.NUM_INDIVIDUO; individuo++) {
                if (listaIndividui[individuo].aggiornaFitnessGlobale(globalFitnessMIgliore, variabilGlobali)) {
                    migliorato = true;
                }
            }
            // semigliorato resetto
            if (migliorato) {
                asyncLogger.add("N iterazione " + movimento);
                globalFitnessMIgliore.stampa(movimento, nameFile,variabilGlobali,asyncLogger,writeFile);
                indiceMovimenti = 0;
                migliorato = false;
            }
            if (indiceMovimenti > VariabilGlobali.STAZIONARIETA) {
                asyncLogger.add("Uscito per stazionarieta");
                break;
            }
            indiceMovimenti++;

        }
        //globalFitnessMIgliore.stampa();
        try {
             asyncLogger.close();
         } catch (InterruptedException ex) {
             Logger.getLogger(BAT_BASE_SINGLE_SOLUTION.class.getName()).log(Level.SEVERE, null, ex);
         }
        writeFile.close();

    }

}
