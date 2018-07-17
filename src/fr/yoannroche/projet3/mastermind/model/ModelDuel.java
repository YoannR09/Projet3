package fr.yoannroche.projet3.mastermind.model;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ModelDuel {
	
	
	private static CodeCacher code = new CodeCacher();
	private static ArrayList<Integer>tableauCodeCacher;
	 static ImageIcon ordi1 = new ImageIcon("images/ordi1.png");
	 static ImageIcon ordi2 = new ImageIcon("images/ordi2.png");
	 static ImageIcon ordi3 = new ImageIcon("images/ordi3.png");
	 static ImageIcon ordi4 = new ImageIcon("images/ordi4.png");
	 static ImageIcon ordi5 = new ImageIcon("images/ordi5.png");
	 static ImageIcon ordi6 = new ImageIcon("images/ordi6.png");
	 static ImageIcon ordi7 = new ImageIcon("images/ordi7.png");
	 static ImageIcon ordi8 = new ImageIcon("images/ordi8.png");
	
	public ModelDuel() {
		
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
		
		 if(tentative==2) {
   		  test1.setText(proposition.getText());
   		  test1.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==3) {
   		  test2.setText(proposition.getText());
   		 test2.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==4) {
   		  test3.setText(proposition.getText());
   		 test3.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==5) {
   		  test4.setText(proposition.getText());
   		 test4.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==6) {
   		  test5.setText(proposition.getText());
   		 test5.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==7) {
   		  test6.setText(proposition.getText());
   		 test6.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==8) {
   		  test7.setText(proposition.getText());
   		 test7.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
   	  if(tentative==9) {
   		  test8.setText(proposition.getText());
   		 test8.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
   	  }
	}
	public static <JTexteArea> void propositionOrdi(JLabel pro1,JLabel pro2,JLabel pro3,JLabel pro4,JLabel pro5,JLabel pro6,JLabel pro7,JLabel pro8,JTextArea dialog,JLabel image,int tentative) {

		if(tentative==2) {
		proposition1(pro1,dialog,image);
		}
		if(tentative==3) {
		proposition2(pro2,dialog,image);	
		}
		if(tentative==4) {
		proposition3(pro3,dialog,image);
		}
		if(tentative==5) {
		proposition4(pro4,dialog,image);
		}
		if(tentative==6) {
		proposition5(pro5,dialog,image);
		}
		if(tentative==7) {
		proposition6(pro6,dialog,image);
		}
		if(tentative==8) {
		proposition7(pro7,dialog,image);
		}
		if(tentative==9) {
		proposition8(pro8,dialog,image);
		}
		
}
	
	private static void proposition1(JLabel pro1,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro1.setText(code.getTableauChiffre().toString());
		pro1.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
		dialog.setText("  Hhmmmmm je dirai \n  "+ code.getTableauChiffre().toString() );
		image.removeAll();
		image.setIcon(ordi1);
		image.revalidate();

	}
	private static void proposition2(JLabel pro2,JTextArea dialog, JLabel image) {

		CodeCacher code = new CodeCacher();
		pro2.setText(code.getTableauChiffre().toString());
		pro2.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
		dialog.setText("  Ou alors  " + code.getTableauChiffre().toString());
		image.removeAll();
		image.setIcon(ordi2);
		image.revalidate();




	}
	private static void proposition3(JLabel pro3,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro3.setText(code.getTableauChiffre().toString());
		pro3.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
		dialog.setText("  Ca doit surement \n être  " + code.getTableauChiffre().toString());
		image.removeAll();
		image.setIcon(ordi3);
		image.revalidate();

	}
	private static void proposition4(JLabel pro4,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro4.setText(code.getTableauChiffre().toString());
		pro4.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
		dialog.setText("  Je m'approche \n ca doit être  "+ code.getTableauChiffre().toString() );
		image.removeAll();
		image.setIcon(ordi4);
		image.revalidate();
	}
	private static void proposition5(JLabel pro5,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro5.setText(code.getTableauChiffre().toString());
		pro5.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
		dialog.setText("  Peut être  "+ code.getTableauChiffre().toString() );
		image.removeAll();
		image.setIcon(ordi5);
		image.revalidate();

	}
	private static void proposition6(JLabel pro6,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro6.setText(code.getTableauChiffre().toString());
		pro6.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
		dialog.setText("  Hmmmm  "+ code.getTableauChiffre().toString() +" \n non toujours pas?" );
		image.removeAll();
		image.setIcon(ordi6);
		image.revalidate();

	}
	private static void proposition7(JLabel pro7,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro7.setText(code.getTableauChiffre().toString());
		pro7.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
		dialog.setText("  Ah je l'ai  "+ code.getTableauChiffre().toString() );
		image.removeAll();
		image.setIcon(ordi7);
		image.revalidate();

	}
	private static void proposition8(JLabel pro8,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro8.setText(code.getTableauChiffre().toString());
		pro8.setBackground(Color.getHSBColor(0.095f, 0.05f, 0.96f));
		dialog.setText("  Ma dernière chance \n je vais dire  "+ code.getTableauChiffre().toString() );
		image.removeAll();
		image.setIcon(ordi8);
		image.revalidate();

	}

	public ArrayList<Integer> getChiffreCacher() {
		return this.tableauCodeCacher;
	}

}
