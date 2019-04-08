package solitaire.tas;

import solitaire.carte.*;

public abstract class Tas {
    protected Carte[] tas;
    protected int nb_cartes;

    public Tas(int i) {
		tas = new Carte[i];
		for(int j=1; j<i; j++) {
		    tas[j] = null;
		}
		nb_cartes = 0;
    }
    
    public void raz() {
    	for(int i=nb_cartes; i>(-1); i--) {
    		tas[i] = null;	
    	}
    	nb_cartes = 0;
    }
    
    public void ajouter(Carte para) {
		nb_cartes++;
		tas[nb_cartes] = para;
    }

    public Carte retirer() {
		Carte tmp = new Carte(0,0);
		tmp = tas[nb_cartes];
		tas[nb_cartes] = null;
		nb_cartes--;
	    return tmp;
    }

    public Carte retirer(int c) {
		Carte retire = new Carte(0,0);
        retire = tas[c];
        tas[c] = null;
        nb_cartes--;
        return retire;
    }

    public abstract boolean add(Carte para);

    public void afficher(int i) {
		System.out.println(tas[i]);
    }

    public Carte getCarte(int j) {
		return tas[j];
    }

    public int getNb() {
		return nb_cartes;
    }

    public boolean vide() {
		return (nb_cartes == 0);
    }
    
    public boolean derniere_carte() {
    	return tas[nb_cartes].isVisible();
	}
}