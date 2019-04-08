package solitaire;

import solitaire.*;

public class Prog {
    public static void main(String argv[]) {
	    	
		Jeu game = new Jeu();
	
		game.affiche();
		
		System.out.println("");
		
	    String com = game.commandes("");
	      
	    while(!com.equals("q")) {
			game.affiche();
			com = game.commandes("");
	 	}
	    
    }
}
