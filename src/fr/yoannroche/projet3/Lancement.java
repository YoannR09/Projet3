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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.yoannroche.projet3.mastermind.view.FenetreMenuMaster;
import fr.yoannroche.projet3.mastermind.view.RegleMastermind;
import fr.yoannroche.projet3.plusmoins.view.FenetreMenuPlusMoins;
import fr.yoannroche.projet3.plusmoins.view.ReglePlusMoins;
/**
 * Class qui gère l'affichage du lancement du jeu.
 * @author El-ra
 *
 */
public class Lancement extends JFrame{

	private JPanel				contentPane		= new JPanel();
	private JPanel				blocJeu			= new JPanel();
	private JToolBar			menuBar			= new JToolBar();
	private JButton				jeu1			= new JButton(new ImageIcon("images/master.png"));
	private JButton				jeu2			= new JButton(new ImageIcon("images/plusmoins.png"));
	private JButton				infos			= new JButton(" ? ");
	private JLabel				infosJeu		= new JLabel();
	private JButton				masterMind		= new JButton("  MasterMind  ");
	private JButton				plusMoins		= new JButton("  Plus ou Moins  ");
	private Dimension			dim				= new Dimension(400,100);
	private Dimension			dim2			= new Dimension(50,55);
	private Dimension			dim3			= new Dimension(100,30);
	private Font				impact			= new Font("impact", 15,15);
	private JButton				dev				= new JButton(" Réglage ");
	private BeanReglage			bean			;


	public Lancement(BeanReglage bean) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(" Projet 3 ");
		this.setSize(555, 315);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		this.bean = bean;

		initEcran();
		initMenuBar();
	}
	/**
	 * Méthode qui gère les barres d'infos.
	 */
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
				RegleMastermind regle = new RegleMastermind(bean);	
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
				ReglePlusMoins regle = new ReglePlusMoins(bean);	
				regle.setVisible(true);
			}
		});
		infosJeu.setText(" Règles : ");
		infosJeu.setForeground(Color.WHITE);
		infosJeu.setFont(impact);
		menuBar.setPreferredSize(new Dimension(545,51));
		menuBar.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
		menuBar.add(infosJeu);
		menuBar.add(masterMind);
		menuBar.add(plusMoins);
		menuBar.add(espace);
		menuBar.add(dev);
		menuBar.addSeparator();
		menuBar.add(infos);
		menuBar.addSeparator();
		infos.setPreferredSize(new Dimension(30,40));
		infos.setBackground(Color.getHSBColor(0.112f, 066f, 0.74f));
		infos.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 056f, 0.64f)));
		infos.addMouseListener(new SourisListener2());
		contentPane.add(menuBar);
		dev.setBackground(Color.getHSBColor(0.112f, 066f, 0.74f));
		dev.addMouseListener(new SourisListener2());
		dev.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 056f, 0.64f)));
		dev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				Reglage reglage = new Reglage(bean);
				reglage.setVisible(true);
			}

		});
	}
	/**
	 * Méthode qui gère l'affichage du menu pour choisir les jeux
	 */
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
				FenetreMenuMaster menu = new FenetreMenuMaster(bean);	
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
				FenetreMenuPlusMoins menu = new FenetreMenuPlusMoins(bean);	
				menu.setVisible(true);
			}
		});
		contentPane.add(blocJeu);
	}
	
	/**
	 * Méthode qui désigne cette class comme la Main.
	 * Définit les vauleurs dans le BeanReglage.
	 * @param args
	 */
	public static void main(String[] args) {

		final Logger logger	= LogManager.getLogger();
		BeanReglage bean = new BeanReglage();
		Lancement l = new Lancement(bean);
		l.setVisible(true);
		bean.setReglage(ResourceBundle.getBundle("Config"));
		bean.setCases(Integer.parseInt(bean.getReglage().getString("cases")));
		bean.setTentatives(Integer.parseInt(bean.getReglage().getString("tentatives")));
		bean.setCouleurs(Integer.parseInt(bean.getReglage().getString("couleur")));
		bean.setDev(Integer.parseInt(bean.getReglage().getString("dev")));
		logger.info(bean.getReglage().getString("main"));
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
			if(arg0.getSource()==dev) {
				dev.setBackground(Color.getHSBColor(0.112f, 076f, 0.84f));
				dev.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 066f, 0.74f)));
			}


		}
		public void mouseExited(MouseEvent arg0) {
			masterMind.setBorder(BorderFactory.createLineBorder(Color.black));
			masterMind.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
			plusMoins.setBorder(BorderFactory.createLineBorder(Color.black));
			plusMoins.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
			infos.setBackground(Color.getHSBColor(0.112f, 066f, 0.74f));
			infos.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 056f, 0.64f)));
			dev.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.112f, 056f, 0.64f)));

		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			dev.setBackground(Color.getHSBColor(0.141f, 0.74f, 0.87f));
		}
	}
}
