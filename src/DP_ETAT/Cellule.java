package DP_ETAT;

import DP_VISITEUR.Visiteur;
import JeuDeLaVie.JeuDeLaVie;

/**
 * Class Cellule qui implement le DP ETAT
 */
public class Cellule {

    /**
     * Etat de la cellule
     */
    private CelluleEtat etat;

    /**
     * Position de la cellule
     */
    private int x,y;

    public Cellule( int x, int y, CelluleEtat etat){
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    /**
     * Mise a jours de l'etat etat
     */
    public void vit() {
        etat = etat.vit();
        // SI IL ETAIT VIVANT AU DEBUT ET QU IL REVIT C EST UN RESCUSITER
    }

    /**
     * Mise a jours de l'etat etat
     */
    public void meurt() {
        etat = etat.meurt();
    }

    /**
     * Connaitre le statut de l'etat
     */
    public boolean estVivante() {
        return etat.estVivante();
    }

    /**
     * Cette methode permet de changer
     * l'etat sans connaitre son etat 
     * actuel
     */
    public void switchStatut(){
        etat = etat.switchStatut();
    }

    /**
     * Retourne le nombre de voisin vivant
     * d'une cellule
     * @param jeu en parametre le jeu de la vie
     * @return retourne le total de voisin 
     */
    public int nombreVoisinesVivantes(JeuDeLaVie jeu){
        int total = 0;
        for(int x = -1 ; x <=1 ; x++ ){
            for(int y = -1 ; y <=1 ; y++ ){
                Cellule tmp = jeu.getGrilleXY(this.x + x, this.y + y);
                if( tmp != null && tmp != this && tmp.estVivante() )
                    total++;
            }
        }
        return total;
    }
    
    public void accepte(Visiteur visiteur){
        etat.accepte(visiteur, this);
    }

}