package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import dao.NhanVienDAO;
import dao.banDAO;
import dao.order_itemDAO;
import dao.ordersDAO;
import dto.banDTO;
import dto.order_itemDTO;
import dto.ordersDTO;
import utilities.DBConnection;

@SuppressWarnings("serial")
public class Order extends JPanel {
	private JTable table;	
	Connection conn;
	ArrayList<String> menuListhish, nv, menuListban;
	 
	

	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings({ "removal", "rawtypes", "unchecked" })
	public Order() throws SQLException, ClassNotFoundException, IOException {
		
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));		
		this.setLayout(null);
		
		this.setBounds(271, 115, 705, 479);
		this.setVisible(true);
		
		
		DBConnection.init("database.properties");
		conn = DBConnection.getConnection();
		//ordersDAO orderss = new ordersDAO(conn);
		
		JPanel panel = new JPanel();		
		panel.setBounds(10, 10, 231, 304);
		panel.setBackground(Color.DARK_GRAY);
		add(panel);
		panel.setLayout(null);
		
		JTextArea giamgia = new JTextArea();
		giamgia.setBounds(101, 160, 120, 22);
		panel.add(giamgia);
		

		
		JLabel lbl_idorder = new JLabel("ID NV Orders");
		lbl_idorder.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
		lbl_idorder.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_idorder.setForeground(Color.WHITE);
		lbl_idorder.setBounds(-18, 10, 91, 22);
		panel.add(lbl_idorder);
		
		JLabel lbl_idmenu = new JLabel("Món Ăn");
		lbl_idmenu.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_idmenu.setForeground(Color.WHITE);
		lbl_idmenu.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
		lbl_idmenu.setBounds(-18, 57, 91, 22);
		panel.add(lbl_idmenu);
		
		JLabel lbl_soluong = new JLabel("Số Lượng");
		lbl_soluong.setFont(new Font("Times New Roman", Font.BOLD, 9));
		lbl_soluong.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_soluong.setForeground(Color.WHITE);
		lbl_soluong.setBounds(-18, 104, 91, 22);
		panel.add(lbl_soluong);
		
		JLabel lbl_giamgia = new JLabel("Giảm Giá");
		lbl_giamgia.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_giamgia.setForeground(Color.WHITE);
		lbl_giamgia.setFont(new Font("Times New Roman", Font.BOLD, 9));
		lbl_giamgia.setBounds(-18, 160, 91, 22);
		panel.add(lbl_giamgia);
		
		JLabel lbl_idban = new JLabel("ID Bàn");
		lbl_idban.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_idban.setForeground(Color.WHITE);
		lbl_idban.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
		lbl_idban.setBounds(-18, 213, 91, 22);
		panel.add(lbl_idban);
		//System.out.println(menuList);
		/****************************************************************************************************************************************************************/
		menuListhish = new ArrayList<String>(); 
		nv = new ArrayList<String>();
		menuListban = new ArrayList<String>();
		menuListban = getban();
		nv = getnv();
		menuListhish=getHish();
		String [] arraynv = nv.toArray(new String[nv.size()]);
		String[] arraymonan = menuListhish.toArray(new String[menuListhish.size()]);	
		String [] arrayban = menuListban.toArray(new String[menuListban.size()]);
		JComboBox monan = new JComboBox(arraymonan);
		monan.setBounds(101, 57, 120, 22);
		panel.add(monan);
		
		JSpinner soluong = new JSpinner();
		soluong.setBounds(101, 103, 120, 26);
		panel.add(soluong);
		soluong.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(495, 62, 186, 263);
		panel_2.setBackground(Color.DARK_GRAY);
		add(panel_2);
		panel_2.setLayout(null);
		
		String date = java.time.LocalDate.now().toString();		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		String time = dateFormat.format(cal.getTime());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(251, 10, 231, 304);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.DARK_GRAY);
		add(panel_1);
		
		JTextArea giamgia_1 = new JTextArea();
		giamgia_1.setBounds(101, 160, 120, 22);
		panel_1.add(giamgia_1);
		
		JLabel lbl_idmenu_1 = new JLabel("Món Ăn");
		lbl_idmenu_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_idmenu_1.setForeground(Color.WHITE);
		lbl_idmenu_1.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
		lbl_idmenu_1.setBounds(0, 57, 79, 22);
		panel_1.add(lbl_idmenu_1);
		
		JLabel lbl_soluong_1 = new JLabel("Số Lượng");
		lbl_soluong_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_soluong_1.setForeground(Color.WHITE);
		lbl_soluong_1.setFont(new Font("Times New Roman", Font.BOLD, 9));
		lbl_soluong_1.setBounds(0, 104, 79, 22);
		panel_1.add(lbl_soluong_1);
		
		JLabel lbl_giamgia_1 = new JLabel("Giảm Giá");
		lbl_giamgia_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_giamgia_1.setForeground(Color.WHITE);
		lbl_giamgia_1.setFont(new Font("Times New Roman", Font.BOLD, 9));
		lbl_giamgia_1.setBounds(0, 160, 79, 22);
		panel_1.add(lbl_giamgia_1);
		
		JComboBox monan_1 = new JComboBox(arraymonan);
		monan_1.setBounds(101, 57, 120, 22);
		panel_1.add(monan_1);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 324, 475, 145);
		add(scrollPane);
		JButton addItem1 = new JButton("Add Item");
		addItem1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		addItem1.setBackground(Color.GREEN);
		addItem1.setBounds(10, 257, 211, 35);
		panel.add(addItem1);
		
		JComboBox idnv_1 = new JComboBox(arraynv);
		idnv_1.setBounds(101, 11, 120, 22);
		panel.add(idnv_1);
		
		JComboBox idbancb1 = new JComboBox(arrayban);
		idbancb1.setBounds(101, 213, 120, 22);
		panel.add(idbancb1);
		addItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String idnv = idnv_1.getSelectedItem().toString();				
				String name = monan.getSelectedItem().toString();
				int price = (int) soluong.getValue();
				Double discount = Double.parseDouble(giamgia.getText());					
					try {						
						String query = "SELECT gia FROM menu where ten_mon = ?";
						PreparedStatement stat = conn.prepareStatement(query);
						stat.setString(1, name);
						ResultSet result = stat.executeQuery();
						if(result.next()) {
							Double gia = result.getDouble(1);						
							Double giaTien = gia*price*(1-discount);
							model.addRow(new Object [] {idnv, name, price, discount, giaTien} );
						}	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		JButton additem2 = new JButton("Add Item");
		additem2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		additem2.setBackground(Color.GREEN);
		additem2.setBounds(10, 257, 211, 35);
		panel_1.add(additem2);
		JSpinner soluong_1 = new JSpinner();
		soluong_1.setBounds(101, 103, 120, 26);
		panel_1.add(soluong_1);
		

		additem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String idnv2 = idnv_1.getSelectedItem().toString();				
				String name2 = monan_1.getSelectedItem().toString();
				int price2 = (int) soluong_1.getValue();
				Double discount2 = Double.parseDouble(giamgia_1.getText());
							
					try {
						String query = "SELECT gia FROM menu where ten_mon = ?";
						PreparedStatement stat = conn.prepareStatement(query);
						stat.setString(1, name2);
						ResultSet result = stat.executeQuery();
						if(result.next()) {
							Double gia2 = result.getDouble(1);						
							Double giaTien = gia2*price2*(1-discount2);
							model.addRow(new Object [] {idnv2, name2, price2, discount2, giaTien} );
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		

		JTextArea lblnv = new JTextArea();
		lblnv.setFont(new Font("Monospaced", Font.BOLD, 15));
		lblnv.setBounds(492, 10, 186, 31);
		//textArea.setDropMode(DropMode.ON);
		add(lblnv);
		
		JLabel lblDate = new JLabel(date);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(10, 10, 166, 69);
		panel_2.add(lblDate);
		
		JLabel lblTime = new JLabel(time);
		lblTime.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(10, 89, 166, 69);
		panel_2.add(lblTime);		

		JLabel lblSum = new JLabel("Price: "+"00.00"+"$");
		lblSum.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSum.setForeground(Color.WHITE);
		lblSum.setBounds(10, 168, 166, 69);
		panel_2.add(lblSum);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Nh\u00E2n Vi\u00EAn", "T\u00EAn m\u00F3n", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u1EA3m gi\u00E1", "Th\u00E0nh ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		/****************************************************************************************************************************************************************/
		int Ido = getIdOrder();
		String idOr = String.valueOf(Ido);
		JLabel lblIdOrder = new JLabel(idOr);
		lblIdOrder.setForeground(Color.GREEN);
		lblIdOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdOrder.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblIdOrder.setBounds(541, 335, 89, 47);
		add(lblIdOrder);
		
		int column = table.getColumnCount();
		//int row = table.getRowCount();

		
		JButton tailai = new JButton(new ImageIcon(new ImageIcon("images/play.png").getImage().getScaledInstance(20, 20,20)));
		tailai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nv1 = idnv_1.getSelectedItem().toString();
				double sum = 0;
				for(int i=0 ; i<table.getRowCount(); i++) {
					Double tam = (double) table.getValueAt(i, column-1);					
					sum = sum + tam;					
				}
				String sumString = String.valueOf(sum);
				lblDate.setText(date);
				lblTime.setText(time);				
				lblSum.setText("Price: "+sumString+"$");				
				lblnv.setText(nv1);
			}
		});
		

		tailai.setBounds(491, 373, 26, 21);
		add(tailai);
		
		scrollPane.setViewportView(table);				
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order_itemDAO item_dao = new order_itemDAO(conn);
				ordersDAO orders_dao = new ordersDAO(conn);
				NhanVienDAO nhanvien_dao = new NhanVienDAO(conn);
				banDAO ban_dao = new banDAO(conn);				
				String id1 =  idbancb1.getSelectedItem().toString();
				int id_ban = Integer.parseInt(id1);
				int idOr = Integer.parseInt(lblIdOrder.getText());			
					try {
						for(int i=0 ; i<table.getRowCount(); i++) {							
							String hishName = (String) table.getValueAt(i, 1);
							int quantyti = (int) table.getValueAt(i, 2);
							Double discount = (Double) table.getValueAt(i, 3);
							System.out.println(quantyti);
								String query = "SELECT id FROM menu where ten_mon = ?";
								PreparedStatement stat = conn.prepareStatement(query);
								stat.setString(1, hishName);
								ResultSet result = stat.executeQuery();
								if (result.next()){
									int idmn = result.getInt(1);									
									order_itemDTO item = new order_itemDTO(idOr, idmn, quantyti, discount, id_ban);								
									item_dao.themItem(item);
								}
						}
						String idnvor = idnv_1.getSelectedItem().toString();						
						int idnv = nhanvien_dao.layIdNhanvienhoTen(idnvor);						
						LocalDate ngayor = LocalDate.parse(lblDate.getText());
						
						String gioor = lblTime.getText();
						int column = table.getColumnCount();				
						double sum = 0;
						for(int i=0 ; i<table.getRowCount(); i++) {
							Double tam = (double) table.getValueAt(i, column-1);					
							sum = sum + tam;					
						}
						
						ordersDTO order = new ordersDTO(idnv, ngayor, gioor, sum);
						orders_dao.themOrders(order);						
						String status = "Đang sữ dụng";
						banDTO ban = new banDTO(id_ban, status);
						ban_dao.suaBan(ban);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidKeySpecException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnOrder.setFont(new Font("Monotype Corsiva", Font.BOLD, 50));
		btnOrder.setForeground(Color.RED);
		btnOrder.setBounds(502, 404, 163, 65);
		add(btnOrder);
		

		
		JButton xoa = new JButton(new ImageIcon(new ImageIcon("images/xoa.jpg").getImage().getScaledInstance(20, 20,20)));
		xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int r = table.getSelectedRow();
				model.removeRow(r);
				
				
			}
		});
		xoa.setBounds(491, 335, 26, 21);
		add(xoa);
		

		this.setVisible(true);
	}	
	
	@SuppressWarnings({ "removal", "rawtypes", "unchecked" })
	public Order(int u, int y) throws SQLException, ClassNotFoundException, IOException {
		
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));		
		this.setLayout(null);
		this.setBounds(271, 115, 705, 479);
		this.setVisible(true);
		
		DBConnection.init("database.properties");
		conn = DBConnection.getConnection();
		//ordersDAO orderss = new ordersDAO(conn);
		
		JPanel panel = new JPanel();		
		panel.setBounds(10, 10, 231, 304);
		panel.setBackground(Color.DARK_GRAY);
		add(panel);
		panel.setLayout(null);
		
		JTextArea giamgia = new JTextArea();
		giamgia.setBounds(101, 160, 120, 22);
		panel.add(giamgia);
		

		
		JLabel lbl_idorder = new JLabel("ID NV Orders");
		lbl_idorder.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
		lbl_idorder.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_idorder.setForeground(Color.WHITE);
		lbl_idorder.setBounds(-18, 10, 91, 22);
		panel.add(lbl_idorder);
		
		JLabel lbl_idmenu = new JLabel("Món Ăn");
		lbl_idmenu.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_idmenu.setForeground(Color.WHITE);
		lbl_idmenu.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
		lbl_idmenu.setBounds(-18, 57, 91, 22);
		panel.add(lbl_idmenu);
		
		JLabel lbl_soluong = new JLabel("Số Lượng");
		lbl_soluong.setFont(new Font("Times New Roman", Font.BOLD, 9));
		lbl_soluong.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_soluong.setForeground(Color.WHITE);
		lbl_soluong.setBounds(-18, 104, 91, 22);
		panel.add(lbl_soluong);
		
		JLabel lbl_giamgia = new JLabel("Giảm Giá");
		lbl_giamgia.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_giamgia.setForeground(Color.WHITE);
		lbl_giamgia.setFont(new Font("Times New Roman", Font.BOLD, 9));
		lbl_giamgia.setBounds(-18, 160, 91, 22);
		panel.add(lbl_giamgia);
		
		JLabel lbl_idban = new JLabel("ID Bàn");
		lbl_idban.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_idban.setForeground(Color.WHITE);
		lbl_idban.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
		lbl_idban.setBounds(-18, 213, 91, 22);
		panel.add(lbl_idban);
		//System.out.println(menuList);
		/****************************************************************************************************************************************************************/
		menuListhish = new ArrayList<String>(); 
		nv = new ArrayList<String>();
		menuListban = new ArrayList<String>();
		menuListban = getban();
		nv = getnv();
		menuListhish=getHish();
		String [] arraynv = nv.toArray(new String[nv.size()]);
		String[] arraymonan = menuListhish.toArray(new String[menuListhish.size()]);	
		//String [] arrayban = menuListban.toArray(new String[menuListban.size()]);
		JComboBox monan = new JComboBox(arraymonan);
		monan.setBounds(101, 57, 120, 22);
		panel.add(monan);
		
		JSpinner soluong = new JSpinner();
		soluong.setBounds(101, 103, 120, 26);
		panel.add(soluong);
		soluong.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(495, 62, 186, 263);
		panel_2.setBackground(Color.DARK_GRAY);
		add(panel_2);
		panel_2.setLayout(null);
		
		String date = java.time.LocalDate.now().toString();		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		String time = dateFormat.format(cal.getTime());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(251, 10, 231, 304);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.DARK_GRAY);
		add(panel_1);
		
		JTextArea giamgia_1 = new JTextArea();
		giamgia_1.setBounds(101, 160, 120, 22);
		panel_1.add(giamgia_1);
		
		JLabel lbl_idmenu_1 = new JLabel("Món Ăn");
		lbl_idmenu_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_idmenu_1.setForeground(Color.WHITE);
		lbl_idmenu_1.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
		lbl_idmenu_1.setBounds(0, 57, 79, 22);
		panel_1.add(lbl_idmenu_1);
		
		JLabel lbl_soluong_1 = new JLabel("Số Lượng");
		lbl_soluong_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_soluong_1.setForeground(Color.WHITE);
		lbl_soluong_1.setFont(new Font("Times New Roman", Font.BOLD, 9));
		lbl_soluong_1.setBounds(0, 104, 79, 22);
		panel_1.add(lbl_soluong_1);
		
		JLabel lbl_giamgia_1 = new JLabel("Giảm Giá");
		lbl_giamgia_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_giamgia_1.setForeground(Color.WHITE);
		lbl_giamgia_1.setFont(new Font("Times New Roman", Font.BOLD, 9));
		lbl_giamgia_1.setBounds(0, 160, 79, 22);
		panel_1.add(lbl_giamgia_1);
		
		JComboBox monan_1 = new JComboBox(arraymonan);
		monan_1.setBounds(101, 57, 120, 22);
		panel_1.add(monan_1);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 324, 475, 145);
		add(scrollPane);
		JButton addItem1 = new JButton("Add Item");
		addItem1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		addItem1.setBackground(Color.GREEN);
		addItem1.setBounds(10, 257, 211, 35);
		panel.add(addItem1);
		
		JComboBox idnv_1 = new JComboBox(arraynv);
		idnv_1.setBounds(101, 11, 120, 22);
		panel.add(idnv_1);
		
		String ban = String.valueOf(u);
		String [] banarray = {ban};
		JComboBox idbancb1 = new JComboBox(banarray);
		idbancb1.setBounds(101, 213, 120, 22);
		panel.add(idbancb1);
		addItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String idnv = idnv_1.getSelectedItem().toString();				
				String name = monan.getSelectedItem().toString();
				int price = (int) soluong.getValue();
				Double discount = Double.parseDouble(giamgia.getText());					
					try {						
						String query = "SELECT gia FROM menu where ten_mon = ?";
						PreparedStatement stat = conn.prepareStatement(query);
						stat.setString(1, name);
						ResultSet result = stat.executeQuery();
						if(result.next()) {
							Double gia = result.getDouble(1);						
							Double giaTien = gia*price*(1-discount);
							model.addRow(new Object [] {idnv, name, price, discount, giaTien} );
						}	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		JButton additem2 = new JButton("Add Item");
		additem2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		additem2.setBackground(Color.GREEN);
		additem2.setBounds(10, 257, 211, 35);
		panel_1.add(additem2);
		JSpinner soluong_1 = new JSpinner();
		soluong_1.setBounds(101, 103, 120, 26);
		panel_1.add(soluong_1);
		

		additem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String idnv2 = idnv_1.getSelectedItem().toString();				
				String name2 = monan_1.getSelectedItem().toString();
				int price2 = (int) soluong_1.getValue();
				Double discount2 = Double.parseDouble(giamgia_1.getText());
							
					try {
						String query = "SELECT gia FROM menu where ten_mon = ?";
						PreparedStatement stat = conn.prepareStatement(query);
						stat.setString(1, name2);
						ResultSet result = stat.executeQuery();
						if(result.next()) {
							Double gia2 = result.getDouble(1);						
							Double giaTien = gia2*price2*(1-discount2);
							model.addRow(new Object [] {idnv2, name2, price2, discount2, giaTien} );
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		

		JTextArea lblnv = new JTextArea();
		lblnv.setFont(new Font("Monospaced", Font.BOLD, 15));
		lblnv.setBounds(492, 10, 186, 31);
		//textArea.setDropMode(DropMode.ON);
		add(lblnv);
		
		JLabel lblDate = new JLabel(date);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(10, 10, 166, 69);
		panel_2.add(lblDate);
		
		JLabel lblTime = new JLabel(time);
		lblTime.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(10, 89, 166, 69);
		panel_2.add(lblTime);		

		JLabel lblSum = new JLabel("Price: "+"00.00"+"$");
		lblSum.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSum.setForeground(Color.WHITE);
		lblSum.setBounds(10, 168, 166, 69);
		panel_2.add(lblSum);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Nh\u00E2n Vi\u00EAn", "T\u00EAn m\u00F3n", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u1EA3m gi\u00E1", "Th\u00E0nh ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		/****************************************************************************************************************************************************************/
		//int Ido = getIdOrder();
		String idOr = String.valueOf(y);	
		JLabel lblIdOrder = new JLabel(idOr);
		lblIdOrder.setForeground(Color.GREEN);
		lblIdOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdOrder.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblIdOrder.setBounds(541, 335, 89, 47);
		add(lblIdOrder);
		
		int column = table.getColumnCount();
		//int row = table.getRowCount();

		
		JButton tailai = new JButton(new ImageIcon(new ImageIcon("images/play.png").getImage().getScaledInstance(20, 20,20)));
		tailai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nv1 = idnv_1.getSelectedItem().toString();
				double sum = 0;
				for(int i=0 ; i<table.getRowCount(); i++) {
					Double tam = (double) table.getValueAt(i, column-1);					
					sum = sum + tam;					
				}
				String sumString = String.valueOf(sum);
				lblDate.setText(date);
				lblTime.setText(time);				
				lblSum.setText("Price: "+sumString+"$");				
				lblnv.setText(nv1);
			}
		});
		

		tailai.setBounds(491, 373, 26, 21);
		add(tailai);
		
		scrollPane.setViewportView(table);				
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order_itemDAO item_dao = new order_itemDAO(conn);
				ordersDAO orders_dao = new ordersDAO(conn);
				NhanVienDAO nhanvien_dao = new NhanVienDAO(conn);
				banDAO ban_dao = new banDAO(conn);				
				String id1 =  idbancb1.getSelectedItem().toString();
				int id_ban = Integer.parseInt(id1);
				int idOr = Integer.parseInt(lblIdOrder.getText());			
					try {
						for(int i=0 ; i<table.getRowCount(); i++) {							
							String hishName = (String) table.getValueAt(i, 1);
							int quantyti = (int) table.getValueAt(i, 2);
							Double discount = (Double) table.getValueAt(i, 3);
								String query = "SELECT id FROM menu where ten_mon = ?";
								PreparedStatement stat = conn.prepareStatement(query);
								stat.setString(1, hishName);
								ResultSet result = stat.executeQuery();
								if (result.next()){
									int idmn = result.getInt(1);									
									order_itemDTO item = new order_itemDTO(idOr, idmn, quantyti, discount, id_ban);								
									item_dao.themItem(item);
								}
						}
						String idnvor = idnv_1.getSelectedItem().toString();						
						int idnv = nhanvien_dao.layIdNhanvienhoTen(idnvor);
						System.out.println(idnvor);
						System.out.println(idnv);
						LocalDate ngayor = LocalDate.parse(lblDate.getText());
						
						String gioor = lblTime.getText();
						int column = table.getColumnCount();				
						double sum = 0;
						for(int i=0 ; i<table.getRowCount(); i++) {
							Double tam = (double) table.getValueAt(i, column-1);					
							sum = sum + tam;					
						}
						
						ordersDTO order = new ordersDTO(idnv, ngayor, gioor, sum);
						orders_dao.themOrders(order);						
						String status = "Đang sữ dụng";
						banDTO ban = new banDTO(id_ban, status);
						ban_dao.suaBan(ban);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidKeySpecException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnOrder.setFont(new Font("Monotype Corsiva", Font.BOLD, 50));
		btnOrder.setForeground(Color.RED);
		btnOrder.setBounds(502, 404, 163, 65);
		add(btnOrder);
		

		
		JButton xoa = new JButton(new ImageIcon(new ImageIcon("images/xoa.jpg").getImage().getScaledInstance(20, 20,20)));
		xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int r = table.getSelectedRow();
				model.removeRow(r);
				
				
			}
		});
		xoa.setBounds(491, 335, 26, 21);
		add(xoa);
		

		this.setVisible(true);
	}	
	
	public int getIdOrder() throws SQLException, ClassNotFoundException, IOException {
		int hish = 0;
		String query = "call get_new_order(@new_id)";
		PreparedStatement stat = conn.prepareStatement(query);
		ResultSet result = stat.executeQuery();
		if (result.next()){			
			hish = result.getInt(1);
			return hish;
		}			
		return hish;	
	}
	
	public ArrayList<String> getHish() throws ClassNotFoundException, IOException, SQLException{		
		String query = "Select ten_mon from menu;";
		PreparedStatement stat = conn.prepareStatement(query);		
		ResultSet result = stat.executeQuery();		
		boolean check = false;
		ArrayList<String> menuList = new ArrayList<String>();
		if(result.next()) {
			check = true;						
			do {
				String hish = result.getString(1);				
				menuList.add(hish);				
			}while (result.next());
		}
		return menuList;			
	}
	
	public ArrayList<String> getban() throws SQLException{
		String query = "SELECT id FROM nhahang.ban where statuss = 'trống';";
		PreparedStatement stat = conn.prepareStatement(query);		
		ResultSet result = stat.executeQuery();	
		boolean check = false;
		ArrayList<String> menuban = new ArrayList<String>(); 
		if(result.next()) {
			check = true;						
			do {
				String idban = result.getString(1);				
				menuban.add(idban);				
			}while (result.next());
		}
		return menuban;
	}
	
	public ArrayList<String> getnv() throws SQLException{
		String query = "select hoTen from nhanvien;";
		PreparedStatement stat = conn.prepareStatement(query);	
		ResultSet result = stat.executeQuery();		
		boolean check = false;
		ArrayList<String> nvList = new ArrayList<String>();
		if(result.next()) {
			check = true;						
			do {
				String hish = result.getString(1);				
				nvList.add(hish);				
			}while (result.next());
		}
		return nvList;		
		}
}
