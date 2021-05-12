package DP_ETAT;

public class PlayEtatPause implements PlayEtat {
     /**
     * instance unique privee
     */
    private static PlayEtatPause instance = new PlayEtatPause();
 
    /**
     * Constructeur privee
     */
    private PlayEtatPause(){}

    /**
     * Methode static pour recuperer l'instance unique
     * @return retourne l'instance unique de PlayEtatPlay
     */
    public static PlayEtatPause getInstance(){
        return instance;
    }

    @Override
    public PlayEtat play() {
        return PlayEtatPlay.getInstance();
    }

    @Override
    public PlayEtat pause() {
        return this;
    }

    @Override
    public boolean estLancer() {
        return false;
    }
}
