package solitaire.interf;

public interface Constantes {
	int B = 10,       // bordure
	    L = 56,       // largeur carte
	    H = 87,       // hauteur carte
	    LC = L + B,     // largeur case
	    HC = H + B,     // hauteur case
	    LT = (7*LC) + B,  // largeur totale
	    HT = (4*HC) + B;  // hauteur totale
	
	int DX = 10,      // decalage lateral pour pioche
	    DY1 = 2,      // decalage vertical pour cartes non visibles (piles roi)
	    DY2 = 20;     // decalage vertical pour cartes visibles (piles roi)
}
