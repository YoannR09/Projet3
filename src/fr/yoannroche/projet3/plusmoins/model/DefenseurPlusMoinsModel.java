package fr.yoannroche.projet3.plusmoins.model;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DefenseurPlusMoinsModel {

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
	static Font impact = new Font ("impact", 17,17);
	Font arial = new Font ("arial", 12,12);
	Font arial2 = new Font ("arial", 10,10);
	static ImageIcon ordi1 = new ImageIcon("images/ordi1.png");

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
}
