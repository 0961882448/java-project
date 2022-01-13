package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import utilities.DBConnection;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class doangthu extends JPanel {
	Connection conn;
	ArrayList<String> menuNam;
	@SuppressWarnings("rawtypes")
	private JComboBox CBnam;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public doangthu() throws ClassNotFoundException, IOException, SQLException {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(705, 479));
		setBounds(271, 115, 705, 479);
		setLayout(null);
		
		DBConnection.init("database.properties");
		conn = DBConnection.getConnection();
		
		menuNam = new ArrayList<String>(); 
		menuNam=getnam();
		String [] arrayNam = menuNam.toArray(new String[menuNam.size()]);
		CBnam = new JComboBox(arrayNam);
		CBnam.setToolTipText("N\u0103m");
		CBnam.setBounds(583, 10, 112, 31);
		add(CBnam);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 685, 348);
		add(panel);
		

		
		JButton btnThu = new JButton("TỔNG THU");
		btnThu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThu.setForeground(new Color(255, 255, 0));
		btnThu.setBackground(new Color(0, 0, 205));
		btnThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nam = CBnam.getSelectedItem().toString();
				int namchon = Integer.parseInt(nam);
				CategoryDataset dataset;
				try {
					panel.removeAll();
					panel.getParent().revalidate();
					dataset = DatasetThu(namchon);
					JFreeChart chart = ChartThu(dataset, nam);
					ChartPanel chartPanel = new ChartPanel(chart);
					chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
					chartPanel.setPreferredSize(new Dimension(685,348));
					chartPanel.setBackground(Color.white);
					panel.add(chartPanel);
					panel.repaint();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnThu.setBounds(10, 418, 140, 51);
		add(btnThu);
		
		JButton btnLoiNhuan = new JButton("LỢI NHUẬN");
		btnLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLoiNhuan.setForeground(new Color(255, 255, 0));
		btnLoiNhuan.setBackground(new Color(0, 0, 205));
		btnLoiNhuan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nam = CBnam.getSelectedItem().toString();
				int namchon = Integer.parseInt(nam);
				CategoryDataset dataset;
				try {
					panel.removeAll();
					panel.getParent().revalidate();					
					dataset = DatasetLoiNhuan(12,namchon);
					JFreeChart chart = ChartLoiNhuan(dataset, nam);
					ChartPanel chartPanel = new ChartPanel(chart);
					chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
					chartPanel.setPreferredSize(new Dimension(685,348));
					chartPanel.setBackground(Color.white);
					panel.add(chartPanel);
					panel.repaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoiNhuan.setBounds(380, 418, 140, 51);
		add(btnLoiNhuan);
		
		JButton btnChi = new JButton("TỔNG CHI");
		btnChi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChi.setForeground(new Color(255, 255, 0));
		btnChi.setBackground(new Color(0, 0, 205));
		btnChi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nam = CBnam.getSelectedItem().toString();
				int namchon = Integer.parseInt(nam);
				CategoryDataset dataset;
				try {
					panel.removeAll();
					panel.getParent().revalidate();
					dataset = DatasetChi(namchon);
					JFreeChart chart = ChartChi(dataset, nam);
					ChartPanel chartPanel = new ChartPanel(chart);
					chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
					chartPanel.setPreferredSize(new Dimension(685,348));
					chartPanel.setBackground(Color.white);
					panel.add(chartPanel);
					panel.repaint();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}				
			}
		});
		btnChi.setBounds(195, 418, 140, 51);
		add(btnChi);
	}
	
	
    private CategoryDataset DatasetThu(int nam) throws SQLException {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String query = "call thu_thang_new(?,?)";
		
		for(int i=1 ; i<=12 ; i++) {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setInt(1, i);
			stat.setInt(2, nam);		
			ResultSet result = stat.executeQuery();		
			if (result.next()){
				do {
					Double gia = result.getDouble(1);				
					dataset.setValue(gia, "Giá tiền", "T "+i);					
				} while (result.next());
				}else if (result.next() == false) {
					int gianull = 0; 
					dataset.setValue(gianull, "Giá tiền", "T "+i);
				}	
		}	   	
		return dataset;
    }

    private JFreeChart ChartThu(CategoryDataset dataset, String nam) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "TỔNG THU TRONG NĂM " + nam,
                "",
                "Giá tiền",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
        return barChart;
    }
	
    private CategoryDataset DatasetChi(int nam) throws SQLException {
    	
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String query = "call chi_thang_new(?,?)";
		
		for(int i=1 ; i<=12 ; i++) {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setInt(1, i);
			stat.setInt(2, nam);		
			ResultSet result = stat.executeQuery();		
			if (result.next()){
				do {
					Double gia = result.getDouble(1);				
					dataset.setValue(gia, "Giá tiền", "T "+i);					
				} while (result.next());
				}else if (result.next() == false) {
					int gianull = 0; 
					dataset.setValue(gianull, "Giá tiền", "T "+i);
				}	
		}
		
		   	
		
       return dataset;
    }

    private JFreeChart ChartChi(CategoryDataset dataset, String nam) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "TỔNG CHI TRONG NĂM "+nam,
                "",
                "Giá tiền",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
        return barChart;
    }
    
	public ArrayList<String> getnam() throws SQLException{
		String query = "select year(ngay_order) as nam from orders Group by year(ngay_order);";
		PreparedStatement stat = conn.prepareStatement(query);	
		ResultSet result = stat.executeQuery();		
		boolean check = false;
		ArrayList<String> namList = new ArrayList<String>();
		if(result.next()) {
			check = true;						
			do {
				String nam = result.getString(1);				
				namList.add(nam);				
			}while (result.next());
		}
		return namList;		
		}
	
	 private CategoryDataset DatasetLoiNhuan(int thang, int nam) throws SQLException {
	    	
	    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			String query = "call loi_nhuan(?,?)";
			
			for(int i=1 ; i<=12 ; i++) {
				PreparedStatement stat = conn.prepareStatement(query);
				stat.setInt(1, i);
				stat.setInt(2, nam);		
				ResultSet result = stat.executeQuery();		
				if (result.next()){
					do {
						Double gia = result.getDouble(1);				
						dataset.setValue(gia, "Giá tiền", "T "+i);						
					} while (result.next());
					}else if (result.next() == false) {
						int gianull = 0; 
						dataset.setValue(gianull, "Giá tiền", "T "+i);
					}	
			}	   	
	       return dataset;
	    }

	    private JFreeChart ChartLoiNhuan(CategoryDataset dataset, String nam) {
	        JFreeChart barChart = ChartFactory.createBarChart(
	                "TỔNG CHI TRONG NĂM "+nam,
	                "",
	                "Giá tiền",
	                dataset,
	                PlotOrientation.VERTICAL,
	                false, true, false);
	        return barChart;
	    }
}
