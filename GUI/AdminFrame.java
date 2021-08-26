import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;

public class AdminFrame extends JFrame implements ActionListener
{      
	ConnectDB db = new ConnectDB();
	leftPanelAdminGUI panel1;
	EditMon p1 = new EditMon();
	EditNV p = new EditNV();
	DsMon l = new DsMon();
	DsNV l1 = new DsNV();
	DsHD hd = new DsHD();
	JLabel nameLabel, label2, name, shiftLabel, shift, label6;

	public AdminFrame() 
	{
		this.setTitle("\u0051\u0075\u0061\u0309\u006e \u006c\u0069\u0301");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(620, 292);

		panel1 = new leftPanelAdminGUI();

		panel1.hoaDon.addActionListener(this);
		panel1.quanLiMon.addActionListener(this);
		panel1.quanLiNV.addActionListener(this);
		panel1.dsNV.addActionListener(this);
		panel1.dsMon.addActionListener(this);
		hd.showInfo.addActionListener(this);
		hd.export.addActionListener(this);
		p1.save.addActionListener(this); 
		p1.cancel.addActionListener(this);
		p1.save1.addActionListener(this);
		p1.cancel1.addActionListener(this);
		p.save.addActionListener(this);
		p.cancel.addActionListener(this);
		p.save1.addActionListener(this);
		p.cancel1.addActionListener(this);
		p1.pho1.addActionListener(this);
		p1.monPhu1.addActionListener(this);
		p1.pho.addActionListener(this);
		p1.monPhu.addActionListener(this);

		panel1.hoaDon.setBackground(Color.WHITE);
		panel1.quanLiMon.setBackground(Color.WHITE);
		panel1.quanLiNV.setBackground(Color.WHITE);
		panel1.dsNV.setBackground(Color.WHITE);
		panel1.dsMon.setBackground(new Color(255, 99, 71));

		this.add(panel1.panel1);
		l.reloadTable();
		this.add(l.panel);

		ImageIcon image = new ImageIcon("pho.png");
		this.setIconImage(image.getImage());

		this.setVisible(true);
	} 


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == p1.pho)
		{
			p1.enableField("1");
		}
		if (e.getSource() == p1.monPhu)
		{
			p1.enableField("0");
		}
		if (e.getSource() == hd.showInfo)
		{
			int row = hd.chiTietHDTabel.getSelectedRow();
			TableModel model = hd.chiTietHDTabel.getModel();
			String mahd = model.getValueAt(row, 0).toString();
			new ctHD(mahd);
		}

		if (e.getSource() == hd.export)
		{
		    System.out.println("export");
			hd.exportFile();
		}

		if (e.getSource() == panel1.hoaDon)
		{
			changeColor((JButton)(e.getSource()));
		}

		if (e.getSource() == panel1.quanLiNV)
		{
			changeColor((JButton)(e.getSource()));
			this.remove(p1.panel);
			this.remove(l1.panel);
			this.remove(l.panel);
			this.remove(hd.panel);
			this.add(p.panel);
			repaint();
        	revalidate();
		}

		if (e.getSource() == panel1.hoaDon)
		{
			changeColor((JButton)(e.getSource()));
			this.remove(p.panel);
			this.remove(l1.panel);
			this.remove(l.panel);
			this.remove(p1.panel);
			this.add(hd.panel);
			repaint();
        	revalidate();
		}

		if (e.getSource() == panel1.quanLiMon)
		{
			changeColor((JButton)(e.getSource()));
			this.remove(p.panel);
			this.remove(l1.panel);
			this.remove(l.panel);
			this.remove(hd.panel);
			this.add(p1.panel);
			repaint();
        	revalidate();
		}

		if (e.getSource() == panel1.dsNV)
		{
			changeColor((JButton)(e.getSource()));
			this.remove(p.panel);
			this.remove(p1.panel); 
			this.remove(l.panel);
			this.remove(hd.panel);
			this.add(l1.panel);
			l1.reloadTable();
			repaint();
        	revalidate();
		}

		if (e.getSource() == panel1.dsMon)
		{
			changeColor((JButton)(e.getSource()));
			this.remove(p.panel);
			this.remove(l1.panel);
			this.remove(p1.panel);
			this.remove(hd.panel);
			this.add(l.panel);
			l.reloadTable();
			repaint();
        	revalidate();
		}

		if (e.getSource() == p1.cancel)
			p1.refresh();

		if (e.getSource() == p1.cancel1)
			p1.refresh1();

		if (e.getSource() == p.cancel)
			p.refresh();

		if (e.getSource() == p.cancel1)
			p.refresh1();

		if (e.getSource() == p.save)
		{
			if (!(p.tenMon.getText().equals("") || p.gia.getText().equals("")))
			{
				String tiTrang = "";
				String query = "";
				String maca = "";
				if ((p.tTrang.getSelectedItem().toString()).equals("\u0110\u0061\u006e\u0067 \u006c\u0061\u0300\u006d"))
					tiTrang = "1";
				else 
					tiTrang = "0";
				if (p.sang.isSelected())
					maca = "1";
				else 
					maca = "2";
				
				query = "insert into nhanvien(ten, luong, tinhtrang, maca, matkhau) values ('" + p.tenMon.getText() + "', '" + p.gia.getText() + "'," + tiTrang + "," + maca + ", '123456')";
				// System.out.println(query);
				try 
		        {
		            try (Statement stmt = db.con.createStatement())
		            {
				    	stmt.executeUpdate(query);
				        JOptionPane.showMessageDialog(null, "\u0054\u0068\u00ea\u006d \u006e\u0068\u00e2\u006e \u0076\u0069\u00ea\u006e \u0074\u0068\u0061\u0300\u006e\u0068 \u0063\u00f4\u006e\u0067", "noti", JOptionPane.INFORMATION_MESSAGE);
				        p.refresh();
				 	
		            }          
		        }
		        catch (SQLException ea)
		        {}
		        // p.nvList();
			}
			else
				JOptionPane.showMessageDialog(null, "\u0054\u0068\u0069\u00ea\u0301\u0075 \u0074\u0068\u00f4\u006e\u0067 \u0074\u0069\u006e \u0068\u006f\u0103\u0323\u0063 \u0072\u00f4\u0303\u006e\u0067", "noti", JOptionPane.ERROR_MESSAGE);
		}

		if (e.getSource() == p.save1)
		{
			if (!(p.tenMon1.getText().equals("") || p.gia1.getText().equals("")))
			{
				String tiTrang = "";
				String query = "";
				String maca = "";
				if ((p.tTrang.getSelectedItem().toString()).equals("\u0110\u0061\u006e\u0067 \u006c\u0061\u0300\u006d"))
					tiTrang = "1";
				else 
					tiTrang = "0";
				if (p.sang.isSelected())
					maca = "1";
				else 
					maca = "2";
				
				query = "UPDATE nhanvien SET ten = '" + p.tenMon1.getText() + "', luong = " + p.gia1.getText() + ", tinhtrang=" + tiTrang + " where manv=" + ((p.options1.getSelectedItem().toString()).split(":"))[0];
				// System.out.println(query);
				try 
		        {
		            try (Statement stmt = db.con.createStatement())
		            {
				    	stmt.executeUpdate(query);
				        JOptionPane.showMessageDialog(null, "\u0043\u0068\u0069\u0309\u006e\u0068 \u0073\u01b0\u0309\u0061 \u006e\u0068\u00e2\u006e \u0076\u0069\u00ea\u006e \u0074\u0068\u0061\u0300\u006e\u0068 \u0063\u00f4\u006e\u0067", "noti", JOptionPane.INFORMATION_MESSAGE);
				        // p1.refresh();	
		            }          
		        }
		        catch (SQLException ea)
		        {}
			}
			else
				JOptionPane.showMessageDialog(null, "\u0054\u0068\u0069\u00ea\u0301\u0075 \u0074\u0068\u00f4\u006e\u0067 \u0074\u0069\u006e \u0068\u006f\u0103\u0323\u0063 \u0072\u00f4\u0303\u006e\u0067", "noti", JOptionPane.ERROR_MESSAGE);
		}

		if (e.getSource() == p1.save)
		{
			if (!(p1.tenMon.getText().equals("") || p1.gia.getText().equals("")))
			{
				String tiTrang = "";
				if ((p1.tTrang.getSelectedItem().toString()).equals("\u0110\u0061\u006e\u0067 \u0062\u0061\u0301\u006e"))
					tiTrang = "1";
				else 
					tiTrang = "0";
				String query = "";
				// System.out.println(p1.tenMon.getText() + " " + p1.gia.getText()); 
				if (p1.pho.isSelected() == true)
					query = "insert into topho(maco, giaban, tinhtrang, sothit) values ('" + p1.tenMon.getText() + "', '" + p1.gia.getText() + "'," + tiTrang + "," + p1.soThit.getText() + ")";
				else 
					query = "insert into monphu(tenmon, giaban, tinhtrang) values ('" + p1.tenMon.getText() + "', '" + p1.gia.getText() + "'," + tiTrang + ")";

				try 
		        {
		            try (Statement stmt = db.con.createStatement())
		            {
				    	stmt.executeUpdate(query);
				        JOptionPane.showMessageDialog(null, "\u0054\u0068\u00ea\u006d \u006d\u006f\u0301\u006e \u0074\u0068\u0061\u0300\u006e\u0068 \u0063\u00f4\u006e\u0067", "noti", JOptionPane.INFORMATION_MESSAGE);
				        p1.refresh();	
		            }          
		        }
		        catch (SQLException ea)
		        {}
			}
			else
				JOptionPane.showMessageDialog(null, "\u0054\u0068\u0069\u00ea\u0301\u0075 \u0074\u0068\u00f4\u006e\u0067 \u0074\u0069\u006e \u0068\u006f\u0103\u0323\u0063 \u0072\u00f4\u0303\u006e\u0067", "noti", JOptionPane.ERROR_MESSAGE);
		}

		if (e.getSource() == p1.save1)
		{
			if (!(p1.tenMon1.getText().equals("") || p1.gia1.getText().equals("")))
			{
				System.out.println("sav1 " + p1.idE);
				String query = "";
				String tt = "";
				if ((p1.tTrang1.getSelectedItem().toString()).equals("\u0110\u0061\u006e\u0067 \u0062\u0061\u0301\u006e"))
					tt = "1";
				else
					tt = "0";
				if (p1.pho1.isSelected())
				{
					query = "UPDATE topho SET tinhtrang = " + tt + ", giaban = " + p1.gia1.getText() + ", maco='" + p1.tenMon1.getText() + "', sothit=" + p1.soThit1.getText() + " WHERE matp = " + p1.idE;
				}
				else 
					query = "UPDATE monphu SET tenmon = '" + p1.tenMon1.getText()  + "', tinhtrang = " + tt + ", giaban = " + p1.gia1.getText() + " WHERE mama = " + p1.idE;
				try 
		        {
		            try (Statement stmt = db.con.createStatement())
		            {
				    	stmt.executeUpdate(query);
		            }          
		        }
		        catch (SQLException ea)
		        {}
		        // if (p1.pho1.isSelected()) p1.changeOptions("Pho");
		        // else p1.changeOptions("MonPhu");
		        JOptionPane.showMessageDialog(null, "\u0043\u0068\u0069\u0309\u006e\u0068 \u0073\u01b0\u0309\u0061 \u0074\u0068\u0061\u0300\u006e\u0068 \u0063\u00f4\u006e\u0067", "noti", JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "\u0054\u0068\u0069\u00ea\u0301\u0075 \u0074\u0068\u00f4\u006e\u0067 \u0074\u0069\u006e \u0068\u006f\u0103\u0323\u0063 \u0072\u00f4\u0303\u006e\u0067", "noti", JOptionPane.ERROR_MESSAGE);
		}

		if (e.getSource() == p1.pho1)
			p1.changeOptions("Pho");
		if (e.getSource() == p1.monPhu1)
			p1.changeOptions("MonPhu");
	}

	public void changeColor(JButton b)
	{
		if (panel1.hoaDon.equals(b))
			panel1.hoaDon.setBackground(new Color(255, 99, 71));
		else 
			panel1.hoaDon.setBackground(Color.WHITE);
		if (panel1.quanLiMon.equals(b))
			panel1.quanLiMon.setBackground(new Color(255, 99, 71));
		else 
			panel1.quanLiMon.setBackground(Color.WHITE);
		if (panel1.quanLiNV.equals(b))
			panel1.quanLiNV.setBackground(new Color(255, 99, 71));
		else 
			panel1.quanLiNV.setBackground(Color.WHITE);
		if (panel1.dsNV.equals(b))
			panel1.dsNV.setBackground(new Color(255, 99, 71));
		else 
			panel1.dsNV.setBackground(Color.WHITE);
		if (panel1.dsMon.equals(b))
			panel1.dsMon.setBackground(new Color(255, 99, 71));
		else 
			panel1.dsMon.setBackground(Color.WHITE);
	}
}