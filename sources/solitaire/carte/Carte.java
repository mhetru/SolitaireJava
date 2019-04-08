package solitaire.carte;

public class Carte {
    private static final int AS = 1;   
    private static final int DEUX = AS + 1;
    private static final int TROIS = DEUX + 1;   
    private static final int QUATRE = TROIS + 1;
    private static final int CINQ = QUATRE + 1;
    private static final int SIX = CINQ + 1;
    private static final int SEPT = SIX + 1;
    private static final int HUIT = SEPT + 1;
    private static final int NEUF = HUIT + 1; 
    private static final int DIX = NEUF + 1;
    private static final int VALET = DIX + 1;
    private static final int DAME = VALET + 1;
    private static final int ROI = DAME + 1;
    
    private boolean visible = false;
    private int chiffre = 0;
    private int couleur = 0;
    private int signe = 0;

    public Carte(int ch, int sig, boolean vis) {
		chiffre = ch;
		if ((sig == 0) || (sig == 3)) {
		    couleur = 0;
		} else {
		    couleur = 1;
		}
		signe = sig;
		visible = vis;
    }

    public Carte(int ch, int sig) {
		chiffre = ch;
		if ((sig == 0) || (sig == 1)) {
		    couleur = 0;
		} else {
		    couleur = 1;
		}
		signe = sig;
		visible = true;
    }
    
    public String toString() {
		String chaine = "";
		if (visible == true) {
		    switch(chiffre) {
			    case AS:
			    	chaine = "   as";
					break;
			    case VALET:
			    	chaine = "valet";
				break;
			    case DAME:
			    	chaine = " dame";
					break;
			    case ROI:
			    	chaine = "  roi";
					break;
			    default:
					if (chiffre != 10) {
				    	chaine = "    " + chiffre;
					} else {
				    	chaine = "   " + chiffre;
					}
					break;
		    }
		    chaine += " ";
		    switch(signe) {
			    case 0:
			    	chaine += "pic ";
					break;
			    case 1:
			    	chaine += "tref";
					break;
			    case 2:
			    	chaine += "caro";
					break;
			    case 3:
			    	chaine += "coeu";
					break;
		    }
		} else {
		    chaine = "**********";
		}	   
		return chaine;
    }

    public boolean isVisible() {
		return visible;
    }
    
    public boolean estRouge(){
		return (couleur == 1);
    }

    public boolean estNoire(){
        return (couleur == 0);
    }    

    public void cacher() {
		visible = false;
    }

    public void montrer() {
		visible = true;
    }

    public boolean estPosableTas(Carte para) {
		return (para.couleur == this.couleur); 
    }

    public boolean estPosablePile(Carte para) {
		return ((para.couleur == this.couleur) && (para.signe == this.signe)); 
    }
    
    public int getChiffre() {
    	return chiffre;	
    }
    
    public int getSigne() {
    	return signe;	
    }
    
    public int getCouleur() {
    	return couleur;	
    }
}