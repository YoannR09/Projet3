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

import fr.yoannroche.projet3.BeanReglage;
import fr.yoannroche.projet3.Resultat;

/**
 * Class qui gère la partie fonctionnement du mode défenseur du Plus ou Moins.
 * @author yoann
 *
 */
public class DefenseurPlusMoinsModel {

	private BeanReglage					bean				;
	private int							nombreCoup			= 0;
	private int							resultMax			= 0;
	private int							resultMin			= 0;
	private Font						arial2				= new Font ("arial", 10,10);
	private ImageIcon					ordi1				= new ImageIcon("images/0.png");
	private int							info				= 0;
	private String						resultProp			= null;
	private int							nbreTour			= 0;
	private int							gagner				= 0;
	private char						tabProp[]			;
	private char						tabMin[]			;
	private char						tabMax[]			;
	private Icon[]						tableauImg			= new Icon[7];
	private int							jeu					= 2;

	public DefenseurPlusMoinsModel(BeanReglage bean, char[] tabProp, char[] tabMin, char[] tabMax) {
		this.bean = bean;
	}

	/**
	 * Méthode qui lance les tantatives de l'ordinateur.
	 * + Le changement de text dans le dialog de l'ordi.
	 * + Affiche la défaite ou la victoire.
	 * @param tentativeOrdi
	 * @param codeSecret2
	 * @param nombreCoup
	 * @param dialog
	 * @param contentPane
	 * @param indiceDev 
	 */
	public void tentativeOrdi(JLabel tentativeOrdi, JLabel codeSecret2, int nombreCoup, JTextArea dialog,JPanel contentPane,JLabel image, JLabel indiceDev) {

		String codeCache = codeSecret2.getText();
		char tabCode[] = codeCache.toCharArray();

		if(nbreTour==0) {
			tour0(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev,contentPane);
		}
		else if(nbreTour==1) {
			tour1(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev,contentPane);
		}
		else if(nbreTour==2){
			tour2(dialog,tabProp,tabCode,tabMax,tabMin,codeCache,indiceDev,contentPane);
		}
		else if(nbreTour>=3){
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


		if(proposition.getText().length()<bean.getCases()) {
			JOptionPane.showMessageDialog(null,"Votre code est trop court !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
		}
		else if(proposition.getText().length()>bean.getCases()) {
			JOptionPane.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
		}
		else if(proposition.getText().length()==bean.getCases()) {
			ok.setEnabled(false);
			codeSecret.setText(proposition.getText());
			dialog.setText("");
			proposition.setText("");
			ordi.setVisible(true);	
			image.setIcon(ordi1);
			tentativeOrdi(tentativeOrdi,codeSecret,nombreCoup,dialog,contentPane,image,indiceDev);
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

		if(gagner==0) {
			if((nbreTour+1)==bean.getTentatives()) {
				new Resultat(null, "Gagner",null,contentPane,jeu,bean).gagner();
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
			new Resultat(null, "Perdu",null,contentPane,jeu,bean).perdu();
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
	
	/**
	 * Cette méthode est appelée au commencement après que le joueur est donné son code secret.
	 * Elle donne la première proposition de l'ordinateur.
	 * @param dialog
	 * @param tabProp
	 * @param tabCode
	 * @param tabMax
	 * @param tabMin
	 * @param codeCache
	 * @param indiceDev
	 * @param contentPane
	 */
	public void tour0(JTextArea dialog, char []tabProp,char []tabCode,char[]tabMax,char [] tabMin,String codeCache, JLabel indiceDev, JPanel contentPane) {
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
		chechTentative(nombreCoup,contentPane,codeCache);
	}

	/**
	 * Cette méthode est appelée pour la deuxième proposition de l'ordinateur.
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
		chechTentative(nombreCoup,contentPane,codeCache);
	}

	/**
	 * Cette méthode est appelée pour la troisème proposition de l'ordinateur.
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
		chechTentative(nombreCoup,contentPane,codeCache);
	}

	/**
	 * Cette méthode est appelée pour les dernière proposition de l'ordinateur.
	 * Il exécutera une boucle avec des + ou - pour trouver les derniers chiffres.
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
		chechTentative(nombreCoup,contentPane,codeCache);
	}

	/**
	 * Méthode appelée lors du click sur bouton "ok" des indices
	 * Il vérifie si les indices donnés sont bons.
	 * @param tentativeOrdi
	 * @param codeSecret
	 * @param nombreCoup
	 * @param dialog
	 * @param contentPane
	 * @param image
	 * @param nbreTour2
	 * @param indiceDev
	 * @param tentativeIA
	 * @param fin
	 * @param cadreOrdi
	 */
	public void finClick(JLabel tentativeOrdi, JLabel codeSecret, int nombreCoup, JTextArea dialog,JPanel contentPane,JLabel image, int nbreTour2, JLabel indiceDev,JLabel tentativeIA,JButton fin,JPanel cadreOrdi) {
		if(!tentativeIA.getText().equals(indiceDev.getText())) {
			fin.setEnabled(true);
			fin.setBackground(Color.getHSBColor(0.154f, 0.85f, 0.94f));
			JOptionPane.showMessageDialog(null,"Les indices entrés ne sont pas bons !!","Erreur",JOptionPane.ERROR_MESSAGE);
		}
		else if(tentativeIA.getText().equals(indiceDev.getText())) {
			dialog.setText(dialog.getText()+"  "+indiceDev.getText()+"\n");
			tentativeIA.setText("");
			cadreOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			indiceDev.setText("");
			nbreTour++;
			tentativeOrdi(tentativeOrdi,codeSecret,nombreCoup,dialog, contentPane, image,indiceDev);
			int i = ((int)(Math.random()*(7 - 0))+0);
			tableauImg[i] = new ImageIcon("images/"+i+".png");
			image.setIcon(tableauImg[i]);
		}
	}

	/**
	 * Cette méthode vérifie la longueur de la proposition.
	 * @param blocProposition
	 * @param nombreClick
	 */
	public void RangeWord(JPanel blocProposition, int nombreClick) {
		
		if(nombreClick==bean.getCases()) {
			blocProposition.setVisible(false);
		}
	}
}
