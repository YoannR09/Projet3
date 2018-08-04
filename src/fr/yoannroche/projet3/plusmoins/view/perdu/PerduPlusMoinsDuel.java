package fr.yoannroche.projet3.plusmoins.view.perdu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.Lancement;
import fr.yoannroche.projet3.plusmoins.view.ChallengerPlusMoins;
import fr.yoannroche.projet3.plusmoins.view.FenetreMenuPlusMoins;

public class PerduPlusMoinsDuel extends JDialog {

	JPanel contentPane = new JPanel();
	JPanel contentPane2;
	JButton relancer = new JButton(" Relancer ");
	JButton home = new JButton(" Menu ");
	JButton quitter = new JButton(" Quitter ");
	Font arial = new Font ("arial", 12,12);
	String proposition;
	ChallengerPlusMoins chal;
	JLabel codeSecret;
	int nombreCoup;

	public PerduPlusMoinsDuel(JFrame parent, String title,String codeSecret, JPanel contentPane2){

		super(parent, title);
		this.setSize(400, 110);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.getContentPane().add(contentPane);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		this.contentPane2 = contentPane2;

		initText();
		initCadre();
	}

	private void initText() {

		JPanel cadreText = new JPanel();
		cadreText.setPreferredSize(new Dimension(380,25));
		cadreText.setBackground(Color.DARK_GRAY);
		JLabel text = new JLabel();
		text.setFont(arial);
		text.setForeground(Color.white);
		text.setText("Vous avez perdu, l'ordinateur à trouvé plus rapidement que vous");
		cadreText.add(text);
		contentPane.add(cadreText);
	}

	private void initCadre() {

		JPanel cadreBouton = new JPanel();
		cadreBouton.setPreferredSize(new Dimension(230,35));
		cadreBouton.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreBouton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		cadreBouton.add(relancer);
		relancer.addMouseListener(new SourisListener());
		relancer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				((JFrame) contentPane2.getTopLevelAncestor()).dispose(); 
				FenetreMenuPlusMoins fen = new FenetreMenuPlusMoins();
				fen.setVisible(true);
			}
		});
		relancer.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		relancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		relancer.setForeground(Color.white);
		cadreBouton.add(home);
		home.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				((JFrame) contentPane2.getTopLevelAncestor()).dispose();
				Lancement menu = new Lancement();	
				menu.setVisible(true);
			}
		});
		home.addMouseListener(new SourisListener());
		home.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		home.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		home.setForeground(Color.white);
		cadreBouton.add(quitter);
		quitter.addMouseListener(new SourisListener());
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				((JFrame) contentPane2.getTopLevelAncestor()).dispose();
			}
		});
		quitter.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		quitter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		quitter.setForeground(Color.white);
		contentPane.add(cadreBouton);
	}

	class SourisListener implements MouseListener {


		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {

			if(arg0.getSource()==relancer) {
				relancer.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				relancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
			if(arg0.getSource()==home) {
				home.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
				home.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
			}
			if(arg0.getSource()==quitter) {
				quitter.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				quitter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
		}

		public void mouseExited(MouseEvent arg0) {

			relancer.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			relancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			relancer.setForeground(Color.white);
			home.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			home.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			quitter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			quitter.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		}

		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {

			relancer.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			relancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			relancer.setForeground(Color.white);
			home.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			home.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			quitter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			quitter.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		}

	}
}
