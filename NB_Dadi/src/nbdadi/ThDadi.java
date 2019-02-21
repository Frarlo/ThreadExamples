package nbdadi;

/**
 * @author Tosetti Luca
 *
 * Questa classe collabora con il main Dadi e con CDatiCondivisi ed estende la
 * classe Thread
 */
public class ThDadi extends Thread {

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
     * @param numeroDado Parametro che inizializza numeroDado a true o false.
     * @param dati Parametro che contiene l'oggetto della classe CDatiCondivisi
     * creato nel main
     *
     */
    public ThDadi(boolean sleep, boolean yield, int numeroDado, CDatiCondivisi dati) {
        super();
        usatoSleep = sleep;
        usatoYield = yield;
        this.numeroDado = numeroDado;
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
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                int num = (int) ((Math.random() * 6) + 1);

                PtrDati.aggiungiStringa("Hai lanciato il " + numeroDado + "° dado: E' uscito " + num);
                if (numeroDado == 1) {
                    PtrDati.setPrimoDado(num);
                }
                if (numeroDado == 2) {
                    PtrDati.setSecondoDado(num);
                }
                if (numeroDado == 3) {
                    PtrDati.setTerzoDado(num);
                }

                if (usatoSleep) {
                    Thread.sleep(500);
                }
                if (usatoYield) {
                    Thread.yield();
                }
            }
        } catch (InterruptedException ex) {

        }

        PtrDati.getTerminationSempahore().release();
    }

    void termina() {
        finito = true;
    }

}
