package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.BasHekim;
import Model.Klinik;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.Icon;

public class BasHekimGUI extends JFrame {
	static BasHekim basHekim = new BasHekim();
	Klinik klinik = new Klinik();
	private JPanel w_pane;
	private JTextField dName;
	private JTextField dTc;
	private JTextField dSifre;
	private JTextField dID;
	private JTable table_doktor;
	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;
	private JTable table_klinik;
	private JTextField klinik_adi;
	private DefaultTableModel klinikModel = null;
	private Object[] klinikData = null;
	private JPopupMenu klinikMenu;
	private JTable table_employee;
	private DefaultTableModel employeeModel = null;
	private Object[] employeeData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGUI frame = new BasHekimGUI(basHekim);
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
	public BasHekimGUI(BasHekim basHekim) throws SQLException {
		// Doktor Model
		doktorModel = new DefaultTableModel();
		Object[] colDoktorName = new Object[4]; // column baþlýklarýný yazacaðýmýz array (type gösterilmeyecek)
		colDoktorName[0] = "ID";
		colDoktorName[1] = "Ad Soyad";
		colDoktorName[2] = "TC No";
		colDoktorName[3] = "Þifre";
		doktorModel.setColumnIdentifiers(colDoktorName);
		doktorData = new Object[4];
		for (int i = 0; i < basHekim.getDoktorList().size(); i++) {
			doktorData[0] = basHekim.getDoktorList().get(i).getId();
			doktorData[1] = basHekim.getDoktorList().get(i).getName();
			doktorData[2] = basHekim.getDoktorList().get(i).getTcno();
			doktorData[3] = basHekim.getDoktorList().get(i).getPassword();
			doktorModel.addRow(doktorData);
		}
		// Klinik Model
		klinikModel = new DefaultTableModel();
		Object[] colKlinik = new Object[2]; // column baþlýklarýný yazacaðýmýz array (type gösterilmeyecek)
		colKlinik[0] = "ID";
		colKlinik[1] = "Poliklinik Adi";
		klinikModel.setColumnIdentifiers(colKlinik);
		klinikData = new Object[2];
		for (int i = 0; i < klinik.getList().size(); i++) {
			klinikData[0] = klinik.getList().get(i).getId();
			klinikData[1] = klinik.getList().get(i).getName();
			klinikModel.addRow(klinikData);
		}
		//Employee Model
		employeeModel=new DefaultTableModel();
		Object[] colEmployee=new Object[2];
		colEmployee[0]="ID";
		colEmployee[1]="Ad Soyad";
		employeeModel.setColumnIdentifiers(colEmployee);
		employeeData=new Object[2];
		
		setBackground(Color.WHITE);
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 430);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + basHekim.getName());
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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(210, 65, 674, 325);
		w_pane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);

		dName = new JTextField();
		dName.setBounds(515, 34, 144, 20);
		panel.add(dName);
		dName.setColumns(10);

		dTc = new JTextField();
		dTc.setColumns(10);
		dTc.setBounds(515, 90, 144, 20);
		panel.add(dTc);

		dSifre = new JTextField();
		dSifre.setColumns(10);
		dSifre.setBounds(515, 144, 144, 20);
		panel.add(dSifre);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(515, 9, 78, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(515, 65, 78, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(515, 121, 78, 14);
		panel.add(lblNewLabel_1_2);

		JButton btn_DoktorEkle = new JButton("Ekle");
		btn_DoktorEkle.setBackground(Color.WHITE);
		btn_DoktorEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dName.getText().length() == 0 || dTc.getText().length() == 0 || dSifre.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {
					try {
						boolean kontrol = basHekim.addDoktor(dTc.getText(), dSifre.getText(), dName.getText());
						if (kontrol) {
							Helper.showMessage("success");
							dName.setText(null);
							dSifre.setText(null);
							dTc.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_DoktorEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_DoktorEkle.setBounds(515, 175, 144, 23);
		panel.add(btn_DoktorEkle);

		JLabel lblNewLabel_1_3 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(515, 209, 78, 14);
		panel.add(lblNewLabel_1_3);

		dID = new JTextField();
		dID.setColumns(10);
		dID.setBounds(515, 232, 144, 20);
		panel.add(dID);

		JButton btn_DoktorSil = new JButton("Sil");
		btn_DoktorSil.setBackground(Color.WHITE);
		btn_DoktorSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dID.getText().length() == 0) {
					Helper.showMessage("Lütfen geçerli bir doktor seçiniz");
				} else {
					if (Helper.confirm("sure")) {
						int selectedID = Integer.parseInt(dID.getText());
						try {
							boolean kontrol = basHekim.deleteDoktor(selectedID);
							boolean kontrol2=basHekim.deleteCalisan(selectedID);
							if (kontrol && kontrol2) {
								Helper.showMessage("success");
								dID.setText(null);
								updateDoctorModel();
								updateEmployeeModel(selectedID);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_DoktorSil.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_DoktorSil.setBounds(515, 263, 144, 23);
		panel.add(btn_DoktorSil);

		JScrollPane w_scrollDocktor = new JScrollPane();
		w_scrollDocktor.setBounds(10, 9, 495, 277);
		panel.add(w_scrollDocktor);

		table_doktor = new JTable(doktorModel);
		w_scrollDocktor.setViewportView(table_doktor);
		
		JLabel bg_1 = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
		bg_1.setBounds(0, 0, 669, 297);
		panel.add(bg_1);

		table_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try { // burada try-catch yapýlmasýnýn nedeni silme yapýldýðýnda tabloda deðiþiklik
						// oluyor ama id giriþi kýsmýnda hala ayný id yi arýyor burda da hata veriyor
						// onu yakalamak için
					dID.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}

		});
		table_doktor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectedID = Integer
							.parseInt(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
					String selectedName = table_doktor.getValueAt(table_doktor.getSelectedRow(), 1).toString();
					String selectedTC = table_doktor.getValueAt(table_doktor.getSelectedRow(), 2).toString();
					String selectedPassword = table_doktor.getValueAt(table_doktor.getSelectedRow(), 3).toString();

					try {
						basHekim.updateDoktor(selectedID, selectedTC, selectedPassword, selectedName);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}

		});
		JPanel w_klinik = new JPanel();
		w_klinik.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, w_klinik, null);
		w_klinik.setLayout(null);

		JScrollPane scroll_klinik = new JScrollPane();
		scroll_klinik.setBounds(10, 11, 229, 275);
		w_klinik.add(scroll_klinik);

		klinikMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		klinikMenu.add(updateMenu);
		klinikMenu.add(deleteMenu);
		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(table_klinik.getValueAt(table_klinik.getSelectedRow(), 0).toString());
				Klinik selectedKlinik = klinik.getFetch(selectedID);
				UpdateKlinikGUI updateGUI = new UpdateKlinikGUI(selectedKlinik);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateKlinikModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}

		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Helper.confirm("sure")) {
					int selectedID = Integer
							.parseInt(table_klinik.getValueAt(table_klinik.getSelectedRow(), 0).toString());
					try {
						if (klinik.deleteKlinik(selectedID)) {
							Helper.showMessage("success");
							updateKlinikModel();
						} else {
							Helper.showMessage("ERROR");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});

		table_klinik = new JTable(klinikModel);
		table_klinik.setBackground(Color.WHITE);
		table_klinik.setComponentPopupMenu(klinikMenu);
		table_klinik.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int seledtedRow = table_klinik.rowAtPoint(point);
				table_klinik.setRowSelectionInterval(seledtedRow, seledtedRow);
			}
		});
		scroll_klinik.setViewportView(table_klinik);

		JLabel lblNewLabel_1_4 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(249, 11, 104, 14);
		w_klinik.add(lblNewLabel_1_4);

		klinik_adi = new JTextField();
		klinik_adi.setColumns(10);
		klinik_adi.setBounds(249, 36, 171, 20);
		w_klinik.add(klinik_adi);

		JButton btn_KlinikEkle = new JButton("Ekle");
		btn_KlinikEkle.setBackground(Color.WHITE);
		btn_KlinikEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (klinik_adi.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {
					try {
						if (klinik.addKlinik(klinik_adi.getText())) {
							Helper.showMessage("success");
							klinik_adi.setText(null);
							updateKlinikModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_KlinikEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_KlinikEkle.setBounds(249, 67, 171, 23);
		w_klinik.add(btn_KlinikEkle);

		JScrollPane scroll_employee = new JScrollPane();
		scroll_employee.setBounds(430, 11, 229, 275);
		w_klinik.add(scroll_employee);
		
		table_employee = new JTable();
		table_employee.setBackground(Color.WHITE);
		scroll_employee.setViewportView(table_employee);
		
		JComboBox select_Doktor = new JComboBox();
		select_Doktor.setBackground(Color.WHITE);
		select_Doktor.setModel(new DefaultComboBoxModel(new String[] {"Doktor Se\u00E7"}));
		select_Doktor.setBounds(249, 227, 171, 22);
		for(int i=0;i<basHekim.getDoktorList().size();i++) {
			select_Doktor.addItem(new Item(basHekim.getDoktorList().get(i).getId(),basHekim.getDoktorList().get(i).getName())); //(comboboxdaki indis numaralarý yerine id dönecek)indis numaralrý yerine Item Class ý seçilen doktorun id ile alýnmasý için ayný isimde birden fazla doktor olma ihtimali var 
		}
		select_Doktor.addActionListener(e->{
			JComboBox c=(JComboBox) e.getSource();
			Item item= (Item) c.getSelectedItem();
		});
		w_klinik.add(select_Doktor);
		
		JButton btn_addEmployee = new JButton("Ekle");
		btn_addEmployee.setBackground(Color.WHITE);
		btn_addEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_klinik.getSelectedRow();
				if(selRow>=0) {
					String selKlinik=table_klinik.getModel().getValueAt(selRow, 0).toString();
					int selKlinikID=Integer.parseInt(selKlinik);
					Item doktorItem=(Item) select_Doktor.getSelectedItem();
					try {
						boolean kontrol=basHekim.addEmployee(doktorItem.getKey(), selKlinikID);
						if(kontrol) {
							Helper.showMessage("success");
							DefaultTableModel clearModel=(DefaultTableModel) table_employee.getModel();
							clearModel.setRowCount(0);
							for(int i=0; i<basHekim.getKlinikDoktorList(selKlinikID).size();i++) {
								employeeData[0]=basHekim.getKlinikDoktorList(selKlinikID).get(i).getId();
								employeeData[1]=basHekim.getKlinikDoktorList(selKlinikID).get(i).getName();
								employeeModel.addRow(employeeData);
							}
							table_employee.setModel(employeeModel);
						}else {
							Helper.showMessage("ERROR");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMessage("Lütfen bir poliklinik seçiniz");
				}
				
			}
		});
		btn_addEmployee.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_addEmployee.setBounds(249, 263, 171, 23);
		w_klinik.add(btn_addEmployee);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_4_1.setBounds(249, 134, 104, 14);
		w_klinik.add(lblNewLabel_1_4_1);
		
		JButton btn_sec = new JButton("Se\u00E7");
		btn_sec.setBackground(Color.WHITE);
		btn_sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int selRow=table_klinik.getSelectedRow();
			if(selRow>=0) {
				String selKlinik=table_klinik.getModel().getValueAt(selRow, 0).toString();
				int selKlinikID=Integer.parseInt(selKlinik);
				DefaultTableModel clearModel=(DefaultTableModel) table_employee.getModel();
				clearModel.setRowCount(0);
				try {
					for(int i=0; i<basHekim.getKlinikDoktorList(selKlinikID).size();i++) {
						employeeData[0]=basHekim.getKlinikDoktorList(selKlinikID).get(i).getId();
						employeeData[1]=basHekim.getKlinikDoktorList(selKlinikID).get(i).getName();
						employeeModel.addRow(employeeData);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table_employee.setModel(employeeModel);
			}else {
				Helper.showMessage("Lütfen bir poliklinik seçiniz");
			}
			}
		});
		btn_sec.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_sec.setBounds(249, 159, 171, 23);
		w_klinik.add(btn_sec);
		
		JLabel bg_1_1 = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
		bg_1_1.setBounds(0, 0, 669, 297);
		w_klinik.add(bg_1_1);
		
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("hast.png")));
		background.setBounds(0, 0, 894, 401);
		w_pane.add(background);
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < basHekim.getDoktorList().size(); i++) {
			doktorData[0] = basHekim.getDoktorList().get(i).getId();
			doktorData[1] = basHekim.getDoktorList().get(i).getName();
			doktorData[2] = basHekim.getDoktorList().get(i).getTcno();
			doktorData[3] = basHekim.getDoktorList().get(i).getPassword();
			doktorModel.addRow(doktorData);
		}
	}

	public void updateKlinikModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_klinik.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < klinik.getList().size(); i++) {
			klinikData[0] = klinik.getList().get(i).getId();
			klinikData[1] = klinik.getList().get(i).getName();
			klinikModel.addRow(klinikData);
		}
	}
	public void updateEmployeeModel(int id) throws SQLException {
	DefaultTableModel clearModel=(DefaultTableModel) table_employee.getModel();
	clearModel.setRowCount(0);
	for(int i=0; i<basHekim.getKlinikDoktorList(id).size();i++) {
		employeeData[0]=basHekim.getKlinikDoktorList(id).get(i).getId();
		employeeData[1]=basHekim.getKlinikDoktorList(id).get(i).getName();
		employeeModel.addRow(employeeData);
	}
	}
}
