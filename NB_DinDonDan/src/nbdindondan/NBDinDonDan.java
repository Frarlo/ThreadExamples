/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbdindondan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Princess Joy Padua
 *
 * @brief Main
 *
 */
public class NBDinDonDan {


    /**
     * @brief Main per la gestione dei suoni.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Scanner scegli = new Scanner(System.in);
        String interruzione;
        System.out.println("Per terminare premere un tasto.");
        /*System.out.println("Scelta 1: solo sleep");
        System.out.println("Scelta 2: sleep + yield");
        System.out.println("Scelta 3: solo yield");
        int scelta=scegli.nextInt();
         */
        DatiCondivisi dati = new DatiCondivisi();

        final Semaphore dinSemaphore = new Semaphore(0);
        final Semaphore donSemaphore = new Semaphore(0);
        final Semaphore danSemaphore = new Semaphore(1);

        ThSuono th1 = new ThSuono("DIN", 3, dati, danSemaphore, dinSemaphore);
        ThSuono th2 = new ThSuono("DON", 3, dati, dinSemaphore, donSemaphore);
        ThSuono th3 = new ThSuono("DAN", 3, dati, donSemaphore, danSemaphore);
        try {
            th1.start();
            th2.start();
            th3.start();
            
            boolean loop = true;
            while(loop){
                dati.printSchermo();
                interruzione = input.readLine();

                if (interruzione.equals("")) {
                    clearConsole();
                    loop = false;
                }
            }
            th1.interrupt();
            th2.interrupt();
            th3.interrupt();

            dati.getTerminationSemaphore().acquireUninterruptibly(3);

            System.out.println("Qual è il suono che è stato richiamato più volte?");
            System.out.println("1)DIN");
            System.out.println("2)DON");
            System.out.println("3)DAN");
            int scelta = scegli.nextInt();
            System.out.println(dati.verificaSeHaiVinto(scelta));
            System.out.println("DIN:" + dati.getContaDIN());
            System.out.println("DON:" + dati.getContaDON());
            System.out.println("DAN:" + dati.getContaDAN());
            System.out.println("Ci vediamo la prossima volta");
        } catch (IOException ex) {
            Logger.getLogger(NBDinDonDan.class.getName()).log(Level.SEVERE, null, ex);
        }
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
