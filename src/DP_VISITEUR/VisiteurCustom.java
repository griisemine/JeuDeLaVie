package DP_VISITEUR;

import java.util.LinkedList;
import java.util.List;

import DP_COMMANDE.CommandeMeurt;
import DP_COMMANDE.CommandeVit;
import DP_ETAT.Cellule;
import JeuDeLaVie.JeuDeLaVie;

public class VisiteurCustom extends Visiteur{

    List<Integer> conditionVie;
    List<Integer> conditionMort;
    
public VisiteurCustom(JeuDeLaVie jeu) {
        super(jeu);
        conditionVie = new LinkedList<>();
        conditionMort = new LinkedList<>();
    }

    @Override
    public void modifyList(List<Integer> conditionVie, List<Integer> conditionMort){
        this.conditionVie = conditionVie;
        this.conditionMort = conditionMort;
    }

    /**
     * Tue une cellule si elle a moins
     * de 2 voisines et plus de 3 voisines
     */
    @Override
    public void visiteCelluleVivante(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivantes(jeu);
        if ( conditionMort.stream().anyMatch( (i) -> i == nbVoisin ) )
            jeu.ajouteCommande( new CommandeMeurt(cellule) );
    }

     /**
     * Rescusite une cellule si elle est mort
     * et qu'elle a exactement 3 voisins
     */
    @Override
    public void visiteCelluleMorte(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivantes(jeu);
        if ( conditionVie.stream().anyMatch( (i) -> i == nbVoisin ) )
            jeu.ajouteCommande( new CommandeVit(cellule) );
    }

    @Override
    public String toString(){
        return "Mode Custom";
    }
}
