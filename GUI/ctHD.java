import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import javax.swing.table.JTableHeader;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class ctHD extends JFrame
{
	ConnectDB db = new ConnectDB();
	JTable chiTietHDTabel;
	String col[] = {"\u0054\u00ea\u006e \u006d\u006f\u0301\u006e", "\u0043\u0068\u0069 \u0074\u0069\u00ea\u0301\u0074","\u0053\u00f4\u0301 \u006c\u01b0\u01a1\u0323\u006e\u0067", "\u0110\u01a1\u006e \u0067\u0069\u0061\u0301"};
	JScrollPane pane;
	JPanel panel;
	String [][]data = new String[1000][4];

	public ctHD(String mahd) 
	{
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(550, 250);
		panel.setLayout(new BorderLayout());

		chiTietHDTabel = new JTable(data,col){
			public boolean isCellEditable(int row,int column)
		  	{
		  		if(column != -1) return false;
		    	return true;
		  	}
		  	public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
		    {
		        super.changeSelection(rowIndex, columnIndex, !extend, extend);
		    }
		};
		chiTietHDTabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		reloadTable(mahd);

		JTableHeader header = chiTietHDTabel.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setFont(new Font("Arial", Font.BOLD, 13));
		header.setForeground(Color.WHITE);

  		pane = new JScrollPane(chiTietHDTabel);
  		pane.setPreferredSize(new Dimension(550, 300));

		panel.add(pane, BorderLayout.CENTER);

		// JFrame this = new JFrame("My title");
		this.setTitle("\u0043\u0068\u0069 \u0074\u0069\u00ea\u0301\u0074 \u0068\u006f\u0061\u0301 \u0111\u01a1\u006e " + mahd);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setSize(570, 292);
		this.add(panel);
		ImageIcon image = new ImageIcon("pho.png");
        this.setIconImage(image.getImage());
		this.setVisible(true);
	}

	public void reloadTable(String mahd)
	{
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 4; j++)
				data[i][j] = "";

		try 
        {
        	int i = 0;
            try (Statement stmt = db.con.createStatement())
            {
            	String query = "SELECT TenMon, ChiTietMon, topho.matp, maco, SoLuong, DonGia FROM topho, chitiethd, monphu where chitiethd.matp=topho.matp and chitiethd.MaMA = monphu.MaMA and mahd=" + mahd;
            	System.out.println(query);
            	ResultSet rs3 = stmt.executeQuery(query);

            	while(rs3.next())
            	{
            		if (rs3.getString("maco").equals("not fur")) 
            			data[i][0] = rs3.getString("tenmon");
            		else
            			data[i][0] = "\u0050\u0068\u01a1\u0309 " + (rs3.getString("maco")).toLowerCase();

            		data[i][1] = rs3.getString("ChiTietMon");
            		data[i][2] = rs3.getString("SoLuong");
            		data[i][3] = rs3.getString("DonGia");
            		i += 1;
            	}
            }          
        }
        catch (SQLException e)
        {}

		DefaultTableModel model = new DefaultTableModel(data,col);
		chiTietHDTabel.setModel(model);
		model.fireTableDataChanged();
	}
}