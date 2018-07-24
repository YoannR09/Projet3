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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.plusmoins.model.ChallengerPlusMoinsModel;



public class ChallengerPlusMoins extends JFrame{


	private static String tentatives;
	private static String bloc;
	private JPanel contentPane = new JPanel();
	private JLabel proposition = new JLabel();
	private JPanel blocProposition = new JPanel();
	private JPanel blocTest = new JPanel();
	private JLabel tentative = new JLabel();
	private JLabel infosTentative = new JLabel();
	JButton ok = new JButton(" OK ");
	JButton supprimer = new JButton(" Suppr ");
	JButton retour = new JButton("Retour");
	Font impact = new Font ("impact", 17,17);
	Font arial = new Font ("arial", 12,12);
	int  [] clavier = {0,1,2,3,4,5,6,7,8,9};
	JButton [] button = new JButton[clavier.length];
	private int nbreTentative = 0;
	private JButton bouton[];
	JPanel espace2 = new JPanel ();

	public ChallengerPlusMoins() {}

	public ChallengerPlusMoins(boolean modeDev) {


		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		tentatives = reglage.getString("tentatives");
		bloc = reglage.getString("cases");


		this.setTitle("Challenger");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));


		initRegle();
		initTentative();
		initInfos();
		initBlocProposition();
		initBlocTest();
		initCadreDev(modeDev);
	}

	private void initCadreDev(boolean modeDev) {
		if(modeDev==true) {
			JLabel codeSecret = new JLabel();
			codeSecret.setFont(impact);
			codeSecret.setForeground((Color.getHSBColor(0.141f, 0.84f, 0.97f)));
			codeSecret.setText("");

			JPanel cadreDev = new JPanel();
			JLabel text = new JLabel();
			text.setText("Code secret : ");
			text.setFont(arial);
			text.setForeground(Color.WHITE);
			cadreDev.add(codeSecret);
			cadreDev.setPreferredSize(new Dimension(50,15));
			cadreDev.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
			espace2.add(cadreDev);
			espace2.add(text);
			espace2.add(codeSecret);
			ChallengerPlusMoinsModel.dev(codeSecret);
		}

	}

	private void initInfos() {
		JPanel infosCadre = new JPanel();
		infosCadre.setPreferredSize(new Dimension(150,50));
		infosCadre.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		infosTentative.setOpaque(true);
		infosTentative.setBackground(Color.WHITE);
		infosTentative.setBorder(BorderFactory.createLineBorder(Color.black));
		infosTentative.setPreferredSize(new Dimension(120,40));
		infosTentative.setHorizontalAlignment(JLabel.CENTER);
		infosTentative.setFont(impact);
		infosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		infosCadre.add(infosTentative);

		contentPane.add(infosCadre);

		espace2.setPreferredSize(new Dimension(320,25));
		espace2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		JLabel nbreTen = new JLabel();
		nbreTen.setText("Nombre de tentative : " + nbreTentative);
		espace2.add(nbreTen);
		nbreTen.setFont(arial);
		nbreTen.setForeground(Color.white);

		contentPane.add(espace2);

	}

	private void initTentative() {
		JPanel tentativePanel = new JPanel();
		tentativePanel.setPreferredSize(new Dimension(150,50));
		tentativePanel.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));


		tentative.setOpaque(true);
		tentative.setBackground(Color.white);
		tentative.setPreferredSize(new Dimension(120,40));
		tentative.setBorder(BorderFactory.createLineBorder(Color.black));
		tentative.setHorizontalAlignment(JLabel.CENTER);
		tentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentative.setFont(impact);

		tentativePanel.add(tentative);
		contentPane.add(tentativePanel);

		JPanel espace3 = new JPanel ();
		espace3.setPreferredSize(new Dimension(40,15));
		espace3.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.74f));
		espace3.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(espace3);
	}

	private void initRegle() {
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
		regle.setText("Vous avez "+ tentativeInt +" tentative pour trouver le code secret à "+ casesInt +" chiffres.");
		regle.setFont(arial);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(320,15));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espace);

	}

	private void initBlocTest() {


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
		ok.addActionListener(new ActionListener(){ // Probleme au deuxieme ajouts , un espace ce place devant le chiffre generé.
			public void actionPerformed(ActionEvent event){ 
				tentative.setText(proposition.getText());
				ChallengerPlusMoinsModel.check(tentative, infosTentative);
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

		contentPane.add(supprimer);
		contentPane.add(ok);


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
		contentPane.add(blocProposition);

	}


	class SourisListener implements MouseListener {


		public void mouseClicked(MouseEvent arg0) {

			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			proposition.setText(proposition.getText()+entrer);

		}

		public void mouseEntered(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,Color.white));
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
			retour.setForeground(Color.white);
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
			ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		}

	}


}