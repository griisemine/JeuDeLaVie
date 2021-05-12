package DP_ETAT;

public class PlayEtatPlay implements PlayEtat {
 /**
     * instance unique privee
     */
    private static PlayEtatPlay instance = new PlayEtatPlay();
 
    /**
     * Constructeur privee
     */
    private PlayEtatPlay(){}

    /**
     * Methode static pour recuperer l'instance unique
     * @return retourne l'instance unique de PlayEtatPlay
     */
    public static PlayEtatPlay getInstance(){
        return instance;
    }

    @Override
    public PlayEtat play() {
        return this;
    }

    @Override
    public PlayEtat pause() {
        return PlayEtatPause.getInstance();
    }

    @Override
    public boolean estLancer() {
        return true;
    }
    
}
