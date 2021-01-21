package View;

import java.awt.BorderLayout;
import Helper.*;
import Model.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class KayitGUI extends JFrame {
	private Hasta hasta=new Hasta();
	private JPanel w_pane;
	private JTextField ad_soyad;
	private JTextField tc_no;
	private JPasswordField sifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KayitGUI frame = new KayitGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KayitGUI() {
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 307);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(214, 11, 70, 14);
		w_pane.add(lblNewLabel_1);
		
		ad_soyad = new JTextField();
		ad_soyad.setColumns(10);
		ad_soyad.setBounds(210, 36, 274, 30);
		w_pane.add(ad_soyad);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. Numraras\u0131");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(210, 71, 105, 14);
		w_pane.add(lblNewLabel_1_1);
		
		tc_no = new JTextField();
		tc_no.setColumns(10);
		tc_no.setBounds(210, 96, 274, 30);
		w_pane.add(tc_no);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(214, 138, 59, 14);
		w_pane.add(lblNewLabel_1_1_1);
		
		sifre = new JPasswordField();
		sifre.setBounds(210, 163, 274, 30);
		w_pane.add(sifre);
		
		JButton btn_kayitOl = new JButton("Kay\u0131t Ol");
		btn_kayitOl.setBackground(Color.WHITE);
		btn_kayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tc_no.getText().length()==0 || sifre.getText().length()==0 || ad_soyad.getText().length()==0) {
					Helper.showMessage("fill");
				}else {
					try {
						boolean kontrol=hasta.kayit(tc_no.getText(), sifre.getText(), ad_soyad.getText());
						if(kontrol) {
							LoginGUI login=new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMessage("ERROR");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_kayitOl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_kayitOl.setBounds(210, 204, 274, 30);
		w_pane.add(btn_kayitOl);
		
		JButton btn_geriDon = new JButton("Geri D\u00F6n");
		btn_geriDon.setBackground(Color.WHITE);
		btn_geriDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_geriDon.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_geriDon.setBounds(210, 237, 274, 30);
		w_pane.add(btn_geriDon);
		
		JLabel back = new JLabel(new ImageIcon(getClass().getResource("hastane.png")));
		back.setBounds(0, 0, 494, 278);
		w_pane.add(back);
	}
}
