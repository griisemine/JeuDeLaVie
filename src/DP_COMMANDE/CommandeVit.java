package DP_COMMANDE;

import DP_ETAT.Cellule;

public class CommandeVit extends Commande {

    public CommandeVit(Cellule c){
        super();
        super.cellule = c;
    }

    @Override
    public void executer(){
        cellule.vit();
    }

}
