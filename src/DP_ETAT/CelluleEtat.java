
package DP_ETAT;

import DP_VISITEUR.Visiteur;

/**
 * Inteface pour l'etat d'une cellule
 */
public interface CelluleEtat {

    public CelluleEtat vit();

    public CelluleEtat meurt();

    public boolean estVivante();

    /**
     * Cette methode permet de changer
     * l'etat sans connaitre son etat 
     * actuel
     */
    public CelluleEtat switchStatut();

    public void accepte(Visiteur visiteur , Cellule cellule);
}
