package DP_VISITEUR;

import DP_ETAT.Cellule;
import JeuDeLaVie.JeuDeLaVie;

/**
 * Identique to Day And Night
 * but when it's activate
 * the color of bg and point
 * is set to blue and green
 */
public class VisiteurGenererMonde extends Visiteur {

    public VisiteurGenererMonde(JeuDeLaVie jeu) {
        super(jeu);
    }
    
   /**
     * Tue une cellule si elle a moins
     * de 2 voisines et plus de 3 voisines
     */
    @Override
    public void visiteCelluleVivante(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivantes(jeu);
        if ( (nbVoisin == 0 || nbVoisin == 1 || nbVoisin == 2 || nbVoisin == 5) )
            cellule.meurt();
    }

    /**
     * Rescusite une cellule si elle est mort
     * et qu'elle a exactement 3 voisins
     */
    @Override
    public void visiteCelluleMorte(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivantes(jeu);
        if ( (nbVoisin == 3 || nbVoisin == 6 || nbVoisin == 7 || nbVoisin == 8) ){
            cellule.vit();
        }
    }


    @Override
    public String toString(){
        return "Mode Generateur Monde";
    }
}
