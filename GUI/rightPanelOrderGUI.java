import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.border.TitledBorder;
import java.sql.*;
import java.util.Date;
import javax.swing.text.MaskFormatter;

public class rightPanelOrderGUI 
{
	// String sb = "";
	ConnectDB db = new ConnectDB();
	// Double tTien;
	JPanel panel2, subPanel3, subPanel4, leftContent, rightContent, centerPanel, orderPanel, infoPane;
	JLabel ttLabel, ngayLabel, thanhTien, ngay, soBan, banLabel; 
	JTable chiTietHDTabel;
	String col[] = {"M\u0061\u0303", "M\u006f\u0301n", "Chi ti\u00ea\u0301t", "S\u00f4\u0301 l\u01b0\u01a1\u0323ng", "\u0110\u01a1\u006e \u0067\u0069\u0061\u0301"};
	String data[][] = new String[1000][5];
	JButton save = new JButton("L\u01b0u");
  	JButton pay = new JButton("Thanh to\u0061\u0301n");
  	JScrollPane pane;
  	OrderMon panel = new OrderMon();
  	TitledBorder titleBor = new TitledBorder(new TitledBorder(new LineBorder(Color.BLACK)), "\u0048\u004f\u0041\u0301 \u0110\u01a0\u004e \u0042\u0041\u0300\u004e 1 ", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, new Font("San serif", Font.BOLD, 14), Color.BLACK);
  	HoaDon hDon = new HoaDon();

	public rightPanelOrderGUI(String sb)
	{
		// this.sb = sb;
		// this.tTien = 0.0;
		hDon.soBan = sb;
		hDon.tongTien = 0.0;

		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setBounds(251, 0, 430, 609);

		panel2.setBorder(titleBor);

		subPanel3 = new JPanel();
		Border titledBorder = new TitledBorder(
	    	new TitledBorder(new LineBorder(Color.BLACK)), "Th\u00f4ng tin", 
	    	TitledBorder.DEFAULT_JUSTIFICATION,
	    	TitledBorder.DEFAULT_POSITION, 
	    	new Font("San serif", Font.BOLD, 15), 
	    	Color.BLACK);

		subPanel3.setBorder(titledBorder);
		subPanel3.setSize(450, 400);
		subPanel3.setBackground(new Color (255, 255, 255));
		subPanel3.setLayout(new BorderLayout());

		ttLabel = new JLabel("Th\u0061\u0300nh ti\u00ea\u0300n: ");
		ngayLabel = new JLabel("Ng\u0061\u0300y: ");
		banLabel = new JLabel("S\u00f4\u0301 B\u0061\u0300n: ");
		soBan = new JLabel(sb);
		thanhTien = new JLabel("0" + " VND");
		ngay = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		thanhTien.setFont(new Font("Arial", Font.PLAIN, 13));
		ngay.setFont(new Font("Arial", Font.PLAIN, 13));
		ttLabel.setFont(new Font("Arial", Font.BOLD, 13));
		ngayLabel.setFont(new Font("Arial", Font.BOLD, 13));
		soBan.setFont(new Font("Arial", Font.PLAIN, 13));
		banLabel.setFont(new Font("Arial", Font.BOLD, 13));

		leftContent = new JPanel();
		rightContent = new JPanel();
		leftContent.setLayout(new BorderLayout()); 
		rightContent.setLayout(new BorderLayout());
		leftContent.setBackground(new Color (255, 255, 255));
		rightContent.setBackground(new Color (255, 255, 255));

		leftContent.add(banLabel, BorderLayout.NORTH);
		leftContent.add(ngayLabel);
		leftContent.add(ttLabel, BorderLayout.SOUTH);

		rightContent.add(soBan, BorderLayout.NORTH);
		rightContent.add(ngay);
		rightContent.add(thanhTien, BorderLayout.SOUTH);		

		subPanel3.add(leftContent, BorderLayout.WEST);
		subPanel3.add(rightContent, BorderLayout.EAST);


		subPanel4 = new JPanel();
		subPanel4.setPreferredSize(new Dimension(415, 400));
		Border titledBorder1 = new TitledBorder(
	    	new TitledBorder(new LineBorder(Color.BLACK)), "Chi ti\u00ea\u0301t", 
	    	TitledBorder.DEFAULT_JUSTIFICATION,
	    	TitledBorder.DEFAULT_POSITION, 
	    	new Font("San serif", Font.BOLD, 15), 
	    	Color.BLACK);
		subPanel4.setBorder(titledBorder1);

		chiTietHDTabel = new JTable(data,col){
			public boolean isCellEditable(int row,int column)
		  	{
		  		if(column == 4 || column == 1 || column == 0 || column == 2) return false;
		    	return true;
		  	}
		  	public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
		    {
		        super.changeSelection(rowIndex, columnIndex, !extend, extend);
		    }
		};
		chiTietHDTabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTableHeader header = chiTietHDTabel.getTableHeader();
		chiTietHDTabel.getColumnModel().getColumn(0).setMinWidth(0);
		chiTietHDTabel.getColumnModel().getColumn(0).setMaxWidth(0);
		header.setBackground(Color.BLACK);
		header.setFont(new Font("Arial", Font.BOLD, 13));
		header.setForeground(Color.WHITE);
		reloadTable(sb);

  		pane = new JScrollPane(chiTietHDTabel);
  		pane.setPreferredSize(new Dimension(408, 400));
  		subPanel4.add(pane);
  		subPanel4.setPreferredSize(new Dimension(150, 435));
  		subPanel4.setBackground(new Color (255, 255, 255));

  		JPanel subPanel5 = new JPanel();
  		subPanel5.setLayout(new BorderLayout());
  		// subPanel5.setPreferredSize(new Dimension(100, 70));
  		Border titledBorder2 = new TitledBorder(
	    	new TitledBorder(new LineBorder(Color.BLACK)), "Thao t\u0061\u0301c", 
	    	TitledBorder.DEFAULT_JUSTIFICATION,
	    	TitledBorder.DEFAULT_POSITION, 
	    	new Font("San serif", Font.BOLD, 15), 
	    	Color.BLACK);
  		subPanel5.setBorder(titledBorder2);
  		
  		save.setFocusable(false);
  		pay.setFocusable(false);
  		save.setPreferredSize(new Dimension(60, 25));
  		pay.setPreferredSize(new Dimension(60, 25));
  		save.setMargin(new Insets(20, 20, 20, 20));
  		pay.setMargin(new Insets(20, 20, 20, 20));

  		subPanel5.add(save, BorderLayout.NORTH);
  		subPanel5.add(pay, BorderLayout.SOUTH);
  		subPanel5.setBackground(new Color (255, 255, 255));

  		infoPane = new JPanel();
  		infoPane.setLayout(new BorderLayout());
  		infoPane.setPreferredSize(new Dimension(200, 150));
  		infoPane.add(subPanel3);
  		infoPane.add(subPanel5, BorderLayout.SOUTH);
  		infoPane.setBackground(new Color (255, 255, 255));


  		centerPanel = new JPanel();
  		centerPanel.setLayout(new BorderLayout());
  		orderPanel = panel.panel;
  		centerPanel.add(orderPanel, BorderLayout.WEST);
  		centerPanel.setBackground(new Color (255, 255, 255));
  		
  		panel2.setBackground(new Color (255, 255, 255));
		panel2.add(subPanel4, BorderLayout.SOUTH);
		panel2.add(centerPanel, BorderLayout.EAST);
		panel2.add(infoPane, BorderLayout.WEST);

	}

	public void reloadTable(String soBan)
	{
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 5; j++)
				data[i][j] = "";

		String mahd = "0";
		double tongTien = 0;

		try 
        {
            try (Statement stmt = db.con.createStatement())
            {
            	String query1 = "SELECT mahd FROM hoadon where soBan=" + soBan + " and tinhtrang = 0";
            	ResultSet rs1 = stmt.executeQuery(query1);
            	
            	while(rs1.next())
            		mahd = rs1.getString("mahd");

          		int i = 0;

            	String query3 = "SELECT hoadon.mahd, tenmon, ChiTietMon, SoLuong, dongia FROM hoadon, chitiethd, monphu WHERE hoadon.mahd = chitiethd.mahd AND hoadon.tinhtrang = 0 and monphu.mama = chitiethd.mama and chitiethd.mama != 1 and hoadon.mahd=" + mahd;
            	ResultSet rs3 = stmt.executeQuery(query3);

            	while(rs3.next())
            	{
            		data[i][0] = rs3.getString("hoadon.mahd");
            		data[i][1] = rs3.getString("tenmon");
            		data[i][2] = rs3.getString("ChiTietMon");
            		data[i][3] = rs3.getString("SoLuong");
            		data[i][4] = rs3.getString("dongia");
            		tongTien += Double.parseDouble(data[i][3]) * Double.parseDouble(data[i][4]);  
            		i += 1;
            	}

                String query2 = "SELECT hoadon.mahd, topho.maco, ChiTietMon, SoLuong, dongia FROM hoadon, chitiethd, topho WHERE hoadon.mahd = chitiethd.mahd AND hoadon.tinhtrang = 0 and topho.matp = chitiethd.matp and chitiethd.matp != 4 and hoadon.mahd=" + mahd;
		    	ResultSet rs2 = stmt.executeQuery(query2);
            	while(rs2.next())
            	{
            		data[i][0] = rs2.getString("hoadon.mahd");
            		data[i][1] = "\u0050\u0068\u01a1\u0309 " + (rs2.getString("topho.maco")).toLowerCase();
            		data[i][2] = rs2.getString("ChiTietMon");
            		data[i][3] = rs2.getString("SoLuong");
            		data[i][4] = rs2.getString("dongia");
            		tongTien += Double.parseDouble(data[i][3]) * Double.parseDouble(data[i][4]); 
            		i += 1;
            	}
            	hDon.tongTien = tongTien;
            	NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            	thanhTien.setText(currencyVN.format(tongTien));
            }          
        }
        catch (SQLException e)
        {}

		DefaultTableModel model = new DefaultTableModel(data,col);
		chiTietHDTabel.setModel(model);
		chiTietHDTabel.getColumnModel().getColumn(0).setMinWidth(0);
		chiTietHDTabel.getColumnModel().getColumn(0).setMaxWidth(0);
		model.fireTableDataChanged();
	}
}