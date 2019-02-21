package nbdadi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tosetti Luca
 *
 * Questa classe collabora con il main Dadi e con CDatiCondivisi ed estende la
 * classe Thread
 */
public class ThVisualizza extends Thread {

    /**
     * Oggetto che riceverà tutti i valori di ciascuna ruota dai corrispettivi
     * thread.
     */
    private CDatiCondivisi PtrDati;
    /**
     * Attributo che indica se bisogna usare il metodo sleep della classe estesa
     * Thread.
     */
    private boolean usatoSleep;
    /**
     * Attributo che indica se va usato il metodo yield() della classe estesa
     * Thread.
     */
    private boolean usatoYield;
    /**
     * Attributo che indica quale dado viene lanciato (il primo o il secondo).
     */
    private int numeroDado;

    private boolean finito;

    /**
     * @brief: Metodo costruttore con parametri usato per inizializzare il
     * thread
     *
     * Viene richiamato il costruttore della classe Thread e vengono
     * inizializzati gli attributi usatoSleep,usatoYield,numeroDado e PtrDati
     * con i parametri che vengono passati al metodo.
     *
     * @param sleep Parametro che inizializza usatoSleep a true o false.
     * @param yield Parametro che inizializza usatoYield a true o false.
     * @param dati Parametro che contiene l'oggetto della classe CDatiCondivisi
     * creato nel main
     *
     */
    public ThVisualizza(boolean sleep, boolean yield, CDatiCondivisi dati) {
        super();
        usatoSleep = sleep;
        usatoYield = yield;
        this.PtrDati = dati;
        finito = false;
    }

    /**
     * @brief: Metodo "main" dei thread che vengono creati con l'uso di questa
     * classe
     *
     * Alla variabile num viene assegnato un numero randomico tra 1 e 6,si fa
     * l'output di una strigna. Successivamente in base al valore assunto
     * dall'attributo numeroDado si usano i metodi
     * setPrimaRuota(),setSecondaRuota(),setTerzaRuota() che vanno ad assegnare
     * il valore che gli viene passato agli attributi della classe
     * CDatiCondivisi, infine si fa attendere al thread 2 secondi con il metodo
     * sleep(2000) oppure si fa ritornare il processo in stato di ready con il
     * metodo yield().
     *
     */
    @Override
    public void run() {
        try {

            while (!finito) {
                clearConsole();
                PtrDati.VisualizzaSchermo();
                Thread.sleep(100);
            }
            clearConsole();

        } catch (InterruptedException ex) {
            Logger.getLogger(ThVisualizza.class.getName()).log(Level.SEVERE, null, ex);
        }

        PtrDati.getTerminationSempahore().release();
    }

        /**
     * @brief: Metodo usato per pulire la console
     *
     * In questo metodo viene dichiarata una stringa che andrà ad assumere il
     * nome del sistema operativo, ed in base al contenuto di questa stringa
     * verrà richiamato il comando cls o clear.
     *
     */
    private void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    void termina() {
        finito = true;
    }

}
