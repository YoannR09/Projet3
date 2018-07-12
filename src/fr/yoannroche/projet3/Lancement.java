package fr.yoannroche.projet3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Lancement extends JFrame{

	private JPanel contentPane = new JPanel();
	private JPanel blocJeu = new JPanel();
	private JMenuBar menuBar = new JMenuBar();
	private JButton jeu1 = new JButton(new ImageIcon("images/fond1.png"));
	private JButton jeu2 = new JButton(new ImageIcon("images/fond1.png"));
	private JButton jeu3 = new JButton(new ImageIcon("images/fond1.png"));
	private Dimension dim = new Dimension(400,100);
	private Dimension dim2 = new Dimension(250,535);


	public Lancement() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(" Projet 3 ");
		this.setLocationRelativeTo(null);
		this.setSize(600, 450);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		initMenuBar();
		initEcran();
	}


	private void initMenuBar() {
		menuBar.setPreferredSize(new Dimension(600,51));
		menuBar.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(menuBar);
	}



	private void initEcran() {
		blocJeu.setPreferredSize(new Dimension(420,320));
		blocJeu.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		blocJeu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.black));

		blocJeu.add(jeu1,BorderLayout.CENTER);
		jeu1.setPreferredSize(dim);
		jeu1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		jeu1.addMouseListener(new SourisListener());



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

}
