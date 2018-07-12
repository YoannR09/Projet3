package fr.yoannroche.projet3.mastermind.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import fr.yoannroche.projet3.Lancement;


public class FenetreMenuMaster extends JFrame{
	private JPanel contentPaneMenu = new JPanel();
	private JRadioButton mode1,mode2,mode3;
	private JPanel blocMode = new JPanel();
	Font police = new Font ("arial black", 12,12);
	Font impact = new Font ("impact", 17,17);
	private Dimension dim = new Dimension(150,50);
	private JButton lancer = new JButton("LANCER");
	private JButton retour = new JButton(new ImageIcon("images/back.png"));
	JTextArea regle = new JTextArea();
	
	
	public FenetreMenuMaster() {
		this.setTitle("Menu du MasterMind");
		this.setSize(550, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPaneMenu);
		contentPaneMenu.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		
		initMenu();

	}

	private void initMenu() {
		
		
		JPanel espace1 = new JPanel();
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(600,20));
		BorderLayout bl=new BorderLayout(100,100);
		FlowLayout fl=new FlowLayout(FlowLayout.CENTER,100,100);
		
		 mode1 = new JRadioButton("Mode Challenger");
		    mode1.setSelected(true);
		    mode2= new JRadioButton("Mode Défenseur");
		    mode3 = new JRadioButton("Mode Duel");
		    
		    ButtonGroup bg = new ButtonGroup();
		    bg.add(mode1);
		    bg.add(mode2);
		    bg.add(mode3);
		    
		    
		    blocMode.add(mode1);
		    mode1.setFont(impact);
		    mode1.setPreferredSize(dim);
		    mode1.setForeground((Color.DARK_GRAY));
		    mode1.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
		    
		    
		    blocMode.add(mode2);
		    mode2.setFont(impact);
		    mode2.setPreferredSize(dim);
		    mode2.setForeground((Color.DARK_GRAY));
		    mode2.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
		    
		    
		    blocMode.add(mode3);
		    mode3.setFont(impact);
		    mode3.setPreferredSize(dim);
		    mode3.setForeground((Color.DARK_GRAY));
		    mode3.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
		    
		    
		    
		    blocMode.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.44f));
		    blocMode.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		    
		    regle.setText("   -Mode challenger- où vous devez trouver\r\n la combinaison secrète de l'ordinateur\r\n\n" + 
		    		"   -Mode défenseur- où c'est à l'ordinateur\r\n de trouver votre combinaison secrète\r\n\n" + 
		    		"   -Mode duel- où l'ordinateur et vous jouez tour à tour,\r\n le premier à trouver la combinaison secrète de l'autre a gagné");
		    regle.setPreferredSize(new Dimension(450,155));
		    regle.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		    regle.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		    regle.setFont(police);
			regle.setEditable(false);
		    
		    lancer.setPreferredSize(new Dimension(90,40));
		    lancer.setBackground(Color.getHSBColor(0.234f, 0.65f, 0.94f));
		    lancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		    
		    retour.setPreferredSize(new Dimension(50,40));
		    retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.84f));
		    retour.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.orange));
            retour.setLayout(new GridLayout(3, 50));
            retour.addActionListener(new ActionListener(){
  		      public void actionPerformed(ActionEvent event){
  		    	  ((JFrame) contentPaneMenu.getTopLevelAncestor()).dispose() ;
  					Lancement menu = new Lancement();	
  					menu.setVisible(true);
  			      }
  			    });
		    
		    
		   
		    contentPaneMenu.add(retour);
		    contentPaneMenu.add(espace1);
		    espace1.setPreferredSize(new Dimension(450,50));
		    espace1.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		    contentPaneMenu.add(regle);
		    contentPaneMenu.add(blocMode);
		    contentPaneMenu.add(espace2);
		    espace2.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		    contentPaneMenu.add(lancer,BorderLayout.SOUTH);
  
		
	}

}
