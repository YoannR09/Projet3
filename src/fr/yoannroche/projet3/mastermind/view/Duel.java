package fr.yoannroche.projet3.mastermind.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.yoannroche.projet3.mastermind.model.ModelChallenger;
import fr.yoannroche.projet3.mastermind.model.ModelDefenseur;
import fr.yoannroche.projet3.mastermind.model.ModelDuel;
import fr.yoannroche.projet3.mastermind.model.Reglage;
import fr.yoannroche.projet3.mastermind.view.Challenger.SourisListener;

public class Duel extends JFrame{

	private JPanel contentPane = new JPanel();
	private JPanel blocGaucheT= new JPanel();
	private int tentative = 0;
	Font impact = new Font ("impact", 17,17);
	Font arial = new Font ("arial", 9,9);
	JLabel cases[] = new JLabel[9];
	JLabel test1 = new JLabel();
	JLabel test2 = new JLabel();
	JLabel test3 = new JLabel();
	JLabel test4 = new JLabel();
	JLabel test5 = new JLabel();
	JLabel test6 = new JLabel();
	JLabel test7 = new JLabel();
	JLabel test8 = new JLabel();
	JLabel find = new JLabel ();
	JLabel find2 = new JLabel ();
	static ImageIcon ordi0 = new ImageIcon("images/ordi0.png");
	JButton retour = new JButton("Retour");
	JButton ok = new JButton(" OK ");
	JButton supprimer = new JButton(" Suppr ");
	JLabel clavierText = new JLabel();
	JLabel caseOrdi[] = new JLabel[9];
	JLabel testOrdi1 = new JLabel();
	JLabel testOrdi2 = new JLabel();
	JLabel testOrdi3 = new JLabel();
	JLabel testOrdi4 = new JLabel();
	JLabel testOrdi5 = new JLabel();
	JLabel testOrdi6 = new JLabel();
	JLabel testOrdi7 = new JLabel();
	JLabel testOrdi8 = new JLabel();
	JTextArea dialog = new JTextArea();
	JLabel image = new JLabel();
	int  [] clavier = {0,1,2,3,4,5,6,7,8,9};
	JButton [] button = new JButton[clavier.length];
	private JButton bouton[];
	private JPanel blocProposition = new JPanel();
	private JLabel proposition = new JLabel();
	private int nbreCases = 0;
	private int essaie = 0;
	private Reglage reglage = new Reglage(nbreCases, essaie);
	


	public Duel() {

		this.setTitle("Duel");
		this.setSize(650, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));

		initRegle();
		initBlocGauche();
		blocJoueur1();
		initEspace();
		blocJoueur2();
		initBlocOrdinateur();
		initBlocProposition();
		initBlocTest();

	}

	private void initEspace() {
		JPanel espaceJoueur = new JPanel();
		espaceJoueur.setPreferredSize(new Dimension(20,200));
		espaceJoueur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		espaceJoueur.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		contentPane.add(espaceJoueur);

	}

	private void initRegle() {
		JPanel espaceRetour = new JPanel ();
		espaceRetour.setPreferredSize(new Dimension(550,20));
		espaceRetour.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		retour.addMouseListener(new SourisListener());
		retour.setForeground(Color.white);
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				FenetreMenuMaster menu = new FenetreMenuMaster();	
				menu.setVisible(true);
			}
		});
		JPanel blocRegle = new JPanel();
		blocRegle.setPreferredSize(new Dimension(750,40));
		JLabel regle = new JLabel();
		regle.setText("Vous et l'ordinateur avez " + essaie + " tentative pour trouver le code secret à " + nbreCases + " de l'autre joueur" );
		regle.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);

		contentPane.add(blocRegle);
	}



	private void initBlocGauche() {

		blocGaucheT.setPreferredSize(new Dimension(130,300));
		blocGaucheT.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocGaucheT.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
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


			blocGaucheT.add(cases[i]);
			cases[i].setPreferredSize(new Dimension(80,31));
			cases[i].setBorder(BorderFactory.createMatteBorder(5, 1, 5, 1,Color.getHSBColor(0.534f, 0.45f, 0.14f)));
			cases[i].setOpaque(true);
			cases[i].setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
			cases[i].setFont(impact);
			cases[i].setForeground(Color.getHSBColor(0.095f, 0.75f, 0.86f));
			cases[i].setText("P : " + 0 + "    B : "+0);
			cases[i].setHorizontalAlignment(JLabel.CENTER);
			contentPane.add(blocGaucheT);

		}
	}
	private void blocJoueur1() {
		JPanel tentativeOrdinateur1 = new JPanel ();
		tentativeOrdinateur1.setPreferredSize(new Dimension(130,300));
		tentativeOrdinateur1.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		tentativeOrdinateur1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		contentPane.add(tentativeOrdinateur1);

		cases[0]=test1;
		cases[1]=test2;
		cases[2]=test3;
		cases[3]=test4;
		cases[4]=test5;
		cases[5]=test6;
		cases[6]=test7;
		cases[7]=test8;
		for(int i = 0 ; i<8; i++){


			tentativeOrdinateur1.add(cases[i]);
			cases[i].setPreferredSize(new Dimension(80,31));
			cases[i].setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1,Color.getHSBColor(0.534f, 0.45f, 0.14f)));
			cases[i].setOpaque(true);
			cases[i].setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
			cases[i].setFont(impact);
			cases[i].setForeground(Color.getHSBColor(0.095f, 0.75f, 0.86f));
			cases[i].setHorizontalAlignment(JLabel.CENTER);



		}



	}


	private void blocJoueur2() {
		JPanel tentativeOrdinateur = new JPanel ();
		tentativeOrdinateur.setPreferredSize(new Dimension(130,300));
		tentativeOrdinateur.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		tentativeOrdinateur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		contentPane.add(tentativeOrdinateur);

		cases[0]=testOrdi1;
		cases[1]=testOrdi2;
		cases[2]=testOrdi3;
		cases[3]=testOrdi4;
		cases[4]=testOrdi5;
		cases[5]=testOrdi6;
		cases[6]=testOrdi7;
		cases[7]=testOrdi8;
		for(int i = 0 ; i<8; i++){


			tentativeOrdinateur.add(cases[i]);
			cases[i].setPreferredSize(new Dimension(80,31));
			cases[i].setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1,Color.getHSBColor(0.534f, 0.45f, 0.14f)));
			cases[i].setOpaque(true);
			cases[i].setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
			cases[i].setFont(impact);
			cases[i].setForeground(Color.getHSBColor(0.095f, 0.75f, 0.86f));
			cases[i].setHorizontalAlignment(JLabel.CENTER);



		}

		find.setOpaque(true);
		find.setPreferredSize(new Dimension(90,25));
		find.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1,Color.getHSBColor(0.534f, 0.45f, 0.14f)));
		find.setBackground(Color.getHSBColor(0.114f, 0.45f, 0.94f));
		find.setHorizontalAlignment(JLabel.CENTER);
		tentativeOrdinateur.add(find);

	}
	private void initBlocOrdinateur() {
		JPanel ordinateur = new JPanel();
		ordinateur.setPreferredSize(new Dimension (180,255));
		ordinateur.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		ordinateur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		JPanel imageOrdi = new JPanel();
		imageOrdi.setPreferredSize(new Dimension(170,150));
		imageOrdi.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		imageOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		imageOrdi.add(image);
		image.setIcon(ordi0);
		ordinateur.add(imageOrdi);

		dialog.setEditable(false);
		dialog.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		dialog.setPreferredSize(new Dimension(170,50));
		dialog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		ordinateur.add(dialog);
		contentPane.add(ordinateur);
		find2.setOpaque(true);
		find2.setPreferredSize(new Dimension(90,30));
		find2.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1,Color.getHSBColor(0.534f, 0.45f, 0.14f)));
		find2.setBackground(Color.getHSBColor(0.114f, 0.45f, 0.94f));
		find2.setHorizontalAlignment(JLabel.CENTER);
		find2.setText("????");
		ordinateur.add(find2);

	}
	private void initBlocProposition() {

		blocProposition.setPreferredSize(new Dimension(160,65));
		blocProposition.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocProposition.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		this.bouton = new JButton[10];
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
		JPanel espaceClavier = new JPanel();
		espaceClavier.setPreferredSize(new Dimension(600,18));
		espaceClavier.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		espaceClavier.add(clavierText);
		clavierText.setText("Entrez votre code secret que l'ordinateur doit découvrir");
		clavierText.setForeground(Color.white);
		clavierText.setHorizontalAlignment(JLabel.CENTER);
		clavierText.setFont(arial);
		contentPane.add(espaceClavier);
		contentPane.add(blocProposition);

	}

	private void initBlocTest() {


		JPanel blocTest = new JPanel();
		blocTest.add(proposition);
		blocTest.setPreferredSize(new Dimension(130,40));
		blocTest.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		blocTest.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		proposition.setForeground(Color.DARK_GRAY);
		proposition.setFont(impact);
		contentPane.add(blocTest);

		ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		ok.addMouseListener(new SourisListener());
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				find.setText(proposition.getText());
				clavierText.setText("");
				++tentative;
				ModelDuel.tentativeCheck(test1,test2,test3,test4,test5,test6,test7,test8, tentative,proposition);

				ModelDuel.check(proposition.getText(), tentative);
				ModelDuel.propositionOrdi(testOrdi1, testOrdi2, testOrdi3, testOrdi4, testOrdi5, testOrdi6, testOrdi7, testOrdi8,dialog,image,tentative);

				proposition.setText("");


			}  
		});



		supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		supprimer.addMouseListener(new SourisListener());
		supprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				proposition.setText("");
			}  
		});
		find.setOpaque(true);
		find.setPreferredSize(new Dimension(100,25));
		find.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1,Color.getHSBColor(0.534f, 0.45f, 0.14f)));
		find.setBackground(Color.getHSBColor(0.114f, 0.45f, 0.94f));
		find.setFont(impact);
		find.setHorizontalAlignment(JLabel.CENTER);


		contentPane.add(supprimer);
		contentPane.add(ok);
		contentPane.add(find);


	}
	class SourisListener implements MouseListener {


		public void mouseClicked(MouseEvent arg0) {

			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			proposition.setText(proposition.getText()+entrer);

		}

		public void mouseEntered(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,Color.white));
			if(arg0.getSource()==retour) {
				retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
			if(arg0.getSource()==ok) {
				ok.setBackground(Color.getHSBColor(0.345f, 0.58f, 0.88f));
				ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.64f)));
			}
			if(arg0.getSource()==supprimer) {
				supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.64f)));
				supprimer.setBackground(Color.getHSBColor(0.534f, 0.65f, 0.84f));
			}
		}

		public void mouseExited(MouseEvent arg0) {
			
			((JButton)arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.black));
			if(((JButton)arg0.getSource()).isEnabled()==false) {
				((JButton)arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.orange));
			}
			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			retour.setForeground(Color.white);
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
			ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		}
		

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {
			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
			ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		}

	}

}
