package solitaire;

import solitaire.*;
import solitaire.carte.*;
import solitaire.interf.*;
import solitaire.util.*;
import solitaire.tas.*;

import java.io.*;
import javax.swing.border.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

//--------------------------------------------------------------------------------------
//PROGRAMME REALISE PAR DES ETUDIANTS EN LICENCE DA2I A L'IUT LILLE1 A VILLENEUVE D'ASCQ
//				Copyright MaTh|eU HETRU & RaCh|d BARHOUNE & IuT LiLLe1
//--------------------------------------------------------------------------------------
public class Proggraph extends Jeu implements Constantes  {
    
    //Composant pour afficher les cartes de Klondike
    
    // Interface pour les constantes (doit etre implemente par les classes les utilisant ou par la classe englobante)

    // Attributs devant etre visibles des classes et des methodes

    private Icon dos;                           // image du dos
    private Icon[][] images = new Icon[4][13];    // image des cartes
    private JButton repeat;                     // bouton de repetition
    private Dessin d;                           // zone ou les cartes sont dessinees
    private JFrame frame = new JFrame("Solitaire (© Mathieu HETRU & Rachid BARHOUNE)");
    private JMenuBar barreMenu;
    private JMenu menuNewPartie;
    private JMenu menuAide;
    private JMenuItem menuNouvellePartie;
    private JMenuItem menuQuitter;
    private JMenuItem menuAPropos;
    private JMenuItem menuRegle;
    private Container pane;
    private JPanel panel;
    private int xPosition;
    private int yPosition;
    private JOptionPane msgbox;
    private JDialog dialog;
        
    private String source="";
	private String destination="";
	private String commandes="";
	private String niveau="";
    private JTextField etat;
    
    public Proggraph() {
        super();
		fabricationImages();
		Grille grid = new Grille();
        barreMenu = new JMenuBar();
        pane = frame.getContentPane();
        panel = new JPanel();
        menuNewPartie = new JMenu("Partie");
        menuAide = new JMenu("Aide");
         
        menuNouvellePartie = new JMenuItem("Nouvelle Partie") ;
        menuNouvellePartie.addActionListener(new Actions()); // définition de l'événement click pour le sous-menu  
        menuQuitter = new JMenuItem("Quitter la partie") ;
        menuQuitter.addActionListener(new Actions());

        barreMenu.add(menuNewPartie);
        
        menuNewPartie.add(menuNouvellePartie);
        menuNewPartie.addSeparator();
        menuNewPartie.add(menuNouvellePartie);
        menuNewPartie.add(menuQuitter);
        
        etat = new JTextField(""); 
         
        pane.add(panel,BorderLayout.NORTH);
        
        pane.add(grid,BorderLayout.CENTER);
        
        pane.add(etat,BorderLayout.SOUTH);
	 	frame.setJMenuBar(barreMenu);
	 	frame.addWindowListener(new WindowAdapter() {
	 		public void windowClosing(WindowEvent e) {
	 			System.exit(0);
	 		}});
		frame.pack();
		frame.show();
    }
    
    public class Actions implements ActionListener {
	 	public void actionPerformed(ActionEvent e) {
	  	  	Object o = e.getSource();
	  	  	if (o == menuQuitter) {
				System.exit(0);
			}
			
	 	    if (o == menuNouvellePartie) {
	 		   	nouvelle_partie();
	 		   	afficher();
	 	  	}
		}
    }

    // Methode qui lit le fichier avec les images et le decoupe en images de cartes
    public void fabricationImages() {
		Image tout, image;
		tout = Toolkit.getDefaultToolkit().getImage("solitaire/images/Cartes256Tok.GIF");
		for(int i = 0; i < 4; i++) {
	    	for(int j=0; j<13; j++) { 
				ImageFilter cropFilter = new CropImageFilter(j * L, i * H , L, H);
				image = frame.createImage(new FilteredImageSource(tout.getSource(),cropFilter));
				images[i][j] = new ImageIcon(image);
	    	}
		}
	  	dos = new ImageIcon("solitaire/images/Dos256Tok.GIF");
    }

    // Methode d'affichage/reaffichage des tas de cartes
    public void afficher() { 
		//super.affiche(); // affichage dans la console
		d.repaint();      // reaffichage des cartes graphiques
    }

    // Classe annexe pour dessiner le fond (peut etre une classe interne)
    public class Fond extends JPanel { 
		public Fond() {
	    	setOpaque(true);
	    	setPreferredSize(new Dimension(LT,HT));
		}
	
		public void paintComponent(Graphics g) {
	    	int i,j;
	    	super.paintComponent(g);
	    	g.setColor(new Color(0,0,123)); // couleur du fond
	    	g.fillRect(0,0,LT,HT);
		    g.setColor(Color.black);
		    for (i=0; i<7; i++) {
				for (j=0; j<2; j++) {
				    if (i!=0&&i!=2||j!=0) {
					g.drawRoundRect(B+LC*i,B+HC*j,L-1,H-1,15,15);
				    }
				}
		    }
		}
    }
    
    
    // Classe annexe pour dessiner les tas de cartes, les adapter (peut etre une classe interne)
    public class Dessin extends JPanel { 
	
		public Dessin() {
		    setOpaque(false);
		    setPreferredSize(new Dimension(LT,HT));
		}
		
		public void paintComponent(Graphics g) {
		    int i,j;
		    Carte c;
		    int visibles = 0;
		    int retournees = 0;
		    String s = "";
		    super.paintComponent(g);
		    
		    // Pioche1
		    if (pioche1.vide() == false) {
				dos.paintIcon(null,g,B,B);
		    }
		    
		    int dx = 0;
		    int n = pioche2.getNb();
		    int v=1; // 0, 1, 2 ou 3
		    
		    if (source.equals("P")) {
		    	g.setColor(Color.white);
		    	g.drawRect(B+LC+dx-(B/2), B-(B/2), LC, H+B);
		    }
		    
		    if (n != 0) {
				//pioche2
				for (i = n-v+1; i < n+1; i++) { 
				    c = pioche2.getCarte(i);
				    images[c.getSigne()][c.getChiffre()-1].paintIcon(null, g, B+LC+dx, B); 
				    dx += DX; 
				}
		    }
		    
		    i = 0;
			// piles du haut
			if (pile1.vide() == false) {
			    c = pile1.getCarte(pile1.getNb()); 
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null, g, B+(i+3)*LC, B);
			    
			}
			
	        i++;
			if (pile2.vide() == false) {
			    c = pile2.getCarte(pile2.getNb());
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null, g, B+(i+3)*LC, B);
			    
			}
			
	        i++;
			if (pile3.vide() == false) {
			    c = pile3.getCarte(pile3.getNb());
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null, g, B+(i+3)*LC, B);
			    
			}
			
	        i++;
			if (pile4.vide() == false) {
			    c = pile4.getCarte(pile4.getNb()); 
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null, g, B+(i+3)*LC, B);
			    
			}
			
			
			i=0;
			//pour les colonnes
			int dy = 0;
			for (j = 1; j < colonne1.retournees()+1; j++) {
			    dos.paintIcon(null, g, B+i*LC, B+HC+dy);
			    dy+=DY1;
			}
			
			if (source.length() > 2) {
				s = source.substring(0,2);	
			} else {
				s = source;	
			}
			
			if ((s.equals("C1")) && (colonne1.getNb() == 1)) {
		    	g.setColor(Color.white);
		    	//images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+LC+dx,B);
		    	//g.drawRect(B+LC+dx-(B/2), B-(B/2), LC, H+B);
		    	g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, H+B);

		    }
		    
		    if ((colonne1.getNb() > 1) && (s.equals("C1"))) {
	    		g.setColor(Color.white);
				visibles = colonne1.visibles();
				retournees = colonne1.retournees();
				if (visibles == 1) { // en fait si il y a une carte
	    			g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    		} else {
	    			if (visibles != 0) {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+((DY2*visibles)-DY2)+(DY1*retournees));
	    			} else {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    			}
	    		}
		    }
			
			
			for(j = colonne1.retournees()+1; j < colonne1.getNb()+1; j++) { 
			    c = colonne1.getCarte(j);
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null, g, B+i*LC, B+HC+dy);
			    dy += DY2; 
			}
			
			i++;
			dy = 0;			
			if ((s.equals("C2")) && (colonne2.getNb() == 1)) {
		    	g.setColor(Color.white);
		    	g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, H+B);

		    }
		    
		    if ((colonne2.getNb() > 1) && (s.equals("C2"))) {
	    		g.setColor(Color.white);
				visibles = colonne2.visibles();
				retournees = colonne2.retournees();				
				if (visibles == 1) { // en fait si il y a une carte
	    			g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    		} else {
	    			if (visibles != 0) {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+((DY2*visibles)-DY2)+(DY1*retournees));
	    			} else {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    			}
	    		}
		    }
			
			
			for (j=1; j < colonne2.retournees()+1; j++) {
			    dos.paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY1;
			}
			
			for (j=colonne2.retournees()+1; j<colonne2.getNb()+1; j++) { 
			    c=colonne2.getCarte(j);
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY2; 
			}
			
			i++;
			dy=0;			
			if ((s.equals("C3")) && (colonne3.getNb() == 1)) {
		    	g.setColor(Color.white);
		    	//images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+LC+dx,B);
		    	//g.drawRect(B+LC+dx-(B/2), B-(B/2), LC, H+B);
		    	g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, H+B);

		    }
		    
		    if ( (colonne3.getNb() > 1) && (s.equals("C3")) ) {
	    		g.setColor(Color.white);
				visibles=colonne3.visibles();
				retournees=colonne3.retournees();				
				if (visibles == 1) { // en fait si il y a une carte
	    			g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    		} else {
	    			if (visibles != 0) {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+((DY2*visibles)-DY2)+(DY1*retournees));
	    			} else {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    			}
	    		}
		    }
			
			for (j=1; j<colonne3.retournees()+1; j++) {
			    dos.paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY1;
			}
	
			for (j=colonne3.retournees()+1; j<colonne3.getNb()+1; j++) { 
			    c=colonne3.getCarte(j);
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY2; 
			}
			
			i++;
			dy=0;			
			if ((s.equals("C4")) && (colonne4.getNb() == 1)) {
		    	g.setColor(Color.white);
		    	//images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+LC+dx,B);
		    	//g.drawRect(B+LC+dx-(B/2), B-(B/2), LC, H+B);
		    	g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, H+B);

		    }
		    
		    if ( (colonne4.getNb() > 1) && (s.equals("C4")) ) {
	    		g.setColor(Color.white);
				visibles=colonne4.visibles();
				retournees=colonne4.retournees();				
				if (visibles == 1) { // en fait si il y a une carte
	    			g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    		} else {
	    			if (visibles != 0) {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+((DY2*visibles)-DY2)+(DY1*retournees));
	    			} else {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    			}
	    		}
		    }
			
			for (j=1; j<colonne4.retournees()+1; j++) {
			    dos.paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY1;
			}
	
			for (j=colonne4.retournees() + 1; j<colonne4.getNb()+1; j++) { 
			    c=colonne4.getCarte(j);
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY2; 
			}
			
			i++;
			dy=0;			
			if ((s.equals("C5")) && (colonne5.getNb() == 1)) {
		    	g.setColor(Color.white);
		    	//images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+LC+dx,B);
		    	//g.drawRect(B+LC+dx-(B/2), B-(B/2), LC, H+B);
		    	g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, H+B);

		    }
		    
		    if ( (colonne5.getNb() > 1) && (s.equals("C5")) ) {
	    		g.setColor(Color.white);
				visibles=colonne5.visibles();
				retournees=colonne5.retournees();				
				if (visibles == 1) { // en fait si il y a une carte
	    			g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    		} else {
	    			if (visibles != 0) {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+((DY2*visibles)-DY2)+(DY1*retournees));
	    			} else {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    			}
	    		}
		    }
			
			for (j=1; j<colonne5.retournees()+1; j++) {
			    dos.paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY1;
			}
	
			for (j=colonne5.retournees() + 1; j<colonne5.getNb()+1; j++) { 
			    c=colonne5.getCarte(j);
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY2; 
			}
			
			i++;
			dy=0;			
			if ((s.equals("C6")) && (colonne6.getNb() == 1)) {
		    	g.setColor(Color.white);
		    	//images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+LC+dx,B);
		    	//g.drawRect(B+LC+dx-(B/2), B-(B/2), LC, H+B);
		    	g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, H+B);

		    }
		    
		    if ( (colonne6.getNb() > 1) && (s.equals("C6")) ) {
	    		g.setColor(Color.white);
				visibles=colonne6.visibles();
				retournees=colonne6.retournees();				
				if (visibles == 1) { // en fait si il y a une carte
	    			g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    		} else {
	    			if (visibles != 0) {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+((DY2*visibles)-DY2)+(DY1*retournees));
	    			} else {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    			}
	    		}
		    }
			
			for (j=1; j<colonne6.retournees()+1; j++) {
			    dos.paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY1;
			}
	
			for (j=colonne6.retournees() + 1; j<colonne6.getNb()+1; j++) { 
			    c=colonne6.getCarte(j);
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY2; 
			}
			
			i++;
			dy=0;			
			if ((s.equals("C7")) && (colonne7.getNb() == 1)) {
		    	g.setColor(Color.white);
		    	//images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+LC+dx,B);
		    	//g.drawRect(B+LC+dx-(B/2), B-(B/2), LC, H+B);
		    	g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, H+B);

		    }
		    
		    if ( (colonne7.getNb() > 1) && (s.equals("C7")) ) {
	    		g.setColor(Color.white);
				visibles=colonne7.visibles();
				retournees=colonne7.retournees();				
				if (visibles == 1) { // en fait si il y a une carte
	    			g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    		} else {
	    			if (visibles != 0) {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+((DY2*visibles)-DY2)+(DY1*retournees));
	    			} else {
	    				g.drawRect(B+(LC*i)-(B/2), B+HC+dy-(B/2), LC, HC+(DY1*retournees));
	    			}
	    		}
		    }
			

			for (j= 1; j<colonne7.retournees()+1; j++) {
			    dos.paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY1;
			}
	
			for (j=colonne7.retournees() + 1; j<colonne7.getNb()+1; j++) { 
			    c=colonne7.getCarte(j);
			    images[c.getSigne()][c.getChiffre()-1].paintIcon(null,g,B+i*LC,B+HC+dy);
			    dy+=DY2; 
			}   
		}
    }

    // Composant multicouches (fond et dessin) l'ajouter a la fenetre (peut etre une classe interne)
    
    public class Grille extends JLayeredPane {
	
		//constructeur
		public Grille() {
		    int i,j,k; // constructeur
		    setLayout(null);
		    setPreferredSize(new Dimension(LT,HT)); 
		    //this.addMouseListener(new myMouseClick());
		    	    
		    d=new Dessin();
		    d.addMouseListener(new myMouseClick());
		    add(d,new Integer(1)); // couche 1
		    d.setBounds(0,0,LT,HT); 
		    
		    Fond f=new Fond();
		    f.addMouseListener(new myMouseClick());
		    add(f,new Integer(0)); // couche 0
		    f.setBounds(0,0,LT,HT); 
		}
    }
    
    //methode qui teste si la partie est gagnee
    public boolean partie_gagnee() {
    	return ((pile1.getNb()==13) && (pile2.getNb()==13) && (pile3.getNb()==13) && (pile4.getNb()==13));
    }
    
    //methode main
    public static void main(String argv[]) {
		Proggraph game  = new Proggraph();
		game.afficher();     
   	}
    
    //evenements souris
	public class myMouseClick extends MouseAdapter {
		public void mouseClicked(MouseEvent e){
			String chaine = "";
			xPosition = e.getX();
			yPosition = e.getY();
			chaine = new Selection().selectionne(xPosition,yPosition,source);

			try {
				//si le tas sélectionné n'est pas la pioche
				if (!chaine.equals("R")) {
					//si il existe déjà la source sélectionnée
					if ((!source.equals("")) && (destination.equals(""))) {
						//on stocke alors la destination
						destination=chaine;
						//si la source & la destination ne sont pas identiques
						if (!source.equals(destination)) {
							//on execute ...
							commandes(source+destination);
							source="";
							//si la partie est gagnée on sort du jeu
							if (partie_gagnee()==true) {
								System.out.println("VOUS AVEZ GAGNE LA PARTIE !!");
								System.exit(0);
							}
					 	} else {
							//sinon on retourne la carte
							commandes(source);		
					    }
					}
				
				
					//si ils sont vides
					if ((source.equals("")) && (destination.equals(""))) {
						//alors je stocke la source
						source=chaine;
					}
						
					//effacement des mémorisations
					if ((source.equals(destination)) || ((!source.equals("")) && (!destination.equals(""))) || (source.equals("") && (!destination.equals("")))) {
						source="";
						destination="";	
					}
				} else {
					//on pioche
					commandes(chaine);
					source="";
					destination="";
				}
				
			} catch(NullPointerException ex) { }
			
			
			//A chaque clic on met à jour l'affichage
			afficher();
		} 
	}
  	
  	//classe gerant les selections de cartes
	public class Selection {
		
		//constructeur vide
		public Selection() { }
		  
	    //Sélection de carte pour Klondike
		public String selectionne(int x, int y, String string_source) {
			String chain="";
			int i,j,nivo;
			//i correspond au numéro de ligne (0 pour la ligne du haut, et 1 pour la ligne du bas)
			i=y/HC;
			//j correspond au numéro de colonne (0 pour la premiere colonne à 7 pour la derniere colonne)
			j=x/LC;
			nivo=y-HC-B;
			
			if (x%LC<B && !(i==0 && j==2)) {// pour pioche
				return null;
			} 
			if (y%HC<B && i<2) {// pour colonne
				return null;
			}
			
			//sur la premiere ligne, on teste
			if (i==0) {//si c'est la premiere colonne, on pioche
				if (j==0) {
					chain=Pioche1Selectionne();
				} else {
					if ((j==1) || (j==2)) {//si on est dans la seconde colonne ou troisieme, c'est une selection dans la pioche2
			        	chain=Pioche2Selectionne();
			        } else {
			        	if (j>=3 && j<=6) {//sinon on selectionne une carte des pile d'as
			        		chain=PileSelectionne(j-3);
			        	}
			       }
			    }
			} else {//sinon on est dans les lignes du bas
				//si on a selectionne une colonne du bas
				chain=ColonneSelectionne(j,y,string_source);
		    }
			return chain;
		}
	
		public String Pioche1Selectionne() { 
			String chaine="";
			if (pioche1.vide()==true) {
				SelectionRenverserPioche();
				chaine="";
			} else {
				chaine="R";
			}
			return chaine;
		}
		
		public void SelectionRenverserPioche() {
			for(int nb = pioche2.getNb();nb>0;nb--) {
				pioche1.ajouter(pioche2.retirer(nb));
			}	
		}
	
		public String Pioche2Selectionne() {
			String retour="";
			int n,v=1;
			if (pioche2.vide()==true) {//si la pioche2 est vide, on retourne ""
				retour="";
			} else {
				retour="P";
			} 
			return retour;
		}
	
		public String PileSelectionne(int j) {
			String retour="";
			switch(j) {// cible possible
				case 0:retour="P1";
				break;
				case 1:retour="P2";
				break;
				case 2:retour="P3";
				break;
				case 3:retour="P4";
				break;	
			}
			return retour;
		}
	
		public String ColonneSelectionne(int j,int y,String chaine_source) { 
		    String retour="";
		    int dy1=0;
		    int dy2=0;
		    int cartes=0;
		    int visibles=0;
		    int retournees=0;
		    
		    switch(j) {//on détermine le nom du tas sélectionné, le nb de cartes ...
				case 0:retour="C1";
						cartes=colonne1.getNb();
						visibles=colonne1.visibles();
						retournees=colonne1.retournees();
				break;
				case 1:retour="C2";
						cartes=colonne2.getNb();
						visibles=colonne2.visibles();
						retournees=colonne2.retournees();
				break;
				case 2:retour="C3";
						cartes=colonne3.getNb();
						visibles=colonne3.visibles();
						retournees=colonne3.retournees();
				break;
				case 3:retour="C4";
						cartes=colonne4.getNb();
						visibles=colonne4.visibles();
						retournees=colonne4.retournees();
				break;
				case 4:retour="C5";
						cartes=colonne5.getNb();
						visibles=colonne5.visibles();
						retournees=colonne5.retournees();
				break;
				case 5:retour="C6";
						cartes=colonne6.getNb();
						visibles=colonne6.visibles();
						retournees=colonne6.retournees();
				break;
				case 6:retour="C7";
						cartes=colonne7.getNb();
						visibles=colonne7.visibles();
						retournees=colonne7.retournees();
				break;
			}

			//jauge correspond à la position de la carte sélectionné à la souris
			//pour permettre le déplacement de tas
			int jauge=0;
			int resultat=y-107-(retournees*DY1);
			
			//si la source est vide et qu'il existe plus d'une carte dans le tas sélectionné
			if ((chaine_source.equals("")) && (cartes>1)) {
				
				int ii=0;
				for(int t=0;t<(DY2*22);t=(t+DY2)) {
					ii++;
					if ((resultat>t) && (resultat <=t+DY2)) {
						jauge=retournees+ii;
					}	
				}
				
				//si la position de la carte sélectionné est inférieur au nb de cartes du tas
				if (jauge < cartes) {
					retour+="-"+jauge+"-";
				}//sinon on conserve le nom du tas dans la chaine "retour"
			}
			return retour;
		}
	}
}