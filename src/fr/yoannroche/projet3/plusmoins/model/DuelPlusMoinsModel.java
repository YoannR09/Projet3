package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
	static int nombreErreur =0;
	static int gagner =0;
	static int info;
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

	public static void okClick(JTextField proposition, JLabel codeSecret, JLabel tentative, JLabel infosTentative,JPanel contentPane,JTextArea dialog, JLabel indiceDev, JPanel cadreJ, JPanel cadreOrdi, JButton ok, JButton fin) {
		if(proposition.getText().length()<nombreString.length()) {
			JOptionPane.showMessageDialog(null,"Votre code est trop court !!","Erreur",JOptionPane.ERROR_MESSAGE);
		
		}
		else if(proposition.getText().length()>nombreString.length()) {
			JOptionPane.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);
			
		}
		else if(codeSecret.getText().length()==nombreString.length()) {
			tentativeJoueur(tentative, infosTentative,proposition);
			cadreJ.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			cadreOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
			tentativeOrdi(indiceDev, codeSecret,dialog, contentPane,nbreTour);
			++nbreTour;
			proposition.setText("");
			fin.setEnabled(true);
			fin.setBackground(Color.getHSBColor(0.154f, 0.85f, 0.94f));
			

		}
		else {
			codeSecret.setText(proposition.getText());
			proposition.setText("");
			proposition.setFont(impact);
			ok.setEnabled(true);
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		}
		if(tentative.getText().equals(nombreString)) {
			new VictoirePlusMoins(null, "Gagner",nombreString,contentPane);
		}
	}

	public static void dev(JLabel codeSecret){
		codeSecret.setText(nombreString);
	}

	public static void tentativeOrdi(JLabel indiceDev, JLabel codeSecret2, JTextArea dialog,JPanel contentPane, int nbreTour2) {

		String codeCache = codeSecret2.getText();
		char tabCode[] = codeCache.toCharArray();

		if(nbreTour2==0) {
			tour0(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev);
		}
		else if(nbreTour2==1) {
			tour1(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev);
		}

		else if(nbreTour2==2){
			tour2(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev);
		}
		else if(nbreTour2>=3){
			tour3etPlus(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev);
		}
	}

	private static char intToChar(int i) {
		String s = ""+i;
		return s.charAt(0);
	}
	public static void checkWord(String proposition,String codeSecret2) {

		if(proposition.equals(codeSecret2)){
			++gagner;
			new PerduPlusMoinsDef(null, codeSecret2, cases, null);
		}
	}
	public static void tour0(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev) {
		for(int o=0;o<cases;o++) { //Création de tableaux max, min et proposition.

			tabMin[o]=intToChar(0);
			tabProp[o]=intToChar(5);
			tabMax[o]=intToChar(9);
			if(tabProp[o]>tabCode[o]) {    
				indiceDev.setText(indiceDev.getText()+"-");
			}
			else if(tabProp[o]<tabCode[o]) { 
				indiceDev.setText(indiceDev.getText()+"+");
			}
			else {
				indiceDev.setText(indiceDev.getText()+"=");
			}

		}
		dialog.setText("");
		resultProp = new String(tabProp);
		dialog.setFont(arial2);
		dialog.setText(dialog.getText()+" Je me lance : "+resultProp+" ?!\n");
		checkWord(resultProp,codeCache);
	}


	public static void tour1(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev) {

		for(int i=0;i<codeCache.length();i++) {


			if(tabProp[i]>tabCode[i]) {
				tabMax[i] = tabProp[i] ;
				info = Character.getNumericValue(tabProp[i]); 
				tabProp[i] = intToChar(info/2);

				if(tabProp[i]>tabCode[i]) {
					tabMax[i] =  tabProp[i];
					indiceDev.setText(indiceDev.getText()+"-");
				}
				else if(tabProp[i]<tabCode[i]) {
					tabMin[i] = tabProp[i];   
					indiceDev.setText(indiceDev.getText()+"+");
				}
				else if(tabProp[i]==tabCode[i]) {
					indiceDev.setText(indiceDev.getText()+"=");
				}

			}
			else if(tabProp[i]<tabCode[i]) {
				tabMin[i] =  tabProp[i];
				info = Character.getNumericValue(tabProp[i]);
				tabProp[i] = intToChar(info+(info/2));

				if(tabProp[i]>tabCode[i]) {
					tabMax[i] = tabProp[i];
					indiceDev.setText(indiceDev.getText()+"-");
				}
				else if(tabProp[i]<tabCode[i]) {
					tabMin[i] = tabProp[i];    
					indiceDev.setText(indiceDev.getText()+"+");
				}
				else if(tabProp[i]==tabCode[i]) {
					indiceDev.setText(indiceDev.getText()+"=");
				}
			}

			resultProp = new String(tabProp);

		}

		dialog.setText(dialog.getText()+" C'est peut être : "+resultProp+"\n");
		checkWord(resultProp,codeCache);
	}

	public static void tour2(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev) {
		for(int i=0; i<codeCache.length();i++) {
			if(tabProp[i]!=tabCode[i]) {
				resultMax = Character.getNumericValue(tabMin[i]);
				resultMin = Character.getNumericValue(tabMax[i]);
				tabProp[i]= intToChar((int)(Math.random()*(resultMax - resultMin))+resultMin); 
				
				if(tabProp[i]>tabCode[i]) {    
					indiceDev.setText(indiceDev.getText()+"-");
				}
				else if(tabProp[i]<tabCode[i]) { 
					indiceDev.setText(indiceDev.getText()+"+");
				}
				else if(tabProp[i]==tabCode[i]){
					indiceDev.setText(indiceDev.getText()+"=");
				}
			}
			else if(tabProp[i]==tabCode[i]){
				indiceDev.setText(indiceDev.getText()+"=");
			}
			resultProp = new String(tabProp);
		}

		dialog.setText(dialog.getText()+" Ou alors peut être : "+resultProp+"\n");
		checkWord(resultProp,codeCache);
	}

	public static void tour3etPlus(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev) {

		for(int i=0; i<codeCache.length();i++) {
			if(tabProp[i]>tabCode[i]) {
				--tabProp[i];
				if(tabProp[i]>tabCode[i]) {    
					indiceDev.setText(indiceDev.getText()+"-");
				}
				else {
					indiceDev.setText(indiceDev.getText()+"=");
				}
			}
			else if(tabProp[i]<tabCode[i]){
				++tabProp[i];
				if(tabProp[i]<tabCode[i]) { 
					indiceDev.setText(indiceDev.getText()+"+");
				}
				else {
					indiceDev.setText(indiceDev.getText()+"=");
				}
			}
			else if(tabProp[i]==tabCode[i]){
				indiceDev.setText(indiceDev.getText()+"=");
			}
			

		

		}
		resultProp = new String(tabProp);

		dialog.setText(dialog.getText()+" Hum : "+resultProp+" ? \n");	
		checkWord(resultProp,codeCache);
	}

	public static void finClick(JLabel tentativeIA, JButton ok, JLabel indiceDev, JButton fin, JPanel cadreJ, JPanel cadreOrdi) {
		if(!tentativeIA.getText().equals(indiceDev.getText())) {
			++nombreErreur;
			fin.setEnabled(true);
			fin.setBackground(Color.getHSBColor(0.154f, 0.85f, 0.94f));
			JOptionPane.showMessageDialog(null,"Les indices entrés ne sont pas bons !!","Erreur",JOptionPane.ERROR_MESSAGE);
		}
		else if(tentativeIA.getText().equals(indiceDev.getText())) {
			ok.setEnabled(true);
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
			tentativeIA.setText("");
			fin.setEnabled(false);
			cadreJ.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
			cadreOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			indiceDev.setText("");
		}

	}
}	


