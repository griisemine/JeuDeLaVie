package DP_VISITEUR;

import DP_COMMANDE.CommandeMeurt;
import DP_COMMANDE.CommandeVit;
import DP_ETAT.Cellule;
import JeuDeLaVie.JeuDeLaVie;

public class VisiteurHighLife extends Visiteur {

    public VisiteurHighLife(JeuDeLaVie jeu) {
        super(jeu);
    }
    

    /**
     * Tue une cellule si elle a moins
     * de 2 voisines et plus de 3 voisines
     */
    @Override
    public void visiteCelluleVivante(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivantes(jeu);
        if ( !(nbVoisin == 2 || nbVoisin == 3) ){
            jeu.ajouteCommande( new CommandeMeurt(cellule) );
        }
    }

    /**
     * Rescusite une cellule si elle est mort
     * et qu'elle a exactement 3 voisins
     */
    @Override
    public void visiteCelluleMorte(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivantes(jeu);
        if ( (nbVoisin == 3) || (nbVoisin == 6)  )
        jeu.ajouteCommande( new CommandeVit(cellule) );
    }


    @Override
    public String toString(){
        return "Mode High Life";
    }
}
