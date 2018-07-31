package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.plusmoins.view.gagner.VictoirePlusMoins;
import fr.yoannroche.projet3.plusmoins.view.perdu.PerduPlusMoinsDef;

public class DuelPlusMoinsModel {

	private static Generateur code = new Generateur();
	private static String nombreString = Integer.toString(code.getNombre());
	static Font arial2 = new Font ("arial", 10,10);
	static Font impact = new Font ("impact", 17,17);
	static boolean codeBon = false;
	static String resultProp;
	static int resultMax;
	static int resultMin;
	static int nbreTour =0;
	static int gagner =0;
	static int info;
	private static String bloc;
	static int cases = 4;
	static char tabProp[] = new char [cases];
	static char tabMin [] = new char [cases];
	static char tabMax[] = new char [cases];

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

	public static void okClick(JTextField proposition, JLabel codeSecret, JLabel tentative, JLabel infosTentative,JPanel contentPane,JLabel tentativeOrdi,JTextArea dialog, JLabel tentativeIA) {
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
			tentativeOrdi(tentativeOrdi, codeSecret,dialog, contentPane,nbreTour,tentativeIA);
			++nbreTour;
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

	public static void tentativeOrdi(JLabel tentativeOrdi, JLabel codeSecret2, JTextArea dialog,JPanel contentPane, int nbreTour2, JLabel tentativeIA) {



		String codeCache = codeSecret2.getText();
		char tabCode[] = codeCache.toCharArray();



		if(nbreTour2==0) {
			tour0(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,tentativeIA);
		}
		else if(nbreTour2==1) {
			tour1(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,tentativeIA);
		}

		else if(nbreTour2==2){
			tour2(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,tentativeIA);
		}
		else if(nbreTour2>=3){
			tour3etPlus(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,tentativeIA);
		}
	}

	private static char intToChar(int i) {
		String s = ""+i;
		return s.charAt(0);
	}
	public static void checkWord(String proposition,String codeSecret2) {

		if(gagner==0) {
			if(proposition.equals(codeSecret2)){
				++gagner;


			}
		}
	}
	public static void tour0(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel tentativeIA) {
		for(int o=0;o<cases;o++) { //Création de tableaux max, min et proposition.

			tabMin[o]=intToChar(0);
			tabProp[o]=intToChar(5);
			tabMax[o]=intToChar(9);
		}
		dialog.setText("");
		resultProp = new String(tabProp);
		dialog.setFont(arial2);
		dialog.setText(dialog.getText()+"Je me lance : "+resultProp+" ?!\n");
		tentativeIA.setText(resultProp);
		checkWord(resultProp,codeCache);
	}


	public static void tour1(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel tentativeIA) {

		for(int i=0;i<codeCache.length();i++) {


			if(tabProp[i]>tabCode[i]) {
				tabMax[i] = tabProp[i] ;
				info = Character.getNumericValue(tabProp[i]); 
				tabProp[i] = intToChar(info/2);

				if(tabProp[i]>tabCode[i]) {
					tabMax[i] =  tabProp[i];
				}
				else if(tabProp[i]<tabCode[i]) {
					tabMin[i] = tabProp[i];   

				}
			}
			else if(tabProp[i]<tabCode[i]) {
				tabMin[i] =  tabProp[i];
				info = Character.getNumericValue(tabProp[i]);
				tabProp[i] = intToChar(info+(info/2));

				if(tabProp[i]>tabCode[i]) {
					tabMax[i] = tabProp[i];

				}
				else if(tabProp[i]<tabCode[i]) {
					tabMin[i] = tabProp[i];    

				}
			}

			resultProp = new String(tabProp);

		}
		tentativeIA.setText(resultProp);
		dialog.setText(dialog.getText()+"C'est peut être : "+resultProp+"\n");
		checkWord(resultProp,codeCache);
	}

	public static void tour2(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel tentativeIA) {
		for(int i=0; i<codeCache.length();i++) {
			if(tabProp[i]!=tabCode[i]) {

				resultMax = Character.getNumericValue(tabMin[i]);
				resultMin = Character.getNumericValue(tabMax[i]);
				tabProp[i]= intToChar((int)(Math.random()*(resultMax - resultMin))+resultMin);   //problème ici !!

			}
			resultProp = new String(tabProp);


		}
		tentativeIA.setText(resultProp);
		dialog.setText(dialog.getText()+"Ou alors peut être : "+resultProp+"\n");
		checkWord(resultProp,codeCache);
	}


	public static void tour3etPlus(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel tentativeIA) {

		for(int i=0; i<codeCache.length();i++) {
			if(tabProp[i]>tabCode[i]) {
				--tabProp[i];

			}
			else if(tabProp[i]<tabCode[i]){
				++tabProp[i];

			}
			resultProp = new String(tabProp);


		}
		resultProp = new String(tabProp);
		tentativeIA.setText(resultProp);
		dialog.setText(dialog.getText()+" Hum : "+resultProp+" ? \n");	

		checkWord(resultProp,codeCache);

	}
}	


