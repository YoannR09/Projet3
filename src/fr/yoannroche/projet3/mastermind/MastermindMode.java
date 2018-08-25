package fr.yoannroche.projet3.mastermind;

public enum MastermindMode {
	Challenger("Mode Challenger"),
	Defenseur("Mode Défenseur"),
	Duel("Mode Duel");
	
	private String mode = "";

	MastermindMode(String mode){
	    this.mode = mode;
	  }
	
	public void getMode(){
	    System.out.println(mode);
	  }
}
