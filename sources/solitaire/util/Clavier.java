package solitaire.util;

import java.io.*;

public class Clavier {

	// lire un String
	public static String readString() {
		String tmp = "";
		char C = '\0';
		try {
	        while ((C = (char) System.in.read()) !='\n') {
	        	if (C != '\r') {
	         		tmp = tmp + C;
	        	}
	       	}
	   	} catch (IOException e) {
	    	System.out.println("Erreur de frappe");
	        System.exit(0);
	    }
	 	return tmp;
	}

	// Lire un entier byte
 	public static byte readByte() {
  		byte x = 0;
 	 	try {
			x = Byte.parseByte(readString());
	  	} catch (NumberFormatException e) {
        	System.out.println("Format numérique incorrect");
          	System.exit(0);
    	}	
	  	return x;
 	}

	// Lire un entier short
 	public static short readShort() {
   	 	short x = 0;
 	 	try {
			x = Short.parseShort(readString());
	  	} catch (NumberFormatException e) {
          	System.out.println("Format numérique incorrect");
          	System.exit(0);
    	}	
	  	return x;
 	}

	// Lire un entier
 	public static int readInt() {
 		int x = 0;
 	 	try {
			 x = Integer.parseInt(readString());
  	 	} catch (NumberFormatException e) {
        	System.out.println("Format numérique incorrect");
        	System.exit(0);
    	}	
	  	return x ;
 	}

	// Lire un entier long
 	public static long readLong() {
 		long x = 0;
 	 	try {
			 x = Integer.parseInt(readString());
  	 	} catch (NumberFormatException e) {
        	System.out.println("Format numérique incorrect");
        	System.exit(0);
    	}	
	  	return x ;
 	}

	// Lire un double
 	public  static double readDouble() {
  		double x = 0.0;
 	 	try {
	 		x = Double.valueOf(readString()).doubleValue();
 	 	} catch (NumberFormatException e) {
          	System.out.println("Format numérique incorrect");
          	System.exit(0);
    	}	
	  	return x ;
 	}

	// Lire un float
	public  static float readFloat() {
		float x = 0.0f;
		try {
			x = Double.valueOf(readString()).floatValue();
		} catch (NumberFormatException e) {
	  		System.out.println("Format numérique incorrect");
	  		System.exit(0);
		}	
		return x ;
	}

	// Lire un caractere
	public static char readChar() {
  		String tmp = readString();
  		if (tmp.length()==0) {
  			return '\n';
  		} else {
			return tmp.charAt(0);
		}
 	}
}