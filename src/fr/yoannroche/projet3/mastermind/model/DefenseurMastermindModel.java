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
	private boolean debutDecal = true;
	private int decal =0;
	private int[] codeSecret = new int[cases];
	private int[] couleurOk = new int[cases];
	private int proposition =0;
	private int nombreTour=0;
	private int placer=0;
	private int changer=0;
	private int couleurSwitch;
	ImageIcon tentative1 = new ImageIcon("images/couleur/0.png");
	InterfaceModel model = new InterfaceModel();


	public void dev(JPanel content){
		for(int i=0;i<cases;i++) {
			control.chiffreCouleur(couleurs[i], content, null);
		}
	}



	public void finClick(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {

		blocTentative[nombreTour].removeAll();
		if(nombreTour==0) {		
			premierTour(propositionIcon,blocTentative,blocIndices);
			placer=0;
			changer=0;
			check(propositionIcon, blocTentative, blocIndices);
		}

		if(nombreTour>=1) {
			tour(propositionIcon,blocTentative,blocIndices);
			placer=0;
			changer=0;
			check(propositionIcon, blocTentative, blocIndices);
		}

		blocTentative[nombreTour].revalidate();
		blocIndices[nombreTour].setText(" ♦ :  "+placer+"   ♢  : "+changer+" ");
		++nombreTour;

	}


	public void premierTour(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {
		for(int i =0;i<cases;i++) {
			propositionTab[i]=model.createJLabel();
			propositionTab[i].setIcon(tentative1);
			blocTentative[nombreTour].add(propositionTab[i]);
			control.couleurChiffre(propositionTab[i].getIcon().toString(), null);
			propositionOrdi[i]=control.getProposition();
		}

	}



	public void tour(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices) {

		blocTentative[nombreTour].removeAll();


		if(nombreTour==1 & changer==0 & placer>=1) {
			for(int i = 0;i<placer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(tentative1);
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placer; i<cases;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}
		else if(nombreTour>=2 & changer<=2 & placer==0 & debutDecal==true) {

			for(int i =0; i<changer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}

			for(int i = changer;i<(changer+changer);i++) {
				propositionTab[i]=model.createJLabel(); // Problème si il y a un chiffre imper.
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[(i-changer)]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i = (changer+changer);i<cases;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			decal=changer+changer;
			debutDecal=false;
		}
		
		else if(nombreTour>2 & changer>=1 & placer==0 & !debutDecal) {

			for(int i =0; i<decal;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}

			for(int i = decal;i<decal+changer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[decal-changer]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i = decal+changer;i<cases;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
           decal=decal+changer;
		}
		
		else if(changer+placer==cases) {
			for(int i=cases;i>0;i--) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}



		else if(nombreTour>1 & changer==0 & placer>=1) {
			for(int i = 0;i<placer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placer; i<cases;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}
		
		else if(changer>=1 & placer>=1) {
			for(int i =0;i<changer+placer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placer+changer; i<cases;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}




		else if(changer==0 & placer==0) {
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


	public void check(JLabel [] propositionIcon,JPanel[] blocTentative,JLabel [] blocIndices) {
		/**
		 * Boucle qui cherche si la couleur est bien placée
		 */
		for(int i=0;i<cases;i++) {
			verif[i]=false;
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			if(propositionOrdi[i]==control.getProposition()) {
				++placer;
				verif[i]=true;
				couleurOk[i]=propositionOrdi[i];
			}
		}
		for(int i=0;i<cases;i++) {
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			couleurSwitch=control.getProposition();
			if(!verif[i]) {
				for(int w =0;w<cases;w++) {
					control.couleurChiffre(propositionIcon[w].getIcon().toString(), null);
					if(!verif[w] & propositionOrdi[i]!=propositionOrdi[w] & propositionOrdi[i]==control.getProposition() & propositionOrdi[w]==couleurSwitch) {
						verif[w]=true;
						verif[i]=true;
							++changer;
							++changer;
							break;
					}
				}
			}
		}

		/**
		 * Boucle qui cherche si la couleur se trouve autre part dans le code secret
		 */
		for(int i=0;i<cases;i++) {
			if(!verif[i]){
				for(int w =0;w<cases;w++) {
					control.couleurChiffre(propositionIcon[w].getIcon().toString(), null);
					if(propositionOrdi[i]==control.getProposition() & !verif[w]) {
						verif[w]=true;
						++changer;
						break;
					}
				}
			}
		}
	}
}
