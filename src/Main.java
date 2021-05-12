import DP_VISITEUR.VisiteurClassique;
import JeuDeLaVie.*;

public class Main {
    public static void main(String[] args) {
        // Instanciation du jeu + insitalisation de la grille
        JeuDeLaVie jeu = new JeuDeLaVie(10, 10 );
        jeu.setVisitor( new VisiteurClassique(jeu) );

        // Instanciation de UI jeu + attachement de l'observateur
        jeu.attacheObservateur( new JeuDeLaVieUI(jeu) );
        jeu.lanceToi();
    }
}
