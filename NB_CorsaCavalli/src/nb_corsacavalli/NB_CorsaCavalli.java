/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb_corsacavalli;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Galimberti_Francesco
 *
 * @brief La classe CorsaCavalli40 permette di simulare una gara tra 5 cavalli
 * La classe collabora con le classi: ThCorsa, DatiCondivisi
 */
public class NB_CorsaCavalli {


    /**
     * @author Galimberti_Francesco
     *
     * @brief Il metodo simula una gara-scommessa tra 5 cavalli.
     *
     * Il metodo permette all'utente di scegliere su quale cavallo puntare. Crea
     * e avvia i 5 Thread che corrispondono ai 5 cavalli. Quando l'utente digita
     * un tasto qualsiasi vengono visualizzati i galoppi dei cavalli altrimenti
     * digitando ENTER la gara termina. Viene visualizzato il cavallo vincitore
     * (in base al numero di galoppi) e verificata la vittoria o meno
     * dell'utente.
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        DatiCondivisi dati = new DatiCondivisi();
        ThCorsa Clop1 = new ThCorsa(1, true, true, dati);
        ThCorsa Clop2 = new ThCorsa(2, true, true, dati);
        ThCorsa Clop3 = new ThCorsa(3, true, true, dati);
        ThCorsa Clop4 = new ThCorsa(4, true, true, dati);
        ThCorsa Clop5 = new ThCorsa(5, true, true, dati);

        System.out.println("Su che cavallo punti? 1/2/3/4/5");
        int numCav = input.nextInt();

        Clop1.start();
        Clop2.start();
        Clop3.start();
        Clop4.start();
        Clop5.start();

        while (!dati.hasWon()) {
            dati.visualizzaLinee();
            clearConsole();
        }

        //attendi
        dati.getInterruptionSempahore().acquireUninterruptibly(5);

        int max = 0;
        int cavallo = 0;
        if (dati.getnClop1() > max) {
            max = dati.getnClop1();
            cavallo = 1;
        }
        if (dati.getnClop2() > max) {
            max = dati.getnClop2();
            cavallo = 2;
        }
        if (dati.getnClop3() > max) {
            max = dati.getnClop3();
            cavallo = 3;
        }
        if (dati.getnClop4() > max) {
            max = dati.getnClop4();
            cavallo = 4;
        }
        if (dati.getnClop5() > max) {
            max = dati.getnClop1();
            cavallo = 5;
        }

        System.out.println("Numero galoppi:");
        System.out.println("Clop1 --> " + dati.getnClop1());
        System.out.println("Clop2 --> " + dati.getnClop2());
        System.out.println("Clop3 --> " + dati.getnClop3());
        System.out.println("Clop4 --> " + dati.getnClop4());
        System.out.println("Clop5 --> " + dati.getnClop5());
        System.out.println("Cavallo vincitore: " + cavallo);
        System.out.println("Cavallo puntato: " + numCav);

        if (numCav == cavallo) {
            System.out.println("WINNER");
        } else {
            System.out.println("LOSER");
        }

        System.out.println("Alla prossima!");

    }

    private static void clearConsole() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

}
