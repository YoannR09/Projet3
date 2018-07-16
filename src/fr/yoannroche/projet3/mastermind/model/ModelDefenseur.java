package fr.yoannroche.projet3.mastermind.model;

import java.sql.Time;
import java.util.ArrayList;

import javax.management.timer.Timer;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;



public class ModelDefenseur {

	private JProgressBar bar;



	public ModelDefenseur() {	

	}


	public static <JTexteArea> void propositionOrdi(JLabel pro1,JLabel pro2,JLabel pro3,JLabel pro4,JLabel pro5,JLabel pro6,JLabel pro7,JLabel pro8,JTextArea dialog,JLabel image) {

				proposition1(pro1,dialog,image);
				try {
					Thread t = new Thread();
					t.sleep(5000);
				}catch(Exception e) {
					e.printStackTrace();
				}
				proposition2(pro2,dialog,image);
				try {
					Thread C = new Thread();
					C.sleep(5000);
				}catch(Exception e) {
					e.printStackTrace();
				}

			
				proposition3(pro3,dialog,image);
				proposition4(pro4,dialog,image);
				proposition5(pro5,dialog,image);
				proposition6(pro6,dialog,image);
				proposition7(pro7,dialog,image);
				proposition8(pro8,dialog,image);
				
	}


	private static void sleep() {
		try {
			Thread t = new Thread();
			t.sleep(5000);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}


	private static void proposition1(JLabel pro1,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro1.setText(code.getTableauChiffre().toString());
		dialog.setText("  Hhmmmmm je dirai  "+ code.getTableauChiffre().toString() );

	}
	private static void proposition2(JLabel pro2,JTextArea dialog, JLabel image) {

		CodeCacher code = new CodeCacher();
		pro2.setText(code.getTableauChiffre().toString());
		dialog.setText("  Ou alors  " + code.getTableauChiffre().toString());




	}
	private static void proposition3(JLabel pro3,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro3.setText(code.getTableauChiffre().toString());
		dialog.setText("  Ca doit surement \n être  " + code.getTableauChiffre().toString());

	}
	private static void proposition4(JLabel pro4,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro4.setText(code.getTableauChiffre().toString());
		dialog.setText("  Je m'approche \n ca doit être  "+ code.getTableauChiffre().toString() );

	}
	private static void proposition5(JLabel pro5,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro5.setText(code.getTableauChiffre().toString());
		dialog.setText("  Peut être  "+ code.getTableauChiffre().toString() );

	}
	private static void proposition6(JLabel pro6,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro6.setText(code.getTableauChiffre().toString());
		dialog.setText("  Hmmmm  "+ code.getTableauChiffre().toString() +" \n non toujours pas?" );

	}
	private static void proposition7(JLabel pro7,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro7.setText(code.getTableauChiffre().toString());
		dialog.setText("  Ah je l'ai  "+ code.getTableauChiffre().toString() );

	}
	private static void proposition8(JLabel pro8,JTextArea dialog, JLabel image) {


		CodeCacher code = new CodeCacher();
		pro8.setText(code.getTableauChiffre().toString());
		dialog.setText("  Ma dernière chance \n je vais dire  "+ code.getTableauChiffre().toString() );

	}

}
