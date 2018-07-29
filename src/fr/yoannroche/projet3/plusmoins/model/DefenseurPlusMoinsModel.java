package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DefenseurPlusMoinsModel {

	private static String bloc;
	private static String maximum = "9999";
	private static String minimum = "1111";
	private static String proposition ="5555";
	private static int nombreCoup =0;
	static char tabMax[] = maximum.toCharArray();
	
	static char tabProp[] = proposition.toCharArray();
	static char tabMin [] = minimum.toCharArray();
	static Font impact = new Font ("impact", 17,17);
	Font arial = new Font ("arial", 12,12);
	Font arial2 = new Font ("arial", 10,10);
	static ImageIcon ordi1 = new ImageIcon("images/ordi1.png");
	static int info;
	static String resultProp;

	public DefenseurPlusMoinsModel() {}

	public static void tentativeOrdi(JLabel tentativeOrdi, JLabel codeSecret2, int nombreCoup, JTextArea dialog) {

		String codeSecret = codeSecret2.getText();
		char tabCode[] = codeSecret.toCharArray();
		char tabProp[] = proposition.toCharArray();
		char tabMin [] = minimum.toCharArray();
		
		++nombreCoup;
		
		for(int i=0;i<codeSecret.length();i++) {


			if(tabProp[i]>tabCode[i]) {
				tabMax[i] = tabProp[i] ;
				info = Character.getNumericValue(tabProp[i]); 
				tabProp[i] = intToChar(info/2);

				if( tabProp[i]>tabCode[i]) {
					tabMax[i] =  tabProp[i];
				}
			}
			else if(tabProp[i]<tabCode[i]) {
				tabMin[i] =  tabProp[i];
				info = Character.getNumericValue(tabProp[i]);
				tabProp[i] = intToChar(info+(info/2));

				if( tabProp[i]>tabCode[i]) {
					tabMax[i] = tabProp[i];

				}
			}
 
			resultProp = new String(tabProp);
			
		}
		dialog.setText(dialog.getText()+"C'est peut être : "+resultProp+"\n");
		++nombreCoup; // Fin de la première boucle.

		for(int i=0; i<codeSecret.length();i++) {
			if(tabProp[i]>tabCode[i]) {
				tabProp[i]= intToChar(tabMin[i] +(int)(Math.random()*(tabMax[i] - tabMin[i])));
				
			}
			else if(tabProp[i]<tabCode[i]){
				tabProp[i]= intToChar(tabMin[i] +(int)(Math.random()*(tabMax[i] - tabMin[i])));

			}
			resultProp = new String(tabProp);
			
			
		}
		dialog.setText(dialog.getText()+"Ou alors peut être : "+resultProp+"\n");
		++nombreCoup;// Fin de la deuxième boucle.
       
		do{
		for(int i=0; i<codeSecret.length();i++) {
			if(tabProp[i]>tabCode[i]) {
				--tabProp[i];
				
			}
			else if(tabProp[i]<tabCode[i]){
				++tabProp[i];

			}
			resultProp = new String(tabProp);
			
		
		}
		resultProp = new String(tabProp);
		
		++nombreCoup;// +1 nombre tentative tant que le code n'est pas trouvé.
		
		}while(!resultProp.equals(codeSecret));// Fin de la troisième boucle
		System.out.println(nombreCoup);
		dialog.setText(dialog.getText()+"Hum j'ai bien réflechi et \n ça doit être : "+resultProp+" ?\n"
				+ "J'ai réussi en "+nombreCoup+" tentatives");
	}

	public static void okClick(JTextField proposition, JLabel codeSecret, JPanel contentPane, JPanel boiteDialog, JPanel ordi,JPanel blocProposition, JLabel image) {

		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		bloc = reglage.getString("cases");
		int cases = Integer.parseInt(bloc);

		if(proposition.getText().length()<cases) {
			JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et dépasse la longueur du code secret
			joperreur.showMessageDialog(null,"Votre code est trop court !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
			blocProposition.setVisible(true);
		}
		else if(proposition.getText().length()>cases) {
			JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et la longueur du code tapé et inférieur au code secret
			joperreur.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);
			proposition.setText("");
			blocProposition.setVisible(true);
		}
		else if(proposition.getText().length()==cases) {
			boiteDialog.setVisible(false);
			codeSecret.setText(proposition.getText());
			proposition.setText("");
			proposition.setFont(impact);
			ordi.setVisible(true);	
			image.setIcon(ordi1);
		}

	}
	/**
	 * Méthode qui permet le passage d'un int en char
	 * @param i
	 * @return
	 */
	private static char intToChar(int i) {
		String s = ""+i;
		return s.charAt(0);
	}
}
