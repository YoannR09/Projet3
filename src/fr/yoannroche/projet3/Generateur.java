package fr.yoannroche.projet3;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Generateur {
	
	private String bloc;
	private int nombre;
	private static final Logger logger = LogManager.getLogger();
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int cases = Integer.parseInt(reglage.getString("cases"));
	private int couleur = Integer.parseInt(reglage.getString("couleur"));
	private int [] couleurs = new int[cases];
	
	public Generateur() {
		
		initNombre(cases);
		initCouleur(cases);
		
	}
	
	public int[] initCouleur(int cases) {
		
		for(int o=4;o<10;o++) {
			if(cases==o) {
				for(int i =0;i<cases;i++) {
					couleurs[i] = 0 +(int)(Math.random()*(couleur - 0));
				}
			}
		}
		logger.trace("Generateur crée une couleur en fonction du nombre de cases selectionnéS :" + cases);
		return couleurs;	
	}
	
	public int[] getCouleurs() {
		return couleurs;
	}

	public void setCouleurs(int[] couleurs) {
		this.couleurs = couleurs;
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
			int min = 111111;
			nombre = min +(int)(Math.random()*(1000000 - min));
		}
		if(cases==7) {
			int min = 1111111;
			nombre = min +(int)(Math.random()*(10000000 - min));
		}
		if(cases==8) {
			int min = 11111111;
			nombre = min +(int)(Math.random()*(100000000 - min));
		}
		logger.trace("Generateur crée un nombre en fonction du nombre de cases selectionnéS :" + cases);
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
