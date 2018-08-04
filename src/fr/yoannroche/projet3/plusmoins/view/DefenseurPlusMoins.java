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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.yoannroche.projet3.plusmoins.model.DefenseurPlusMoinsModel;

public class DefenseurPlusMoins extends JFrame{

	private JLabel codeSecret = new JLabel();
	private JPanel contentPane = new JPanel();
	private JButton retour = new JButton(" Retour ");
	private JButton ok = new JButton(" Ok ");
	private JButton fin = new JButton(" Ok ");
	private JButton refresh = new JButton("⟲");
	private JButton supprimer = new JButton(" Suppr ");
	String [] indice = {"+","-","="};
	JButton [] indices = new JButton[indice.length];
	private JButton indi[];
	private JPanel blocProposition = new JPanel();
	private JPanel blocTest = new JPanel();
	private JPanel ordi = new JPanel();
	private JLabel tentativeOrdi = new JLabel();
	JPanel boiteDialog = new JPanel();
	private JTextField proposition = new JTextField(); 
	int  [] clavier = {0,1,2,3,4,5,6,7,8,9};
	JButton [] button = new JButton[clavier.length];
	private JButton bouton[];
	private static String tentatives;
	private static String bloc;
	private JLabel tentativeIA =new JLabel();
	private JPanel cadreOrdi = new JPanel();
	private JTextArea dialog = new JTextArea();
	private int nombreClick;
	private JPanel espaceDev = new JPanel();
	private JLabel indiceDev = new JLabel();
	JLabel image = new JLabel();
	Font impact = new Font ("impact", 17,17);
	Font arial = new Font ("arial", 12,12);
	Font arial2 = new Font ("arial", 10,10);
	static ImageIcon ordi0 = new ImageIcon("images/ordi0.png");
	static int nombreCoup = 0;
	int click=0;
	int nombreTour = 0;
	private DefenseurPlusMoinsModel def = new DefenseurPlusMoinsModel();


	public DefenseurPlusMoins() {

		this.setTitle(" Défenseur ");
		this.setSize(400, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		tentatives = reglage.getString("tentatives");
		bloc = reglage.getString("cases");
		initCadreDev();
		initBar();
		initCadreOrdi();
		initCadreDialogOrdi();
		initDialog();
		initCode();
	}

	private void initCadreDev() {
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		String devStatus = reglage.getString("dev");
		int devMode = Integer.parseInt(devStatus);
		if(devMode==1) {
			JLabel text2 = new JLabel();
			text2.setText(" Les indices à indiquer sont :");
			text2.setFont(arial2);
			text2.setForeground(Color.WHITE);
			espaceDev.add(text2);
			espaceDev.add(indiceDev);
			indiceDev.setForeground(Color.orange);
			indiceDev.setFont(arial2);
			
		}
	}


	private void initCadreDialogOrdi() {
		JLabel ordi = new JLabel();
		ordi.setText(" Ordinateur ");
		ordi.setOpaque(true);
		ordi.setFont(arial);
		ordi.setBorder(BorderFactory.createLineBorder(Color.black));
		dialog.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		dialog.setPreferredSize(new Dimension(150,80));
		dialog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		dialog.setEditable(false);
		dialog.setText(" \n Je suis prêt \n c'est quand vous voulez ! ");
		JPanel tentativePanel = new JPanel();
		tentativePanel.setPreferredSize(new Dimension(150,70));
		tentativePanel.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		tentativePanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentativeIA.setOpaque(true);
		tentativeIA.setBackground(Color.white);
		tentativeIA.setPreferredSize(new Dimension(130,30));
		tentativeIA.setHorizontalAlignment(JLabel.CENTER);
		tentativeIA.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentativeIA.setFont(arial);
		tentativeIA.setText("Entrez les indices");
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(65,5));
		espace2.setBackground(Color.DARK_GRAY);
		espace2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreOrdi.setPreferredSize(new Dimension(180,205));
		cadreOrdi.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreOrdi.add(ordi);
		cadreOrdi.add(dialog);
		cadreOrdi.add(espace2);

		tentativePanel.add(tentativeIA);
	
		refresh.setForeground((Color.getHSBColor(0.141f, 0.84f, 0.97f)));
		refresh.setBorder(BorderFactory.createLineBorder(Color.black));
		refresh.setBackground(Color.DARK_GRAY);
		refresh.addActionListener(new ActionListener(){ // Probleme au deuxieme ajouts , un espace ce place devant le nombre generé.
			public void actionPerformed(ActionEvent event){ 
				tentativeIA.setText("");

			}  
		});
		refresh.setPreferredSize(new Dimension(20,20));
		cadreOrdi.add(tentativePanel);
		contentPane.add(cadreOrdi);
		this.indi = new JButton[3];
		int i = 0;
		for(@SuppressWarnings("unused") String c : indice){
			this.indi[i] = new JButton(indice[i]);
			indi[i].setPreferredSize(new Dimension(20,20));
			indi[i].setBackground(Color.DARK_GRAY);
			indi[i].setBorder(BorderFactory.createLineBorder(Color.black));
			indi[i].setForeground(Color.white);
			indi[i].addMouseListener(new SourisListener3());
			tentativePanel.add(indi[i]).setEnabled(true);
			i++;
		}
		fin.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		tentativePanel.add(fin);
		tentativePanel.add(refresh);
		

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

				def.okClick(proposition,codeSecret,contentPane,ordi,blocProposition,image,tentativeOrdi,dialog, indiceDev,ok,fin);	
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
		fin.setPreferredSize(new Dimension(25,14));
		fin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
		fin.setBackground(Color.getHSBColor(0.154f, 0.45f, 0.44f));
		fin.setEnabled(false);
		fin.addActionListener(new ActionListener(){ // Probleme au deuxieme ajouts , un espace ce place devant le nombre generé.
			public void actionPerformed(ActionEvent event){ 
				
				
			def.finClick(tentativeOrdi,codeSecret,nombreCoup,dialog,contentPane,image,nombreTour,indiceDev,tentativeIA,fin,cadreOrdi);
			
			}  
		});
	}

	private void initDialog() {

		boiteDialog.setPreferredSize(new Dimension(170,150));
		boiteDialog.setBackground(Color.GRAY);
		boiteDialog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));	
		espaceDev.setPreferredSize(new Dimension(520,25));
		espaceDev.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espaceDev);
		contentPane.add(blocProposition);
		contentPane.add(blocTest);
		contentPane.add(supprimer);
		contentPane.add(ok);
	}

	private void initCadreOrdi() {
		
		JPanel ordinateur = new JPanel();
		ordinateur.setPreferredSize(new Dimension (180,205));
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
		JPanel cadreCode = new JPanel();
		cadreCode.setPreferredSize(new Dimension(165,30));
		cadreCode.setBackground(Color.DARK_GRAY);
	    cadreCode.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		JLabel codeText = new JLabel();
		codeText.setText("Votre code secret : ");
		codeText.setFont(arial);
		codeText.setForeground(Color.WHITE);
		codeSecret.setText("?");
		codeSecret.setForeground(Color.getHSBColor(0.143f, 0.84f, 0.86f));
		codeSecret.setFont(arial);
		cadreCode.add(codeText);
		cadreCode.add(codeSecret);
		ordinateur.add(cadreCode);
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
			if(click==0) {
				supprimer.doClick();
			}
			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			proposition.setText(proposition.getText()+entrer);
			click++;
			def.RangeWord(blocProposition,nombreClick);
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
	class SourisListener3 implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			if(nombreClick==0) {
				refresh.doClick();
			}
			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			tentativeIA.setText(tentativeIA.getText()+entrer);
			nombreClick++;

		}
		public void mouseEntered(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBackground(Color.GRAY);
		}
		public void mouseExited(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBackground(Color.DARK_GRAY);

		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}
}





