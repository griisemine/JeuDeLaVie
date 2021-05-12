package DP_VISITEUR;

import DP_COMMANDE.CommandeMeurt;
import DP_COMMANDE.CommandeVit;
import DP_ETAT.Cellule;
import JeuDeLaVie.JeuDeLaVie;

public class VisiteurDayAndNight extends Visiteur {

    public VisiteurDayAndNight(JeuDeLaVie jeu) {
        super(jeu);
    }
    

    /**
     * Tue une cellule si elle a moins
     * de 2 voisines et plus de 3 voisines
     */
    public void visiteCelluleVivante(Cellule cellule) {
        long nbVoisins = cellule.nombreVoisinesVivantes(jeu);
        if (nbVoisins < 3 || nbVoisins == 5) {
            jeu.ajouteCommande( new CommandeMeurt(cellule) );
        }
    }

    public void visiteCelluleMorte(Cellule cellule) {
        long nbVoisins = cellule.nombreVoisinesVivantes(jeu);
        if (nbVoisins == 3 || nbVoisins >= 6) {
            jeu.ajouteCommande( new CommandeVit(cellule) );
        }
    }

    @Override
    public String toString(){
        return "Mode Day & Night";
    }
}
