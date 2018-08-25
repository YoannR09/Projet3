package fr.yoannroche.projet3.mastermind.model;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.Reglage;
import fr.yoannroche.projet3.mastermind.control.Control;

public class DefenseurMastermindModel {

	int jeu = 5;
	Reglage reg = new Reglage();
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int cases =  Integer.parseInt(reglage.getString("cases"));
	private int tentative = Integer.parseInt(reglage.getString("tentatives"));
	private JLabel tabView [] = new JLabel[cases];
	JLabel propositionTab [] = new JLabel[cases];
	int propositionOrdi [] = new int [cases];
	Generateur gen = new Generateur();
	Control control = new Control();
	private int[] couleurs = gen.getCouleurs();
	private boolean [] verif = new boolean [cases];
	private int[] codeSecret = new int[cases];
	private int proposition =0;
	private int nombreTour=0;
	private int placer=0;
	private int changer=0;
	ImageIcon tentative1 = new ImageIcon("images/couleur/0.png");
	InterfaceModel model = new InterfaceModel();


	public void dev(JPanel content){
		for(int i=0;i<cases;i++) {
			control.chiffreCouleur(couleurs[i], content, null);
		}
	}



	public void finClick(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {


		if(nombreTour==1) {		
		}
		
		if(nombreTour==2) {
		}
		++nombreTour;
	}


	public void tour1(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {

		blocTentative[nombreTour].removeAll();
		



		if(changer==0 & placer>=1) {
			for(int i = 0;i<placer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(tentative1);
				blocTentative[nombreTour].add(propositionTab[i]);

			}
			for(int i =0; i<(cases-placer);i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}

		}

		if(changer==0 & placer==0) {
			for(int i = 0;i<cases;i++) {
				propositionTab[i]=model.createJLabel();// on crée les JLabel et on met dans tab
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}

		}
		for(int a =0;a<cases;a++) {

			control.couleurChiffre(propositionTab[a].getIcon().toString(), null);
			propositionOrdi[a]=control.getProposition();
			
		}
		blocTentative[nombreTour].revalidate();
	}



	public void tour2(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {

		blocTentative[nombreTour].removeAll();
		
		if(changer==0 & placer>=1) {
			for(int i = 0;i<placer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(tentative1);
				blocTentative[nombreTour].add(propositionTab[i]);

			}
			for(int i =0; i<(cases-placer);i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}

		}
		if(changer==0 & placer==0) {
			for(int i = 0;i<cases;i++) {
				propositionTab[i]=model.createJLabel();// on crée les JLabel et on met dans tab
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}
		for(int a =cases;a>0;a++) {

			control.couleurChiffre(propositionTab[a].getIcon().toString(), null);
			propositionOrdi[a]=control.getProposition();
			System.out.println(propositionOrdi[a]);
		}
		blocTentative[nombreTour].revalidate();

	}

	public void check(JLabel [] blocIndices,JLabel [] propositionIcon) {
	}



	public void okClick(int nombreClick, JPanel[] blocTentative, JLabel[] propositionIcon, JLabel[] blocIndices,
			int changer2, int placer2, JPanel contentPane) {
		
	}
}
