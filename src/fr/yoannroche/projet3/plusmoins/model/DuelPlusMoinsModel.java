package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.plusmoins.view.gagner.VictoirePlusMoins;
import fr.yoannroche.projet3.plusmoins.view.gagner.VictoirePlusMoinsDuel;
import fr.yoannroche.projet3.plusmoins.view.perdu.PerduPlusMoinsDef;
import fr.yoannroche.projet3.plusmoins.view.perdu.PerduPlusMoinsDuel;

public class DuelPlusMoinsModel {

	private String bloc;
	private Generateur code = new Generateur();
	private String nombreString = Integer.toString(code.getNombre());
	private Font arial2 = new Font ("arial", 10,10);
	private Font impact = new Font ("impact", 17,17);
	private String resultProp;
	private int resultMax;
	private int resultMin;
	private int nbreTour =0;
	private int gagner =0;
	private int info;
	private int cases = 4;
	private char tabProp[] = new char [cases];
	private char tabMin [] = new char [cases];
	private char tabMax[] = new char [cases];

	/**
	 * Compare la tentative du joueur au code secret
	 * Des indices sont donnés pour le joueur.
	 * @param tentative
	 * @param infosTentative
	 * @param proposition
	 */
	public void tentativeJoueur(JLabel tentative, JLabel infosTentative, JTextField proposition) {
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

	/**
	 * Méthode qui s'execute lors du clique du bouton "OK"
	 * Elle permet de définir en premier le code secret et ensuite de définir les propotitions.
	 * Elle lance les méthodes tentativeJoueur et tentativeOrdi.
	 * Permet aussi de vérifier si votre proposition est égal au code secret.
	 * @param proposition
	 * @param codeSecret
	 * @param tentative
	 * @param infosTentative
	 * @param contentPane
	 * @param dialog
	 * @param indiceDev
	 * @param cadreJ
	 * @param cadreOrdi
	 * @param ok
	 * @param fin
	 */
	public void okClick(JTextField proposition, JLabel codeSecret, JLabel tentative, JLabel infosTentative,JPanel contentPane,JTextArea dialog, JLabel indiceDev, JPanel cadreJ, JPanel cadreOrdi, JButton ok, JButton fin) {
		if(proposition.getText().length()<nombreString.length()) {
			JOptionPane.showMessageDialog(null,"Votre code est trop court !!","Erreur",JOptionPane.ERROR_MESSAGE);
			ok.setEnabled(true);
		}
		else if(proposition.getText().length()>nombreString.length()) {
			JOptionPane.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);	
			ok.setEnabled(true);
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
			if(gagner==0) {
			new VictoirePlusMoinsDuel(null, "Gagner",nombreString,contentPane);
			gagner++;
			}
		}
	}

	/**
	 * Si le mode developpeur est actif cela affiche le code secret de l'ordinateur.
	 * @param codeSecret
	 */
	public void dev(JLabel codeSecret){
		codeSecret.setText(nombreString);
	}

	/**
	 * Cette méthode execute chaque tour de l'ordinateur avec chaque partie de l'algorithme.
	 * @param indiceDev
	 * @param codeSecret2
	 * @param dialog
	 * @param contentPane
	 * @param nbreTour2
	 */
	public void tentativeOrdi(JLabel indiceDev, JLabel codeSecret2, JTextArea dialog,JPanel contentPane, int nbreTour2) {

		String codeCache = codeSecret2.getText();
		char tabCode[] = codeCache.toCharArray();

		if(nbreTour2==0) {
			tour0(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev,contentPane);
		}
		else if(nbreTour2==1) {
			tour1(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev,contentPane);
		}

		else if(nbreTour2==2){
			tour2(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev,contentPane);
		}
		else if(nbreTour2>=3){
			tour3etPlus(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev,contentPane);
		}
	}

	/**
	 * Cette méthode transforme les int en char
	 * @param i
	 * @return
	 */
	private char intToChar(int i) {
		String s = ""+i;
		return s.charAt(0);
	}
	/**
	 * Cette méthode vérifie si l'ordinateur à trouvé le bon code.
	 * @param proposition
	 * @param codeSecret2
	 */
	public void checkWord(String proposition,String codeSecret2,JPanel contentPane) {

		if(proposition.equals(codeSecret2)){
			if(gagner==0) {
			++gagner;
			new PerduPlusMoinsDuel(null,"Perdu" ,codeSecret2, contentPane);
			}
		}
	}
	/**
	 * Lance le premier tour de l'ordinateur (La première partie de l'algorithme).
	 * @param dialog
	 * @param tabProp
	 * @param tabCode
	 * @param tabMax
	 * @param tabMin
	 * @param codeCache
	 * @param indiceDev
	 */
	public void tour0(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev,JPanel contentPane) {
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
		checkWord(resultProp,codeCache,contentPane);
	}

    /**
     * Lance le deuxième tour de l'ordinateur (La deuxième partie de l'algorithme).
     * @param dialog
     * @param tabProp
     * @param tabCode
     * @param tabMax
     * @param tabMin
     * @param codeCache
     * @param indiceDev
     * @param contentPane 
     */
	public void tour1(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev, JPanel contentPane) {

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
			else if(tabProp[i]==tabCode[i]) {
				indiceDev.setText(indiceDev.getText()+"=");
			}

			resultProp = new String(tabProp);

		}

		dialog.setText(dialog.getText()+" C'est peut être : "+resultProp+"\n");
		checkWord(resultProp,codeCache,contentPane);
	}

	/**
	 * Lance le troisème tour de l'ordinateur (La troisième partie de l'algorithme).
	 * @param dialog
	 * @param tabProp
	 * @param tabCode
	 * @param tabMax
	 * @param tabMin
	 * @param codeCache
	 * @param indiceDev
	 * @param contentPane 
	 */
	public void tour2(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev, JPanel contentPane) {
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
		checkWord(resultProp,codeCache,contentPane);
	}

	/**
	 *  Lance le dernier tour de l'ordinateur (La dernière partie de l'algorithme).
	 *  Celle-ci est une boucle, si l'ordinateur ne trouve toujours pas il la recommencera à ce tour.
	 * @param dialog
	 * @param tabProp
	 * @param tabCode
	 * @param tabMax
	 * @param tabMin
	 * @param codeCache
	 * @param indiceDev
	 * @param contentPane 
	 */
	public void tour3etPlus(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev, JPanel contentPane) {

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
		checkWord(resultProp,codeCache,contentPane);
	}

	/**
	 * Méthode qui se lance lors du clique du bouton "OK" placé dans le cadre des indices.
	 * Verifie si les indices sont bons et passe le tour.
	 * @param tentativeIA
	 * @param ok
	 * @param indiceDev
	 * @param fin
	 * @param cadreJ
	 * @param cadreOrdi
	 * @param tour 
	 * @param dialog 
	 */
	public void finClick(JLabel tentativeIA, JButton ok, JLabel indiceDev, JButton fin, JPanel cadreJ, JPanel cadreOrdi, JLabel tour, JTextArea dialog) {
		if(!tentativeIA.getText().equals(indiceDev.getText())) {
			fin.setEnabled(true);
			fin.setBackground(Color.getHSBColor(0.154f, 0.85f, 0.94f));
			JOptionPane.showMessageDialog(null,"Les indices entrés ne sont pas bons !!","Erreur",JOptionPane.ERROR_MESSAGE);
		}
		else if(tentativeIA.getText().equals(indiceDev.getText())) {
			dialog.setText(dialog.getText()+"  "+indiceDev.getText()+"\n");
			ok.setEnabled(true);
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
			tentativeIA.setText("");
			fin.setEnabled(false);
			cadreJ.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
			cadreOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			indiceDev.setText("");
			tour.setText(" Nombre de tour : "+nbreTour+" ");
		}

	}
	/**
	 * Permet de cacher le clavier des chiffres si le nombres de cliques est égal à la longueur du code.
	 * @param blocProposition
	 * @param nombreClick
	 */
	public void RangeWord(JPanel blocProposition, int nombreClick) {
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		bloc = reglage.getString("cases");
		int cases = Integer.parseInt(bloc); 

		if(nombreClick==cases) {
			blocProposition.setVisible(false);
		}


	}
}	


