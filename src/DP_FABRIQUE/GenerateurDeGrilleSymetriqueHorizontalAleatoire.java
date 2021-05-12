package DP_FABRIQUE;
import DP_ETAT.*;

public class GenerateurDeGrilleSymetriqueHorizontalAleatoire implements GenerateurDeGrille {
    private static GenerateurDeGrilleSymetriqueHorizontalAleatoire instance = new GenerateurDeGrilleSymetriqueHorizontalAleatoire();

    private GenerateurDeGrilleSymetriqueHorizontalAleatoire(){}

    public static GenerateurDeGrilleSymetriqueHorizontalAleatoire getInstance(){
        return instance;
    }

    @Override
    public Cellule[][] genereUneGrille(int xMax, int yMax) {
        Cellule grille[][] = new Cellule[xMax][yMax];

        for(int x = 0 ; x < xMax ; x++)
            for( int y = 0 ; y < yMax / 2 ; y++ ){ 
                CelluleEtat uneCellule = (Math.random() * 1) <= 0.5 ?  CelluleEtatVivant.getInstance() : CelluleEtatMort.getInstance();
                grille[x][y] = new Cellule(x, y, uneCellule ); // Choix de l'instance à mettre
                grille[ x ][ yMax - 1 - y] = new Cellule(x, y, uneCellule ); // Choix de l'instance à mettre
            }
        // Si grille impaire
        if ( yMax%2 != 0 ){
            for( int x = 0 ; x < xMax ; x++ )
                grille[ x ][ yMax/2 ] = grille[ x ][ yMax/2 - 1];
        }
        return grille;
    }

    @Override
    public String toString(){
        return "Generateur Grille Symetrique Horizontal Aleatoire";
    }
}



