package fr.yoannroche.projet3;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Class qui permet de récupèrer via des getters et de transmettre dans l'application via des setters
 * @author Pierrosan
 *
 */
public class BeanReglage implements Serializable{

	private 	int 			cases;
	private 	int 			tentatives;
	private	 	int 			couleurs;
	private 	int 			dev;
	private		ResourceBundle 	reglage;
	
	
	public ResourceBundle getReglage() {
		return reglage;
	}
	public void setReglage(ResourceBundle reglage) {
		this.reglage = reglage;
	}
	public int getCases() {
		return cases;
	}
	public void setCases(int cases) {
		this.cases = cases;
	}
	public int getTentatives() {
		return tentatives;
	}
	public void setTentatives(int tentatives) {
		this.tentatives = tentatives;
	}
	public int getCouleurs() {
		return couleurs;
	}
	public void setCouleurs(int couleurs) {
		this.couleurs = couleurs;
	}
	public int getDev() {
		return dev;
	}
	public void setDev(int dev) {
		this.dev = dev;
	}

	

}
