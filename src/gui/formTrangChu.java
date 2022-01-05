package gui;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilities.DBConnection;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Font;



@SuppressWarnings("serial")
public class formTrangChu extends JFrame {

	private JPanel contentPane;
	private final JSeparator separator = new JSeparator();
	private JPanel panel;
	private JPanel panelChinh, currentPanel;
	GridBagConstraints c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formTrangChu frame = new formTrangChu();
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
	public <Dim> formTrangChu() {
		getContentPane().setLayout(null);
		this.setTitle("Quản lý nhà hàng");
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
		
		
		JButton btnBan = new JButton("BÀN");
		btnBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.getParent().remove(panelChinh);				
				panel.getParent().revalidate();		
				try {
					
					panel.getParent().add(new ban());	
					
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block					
					e1.printStackTrace();
				}
				panel.getParent().repaint();				
			}
		});
		btnBan.setForeground(Color.BLACK);
		btnBan.setBounds(10, 203, 231, 32);
		btnBan.setBackground(Color.GREEN);
		panel.add(btnBan);
		/*************************************************************************************************************************************************************************/
		JButton btnOrder = new JButton("ORDER");
		btnOrder.setForeground(Color.BLACK);
		btnOrder.setBounds(10, 37, 231, 32);
		btnOrder.setBackground(Color.GREEN);
		panel.add(btnOrder);
		btnOrder.addActionListener(new ActionListener() {
			@Override
		public void actionPerformed(ActionEvent e) {

				// Load panel1 vao vi tri cua contentPanel	
				panel.getParent().remove(panelChinh);
				panel.getParent().revalidate();		
				try {					
					panel.getParent().add(new Order());	
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block					
					e1.printStackTrace();
				}
				panel.getParent().repaint();
		}	
	});
		
		JButton btnMenu = new JButton("MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.getParent().remove(panelChinh);
				panel.getParent().revalidate();		
				try {					
					panel.getParent().add(new menu());	
					
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block					
					e1.printStackTrace();
				}
				panel.getParent().repaint();
			}
		});
		btnMenu.setForeground(Color.BLACK);
		btnMenu.setBounds(10, 369, 231, 32);
		btnMenu.setBackground(new Color(127, 255, 0));
		panel.add(btnMenu);
		
		JButton btnthanhtoan = new JButton("THANH TOÁN");
		btnthanhtoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.getParent().remove(panelChinh);
				panel.getParent().revalidate();		
				try {					
					panel.getParent().add(new thanhtoan());	
					
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block					
					e1.printStackTrace();
				}
				panel.getParent().repaint();
			}
		});
		btnthanhtoan.setForeground(Color.BLACK);
		btnthanhtoan.setBounds(10, 120, 231, 32);
		btnthanhtoan.setBackground(new Color(127, 255, 0));
		panel.add(btnthanhtoan);
		
		JButton btnNewButton_4 = new JButton("NGUYÊN LIỆU");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.getParent().remove(panelChinh);
				panel.getParent().revalidate();		
				try {					
					panel.getParent().add(new nguyenlieu());	
					
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block					
					e1.printStackTrace();
				}
				panel.getParent().repaint();
			}
		});
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setBounds(10, 286, 231, 32);
		btnNewButton_4.setBackground(new Color(127, 255, 0));
		panel.add(btnNewButton_4);
		
		JButton btnitem = new JButton("ĐƠN");
		btnitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.getParent().remove(panelChinh);
				panel.getParent().revalidate();		
				try {					
					panel.getParent().add(new Don());	
					
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block					
					e1.printStackTrace();
				}
				panel.getParent().repaint();
			}
		});
		btnitem.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnitem.setBackground(Color.GREEN);
		btnitem.setBounds(10, 437, 231, 32);
		panel.add(btnitem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 986, 105);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAnh = new JLabel(new ImageIcon(new ImageIcon("images/abd2.jpg").getImage().getScaledInstance(986, 105,20)));
		lblAnh.setForeground(UIManager.getColor("textText"));
		lblAnh.setBounds(0, 0, 986, 105);
		panel_1.add(lblAnh);		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(884, 69, 102, 36);
		panel_1.add(lblNewLabel);
		
	}
    private void removeAllJLabel(JPanel panel) {
        panel.removeAll();
        panel.validate();
        panel.repaint();       
    }
}
