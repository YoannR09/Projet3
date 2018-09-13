package fr.yoannroche.projet3.mastermind.model;

import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.yoannroche.projet3.BeanReglage;
import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.Resultat;
import fr.yoannroche.projet3.mastermind.control.Control;

public class DefenseurMastermindModel {


	private BeanReglage			bean				;
	private int 				jeu					= 5;
	private Control				control				;
	private boolean				debutDecal			= true;
	private int					couleurOk			= 0;
	private int					decal				= 0;
	private int					nombreTour			= 0;
	private int					placer				= 0;
	private int					changer				= 0;
	private int					couleurSwitch		= 0;
	private ImageIcon			tentative1			= new ImageIcon("images/couleur/0.png");
	private InterfaceModel		model				= new InterfaceModel();
	private boolean				switchCouleur		= false;
	private boolean				partiFini			= false;
	private JLabel				propositionTab[]	;
	private int					propositionOrdi[]	;



	public DefenseurMastermindModel(BeanReglage bean, JLabel propositionTab[], int propositionOrdi[],Control control) {
		this.bean = bean;
		this.control = control;
		this.propositionTab = propositionTab;
		this.propositionOrdi = propositionOrdi;
	}

	public void dev(JPanel content){
		for(int i=0;i<bean.getCases();i++) {
		}
	}

	/**
	 * Méthode appelée à la fin du tour lors du click du bouton "ok" dans le cadre d'indices.
	 * En focntion du tour , lance un méthode diffèrente.
	 * @param propositionIcon
	 * @param blocTentative
	 * @param blocIndices
	 * @param changerClick
	 * @param placerClick
	 * @param contentPane
	 * @param indice
	 */
	public void finClick(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices,int changerClick, int placerClick,JPanel contentPane, JLabel indice,BeanReglage bean) {


		boolean[]			verif				= new boolean [bean.getCases()]; 

		if(nombreTour==0) {		
			blocTentative[nombreTour].removeAll();
			premierTour(propositionIcon,blocTentative,blocIndices,propositionTab,propositionOrdi,verif);
			placer=0;
			changer=0;
			check(propositionIcon, blocTentative, blocIndices,propositionTab,propositionOrdi,verif);
			blocTentative[nombreTour].revalidate();
			++nombreTour;
		}
		else if(nombreTour>=1) {		
			if(changerClick==changer & placerClick==placer ) {
				blocTentative[nombreTour].removeAll();
				blocIndices[nombreTour-1].setText(" ♦ :  "+placer+"   ♢  : "+changer+" ");
				tour(propositionIcon,blocTentative,blocIndices,contentPane,propositionTab,propositionOrdi,verif);
				placer=0;
				changer=0;
				check(propositionIcon, blocTentative, blocIndices,propositionTab,propositionOrdi,verif);
				blocTentative[nombreTour].revalidate();
				++nombreTour;
			}
			else {
				JOptionPane.showMessageDialog(null, "Donnez les bons indices !", "Attention", JOptionPane.WARNING_MESSAGE);
			}			
		}	
		if(nombreTour==bean.getTentatives() & !partiFini) {
			new Resultat(null, "Gagner",null,contentPane,jeu,bean).gagner();
			partiFini=true;
		}
	}

	/**
	 * Méthode qui appelée lors du premier tour pour créer la proposition de l'ordinateur.
	 * @param propositionIcon
	 * @param blocTentative
	 * @param blocIndices
	 * @param verif 
	 * @param propositionOrdi 
	 * @param propositionTab 
	 */
	public void premierTour(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices, JLabel[] propositionTab, int[] propositionOrdi, boolean[] verif) {
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
	 * @param verif 
	 * @param propositionOrdi 
	 * @param propositionTab 
	 */
	public void tour(JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices,JPanel contentPane, JLabel[] propositionTab, int[] propositionOrdi, boolean[] verif) {

		blocTentative[nombreTour].removeAll();

		if(nombreTour==1 & changer==0 & placer>=1 & (placer+changer)<bean.getCases()) {
			for(int i = 0;i<placer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(tentative1);
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placer; i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}

		/**
		 * L'ordinateur inverse le placement des couleurs.
		 */
		else if(changer+placer==bean.getCases() & !switchCouleur) {
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
		else if(changer+placer==bean.getCases() & switchCouleur==true) {
			for(int i=0;i<bean.getCases();i++) {
				control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(propositionIcon[i].getIcon());
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}
		else if(nombreTour>=2 & changer<=2 & placer==0 & debutDecal==true & (placer+changer)<bean.getCases()) {

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
			for(int i = (changer+changer);i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			decal=changer+changer;
			debutDecal=false;
		}
		else if(changer>2 & placer==0 & (placer+changer)<bean.getCases()) {
			for(int i = 0;i<changer;i++) {
				propositionTab[i]=model.createJLabel(); 
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i=changer;i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}

		else if(nombreTour>2 & changer>=1 & placer==0 & !debutDecal & (placer+changer)<bean.getCases()) {

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
			for(int i = decal+changer;i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			decal=decal+changer;
		}

		else if(nombreTour>1 & changer==0 & placer>=1 & (placer+changer)<bean.getCases()) {
			for(int i = 0;i<placer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placer; i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}

		else if(changer>=1 & placer>=1 & (placer+changer)<bean.getCases()) {
			for(int i =0;i<changer+placer;i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+propositionOrdi[i]+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
			for(int i =placer+changer; i<bean.getCases();i++) {
				propositionTab[i]=model.createJLabel();
				propositionTab[i].setIcon(new ImageIcon("images/couleur/"+nombreTour+".png"));
				blocTentative[nombreTour].add(propositionTab[i]);
			}
		}

		else if(changer==0 & placer==0) {
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
		if(couleurOk==bean.getCases()) {
			new Resultat(null, "Gagner",null,contentPane,jeu,bean).perdu();
			partiFini=true;
		}
		else {
			couleurOk=0;
		}

		blocTentative[nombreTour].revalidate();
	}

	/**
	 * Méthode pour regarder le nombre de couleur bien placés ou mal placés.
	 * @param propositionIcon
	 * @param blocTentative
	 * @param blocIndices
	 * @param verif 
	 * @param propositionOrdi 
	 * @param propositionTab 
	 */
	public void check(JLabel [] propositionIcon,JPanel[] blocTentative,JLabel [] blocIndices, JLabel[] propositionTab, int[] propositionOrdi, boolean[] verif) {
		/**
		 * Boucle qui cherche si la couleur est bien placée
		 */
		for(int i=0;i<bean.getCases();i++) {
			verif[i]=false;
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			if(propositionOrdi[i]==control.getProposition()) {
				++placer;
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
			for(int w =0;w<bean.getCases();w++) {
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
