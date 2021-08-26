import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import javax.swing.table.JTableHeader;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class DsMon 
{
	ConnectDB db = new ConnectDB();
	JTable chiTietHDTabel;
	String col[] = {"\u004d\u006f\u0301\u006e", "\u004c\u006f\u0061\u0323\u0069","\u0047\u0069\u0061\u0301"};
	JScrollPane pane;
	JPanel panel;
	String [][]data = new String[1000][3];

	public DsMon()
	{
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(185, 0, 410, 250);
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

		reloadTable();

		JTableHeader header = chiTietHDTabel.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setFont(new Font("Arial", Font.BOLD, 13));
		header.setForeground(Color.WHITE);

  		pane = new JScrollPane(chiTietHDTabel);
  		pane.setPreferredSize(new Dimension(200, 300));

		panel.add(pane, BorderLayout.CENTER);
	}

	public void reloadTable()
	{
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 3; j++)
				data[i][j] = "";

		System.out.println("run");

		try 
        {
            try (Statement stmt = db.con.createStatement())
            {
            	int i = 0;
            	String query3 = "SELECT maco, giaban FROM topho where giaban > 0";
            	ResultSet rs3 = stmt.executeQuery(query3);
				
            	while(rs3.next())
            	{
            		data[i][0] = rs3.getString("maco");
            		data[i][1] = "\u0050\u0068\u01a1\u0309";
            		double price1 = Double.parseDouble(rs3.getString("giaban"));
                	int price = (int)price1;
            		data[i][2] = String.valueOf(price); 
            		i += 1;
            	}

                String query2 = "SELECT tenmon, giaban FROM monphu where giaban > 0";
		    	ResultSet rs2 = stmt.executeQuery(query2);
            	while(rs2.next())
            	{
            		data[i][0] = rs2.getString("tenmon");
            		data[i][1] = "\u004d\u006f\u0301\u006e \u0070\u0068\u0075\u0323";
            		double price1 = Double.parseDouble(rs2.getString("giaban"));
                	int price = (int)price1;
            		data[i][2] = String.valueOf(price); 
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