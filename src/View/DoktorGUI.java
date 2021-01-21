package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doktor;
import Model.Randevu;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.Icon;

public class DoktorGUI extends JFrame {

	private JPanel w_pane;
	private static Doktor doktor = new Doktor();
	private JTable table_calisma;
	private DefaultTableModel calismaModel;
	private Object[] calismaData = null;
	private JTable randevu_table;
	private DefaultTableModel randevuModel;
	private Object[] randevuData = null;
	private Randevu randevu=new Randevu();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorGUI frame = new DoktorGUI(doktor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public DoktorGUI(Doktor doktor) throws SQLException {
		setBackground(Color.WHITE);

		calismaModel = new DefaultTableModel();
		Object[] colCalisma = new Object[2];
		colCalisma[0] = "ID";
		colCalisma[1] = "Tarih";
		calismaModel.setColumnIdentifiers(colCalisma);
		calismaData = new Object[2];
		for (int i = 0; i < doktor.getCalismaList(doktor.getId()).size(); i++) {
			try {
				calismaData[0] = doktor.getCalismaList(doktor.getId()).get(i).getId();
				calismaData[1] = doktor.getCalismaList(doktor.getId()).get(i).getTarih();
				calismaModel.addRow(calismaData);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		randevuModel = new DefaultTableModel();
		Object[] colRandevu = new Object[3]; // column baþlýklarýný yazacaðýmýz array (type gösterilmeyecek)
		colRandevu[0] = "ID";
		colRandevu[1] = "Hasta Adý";
		colRandevu[2] = "Tarih";
		randevuModel.setColumnIdentifiers(colRandevu);
		randevuData = new Object[3];
		for(int i=0; i<randevu.getDoktorList(doktor.getId()).size();i++) {
			randevuData[0]=randevu.getDoktorList(doktor.getId()).get(i).getId();
			randevuData[1]=randevu.getDoktorList(doktor.getId()).get(i).getHasta_name();
			randevuData[2]=randevu.getDoktorList(doktor.getId()).get(i).getTarih();
			randevuModel.addRow(randevuData);
		}

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 430);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + doktor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(210, 11, 297, 27);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnNewButton.setBounds(767, 15, 117, 23);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(210, 67, 674, 323);
		w_pane.add(w_tab);

		JPanel w_calismaSaati = new JPanel();
		w_calismaSaati.setBackground(Color.WHITE);
		w_tab.addTab("Çalýþma Saatleri", null, w_calismaSaati, null);
		w_calismaSaati.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.getCalendarButton().setBackground(Color.WHITE);
		select_date.setBounds(10, 11, 97, 20);
		w_calismaSaati.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setBackground(Color.WHITE);
		select_time.setModel(new DefaultComboBoxModel(new String[] {"9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"}));
		select_time.setBounds(117, 11, 63, 22);
		w_calismaSaati.add(select_time);

		JButton btn_saatEkle = new JButton("Ekle");
		btn_saatEkle.setBackground(Color.WHITE);
		btn_saatEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = ""; // length=0 oluyor balangýçta
				try {
					date = sdf.format(select_date.getDate());// tarih seçilmediðinde null deðer dönüyor o nedenle
																// try-catch ile hata yakalanýyor
				} catch (Exception e2) {
				}
				if (date.length() == 0) {
					Helper.showMessage("Lütfen geçerli bir tarih giriniz!");
				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					boolean kontrol;
					try {
						kontrol = doktor.addCalismaSaati(doktor.getId(), doktor.getName(), selectDate);
						if (kontrol) {
							updateCalismaSaatiModel(doktor);
						} else {
							Helper.showMessage("ERROR");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_saatEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btn_saatEkle.setBounds(190, 11, 97, 23);
		w_calismaSaati.add(btn_saatEkle);

		JScrollPane scroll_calisma = new JScrollPane();
		scroll_calisma.setBounds(10, 42, 649, 242);
		w_calismaSaati.add(scroll_calisma);

		table_calisma = new JTable(calismaModel);
		table_calisma.setBackground(Color.WHITE);
		scroll_calisma.setViewportView(table_calisma);
		
		JButton btn_deleteCalisma = new JButton("Sil");
		btn_deleteCalisma.setBackground(Color.WHITE);
		btn_deleteCalisma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_calisma.getSelectedRow();
				if(selRow>=0) {
					String selectRow=table_calisma.getModel().getValueAt(selRow, 0).toString();
					int selID=Integer.parseInt(selectRow);
					boolean kontrol;
					try {
						kontrol=doktor.deleteCalismaSaati(selID);
						if(kontrol) {
							Helper.showMessage("success");
							updateCalismaSaatiModel(doktor);
					}else {
						Helper.showMessage("ERROR");
					}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMessage("Lütfen bir tarih seçiniz!");
				}
			}
		});
		btn_deleteCalisma.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btn_deleteCalisma.setBounds(562, 11, 97, 23);
		w_calismaSaati.add(btn_deleteCalisma);
		
		JLabel bg_1 = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
		bg_1.setBounds(0, 0, 669, 297);
		w_calismaSaati.add(bg_1);
		
		JPanel w_rand = new JPanel();
		w_rand.setBackground(Color.WHITE);
		w_tab.addTab("Randevular", null, w_rand, null);
		w_rand.setLayout(null);
		
		JScrollPane scrollRandevu = new JScrollPane();
		scrollRandevu.setBounds(10, 42, 649, 244);
		w_rand.add(scrollRandevu);
		
		randevu_table = new JTable(randevuModel);
		scrollRandevu.setViewportView(randevu_table);
		
		JButton btn_deleteRandevu = new JButton("\u0130ptal Et");
		btn_deleteRandevu.setBackground(Color.WHITE);
		btn_deleteRandevu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow =randevu_table.getSelectedRow();
				if(selRow>=0) {
					String selectRow=randevu_table.getModel().getValueAt(selRow, 0).toString();
					int selID=Integer.parseInt(selectRow);
					String selTarih=randevu_table.getModel().getValueAt(selRow, 2).toString();
					boolean kontrol;
					boolean kontrol2;
					try {
						kontrol=doktor.updateRandevu(selTarih);
						kontrol2=doktor.deleteRandevu(selID);
						if(kontrol && kontrol2) {
							Helper.showMessage("success");
							DefaultTableModel clearModel=(DefaultTableModel) randevu_table.getModel();
							clearModel.setRowCount(0);
							for(int i=0; i<randevu.getDoktorList(doktor.getId()).size();i++) {
								randevuData[0]=randevu.getDoktorList(doktor.getId()).get(i).getId();
								randevuData[1]=randevu.getDoktorList(doktor.getId()).get(i).getHasta_name();
								randevuData[2]=randevu.getDoktorList(doktor.getId()).get(i).getTarih();
								randevuModel.addRow(randevuData);
							}
							updateCalismaSaatiModel(doktor);
					}else {
						Helper.showMessage("ERROR");
					}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMessage("Lütfen bir randevu seçiniz!");
				}
			}
		});
		btn_deleteRandevu.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btn_deleteRandevu.setBounds(562, 11, 97, 23);
		w_rand.add(btn_deleteRandevu);
		
		JLabel bg_1_1 = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
		bg_1_1.setBounds(0, 0, 669, 297);
		w_rand.add(bg_1_1);
		
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("hast.png")));
		background.setBounds(0, 0, 894, 401);
		w_pane.add(background);
	}
	public void updateCalismaSaatiModel(Doktor doktor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_calisma.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < doktor.getCalismaList(doktor.getId()).size(); i++) {
				calismaData[0] = doktor.getCalismaList(doktor.getId()).get(i).getId();
				calismaData[1] = doktor.getCalismaList(doktor.getId()).get(i).getTarih();
				calismaModel.addRow(calismaData);
			}
	}
	public void updateRandevuModel(int doktor_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) randevu_table.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<randevu.getDoktorList(doktor.getId()).size();i++) {
			randevuData[0]=randevu.getDoktorList(doktor.getId()).get(i).getId();
			randevuData[1]=randevu.getDoktorList(doktor.getId()).get(i).getHasta_name();
			randevuData[2]=randevu.getDoktorList(doktor.getId()).get(i).getTarih();
			randevuModel.addRow(randevuData);
		}
	}
}
