package fr.yoannroche.projet3.plusmoins.view;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.yoannroche.projet3.plusmoins.model.ChallengerPlusMoinsModel;
import fr.yoannroche.projet3.plusmoins.model.DefenseurPlusMoinsModel;
import fr.yoannroche.projet3.plusmoins.view.ChallengerPlusMoins.SourisListener;
import fr.yoannroche.projet3.plusmoins.view.ChallengerPlusMoins.SourisListener2;

public class DefenseurPlusMoins extends JFrame{

	private JLabel codeSecret = new JLabel();
	private JPanel contentPane = new JPanel();
	private JButton retour = new JButton(" Retour ");
	private JButton ok = new JButton(" Ok ");
	private JButton supprimer = new JButton(" Suppr ");
	private JPanel blocProposition = new JPanel();
	private JPanel blocTest = new JPanel();
	private JPanel ordi = new JPanel();
	private JPanel code = new JPanel();
	private JLabel tentativeOrdi = new JLabel();
	JPanel boiteDialog = new JPanel();
	private JTextField proposition = new JTextField(); 
	int  [] clavier = {0,1,2,3,4,5,6,7,8,9};
	JButton [] button = new JButton[clavier.length];
	private JButton bouton[];
	private static String tentatives;
	private static String bloc;
	private JTextArea dialog = new JTextArea();
	private int nombreClick;
	JLabel image = new JLabel();
	Font impact = new Font ("impact", 17,17);
	Font arial = new Font ("arial", 12,12);
	Font arial2 = new Font ("arial", 10,10);
	static ImageIcon ordi0 = new ImageIcon("images/ordi0.png");
	static int nombreCoup = 0;


	public DefenseurPlusMoins() {

		this.setTitle("Challenger");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		tentatives = reglage.getString("tentatives");
		bloc = reglage.getString("cases");
		initBar();
		initCadreOrdi();
		initCadreDialogOrdi();
		initDialog();
		initCode();
	}



	private void initCadreDialogOrdi() {
		ordi.setVisible(false);
		ordi.setPreferredSize(new Dimension(170,150));
		ordi.setBackground(Color.GRAY);
		ordi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		code.setPreferredSize(new Dimension(100,35));
		code.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.55f, 0.44f)));
		code.setBackground(Color.DARK_GRAY);
		codeSecret.setOpaque(true);
		codeSecret.setPreferredSize(new Dimension(80,20));
		codeSecret.setHorizontalAlignment(JLabel.CENTER);
		codeSecret.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.64f));
		codeSecret.setForeground(Color.orange);
		codeSecret.setFont(impact);
		dialog.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		dialog.setPreferredSize(new Dimension(150,95));
		dialog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		dialog.setEditable(false);
		dialog.setFont(arial);
		tentativeOrdi.setForeground(Color.orange);
		tentativeOrdi.setFont(impact);
		code.add(codeSecret);
		ordi.add(code);

		ordi.add(dialog);
		ordi.add(tentativeOrdi);
		contentPane.add(ordi);
	}



	private void initCode() {

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
			bouton[i].addMouseListener(new SourisListener2());
			blocProposition.add(bouton[i]).setEnabled(true);
			i++;
		}


		blocTest.add(proposition);
		blocTest.setPreferredSize(new Dimension(130,40));
		blocTest.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		blocTest.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		proposition.setText(" Entrez votre code secret ");
		proposition.setForeground(Color.DARK_GRAY);
		proposition.setFont(arial2);
		proposition.setPreferredSize(new Dimension(120,27));
		proposition.setBorder(BorderFactory.createLineBorder(Color.black));

		ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		ok.addMouseListener(new SourisListener());
		ok.addActionListener(new ActionListener(){ // Probleme au deuxieme ajouts , un espace ce place devant le nombre generé.
			public void actionPerformed(ActionEvent event){ 

				DefenseurPlusMoinsModel.okClick(proposition,codeSecret,contentPane,boiteDialog,ordi,blocProposition,image,tentativeOrdi,dialog);	
				nombreClick = 0;

			}  
		});
		supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		supprimer.addMouseListener(new SourisListener());
		supprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				proposition.setText("");
				nombreClick = 0;
				blocProposition.setVisible(true);
			}  
		});
	}


	private void initDialog() {

		boiteDialog.setPreferredSize(new Dimension(170,150));
		boiteDialog.setBackground(Color.GRAY);
		boiteDialog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));	
		boiteDialog.add(blocTest);
		boiteDialog.add(blocProposition);
		boiteDialog.add(supprimer);
		boiteDialog.add(ok);

		contentPane.add(boiteDialog);
	}


	private void initCadreOrdi() {
		JPanel ordinateur = new JPanel();
		ordinateur.setPreferredSize(new Dimension (180,168));
		ordinateur.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		ordinateur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		JPanel imageOrdi = new JPanel();
		imageOrdi.setPreferredSize(new Dimension(170,155));
		imageOrdi.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.24f));
		imageOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		image.setIcon(ordi0);
		image.setPreferredSize(new Dimension(160,140));
		image.setBorder(BorderFactory.createLineBorder(Color.black));
		imageOrdi.add(image);
		ordinateur.add(imageOrdi);
		contentPane.add(ordinateur);

	}


	private void initBar() {
		JPanel espaceRetour = new JPanel ();
		espaceRetour.setPreferredSize(new Dimension(320,20));
		espaceRetour.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		retour.setForeground(Color.white);
		retour.addMouseListener(new SourisListener());
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				FenetreMenuPlusMoins menu = new FenetreMenuPlusMoins();	
				menu.setVisible(true);
			}
		});
		JPanel blocRegle = new JPanel();
		blocRegle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		blocRegle.setPreferredSize(new Dimension(500,40));
		JLabel regle = new JLabel();
		int tentativeInt = Integer.parseInt(tentatives);
		int casesInt = Integer.parseInt(bloc);
		regle.setText("L'ordinateur a "+ tentativeInt +" tentative pour trouver le code secret à "+ casesInt +" chiffres.");
		regle.setFont(arial);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(320,5));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espace);

	}

	class SourisListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {

			if(arg0.getSource()==ok) {
				ok.setBackground(Color.getHSBColor(0.345f, 0.58f, 0.88f));
				ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.64f)));
			}
			if(arg0.getSource()==supprimer) {
				supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.64f)));
				supprimer.setBackground(Color.getHSBColor(0.534f, 0.65f, 0.84f));
			}
			if(arg0.getSource()==retour) {
				retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
		}

		public void mouseExited(MouseEvent arg0) {

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
			retour.setForeground(Color.white);
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
			ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		}
	}
	class SourisListener2 implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			proposition.setText(proposition.getText()+entrer);
			nombreClick++;
			ChallengerPlusMoinsModel.RangeWord(blocProposition,nombreClick);
		}
		public void mouseEntered(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,Color.white));
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





