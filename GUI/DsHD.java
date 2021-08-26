import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import javax.swing.table.JTableHeader;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;

public class DsHD 
{
	ConnectDB db = new ConnectDB();
	JTable chiTietHDTabel;
	String col[] = {"M\u00E3 h\u00F3a \u0111\u01A1n", "Ng\u00E0y l\u1EADp","Th\u00E0nh ti\u1EC1n"};
	JScrollPane pane;
	public JPanel panel;
	String [][]data = new String[1000][3];
    String [][]data1 = new String[10000][3];
	JButton showInfo = new JButton("Chi ti\u1EBFt");
	JButton export = new JButton("Xu\u1EA5t file");
	String []thang = {"Th\u00E1ng 1", "Th\u00E1ng 2", "Th\u00E1ng 3", "Th\u00E1ng 4", "Th\u00E1ng 5", "Th\u00E1ng 6", "Th\u00E1ng 7", "Th\u00E1ng 8", "Th\u00E1ng 9", "Th\u00E1ng 10", "Th\u00E1ng 11", "Th\u00E1ng 12"};
	JComboBox optionThang = new JComboBox(thang), optionNam = new JComboBox(), day = new JComboBox(thang);
    int []days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    JTable tableK = new JTable(data1, col);

	public DsHD()
	{
        showInfo.setPreferredSize(new Dimension(80, 30));
        export.setPreferredSize(new Dimension(80, 30));
        export.setFocusable(false);
        showInfo.setFocusable(false);
        showInfo.setFont(new Font("Arial", Font.BOLD, 12));
        export.setFont(new Font("Arial", Font.BOLD, 12));
		optionThang.setPreferredSize(new Dimension(80, 30));
		optionNam.setPreferredSize(new Dimension(80, 30));
        day.setPreferredSize(new Dimension(80, 30));

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(185, 0, 415, 250);
		panel.setLayout(new BorderLayout());

		changeOptions();
        changeNgay(new SimpleDateFormat("MM").format(new Date()), new SimpleDateFormat("yyyy").format(new Date()));

		optionThang.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
            	String []array = ((e.getItem()).toString()).split(" ");
                changeNgay(array[1], (optionNam.getSelectedItem()).toString());
                String []arr = ((day.getSelectedItem()).toString()).split(" ");
                System.out.println(array[1] + "  " + (optionNam.getSelectedItem()).toString() + "  " + arr[1]);
                // changeNgay(array[1], (optionNam.getSelectedItem()).toString());
            	reloadTable(array[1], (optionNam.getSelectedItem()).toString(), arr[1]);
            }
        });

        changeOptions();

        optionNam.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
            	String []array = ((optionThang.getSelectedItem()).toString()).split(" ");
                changeNgay(array[1], (optionNam.getSelectedItem()).toString());
                String []arr = ((day.getSelectedItem()).toString()).split(" ");
                System.out.println(array[1] + "  " + (optionNam.getSelectedItem()).toString() + "  " + arr[1]);
                
            	reloadTable(array[1], (e.getItem()).toString(), arr[1]);
            }
        });

        day.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                try
                {
                    String []array = ((optionThang.getSelectedItem()).toString()).split(" ");
                    String []arr = ((day.getSelectedItem()).toString()).split(" ");
                    System.out.println(array[1] + "  " + (optionNam.getSelectedItem()).toString() + "  " + arr[1]);
                    reloadTable(array[1], (optionNam.getSelectedItem()).toString(), arr[1]);
                }
                catch(Exception ea){}
            }
        });

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
  		pane.setPreferredSize(new Dimension(190, 210));

  		JPanel panel2 = new JPanel();
  		panel2.setPreferredSize(new Dimension(210, 50));
  		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
  		
  		panel2.add(optionThang);
        panel2.add(day);
        panel2.add(optionNam);

        panel2.add(showInfo);
        panel2.add(export);
  		panel.add(panel2, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		reloadTable(" ", " ", " ");
        day.setSelectedItem("\u004e\u0067\u0061\u0300\u0079 " + new SimpleDateFormat("dd").format(new Date()));
	}

    public void changeNgay(String thang, String nam)
    {
        day.removeAllItems();
        if (Integer.parseInt(thang) != 2)
            for (int i = 1; i <= days[Integer.parseInt(thang) - 1]; i++)
                day.addItem("\u004e\u0067\u0061\u0300\u0079 " + String.valueOf(i));
        if (Integer.parseInt(thang) == 2 && ((Integer.parseInt(nam) % 4 == 0) && (Integer.parseInt(nam) % 100!= 0)) || (Integer.parseInt(nam)%400 == 0) )
            for (int i = 1; i <= 29; i++)
                day.addItem("\u004e\u0067\u0061\u0300\u0079 " + String.valueOf(i));

    }

	public void changeOptions()
	{
		optionNam.removeAllItems();
		try 
        {
            try (Statement stmt = db.con.createStatement())
            { 
                String query = "SELECT distinct year(ngaylap) FROM hoadon";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                	optionNam.addItem(rs.getString("year(ngaylap)"));
            }          
        }
        catch (SQLException ea)
        {}
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        optionThang.setSelectedIndex(month - 1);
        optionNam.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));

	}

	public void reloadTable(String month, String year, String date)
	{
		if (month.equals(" "))
		{
			month = new SimpleDateFormat("MM").format(new Date());
			year = new SimpleDateFormat("yyyy").format(new Date());
            date = new SimpleDateFormat("dd").format(new Date());
		}
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 3; j++)
				data[i][j] = "";
		
		try 
        {
            try (Statement stmt = db.con.createStatement())
            {
            	int i = 0;
            	String query3 = "SELECT mahd, ngaylap, thanhtien FROM hoadon where tinhtrang = 1 and month(ngaylap)='" + month + "' and year(ngaylap)='" + year + "' and day(ngaylap)='" + date + "'";
            	System.out.println(query3);
                ResultSet rs3 = stmt.executeQuery(query3);

            	while(rs3.next())
            	{
            		data[i][0] = rs3.getString("mahd");
            		data[i][1] = rs3.getString("ngaylap"); 
            		double price1 = Double.parseDouble(rs3.getString("thanhtien"));
                	int price = (int)price1;
            		data[i][2] = String.valueOf(price); 
            		i += 1;
            	}
            }          
        }
        catch (SQLException e)
        {
        	System.out.println("not connected");
			System.out.println("sqle: " + e.getMessage());
			System.out.println("sqls: " + e.getSQLState());
			System.out.println("sqle: " + e.getErrorCode());
        }

		DefaultTableModel model = new DefaultTableModel(data,col);
		chiTietHDTabel.setModel(model);
		model.fireTableDataChanged();
	}

    private void getAllInM(String month)
    {
        String query = "Select mahd, ngaylap, thanhtien from hoadon where tinhtrang=1 and month(ngaylap)=" + month;
        for (int i = 0; i < 10000; i++)
            for (int j = 0; j < 3; j++)
                data1[i][j] = "";
        
        try 
        {
            try (Statement stmt = db.con.createStatement())
            {
                int i = 0;
                // String query3 = "SELECT mahd, ngaylap, thanhtien FROM hoadon where tinhtrang = 1 and month(ngaylap)='" + month + "' and year(ngaylap)='" + year + "' and day(ngaylap)='" + date + "'";
                System.out.println(query);
                ResultSet rs3 = stmt.executeQuery(query);

                while(rs3.next())
                {
                    data1[i][0] = rs3.getString("mahd");
                    data1[i][1] = rs3.getString("ngaylap"); 
                    double price1 = Double.parseDouble(rs3.getString("thanhtien"));
                    int price = (int)price1;
                    data1[i][2] = String.valueOf(price); 
                    i += 1;
                }
            }          
        }
        catch (SQLException e)
        {}

        DefaultTableModel model = new DefaultTableModel(data1,col);
        tableK.setModel(model);
        model.fireTableDataChanged();
    }

	public void exportFile()
	{
		System.out.println("runner up ok111");

        String thang = String.valueOf(optionThang.getSelectedIndex() + 1);
        String nam =  (optionNam.getSelectedItem()).toString();

        getAllInM(thang);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("HoaDon" + thang + "_" + nam);

        DefaultTableModel dtm = (DefaultTableModel) tableK.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    Object[][] tableData = new Object[nRow + 1][nCol];
        tableData[0][0] = "MAHD";
        tableData[0][1] = "NGAYLAP";
        tableData[0][2] = "THANHTIEN";
	    for (int i = 1; i < nRow + 1; i++)
	        for (int j = 0; j < nCol; j++)
	        	tableData[i][j] = dtm.getValueAt(i - 1,j);
 
        int rowCount = 0;
         
        for (Object[] aBook : tableData) {
            Row row = sheet.createRow(rowCount);
            rowCount += 1;
             
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(columnCount);
                columnCount += 1;
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
        System.out.println("xuat compile");
        if (thang.length()==1)
            thang = "0" + thang;
        String dir = "hoadon/hoadon" + new SimpleDateFormat("yyyy").format(new Date()) + "-" + thang;
        File theDir = new File(dir);
        if (!theDir.exists())
            theDir.mkdirs();
        String filename = dir + "\\TongHoaDon" + thang + "_" + nam + ".xlsx";
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(null, "Xu\u1EA5t h\u00F3a \u0111\u01A1n " + (optionThang.getSelectedItem()).toString() + " n\u0103m " + (optionNam.getSelectedItem()).toString(), "noti", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
        	System.out.println("loi");
        }
	}
}