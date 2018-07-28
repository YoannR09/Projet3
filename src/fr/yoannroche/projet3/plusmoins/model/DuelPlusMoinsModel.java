package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.plusmoins.view.VictoirePlusMoins;

public class DuelPlusMoinsModel {

	private static Generateur code = new Generateur();
	private static String nombreString = Integer.toString(code.getNombre());
	static Font arial2 = new Font ("arial", 10,10);
	static Font impact = new Font ("impact", 17,17);
	static boolean codeBon = false;

	public static void tentativeJoueur(JLabel tentative, JLabel infosTentative, JTextField proposition) {
		tentative.setText(proposition.getText());
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

	public static void okClick(JTextField proposition, JLabel codeSecret, JLabel tentative, JLabel infosTentative,JPanel contentPane) {
		if(proposition.getText().length()<nombreString.length()) {
			JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et dépasse la longueur du code secret
			joperreur.showMessageDialog(null,"Votre code est trop court !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
		}
		else if(proposition.getText().length()>nombreString.length()) {
			JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et la longueur du code tapé et inférieur au code secret
			joperreur.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
		}
		else if(codeSecret.getText().length()==nombreString.length()) {
			tentativeJoueur(tentative, infosTentative,proposition);
			proposition.setText("");
		}
		
		else {

			codeSecret.setText(proposition.getText());
			proposition.setText("");
			proposition.setFont(impact);
		}
		if(tentative.getText().equals(nombreString)) {
			VictoirePlusMoins jop2 = new VictoirePlusMoins(null, "Gagner",nombreString,contentPane);
		}
	}
	public static void dev(JLabel codeSecret){
		codeSecret.setText(nombreString);
	}


}
