package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import Model.Klinik;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Helper.*;
public class UpdateKlinikGUI extends JFrame {

	private JPanel contentPane;
	private JTextField klinikName;
	private static Klinik klinik;
	private JLabel bg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateKlinikGUI frame = new UpdateKlinikGUI(klinik);
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
	public UpdateKlinikGUI(Klinik klinik) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_4 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(10, 11, 104, 14);
		contentPane.add(lblNewLabel_1_4);
		
		klinikName = new JTextField();
		klinikName.setColumns(10);
		klinikName.setBounds(10, 36, 171, 27);
		klinikName.setText(klinik.getName());
		contentPane.add(klinikName);
		
		
		JButton btn_Duzenle = new JButton("D\u00FCzenle");
		btn_Duzenle.setBackground(Color.WHITE);
		btn_Duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(Helper.confirm("sure")) {
					try {
						klinik.updateKlinik(klinik.getId(), klinikName.getText());
						Helper.showMessage("success");
						dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btn_Duzenle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_Duzenle.setBounds(10, 73, 171, 27);
		contentPane.add(btn_Duzenle);
		
		bg = new JLabel(new ImageIcon(getClass().getResource("hast2.png")));
		bg.setBounds(0, 0, 209, 111);
		contentPane.add(bg);
	}
}
