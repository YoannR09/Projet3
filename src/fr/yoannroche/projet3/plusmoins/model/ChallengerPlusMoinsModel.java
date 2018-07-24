package fr.yoannroche.projet3.plusmoins.model;

import javax.swing.JLabel;

import fr.yoannroche.projet3.Generateur;


public class ChallengerPlusMoinsModel {

	private static Generateur code = new Generateur();
	static String nombreString = Integer.toString(code.getNombre());
	
	
	public static void dev(JLabel codeSecret){
		codeSecret.setText(nombreString);
	}




	public static void check(JLabel tentative, JLabel infosTentative) {  // Probleme au deuxieme ajouts , un espace ce place devant le chiffre generé.
		infosTentative.setText("");
		String test=tentative.getText();



		if(test==nombreString){
			System.out.println("gagner");
		}

		/**
		 * Boucle qui test le code écrit pour le comparer au code caché
		 * Affiche + - = dans le JLabel infosTentative
		 * En fonction des conditions
		 */
		for(int i =0;i<test.length();i++){


			if(test.charAt(i)==nombreString.charAt(i)) {
				infosTentative.setText(infosTentative.getText()+"=");
			}
			if(test.charAt(i)<nombreString.charAt(i)) {
				infosTentative.setText(infosTentative.getText()+"+");
			}
			if(test.charAt(i)>nombreString.charAt(i)) {
				infosTentative.setText(infosTentative.getText()+"-");
			}

		}

	}
}
