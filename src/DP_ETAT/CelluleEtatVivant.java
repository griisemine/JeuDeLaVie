package DP_ETAT;
import DP_VISITEUR.*;

/**
 * Class CelluleEtatVivant qui implement le DP singleton
 */
public class CelluleEtatVivant implements CelluleEtat {

    /**
     * instance unique privee
     */
    private static CelluleEtatVivant instance = new CelluleEtatVivant();

    /**
     * Constructeur privee
     */
    private CelluleEtatVivant(){}

    /**
     * Methode static pour recuperer l'instance unique
     * @return retourne l'instance unique de CelluleEtatVivant
     */
    public static CelluleEtatVivant getInstance(){
        return instance;
    }

    /**
     * Retourne cellule this
     * car deja en vie
     */
    @Override
    public CelluleEtat vit() {
        return this;
    }

    /**
     * Retourne cellule morte
     * la cellule meurt
     */
    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMort.getInstance();
    }

    /**
     * Retoourne vrai car dans cellule vivante
     */
    @Override
    public boolean estVivante() {
        return true;
    }

    /**
     * Accepte un visiteur et realise l'action sur
     * la cellule
     */
    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCelluleVivante(cellule);
    }

    /**
     * Cette methode permet de changer
     * l'etat sans connaitre son etat 
     * actuel
     */
    @Override
    public CelluleEtat switchStatut() {
        return CelluleEtatMort.getInstance();
    }
    
}
