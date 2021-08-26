import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import javax.swing.table.JTableHeader;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DsNV 
{
	ConnectDB db = new ConnectDB();
	JTable chiTietHDTabel;
	String col[] = {"\u0054\u00ea\u006e", "\u004c\u01b0\u01a1\u006e\u0067","Ca"};
	JScrollPane pane;
	JPanel panel;
	String [][]data = new String[1000][3];

	public DsNV()
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

		JTableHeader header = chiTietHDTabel.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setFont(new Font("Arial", Font.BOLD, 13));
		header.setForeground(Color.WHITE);

  		pane = new JScrollPane(chiTietHDTabel);
  		pane.setPreferredSize(new Dimension(200, 300));

		panel.add(pane, BorderLayout.CENTER);
		reloadTable();
	}

	public void reloadTable()
	{
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 3; j++)
				data[i][j] = "";

		try 
        {
            try (Statement stmt = db.con.createStatement())
            {
            	int i = 0;
            	String query3 = "SELECT ten, luong, maca FROM nhanvien where tinhtrang = 1";
            	ResultSet rs3 = stmt.executeQuery(query3);

            	while(rs3.next())
            	{
            		data[i][0] = rs3.getString("ten");
            		double sal1 = Double.parseDouble(rs3.getString("luong"));
                	int sal = (int)sal1;
            		data[i][1] = String.valueOf(sal); 
            		if ((rs3.getString("maca")).equals("1"))
            			data[i][2] = "\u0053\u0061\u0301\u006e\u0067";
            		else
            			data[i][2] = "\u0054\u00f4\u0301\u0069";
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