package fr.yoannroche.projet3.mastermind;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum MastermindMode {
	
	
	Challenger("Mode Challenger"),
	Defenseur("Mode Défenseur"),
	Duel("Mode Duel");
	
	private String mode = "";
	private static final Logger logger = LogManager.getLogger();
	
	MastermindMode(String mode){
	    this.mode = mode;
	  }
	
	public void getMode(){
	    logger.info(mode);
	  }
}
