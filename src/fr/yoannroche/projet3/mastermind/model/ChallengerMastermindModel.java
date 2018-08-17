package fr.yoannroche.projet3.mastermind.model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.mastermind.control.Control;

public class ChallengerMastermindModel{
	
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int cases = Integer.parseInt(reglage.getString("cases"));
	private int tentative = Integer.parseInt(reglage.getString("tentatives"));
	private JLabel tabView [] = new JLabel[cases];
	private int nombreTentative=0;
	Generateur gen = new Generateur();
	Control control = new Control();
	private int [] couleurs = gen.getCouleurs();
	int proposition =0;
	private int changer = 0;
	private int placer = 0;
	
	

	public void okClick(int nombreClick,JPanel[] blocTentative, JLabel[] propositionIcon, JLabel[] blocIndices, int changer, int placer) {
		
		blocTentative[nombreTentative].removeAll();
		for(int i=0;i<cases;i++) {
			
			tabView[i]= createJLabel();	
			blocTentative[nombreTentative].add(tabView[i]);	
			tabView[i].setIcon(propositionIcon[i].getIcon());
			control.couleurChiffre(propositionIcon[i].getIcon().toString(), null);
			checkPosi(control.getProposition(),blocIndices,couleurs,nombreTentative);
			System.out.println(control.getProposition());
			
		}
		blocTentative[nombreTentative].revalidate();
		++nombreTentative;
	}
	
	public void dev(JPanel content){
		for(int i=0;i<cases;i++) {
			control.chiffreCouleur(couleurs[i], content);
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

	public void blocJeuSize(JPanel blocJeu) {
		if(tentative==4) {
			blocJeu.setPreferredSize(new Dimension(350,190));
		}
		if(tentative==5) {
			blocJeu.setPreferredSize(new Dimension(350,190));
		}
		if(tentative==6) {
			blocJeu.setPreferredSize(new Dimension(350,235));
		}
		if(tentative==7) {
			blocJeu.setPreferredSize(new Dimension(350,275));
		}
		if(tentative==8) {
			blocJeu.setPreferredSize(new Dimension(350,315));
		}
		
		
	}
	public void checkPosi(int proposition, JLabel[] blocIndices, int[] couleurs2,int nombreTentative){
		for(int i = 0;i<cases;i++) {
			if(proposition==couleurs2[i]) {
				++changer;
			}
		}
		blocIndices[nombreTentative].setText(" P : "+placer+"  C : "+changer+" ");
	}
	public void checkOk(int proposition, JLabel[] blocIndices, int[] couleurs2,int nombreTentative){
		for(int i = 0;i<cases;i++) {
			if(proposition==couleurs2[i]) {
				
				blocIndices[nombreTentative].setText(" P");
				
			}
		}
	}
	
}
