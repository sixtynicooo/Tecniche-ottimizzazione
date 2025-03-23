/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classi_condivise;

import static classi_condivise.VariabilGlobali.ARR_DOUBLE_MIN;
import java.util.Arrays;

/**
 *
 * @author sixty
 */
public class gestioneArray1DMultidimensionali {

//        // Dati di esempio
//        double[] array = new double[100];
//        int[][] offsets = {
//            {0, 10},  // Array 1D con 10 elementi
//            {10, 20}, // Array 1D con 10 elementi
//        };
//        int[][] listaStrutturaDati = {
//            {10}, // Array 1D con 10 elementi
//            {10}, // Array 1D con 10 elementi
//        };
//
//        // Test set e get
//        gestioneArray1DMultidimensionali.setArray1D(array, offsets, listaStrutturaDati, 1, 2, 25.5); // Imposta il valore 25.5
//        double value = gestioneArray1DMultidimensionali.getArray1D(array, offsets, listaStrutturaDati, 0, 5); // Ottiene il valore
//        System.out.println("Valore ottenuto da array 1D: " + value);
////
////        // Test stampa
//        gestioneArray1DMultidimensionali.stampaArray1D(array, offsets, listaStrutturaDati, 1); // Stampa l'array 1D
//        double[] array = new double[100];
//        int[][] offsets = {{0, 27}, {27, 54}};
//        int[][] listaStrutturaDati = {{3, 3}, {3, 3}}; // Due matrici 3x3
//
//// Impostare un valore nella matrice
//        gestioneArray1DMultidimensionali.setMatrice(array, offsets, listaStrutturaDati, 1, 2, 1, 3.14);
//
//// Ottieni un valore dalla matrice
//        double value = gestioneArray1DMultidimensionali.getMatrice(array, offsets, listaStrutturaDati, 1,2, 1);
//        System.out.println("Valore ottenuto: " + value);
//
//// Stampa una matrice
//        gestioneArray1DMultidimensionali.stampaMatrice(array, offsets, listaStrutturaDati, 1);
    // Inizializzazione dell'array di cubi
//        double[] array = new double[27];  // Array principale per memorizzare i cubi
//        int[][] offsets = {{0, 27}};  // Offset per il cubo
//        int[][] listaStrutturaDati = {{3, 3, 3}};  // Un cubo 3x3x3
//            gestioneArray1DMultidimensionali.setCubo(array, offsets, listaStrutturaDati, 0,0,1,2, 20.5);  // Imposta valore errore
//        // Test di stampaCubo
//        gestioneArray1DMultidimensionali.stampaCubo(array, offsets, listaStrutturaDati, 0);  // Stampa il cubo
    // Funzione per calcolare la dimensione totale dell'array double[]
    public static int calcolaDimensioneTotale(int[][] listaStrutturaDati) {
        int totalSize = 0;
        for (int[] struttura : listaStrutturaDati) {
            totalSize += getSize(struttura);
        }
        return totalSize;
    }

    // Funzione per calcolare il numero totale di elementi di una singola struttura
    public static int getSize(int[] dimensioni) {
        int size = 1;
        for (int dim : dimensioni) {
            size *= dim; // Moltiplica le dimensioni per ottenere il numero totale di elementi
        }
        return size;
    }

    // Funzione per calcolare gli offset start e end per ogni struttura dati
    public static int[][] calcolaOffset(int[][] listaStrutturaDati) {
        int totalSize = 0;
        int[][] offsets = new int[listaStrutturaDati.length][2]; // Matrice per start-end

        for (int i = 0; i < listaStrutturaDati.length; i++) {
            int size = getSize(listaStrutturaDati[i]); // Calcola la dimensione della struttura
            offsets[i][0] = totalSize;                // Start
            totalSize += size;
            offsets[i][1] = totalSize;            // End
            // non uso -1 perchè così uso < nei for in modo tradizionale
            //offsets[i][1] = totalSize - 1;            // End
        }

        return offsets;
    }

    // --- METODI SPECIFICI PER ARRAY 1D ---
    /**
     * Imposta un valore nell'array 1D in base alla posizione calcolata usando
     * gli offset e l'indice.
     *
     * <p>
     * Questo metodo calcola la posizione di un valore all'interno di un array
     * 1D a partire dagli offset specificati e imposta il valore nella posizione
     * calcolata. Gli offset definiscono l'intervallo valido per l'indice e il
     * valore viene inserito solo se l'indice è compreso nell'intervallo.</p>
     *
     * @param <T> il tipo dell'array e del valore da inserire. Può essere
     * qualsiasi tipo di oggetto.
     * @param array l'array 1D in cui il valore deve essere inserito.
     * @param offsets una matrice 2D di offset, dove ogni elemento rappresenta
     * l'inizio e la fine di un intervallo.
     * @param listaStrutturaDati una matrice 2D che contiene informazioni sulla
     * struttura dei dati (non utilizzato direttamente in questo metodo).
     * @param strutturaIndex l'indice della struttura di dati all'interno della
     * matrice `offsets` che deve essere utilizzata.
     * @param index l'indice all'interno della struttura, usato per calcolare la
     * posizione finale nell'array.
     * @param value il valore da inserire nell'array 1D, del tipo generico `T`.
     *
     * @throws IndexOutOfBoundsException se l'indice è fuori dall'intervallo
     * valido calcolato con gli offset.
     */
    public static <T> void setArray1D(T[] array, int[][] offsets, int[][] listaStrutturaDati,
            int strutturaIndex, int index, T value) {
        // Calcola la posizione di partenza nell'array 1D usando l'offset della struttura.
        int start = offsets[strutturaIndex][0];
        int end = offsets[strutturaIndex][1];

        // Verifica che l'indice sia all'interno dell'intervallo valido.
        if (index < 0 || index >= end) {
            throw new IndexOutOfBoundsException("Indice fuori dall'intervallo valido per l'array 1D.");
        }

        // Imposta il valore nell'array 1D.
        array[start + index] = value;
    }

    /**
     * Ottiene un valore da una specifica posizione di un array 1D.
     *
     * @param <T> Il tipo di elemento dell'array.
     * @param array L'array principale che contiene tutte le strutture dati.
     * @param offsets Matrice degli offset [start, end] per ogni struttura dati.
     * @param listaStrutturaDati Lista delle dimensioni delle strutture dati
     * (matrici, cubi, array).
     * @param strutturaIndex L'indice della struttura dati da leggere.
     * @param index La posizione nell'array 1D relativo a questa struttura.
     * @return Il valore dell'elemento alla posizione specificata.
     * @throws IndexOutOfBoundsException Se l'indice è fuori dall'intervallo
     * valido.
     */
    public static <T> T getArray1D(T[] array, int[][] offsets, int[][] listaStrutturaDati, int strutturaIndex, int index) {
        // Calcola la posizione di partenza nell'array 1D usando l'offset della struttura.
        int start = offsets[strutturaIndex][0];
        // Calcola la posizione finale per il controllo degli indici.
        int end = offsets[strutturaIndex][1];

        // Verifica che l'indice sia all'interno dell'intervallo valido.
        if (index < 0 || index >= (end - start)) {
            throw new IndexOutOfBoundsException("Indice fuori dall'intervallo valido per l'array 1D.");
        }

        // Restituisce il valore nell'array 1D.
        return array[start + index];
    }

    /**
     * Stampa il contenuto di un array 1D specifico.
     *
     * @param <T> Il tipo di elemento dell'array.
     * @param array L'array principale che contiene tutte le strutture dati.
     * @param offsets Matrice degli offset [start, end] per ogni struttura dati.
     * @param listaStrutturaDati Lista delle dimensioni delle strutture dati
     * (matrici, cubi, array).
     * @param strutturaIndex L'indice della struttura dati da stampare.
     */
    public static <T> void stampaArray1D(T[] array, int[][] offsets, int[][] listaStrutturaDati, int strutturaIndex) {
        // Calcola la posizione di partenza nell'array 1D usando l'offset della struttura.
        int start = offsets[strutturaIndex][0];
        // Calcola la posizione di fine (sfruttando il secondo valore nell'offset).
        int end = offsets[strutturaIndex][1];

        // Verifica che gli offset siano corretti.
        if (start < 0 || end > array.length || start >= end) {
            throw new IndexOutOfBoundsException("Gli offset per l'array 1D sono fuori intervallo.");
        }

        // Stampa l'array 1D nella forma di una sottosequenza dell'array principale.
        System.out.println("Array 1D: " + Arrays.toString(Arrays.copyOfRange(array, start, end)));
    }

    // --- METODI SPECIFICI PER MATRICI ---
    /**
     * Imposta un valore in una specifica posizione di una matrice, all'interno
     * di un array 1D.
     *
     * @param <T> Tipo dell'array numerico (es. Double, Integer, etc.).
     * @param array L'array principale che contiene tutte le strutture dati.
     * @param offsets Matrice degli offset [start, end] per ogni struttura dati.
     * @param listaStrutturaDati Lista delle dimensioni delle strutture dati
     * (matrici, cubi, array).
     * @param strutturaIndex L'indice della struttura dati da modificare.
     * @param row La riga della matrice in cui inserire il valore.
     * @param col La colonna della matrice in cui inserire il valore.
     * @param value Il valore da inserire nella matrice.
     */
    public static <T> void setMatrice(T[] array, int[][] offsets, int[][] listaStrutturaDati, int strutturaIndex, int row, int col, T value) {
        // Calcola la posizione di partenza nell'array 1D usando l'offset della struttura.
        int start = offsets[strutturaIndex][0];
        // Ottieni il numero di righe e colonne dalla listaStrutturaDati.
        int rows = listaStrutturaDati[strutturaIndex][0];
        int cols = listaStrutturaDati[strutturaIndex][1];

        // Verifica che le righe e le colonne siano all'interno dell'intervallo valido.
        if (row < 0 || row >= rows) {
            throw new IndexOutOfBoundsException("Riga fuori intervallo valido per la matrice.");
        }
        if (col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Colonna fuori intervallo valido per la matrice.");
        }

        // Calcola la posizione nell'array 1D (riga * numero colonne + colonna).
        array[start + row * cols + col] = value;
    }

    /**
     * Ottiene un valore da una specifica posizione di una matrice all'interno
     * di un array 1D.
     *
     * @param <T> Tipo dell'array numerico (es. Double, Integer, etc.).
     * @param array L'array principale che contiene tutte le strutture dati.
     * @param offsets Matrice degli offset [start, end] per ogni struttura dati.
     * @param listaStrutturaDati Lista delle dimensioni delle strutture dati
     * (matrici, cubi, array).
     * @param strutturaIndex L'indice della struttura dati da leggere.
     * @param row La riga della matrice da cui ottenere il valore.
     * @param col La colonna della matrice da cui ottenere il valore.
     * @return Il valore della matrice alla posizione specificata.
     */
    public static <T> T getMatrice(T[] array, int[][] offsets, int[][] listaStrutturaDati, int strutturaIndex, int row, int col) {
        // Calcola la posizione di partenza nell'array 1D usando l'offset della struttura.
        int start = offsets[strutturaIndex][0];
        // Ottieni il numero di righe e colonne dalla listaStrutturaDati.
        int rows = listaStrutturaDati[strutturaIndex][0];
        int cols = listaStrutturaDati[strutturaIndex][1];

        // Verifica che le righe e le colonne siano all'interno dell'intervallo valido.
        if (row < 0 || row >= rows) {
            throw new IndexOutOfBoundsException("Riga fuori intervallo valido per la matrice.");
        }
        if (col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Colonna fuori intervallo valido per la matrice.");
        }

        // Calcola la posizione nell'array 1D (riga * numero colonne + colonna).
        return array[start + row * cols + col];
    }

    /**
     * Stampa il contenuto di una matrice specifica.
     *
     * @param <T> Tipo dell'array numerico (es. Double, Integer, etc.).
     * @param array L'array principale che contiene tutte le strutture dati.
     * @param offsets Matrice degli offset [start, end] per ogni struttura dati.
     * @param listaStrutturaDati Lista delle dimensioni delle strutture dati
     * (matrici, cubi, array).
     * @param strutturaIndex L'indice della struttura dati da stampare.
     */
    public static <T> void stampaMatrice(T[] array, int[][] offsets, int[][] listaStrutturaDati, int strutturaIndex) {
        // Calcola la posizione di partenza nell'array 1D usando l'offset della struttura.
        int start = offsets[strutturaIndex][0];
        // Ottieni il numero di righe e colonne dalla listaStrutturaDati.
        int rows = listaStrutturaDati[strutturaIndex][0];
        int cols = listaStrutturaDati[strutturaIndex][1];

        // Verifica che gli offset siano corretti.
        if (start < 0 || start + rows * cols > array.length) {
            throw new IndexOutOfBoundsException("Gli offset per la matrice sono fuori intervallo.");
        }

        // Stampa la matrice.
        System.out.println("Matrice " + rows + "x" + cols + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[start + i * cols + j] + " ");
            }
            System.out.println();
        }
    }

    // --- METODI SPECIFICI PER CUBI ---
    /**
     * Imposta un valore in una specifica posizione di un cubo, all'interno di
     * un array 1D.
     *
     * @param <T> Tipo dell'array principale (es. Double).
     * @param array L'array principale che contiene tutte le strutture dati.
     * @param offsets Matrice degli offset [start, end] per ogni struttura dati.
     * @param listaStrutturaDati Lista delle dimensioni delle strutture dati
     * (matrici, cubi, array).
     * @param strutturaIndex L'indice della struttura dati da modificare.
     * @param x La coordinata X del cubo in cui inserire il valore. In questo
     * caso, `x` rappresenta l'indice della matrice (dimensione del cubo).
     * @param y La coordinata Y del cubo in cui inserire il valore. In questo
     * caso, `y` rappresenta la riga della matrice.
     * @param z La coordinata Z del cubo in cui inserire il valore. In questo
     * caso, `z` rappresenta la colonna della matrice.
     * @param value Il valore da inserire nel cubo.
     * @throws IndexOutOfBoundsException Se gli indici sono fuori dai limiti del
     * cubo.
     */
    public static <T> void setCubo(T[] array, int[][] offsets, int[][] listaStrutturaDati, int strutturaIndex, int x, int y, int z, T value) {
        // Controlla che gli indici siano validi.
        int dimX = listaStrutturaDati[strutturaIndex][0];
        int dimY = listaStrutturaDati[strutturaIndex][1];
        int dimZ = listaStrutturaDati[strutturaIndex][2];
        if (x < 0 || x >= dimX || y < 0 || y >= dimY || z < 0 || z >= dimZ) {
            throw new IndexOutOfBoundsException("Gli indici specificati sono fuori dai limiti del cubo.");
        }

        // Calcola la posizione nell'array 1D (x * (dimY * dimZ) + y * dimZ + z).
        int start = offsets[strutturaIndex][0];
        int strideY = listaStrutturaDati[strutturaIndex][1]; // dimY
        int strideZ = listaStrutturaDati[strutturaIndex][2]; // dimZ
        int posizione = start + x * (dimY * dimZ) + y * dimZ + z;
        System.out.println("pos " + posizione);

        // Imposta il valore nella posizione calcolata.
        array[posizione] = value;
    }

    /**
     * Ottiene un valore da una specifica posizione di un cubo all'interno di un
     * array 1D.
     *
     * @param <T> Tipo dell'array principale (es. Double).
     * @param array L'array principale che contiene tutte le strutture dati.
     * @param offsets Matrice degli offset [start, end] per ogni struttura dati.
     * @param listaStrutturaDati Lista delle dimensioni delle strutture dati
     * (matrici, cubi, array).
     * @param strutturaIndex L'indice della struttura dati da leggere.
     * @param x La coordinata X del cubo da cui ottenere il valore. In questo
     * caso, `x` rappresenta l'indice della matrice (dimensione del cubo).
     * @param y La coordinata Y del cubo da cui ottenere il valore. In questo
     * caso, `y` rappresenta la riga della matrice.
     * @param z La coordinata Z del cubo da cui ottenere il valore. In questo
     * caso, `z` rappresenta la colonna della matrice.
     * @return Il valore del cubo alla posizione specificata.
     * @throws IndexOutOfBoundsException Se gli indici sono fuori dai limiti del
     * cubo.
     */
    public static <T> T getCubo(T[] array, int[][] offsets, int[][] listaStrutturaDati, int strutturaIndex, int x, int y, int z) {
        // Controlla che gli indici siano validi.
        int dimX = listaStrutturaDati[strutturaIndex][0];
        int dimY = listaStrutturaDati[strutturaIndex][1];
        int dimZ = listaStrutturaDati[strutturaIndex][2];
        if (x < 0 || x >= dimX || y < 0 || y >= dimY || z < 0 || z >= dimZ) {
            throw new IndexOutOfBoundsException("Gli indici specificati sono fuori dai limiti del cubo.");
        }

        // Calcola la posizione nell'array 1D (x * (dimY * dimZ) + y * dimZ + z).
        int start = offsets[strutturaIndex][0];
        int strideY = listaStrutturaDati[strutturaIndex][1]; // dimY
        int strideZ = listaStrutturaDati[strutturaIndex][2]; // dimZ
        int posizione = start + x * (dimY * dimZ) + y * dimZ + z;

        // Restituisce il valore alla posizione calcolata.
        return array[posizione];
    }

    /**
     * Stampa il contenuto di un cubo specifico.
     *
     * @param <T> Tipo dell'array principale (es. Double).
     * @param array L'array principale che contiene tutte le strutture dati.
     * @param offsets Matrice degli offset [start, end] per ogni struttura dati.
     * @param listaStrutturaDati Lista delle dimensioni delle strutture dati
     * (matrici, cubi, array).
     * @param strutturaIndex L'indice della struttura dati da stampare.
     */
    public static <T> void stampaCubo(T[] array, int[][] offsets, int[][] listaStrutturaDati, int strutturaIndex) {
        int start = offsets[strutturaIndex][0];
        int dimX = listaStrutturaDati[strutturaIndex][0];
        int dimY = listaStrutturaDati[strutturaIndex][1];
        int dimZ = listaStrutturaDati[strutturaIndex][2];

        System.out.println("Cubo " + dimX + "x" + dimY + "x" + dimZ + ":");
        for (int x = 0; x < dimX; x++) {
            System.out.println("Livello " + x + ":");
            for (int y = 0; y < dimY; y++) {
                for (int z = 0; z < dimZ; z++) {
                    int posizione = start + x * (dimY * dimZ) + y * dimZ + z;
                    System.out.print(array[posizione] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
