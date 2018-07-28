package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Font;
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
	static Font arial2 = new Font ("arial", 10,10);
	static Font impact = new Font ("impact", 17,17);


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
	public static void check(JLabel tentative, JLabel infosTentative) {
		infosTentative.setText("");
		String test=tentative.getText();


		/**
		 * Boucle qui test le code écrit pour le comparer au code caché
		 * Affiche + - = dans le JLabel infosTentative
		 * En fonction des conditions
		 */
		  try {
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
		  }catch(Exception e) {
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


	public static void RangeWord(JPanel blocProposition, int nombreClick) {
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		bloc = reglage.getString("cases");
		int cases = Integer.parseInt(bloc); 

		if(nombreClick==cases) {
			blocProposition.setVisible(false);
		}


	}
	public static void okClick(JTextField proposition, JLabel tentative, JLabel infosTentative,JPanel contentPane, JPanel blocProposition,int nbreTentative) {
		if(proposition.getText().length()<nombreString.length()) {
			JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et dépasse la longueur du code secret
			joperreur.showMessageDialog(null,"Votre code est trop court !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
			blocProposition.setVisible(true);
			
		}
		else if(proposition.getText().length()>nombreString.length()) {
			JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et la longueur du code tapé et inférieur au code secret
			joperreur.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
			blocProposition.setVisible(true);
			
		}
		
		else {
			tentative.setText(proposition.getText());
            check(tentative, infosTentative);
			tentative.setText(proposition.getText());
			proposition.setText("");
			proposition.setFont(impact);
			blocProposition.setVisible(true);
		}
		if(tentative.getText().equals(nombreString)) {
			VictoirePlusMoins jop2 = new VictoirePlusMoins(null, "Gagner",nombreString,contentPane);
		}
	}
}
