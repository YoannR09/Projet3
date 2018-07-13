package fr.yoannroche.projet3.mastermind.model;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.mastermind.view.Challenger;

public class ModelChallenger {
	private Challenger chal;
	private static ArrayList<Integer>tableauCodeCacher;
	private static CodeCacher code = new CodeCacher();


	public ModelChallenger() {
			
		
	}
	public static void check(String proposition, int tentative) {
		ArrayList<Integer> tableau = code.getTableauChiffre();
		
		
		char[] charDuTableau = proposition.toCharArray();
		
		

		
		System.out.println(tableau);
		System.out.println(tentative);
		
		for(int i = 0 ; i<tableau.size() ; i++ ) {
			
		}
		
		
	}
	public static void tentativeCheck(JLabel test1,JLabel test2,JLabel test3,JLabel test4,JLabel test5,JLabel test6,JLabel test7,JLabel test8,int tentative,JLabel proposition) {
		 if(tentative==0) {
   		  test1.setText(proposition.getText());
   		  test1.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==1) {
   		  test2.setText(proposition.getText());
   		 test2.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==2) {
   		  test3.setText(proposition.getText());
   		 test3.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==3) {
   		  test4.setText(proposition.getText());
   		 test4.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==4) {
   		  test5.setText(proposition.getText());
   		 test5.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==5) {
   		  test6.setText(proposition.getText());
   		 test6.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==6) {
   		  test7.setText(proposition.getText());
   		 test7.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==7) {
   		  test8.setText(proposition.getText());
   		 test8.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
	}

	public ArrayList<Integer> getChiffreCacher() {
		return this.tableauCodeCacher;
	}
}