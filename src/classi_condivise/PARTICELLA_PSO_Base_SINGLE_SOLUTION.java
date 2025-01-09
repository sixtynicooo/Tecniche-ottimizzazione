/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;



/**
 *
 * @author sixty
 */
public class PARTICELLA_PSO_Base_SINGLE_SOLUTION implements utility.FitnessEntity {
    static VariabilGlobali  variabilGlobali;
    double[] ArrDoublePos;          // array double parametri

    
    double[] ArrDoubleVel;          // array double parametri
    public double fitness;          // array double parametri

    double[] ArrDoublePosMiglioreLocale;          // array double parametri
    double fitnessLocaleMigliore;          // array double parametri

    
    static random rand = new random();
    static Fitness fitnessClass=new Fitness();
    static utility utilita=new utility();
    
public double[] getArrDoublePos() {
        return this.ArrDoublePos;
    }
public void setArrDoublePos(double[] ARR_DOUBLE_POS) {
        this.ArrDoublePos = ARR_DOUBLE_POS;
    }


    public double getFitness() {
        return this.fitness;
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    public PARTICELLA_PSO_Base_SINGLE_SOLUTION(VariabilGlobali  variabilGlobali) {
        
        PARTICELLA_PSO_Base_SINGLE_SOLUTION.variabilGlobali=new VariabilGlobali();
        PARTICELLA_PSO_Base_SINGLE_SOLUTION.variabilGlobali=variabilGlobali;
        this.ArrDoublePos = new double[variabilGlobali.DIM_ARR_DOUBLE];
        this.ArrDoubleVel = new double[variabilGlobali.DIM_ARR_DOUBLE];
        
        ArrDoublePosMiglioreLocale=new double[variabilGlobali.DIM_ARR_DOUBLE];
        // inizializzo ARR_DOUBLE
        for (int i = 0; i < variabilGlobali.DIM_ARR_DOUBLE; i++) {
            this.ArrDoublePos[i] = rand.generateRandomDouble(variabilGlobali.ARR_DOUBLE_MIN[i], variabilGlobali.ARR_DOUBLE_MAX[i]);
        }
    }

    public void aggiornaVelocitaPosizione(int DIM_ARR_DOUBLE, double w, double c1, double c2, double[] ARR_DOUBLE_MIN, double[] ARR_DOUBLE_MAX, PARTICELLA_PSO_Base_SINGLE_SOLUTION globalFitnessMIgliore) {
        for (int d = 0; d < DIM_ARR_DOUBLE; d++) {
            double r1 = rand.generateRandomDouble(0, 1); // Fattore casuale per componente cognitiva
            double r2 = rand.generateRandomDouble(0, 1); // Fattore casuale per componente sociale
            this.ArrDoubleVel[d]
                    = w * ArrDoubleVel[d]
                    + c1 * r1 * (this.ArrDoublePosMiglioreLocale[d] - this.ArrDoublePos[d])
                    + c2 * r2 * (globalFitnessMIgliore.getArrDoublePos()[d] - this.ArrDoublePos[d]);
            
            this.ArrDoublePos[d]+=this.ArrDoubleVel[d];
            utilita.verificaIntervalloDouble(this.ArrDoublePos[d], ARR_DOUBLE_MIN[d], ARR_DOUBLE_MAX[d]);
             
        }
        this.calcoloFitnessPos();
    }


    public void calcoloFitnessPos() {
    // Calcolo del fitness attuale
    this.fitness = fitnessClass.fitness(this.ArrDoublePos);

    // Verifica miglioramento locale in base al tipo di problema
    this.fitnessLocaleMigliore=utilita.verificaMiglioramentoLocale(this.fitness,this.ArrDoublePos,this.fitnessLocaleMigliore,this.ArrDoublePosMiglioreLocale,variabilGlobali.problemaMassimizzareMinimizzare);
}

    public boolean aggiornaFitnessGlobale(PARTICELLA_PSO_Base_SINGLE_SOLUTION globalFitnessMIgliore) {
        return utilita.aggiornaFitnessGlobale(globalFitnessMIgliore,this.fitness,this.ArrDoublePos,variabilGlobali.problemaMassimizzareMinimizzare);
       
    }

    public void stampa() {
         System.out.println(" fitness "+ fitness+" x= "+ArrDoublePos[0]+" y= "+ArrDoublePos[1]);
    }



}
