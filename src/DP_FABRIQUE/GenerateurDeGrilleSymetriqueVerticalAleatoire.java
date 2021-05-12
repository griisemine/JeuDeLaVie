package DP_FABRIQUE;

import DP_ETAT.*;

public class GenerateurDeGrilleSymetriqueVerticalAleatoire implements GenerateurDeGrille {
    private static GenerateurDeGrilleSymetriqueVerticalAleatoire instance = new GenerateurDeGrilleSymetriqueVerticalAleatoire();

    private GenerateurDeGrilleSymetriqueVerticalAleatoire(){}

    public static GenerateurDeGrilleSymetriqueVerticalAleatoire getInstance(){
        return instance;
    }

    @Override
    public Cellule[][] genereUneGrille(int xMax, int yMax) {
        Cellule grille[][] = new Cellule[xMax][yMax];

        for(int x = 0 ; x < xMax / 2 ; x++)
            for( int y = 0 ; y < yMax ; y++ ){ 
                CelluleEtat uneCellule = (Math.random() * 1) <= 0.5 ?  CelluleEtatVivant.getInstance() : CelluleEtatMort.getInstance();
                grille[x][y] = new Cellule(x, y, uneCellule ); // Choix de l'instance à mettre
                grille[ xMax - 1 - x ][y] = new Cellule(x, y, uneCellule ); // Choix de l'instance à mettre
            }
        // Si grille impaire
        if ( xMax%2 != 0 ){
            for( int y = 0 ; y < yMax ; y++ )
                grille[ xMax/2 ][y] = grille[ xMax/2 - 1 ][y];
        }
        return grille;
    }

    @Override
    public String toString(){
        return "Generateur Grille Symetrique Vertical Aleatoire";
    }
}
