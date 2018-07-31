package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.yoannroche.projet3.plusmoins.view.gagner.VictoirePlusMoins;
import fr.yoannroche.projet3.plusmoins.view.gagner.VictoirePlusMoinsDef;
import fr.yoannroche.projet3.plusmoins.view.perdu.PerduPlusMoins;
import fr.yoannroche.projet3.plusmoins.view.perdu.PerduPlusMoinsDef;

public class DefenseurPlusMoinsModel {

	private static String bloc;
	private static String maximum ;
	private static String minimum ;
	private static String proposition ;
	private static int nombreCoup =0;

	private static String tentative;
	private static String blocs;
	static int resultMax;
	static int resultMin;

	static Font impact = new Font ("impact", 17,17);
	Font arial = new Font ("arial", 12,12);
	static Font arial2 = new Font ("arial", 10,10);
	static ImageIcon ordi1 = new ImageIcon("images/ordi1.png");
	static ImageIcon ordi8 = new ImageIcon("images/ordi8.png");
	static int info;
	static String resultProp;
	static JLabel image;
	static int gagner =0;

	public DefenseurPlusMoinsModel() {}

	/**
	 * Méthode qui lance les tantatives de l'ordinateur.
	 * + Le changement de text dans le dialog de l'ordi.
	 * + Affiche la défaite ou la victoire.
	 * @param tentativeOrdi
	 * @param codeSecret2
	 * @param nombreCoup
	 * @param dialog
	 * @param contentPane
	 */
	public static void tentativeOrdi(JLabel tentativeOrdi, JLabel codeSecret2, int nombreCoup, JTextArea dialog,JPanel contentPane,JLabel image) {


		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		bloc = reglage.getString("cases");
		int cases = Integer.parseInt(bloc);	
		String codeSecret = codeSecret2.getText();
		char tabCode[] = codeSecret.toCharArray();
		char tabProp[] = new char [cases];
		char tabMin [] = new char [cases];
		char tabMax[] = new char [cases];


		for(int o=0;o<cases;o++) { //Création de tableaux max, min et proposition.

			tabMin[o]=intToChar(0);
			tabProp[o]=intToChar(5);
			tabMax[o]=intToChar(9);
		}
		resultProp = new String(tabProp);
		dialog.setFont(arial2);
		dialog.setText(dialog.getText()+"Je me lance : "+resultProp+" ?!\n");
		++nombreCoup;
		checkWord(resultProp,codeSecret,nombreCoup);

		for(int i=0;i<codeSecret.length();i++) {


			if(tabProp[i]>tabCode[i]) {
				tabMax[i] = tabProp[i] ;
				info = Character.getNumericValue(tabProp[i]); 
				tabProp[i] = intToChar(info/2);

				if( tabProp[i]>tabCode[i]) {
					tabMax[i] =  tabProp[i];
				}
				//				else if( tabProp[i]<tabCode[i]) {
				//				tabMin[i] = tabProp[i];       L'ordinateur trouvera en moins de tentatives.
				//
				//			}
			}
			else if(tabProp[i]<tabCode[i]) {
				tabMin[i] =  tabProp[i];
				info = Character.getNumericValue(tabProp[i]);
				tabProp[i] = intToChar(info+(info/2));

				if( tabProp[i]>tabCode[i]) {
					tabMax[i] = tabProp[i];

				}
				//				else if( tabProp[i]<tabCode[i]) {
				//					tabMin[i] = tabProp[i];       L'ordinateur trouvera en moins de tentatives.
				//
				//				}
			}

			resultProp = new String(tabProp);

		}
		dialog.setText(dialog.getText()+"C'est peut être : "+resultProp+"\n");
		++nombreCoup; // Fin de la première boucle.
		checkWord(resultProp,codeSecret,nombreCoup);
		chechTentative(nombreCoup,contentPane, codeSecret, image);

		for(int i=0; i<codeSecret.length();i++) {
			if(tabProp[i]>tabCode[i]) {
				resultMax = Character.getNumericValue(tabMin[i]);
				resultMin = Character.getNumericValue(tabMax[i]);
				tabProp[i]= intToChar((int)(Math.random()*(resultMax - resultMin))+resultMin); 
				

			}
			else if(tabProp[i]<tabCode[i]){
				resultMax = Character.getNumericValue(tabMin[i]);
				resultMin = Character.getNumericValue(tabMax[i]);
				tabProp[i]= intToChar((int)(Math.random()*(resultMax - resultMin))+resultMin); 
			

			}
			resultProp = new String(tabProp);


		}
		dialog.setText(dialog.getText()+"Ou alors peut être : "+resultProp+"\n");
		++nombreCoup;// Fin de la deuxième boucle.
		checkWord(resultProp,codeSecret,nombreCoup);
		chechTentative(nombreCoup,contentPane, codeSecret,image);

		do{
			for(int i=0; i<codeSecret.length();i++) {
				if(tabProp[i]>tabCode[i]) {
					--tabProp[i];

				}
				else if(tabProp[i]<tabCode[i]){
					++tabProp[i];

				}
				resultProp = new String(tabProp);


			}
			resultProp = new String(tabProp);

			dialog.setText(dialog.getText()+" Hum : "+resultProp+" ? \n");	
			++nombreCoup;// +1 nombre tentative tant que le code n'est pas trouvé.
			checkWord(resultProp,codeSecret,nombreCoup);
			chechTentative(nombreCoup,contentPane, codeSecret,image);

		}while(!resultProp.equals(codeSecret));// Fin de la troisième boucle
	}

	/**
	 * Compare le texte écrit par le joueur si il est de bonne taille.
	 * Si le texte entré est bon, on lance alors la méthode tentativeOrdi.
	 * @param proposition
	 * @param codeSecret
	 * @param contentPane
	 * @param boiteDialog
	 * @param ordi
	 * @param blocProposition
	 * @param image
	 * @param tentativeOrdi
	 * @param dialog
	 */
	public static void okClick(JTextField proposition, JLabel codeSecret, JPanel contentPane, JPanel boiteDialog, JPanel ordi,JPanel blocProposition, JLabel image, JLabel tentativeOrdi, JTextArea dialog) {

		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		bloc = reglage.getString("cases");
		int cases = Integer.parseInt(bloc);

		if(proposition.getText().length()<cases) {
			JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et dépasse la longueur du code secret
			joperreur.showMessageDialog(null,"Votre code est trop court !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
			blocProposition.setVisible(true);
		}
		else if(proposition.getText().length()>cases) {
			JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et la longueur du code tapé et inférieur au code secret
			joperreur.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
			blocProposition.setVisible(true);
		}
		else if(proposition.getText().length()==cases) {
			boiteDialog.setVisible(false);
			codeSecret.setText(proposition.getText());
			proposition.setText("");
			proposition.setFont(impact);
			ordi.setVisible(true);	
			image.setIcon(ordi1);
			DefenseurPlusMoinsModel.tentativeOrdi(tentativeOrdi,codeSecret,nombreCoup,dialog, contentPane, image);
		}

	}
	/**
	 * Regarde le nombre de tentatives.
	 * Une fois dépassé c'est gagné pour le joueur.
	 * @param nombreCoup
	 * @param contentPane
	 * @param codeSecret
	 */
	public static void chechTentative(int nombreCoup,JPanel contentPane,String codeSecret, JLabel image) {

		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		tentative = reglage.getString("tentatives");
		int tentatives = Integer.parseInt(tentative);

		if(nombreCoup==tentatives) {
			image.setIcon(ordi8);
			VictoirePlusMoinsDef jop2 = new VictoirePlusMoinsDef(null,"Gagner", codeSecret, contentPane,nombreCoup);
		}

	}
	/**
	 * Compare la proposition de l'ordi au code secret.
	 * @param codeSecret
	 * @param codeSecret2
	 */
	public static void checkWord(String proposition,String codeSecret2,int nombreCoup) {
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		tentative = reglage.getString("tentatives");
		int tentatives = Integer.parseInt(tentative);
		if(gagner==0) {
			if(nombreCoup<=tentatives) {
				if(proposition.equals(codeSecret2)){
					++gagner;
					PerduPlusMoinsDef vic = new PerduPlusMoinsDef(null, "Gagner", nombreCoup, null);
				}

			}
		}
	}

	/**
	 * Méthode qui permet le passage d'un int en char
	 * @param i
	 * @return
	 */
	private static char intToChar(int i) {
		String s = ""+i;
		return s.charAt(0);
	}
}
