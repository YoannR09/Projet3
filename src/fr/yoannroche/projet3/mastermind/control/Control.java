package fr.yoannroche.projet3.mastermind.control;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.BeanReglage;

/**
 * Class qui fère le changement des couleurs en chiffre et inversement des chiffres en couleurs.
 * @author El-ra
 *
 */
public class Control {

	private BeanReglage			bean			;
	private int					proposition		= 0;


	
	public Control(BeanReglage bean) {
		this.bean = bean;
	}
/**
 * Changement des chiffres en couleurs.
 * @param couleurs
 * @param content
 * @param indice
 */
	public void chiffreCouleur(int couleurs,JPanel content, JLabel indice) {

		indice=createJLabel();		
		indice.setIcon(new ImageIcon("images/couleur/"+couleurs+".png"));	
		content.add(indice);
	}
	
/**
 * Changement des couleurs en chiffres.
 * @param couleur
 * @param content
 */
	public void couleurChiffre(String couleur,JPanel content) {

		for(int i = 0;i<bean.getCouleurs();i++) {
			if(couleur.equals("images/couleur/"+i+".png")) {
				setProposition(i);
			}
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
	public int getProposition() {
		return proposition;
	}
	public void setProposition(int proposition) {
		this.proposition = proposition;
	}
}

