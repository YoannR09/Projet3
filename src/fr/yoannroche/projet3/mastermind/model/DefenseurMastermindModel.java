package fr.yoannroche.projet3.mastermind.model;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.mastermind.control.Control;

public class DefenseurMastermindModel {

	int jeu = 5;
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int cases = Integer.parseInt(reglage.getString("cases"));
	private int tentative = Integer.parseInt(reglage.getString("tentatives"));
	private JLabel tabView [] = new JLabel[cases];
	JLabel propositionTab [] = new JLabel[cases];
	int propositionOrdi [] = new int [cases];
	private int nombreTentative=0;
	Generateur gen = new Generateur();
	Control control = new Control();
	private int [] couleurs = gen.getCouleurs();
	private boolean [] verif = new boolean [cases];
	private int proposition =0;
	private int nombreTour=0;
	private int placer=0;
	private int changer=0;
	ImageIcon tentative1 = new ImageIcon("images/couleur/0.png");


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
	public void dev(JPanel content){
		for(int i=0;i<cases;i++) {
			control.chiffreCouleur(couleurs[i], content);
		}
	}
	
	
	public void okClick(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel vosTentative, JLabel[] blocIndices) {
		blocTentative[nombreTour].removeAll();
		vosTentative.setText(" Entrez les indices pour aider l'ordinateur ");
		for(int i =0;i<cases;i++) {
			propositionTab[i]=createJLabel();// on crée les JLabel et on met dans tab
			propositionTab[i].setIcon(tentative1);
			blocTentative[nombreTour].add(propositionTab[i]);
		}
		for(int a =0;a<cases;a++) {
			control.couleurChiffre(propositionTab[a].getIcon().toString(), null);
			propositionOrdi[a]=control.getProposition();
		}
		
		check(blocIndices,propositionIcon);
		blocTentative[nombreTour].revalidate();
		++nombreTour;
	}
	
	
	public void finClick(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {

		
		blocIndices[nombreTour-1].setText("  Ok : "+placer+"  Pl : "+changer+"  ");
		
		if(nombreTour==1) {
			tour1(propositionIcon,blocTentative,blocIndices);
			check(blocIndices,propositionIcon);
		}
		if(nombreTour==2) {
			tour2(propositionIcon,blocTentative,blocIndices);
			check(blocIndices,propositionIcon);
		}
		for(int a =0;a<cases;a++) {
			control.couleurChiffre(propositionTab[a].getIcon().toString(), null);
			propositionOrdi[a]=control.getProposition();
		}
		placer=0;
		changer=0;
		check(blocIndices,propositionIcon);
		
		++nombreTour;
	}
	
	
	public void tour1(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {
		
		blocTentative[nombreTour].removeAll();
		
		if(changer==0 & placer==1) {
			for(int i = 0;i<cases;i++) {
			propositionTab[i]=createJLabel();
			propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
			propositionTab[0].setIcon(tentative1);
			blocTentative[nombreTour].add(propositionTab[i]);
			}
		}
		
		if(changer==0 & placer==0) {
			for(int i = 0;i<cases;i++) {
				propositionTab[i]=createJLabel();// on crée les JLabel et on met dans tab
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			
		}
		blocTentative[nombreTour].revalidate();
	}
	
	public void check(JLabel [] blocIndices,JLabel [] propositionIcon) {
		for(int i=0;i<cases;i++) {
			verif[i]=false;
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			
			if(propositionOrdi[i]==control.getProposition()) {
				++placer;
				verif[i]=true;
			}
		}
		/**
		 * Boucle qui cherche si la couleur se trouve autre part dans le code secret
		 */
		for(int o=0;o<cases;o++) {
			control.couleurChiffre(propositionIcon[o].getIcon().toString(), null);
			if(verif[o]==true) {
				continue;
			}
			else if(verif[o]!=true){
				for(int w =0;w<cases;w++) {
					control.couleurChiffre(propositionIcon[w].getIcon().toString(), null);
					if(propositionOrdi[o]==control.getProposition()) {
						if(verif[w]!=true) {
							verif[w]=true;
							++changer;
							break;
						}
					}
				}
			}
		}
		
	}
	
	public void tour2(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {
		
	}
	
}
