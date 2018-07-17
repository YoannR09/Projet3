package fr.yoannroche.projet3.mastermind.model;

public class Reglage {
	
	private int nbreCases;
	private int essaie;
	
	public Reglage(int nbreCases,int essaie){
		this.nbreCases = nbreCases;
		this.essaie = essaie;
	}
	
	public int getEssaie() {
		return essaie;
	}
	public void setEssaie(int essaie) {
		this.essaie = essaie;
	}
	public int getNbreCasses() {
		return nbreCases;
	}
	public void setNbreCases(int nbreCases) {
		this.nbreCases = nbreCases;
	}

}
