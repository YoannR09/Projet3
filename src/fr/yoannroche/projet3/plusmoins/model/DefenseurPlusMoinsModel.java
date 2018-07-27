package fr.yoannroche.projet3.plusmoins.model;

import javax.swing.JLabel;

public class DefenseurPlusMoinsModel {

	private static String tentative;
	private static String bloc;
	private static String maximum = "9999";
	private static String minimum = "1111";
	private static String proposition;
	private static String codeSecret = "3456";
	private static int nombreCoup =0;
	static int tabMax[] = new int [codeSecret.length()];
	static int tabCode[] = new int [codeSecret.length()];
	static int tabProp[] = new int [codeSecret.length()];
	static int tabMin [] = new int [codeSecret.length()];
	static int max;
	static int prop;
	static int min;
	static int code;

	public DefenseurPlusMoinsModel() {}
	
	public static void tentativeOrdi(JLabel tentativeOrdi) {

		do {
			for(int i=0;i<codeSecret.length();i++) {
				max = Character.getNumericValue(maximum.charAt(i));
				prop = Character.getNumericValue(proposition.charAt(i));
				min = Character.getNumericValue(minimum.charAt(i));
				code = Character.getNumericValue(codeSecret.charAt(i));

				prop = 5;

				if(prop>code) {
					prop = max ;
					prop = prop/2;
					
					if(prop>code) {
						max = prop;
					}
				}
				else if(prop<code) {
					min = prop;
					prop = prop+(prop/2);
					if(prop>code) {
						max=prop;

					}
				}
				
			tabMax[i]=max;
			
				
				
				
			}
			maximum=tabMax.toString();
			++nombreCoup; // Fin de la première boucle.
	System.out.println(maximum);
			for(int i=0; i<codeSecret.length();i++) {
				
				
			}

		}while(proposition==codeSecret);
	

	}
}
