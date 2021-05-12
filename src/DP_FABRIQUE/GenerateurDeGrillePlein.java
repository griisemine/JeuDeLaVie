package DP_FABRIQUE;

import DP_ETAT.*;

public class GenerateurDeGrillePlein implements GenerateurDeGrille {
    private static GenerateurDeGrillePlein instance = new GenerateurDeGrillePlein();

    private GenerateurDeGrillePlein(){}

    public static GenerateurDeGrillePlein getInstance(){
        return instance;
    }

    @Override
    public Cellule[][] genereUneGrille(int xMax, int yMax) {
        Cellule grille[][] = new Cellule[xMax][yMax];

        for(int x = 0 ; x < xMax ; x++)
            for( int y = 0 ; y < yMax ; y++ ){ 
                grille[x][y] = new Cellule(x, y,CelluleEtatVivant.getInstance() ); // Choix de l'instance à mettre
            }
        return grille;
    }

    @Override
    public String toString(){
        return "Generateur de Grille Pleine";
    }
}
