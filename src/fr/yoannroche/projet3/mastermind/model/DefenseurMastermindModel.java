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
	private int nombreTentative=0;
	Generateur gen = new Generateur();
	Control control = new Control();
	private int [] couleurs = gen.getCouleurs();
	private boolean [] verif = new boolean [cases];
	private int proposition =0;
	private int nombreTour=0;
	ImageIcon tentative1 = new ImageIcon("images/couleur/1.png");
	
	
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
	public void okClick(JLabel[] propositionIcon, JPanel[] blocTentative) {
		blocTentative[nombreTour].removeAll();
		for(int i = 0;i<cases;i++) {
			propositionTab[i]=createJLabel();// on crée les JLabel et on met dans tab
	        propositionTab[i].setIcon(tentative1);
	        blocTentative[nombreTour].add(propositionTab[i]);
		}
		++nombreTour;
		blocTentative[nombreTour].revalidate();
	}
	public void finClick(JLabel[] propositionIcon, JPanel[] blocTentative) {
	
		System.out.println(nombreTour);
		if(nombreTour==1) {
			blocTentative[nombreTour].removeAll();
			for(int i = 0;i<cases;i++) {
				propositionTab[i]=createJLabel();// on crée les JLabel et on met dans tab
		        propositionTab[i].setIcon(tentative1);
		        blocTentative[nombreTour].add(propositionTab[i]);
			}
			blocTentative[nombreTour].revalidate();
		}
		
		++nombreTour;
	}
}
