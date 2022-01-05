package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JPanel;

import dao.menuDAO;
import dto.menuDTO;
import utilities.DBConnection;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class themMenu extends JPanel {
	private JTextField tften;
	private JTextField tfgia;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public themMenu() throws ClassNotFoundException, IOException, SQLException {
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(420, 327));
		this.setBounds(271, 115, 705, 479);
		setLayout(null);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		menuDAO menu_dao = new menuDAO(conn);
		
		JLabel lblNewLabel = new JLabel("Th\u00EAm M\u00F3n \u0102n");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(60, 10, 300, 49);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(60, 94, 110, 35);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Gi\u00E1 : ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(60, 175, 110, 35);
		add(lblNewLabel_1_1);
		
		tften = new JTextField();
		tften.setBounds(206, 94, 154, 35);
		add(tften);
		tften.setColumns(10);
		
		tfgia = new JTextField();
		tfgia.setColumns(10);
		tfgia.setBounds(206, 175, 154, 35);
		add(tfgia);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setForeground(new Color(220, 20, 60));
		btnThem.setFont(new Font("Monotype Corsiva", Font.BOLD, 35));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = tften.getText();
				Double gia = Double.parseDouble(tfgia.getText());
				menuDTO them = new menuDTO(ten, gia);
				try {
					menu_dao.themmenu(them);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.setBounds(149, 245, 110, 49);
		add(btnThem);
		


	}
}
