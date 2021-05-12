package DP_VISITEUR;
import java.util.List;

import DP_ETAT.*;
import JeuDeLaVie.JeuDeLaVie;

public abstract class Visiteur {
    protected JeuDeLaVie jeu;

    public Visiteur(JeuDeLaVie jeu){
        this.jeu = jeu ;
    }

    public void visiteCelluleVivante(Cellule cellule){}

    public void visiteCelluleMorte(Cellule cellule){}

    public void modifyList(List<Integer> conditionVie, List<Integer> conditionMort){}

    @Override
    public String toString(){
        return "Bizarre que tu vois ca";
    }
}
