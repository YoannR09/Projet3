package fr.yoannroche.projet3;

import java.io.Serializable;

public class BeanReglage implements Serializable{

	private 	int 	cases;
	private 	int 	tentatives;
	private	 	int 	couleurs;
	private 	int 	dev;
	
	
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
