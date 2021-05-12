package DP_FABRIQUE;

import DP_ETAT.*;

public class GenerateurDeGrilleSymetrique implements GenerateurDeGrille {
    private static GenerateurDeGrilleSymetrique instance = new GenerateurDeGrilleSymetrique();

    private GenerateurDeGrilleSymetrique(){}

    public static GenerateurDeGrilleSymetrique getInstance(){
        return instance;
    }

    @Override
    public Cellule[][] genereUneGrille(int xMax, int yMax) {
        Cellule grille[][] = new Cellule[xMax][yMax];

        for(int x = 0 ; x < xMax / 2 ; x++)
            for( int y = 0 ; y < yMax / 2 ; y++ ){ 
                CelluleEtat uneCellule = (Math.random() * 1) <= 0.5 ?  CelluleEtatVivant.getInstance() : CelluleEtatMort.getInstance();
                grille[x][y] = new Cellule(x, y, uneCellule ); // Choix de l'instance à mettre
                grille[ xMax - 1 - x ][ y ] = new Cellule(x, y, uneCellule ); // Choix de l'instance à mettre
                grille[ x ][ yMax - 1 - y ] = new Cellule(x, y, uneCellule ); // Choix de l'instance à mettre
                grille[ xMax - 1 - x ][ yMax - 1 - y ] = new Cellule(x, y, uneCellule ); // Choix de l'instance à mettre
            }

            
        if ( xMax%2 != 0 ){
            for( int y = 0 ; y < yMax ; y++ )
                grille[ xMax/2 ][y] = grille[ xMax/2 - 1 ][y];
        }
        if ( yMax%2 != 0 ){
            for( int x = 0 ; x < xMax ; x++ )
                grille[ x ][ yMax/2 ] = grille[ x ][ yMax/2 - 1];
        } 
        
        return grille;
    }

    @Override
    public String toString(){
        return "Generateur Grille Symetrique Aleatoire";
    }


}
