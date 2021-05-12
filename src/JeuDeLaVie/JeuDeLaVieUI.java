package JeuDeLaVie;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.LinkedList;
import java.util.List;

import DP_FABRIQUE.*;
import DP_OBSERVATEUR.Observateur;
import DP_VISITEUR.*;

public class JeuDeLaVieUI extends JPanel implements Observateur {
    /**
     *
     */
    private static final long serialVersionUID = -3172508874704652637L;
    /**
     * L'interface connait le jeu
     */
    private JeuDeLaVie jeu;
    /**
     * Label qui permet de compter le nombre de generation
     * car il faut le rafraichir
     */
    private JLabel generationCount = new JLabel( "Compteur generation : 0" );
    { generationCount.setAlignmentX(CENTER_ALIGNMENT); }

    /**
     * Indication afin de savoir si le builder
     * est activer ou non
     */
    private boolean builderIsOn = false;

    /**
     * Couleur de fond et de bg
     * des points
     */
    private Color bgColor = Color.WHITE; 
    private Color pointColor = Color.BLACK; 
    private JComboBox<String> comboBoxBg; // Permet de stocker la selection actuel et a griser le bouton le cas du monde Generation monde
    private JComboBox<String> comboBoxPoint; // permet de stocker la selection et a griser le bouton dans le cas du monde Generation monde

    /**
     * Variable pour les custom modes
     */
    private List<Integer> conditionVie = new LinkedList<>();
    private List<Integer> conditionMort = new LinkedList<>();
    private Box boxVie;
    private Box boxMort;
    private boolean isCustomMode = false;

    /**
     * Constructeur de l'UI du jeu 
     * de la vie
     * @param jeu
     */
    public JeuDeLaVieUI(JeuDeLaVie jeu){
        super();
        this.jeu = jeu;
        setVisible(true);
        addMouseListener( mouseListener() );
        creationInterface();
    }

    private void creationInterface(){

        // JFRAME Principal
        JFrame fenetre = new JFrame("Jeu de la vie");
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setSize(480, 530 + 28); // 30 IS HEADERBAR HEIGHT SIZE
        fenetre.setMinimumSize(new Dimension(480, 530 + 28));
        fenetre.add( boxToolbar(), BorderLayout.NORTH );
        fenetre.add(this, BorderLayout.CENTER );
        fenetre.add(btnSlider(), BorderLayout.SOUTH );
        fenetre.setVisible(true);
    }

    /**
     * Genere la toolbar de l'interface
     * @return
     */
    private JMenuBar boxToolbar(){
        JMenuBar menuBar = new JMenuBar();


        JButton nextGen = btnNextGeneration();
        JButton builder = btnBuilder();
        JButton playPause = btnPlayPause(nextGen , builder);

        Box vBox = Box.createVerticalBox();

        Box hBoxTop = Box.createHorizontalBox();
        hBoxTop.add( playPause );
        hBoxTop.add( nextGen );
        hBoxTop.add( comboBoxVisitor() );
        hBoxTop.add( builder );
        vBox.add( hBoxTop );

        boxVie = creationCheckBox( true );
        boxMort = creationCheckBox( false );
        boxVie.setVisible(isCustomMode);
        boxMort.setVisible(isCustomMode);
        vBox.add( boxVie );
        vBox.add( boxMort );


        vBox.add( selectTypeOfGrid() );
        vBox.add(restartBox());

        hBoxTop = Box.createHorizontalBox();
        hBoxTop.add(new JLabel("  bg Color : "));
        hBoxTop.add( choisirCouleur(true) );
        hBoxTop.add(new JLabel("  point Color : "));
        hBoxTop.add( choisirCouleur(false) );
        vBox.add(hBoxTop);

        menuBar.add( vBox );
      
        return menuBar;
    }

    /**
     * Nethode qui genere les checkbox des modes custome
     * @param checkBoxVie boolean vrai si pour mode vie faux pour mode faux
     * @return retourne la box qui comprends l'element
     */
    private Box creationCheckBox( boolean checkBoxVie ){
         // CHECKBOX
         Box hBoxTop = Box.createHorizontalBox();
         if ( checkBoxVie )
            hBoxTop.add(new JLabel("Condition Vie   :") );
         else 
            hBoxTop.add(new JLabel("Condition Mort :") );

         for(int i = 0 ; i <= 8 ; i++){
            final Integer valueCheckBox = i; // Valeur de la checkbox
            hBoxTop.add( new JLabel( " " + valueCheckBox.toString() ) ); // Label de la checkbox
            JCheckBox checkBox = new JCheckBox(); // La checkbox
            // Evenemnt clique
            checkBox.addItemListener( new ItemListener(){
                // Mettre à jours la liste
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if ( e.getStateChange() == ItemEvent.SELECTED ){
                        if( checkBoxVie )
                            conditionVie.add( valueCheckBox );
                        else 
                            conditionMort.add( valueCheckBox );
                    } else {
                        if( checkBoxVie )
                            conditionVie.remove( valueCheckBox );
                        else 
                            conditionMort.remove( valueCheckBox );
                    }
                    jeu.getVisitor().modifyList(conditionVie,conditionMort);
                }
            } );
            hBoxTop.add( checkBox );
         }

         return hBoxTop;
    }

    /**
     * Methode qui permet de generer
     * les deux combobox pour les couleurs
     * de bg et des points
     * @param isBg
     * @return
     */
    private JComboBox<String> choisirCouleur(boolean isBg){
        Color mesCouleurs[] = { Color.BLACK , Color.BLUE , Color.CYAN , Color.DARK_GRAY , Color.GRAY , Color.GREEN , Color.LIGHT_GRAY, Color.MAGENTA , Color.ORANGE, Color.PINK , Color.RED, Color.WHITE, Color.YELLOW };
        String ColorName[] = {"BLACK","BLUE" , "CYAN" , "DARK_GRAY" , "GRAY" , "GREEN" , "LIGHT_GRAY", "MAGENTA" , "ORANGE", "PINK" , "RED", "WHITE", "YELLOW" };
        JComboBox<String> comboBox = new JComboBox<String>( ColorName );
        comboBox.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( isBg )
                    bgColor = mesCouleurs[((JComboBox<String>)e.getSource()).getSelectedIndex()];
                else 
                    pointColor = mesCouleurs[((JComboBox<String>)e.getSource()).getSelectedIndex()];
                jeu.notifieObservateurs();
            }
        });
        if( isBg ){
            comboBoxBg = comboBox;
            comboBox.setSelectedIndex(11); // NB MAGIC // ATTENTION A NE PAS CHANGER LA COULEUR PAR DEFAULT EN HAUT SINON L'AFFICHEUR NE SERA PAS CONFORME A LA SELECTION PAR DEFAULT
        }
        else {
            comboBoxPoint = comboBox;
            comboBox.setSelectedIndex(0); // NB MAGIC // ATTENTION A NE PAS CHANGER LA COULEUR PAR DEFAULT EN HAUT SINON L'AFFICHEUR NE SERA PAS CONFORME A LA SELECTION PAR DEFAULT
        }

        return comboBox;
    }

    /**
     * Change le generateur de grille
     * @return
     */
    private JComboBox<GenerateurDeGrille> selectTypeOfGrid(){
        GenerateurDeGrille mesGenerateur[] = { GenerateurDeGrilleAleatoire.getInstance() , GenerateurDeGrilleVide.getInstance() , 
                                                GenerateurDeGrillePlein.getInstance(), GenerateurDeGrilleSymetriqueVerticalAleatoire.getInstance() , 
                                                GenerateurDeGrilleSymetriqueHorizontalAleatoire.getInstance(), GenerateurDeGrilleSymetrique.getInstance() };

        JComboBox<GenerateurDeGrille> comboBox = new JComboBox<GenerateurDeGrille>( mesGenerateur );
        comboBox.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.setGenerateurDeGrille( (GenerateurDeGrille)((JComboBox<Visiteur>)e.getSource()).getSelectedItem() );
            }
        });
        return comboBox;
    }

    /**
     * Creation du button builder
     * @return
     */
    private JButton btnBuilder(){
        JButton builder = new JButton("Builder");
        builder.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                builderIsOn = !builderIsOn;
                jeu.notifieObservateurs();
            }
        } );

        return builder;
    }

    /**
     * Methode qui permet de catch 
     * le click d'un utilisateur sur la grille
     * de jeu afin d'en initier une nouvelle
     * ou de modifie la grille actuel
     * @return
     */
    private MouseListener mouseListener(){
        MouseListener listener = new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                 if ( builderIsOn == true){
                    int x = getWidth() / jeu.getXMax();
                    int y = getHeight() / jeu.getYMax();
                    System.out.println(e.getX() + "," + e.getY());
                    System.out.println(e.getX() / x + " - " + e.getY() / y);
 
                    jeu.getGrilleXY( e.getX() / x, e.getY() / y).switchStatut();
                    jeu.notifieObservateurs();
                 }
            }
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        };
        return listener;
    }

    /**
     * Methode qui genere le boutton Play/Pause
     * avec son signal deja connecter
     * @return retourne le boutton
     */
    private JButton btnPlayPause( JButton nextGen, JButton builder ){
        JButton play_pause = new JButton("Play");
        play_pause.setPreferredSize(new Dimension(60,0));
        play_pause.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( jeu.estLancer() ) {
                    play_pause.setText("Play");
                    nextGen.setEnabled(true);
                    builder.setEnabled(true);
                    jeu.pause();
                }
                else {
                    jeu.play();
                    nextGen.setEnabled(false);
                    builder.setEnabled(false);
                    play_pause.setText("Pause");  
                    builderIsOn = false;
                }
                    
            }
        } );
        return play_pause;
    }

    /**
     * Methode qui genere le boutton 
     * generation suivante quand le
     * jeu est en pause uniquement
     * avec son signal deja connecter
     * @return retourne le boutton
     */
    private JButton btnNextGeneration(){
        JButton nextGen = new JButton("Next gen");
        nextGen.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( jeu.estLancer() == false ) 
                    jeu.calculerGenerationSuivante();
            }
        } );
        return nextGen;
    }

    /**
     * Creation de la box qui permet de restart 
     * la parti avec de nouveau parametre
     * @return
     */
    private Box restartBox(){
        Box box = Box.createHorizontalBox();
        JButton restart = new JButton("Generate new");
        JTextField size[] = textFieldWithListener( restart );


        restart.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.restart( Integer.parseInt(size[0].getText()) , Integer.parseInt(size[1].getText()) );
                jeu.notifieObservateurs();
            }
        } );

        box.add( new JLabel("  Rows : "));
        box.add(size[1]);
        box.add( new JLabel("    Columns : "));
        box.add(size[0]);
        box.add(restart);
        return box;
    }

    /**
     * Methode qui cree deux textField
     * et les renvois dans un tableau 
     * les listeners son associer
     * @param restart le bouton a modifier en enable true/false
     * @return 
     */
    private JTextField[] textFieldWithListener( JButton restart ){
        JTextField tf[] = { new JTextField() , new JTextField() };
        tf[0].setText( jeu.getXMax().toString() );
        tf[1].setText( jeu.getYMax().toString() );

        DocumentListener func = new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                enableRestartIfInteger( tf[0].getText().toString() , tf[1].getText().toString() , restart );
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableRestartIfInteger( tf[0].getText().toString() , tf[1].getText().toString() , restart );
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableRestartIfInteger( tf[0].getText().toString() , tf[1].getText().toString() , restart );
            }
            
        };

        tf[0].getDocument().addDocumentListener( func );
        tf[1].getDocument().addDocumentListener( func );

        return tf;
    }

    /**
     * Test si deux string son des ints
     * @param a chaine a
     * @param b chaine b
     * @return vrai/faux
     */
    private void enableRestartIfInteger(String a, String b , JButton restart){
        try {
            int a_int = Integer.parseInt(a);
            int b_int = Integer.parseInt(b);
            if( b_int > 0 && a_int > 0 )
                restart.setEnabled(true);
            else
                restart.setEnabled(false);
        } catch (final NumberFormatException e) {
            restart.setEnabled(false);
        }
    }

    /**
     * Cree la combobox et ca methode associer
     * @return
     */
    private JComboBox<Visiteur> comboBoxVisitor(){
        // Ma liste de visiteur
        Visiteur[] mesVisiteurs = { new VisiteurClassique(jeu), new VisiteurDayAndNight(jeu), new VisiteurHighLife(jeu), new VisiteurGenererMonde(jeu), new VisiteurCustom(jeu) };

        JComboBox<Visiteur> comboBox = new JComboBox<Visiteur>(mesVisiteurs);

        comboBox.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.setVisitor( (Visiteur)((JComboBox)e.getSource()).getSelectedItem() );

                // HIDE CUSTOM BY DEFAULT
                isCustomMode = false;
                boxVie.setVisible(isCustomMode);
                boxMort.setVisible(isCustomMode);

                if( jeu.getVisitor() instanceof VisiteurGenererMonde ){
                    comboBoxBg.setEnabled(false);
                    comboBoxPoint.setEnabled(false);
                    jeu.notifieObservateurs();
                }
                else {
                    if ( jeu.getVisitor() instanceof VisiteurCustom ) {
                        isCustomMode = true;
                        boxVie.setVisible(isCustomMode);
                        boxMort.setVisible(isCustomMode);
                    }
                    comboBoxBg.setEnabled(true);
                    comboBoxPoint.setEnabled(true);
                    jeu.notifieObservateurs();
                }
            }
        });

        return comboBox;
    }

    /**
     * Methode qui cree le Slider
     * @return
     */
    private Box btnSlider(){
        JLabel label = new JLabel("1 gen/secondes");
        label.setAlignmentX(CENTER_ALIGNMENT);

        JSlider slider = new JSlider(JSlider.HORIZONTAL,1,1000,1);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                Integer value = (int)((JSlider)e.getSource()).getValue();
                if( value > 0 ){
                    jeu.setFrequence( value );
                    label.setText(value.toString() + " gen/secondes");
                }
            }
        });

        Box box = Box.createVerticalBox();
        box.add( generationCount );
        box.add( slider );
        box.add( label );
        return box;
    }

    /**
     * Methode qui actualise la fenetre
     */
    @Override
    public void actualise() {
        repaint();
        generationCount.setText("Compteur generation : " + jeu.getGenerationCount().toString()); // Update le compteur
    }

    /**
     * Methode de dessin du jeu de la vie
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if( !jeu.estLancer() && builderIsOn )
            printGrilleConstruction(g);
        else 
            printJeu(g);
    }

    /**
     * Methode qui affiche le jeu en cours
     * @param g
     */
    private void printJeu(Graphics g){
        int xMax = jeu.getXMax();
        int yMax = jeu.getYMax();
        int width = (int)(getSize().width / xMax) * xMax;
        int height = (int)(getSize().height / yMax) * yMax;

        g.setColor(bgColor);
        if( jeu.getVisitor() instanceof VisiteurGenererMonde )
            g.setColor(Color.blue);

        g.fillRect(0, 0, getSize().width, getSize().height);

        g.setColor(pointColor);
        if( jeu.getVisitor() instanceof VisiteurGenererMonde )
            g.setColor(Color.green);
        for(int x  = 0 ; x < xMax; x++ ){
            for( int y  = 0 ; y < yMax; y++ ){
                if( jeu.getGrilleXY(x, y).estVivante() ){                    
                    g.fillOval( (x) * ( width / xMax ), (y) * (height / yMax ), ( width / xMax), ( height / yMax )); 
                }
            }
        }
    }

    /**
     * Methode qui affiche un constructeur de grille
     * @param g
     */
    private void printGrilleConstruction(Graphics g){
        int xMax = jeu.getXMax();
        int yMax = jeu.getYMax();
        int width = (int)(getSize().width / xMax) * xMax;
        int height = (int)(getSize().height / yMax) * yMax;
        
        g.setColor(Color.BLACK);

        for(int x  = 0 ; x < xMax; x++ ){
            g.fillRect(  (x + 1) * ( width / xMax ) , 0 , 2 , height);
        }
        for( int y  = 0 ; y < yMax; y++ ){
            g.fillRect( 0, (y + 1) *( height / yMax ) , (int)(width / xMax) * xMax , 2);
        }

        for(int x  = 0 ; x < xMax; x++ ){
            for( int y  = 0 ; y < yMax; y++ ){
                if( jeu.getGrilleXY(x, y).estVivante() ){                    
                    g.fillOval( (x) * ( getSize().width / xMax ), (y) * (getSize().height / yMax ), ( getSize().width / xMax), ( getSize().height / yMax )); 
                }
            }
        }
    }
}