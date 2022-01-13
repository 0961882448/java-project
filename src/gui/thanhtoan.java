package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.banDAO;
import dao.menuDAO;
import dao.order_itemDAO;
import dto.banDTO;
import utilities.DBConnection;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;

@SuppressWarnings("serial")
public class thanhtoan extends JPanel {
	private JTable table;
	private JTable table_1;
	

	

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public thanhtoan() throws ClassNotFoundException, IOException, SQLException {
		
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));
		setBounds(271, 115, 705, 479);
		setLayout(null);
		this.setVisible(true);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		banDAO ban_dao = new banDAO(conn);
		menuDAO menu_dao = new menuDAO(conn); 
		//ordersDAO order_dao = new ordersDAO(conn);
		order_itemDAO item_dao = new order_itemDAO(conn);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 228, 459);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID B\u00E0n", "Tr\u1EA1ng th\u00E1i"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		ban_dao.getbanDSDtable(table, model);		
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(248, 10, 447, 292);
		add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ID Order", "M\u00F3n \u0103n", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u1EA3m gi\u00E1", "Gi\u00E1 ($)"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Integer.class, Double.class, Double.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JLabel tong = new JLabel("");
		tong.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tong.setBounds(621, 312, 74, 32);
		add(tong);
		
		JLabel lblTngGiTin = new JLabel("T\u1ED5ng gi\u00E1 ti\u1EC1n: ");
		lblTngGiTin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTngGiTin.setBounds(473, 312, 125, 32);
		add(lblTngGiTin);
		
		JButton btnXoa = new JButton(new ImageIcon(new ImageIcon("images/xoa.jpg").getImage().getScaledInstance(40, 40,20)));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				int r = table_1.getSelectedRow();
				if(r != -1) {
					int id = (int) table_1.getValueAt(r, 0);
					try {
						item_dao.xoaItem(id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					model.removeRow(r);
				}else if (r == -1) {
					JOptionPane.showMessageDialog(null,"Vui lòng chọn món ăn cần xoá.");
				}

			}
		});
		btnXoa.setBounds(248, 304, 40, 40);
		add(btnXoa);
		
	      table.addMouseListener(new MouseListener(){
	  		@Override
	  		public void mouseClicked(MouseEvent e) {
	  			// TODO Auto-generated method stub
	  			int r = table.getSelectedRow();	  			
	  			String ban = String.valueOf(table.getValueAt(r,0));	  			
	  			int idban = Integer.parseInt(ban);				
	  				DefaultTableModel df = (DefaultTableModel) table_1.getModel();		
	  				String query = "call new_idOrder(?);";	  				
					try {						
						DefaultTableModel m = (DefaultTableModel)table_1.getModel();
						m.getDataVector().removeAllElements();
						m.fireTableDataChanged();
						PreparedStatement stat = conn.prepareStatement(query);
						stat.setInt(1, idban);
		  				ResultSet result = stat.executeQuery();	
		  				if(result.next()){		  					
		  					do {
		  					int id = result.getInt(1);
		  					int idor = result.getInt(2);
		  					int idmenu = result.getInt(3);
		  					String tenmon = menu_dao.getMenuid(idmenu);		  					
		  					Double gia1donVi = menu_dao.getPriceMenu(tenmon);	  					
		  					int soluong = result.getInt(4);
		  					Double giamgia = result.getDouble(5);	
		  					Double gia = gia1donVi*soluong*(1-giamgia);
		  					df.addRow(new Object [] {id,idor, tenmon, soluong, giamgia, gia} );			
		  					} while (result.next());
		  					}
		  				int column = table_1.getColumnCount();
		  				//System.out.println(column);
						double sum = 0;
						for(int i=0 ; i<table_1.getRowCount(); i++) {
							Double tam = (double) table_1.getValueAt(i, column -1);					
							sum = sum + tam;					
						}
						String sumString = String.valueOf(sum);						
						tong.setText(sumString+" $");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  			
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
	      

	      
			JButton btnthanhtoan = new JButton(new ImageIcon(new ImageIcon("images/thantoan.png").getImage().getScaledInstance(85, 86,20)));
			btnthanhtoan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					int idban = getIDBan();
		  			String statu = "trống";
		  			banDTO upde = new banDTO(idban, statu);
		  			try {
						ban_dao.suaBan(upde);
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.getDataVector().removeAllElements();
						m.fireTableDataChanged();
						ban_dao.getbanDSDtable(table, model);
						DefaultTableModel m1 = (DefaultTableModel)table_1.getModel();
						m1.getDataVector().removeAllElements();
						m1.fireTableDataChanged();
						JOptionPane.showMessageDialog(null,"Thanh toán thành công");
					} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		  			
				}
			});
			btnthanhtoan.setForeground(Color.GREEN);
			btnthanhtoan.setBounds(341, 363, 85, 86);
			add(btnthanhtoan);
			
			JButton btnin = new JButton(new ImageIcon(new ImageIcon("images/in.jpg").getImage().getScaledInstance(85, 86,20)));
			btnin.setForeground(Color.GREEN);
			btnin.setBounds(513, 363, 85, 86);
			add(btnin);			
			
			JButton btnThem = new JButton(new ImageIcon(new ImageIcon("images/themmonan.jpg").getImage().getScaledInstance(80, 80,20)));
			btnThem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int r = table.getSelectedRow();					
					if(r != -1) {	
						String ban = String.valueOf(table.getValueAt(r,0));	  			
			  			int idban = Integer.parseInt(ban);
			  			int idor = 0;						
		  				String query = "call new_idOrder(?);";							
							try {								
								PreparedStatement stat= conn.prepareStatement(query);
								stat = conn.prepareStatement(query);
								stat.setInt(1, idban);
				  				ResultSet result = stat.executeQuery();	
				  				if(result.next()) {
				  					idor = result.getInt(2);
				  				}
				  				JOptionPane.showMessageDialog(null,new Order(idban, idor));	
							} catch (SQLException | HeadlessException | ClassNotFoundException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();							} 
					} else if (r == -1) {
						JOptionPane.showMessageDialog(null,"Vui lòng chọn bàn cần thêm.");
					} 	
				}
			});
			btnThem.setBounds(305, 304, 40, 40);
			add(btnThem);	
	}
	public int getIDBan() {
		int r = table.getSelectedRow();
		int idban = 0;
		if(r!= -1) {
			String ban = String.valueOf(table.getValueAt(r,0));
			idban = Integer.parseInt(ban);
			return idban;
		} else if ( r == -1) {
			JOptionPane.showMessageDialog(null,"Vui lòng chọn bàn cần thanh toán.");
		}
		return idban;						
		
	}
}
