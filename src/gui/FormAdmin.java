package gui;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagConstraints;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;



@SuppressWarnings("serial")
public class FormAdmin extends JFrame {

	private JPanel contentPane;
	private final JSeparator separator = new JSeparator();
	private JPanel panel;
	private JPanel panelChinh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAdmin frame = new FormAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param <Dim>
	 */
	public <Dim> FormAdmin() {
		getContentPane().setLayout(null);
		this.setVisible(true);

		String ngay = java.time.LocalDate.now().toString();		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		String gio = dateFormat.format(cal.getTime());
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		separator.setBounds(0, 103, 986, 2);
		contentPane.add(separator);

		
		panel = new JPanel();
		panel.setBounds(10, 115, 251, 479);
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btAddAdmin = new JButton("Admin");
		btAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//panel.getParent().remove( new NhanVien());
				panel.getParent().remove(panelChinh);
				panel.getParent().revalidate();		
				try {					
					panelChinh = new admin();
					panel.getParent().add(panelChinh);	
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block					
					e1.printStackTrace();
				}
				panel.getParent().repaint();
			}
		});
		btAddAdmin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btAddAdmin.setBounds(10, 229, 231, 32);
		btAddAdmin.setBackground(Color.GREEN);
		panel.add(btAddAdmin);
		
		JButton btnDoangthu = new JButton("Doanh Thu");
		btnDoangthu.setFont(new Font("Times New Roman", Font.BOLD, 20));
//		btnOrder.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {			
//					// Load panel1 vao vi tri cua contentPanel				
//					btnOrder.getParent().remove(panelChinh);
//					btnOrder.getParent().revalidate();
//					
//					//c.fill = GridBagConstraints.BOTH;			
//					try {
//					//	btnOrder.getParent().add(new Order().setBound(271, 115, 705, 479));
//						//Order.setBounds(271, 115, 705, 479);
//					} catch (ClassNotFoundException | SQLException | IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					btnOrder.getParent().repaint();
//				}	
//			
//		});
		btnDoangthu.setBounds(10, 37, 231, 32);
		btnDoangthu.setBackground(Color.GREEN);
		panel.add(btnDoangthu);
		
		JButton btAddNV = new JButton("Nhân Viên");
		btAddNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.getParent().remove(panelChinh);
				panel.getParent().revalidate();		
				try {	
					panelChinh = new NhanVien();
					panel.getParent().add(panelChinh);	
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block					
					e1.printStackTrace();
				}
				panel.getParent().repaint();
			}
		});
		btAddNV.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btAddNV.setBounds(10, 135, 231, 32);
		btAddNV.setBackground(new Color(127, 255, 0));
		panel.add(btAddNV);
		
		panelChinh = new JPanel();
		panelChinh.setBounds(271, 115, 705, 479);
		panelChinh.setBackground(Color.DARK_GRAY);
		contentPane.add(panelChinh);
		panelChinh.setLayout(null);
		JButton btndx = new JButton(new ImageIcon(new ImageIcon("images/dangxuat.jpg").getImage().getScaledInstance(10, 20,20)));
		btndx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new form_dangnhap();
				dispose();
			}
		});
		btndx.setBounds(943, 0, 43, 32);
		contentPane.add(btndx);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 986, 105);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAnh = new JLabel(new ImageIcon(new ImageIcon("images/abd2.jpg").getImage().getScaledInstance(986, 105,20)));
		lblAnh.setForeground(UIManager.getColor("textText"));
		//lblAnh.setBackground(Color.TRANSLUCENT);
		lblAnh.setBounds(0, 0, 986, 105);
		panel_1.add(lblAnh);	

	}
}
