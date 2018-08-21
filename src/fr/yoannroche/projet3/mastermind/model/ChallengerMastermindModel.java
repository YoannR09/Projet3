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
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int cases = Integer.parseInt(reglage.getString("cases"));
	private int tentative = Integer.parseInt(reglage.getString("tentatives"));
	private JLabel tabView [] = new JLabel[cases];
	private int nombreTentative=0;
	Generateur gen = new Generateur();
	Control control = new Control();
	private int [] couleurs = gen.getCouleurs();
	private boolean [] verif = new boolean [cases];
	private int proposition =0;




	public void okClick(int nombreClick,JPanel[] blocTentative, JLabel[] propositionIcon, JLabel[] blocIndices, int changer, int placer,JPanel contentPane) {


		placer=0;
		changer=0;
		blocTentative[nombreTentative].removeAll();
		/**
		 * Boucle qui gère si la couleur est bien placée
		 */
		for(int i=0;i<cases;i++) {
			verif[i]=false;
			tabView[i]= createJLabel();	
			blocTentative[nombreTentative].add(tabView[i]);	
			tabView[i].setIcon(propositionIcon[i].getIcon());
			System.out.println(control.getProposition());
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			if(control.getProposition()==couleurs[i]) {
				++placer;
				blocIndices[nombreTentative].setText("  Ok : "+placer+"  Pl : "+changer+"  ");
				verif[i]=true;
			}
		}

		/**
		 * Boucle qui gère sila couleur se trouve autre part dans le code secret
		 */
		for(int o=0;o<cases;o++) {
			control.couleurChiffre(propositionIcon[o].getIcon().toString(), null);
			System.out.println(control.getProposition());
			if(verif[o]==true) {
				continue;
			}
			else if(verif[o]!=true){
				for(int w =0;w<cases;w++) {
					if(control.getProposition()==couleurs[w]) {
						if(verif[w]!=true) {
							verif[w]=true;
							++changer;
							blocIndices[nombreTentative].setText(" Ok : "+placer+"  Pl : "+changer+" ");
							break;
						}
					}
				}
			}
		}

		if(placer==cases) {
			new Resultat(null, "Victoire", null,contentPane, jeu).gagner();
		}
		blocTentative[nombreTentative].revalidate();
		++nombreTentative;
	}

	public void dev(JPanel content){
		for(int i=0;i<cases;i++) {
			control.chiffreCouleur(couleurs[i],content);
		}
	}

	public JLabel createJLabel(){
		JLabel jl = new JLabel();
		return jl;
	}
	public JPanel createJPanel(){
		JPanel jp = new JPanel();
		return jp;
	}

	public void blocJeuSize(JPanel blocJeu) {
		if(tentative==4) {
			blocJeu.setPreferredSize(new Dimension(350,190));
		}
		if(tentative==5) {
			blocJeu.setPreferredSize(new Dimension(350,190));
		}
		if(tentative==6) {
			blocJeu.setPreferredSize(new Dimension(350,235));
		}
		if(tentative==7) {
			blocJeu.setPreferredSize(new Dimension(350,275));
		}
		if(tentative==8) {
			blocJeu.setPreferredSize(new Dimension(350,315));
		}


	}

}
