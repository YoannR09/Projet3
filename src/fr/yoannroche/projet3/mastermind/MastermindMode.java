package fr.yoannroche.projet3.mastermind;

/**
 * Enumeration qui gère ke mode choisis du Mastermind.
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum MastermindMode {
	
	
	Challenger("Mode Challenger"),
	Defenseur("Mode Défenseur");
	
	private String mode = "";
	private static final Logger logger = LogManager.getLogger();
	
	MastermindMode(String mode){
	    this.mode = mode;
	  }
	
	public void getMode(){
	    logger.info(mode);
	  }
}
