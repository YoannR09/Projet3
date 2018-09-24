package fr.yoannroche.projet3.mastermind.model;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.yoannroche.projet3.BeanReglage;
import fr.yoannroche.projet3.Resultat;
import fr.yoannroche.projet3.mastermind.control.Control;

public class DuelMastermindModel {

	private BeanReglage		bean					;
	private int				jeu						= 6;
	private InterfaceModel	model					;
	private Control			control					;
	private int[]			couleurs				;
	private int				nombreTentative 		= 0;
	private int				couleurSwitch			= 0;
	private int				nombreTour				= 0;
	private int				couleurOk				= 0;
	private int				placerOrdi				= 0;
	private int				changerOrdi				= 0;
	private boolean			switchCouleur			= false;
	private boolean			partiFini				= false;
	private ImageIcon		tentative1				= new ImageIcon("images/couleur/0.png");
	private JLabel			propositionTab[]	    ;
	private int				propositionOrdi[]	    ;
	final Logger			logger					= LogManager.getLogger();


	public DuelMastermindModel(BeanReglage bean, JLabel propositionTab[],int propositionOrdi[],Control control,int[]
			couleurs,InterfaceModel modelInterface) {
		this.bean = bean;
		this.couleurs = couleurs;
		this.control = control;
		this.propositionTab = propositionTab;
		this.propositionOrdi = propositionOrdi;
		this.model = modelInterface;
		logger.info("Vous êtes dans le mode duel du jeu Mastermind");
	}

	/**
	 * Méthode appelée lors du click du bouton "ok" pour donner votre proposition.
	 * Elle donnera les indices 
	 * @param blocTentative
	 * @param propositionIcon
	 * @param blocIndices
	 * @param changer
	 * @param placer
	 * @param contentPane
	 */
	public void okClick(JPanel[] blocTentative, JLabel[] propositionIcon, JLabel[] blocIndices, int changer, int placer,JPanel contentPane, BeanReglage bean) {

		boolean[] verif	= new boolean [bean.getCases()];
		JLabel tabView[] = new JLabel[bean.getCases()];
		placer=0;
		changer=0;
		blocTentative[nombreTentative].removeAll();

		/**
		 * Boucle qui cherche si la couleur est bien placée
		 */
		for(int i=0;i<bean.getCases();i++) {
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
		 * Boucle qui cherche si il y a un switch de deux couleurs a faire.
		 */
		for(int i=0;i<bean.getCases();i++) {
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			couleurSwitch=control.getProposition();
			if(!verif[i]) {
				for(int w =0;w<bean.getCases();w++) {
					control.couleurChiffre(propositionIcon[w].getIcon().toString(), null);
					if(!verif[w] & couleurSwitch!=control.getProposition() & couleurSwitch==couleurs[w] & control.getProposition()==couleurs[i]) {
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
		for(int i=0;i<bean.getCases();i++) {
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			for(int w =0;w<bean.getCases();w++) {
				if(!verif[w] & control.getProposition()==couleurs[w]) {
					verif[w]=true;
					++changer;
					break;
				}
			}
		}
		if(placer==bean.getCases() & !partiFini) {
			new Resultat(null, "Victoire", null,contentPane, jeu,bean).gagner();
			partiFini=true;
		}
		blocIndices[nombreTentative].setText(" ♦ :  "+placer+"   ♢  : "+changer+" ");
		blocTentative[nombreTentative].revalidate();
		++nombreTentative;
	}

	public void finClick(JLabel[] propositionIcon, JPanel[] blocTentative,
			JLabel[] blocIndices,int changerClick, int placerClick,JPanel contentPane,
			JLabel indice, BeanReglage bean, JLabel indiceSecret) {

		boolean[] verif = new boolean [bean.getCases()];

		if(nombreTour==0) {		
			blocTentative[nombreTour].removeAll();
			premierTour(propositionIcon,blocTentative,blocIndices,propositionTab,propositionOrdi);
			placerOrdi=0;
			changerOrdi=0;
			check(propositionIcon, blocTentative, blocIndices,propositionTab,propositionOrdi,verif);
			blocTentative[nombreTour].revalidate();
			++nombreTour;
		}
		else if(nombreTour>=1) {		
			if(changerClick==changerOrdi & placerClick==placerOrdi ) {
				blocTentative[nombreTour].removeAll();
				tour(propositionIcon,blocTentative,blocIndices,contentPane,propositionTab,propositionOrdi);
				placerOrdi=0;
				changerOrdi=0;
				check(propositionIcon, blocTentative, blocIndices,propositionTab,propositionOrdi,verif);
				blocTentative[nombreTour].revalidate();
				++nombreTour;
			}
			else {
				JOptionPane.showMessageDialog(null, "Donnez les bons indices !", "Attention", JOptionPane.WARNING_MESSAGE);
			}			
		}	
		indiceSecret.setText(" ♦ :  "+placerOrdi+"   ♢  : "+changerOrdi+" ");
	}

	/**
	 * Méthode qui appelée lors du premier tour pour créer la proposition de l'ordinateur.
	 * @param propositionIcon
	 * @param blocTentative
	 * @param blocIndices
	 * @param propositionOrdi 
	 * @param propositionTab 
	 */
	public void premierTour(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices, JLabel[] propositionTab, int[] propositionOrdi) {
		for(int i =0;i<bean.getCases();i++) {
			propositionTab[i]=model.createJLabel();
			propositionTab[i].setIcon(tentative1);
			blocTentative[nombreTour].add(propositionTab[i]);
			control.couleurChiffre(propositionTab[i].getIcon().toString(), null);
			propositionOrdi[i]=control.getProposition();
		}
	}

	/**
	 * Méthode appelée à chaque tour.
	 * Donne une proposition en fonction des indices donnés.
	 * @param propositionIcon
	 * @param blocTentative
	 * @param blocIndices
	 * @param contentPane
	 * @param propositionOrdi 
	 * @param propositionTab 
	 */
	public void tour(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices,JPanel contentPane, JLabel[] propositionTab, int[] propositionOrdi) {

		blocTentative[nombreTour].removeAll();

		if(nombreTour==1 & changerOrdi==0 & placerOrdi>=1 & (placerOrdi+changerOrdi)<bean.getCases()) {
			for(int i = 0;i<placerOrdi;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(tentative1);
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placerOrdi; i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}

		/**
		 * L'ordinateur inverse le placement des couleurs.
		 */
		else if(changerOrdi+placerOrdi==bean.getCases() & !switchCouleur) {
			int couleurFin =bean.getCases()-1;
			for(int i=0;i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[couleurFin]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
				couleurFin--;
			}
			switchCouleur=true;
		}

		/**
		 * L'ordinateur possède assez d'indices pour trouver le code secret.
		 */
		else if(changerOrdi+placerOrdi==bean.getCases() & switchCouleur==true) {
			for(int i=0;i<bean.getCases();i++) {
				control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(propositionIcon[i].getIcon());
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}
		else if(nombreTour>1 & changerOrdi>0 & placerOrdi==0) {

			for(int i = 0;i<changerOrdi;i++) {
				propositionTab[i]=model.createJLabel(); 
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i = changerOrdi;i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}
		else if(changerOrdi>2 & placerOrdi==0 & (placerOrdi+changerOrdi)<bean.getCases()) {
			for(int i = 0;i<changerOrdi;i++) {
				propositionTab[i]=model.createJLabel(); 
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i=changerOrdi;i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}
		else if(nombreTour>1 & changerOrdi==0 & placerOrdi>=1 & (placerOrdi+changerOrdi)<bean.getCases()) {
			for(int i = 0;i<placerOrdi;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placerOrdi; i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}

		else if(changerOrdi>=1 & placerOrdi>=1 & (placerOrdi+changerOrdi)<bean.getCases()) {
			for(int i =0;i<changerOrdi+placerOrdi;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placerOrdi+changerOrdi; i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}

		else if(changerOrdi==0 & placerOrdi==0) {
			for(int i = 0;i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();// on crée les JLabel et on met dans tab
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}

		/**
		 * On définit la tentative de l'ordinateur.
		 */
		for(int a =0;a<bean.getCases();a++) {

			control.couleurChiffre(propositionTab[a].getIcon().toString(), null);
			propositionOrdi[a]=control.getProposition();		
		}

		/**
		 * Regarde si l'ordinateur à finit la partie en trouvant le code secret.
		 */
		for(int i=0;i<bean.getCases();i++) {
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			if(propositionOrdi[i]==control.getProposition()) {
				++couleurOk;
			}
		}
		if(couleurOk==bean.getCases() & !partiFini) {
			new Resultat(null, "Perdu",null,contentPane,jeu,bean).perdu(couleurs,control,jeu,null);
			partiFini=true;
		}
		else {
			couleurOk=0;
		}
		blocTentative[nombreTour].revalidate();
	}

	/**
	 * Méthode qui regarde le nombre de couleur bien ou mal placé que l'ordinateur possède.
	 * @param propositionIcon
	 * @param blocTentative
	 * @param blocIndices
	 * @param propositionTab
	 * @param propositionOrdi
	 * @param verif
	 */
	public void check(JLabel [] propositionIcon,JPanel[] blocTentative,JLabel [] blocIndices, JLabel[] propositionTab, int[] propositionOrdi, boolean[] verif) {
		/**
		 * Boucle qui cherche si la couleur est bien placée
		 */
		for(int i=0;i<bean.getCases();i++) {
			verif[i]=false;
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			if(propositionOrdi[i]==control.getProposition()) {
				++placerOrdi;
				verif[i]=true;
			}
		}

		for(int i=0;i<bean.getCases();i++) {
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			couleurSwitch=control.getProposition();
			if(!verif[i]) {
				for(int w =0;w<bean.getCases();w++) {
					control.couleurChiffre(propositionIcon[w].getIcon().toString(), null);
					if(!verif[w] & propositionOrdi[i]!=propositionOrdi[w] & propositionOrdi[i]==control.getProposition() & propositionOrdi[w]==couleurSwitch) {
						verif[w]=true;
						verif[i]=true;
						++changerOrdi;
						++changerOrdi;
						break;
					}
				}
			}
		}

		/**
		 * Boucle qui cherche si la couleur se trouve autre part dans le code secret
		 */
		for(int i=0;i<bean.getCases();i++) {
			for(int w =0;w<bean.getCases();w++) {
				control.couleurChiffre(propositionIcon[w].getIcon().toString(), null);
				if(propositionOrdi[i]==control.getProposition() & !verif[w]) {
					verif[w]=true;
					++changerOrdi;
					break;
				}
			}
		}
	}

	/**
	 * Méthode qui vérifie les indices donnés
	 * @param placerClick
	 * @param changerClick
	 * @param blocIndicesOrdi
	 * @param fin
	 * @param nombreTourAffichage 
	 * @param i 
	 * @param codeSecretOk 
	 * @param entrerIndi 
	 * @param vosTentative 
	 * @param entrerProp 
	 * @param indiceOk 
	 */
	public void checkIndice(int placerClick, int changerClick, JLabel[] blocIndicesOrdi, JButton fin, JLabel nombreTourAffichage,
			JLabel entrerProp, JLabel vosTentative, JLabel entrerIndi, boolean codeSecretOk, int i, boolean verif) {

		if(changerClick==changerOrdi & placerClick==placerOrdi ) {
			blocIndicesOrdi[nombreTour-1].setText(" ♦  :  "+placerOrdi+"   ♢   :  "+changerOrdi+" ");
			fin.setEnabled(false);
			status(entrerProp,vosTentative,entrerIndi,codeSecretOk,1);
			verif=true;
			nombreTourAffichage.setText((" Nombre de tour : "+nombreTour+" "));
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Donnez les bons indices !", "Attention", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Méthode qui gère l'éclairage des informations en haut des ecrans.
	 * @param entrerProp
	 * @param vosTentative
	 * @param entrerIndi
	 * @param codeSecretOk
	 * @param status
	 */
	public void status(JLabel entrerProp, JLabel vosTentative,
			JLabel entrerIndi,boolean codeSecretOk,int status) {

		if(status==1) {
			vosTentative.setBackground(Color.getHSBColor(0.134f, 0.05f, 0.45f));
			entrerProp.setBackground(Color.getHSBColor(0.134f, 0.25f, 0.95f));
			entrerIndi.setBackground(Color.getHSBColor(0.134f, 0.05f, 0.45f));
		}
		else if(status==2) {
			entrerProp.setBackground(Color.getHSBColor(0.134f, 0.05f, 0.45f));
			entrerIndi.setBackground(Color.getHSBColor(0.134f, 0.25f, 0.95f));
		}
	}

	/**
	 * Méthode qui gère l'affiche des infos du mode développeur.
	 * @param content
	 * @param indiceSecret 
	 */
	public void dev(JPanel content, JLabel indiceSecret){
		JLabel [] indice = new JLabel[bean.getCases()];
		for(int i=0;i<bean.getCases();i++) {
			control.chiffreCouleur(couleurs[i],content,indice[i]);
		}
	}
}
