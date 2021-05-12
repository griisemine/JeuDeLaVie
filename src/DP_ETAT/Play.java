package DP_ETAT;

public class Play {

    /**
     * Etat du jeu
     */
    private PlayEtat playEtat;

    public Play( PlayEtat playEtat){
        this.playEtat = playEtat;
    }

    public void play(){
        playEtat = playEtat.play();
    }

    public void pause(){
        playEtat = playEtat.pause();
    }

    public boolean estLancer(){
        return playEtat.estLancer();
    }
    
}
