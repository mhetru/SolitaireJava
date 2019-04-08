package solitaire;

import solitaire.util.*;
import solitaire.carte.*;
import solitaire.tas.*;

public class Jeu {
    //variables static final correspondant aux colonnes
    // 1=pioche1  2=pioche2             3=pile1     4=pile2     5=pile3     6=pile4
    // 7=colonne1 8=colonne2 9=colonne3 10=colonne4 11=colonne5 12=colonne6 13=colonne7
    private static final int R = 1;   
    private static final int P = R + 1;
    private static final int P1 = P + 1;   
    private static final int P2 = P1 + 1;
    private static final int P3 = P2 + 1;
    private static final int P4 = P3 + 1;
    private static final int C1 = P4 + 1;
    private static final int C2 = C1 + 1;
    private static final int C3 = C2 + 1; 
    private static final int C4 = C3 + 1;
    private static final int C5 = C4 + 1;
    private static final int C6 = C5 + 1;
    private static final int C7 = C6 + 1;

    //tableau contenant toutes les cartes crees
    private Carte[] tableau = new Carte[53];

    //tas de cartes representant les 2 pioches
    public Pioche pioche1 = new Pioche(25);
    public Pioche pioche2 = new Pioche(25);

    //tas de cartes representant les 4 piles de cartes ordonnees
    public Pile pile1 = new Pile(14);
    public Pile pile2 = new Pile(14);
    public Pile pile3 = new Pile(14);
    public Pile pile4 = new Pile(14);
    
    //tas de cartes representant les 7 tas de cartes du bas
    public Colonne colonne1 = new Colonne(14);
    public Colonne colonne2 = new Colonne(15);
    public Colonne colonne3 = new Colonne(16);
    public Colonne colonne4 = new Colonne(17);
    public Colonne colonne5 = new Colonne(18);
    public Colonne colonne6 = new Colonne(19);
    public Colonne colonne7 = new Colonne(20);

    //constructeur
    public Jeu() {
		this.initialise();
		this.melange();
		this.distribue();
    }
    
    //methode qui relance une nouvelle partie sur la partie actuelle
    public void nouvelle_partie() {
	    //tous les tas de cartes sont vidés et remis à zero
	    pioche1.raz();
	    pioche2.raz();
	    pile1.raz();
	    pile2.raz();
	    pile3.raz();
	    pile4.raz();
	    colonne1.raz();
	    colonne2.raz();
	    colonne3.raz();
	    colonne4.raz();
	    colonne5.raz();
	    colonne6.raz();
	    colonne7.raz();
		
		//on reinitialise un jeu de 52 cartes
		//=on recree un jeu de 52 cartes
		//car quand on l'a distribué tout à l'heure
		//on a effacé le grand tableau de cartes avec des valeurs null
		initialise();
		
		//on remélange le grand tableau de cartes principal
		melange();
		
		//on le distribue
		distribue();
    }
    
    //cette methode vide cree toutes les cartes du jeu et les stocke dans "tableau"
    public void initialise() {

		//creation des cartes de pique de l'as au roi
		for(int i = 1; i < 14; i++) {
		    tableau[i] = new Carte(i,0);
		}
		
		//creation des cartes de coeur de l'as au roi
		for(int i = 14; i < 27; i++) {
		    tableau[i] = new Carte(i-13,1);
		}
		
		//creation des cartes de carreau de l'as au roi
		for(int i = 27; i < 40; i++) {
		    tableau[i] = new Carte(i-26,2);
		}
		
		//creation des cartes de trefle de l'as au roi
		for(int i = 40; i < 53; i++) {
		    tableau[i] = new Carte(i-39,3);
		}	
	}
	
	
	//cette methode vide melange toutes les cartes avec la methode de permutation de cartes
	public void melange() {
		//je cree une carte qui stockera la carte à l'indice "i"
		Carte stock=new Carte(0,0);
		for(int i=1; i<53; i++) {
		    stock = tableau[i];
		    //la carte à l'indice "nb_hasard" sera la carte à l'indice "i"
		    int nb_hasard = (int)(Math.random()*52) + 1;
		    tableau[i] = tableau[nb_hasard];
		    //je stocke la carte de l'indice "i" à la place de la carte de l'indice "nb_hasard"
		    tableau[nb_hasard] = stock;
		}
    }

    //cette methode vide distribue les cartes du "tableau" dans tous les tas de cartes
    void distribue() {
		int nb = tableau.length-1;
		
		//on stocke la derniere carte "tableau[nb]" du tableau dans le premier tas (tas1)
		colonne1.ajouter(tableau[nb]);
		tableau[nb] = null;
		//on decremente le nb de cartes "nb" du tableau de 1
		nb--;
		
		//on cache la carte avant de la mettre dans le tas2
		tableau[nb].cacher();
	
		//on stocke les cartes "nb" a "nb-2" du tableau dans le second tas (tas2)
		for(int j = nb; j>(nb-2); j--) {	    
		    colonne2.ajouter(tableau[j]);
		    tableau[j] = null;   
		}
		//on decremente le nb de cartes "nb" du tableau de 2
		nb -= 2;
		
		//on cache deux cartes avant de les mettre dans le tas3
		tableau[nb].cacher();
		tableau[nb-1].cacher();
		
		//on stocke les cartes "nb" a "nb-3" du tableau dans le 3eme tas (tas3)
	    for(int k = nb; k>(nb-3); k--) {
		    colonne3.ajouter(tableau[k]);
		    tableau[k] = null;
		}
		
		//on decremente le nb de cartes "nb" du tableau de 3
	    nb -= 3;
	
	    //on cache trois cartes avant de les mettre dans le tas4
		tableau[nb].cacher();
		tableau[nb-1].cacher();
		tableau[nb-2].cacher();
	
		//on stocke les cartes "nb" a "nb-4" du tableau dans le 4eme tas (tas4)
		for(int l = nb;l > (nb-4); l--) {
		    colonne4.ajouter(tableau[l]);
		    tableau[l] = null;
		}
		//on decremente le nb de cartes "nb" du tableau de 4
		nb -= 4;
		
		//on cache quatres cartes avant de les mettre dans le tas5
		tableau[nb].cacher();
		tableau[nb-1].cacher();
		tableau[nb-2].cacher();
		tableau[nb-3].cacher();
	
		//on stocke les cartes "nb" a "nb-5" du tableau dans le 5eme tas (tas5)
		for(int m = nb; m > (nb-5); m--) {
		    colonne5.ajouter(tableau[m]);
		    tableau[m] = null;
		}
		//on decremente le nb de cartes "nb" du tableau de 5
		nb -= 5;
	
	    //on cache cinq cartes avant de les mettre dans le tas6
		tableau[nb].cacher();
		tableau[nb-1].cacher();
		tableau[nb-2].cacher();
		tableau[nb-3].cacher();
		tableau[nb-4].cacher();
	
		//on stocke les cartes "nb" a "nb-6" du tableau dans le 6eme tas (tas6)
		for(int n = nb; n > (nb-6); n--) {
		    colonne6.ajouter(tableau[n]);
		    tableau[n] = null;
		}
		//on decremente le nb de cartes "nb" du tableau de 6
		nb -= 6;
	
	    //on cache six cartes avant de les mettre dans le tas7
		tableau[nb].cacher();
	    tableau[nb-1].cacher();
		tableau[nb-2].cacher();
		tableau[nb-3].cacher();
		tableau[nb-4].cacher();
		tableau[nb-5].cacher();
		
		//on stocke les cartes "nb" a "nb-7" du tableau dans le 7eme tas (tas7)
	    for(int o = nb; o > (nb-7); o--) {
		    colonne7.ajouter(tableau[o]);
		    tableau[o] = null;
		}
		//on decremente le nb de cartes "nb" du tableau de 7
		nb -= 7;
	
		//on stocke le reste du "tableau" dans la pioche1
		for(int p = nb; p > 0; p--) {
		    pioche1.ajouter(tableau[p]);
		    tableau[p] = null;
		}
		//on stocke la valeur zero dans nb
        nb = 0;
	}

    public void affiche() {
	
		//la 1ere ligne des tirets "-"
		for(int i = 1; i < 79; i++) {
		    System.out.print("-");
		}
	
		//on retourne a la ligne
		System.out.print("\n");
		
		//on affiche la ligne 2
		System.out.println("|    R     |    P     |          |    P1    |    P2    |    P3    |    P4    |");
	
		//l'indice maximal correspond au nb de cartes maximum des 4 piles de cartes
	
		//on construit la chaine 1 
		String chaine = "|";
	
	        
		if (pioche1.getNb() != 0) {
		    chaine += "**********";
		} else {
		    chaine += "          ";
		}
		chaine += "|";
		if (pioche2.getNb() == 0) {
		    chaine += "          ";
		} else {
		    chaine += pioche2.getCarte(pioche2.getNb()).toString();
		}
		chaine += "|          |";
		if (pile1.getNb() == 0) {
		    chaine += "          ";
		} else {
		    chaine += pile1.getCarte(pile1.getNb()).toString();
		}
		chaine+="|";
		if (pile2.getNb() == 0) {
		    chaine += "          ";
		} else {
		    chaine += pile2.getCarte(pile2.getNb()).toString();
		}
		chaine += "|";
		if (pile3.getNb() == 0) {
		    chaine += "          ";
		} else {
		    chaine += pile3.getCarte(pile3.getNb()).toString();
		}
		chaine += "|";
		if (pile4.getNb() == 0) {
		    chaine += "          ";
		} else {
		    chaine += pile4.getCarte(pile4.getNb()).toString();
		}
		chaine += "|";
		
	
		System.out.print("" + chaine);
	
		System.out.print("\n");
		chaine = "";
	
		//on affiche la seconde ligne de tirets
		for(int i = 1; i < 79; i++) {
		    System.out.print("-");
		}
		System.out.print("\n");
	
		//on affiche la ligne des noms de colonnes
		System.out.println("|    C1    |    C2    |    C3    |    C4    |    C5    |    C6    |    C7    |");
		
		int indice_maxi = 0;
		//l'indice maximal correspond au nb de cartes maximum des 4 piles de cartes
		indice_maxi = (int)Math.max((double)colonne1.getNb(),(double)colonne2.getNb());
		indice_maxi = (int)Math.max((double)indice_maxi,(double)colonne3.getNb());
		indice_maxi = (int)Math.max((double)indice_maxi,(double)colonne4.getNb());
		indice_maxi = (int)Math.max((double)indice_maxi,(double)colonne5.getNb());
		indice_maxi = (int)Math.max((double)indice_maxi,(double)colonne6.getNb());
		indice_maxi = (int)Math.max((double)indice_maxi,(double)colonne7.getNb());
	
		
		//on affiche le contenu des tas de cartes du bas
		
		for(int pp = 1; pp < indice_maxi + 1; pp++) {
		    String chainee = "|";
		    if (colonne1.getCarte(pp) != null) {
				chainee += colonne1.getCarte(pp).toString();
		    } else {
				chainee += "          ";
		    }
		    chainee += "|";
		    
		    if (colonne2.getCarte(pp) != null) {
				chainee += colonne2.getCarte(pp).toString();
		    } else {
				chainee += "          ";
		    }
		    chainee += "|";
	
		    if (colonne3.getCarte(pp) != null) {
				chainee += colonne3.getCarte(pp).toString();
		    } else {
				chainee += "          ";
		    }
		    chainee += "|";
		    
		    if (colonne4.getCarte(pp) != null) {
				chainee += colonne4.getCarte(pp).toString();
		    } else {
				chainee += "          ";
		    }
		    chainee+="|";
	
		    if (colonne5.getCarte(pp) != null) {
				chainee += colonne5.getCarte(pp).toString();
		    } else {
				chainee += "          ";
		    }
		    chainee += "|";
	
		    if (colonne6.getCarte(pp) != null) {
				chainee += colonne6.getCarte(pp).toString();
		    } else {
				chainee	+= "          ";
		    }
		    chainee += "|";
	
		    if (colonne7.getCarte(pp) != null) {
				chainee+=colonne7.getCarte(pp).toString();
		    } else {
				chainee+="          ";
		    }
		    chainee+="|";
	
		    System.out.print(""+chainee);
		    System.out.print("\n");
		}
		
		//la derniere ligne des tirets "-"
		for(int i = 1; i < 79; i++) {
		    System.out.print("-");
		}
		//je retourne a la ligne
		System.out.print("\n");
    }

    public String commandes(String param) {
    	String commande = "";
    	if (!param.equals("")) {
    		commande = param;	
    	}
		int tas_destination = 0;
		int tas_source = 0;
		int tas_depart = 0;
		int compteur = 1;
		int indice = 0;
		int indice1 = 0;
		String source = "";
		String depart = "";
		String destination = "";
		
		if (!param.equals("")) {
    		commande = param;	
    	} else {
			System.out.println("Commencez a jouer");
			commande = Clavier.readString();
		}
	
		//decoupage de la chaine en tas source & destination pour alleger les traitements de deplacements
		
		//carte temporaire source
		// pioche1=P  pioche2=R                pile1=P1    pile2=P2    pile3=P3    pile4=P4
		// colonne1=C1 colonne2=C2 colonne3=C3 colonne4=C4 colonne5=C5 colonne6=C6 colonne7=C7
	
		
		Carte tmp = new Carte(0,0);
		Carte[] tab_tmp;
		tab_tmp = new Carte[13];
		int cpt_tab_tmp = 0;
		boolean reponse = false;
		
		
		//DECOUPAGE DES CHAINES DE COMMANDES
		switch(commande.length()) {
			case 1:
			    //on pioche
			    source = "R";
			    tmp = pioche1.getCarte(pioche1.getNb());
			    destination = "P";
			    break;
			case 2:
			    source = commande;
			    break;
			case 3:
			    //ca vient de la pioche
			    source = commande.substring(0,1);
			    tmp = pioche2.getCarte(pioche2.getNb());
			    destination = commande.substring(1,commande.length());
			    break;
			case 4:
			    source = commande.substring(0,2);
			    destination = commande.substring(2,commande.length());
			    break;
			case 7:
			    //Exemple : C6-4-C3
			    source = commande.substring(0,2);
			    depart = commande.substring(3,4);
			    destination = commande.substring(5,commande.length());
			    break;
		}
	
	
		
	
	
		//determination du tas source
		if (source.equals("R")) {
		    tas_source = R;
		} else {
		    if (source.equals("P")) {
				tas_source = P;
		    } else {
				if (source.equals("P1")) {
				    tas_source = P1;
				} else {
				    if (source.equals("P2")) {
						tas_source = P2;
				    } else {
						if (source.equals("P3")) {
						    tas_source = P3;
						} else {
						    if (source.equals("P4")) {
								tas_source = P4;
						    } else {
								if (source.equals("C1")) {
								    tas_source = C1;
								} else {
								    if (source.equals("C2")) {
										tas_source = C2;
									    } else {
										if (source.equals("C3")) {
										    tas_source = C3;
										} else {
										    if (source.equals("C4")) {
												tas_source = C4;
											    } else {
												if (source.equals("C5")) {
												    tas_source = C5;
												} else {
												    if (source.equals("C6")) {
															tas_source = C6;
												    } else {
														if (source.equals("C7")) {
													    	tas_source = C7;
														}
												    }
												}
										    }
										}
								    }
								}
						    }
						}
				    }
				}
		    }
		}
	
		//determination du tas de destination
		if (destination.equals("P")) {
		    tas_destination = P;
		} else {
		    if (destination.equals("P1")) {
				tas_destination = P1;
		    } else {
				if (destination.equals("P2")) {
				    tas_destination = P2;
				} else {
				    if (destination.equals("P3")) {
						tas_destination = P3;
				    } else {
						if (destination.equals("P4")) {
						    tas_destination = P4;
						} else {
						    if (destination.equals("C1")) {
								tas_destination = C1;
						    } else {
								if (destination.equals("C2")) {
								    tas_destination = C2;
								} else {
								    if (destination.equals("C3")) {
										tas_destination = C3;
								    } else {
										if (destination.equals("C4")) {
										    tas_destination = C4;
										} else {
										    if (destination.equals("C5")) {
												tas_destination = C5;
										    } else {
												if (destination.equals("C6")) {
												    tas_destination = C6;
												} else {
												    if (destination.equals("C7")) {
														tas_destination = C7;
												    }
												}
										    }
										}
								    }
								}
						    }
						}
				    }
				}
		    }
		}
	
		//determination du numero de carte de depart du tas a deplacer
		try {
		    tas_depart = Integer.parseInt(depart);
		} catch(Exception e) {}
	
		//routine pour retourner une carte
		if ((commande.length() == 2) && ((tas_source > C1) && (tas_source <= C7))) {
		    switch(tas_source) {
		    case C1:
				colonne1.getCarte(colonne1.getNb()).montrer();
				break;
		    case C2:
				colonne2.getCarte(colonne2.getNb()).montrer();
				break;
		    case C3:
				colonne3.getCarte(colonne3.getNb()).montrer();
				break;
		    case C4:
				colonne4.getCarte(colonne4.getNb()).montrer();
				break;
		    case C5:
				colonne5.getCarte(colonne5.getNb()).montrer();
				break;
		    case C6:
				colonne6.getCarte(colonne6.getNb()).montrer();
				break;
		    case C7:
				colonne7.getCarte(colonne7.getNb()).montrer();
				break;
		    }
		}
		
		//on stocke une carte dans la variable carte tmp
		if ((commande.length() == 4) && ((tas_source >= C1) && (tas_source <= C7))) {
		    switch(tas_source) {
			    case C1:
					tmp = colonne1.getCarte(colonne1.getNb());
					break;
			    case C2:
					tmp = colonne2.getCarte(colonne2.getNb());
					break;
			    case C3:
					tmp = colonne3.getCarte(colonne3.getNb());
					break;
			    case C4:
					tmp = colonne4.getCarte(colonne4.getNb());
					break;
			    case C5:
					tmp = colonne5.getCarte(colonne5.getNb());
					break;
			    case C6:
					tmp = colonne6.getCarte(colonne6.getNb());
					break;
			    case C7:
					tmp = colonne7.getCarte(colonne7.getNb());
					break;
		    }
		}
		
		
	
		//on stocke dans le tableau de carte tab_tmp le tas de carte a partir de l'indice tas_depart
		if ((commande.length() == 7) && ((tas_source >= C1) && (tas_source <= C7))) {
		    switch(tas_source) {
			    case C1:
					compteur = 1;
					for(indice = tas_depart; indice <= colonne1.getNb(); indice++) {
					    tab_tmp[indice-indice+compteur] = colonne1.getCarte(indice);
					    compteur++;
					}
					cpt_tab_tmp = colonne1.getNb() - tas_depart + 1;
					break;
			    case C2:
					compteur = 1;
					for(indice = tas_depart; indice <= colonne2.getNb(); indice++) {
					    tab_tmp[indice-indice+compteur] = colonne2.getCarte(indice);
					    compteur++;
					}
					cpt_tab_tmp = colonne2.getNb() - tas_depart + 1;
					break;
			    case C3:
					compteur = 1;
					for(indice = tas_depart;indice <= colonne3.getNb(); indice++) {
					    tab_tmp[indice-indice+compteur] = colonne3.getCarte(indice);
					    compteur++;
					}
					cpt_tab_tmp = colonne3.getNb() - tas_depart + 1;
					break;
			    case C4:
					compteur = 1;
					for(indice = tas_depart; indice <= colonne4.getNb(); indice++) {
					    tab_tmp[indice-indice+compteur] = colonne4.getCarte(indice);
					    compteur++;
					}
			  		cpt_tab_tmp = colonne4.getNb() - tas_depart + 1;
					break;
			    case C5:
					compteur = 1;
					for(indice = tas_depart; indice <= colonne5.getNb(); indice++) {
					    tab_tmp[indice-indice+compteur] = colonne5.getCarte(indice);
					    compteur++;
					}
					cpt_tab_tmp = colonne5.getNb() - tas_depart + 1;
					break;
			    case C6:
					compteur = 1;
					for(indice = tas_depart; indice <= colonne6.getNb(); indice++) {
					    tab_tmp[indice-indice+compteur] = colonne6.getCarte(indice);
					    compteur++;
					}
					cpt_tab_tmp = colonne6.getNb() - tas_depart + 1;
					break;
			    case C7:
					compteur = 1;
					for(indice = tas_depart; indice <= colonne7.getNb(); indice++) {
					    tab_tmp[indice-indice+compteur] = colonne7.getCarte(indice);
					    compteur++;
					}
					cpt_tab_tmp = colonne7.getNb() - tas_depart + 1;
					break;
		    }
		}
	
		if (commande.length() != 7) {
		    //on teste la carte source sur le tas de destination (si on peut on l'ajoute au tas)
		    switch(tas_destination) {
			    case P:
					if ((pioche2.add(tmp) == true) && (tmp != null)) {
					    reponse = true;
					    pioche2.ajouter(tmp);
					} else {
					    //routine pour la remise de pioche automatique
					    for(int nb = pioche2.getNb();nb>0;nb--) {
						pioche1.ajouter(pioche2.retirer(nb));
					    }
					}
					break;
			    case P1:
					if (pile1.add(tmp) == true) {
					    reponse = true;
					    pile1.ajouter(tmp);
					}
					break;
			    case P2:
					if (pile2.add(tmp) == true) {
					    reponse = true;
					    pile2.ajouter(tmp);
					}
					break;
			    case P3:
					if (pile3.add(tmp) == true) {
					    reponse = true;
					    pile3.ajouter(tmp);
					}
					break;
			    case P4:
					if (pile4.add(tmp) == true) {
					    reponse = true;
					    pile4.ajouter(tmp);
					}
					break;
			    case C1:
					if (colonne1.add(tmp) == true) {
					    reponse = true;
					    colonne1.ajouter(tmp);
					}
					break;
			    case C2:
					if (colonne2.add(tmp) == true) {
					    reponse = true;
					    colonne2.ajouter(tmp);
					}
					break;
			    case C3:
					if (colonne3.add(tmp) == true) {
					    reponse = true;
					    colonne3.ajouter(tmp);
					}
					break;
			    case C4:
					if (colonne4.add(tmp) == true) {
					    reponse = true;
					    colonne4.ajouter(tmp);
					}
					break;
			    case C5:
					if (colonne5.add(tmp) == true) {
					    reponse = true;
					    colonne5.ajouter(tmp);
					}
					break;
			    case C6:
					if (colonne6.add(tmp) == true) {
					    reponse = true;
					    colonne6.ajouter(tmp);
					}
					break;
			    case C7:
					if (colonne7.add(tmp) == true) {
					    reponse = true;
					    colonne7.ajouter(tmp);
					}
					break;
		    }
		}
	
		//on stocke les cartes du vecteur de cartes tab_tmp vers la colonne de destination si ca correspond
		if ((commande.length() == 7) && ((tas_destination >= C1) && (tas_destination <= C7))) {	
		    switch(tas_destination) {
			    case C1:
					if (colonne1.add(tab_tmp[1]) == true) {
					    reponse = true;
					    for(indice = 1; indice <= cpt_tab_tmp; indice++) {
							colonne1.ajouter(tab_tmp[indice]);
					    }
					}
					break;
			    case C2:
					if (colonne2.add(tab_tmp[1]) == true) {
					    reponse = true;
					    for(indice = 1; indice <= cpt_tab_tmp; indice++) {
							colonne2.ajouter(tab_tmp[indice]);
					    }
					}
					break;
			    case C3:
					if (colonne3.add(tab_tmp[1]) == true) {
					    reponse = true;
					    for(indice = 1; indice <= cpt_tab_tmp; indice++) {
							colonne3.ajouter(tab_tmp[indice]);
					    }
					}
					break;
			    case C4:
					if (colonne4.add(tab_tmp[1]) == true) {
					    reponse = true;
					    for(indice = 1; indice <= cpt_tab_tmp; indice++) {
							colonne4.ajouter(tab_tmp[indice]);
					    }
					}
					break;
			    case C5:
					if (colonne5.add(tab_tmp[1]) == true) {
					    reponse = true;
					    for(indice = 1; indice <= cpt_tab_tmp; indice++) {
							colonne5.ajouter(tab_tmp[indice]);
					    }
					}
					break;
			    case C6:
					if (colonne6.add(tab_tmp[1]) == true) {
					    reponse = true;
					    for(indice = 1;indice <= cpt_tab_tmp; indice++) {
							colonne6.ajouter(tab_tmp[indice]);
					    }
					}
					break;
			    case C7:
					if (colonne7.add(tab_tmp[1]) == true) {
					    reponse = true;
					    for(indice = 1; indice <= cpt_tab_tmp; indice++) {
							colonne7.ajouter(tab_tmp[indice]);
					    }
					}
					break;
		    }
		}
	
		if (commande.length() != 7)  {
		    //si on peut l'ajouter alors on la retire du tas source
		    if (reponse == true) {
				//on teste le tas source pour supprimer la carte
				switch(tas_source) {
					case R:
					    pioche1.retirer(pioche1.getNb());
					    break;
					case P:
					    pioche2.retirer(pioche2.getNb());
					    break;
					case P1:
					    pile1.retirer(pile1.getNb());
					    break;
					case P2:
					    pile2.retirer(pile2.getNb());
					    break;
					case P3:
					    pile3.retirer(pile3.getNb());
					    break;
					case P4:
					    pile4.retirer(pile4.getNb());
					    break;
					case C1:
					    colonne1.retirer(colonne1.getNb());
					    break;
					case C2:
					    colonne2.retirer(colonne2.getNb());
					    break;
					case C3:
					    colonne3.retirer(colonne3.getNb());
					    break;
					case C4:
					    colonne4.retirer(colonne4.getNb());
					    break;
					case C5:
					    colonne5.retirer(colonne5.getNb());
					    break;
					case C6:
					    colonne6.retirer(colonne6.getNb());
					    break;
					case C7:
					    colonne7.retirer(colonne7.getNb());
					    break; 
				}
		    }
		}
	
		if (commande.length() == 7) {
		    //si on peut l'ajouter alors on la retire du tas source
		    if (reponse == true) {
			//on teste le tas source pour supprimer la carte
				switch(tas_source) {
					case C1:
					    indice1 = colonne1.getNb();
					    while(indice1 != (tas_depart - 1)) {
							colonne1.retirer(indice1);
							indice1--;
					    }
					    break;
					case C2:
					    indice1 = colonne2.getNb();
					    while(indice1 != (tas_depart - 1)) {
							colonne2.retirer(indice1);
							indice1--;
					    }
					    break;
					case C3:
					    indice1 = colonne3.getNb();
					    while(indice1 != (tas_depart - 1)) {
							colonne3.retirer(indice1);
							indice1--;
					    }
					    break;
					case C4:
					    indice1 = colonne4.getNb();
					    while(indice1 != (tas_depart - 1)) {
							colonne4.retirer(indice1);
							indice1--;
					    }
					    break;
					case C5:
					    indice1 = colonne5.getNb();
					    while(indice1 != (tas_depart - 1)) {
							colonne5.retirer(indice1);
							indice1--;
					    }
					    break;
					case C6:
					    indice1 = colonne6.getNb();
					    while(indice1 != (tas_depart - 1)) {
							colonne6.retirer(indice1);
							indice1--;
					    }
					    break;
					case C7:
					    indice1 = colonne7.getNb();
					    while(indice1 != (tas_depart - 1)) {
							colonne7.retirer(indice1);
							indice1--;
					    }
					    break;
				}
		    }
		}
		return commande;
    }
}