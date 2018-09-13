package fr.yoannroche.projet3;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Generateur {

	private BeanReglage				bean			;
	private int						nombre			= 0;
	private static final Logger		logger			= LogManager.getLogger();
	private int[] couleurs	;

	public Generateur(BeanReglage bean) {

		this.bean = bean;
		 int[] couleurs = new int[bean.getCases()];
		 this.couleurs = couleurs;
		initNombre(couleurs);
		initCouleur(couleurs);

	}

	/**
	 * Génère une couleur et la place retourne couleurs.
	 * @param couleurs 
	 * @return
	 */
	public int[] initCouleur(int[] couleurs) {

		for(int i=0;i<bean.getCases();i++) {
			couleurs[i] = 0 +(int)(Math.random()*(bean.getCouleurs() - 0));
		}	

		logger.trace("Generateur crée une couleur en fonction du nombre de cases selectionnéS :" + bean.getCases());
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
	 * @param couleurs 
	 * @return
	 */


	public int initNombre(int[] couleurs){

		// for(int i = 0;i<cases;i++) {
		//	 nombre[i]= 0+(int)(Math.random()*(9-0));     Changer un tableau de int en un seul int.
		// }
		if(bean.getCases()==4) {
			int min = 1111;
			nombre = min +(int)(Math.random()*(10000 - min));
		}
		if(bean.getCases()==5) {
			int min = 11111;
			nombre = min +(int)(Math.random()*(100000 - min));
		}
		if(bean.getCases()==6) {
			int min = 111111;
			nombre = min +(int)(Math.random()*(1000000 - min));
		}
		if(bean.getCases()==7) {
			int min = 1111111;
			nombre = min +(int)(Math.random()*(10000000 - min));
		}
		if(bean.getCases()==8) {
			int min = 11111111;
			nombre = min +(int)(Math.random()*(100000000 - min));
		}
		logger.trace("Generateur crée un nombre en fonction du nombre de cases selectionnéS :" + bean.getCases());
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
