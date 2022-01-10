package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import dao.nguyenlieuDAO;
import dto.nguyenlieuDTO;
import utilities.DBConnection;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class themNguyenLieu extends JPanel {
	private JTextField tften;
	private JTextField tfgia;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public themNguyenLieu() throws ClassNotFoundException, IOException, SQLException {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(596, 400));
		setLayout(null);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		nguyenlieuDAO nguyenlieu_dao = new nguyenlieuDAO(conn);
		
		JLabel lblNewLabel = new JLabel("TH\u00CAM NGUY\u00CAN LI\u1EC6U");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 10, 374, 60);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn nguy\u00EAn li\u1EC7u:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(57, 93, 187, 45);
		add(lblNewLabel_1);
		
		tften = new JTextField();
		tften.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tften.setBounds(272, 93, 247, 45);
		add(tften);
		tften.setColumns(10);
		
		tfgia = new JTextField();
		tfgia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfgia.setColumns(10);
		tfgia.setBounds(272, 163, 247, 45);
		add(tfgia);
		
		JLabel lblNewLabel_1_1 = new JLabel("Gi\u00E1 nguy\u00EAn li\u1EC7u (1 \u0111\u01A1n v\u1ECB):");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 163, 234, 45);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(57, 229, 187, 45);
		add(lblNewLabel_1_2);
		JSpinner tfsl = new JSpinner();
		tfsl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfsl.setBounds(272, 229, 247, 45);
		add(tfsl);
		
		JButton btnthem = new JButton("TH\u00CAM");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = tften.getText();
				Double gia = Double.parseDouble(tfgia.getText());
				int sol = (int) tfsl.getValue();
				LocalDate ngay = java.time.LocalDate.now();
				
				
				try {
					nguyenlieuDTO them = new nguyenlieuDTO(ten, gia,sol,ngay);
					nguyenlieu_dao.themNguyenLieu(them);
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnthem.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		btnthem.setBackground(Color.GREEN);
		btnthem.setForeground(Color.RED);
		btnthem.setBounds(221, 312, 124, 45);
		add(btnthem);
	}
}
