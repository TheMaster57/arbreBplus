/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Arbre.Arbre;
import data.Personne;
import graphique.AffichageConsole;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Antoine
 */
public class TestPersonne {

    /**
     * Arbre courant.
     */
    public static Arbre[] arbreUtilise = new Arbre[2];
    public static int indice;
    
    public static void main(String[] args) {

        //Parcourir tous les fichiers      
        //Utiliser la méthode charger de Personne
        //Stocker les personnes (liste)
        //Créer l'arbre de personnes
        String chemin;
        ArrayList<Personne> liste = new ArrayList<>();
        
        for (int i = 1; i < 51; i++) {
            // Il faut mettre le dossier des données dans C:/DONNEES
            // Ex : C:/DONNEES/F1.txt
            chemin = "Fichiers/F" + i + ".txt";
            liste.add(Personne.chargerDepuisFichier(chemin));
            System.out.println("Importation de " + liste.get(i - 1) + "reussi.");
        }
        
        System.out.println("");

        // Création d'un arbre
        Arbre b = new Arbre(3, liste.get(0).getAge() + "", liste.get(0).getNom());
        // Insertions
        for (int i = 1; i < 50; i++) {
            b.insertion(liste.get(i).getAge() + "", liste.get(i).getNom());
        }
        /*System.out.println("Arbre final (Cle=Age, Valeur=Prenom): ");
         // Affichage de l'arbre
         AffichageConsole.afficherArbre(b);
         System.out.println("\n\n\n");*/

        // Création d'un arbre
        Arbre b2 = new Arbre(3, liste.get(0).getNom(), liste.get(0).getAge() + "");
        // Insertions
        for (int i = 1; i < 50; i++) {
            b2.insertion(liste.get(i).getNom(), liste.get(i).getAge() + "");
        }
        System.out.println("Arbre final (Cle=Prenom, Valeur=Age): ");
        // Affichage de l'arbre
        AffichageConsole.afficherArbre(b2);
        
        arbreUtilise[0] = b;
        arbreUtilise[1] = b2;
        indice = 1;

        // Lancement d'un SHELL
        Scanner sc = new Scanner(System.in);
        
        afficheAide();
        while (true) {
            System.out.print(">>> ");
            String cmd = sc.nextLine();
            
            String[] split = cmd.split(" ");
            switch (split.length) {
                case 1:
                    switch (cmd) {
                        case "help":
                            afficheAide();
                            break;
                        case "switch":
                            // Changement de l'indice
                            if (indice == 0) {
                                indice = 1;
                            } else {
                                indice = 0;
                            }

                            // Affichage de l'arbre
                            AffichageConsole.afficherArbre(arbreUtilise[indice]);
                            break;
                        case "exit":
                            System.out.println("*** FIN DU PROGRAMME ***");
                            System.exit(1);
                            break;
                        case "show":
                            // Affichage de l'arbre
                            System.out.println("Affichage de l'arbre ... ");
                            AffichageConsole.afficherArbre(arbreUtilise[indice]);
                            break;
                    }
                    break;
                case 2:
                    if (split[0].equals("get")) {
                        // On cherche la valeur pour l'afficher
                        System.out.println(arbreUtilise[indice].chercherValeur(split[1]));
                    }
                    if (split[0].equals("sup")) {
                        // On supprime une cle
                        System.out.print("Suppression de " + split[1] + " ... ");
                        arbreUtilise[indice].suppression(split[1]);
                        System.out.println("Fait.");
                    }
                    break;
                case 3:
                    if (split[0].equals("add")) {
                        // On insère une nouvelle valeur dans l'arbre
                        System.out.print("Ajout de ("+split[1]+","+split[2]+") ... ");
                        arbreUtilise[indice].insertion(split[1], split[2]);
                        System.out.println("Fait.");
                    }
                    break;
            }
        }
    }
    
    private static void afficheAide() {
        System.out.println("\n\tEntrer une commande : ");
        System.out.println("\t------------------------");
        System.out.println("help               : affiche la presente aide.");
        System.out.println("show               : affiche l'arbre.");
        System.out.println("switch             : changer le type d'arbre (inversion cle/valeur)");
        System.out.println("add <cle> <valeur> : ajoute une donnee dans l'arbre.");
        System.out.println("get <cle>          : cherche une valeur dans l'arbre.");
        System.out.println("sup <cle>          : supprime une cle dans l'arbre (version alpha)");
        System.out.println("exit               : quitte le programme.");
    }
    
}
