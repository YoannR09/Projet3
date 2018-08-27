package fr.yoannroche.projet3.mastermind.model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.Resultat;
import fr.yoannroche.projet3.mastermind.control.Control;

public class ChallengerMastermindModel{

	int jeu = 4;
	InterfaceModel model = new InterfaceModel();
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int cases = Integer.parseInt(reglage.getString("cases"));
	private JLabel tabView [] = new JLabel[cases];
	Generateur gen = new Generateur();
	Control control = new Control();
	private int [] couleurs = gen.getCouleurs();
	private int nombreTentative =0;
	private boolean [] verif = new boolean [cases];
	public void okClick(JPanel[] blocTentative, JLabel[] propositionIcon, JLabel[] blocIndices, int changer, int placer,JPanel contentPane) {


		placer=0;
		changer=0;
		blocTentative[nombreTentative].removeAll();
		/**
		 * Boucle qui cherche si la couleur est bien placée
		 */
		for(int i=0;i<cases;i++) {
			verif[i]=false;
			tabView[i]= model.createJLabel();	
			blocTentative[nombreTentative].add(tabView[i]);	
			tabView[i].setIcon(propositionIcon[i].getIcon());
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			if(control.getProposition()==couleurs[i]) {
				++placer;
				verif[i]=true;
			}
		}

		/**
		 * Boucle qui cherche si la couleur se trouve autre part dans le code secret
		 */
		for(int i=0;i<cases;i++) {
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			if(!verif[i]){
				for(int w =0;w<cases;w++) {
					if(control.getProposition()==couleurs[w]) {
							++changer;
							break;
					}
				}
			}
		}
		if(placer==cases) {
			new Resultat(null, "Victoire", null,contentPane, jeu).gagner();
		}
		blocIndices[nombreTentative].setText(" ♦ :  "+placer+"   ♢  : "+changer+" ");
		blocTentative[nombreTentative].revalidate();
		++nombreTentative;
	}

	public void dev(JPanel content){
		JLabel [] indice = new JLabel[cases];
		for(int i=0;i<cases;i++) {

			control.chiffreCouleur(couleurs[i],content,indice[i]);
				
		}
	}

	

}
