public class ExportHD
{
	ConnectDB db = new ConnectDB();
	String [][]data1 = new String[10000][3];
    String col[] = {"M\u00E3 h\u00F3a \u0111\u01A1n", "Ng\u00E0y l\u1EADp","Th\u00E0nh ti\u1EC1n"};
    JTable tableK = new JTable(data1, col);

    public ExportHD(){}

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

	public void exportFile(String thang, String nam)
	{
		System.out.println("runner up ok111");

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