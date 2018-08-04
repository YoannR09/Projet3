package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.yoannroche.projet3.plusmoins.view.gagner.VictoirePlusMoinsDef;
import fr.yoannroche.projet3.plusmoins.view.perdu.PerduPlusMoinsDef;

public class DefenseurPlusMoinsModel {

	private String bloc;
	private int nombreCoup =0;

	private String tentative;
	private int resultMax;
	private int resultMin;
	Font arial = new Font ("arial", 12,12);
	private Font arial2 = new Font ("arial", 10,10);
	private ImageIcon ordi1 = new ImageIcon("images/0.png");
	private int info;
	private String resultProp;
	private int nbreTour =0;
	private int gagner =0;
	private int cases = 4;
	private char tabProp[] = new char [cases];
	private char tabMin [] = new char [cases];
	private char tabMax[] = new char [cases];
	private Icon[] tableauImg = new Icon[7];

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
	 * @param nbreTour2 
	 * @param indiceDev 
	 */
	public void tentativeOrdi(JLabel tentativeOrdi, JLabel codeSecret2, int nombreCoup, JTextArea dialog,JPanel contentPane,JLabel image, int nbreTour2, JLabel indiceDev) {

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
	public void okClick(JTextField proposition, JLabel codeSecret, JPanel contentPane, JPanel ordi,JPanel blocProposition, JLabel image, JLabel tentativeOrdi, JTextArea dialog,JLabel indiceDev,JButton ok,JButton fin) {

		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		bloc = reglage.getString("cases");
		int cases = Integer.parseInt(bloc);

		if(proposition.getText().length()<cases) {
			JOptionPane.showMessageDialog(null,"Votre code est trop court !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
		}
		else if(proposition.getText().length()>cases) {
			JOptionPane.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
		}
		else if(proposition.getText().length()==cases) {
			ok.setEnabled(false);
			codeSecret.setText(proposition.getText());
			dialog.setText("");
			proposition.setText("");
			ordi.setVisible(true);	
			image.setIcon(ordi1);
			tentativeOrdi(tentativeOrdi,codeSecret,nombreCoup,dialog,contentPane,image,nbreTour,indiceDev);
			fin.setEnabled(true);
			fin.setBackground(Color.getHSBColor(0.154f, 0.85f, 0.94f));
		}

	}
	/**
	 * Regarde le nombre de tentatives.
	 * Une fois dépassé c'est gagné pour le joueur.
	 * @param nombreCoup
	 * @param contentPane
	 * @param codeSecret
	 */
	public void chechTentative(int nombreCoup,JPanel contentPane,String codeSecret) {

		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		tentative = reglage.getString("tentatives");
		int tentatives = Integer.parseInt(tentative);

		if(gagner==0) {
			if((nbreTour+1)==tentatives) {
				new VictoirePlusMoinsDef(null,"Gagner", codeSecret,contentPane,nombreCoup);
			}
		}
	}
	/**
	 * Compare la proposition de l'ordi au code secret.
	 * @param codeSecret
	 * @param codeSecret2
	 */
	public void checkWord(String proposition,String codeSecret2,JPanel contentPane) {

		if(proposition.equals(codeSecret2)){
			++gagner;
			new PerduPlusMoinsDef(null, "Perdu",nbreTour,contentPane);
		}
	}

	/**
	 * Méthode qui permet le passage d'un int en char
	 * @param i
	 * @return
	 */
	private char intToChar(int i) {
		String s = ""+i;
		return s.charAt(0);
	}
	public void tour0(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev, JPanel contentPane) {
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
		chechTentative(nombreCoup,contentPane,codeCache);
	}


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
		chechTentative(nombreCoup,contentPane,codeCache);
	}

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
		chechTentative(nombreCoup,contentPane,codeCache);
	}

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
		chechTentative(nombreCoup,contentPane,codeCache);
	}
	public void finClick(JLabel tentativeOrdi, JLabel codeSecret, int nombreCoup, JTextArea dialog,JPanel contentPane,JLabel image, int nbreTour2, JLabel indiceDev,JLabel tentativeIA,JButton fin,JPanel cadreOrdi) {
		if(!tentativeIA.getText().equals(indiceDev.getText())) {
			fin.setEnabled(true);
			fin.setBackground(Color.getHSBColor(0.154f, 0.85f, 0.94f));
			JOptionPane.showMessageDialog(null,"Les indices entrés ne sont pas bons !!","Erreur",JOptionPane.ERROR_MESSAGE);
		}
		else if(tentativeIA.getText().equals(indiceDev.getText())) {
			tentativeIA.setText("");
			cadreOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			indiceDev.setText("");
			nbreTour++;
			tentativeOrdi(tentativeOrdi,codeSecret,nombreCoup,dialog, contentPane, image,nbreTour,indiceDev);
			int i = ((int)(Math.random()*(7 - 0))+0);
			tableauImg[i] = new ImageIcon("images/"+i+".png");
			image.setIcon(tableauImg[i]);
		}
	}
	public void RangeWord(JPanel blocProposition, int nombreClick) {
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		bloc = reglage.getString("cases");
		int cases = Integer.parseInt(bloc); 

		if(nombreClick==cases) {
			blocProposition.setVisible(false);
		}
	}
}
