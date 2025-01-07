/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;



/**
 *
 * @author sixty
 */
public class PARTICELLA_PSO_Base {

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
    public PARTICELLA_PSO_Base(int DIM_ARR_DOUBLE, double[] ARR_DOUBLE_MIN, double[] ARR_DOUBLE_MAX) {
        this.ArrDoublePos = new double[DIM_ARR_DOUBLE];
        this.ArrDoubleVel = new double[DIM_ARR_DOUBLE];
        
        ArrDoublePosMiglioreLocale=new double[DIM_ARR_DOUBLE];

        for (int i = 0; i < DIM_ARR_DOUBLE; i++) {
            this.ArrDoublePos[i] = rand.generateRandomDouble(ARR_DOUBLE_MIN[i], ARR_DOUBLE_MAX[i]);
        }
    }

    public void aggiornaVelocitaPosizione(int DIM_ARR_DOUBLE, double w, double c1, double c2, double[] ARR_DOUBLE_MIN, double[] ARR_DOUBLE_MAX, PARTICELLA_PSO_Base globalFitnessMIgliore) {
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
        this.fitness=fitnessClass.fitness(this.ArrDoublePos);
        if(this.fitness<this.fitnessLocaleMigliore){
            this.fitnessLocaleMigliore=this.fitness;
            this.ArrDoublePosMiglioreLocale = ArrDoublePos.clone();
        }
    }

    public boolean aggiornaFitnessGlobale(PARTICELLA_PSO_Base globalFitnessMIgliore) {
        boolean migliorato=false;
        if(this.fitness<globalFitnessMIgliore.getFitness()){
            globalFitnessMIgliore.setFitness(this.fitness);
            globalFitnessMIgliore.setArrDoublePos(this.ArrDoublePos.clone());
            globalFitnessMIgliore.stampa();
        }
        return migliorato;
       
    }

    public void stampa() {
         System.out.println(" fitness "+ fitness+" x= "+ArrDoublePos[0]+" y= "+ArrDoublePos[1]);
    }



}
