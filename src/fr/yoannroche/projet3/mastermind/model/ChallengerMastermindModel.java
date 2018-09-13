package fr.yoannroche.projet3.mastermind.model;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.BeanReglage;
import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.Resultat;
import fr.yoannroche.projet3.mastermind.control.Control;

/**
 * Class qui permet de controler la proposition et d'exposer les indications.
 * Elle tient également compte du mode développeur.
 * 
 * @author El-ra
 */
public class ChallengerMastermindModel {

	private BeanReglage		bean			;
	private int				jeu				= 4;
	private InterfaceModel	model			= new InterfaceModel();
	private int				nombreTentative = 0;
	private int				couleurSwitch	= 0;
	private int[]			couleurs		;
	private boolean			partiFini		= false;
	private Control			control			;

	public ChallengerMastermindModel(BeanReglage bean,Control control,int[] couleurs) {
		
		this.bean = bean;
		this.control = control;
		this.couleurs = couleurs;
	}

	/**
	 * Class appelée lors du click du bouton ok pour envoyer votre proposition
	 * @param blocTentative
	 * @param propositionIcon
	 * @param blocIndices
	 * @param changer
	 * @param placer
	 * @param contentPane
	 */
	public void okClick(JPanel[] blocTentative, JLabel[] propositionIcon, JLabel[] blocIndices, int changer, int placer,JPanel contentPane,BeanReglage bean) {

		boolean[] verif	= new boolean [bean.getCases()];
		JLabel	tabView[] = new JLabel[bean.getCases()];
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
		if(nombreTentative==bean.getTentatives() & !partiFini) {
			new Resultat(null, "Victoire", null,contentPane, jeu,bean).perdu();
			partiFini=true;
		}
	}

	public void dev(JPanel content){
		JLabel [] indice = new JLabel[bean.getCases()];
		for(int i=0;i<bean.getCases();i++) {
			control.chiffreCouleur(couleurs[i],content,indice[i]);
		}
	}
}
