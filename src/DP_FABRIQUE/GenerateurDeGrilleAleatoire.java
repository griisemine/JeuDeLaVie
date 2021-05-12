package DP_FABRIQUE;

import DP_ETAT.*;

public class GenerateurDeGrilleAleatoire implements GenerateurDeGrille{

    private static GenerateurDeGrilleAleatoire instance = new GenerateurDeGrilleAleatoire();

    private GenerateurDeGrilleAleatoire(){}

    public static GenerateurDeGrilleAleatoire getInstance(){
        return instance;
    }


    @Override
    public Cellule[][] genereUneGrille(int xMax, int yMax) {
        Cellule grille[][] = new Cellule[xMax][yMax];

        for(int x = 0 ; x < xMax ; x++)
            for( int y = 0 ; y < yMax ; y++ ){ 
                grille[x][y] = new Cellule(x, y, (Math.random() * 1) <= 0.5 ?  CelluleEtatVivant.getInstance() : CelluleEtatMort.getInstance() ); // Choix de l'instance à mettre
            }

        return grille;
    }

    @Override
    public String toString(){
        return "Generateur Aleatoire";
    }
    
}
