package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JPanel;

import utilities.DBConnection;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.menuDAO;
import dto.menuDTO;
import dto.nguyenlieuDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class menu extends JPanel {
	private JTable table;
	private JTextField tften;
	private JTextField tfgia;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@SuppressWarnings("serial")
	public menu() throws ClassNotFoundException, IOException, SQLException {
		
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));
		this.setBounds(271, 115, 705, 479);
		setLayout(null);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		menuDAO menu_dao = new menuDAO(conn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 525, 379);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn m\u00F3n \u0103n", "Gi\u00E1 ($)"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		menu_dao.getmenutable(table, model);
		scrollPane.setViewportView(table);
		
		JButton btnthem = new JButton(new ImageIcon(new ImageIcon("images/them.png").getImage().getScaledInstance(150, 150,20)));
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showInputDialog(null,new themMenu());
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					menu_dao.getmenutable(table, model);
				} catch (HeadlessException | ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnthem.setBounds(545, 10, 150, 150);
		add(btnthem);
		
		JButton btnsua = new JButton(new ImageIcon(new ImageIcon("images/sua.jpg").getImage().getScaledInstance(150, 150,20)));
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				String ten = tften.getText();
				double gia = Double.parseDouble(tfgia.getText());		
				menuDTO neww = new menuDTO(r,ten, gia);
				try {
					menu_dao.suaMenu(neww);					
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					menu_dao.getmenutable(table, model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnsua.setBounds(545, 171, 150, 138);
		add(btnsua);
		

		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 399, 172, 23);
		add(lblNewLabel);
		
		JLabel lblTnMnn = new JLabel("T\u00EAn m\u00F3n \u0103n");
		lblTnMnn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTnMnn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTnMnn.setBounds(189, 399, 172, 23);
		add(lblTnMnn);
		
		JLabel lblGi = new JLabel("Gi\u00E1 ($)");
		lblGi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGi.setHorizontalAlignment(SwingConstants.CENTER);
		lblGi.setBounds(363, 399, 172, 23);
		add(lblGi);
		
		JLabel tfid = new JLabel("");
		tfid.setForeground(Color.RED);
		tfid.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tfid.setHorizontalAlignment(SwingConstants.CENTER);
		tfid.setBounds(10, 432, 172, 37);
		add(tfid);
		
		tften = new JTextField();
		tften.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tften.setHorizontalAlignment(SwingConstants.CENTER);
		tften.setBounds(189, 432, 172, 37);
		add(tften);
		tften.setColumns(10);
		
		tfgia = new JTextField();
		tfgia.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tfgia.setHorizontalAlignment(SwingConstants.CENTER);
		tfgia.setColumns(10);
		tfgia.setBounds(363, 432, 172, 37);
		add(tfgia);
		
		JButton btnxoa = new JButton(new ImageIcon(new ImageIcon("images/xoa.jpg").getImage().getScaledInstance(150, 150,20)));
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = Integer.parseInt(tfid.getText());
				System.out.println(r);
				try {
					menu_dao.xoaMenu(r);
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					menu_dao.getmenutable(table, model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnxoa.setBounds(545, 319, 150, 150);
		add(btnxoa);
		
		 table.addMouseListener(new MouseListener(){
		  		@Override
		  		public void mouseClicked(MouseEvent e) {
		  			// TODO Auto-generated method stub
		  			int r = table.getSelectedRow();
		  			int r_model = -1;
		  			if (r != -1){
		  				r_model = table.convertRowIndexToModel(r);
		  			}
		  			tfid.setText(String.valueOf(table.getValueAt(r_model,0)));
		  			tften.setText(String.valueOf(table.getValueAt(r_model,1)));
		  			tfgia.setText(String.valueOf(table.getValueAt(r_model,2)));
		  		}
		  		@Override
		  		public void mouseEntered(MouseEvent e) {
		  			// TODO Auto-generated method stub	  			
		  		}
		  		@Override
		  		public void mouseExited(MouseEvent e) {
		  			// TODO Auto-generated method stub	  			
		  		}
		  		@Override
		  		public void mousePressed(MouseEvent e) {
		  			// TODO Auto-generated method stub	  			
		  		}
		  		@Override
		  		public void mouseReleased(MouseEvent e) {
		  			// TODO Auto-generated method stub	  			
		  		}	      	  
		        });
		

	}
}
