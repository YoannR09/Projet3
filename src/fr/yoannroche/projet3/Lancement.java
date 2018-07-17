package fr.yoannroche.projet3;

import java.awt.BorderLayout;
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
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import fr.yoannroche.projet3.mastermind.model.Reglage;
import fr.yoannroche.projet3.mastermind.view.FenetreMenuMaster;

public class Lancement extends JFrame{

	private JPanel contentPane = new JPanel();
	private JPanel blocJeu = new JPanel();
	private JMenuBar menuBar = new JMenuBar();
	private JButton jeu1 = new JButton(new ImageIcon("images/fond1.png"));
	private JButton jeu2 = new JButton(new ImageIcon("images/fond2.png"));
	private JButton jeu3 = new JButton(new ImageIcon("images/fond3.png"));
	private JButton infos = new JButton(" ? ");
	JLabel infosJeu = new JLabel();
	JButton masterMind = new JButton(" MasterMind ");
	JButton jeuT2 = new JButton ("  Jeu 2  ");
	JButton jeuT3 = new JButton ("  Jeu 3  ");
	private Dimension dim = new Dimension(400,100);
	private Dimension dim2 = new Dimension(50,55);
	private Dimension dim3 = new Dimension(100,30);
	private Lancement l;
	Font impact = new Font ("impact", 15,15);
	Font impact2 = new Font ("impact", 13,13);
	private int tentative;
	private int cases;
	Reglage reglage = new Reglage(cases, tentative);


	public Lancement() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(" Projet 3 ");
		this.setSize(600, 450);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		JPanel espaceTop = new JPanel();
		JLabel choix = new JLabel();
		choix.setText("  Choisissez un jeu parmis ces 3 ");
		choix.setFont(impact2);
		espaceTop.add(choix);
		espaceTop.setPreferredSize(new Dimension(600,25));
		choix.setForeground(Color.DARK_GRAY);
		espaceTop.setBackground(Color.ORANGE);
		espaceTop.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(espaceTop);
		
		initEcran();
		initMenuBar();
	}


	private void initMenuBar() {
		
		
		JPanel espace = new JPanel();
		espace.setPreferredSize(dim2);
		espace.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		masterMind.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		masterMind.setBorder(BorderFactory.createLineBorder(Color.black));
		masterMind.setPreferredSize(dim3);
		masterMind.addMouseListener(new SourisListener2());
		
		
		jeuT2.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		jeuT2.setBorder(BorderFactory.createLineBorder(Color.black));
		jeuT2.setPreferredSize(dim3);
		jeuT2.addMouseListener(new SourisListener2());
		
		
		jeuT3.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		jeuT3.setBorder(BorderFactory.createLineBorder(Color.black));
		jeuT3.setPreferredSize(dim3);
		jeuT3.addMouseListener(new SourisListener2());
		
		
		infosJeu.setText("  Informations des jeux     ");
		infosJeu.setForeground(Color.WHITE);
		infosJeu.setFont(impact);
		menuBar.setPreferredSize(new Dimension(595,51));
		menuBar.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
		menuBar.add(infosJeu);
		menuBar.add(masterMind);
		menuBar.add(jeuT2);
		menuBar.add(jeuT3);
		menuBar.add(espace);
		menuBar.add(infos);
		infos.setPreferredSize(new Dimension(30,40));
		infos.setBackground(Color.getHSBColor(0.112f, 066f, 0.74f));
		infos.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 056f, 0.64f)));
		infos.addMouseListener(new SourisListener2());
		contentPane.add(menuBar);
	}



	private void initEcran() {
		blocJeu.setPreferredSize(new Dimension(420,325));
		blocJeu.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		blocJeu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.black));

		blocJeu.add(jeu1,BorderLayout.CENTER);
		jeu1.setPreferredSize(dim);
		jeu1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		jeu1.addMouseListener(new SourisListener());
		jeu1.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  ((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
					FenetreMenuMaster menu = new FenetreMenuMaster();	
					menu.setVisible(true);
			      }
			    });



		blocJeu.add(jeu2,BorderLayout.CENTER);
		jeu2.setPreferredSize(dim);
		jeu2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		jeu2.addMouseListener(new SourisListener());



		blocJeu.add(jeu3,BorderLayout.CENTER);
		jeu3.setPreferredSize(dim);
		jeu3.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		jeu3.addMouseListener(new SourisListener());



		contentPane.add(blocJeu);

	}

	public static void main(String[] args) {
		Lancement l = new Lancement();
		l.setVisible(true);

	}

	class SourisListener implements MouseListener{


		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource()==jeu1) {
			jeu1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0,Color.getHSBColor(0.534f, 0.45f, 0.94f)));
			jeu2.setBorder(BorderFactory.createMatteBorder(10,0,10,0 ,Color.getHSBColor(0.534f, 0.25f, 0.24f)));
			jeu3.setBorder(BorderFactory.createMatteBorder(10,0,10,0 ,Color.getHSBColor(0.534f, 0.25f, 0.24f)));
			}
			if(arg0.getSource()==jeu2) {
			jeu2.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0,Color.getHSBColor(0.534f, 0.45f, 0.94f)));
			jeu1.setBorder(BorderFactory.createMatteBorder(10,0,10,0 ,Color.getHSBColor(0.534f, 0.25f, 0.24f)));
			jeu3.setBorder(BorderFactory.createMatteBorder(10,0,10,0 ,Color.getHSBColor(0.534f, 0.25f, 0.24f)));
			}
			if(arg0.getSource()==jeu3) {
			jeu3.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0,Color.getHSBColor(0.534f, 0.45f, 0.94f)));
			jeu2.setBorder(BorderFactory.createMatteBorder(10,0,10,0 ,Color.getHSBColor(0.534f, 0.25f, 0.24f)));
			jeu1.setBorder(BorderFactory.createMatteBorder(10,0,10,0 ,Color.getHSBColor(0.534f, 0.25f, 0.24f)));
			}


		}
		public void mouseExited(MouseEvent arg0) {
			jeu1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			jeu1.setSize(dim);
			jeu2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			jeu2.setSize(dim);
			jeu3.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			jeu3.setSize(dim);


		}
		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
		public void mouseClicked(MouseEvent arg0) {	

		}

	}
	class SourisListener2 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource()==masterMind) {
				masterMind.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.getHSBColor(0.534f, 0.25f, 0.94f)));
				masterMind.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
			}
			if(arg0.getSource()==jeuT2) {
				jeuT2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.getHSBColor(0.534f, 0.25f, 0.94f)));
				jeuT2.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
			}
			if(arg0.getSource()==jeuT3) {
				jeuT3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.getHSBColor(0.534f, 0.25f, 0.94f)));
				jeuT3.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
			}
			if(arg0.getSource()==infos) {
				infos.setBackground(Color.getHSBColor(0.112f, 076f, 0.84f));
				infos.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 066f, 0.74f)));
			}
			
			
		}
		public void mouseExited(MouseEvent arg0) {
			masterMind.setBorder(BorderFactory.createLineBorder(Color.black));
			masterMind.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
			jeuT2.setBorder(BorderFactory.createLineBorder(Color.black));
			jeuT2.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
			jeuT3.setBorder(BorderFactory.createLineBorder(Color.black));
		    jeuT3.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		    infos.setBackground(Color.getHSBColor(0.112f, 066f, 0.74f));
			infos.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 056f, 0.64f)));
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
