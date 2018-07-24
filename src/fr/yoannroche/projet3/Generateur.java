package fr.yoannroche.projet3;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Locale;
import java.util.ResourceBundle;

public class Generateur {
	
	private static String tentative;
	private static String bloc;
	private int nombre;
	private static final Logger logger = LogManager.getLogger();
	
	
	public Generateur() {
		
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		tentative = reglage.getString("tentatives");
		bloc = reglage.getString("cases");
		int tentatives = Integer.parseInt(tentative);
		int cases = Integer.parseInt(bloc);
		initNombre(cases);
		
	}
	
	/**
	 * Genere un nombre cases en fontion des réglages définit dans le fichier Config.properties.
	 * @param cases
	 * @return
	 */
	
	
	 public int initNombre(int cases){
		if(cases==4) {
			int min = 1111;
			nombre = min +(int)(Math.random()*(10000 - min));
		}
		if(cases==5) {
			int min = 11111;
			nombre = min +(int)(Math.random()*(100000 - min));
		}
		if(cases==6) {
			nombre = (int)(Math.random()*(1000000));
		}
		if(cases==7) {
			nombre = (int)(Math.random()*(10000000));
		}
		if(cases==8) {
			nombre = (int)(Math.random()*(100000000));
		}
		logger.trace("Generateur crée un nombre en fonction du nombre de cases selectionné :" + cases);
		return nombre;
	
	}
	 /**
	  * Le setter et getter de la variable nombre.
	  * le nombre est définit par la méthode initNombre.
	  * @param nombre
	  */
	 public void setNombre(int nombre) {
			this.nombre = nombre;
		}
		public int getNombre() {
			return this.nombre;
		}

}
