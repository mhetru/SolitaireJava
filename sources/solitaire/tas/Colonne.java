package solitaire.tas;

import solitaire.tas.*;
import solitaire.carte.*;

public class Colonne extends Tas {

    public Colonne(int c) {
		super(c);
    }
    
    public boolean add(Carte para) {
        boolean rep = false;
        if ((nb_cartes == 0) && (para.getChiffre() == 13)) {
			rep = true;
        } else {
        	if ((nb_cartes == 0) && (para.getChiffre() != 13)) {
        		rep = false;
        	} else {
       			rep = (Math.abs((this.tas[this.getNb()].getCouleur())-1) == para.getCouleur()) && (((this.tas[this.getNb()].getChiffre())-1) == para.getChiffre());
       		}
      	}
		return rep;   
    }

    public int retournees() {
		int nbc = 0;
		if (nb_cartes != 0) {
		    for(int nb=1; nb<(nb_cartes+1); nb++) {
				if (tas[nb].isVisible() == false) {
			    	nbc++;
				}
			}
		}
		return nbc;
    }
    
    public int visibles() {
		int nbc = 0;
		if (nb_cartes != 0) {
		    for(int nb=1; nb<(nb_cartes+1); nb++) {
				if (tas[nb].isVisible() == true) {
				    nbc++;
				}
		    }
		}
		return nbc;
    }
}