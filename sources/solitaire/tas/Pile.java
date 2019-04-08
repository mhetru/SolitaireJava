package solitaire.tas;

import solitaire.tas.*;
import solitaire.carte.*;

public class Pile extends Tas {

    public Pile(int p) {
		super(p);
    }

    public boolean add(Carte para) {
		boolean reponse = false;
		if ((nb_cartes == 0) && (para.getChiffre() != 1)) {
		    reponse = false;
		} else {
		    if ((nb_cartes == 0) && (para.getChiffre() == 1)) {
				reponse = true;
		    } else {
				reponse = ((this.tas[this.getNb()].getSigne() == para.getSigne()) && ((para.getChiffre() - 1) == this.tas[this.getNb()].getChiffre()));
		    }
		}
		return reponse;
    }
}