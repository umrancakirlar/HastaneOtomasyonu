package View;

import java.awt.BorderLayout;
import Model.*;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.*;
import com.toedter.calendar.JYearChooser;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField hasta_tc;
	private JTextField doktor_tc;
	private JPasswordField doktor_sifre;
	private JPasswordField hasta_sifre;
	private DBConnection conn = new DBConnection();
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setBackground(Color.WHITE);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 430);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel label_logo = new JLabel(new ImageIcon(getClass().getResource("Adsýz.png")));
		label_logo.setBounds(422, 21, 93, 57);
		w_pane.add(label_logo);

		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(326, 89, 306, 30);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(210, 130, 474, 229);
		w_pane.add(w_tabpane);
		
				JPanel w_hastaLogin = new JPanel();
				w_hastaLogin.setBackground(Color.WHITE);
				w_tabpane.addTab("Hasta Giriþi", null, w_hastaLogin, null);
				w_hastaLogin.setLayout(null);
				
						JLabel lblTcNumranaz = new JLabel("T.C. Numrana\u0131z:");
						lblTcNumranaz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
						lblTcNumranaz.setBounds(22, 25, 129, 32);
						w_hastaLogin.add(lblTcNumranaz);
						
								JLabel lblifre = new JLabel("\u015Eifre:");
								lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
								lblifre.setBounds(22, 68, 129, 31);
								w_hastaLogin.add(lblifre);
								
										hasta_tc = new JTextField();
										hasta_tc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
										hasta_tc.setBounds(178, 25, 271, 32);
										w_hastaLogin.add(hasta_tc);
										hasta_tc.setColumns(10);
										
												JButton btn_hastaKayit = new JButton("Kay\u0131t Ol");
												btn_hastaKayit.setBackground(Color.WHITE);
												btn_hastaKayit.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														KayitGUI rGUI = new KayitGUI();
														rGUI.setVisible(true);
														dispose();
													}
												});
												btn_hastaKayit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
												btn_hastaKayit.setBounds(22, 128, 200, 49);
												w_hastaLogin.add(btn_hastaKayit);
												
														JButton btn_hastaLogin = new JButton("Giri\u015F Yap");
														btn_hastaLogin.setBackground(Color.WHITE);
														btn_hastaLogin.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																if (hasta_tc.getText().length() == 0 || hasta_sifre.getText().length() == 0) {
																	Helper.showMessage("fill");
																} else {
																	boolean key = true;
																	try {
																		Connection con = conn.connDb();
																		Statement st = con.createStatement();
																		ResultSet rs = st.executeQuery("SELECT * FROM user");
																		while (rs.next()) {
																			if (hasta_tc.getText().equals(rs.getString("tcno"))
																					&& hasta_sifre.getText().equals(rs.getString("password"))) {
																				if (rs.getString("type").equals("hasta")) {
																					Hasta hasta = new Hasta();
																					hasta.setId(rs.getInt("id"));
																					hasta.setPassword("password");
																					hasta.setTcno(rs.getString("tcno"));
																					hasta.setName(rs.getString("name"));
																					hasta.setType(rs.getString("type"));
																					HastaGUI hGUI = new HastaGUI(hasta);
																					hGUI.setVisible(true);
																					dispose();
																					key = false;
																				}
																			}
																		}
																	} catch (SQLException ex) {
																		ex.printStackTrace();
																	}
																	if(key) {
																		Helper.showMessage("Böyle bir kullanýcý bulunamadý. Lütfen kayýt olunuz!");
																	}
																}
															}
														});
														btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
														btn_hastaLogin.setBounds(249, 128, 200, 49);
														w_hastaLogin.add(btn_hastaLogin);
														
																hasta_sifre = new JPasswordField();
																hasta_sifre.setBounds(178, 71, 271, 31);
																w_hastaLogin.add(hasta_sifre);
																
																JLabel backg2 = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
																backg2.setBounds(0, 0, 469, 201);
																w_hastaLogin.add(backg2);

		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Giriþi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);

		JLabel lblTcNumranaz_1 = new JLabel("T.C. Numrana\u0131z:");
		lblTcNumranaz_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTcNumranaz_1.setBounds(20, 25, 129, 32);
		w_doktorLogin.add(lblTcNumranaz_1);

		doktor_tc = new JTextField();
		doktor_tc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		doktor_tc.setColumns(10);
		doktor_tc.setBounds(176, 25, 271, 32);
		w_doktorLogin.add(doktor_tc);

		JButton btn_doktorLogin = new JButton("Giri\u015F Yap");
		btn_doktorLogin.setBackground(Color.WHITE);
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (doktor_tc.getText().length() == 0 || doktor_sifre.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (doktor_tc.getText().equals(rs.getString("tcno"))
									&& doktor_sifre.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									BasHekim bh = new BasHekim();
									bh.setId(rs.getInt("id"));
									bh.setPassword("password");
									bh.setTcno(rs.getString("tcno"));
									bh.setName(rs.getString("name"));
									bh.setType(rs.getString("type"));
									BasHekimGUI bGUI = new BasHekimGUI(bh);
									bGUI.setVisible(true);
									dispose();
								}
								if (rs.getString("type").equals("doktor")) {
									Doktor doktor = new Doktor();
									doktor.setId(rs.getInt("id"));
									doktor.setPassword("password");
									doktor.setTcno(rs.getString("tcno"));
									doktor.setName(rs.getString("name"));
									doktor.setType(rs.getString("type"));
									DoktorGUI dGUI = new DoktorGUI(doktor);
									dGUI.setVisible(true);
									dispose();

								}
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
		});
		btn_doktorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_doktorLogin.setBounds(20, 128, 427, 49);
		w_doktorLogin.add(btn_doktorLogin);

		doktor_sifre = new JPasswordField();
		doktor_sifre.setBounds(176, 68, 271, 32);
		w_doktorLogin.add(doktor_sifre);

		JLabel lblifre_1 = new JLabel("\u015Eifre:");
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblifre_1.setBounds(20, 68, 129, 31);
		w_doktorLogin.add(lblifre_1);
		
		JLabel backg = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
		backg.setBounds(0, 0, 469, 201);
		w_doktorLogin.add(backg);
		
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("hast.png")));
		background.setBounds(0, 0, 694, 401);
		w_pane.add(background);
	}
}
