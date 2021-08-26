import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.Date;

public class OrderGUI extends JFrame implements ActionListener
{     
	leftPanelOrderGUI panel1;
	rightPanelOrderGUI panel2;
	String id, nameNV, ca, soBan = "1";
	ConnectDB db = new ConnectDB();
	NhanVien nv = new NhanVien();

	public OrderGUI(String id, String nameNV, String ca) 
	{
		this.id = id;
		this.setTitle("\u0110\u0103\u0323t m\u006f\u0301n");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(700, 650);

		nv.nv = id;
		nv.ten = nameNV;
		nv.ca = ca;

		try 
        {
            try (Statement stmt = db.con.createStatement())
            {
            	String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String query2 =  "UPDATE hoadon SET tinhtrang = 2 WHERE date(ngaylap) != '" + today + "' and tinhtrang=0";
                // System.out.println(query2);
		    	stmt.executeUpdate(query2);
            }          
        }
        catch (SQLException ea)
        {}

		panel2 = new rightPanelOrderGUI(soBan);
		panel1 = new leftPanelOrderGUI(nv.ten, nv.ca);

		panel2.save.addActionListener(this);
		panel2.pay.addActionListener(this);
		panel2.panel.pho.addActionListener(this);
		panel2.panel.monPhu.addActionListener(this);
		panel2.panel.save.addActionListener(this);
		panel2.panel.cancel.addActionListener(this);

		for (int i = 0; i < 50; i++)
			panel1.butt[i].addActionListener(this);

		this.add(panel1.panel1);
		this.add(panel2.panel2);

		ImageIcon image = new ImageIcon("pho.png");
		this.setIconImage(image.getImage());

		this.getContentPane().setBackground(new Color (255, 255, 255));
		this.setVisible(true);
	} 


	@Override
	public void actionPerformed(ActionEvent e)
	{ 
		if (e.getSource() == panel2.panel.pho)
			panel2.panel.changeOptions("Pho");
		else if (e.getSource() == panel2.panel.monPhu)
			panel2.panel.changeOptions("MonPhu");
		else if (e.getSource() == panel2.save)
		{
			System.out.println("click save");
			int index = panel2.chiTietHDTabel.getSelectedRow();
			if (index >= 0)
			{	//"mahd, maco, chitietmon, soluong"
				TableModel model = panel2.chiTietHDTabel.getModel();
				// System.out.println(index);
				String mahd = model.getValueAt(index, 0).toString();
				String mon = model.getValueAt(index, 1).toString();
				String chitiet = model.getValueAt(index, 2).toString();
				String soluong = model.getValueAt(index, 3).toString();
				String query = "";
				System.out.println("soluong " + soluong);
				if ((mon.split(" "))[0].equals("Phở"))
				{
					String matp = "";
					String []arr = mon.split(" ", 2);

					try 
					{
					    try (Statement stmt = db.con.createStatement())
					    {
					    	ResultSet rs1 = stmt.executeQuery("select matp from topho where maco='" + (mon.split(" "))[1] + "'");
		                	while(rs1.next())
		                		matp = rs1.getString("matp");
					    }          
					}
					catch (SQLException ea)
					{}
					query = "UPDATE chitiethd SET soluong=" + soluong + " where mahd=" + mahd + " and matp="+ matp + " and chitietmon='" + chitiet + "'";
				}
				else 
				{
					String mama = "";
					String query1 = "select mama from monphu where tenmon ='" + mon + "'";
					try 
					{
					    try (Statement stmt = db.con.createStatement())
					    {
					    	ResultSet rs1 = stmt.executeQuery(query1);
		                	while(rs1.next())
		                		mama = rs1.getString("mama");
					    }          
					}
					catch (SQLException ea)
					{}
                	
					query = "UPDATE chitiethd SET soluong=" + soluong + " where mahd=" + mahd + " and matp=4 and chitietmon='" + chitiet + "' and mama=" + mama;
				}
				System.out.println(query);
				try 
				{
				    try (Statement stmt = db.con.createStatement())
				    {
				    	stmt.executeUpdate(query);
						panel2.reloadTable(soBan);
				    }          
				}
				catch (SQLException ea)
				{}				

			}
		}
		else if (e.getSource() == panel2.pay)
		{
			System.out.println("check doi");
			int soBan1 = Integer.parseInt(soBan);
			if (!(panel1.butt[soBan1 - 1].getBackground().equals(new Color(173, 173, 133))))
			{
				System.out.print("pay " + soBan);
				try 
	            {
	                try (Statement stmt = db.con.createStatement())
	                {
	                    String query =  "UPDATE hoadon SET tinhtrang = 1, thanhtien = " + panel2.hDon.tongTien + " WHERE soban = " + soBan;
				    	
				    	String [][]data = new String[1000][5];
				    	for (int i = 0; i < 1000; i++)
				    		for (int j = 0; j < 5; j++)
				    			data[i][j] = "";
				    	TableModel model = panel2.chiTietHDTabel.getModel();
				    	for (int i = 0; i < 1000; i++)
				    		for (int j = 0; j < 5; j++)
				    			if (!((model.getValueAt(i, j).toString()).equals("")))
				    				data[i][j] = (model.getValueAt(i, j)).toString();
	 
				    	PdfEx.exportPDF(data[0][0], panel1.name.getText(), String.valueOf(panel2.hDon.tongTien), data, soBan);
				    	stmt.executeUpdate(query);
	                }          
	            }
	            catch (SQLException ea)
	            {}
	            JOptionPane.showMessageDialog(null, "Thanh to\u0061\u0301n th\u0061\u0300nh c\u00f4ng", "noti", JOptionPane.INFORMATION_MESSAGE);
	            panel1.butt[Integer.parseInt(soBan) - 1].setBackground(new Color(173, 173, 133));
	            panel2.reloadTable(soBan);
			}
			else 
				JOptionPane.showMessageDialog(null, "\u0042\u0061\u0300\u006e \u0063\u0068\u01b0\u0061 \u0063\u006f\u0301 \u0068\u006f\u0061\u0301 \u0111\u01a1\u006e", "noti", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == panel2.panel.cancel)
		{
			panel2.panel.resetFields();
		}
		else if (e.getSource() == panel2.panel.save)
		{
			if (!(panel2.panel.chiTiet.getText().equals("") || panel2.panel.soLuong.getText().equals("")))
			{
				int soBan1 = Integer.parseInt(soBan);
				System.out.println("save " + soBan1);  
				int billId;
				if (panel1.butt[soBan1 - 1].getBackground().equals(new Color(173, 173, 133)))
				{
					try 
		            {
		                try (Statement stmt = db.con.createStatement())
		                {
		                    String query =  "INSERT INTO hoadon(manv, soban, ngaylap, tinhtrang) VALUES (" + this.id + "," + soBan1  + "," + "'" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "', 0)";
					    	// System.out.println("query: " + query);
					    	stmt.executeUpdate(query);
		                }          
		            }
		            catch (SQLException ea)
		            {}
		            panel1.butt[soBan1 - 1].setBackground(Color.GREEN);
				}

				if (panel2.panel.pho.isSelected())
				{
					if (new LogicLayer().checkDetail(panel2.panel.options.getSelectedItem().toString(), panel2.panel.chiTiet.getText()) == true)
					{
						try 
			            {
			                try (Statement stmt = db.con.createStatement())
			                {
			                	String unitPrice = "";
			                	String mahd = "", matp="";
			                	String query1 = "select giaban, matp from topho where maco ='" + panel2.panel.options.getSelectedItem().toString() + "'";
			                	ResultSet rs1 = stmt.executeQuery(query1);
			                	while(rs1.next())
			                	{
			                		unitPrice = rs1.getString("giaban");
			                		matp = rs1.getString("matp");
			                	}

			                	String query3 = "select mahd from hoadon where soban=" + soBan1 + " and tinhtrang = 0";
			                	ResultSet rs3 = stmt.executeQuery(query3);
			                	while(rs3.next())
			                		mahd = rs3.getString("mahd");

			                    String query2 =  "INSERT INTO chitiethd(mahd, matp, chitietmon, soluong, dongia) VALUES (" + mahd + "," + matp  + ",'" + panel2.panel.chiTiet.getText() + "'," + panel2.panel.soLuong.getText() + "," + unitPrice + ") ON DUPLICATE KEY UPDATE soluong=soluong+" + panel2.panel.soLuong.getText() + ", dongia=" + unitPrice;
						    	stmt.executeUpdate(query2);
						    	panel2.panel.resetFieldsPho();

			                }          
			            }
			            catch (SQLException ea)
			            {}
			            JOptionPane.showMessageDialog(null, "Th\u00eam th\u0061\u0300nh c\u00f4ng", "noti", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "Qu\u0061\u0301 nhi\u00ea\u0300u th\u0069\u0323t", "noti", JOptionPane.INFORMATION_MESSAGE);

				}
				else
				{
					try 
		            {
		                try (Statement stmt = db.con.createStatement())
		                {
		                	String unitPrice = "";
		                	String mahd = "";
		                	String mama = "";
		                	String query1 = "select giaban, mama from monphu where tenmon ='" + panel2.panel.options.getSelectedItem().toString() + "'";
		                	ResultSet rs1 = stmt.executeQuery(query1);
		                	while(rs1.next())
		                	{
		                		unitPrice = rs1.getString("giaban");
		                		mama = rs1.getString("mama");
		                	}

		                	String query3 = "select mahd from hoadon where soban=" + soBan1 + " and tinhtrang = 0";
		                	ResultSet rs3 = stmt.executeQuery(query3);
		                	while(rs3.next())
		                		mahd = rs3.getString("mahd");

		                    String query2 =  "INSERT INTO chitiethd(mahd, mama, matp, chitietmon, soluong, dongia) VALUES (" + mahd + ", " + mama + ",4,'\u0054\u0068\u01b0\u01a1\u0300\u006e\u0067'," + panel2.panel.soLuong.getText() + "," + unitPrice + ") ON DUPLICATE KEY UPDATE soluong=soluong+" + panel2.panel.soLuong.getText() + ", dongia=" + unitPrice;
					    	stmt.executeUpdate(query2);
					    	panel2.panel.resetFieldsMon();
		                }          
		            }
		            catch (SQLException ea)
		            {}
		            JOptionPane.showMessageDialog(null, "Th\u00eam th\u0061\u0300nh c\u00f4ng", "noti", JOptionPane.INFORMATION_MESSAGE);
				}
				panel2.reloadTable(String.valueOf(soBan1));
				

			}
			else
				JOptionPane.showMessageDialog(new JFrame(), "Thi\u00ea\u0301u chi ti\u00ea\u0301t ho\u0103\u0323c (v\u0061\u0300) s\u00f4\u0301 l\u01b0\u01a1\u0323ng!");
		}
		else
		{
			System.out.println("Previous num: " + soBan); 
			int num = ((ButtonBan)e.getSource()).getIndex();
			soBan = String.valueOf(num + 1);
			panel2.titleBor.setTitle("HO\u0041\u0301 \u0110\u01a0N B\u0041\u0300N " + soBan + " ");
			panel2.reloadTable(soBan);
			panel2.soBan.setText(soBan);
			panel2.panel2.repaint();
		}
	}
}

