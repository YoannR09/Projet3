package fr.yoannroche.projet3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import fr.yoannroche.projet3.mastermind.view.FenetreMenuMaster;
import fr.yoannroche.projet3.mastermind.view.RegleMastermind;
import fr.yoannroche.projet3.plusmoins.view.FenetreMenuPlusMoins;
import fr.yoannroche.projet3.plusmoins.view.ReglePlusMoins;
/**
 * 
 * @author El-ra
 *
 */
public class Lancement extends JFrame{
	
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	BeanReglage bean = new BeanReglage();
	private JPanel contentPane = new JPanel();
	private JPanel blocJeu = new JPanel();
	private JToolBar menuBar = new JToolBar();
	private JButton jeu1 = new JButton(new ImageIcon("images/master.png"));
	private JButton jeu2 = new JButton(new ImageIcon("images/plusmoins.png"));
	private JButton infos = new JButton(" ? ");
	JLabel infosJeu = new JLabel();
	JButton masterMind = new JButton("  MasterMind  ");
	JButton plusMoins = new JButton ("  Plus ou Moins  ");
	private Dimension dim = new Dimension(400,100);
	private Dimension dim2 = new Dimension(50,55);
	private Dimension dim3 = new Dimension(100,30);
	Font impact = new Font ("impact", 15,15);
	Font impact2 = new Font ("impact", 13,13);
	
	public Lancement() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(" Projet 3 ");
		this.setSize(555, 315);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		
		
		initEcran();
		initMenuBar();
	}


	private void initMenuBar() {
		
		menuBar.addSeparator();
		JPanel espace = new JPanel();
		espace.setPreferredSize(dim2);
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));	
		masterMind.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		masterMind.setBorder(BorderFactory.createLineBorder(Color.black));
		masterMind.setPreferredSize(dim3);
		masterMind.addMouseListener(new SourisListener2());
		masterMind.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  ((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
					RegleMastermind regle = new RegleMastermind();	
					regle.setVisible(true);
			      }
			    });
		
		
		
		plusMoins.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		plusMoins.setBorder(BorderFactory.createLineBorder(Color.black));
		plusMoins.setPreferredSize(dim3);
		plusMoins.addMouseListener(new SourisListener2());
		plusMoins.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  ((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
					ReglePlusMoins regle = new ReglePlusMoins();	
					regle.setVisible(true);
			      }
			    });
		
		JPanel espaceInfosJeu = new JPanel();
		espaceInfosJeu.setPreferredSize(new Dimension(10,30));
		espaceInfosJeu.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		infosJeu.setText(" Informations des jeux");
		infosJeu.setForeground(Color.WHITE);
		infosJeu.setFont(impact);
		menuBar.setPreferredSize(new Dimension(545,51));
		menuBar.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
		menuBar.add(infosJeu);
		
		menuBar.add(espaceInfosJeu);
		menuBar.add(masterMind);
		menuBar.add(plusMoins);
		menuBar.add(espace);
		menuBar.add(infos);
		infos.setPreferredSize(new Dimension(30,40));
		infos.setBackground(Color.getHSBColor(0.112f, 066f, 0.74f));
		infos.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 056f, 0.64f)));
		infos.addMouseListener(new SourisListener2());
		contentPane.add(menuBar);
	}



	private void initEcran() {
		blocJeu.setPreferredSize(new Dimension(420,218));
		blocJeu.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
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
		jeu2.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  ((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
					FenetreMenuPlusMoins menu = new FenetreMenuPlusMoins();	
					menu.setVisible(true);
			      }
			    });




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
			jeu2.setBorder(BorderFactory.createMatteBorder(10,0,10,0 ,Color.getHSBColor(0.534f, 0.35f, 0.34f)));
			}
			if(arg0.getSource()==jeu2) {
			jeu2.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0,Color.getHSBColor(0.534f, 0.45f, 0.94f)));
			jeu1.setBorder(BorderFactory.createMatteBorder(10,0,10,0 ,Color.getHSBColor(0.534f, 0.35f, 0.34f)));
			}


		}
		public void mouseExited(MouseEvent arg0) {
			jeu1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			jeu1.setSize(dim);
			jeu2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			jeu2.setSize(dim);


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
			if(arg0.getSource()==plusMoins) {
				plusMoins.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.getHSBColor(0.534f, 0.25f, 0.94f)));
				plusMoins.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
			}
			if(arg0.getSource()==infos) {
				infos.setBackground(Color.getHSBColor(0.112f, 076f, 0.84f));
				infos.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 066f, 0.74f)));
			}
			
			
		}
		public void mouseExited(MouseEvent arg0) {
			masterMind.setBorder(BorderFactory.createLineBorder(Color.black));
			masterMind.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
			plusMoins.setBorder(BorderFactory.createLineBorder(Color.black));
			plusMoins.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
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
