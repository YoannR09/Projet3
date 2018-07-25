package fr.yoannroche.projet3.plusmoins.model;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.plusmoins.view.ChallengerPlusMoins;
import fr.yoannroche.projet3.plusmoins.view.PerduPlusMoins;
import fr.yoannroche.projet3.plusmoins.view.VictoirePlusMoins;


public class ChallengerPlusMoinsModel {

	private static Generateur code = new Generateur();
	private static String nombreString = Integer.toString(code.getNombre());
	private static String tentative;
	private static String bloc;
	static JOptionPane jop1;
	static JOptionPane jop2;


	public ChallengerPlusMoinsModel() {
	
	}
	/**
	 * Méthode qui s'execute si le mode développeur est actif.
	 * Dans ce cas le code secret s'affiche.
	 * @param codeSecret
	 */
	public static void dev(JLabel codeSecret){
		codeSecret.setText(nombreString);
	}



	/**
	 * Méthode qui test la proposition pour le comparer au code secret.
	 * @param tentative
	 * @param infosTentative
	 * @param proposition 
	 */
	public static void check(JLabel tentative, JLabel infosTentative) {  // Probleme au deuxieme ajouts , un espace ce place devant le chiffre generé.
		infosTentative.setText("");
		String test=tentative.getText();


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




	/**
	 * Méthode qui permet de vérifier si le joueur n'a pas dépassé le nombre de tentative.
	 * @param nbreTentative
	 */
	public static void chechTentative(int nbreTentative,JPanel contentPane) {

		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		tentative = reglage.getString("tentatives");
		int tentatives = Integer.parseInt(tentative);

		if(nbreTentative==tentatives) {
			PerduPlusMoins jop2 = new PerduPlusMoins(null, "Perdu",nombreString,contentPane);
		}

	}



	public static void checkCode(JTextField proposition,JPanel contentPane) {
		String propositionString = proposition.getText();

		if(propositionString.equals(nombreString)) {

			VictoirePlusMoins jop2 = new VictoirePlusMoins(null, "Gagner",nombreString,contentPane);

		}

	}
	public static void RangeWord(JPanel blocProposition, int nombreClick) {
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		bloc = reglage.getString("cases");
		int cases = Integer.parseInt(bloc); 

		if(nombreClick==cases) {
			blocProposition.setVisible(false);
		}


	}
}
