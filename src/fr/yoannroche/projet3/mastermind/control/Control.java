package fr.yoannroche.projet3.mastermind.control;

import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Control {

	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int cases = Integer.parseInt(reglage.getString("cases"));
	int proposition= 0;
	
	
	
	public void chiffreCouleur(int chiffre,JPanel content) {
	 JLabel [] indice = new JLabel[cases];
	 for(int i = 0;i<cases;i++) {
		if(chiffre==i) {
			indice[i]= createJLabel();		
			indice[i].setIcon(new ImageIcon("images/couleur/"+i+".png"));
			content.add(indice[i]);		
		}
	 }
	}
	public void couleurChiffre(String couleur,JPanel content) {
		
		 for(int i = 0;i<cases;i++) {
			if(couleur.equals("images/couleur/"+i+".png")) {
				setProposition(i);
			}
			
		 }
		}
	
	public JLabel createJLabel(){
        JLabel jl = new JLabel();
        return jl;
    }
 public JPanel createJPanel(){
        JPanel jp = new JPanel();
        return jp;
    }
 public int getProposition() {
		return proposition;
	}
	public void setProposition(int proposition) {
		this.proposition = proposition;
	}
}

