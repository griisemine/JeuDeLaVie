package DP_COMMANDE;

import DP_ETAT.Cellule;

public class CommandeMeurt extends Commande {
    
    public CommandeMeurt(Cellule c){
        super();
        super.cellule = c;
    }

    @Override
    public void executer(){
        cellule.meurt();
    }

}
