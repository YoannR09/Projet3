package fr.yoannroche.projet3.mastermind.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Challenger extends JFrame {

	private JPanel contentPane = new JPanel();
	private JPanel blocProposition = new JPanel();
	private JPanel blocTest = new JPanel();
	private JPanel blocGauche = new JPanel();
	private JPanel blocDroit = new JPanel();
	private JLabel proposition = new JLabel();
	private JPanel espace = new JPanel();
	Font impact = new Font ("impact", 17,17);
	int  [] clavier = {1,2,3,4,5,6,7,8,9};
	JLabel [] testLabel = new JLabel[8];
	
	JButton [] button = new JButton[clavier.length];
	private JButton bouton[];
	private Component test;

	public Challenger() {
		this.setTitle("Challenger");
		this.setSize(400, 490);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		initRegle();
		initBlocGauche();
		initBlocDroit();
		initBlocProposition();
		initBlocTest();
	}

	private void initRegle() {
	JPanel blocRegle = new JPanel();
	blocRegle.setPreferredSize(new Dimension(300,40));
	 JLabel regle = new JLabel();
	 regle.setText("TESTSETSETSETSET");
	 blocRegle.add(regle);
	 contentPane.add(blocRegle);
	}

	private void initBlocTest() {
		

		blocTest.add(proposition);
		blocTest.setPreferredSize(new Dimension(130,40));
		blocTest.setBackground(Color.LIGHT_GRAY);
		blocTest.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		proposition.setForeground(Color.DARK_GRAY);
		proposition.setFont(impact);
		contentPane.add(blocTest);
		JButton ok = new JButton(" OK ");
		ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		JButton supprimer = new JButton(" Suppr ");
		supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		supprimer.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  proposition.setText("");
		      }  
		      });
		
		contentPane.add(supprimer);
		contentPane.add(ok);


	}

	private void initBlocDroit() {
		blocDroit.setPreferredSize(new Dimension(130,300));
		blocDroit.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocDroit.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		contentPane.add(blocDroit);
		/**
		 * Création d'un espace entre les différents blocs
		 */
		
		contentPane.add(espace);

	}

	private void initBlocGauche() {
		espace.setPreferredSize(new Dimension(400,15));
		espace.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		JLabel cases[] = new JLabel[9];
		JLabel test1 = new JLabel();
		JLabel test2 = new JLabel();
		JLabel test3 = new JLabel();
		JLabel test4 = new JLabel();
		JLabel test5 = new JLabel();
		JLabel test6 = new JLabel();
		JLabel test7 = new JLabel();
		JLabel test8 = new JLabel();
		cases[0]=test1;
		cases[1]=test2;
		cases[2]=test3;
		cases[3]=test4;
		cases[4]=test5;
		cases[5]=test6;
		cases[6]=test7;
		cases[7]=test8;
		for(int i = 0 ; i<8; i++){
			
 
			blocGauche.add(cases[i]);
			cases[i].setPreferredSize(new Dimension(130,31));
			cases[i].setBorder(BorderFactory.createMatteBorder(2, 5, 2, 5,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			cases[i].setOpaque(true);
			cases[i].setBackground(Color.LIGHT_GRAY);
			
			
		}
		
		blocGauche.setPreferredSize(new Dimension(150,300));
		blocGauche.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocGauche.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		contentPane.add(blocGauche);

	}

	private void initBlocProposition() {

		blocProposition.setPreferredSize(new Dimension(160,65));
		blocProposition.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocProposition.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		this.bouton = new JButton[9];
		int i = 0;
		for(int c : clavier){
			this.bouton[i] = new JButton(String.valueOf(c).toUpperCase());

			bouton[i].setPreferredSize(new Dimension(23,23));
			bouton[i].setBackground(Color.DARK_GRAY);
			bouton[i].setBorder(BorderFactory.createLineBorder(Color.black));
			bouton[i].setForeground(Color.white);
			bouton[i].addMouseListener(new SourisListener());

			blocProposition.add(bouton[i]).setEnabled(true);
			i++;
		}
		contentPane.add(blocProposition);

	}
	
	/**
	 * Class qui permet la modification de l'affichage lors du passage de souris sur le clavier de chiffre.
	 * Lors du clique l'ajout d'un chiffre dans le JLabel proposition se fait.
	 * @author El-ra
	 *
	 */
	class SourisListener implements MouseListener {


		public void mouseClicked(MouseEvent arg0) {

			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			proposition.setText(proposition.getText()+entrer);
			
		}

		public void mouseEntered(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2,Color.getHSBColor(0.534f, 0.45f, 0.94f)));
		}

		public void mouseExited(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.black));
			if(((JButton)arg0.getSource()).isEnabled()==false) {
				((JButton)arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.orange));
			}
		}

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {
			
		}

	}

}
