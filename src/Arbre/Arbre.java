package Arbre;

import graphique.AffichageConsole;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class Arbre {

    /**
     * Racine de l'arbre.
     */
    private Noeud racine;

    /**
     * Constructeur d'un arbre.
     *
     * @param ordre ordre de l'arbre
     * @param cle première clé
     * @param valeur première valeur
     */
    public Arbre(int ordre, String cle, String valeur) {
        // Initialisation de la racine
        this.racine = Noeud.creerNoeudRacine(ordre, cle, valeur, this);
    }

    /**
     * Permet de récupérer la racine de l'arbre.
     *
     * @return la racine
     */
    public Noeud getRacine() {
        return racine;
    }

    /**
     * Permet de modifier la racine de l'arbre.
     *
     * @param racine la nouvelle racine
     */
    public void setRacine(Noeud racine) {
        this.racine = racine;
    }

    /**
     * Recherche dans quel noeud appartient la valeur en paramètre
     *
     * @param str la valeur recherchée
     * @return le noeud obtenu
     */
    public Noeud recherche(String str) {
        // Initialisation de la recherche
        Noeud n = this.racine;

        // Tant que ce n'est pas une feuille
        while (!n.isFeuille()) {
            // On cherche le pointeur correct suivant
            n = n.rechercheNoeudSuivant(str);

        }

        // Quand on arrive ici, on a trouvé la feuille
        // On fait une dernière vérification : parcours du noeud obtenu
        boolean contient = false;
        for (String s : n.getTabCle()) {
            if (s.equals(str)) {
                contient = true;
                break;
            }
        }

        /*// On renvoit null si on a pas trouvé la valeur
         if(!contient) {
         n = null;
         }*/
        return n;
    }

    /**
     * Insertion dans un arbre.
     *
     * @param cle clé
     * @param valeur valeurs
     */
    public void insertion(String cle, String valeur) {
        // On recherche le noeud dans lequel on va insérer la valeur
        Noeud n = this.recherche(cle);

        // Si la clé n'existe pas déjà dans l'arbre
        if (n.rechercheIndice(cle) == (-1)) {

            // On l'insère au bon endroit
            n.ajouterValeur(cle, valeur);

        }
    }
    
    public String chercherValeur(String cle) {
        // Initialisation du résultat
        String res = "Valeur non trouvee";
        
        // On recherche le noeud dans lequel se trouve la cle
        Noeud n = this.recherche(cle);
        
        // On récupère l'indice
        int indice = -1;
        if(n.getTabCle().contains(cle)) {
            indice = n.getTabCle().indexOf(cle);
        }
        
        // On récupère la valeur
        if(indice != (-1)) {
            res = n.getTabPointeurs().get(indice).toString();
        }
        
        return res;
    }
    
    /**
     * Supprime une cle (et sa valeur correspondante) dans l'arbre.
     * 
     * @param cle la cle que l'on souhaite supprimer.
     */
    public void suppression(String cle) {
        // On recherche le noeud dans lequel se trouve la valeur à supprimer
        Noeud n = this.recherche(cle);
        
        // Et on la supprime
        n.supprimerCle(cle);
    }
}
