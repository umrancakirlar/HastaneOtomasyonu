package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.*;
import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.Icon;

public class HastaGUI extends JFrame {
	private CalismaSaati calisma = new CalismaSaati();
	private Klinik klinik = new Klinik();
	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private JTable table_doktor;
	private DefaultTableModel doktorModel;
	private Object[] doktorData = null;
	private JTable table_calisma;
	private DefaultTableModel calismaModel;
	private Object[] calismaData = null;
	private int selectDoktorID=0;
	private String selectDoktorName;
	private JTable table_randevularim;
	private DefaultTableModel randevularimModel;
	private Object[] randevularimData = null;
	private Randevu randevu=new Randevu();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
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
	public HastaGUI(Hasta hasta) throws SQLException {
		setBackground(Color.WHITE);

		doktorModel = new DefaultTableModel();
		Object[] colDoktor = new Object[2]; // column baþlýklarýný yazacaðýmýz array (type gösterilmeyecek)
		colDoktor[0] = "ID";
		colDoktor[1] = "Ad Soyad";
		doktorModel.setColumnIdentifiers(colDoktor);
		doktorData = new Object[2];

		calismaModel = new DefaultTableModel();
		Object[] colCalisma = new Object[2]; // column baþlýklarýný yazacaðýmýz array (type gösterilmeyecek)
		colCalisma[0] = "ID";
		colCalisma[1] = "Tarih";
		calismaModel.setColumnIdentifiers(colCalisma);
		calismaData = new Object[2];
		
		randevularimModel = new DefaultTableModel();
		Object[] colRandevu = new Object[3]; // column baþlýklarýný yazacaðýmýz array (type gösterilmeyecek)
		colRandevu[0] = "ID";
		colRandevu[1] = "Doktor Adý";
		colRandevu[2] = "Tarih";
		randevularimModel.setColumnIdentifiers(colRandevu);
		randevularimData = new Object[3];
		for(int i=0; i<randevu.getHastaList(hasta.getId()).size();i++) {
			randevularimData[0]=randevu.getHastaList(hasta.getId()).get(i).getId();
			randevularimData[1]=randevu.getHastaList(hasta.getId()).get(i).getDoktor_name();
			randevularimData[2]=randevu.getHastaList(hasta.getId()).get(i).getTarih();
			randevularimModel.addRow(randevularimData);
		}

		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 430);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + hasta.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(210, 11, 297, 27);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
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
		w_tab.setBackground(Color.WHITE);
		w_tab.setBounds(210, 65, 674, 325);
		w_pane.add(w_tab);

		JPanel w_randevu = new JPanel();
		w_randevu.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_randevu, null);
		w_randevu.setLayout(null);

		JScrollPane scrollDoktor = new JScrollPane();
		scrollDoktor.setBounds(10, 28, 238, 258);
		w_randevu.add(scrollDoktor);

		table_doktor = new JTable(doktorModel);
		table_doktor.setBackground(Color.WHITE);
		scrollDoktor.setViewportView(table_doktor);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 11, 105, 14);
		w_randevu.add(lblNewLabel_1);

		JLabel lblNewLabel_1_4 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(258, 29, 104, 14);
		w_randevu.add(lblNewLabel_1_4);

		JComboBox select_klinik = new JComboBox();
		select_klinik.setBackground(Color.WHITE);
		select_klinik.setBounds(258, 55, 153, 22);
		select_klinik.addItem("Poliklinik Seç");
		for (int i = 0; i < klinik.getList().size(); i++) {
			select_klinik.addItem(new Item(klinik.getList().get(i).getId(), klinik.getList().get(i).getName()));
		}
		select_klinik.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				if (select_klinik.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) ex.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < klinik.getKlinikDoktorList(item.getKey()).size(); i++) {
							doktorData[0] = klinik.getKlinikDoktorList(item.getKey()).get(i).getId();
							doktorData[1] = klinik.getKlinikDoktorList(item.getKey()).get(i).getName();
							doktorModel.addRow(doktorData);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_randevu.add(select_klinik);

		JLabel lblNewLabel_1_4_1 = new JLabel("Doktor Se\u00E7");
		lblNewLabel_1_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_4_1.setBounds(258, 111, 104, 14);
		w_randevu.add(lblNewLabel_1_4_1);

		JButton btn_sec = new JButton("Se\u00E7");
		btn_sec.setBackground(Color.WHITE);
		btn_sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doktor.getSelectedRow();
				if (row >= 0) {
					String value = table_doktor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_calisma.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < calisma.getCalismaList(id).size(); i++) {
							calismaData[0] = calisma.getCalismaList(id).get(i).getId();
							calismaData[1] = calisma.getCalismaList(id).get(i).getTarih();
							calismaModel.addRow(calismaData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_calisma.setModel(calismaModel);
					selectDoktorID=id;
					selectDoktorName=table_doktor.getModel().getValueAt(row, 1).toString();
				}else {
					Helper.showMessage("Lütfen bir doktor seçiniz!");
				}
			}
		});
		btn_sec.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_sec.setBounds(258, 136, 153, 23);
		w_randevu.add(btn_sec);

		JScrollPane scrollCalisma = new JScrollPane();
		scrollCalisma.setBounds(421, 28, 238, 258);
		w_randevu.add(scrollCalisma);

		table_calisma = new JTable(calismaModel);
		table_calisma.setBackground(Color.WHITE);
		scrollCalisma.setViewportView(table_calisma);
		table_calisma.getColumnModel().getColumn(0).setPreferredWidth(5); //ID'nin daha küçük, tarihin daha büyük gözükmeksi için

		JLabel lblNewLabel_1_1 = new JLabel("Uygun Saatler");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(421, 11, 105, 14);
		w_randevu.add(lblNewLabel_1_1);
		
		JButton btn_randevuAl = new JButton("Randevu Al");
		btn_randevuAl.setBackground(Color.WHITE);
		btn_randevuAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_calisma.getSelectedRow();
				if(selRow>=0) {
					String date=table_calisma.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean kontrol=hasta.randevuAl(selectDoktorID, hasta.getId(), selectDoktorName, hasta.getName(), date);
						if(kontrol) {
							Helper.showMessage("success");
							hasta.updateCalismaDurum(selectDoktorID, date);
							updateCalismaSaatiModel(selectDoktorID);
							updateRandevuModel(hasta.getId());
						}else {
							Helper.showMessage("ERROR");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					Helper.showMessage("Lütfen geçerli bir tarih seçiniz!");
				}
			}
		});
		btn_randevuAl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_randevuAl.setBounds(258, 170, 153, 23);
		w_randevu.add(btn_randevuAl);
		
		JLabel bg = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
		bg.setBounds(0, 0, 669, 297);
		w_randevu.add(bg);
		
		JPanel w_randevularim = new JPanel();
		w_randevularim.setBackground(Color.WHITE);
		w_tab.addTab("Randevularým", null, w_randevularim, null);
		w_randevularim.setLayout(null);
		
		JScrollPane scrollRandevu = new JScrollPane();
		scrollRandevu.setBounds(10, 45, 649, 241);
		w_randevularim.add(scrollRandevu);
		
		table_randevularim = new JTable(randevularimModel);
		table_randevularim.setBackground(Color.WHITE);
		scrollRandevu.setViewportView(table_randevularim);
		
		JButton btn_deleteRandevu = new JButton("\u0130ptal Et");
		btn_deleteRandevu.setBackground(Color.WHITE);
		btn_deleteRandevu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow =table_randevularim.getSelectedRow();
				if(selRow>=0) {
					String selectRow=table_randevularim.getModel().getValueAt(selRow, 0).toString();
					int selID=Integer.parseInt(selectRow);
					String selTarih=table_randevularim.getModel().getValueAt(selRow, 2).toString();
					boolean kontrol;
					boolean kontrol2;
					try {
						kontrol=hasta.updateRandevu(selTarih);
						kontrol2=hasta.deleteRandevu(selID);
						if(kontrol && kontrol2) {
							Helper.showMessage("success");
							DefaultTableModel clearModel=(DefaultTableModel) table_randevularim.getModel();
							clearModel.setRowCount(0);
							for(int i=0; i<randevu.getHastaList(hasta.getId()).size();i++) {
								randevularimData[0]=randevu.getHastaList(hasta.getId()).get(i).getId();
								randevularimData[1]=randevu.getHastaList(hasta.getId()).get(i).getDoktor_name();
								randevularimData[2]=randevu.getHastaList(hasta.getId()).get(i).getTarih();
								randevularimModel.addRow(randevularimData);
							}
							
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
		w_randevularim.add(btn_deleteRandevu);
		
		JLabel bg_1 = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
		bg_1.setBounds(0, 0, 669, 297);
		w_randevularim.add(bg_1);
		
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("hast.png")));
		background.setBounds(0, 0, 894, 401);
		w_pane.add(background);
	}
	public void updateCalismaSaatiModel(int doktor_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_calisma.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < calisma.getCalismaList(doktor_id).size(); i++) {
			calismaData[0] = calisma.getCalismaList(doktor_id).get(i).getId();
			calismaData[1] = calisma.getCalismaList(doktor_id).get(i).getTarih();
			calismaModel.addRow(calismaData);
		}
	}
	public void updateRandevuModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_randevularim.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<randevu.getHastaList(hasta_id).size();i++) {
			randevularimData[0]=randevu.getHastaList(hasta_id).get(i).getId();
			randevularimData[1]=randevu.getHastaList(hasta_id).get(i).getDoktor_name();
			randevularimData[2]=randevu.getHastaList(hasta_id).get(i).getTarih();
			randevularimModel.addRow(randevularimData);
		}
	}
}
