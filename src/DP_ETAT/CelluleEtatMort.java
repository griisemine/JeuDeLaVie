package DP_ETAT;
import DP_VISITEUR.*;

public class CelluleEtatMort implements CelluleEtat {

    /**
     * instance unique privee
     */
    private static CelluleEtatMort instance = new CelluleEtatMort();
 
    /**
     * Constructeur privee
     */
    private CelluleEtatMort(){}

    /**
     * Methode static pour recuperer l'instance unique
     * @return retourne l'instance unique de CelluleEtatVivant
     */
    public static CelluleEtatMort getInstance(){
        return instance;
    }

    /**
     * Retourne cellule this
     * car deja en vie
     */
    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivant.getInstance();
    }

    /**
     * Retourne cellule morte
     * la cellule meurt
     */
    @Override
    public CelluleEtat meurt() {
        return this;
    }

    /**
     * Retoourne faux car dans cellule morte
     */
    @Override
    public boolean estVivante() {
        return false;
    }

    /**
     * Accepte un visiteur et realise l'action sur
     * la cellule
     */
    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCelluleMorte(cellule);
    }

    /**
     * Cette methode permet de changer
     * l'etat sans connaitre son etat 
     * actuel
     */
    @Override
    public CelluleEtat switchStatut() {
        return CelluleEtatVivant.getInstance();
    }
    
}
