/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbdadi;

import java.util.concurrent.Semaphore;

/**
 * @author Tosetti Luca
 *
 * Classe che collabora con il Main e ThDado1 andando raggruppare e a gestire i
 * dati condivisi
 */
public class CDatiCondivisi {
    /**
     * Attributo che conterrà le varie linee di testo che dovranno poi comparire su schermo
     */
    private String[] schermo;
    /**
     * Attributo che indica a quanti elementi si è arrivati ad avere nell'attributo "schermo"
     */
    private int Elementi;
    /**
     * Attributo che assume il valore del "primo dado" 
     */
    private int primoDado;
    /**
     * Attributo che assume il valore del "secondo dado" 
     */
    private int secondoDado;
    /**
     * Attributo che assume il valore del "terzo dado" 
     */
    private int terzoDado;

    private final Semaphore terminationSempahore;

    /**
     * @brief: Metodo costruttore con parametri che inizializza la slot machine
     *
     * Questo metodo assegna a primoDado, secondoDado, terzaRuota i parametri
     * passati, ed inizializza le varie posizioni dell'attributo schermo ad "", oltre
     * che inizializzare l'attributo Elementi a 0.
     *
     * @param primoDado valore che si vuole far assumere al primoDado 
     * @param secondoDado valore che si vuole far assumere al secondoDado
     * @param terzoDado valore che si vuole far assumere al terzoDado
     *
     */
    public CDatiCondivisi(int primoDado, int secondoDado, int terzoDado) {
        schermo = new String[10000];
        for (int i = 0; i < 10000; i++) {
            schermo[i] = "";
        }
        this.primoDado = primoDado;
        this.secondoDado = secondoDado;
        this.terzoDado = terzoDado;
        this.Elementi = 0;

        this.terminationSempahore = new Semaphore(0);
    }

    /**
     * @brief: Metodo costruttore senza parametri
     *
     * In questo metodo gli attributi primaRuota,secondaRuota,terzaRuota ed Elementi
     * vengono inizializzati a 0, mentre le varie posizioni dell'attributo schermo
     * vengono inizializzate a "".
     *
     */
    public CDatiCondivisi() {
        this.primoDado = 0;
        this.secondoDado = 0;
        this.terzoDado = 0;
        schermo = new String[10000];
        for (int i = 0; i < 10000; i++) {
            schermo[i] = "";
        }
        this.Elementi = 0;

        this.terminationSempahore = new Semaphore(0);
    }
    /**
     * @brief Metodo get dell'attributo Elementi
     * 
     * questo metodo si occupa di ritornare il valore dell'attributo Elementi
     * di questa classe.
     * 
     * @return Valore dell'attributo Elementi
     */
    public synchronized int getNumElementi() {
        return Elementi;
    }

    /**
     * @brief: Metodo get dell'attributo primaRuota
     *
     * Questo metodo si occupa di ritornare il valore dell'attributo primaRuota
     * di questa classe
     *
     * @return valore dell'attributo primoDado
     *
     */
    public synchronized int getPrimoDado() {
        return primoDado;
    }

    /**
     * @brief: Metodo set dell'attributo secondaRuota
     *
     * Questo metodo si occupa di assegnare all'attributo primaRuota il valore
     * che gli viene passato
     *
     * @param primoDado valore da assegnare all'attributo primoDado
     *
     */
    public synchronized void setPrimoDado(int primoDado) {
        this.primoDado = primoDado;
    }

    /**
     * @brief: Metodo get dell'attributo secondaRuota
     *
     * Questo metodo si occupa di ritornare il valore dell'attributo
     * secondaRuota di questa classe
     *
     * @return valore dell'attributo secondoDado
     *
     */
    public synchronized int getSecondoDado() {
        return secondoDado;
    }

    /**
     * @brief: Metodo set dell'attributo secondaRuota
     *
     * Questo metodo si occupa di assegnare all'attributo secondaRuota il valore
     * che gli viene passato
     *
     * @param secondoDado valore da assegnare all'attributo secondoDado
     *
     */
    public synchronized void setSecondoDado(int secondoDado) {
        this.secondoDado = secondoDado;
    }

    /**
     * @brief: Metodo get dell'attributo terzaRuota
     *
     * Questo metodo si occupa di ritornare il valore dell'attributo terzaRuota
     * di questa classe
     *
     * @return valore dell'attributo terzoDado
     *
     */
    public synchronized int getTerzoDado() {
        return terzoDado;
    }

    /**
     * @brief: Metodo set dell'attributo terzaRuota
     *
     * Questo metodo si occupa di assegnare all'attributo terzaRuota il valore
     * che gli viene passato
     *
     * @param terzoDado valore da assegnare all'attributo terzoDado
     *
     */
    public synchronized void setTerzoDado(int terzoDado) {
        this.terzoDado = terzoDado;
    }
    
    /**
     * @brief: Metodo get di una posizione dell'attributo schermo
     * 
     * Questo metodo si occupa di ritornare il valore contenuto in una posizione,
     * indicata dal parametro che gli viene passato, dell'attributo schermo.
     * 
     * @param posizione Valore usato come indice dell'array per identificare il valore da ritornare
     * 
     * @return Valore contenuto in una posizione dell'attributo schermo
     */
    public synchronized String getRiga(int posizione) {
        return schermo[posizione];
    }

    /**
     * @brief: Metodo che si occupa di far visualizzare a schermo tutte le linee memorizzate nell'attributo schermo
     * 
     * In questo metodo viene fatto l'output a schermo dei valori contenuti in tutte le linee dell'attributo schermo
     * fino a quando si arriva al numero di linee salvate nello stesso attributo.
     * 
     */
    public synchronized void VisualizzaSchermo() {
        System.out.println("--------------------------------");
        for (int i = 0; i < Elementi; i++) {
            if (schermo[i].equals("")) {
                i = 10000;
            } else {
                System.out.println(schermo[i]);
            }
            if (i % 3 == 2)
                System.out.println();
        }
        System.out.println("--------------------------------");
    }
    
    /**
     * @brief: Metodo che si occupa di aggiungere una linea di testo nella prima posizione vuota dell'attributo schermo
     * 
     * @param str Stringa in cui è contenuto il valore della linea di testo da memorizzare.
     */
    public synchronized void aggiungiStringa(String str) {
        schermo[Elementi] = str;
        Elementi++;
    }

    public Semaphore getTerminationSempahore() {
        return terminationSempahore;
    }
}
