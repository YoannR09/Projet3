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

import fr.yoannroche.projet3.BeanReglage;
import fr.yoannroche.projet3.Resultat;

/**
 * Cette class gère la partie fonctionnement du mode Duel du Plus ou Moins.
 * @author yoann
 *
 */
public class DuelPlusMoinsModel {

	private BeanReglage			bean				;
	private String				nombreString		;
	private Font				arial2				= new Font ("arial", 10,10);
	private Font				impact				= new Font ("impact", 17,17);
	private String				resultProp			= null;
	private int					resultMax			= 0;
	private int					resultMin			= 0;
	private int					nbreTour			= 0;
	private boolean				gagner				= false;
	private int					info				= 0;
	private char				tabProp[]			;
	private char				tabMin[]			;
	private char				tabMax[] 			;
	private int					jeu					= 3;


	public DuelPlusMoinsModel(BeanReglage bean,int nombreString2, char[] tabProp, char[] tabMin, char[] tabMax) {
		
		this.bean = bean;
		this.tabProp = tabProp;
		this.tabMin = tabMin;
		this.tabMax = tabMax;
		this.nombreString = Integer.toString(nombreString2);
	}
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
			if(tentative.getText().equals(nombreString)) {
				if(gagner==false) {
					new Resultat(null, "Gagner",nombreString,contentPane,jeu,bean).gagner();
					gagner=true;
				}
			}
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
			if(gagner==false){
				new Resultat(null,"Perdu" ,codeSecret2, contentPane,jeu,bean).perdu(null,null,jeu,nombreString);
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
		for(int o=0;o<bean.getCases();o++) { //Création de tableaux max, min et proposition.

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
				resultMin = Character.getNumericValue(tabMin[i]);
				resultMax = Character.getNumericValue(tabMax[i]);
				
				
				if(resultMin==5 & resultMax==7) {
					tabProp[i]=intToChar(6);
				}
				else if(resultMin==7 & resultMax==9) {
					tabProp[i]=intToChar(8);
				}
				else if(resultMin==0 & resultMax==2) {
					tabProp[i]=intToChar(1);
				}
				else if(resultMin==2 & resultMax==5) {
					tabProp[i]=intToChar(4);
				}

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

		if(nombreClick==bean.getCases()) {
			blocProposition.setVisible(false);
		}
	}
}	
