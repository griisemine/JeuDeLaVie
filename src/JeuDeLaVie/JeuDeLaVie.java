package JeuDeLaVie;

import java.util.LinkedList;
import java.util.List;

import DP_COMMANDE.Commande;
import DP_ETAT.*;
import DP_FABRIQUE.GenerateurDeGrille;
import DP_FABRIQUE.GenerateurDeGrilleAleatoire;
import DP_OBSERVATEUR.Observable;
import DP_OBSERVATEUR.Observateur;
import DP_VISITEUR.Visiteur;
import DP_VISITEUR.VisiteurClassique;

public class JeuDeLaVie implements Observable{

    /**
     * Grille de jeu
     */
    private Cellule grille[][];
    /**
     * Position max/min grille
     */
    private int xMax,yMax;

    /**
     * List d'observateur de la class Jeu de la Vie
     */
    private List<Observateur> observateurs;

    /**
     * List des commandes
     */
    private List<Commande> commandes;

    /**
     * Visiteur de la class jeu
     */
    private Visiteur visiteur;
    
    /**
     * Statut en pause du jeu
     */
    private Play playEtat;
    /**
     * Compteur de generation
     */
    private long generationCount = 0;

    /**
     * 
     */
    private int frequence = 1;

    private GenerateurDeGrille generateurDeGrille = GenerateurDeGrilleAleatoire.getInstance();

    /**
     * Constructeur de la class JeuDeLaVie
     * @param xMax taille max X
     * @param yMin taille max Y
     * @param visiteur donner un type de visiteur
     */
    public JeuDeLaVie( int xMax, int yMin){
        // initalisation
        playEtat = new Play( PlayEtatPause.getInstance() );
        observateurs = new LinkedList<Observateur>();
        commandes = new LinkedList<Commande>();
        // affectation
        this.xMax = xMax;
        this.yMax = yMin;
        this.visiteur = new VisiteurClassique(this);
        // initialisation necessitant affectation
        grille = new Cellule[xMax][yMax];

        // Appel de methode
        initialiseGrille();
    }

    ////////////////// ACCESSOR //////////////////
    public Integer getXMax(){
        return xMax;
    }
    public Integer getYMax(){
        return yMax;
    }

    public Long getGenerationCount(){
        return generationCount;
    }

    /**
     * Retourne la cellule de la grille 
     * qui est a la position x/y
     * null si out of bounds
     * @param x
     * @param y
     * @return
     */
    public Cellule getGrilleXY(int x, int y){
        if( x < xMax && y < yMax && x >= 0 && y >= 0 )
            return grille[x][y];
        return null;
    }

    /**
     * Methode qui permet de set un visiteur
     * @param visiteur
     */
    public void setVisitor(Visiteur visiteur){
        this.visiteur = visiteur;
    }

    /**
     * Retourne le visiteur actuel
     * @return
     */
    public Visiteur getVisitor(){
        return this.visiteur;
    }

    /**
     * Modifie la frequence de rafraichissement
     * @param frequence
     */
    public void setFrequence(int frequence){
        this.frequence = frequence;
    }

    /**
     * Modifie le generateur de monde actuel
     * @param generateurDeGrille
     */
    public void setGenerateurDeGrille(GenerateurDeGrille generateurDeGrille){
        this.generateurDeGrille = generateurDeGrille;
    }
    ////////////////// METHODE //////////////////

    /**
     * Dit si le jeu est lancer ou non
     * @return
     */
    public boolean estLancer(){
        return playEtat.estLancer();
    }

    /**
     * Mettre le je en marche
     */
    public void play(){
        playEtat.play();
    }

    /**
     * Mettre le jeu en pause
     */
    public void pause(){
        playEtat.pause();
    }

    /**
     * Methode qui permet de lancer le jeu
     */
    public void lanceToi(){

        new Thread( new Runnable(){
            @Override
            public void run() {
                while( true ){
                    if( playEtat.estLancer() == true ){
                        calculerGenerationSuivante();   
                        try {
                            Thread.sleep(1000/frequence);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Thread.yield();
                    }  
                }
            }
        } ).run();
        
    }

    /**
     * initialisation de la grille de jeu
     */
    public void initialiseGrille(){
        grille = generateurDeGrille.genereUneGrille(xMax, yMax);
    }

    /**
     * Methode qui permet d'ajouter un observateurs
     * a la list d'observateur
     * @param o
     */
    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * Methode qui permet de retirer un observateurs
     * a la list d'observateur
     * @param o
     */
    @Override
    public void detacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * Methode qui permet
     * de notifier un observateur sur ca methode actualise
     */
    @Override
    public void notifieObservateurs() {
        observateurs.stream().forEach( Observateur::actualise );
    }

    /**
     * Ajoute une commande a la liste
     * de commande
     * @param c
     */
    public void ajouteCommande(Commande c){
        commandes.add(c);
    }

    /**
     * Execute les commandes 
     * presente dans la liste de commande
     */
    public void executeCommandes(){
        commandes.stream().forEach( Commande::executer );
        commandes.removeAll(commandes); 
    }

    /**
     * Distribue un visiteur à une 
     * cellule
     */
    public void distribueVisiteur(){
        for(int x = 0 ; x < xMax ; x++)
            for( int y = 0 ; y < yMax ; y++ ){ 
                grille[x][y].accepte(visiteur);
            }
    }

    /**
     * Permet de calculer la generation suivante
     * 
     * synchronized evite que calculerGenerationSuivante
     * et restart soit appeler au meme moment ce qui 
     * evite d'acceder a un tableau non instancier
     */
    public synchronized void calculerGenerationSuivante(){
        this.generationCount += 1;
        distribueVisiteur();
        executeCommandes();
        notifieObservateurs();
    }
    
    /**
     * Remet la grille a zero
     * 
     * synchronized evite que calculerGenerationSuivante
     * et restart soit appeler au meme moment ce qui 
     * evite d'acceder a un tableau non instancier
     */
    public synchronized void restart( int xMax , int yMax){
        this.xMax = xMax;
        this.yMax = yMax;

        this.generationCount = 0;
        // initalisation
        commandes = new LinkedList<Commande>();
        // affectation
        // initialisation necessitant affectation
        grille = new Cellule[xMax][yMax];
        // Appel de methode
        initialiseGrille();
    }


        /**
     * Affiche le plateau dans le terminal
     */
    @Override
    public String toString(){
        String maChaine = "";
        for( int y  = 0 ; y < yMax; y++ ){
            for(int x  = 0 ; x < xMax; x++ ){
                if( getGrilleXY(x, y).estVivante() )
                    maChaine += 'O';
                else 
                    maChaine += ' ';
            }
            maChaine += '\n';
        }
        return maChaine;
    }

}
