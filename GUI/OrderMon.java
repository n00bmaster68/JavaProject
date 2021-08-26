import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.border.TitledBorder;
import java.sql.*;

public class OrderMon
{
	ConnectDB db = new ConnectDB();
	JPanel panel, panel1, panel2, panel3, rightContent, leftContent;
	JButton save, cancel;
	JRadioButton pho, monPhu;
	ButtonGroup gr;
	JComboBox options;
	JTextField  chiTiet, soLuong;
	JLabel []labels = new JLabel[10];

	public OrderMon()
	{
		panel = new JPanel();
		panel.setBackground(new Color (255, 255, 255));
	
	    Border thatBorder = new TitledBorder(
	    	new TitledBorder(new LineBorder(Color.BLACK)), "G\u006f\u0323i m\u006f\u0301n", 
	    	TitledBorder.DEFAULT_JUSTIFICATION,
	    	TitledBorder.CENTER, 
	    	new Font("San serif", Font.BOLD, 15), 
	    	Color.BLACK);

		panel.setBorder(thatBorder);

		panel.setPreferredSize(new Dimension(200, 140));
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel1.setBackground(new Color (255, 255, 255));
		panel2.setBackground(new Color (255, 255, 255));
		panel3.setBackground(new Color (255, 255, 255));

		panel.setLayout(new BorderLayout());
		panel1.setLayout(new BorderLayout());

		pho = new JRadioButton("Ph\u01a1\u0309", true);
		monPhu = new JRadioButton("M\u006f\u0301n ph\u0075\u0323", false);
		gr = new ButtonGroup();
		gr.add(pho);
		gr.add(monPhu);
		pho.setFocusable(false);
		monPhu.setFocusable(false);
		pho.setBackground(new Color (255, 255, 255));
		monPhu.setBackground(new Color (255, 255, 255));

		chiTiet = new JTextField();
		soLuong = new JTextField();
		chiTiet.setPreferredSize(new Dimension(100,20));
		soLuong.setPreferredSize(new Dimension(100,20));

		options = new JComboBox();
		changeOptions("Pho");
		options.setPreferredSize(new Dimension(50, 20));

		panel1.add(pho, BorderLayout.WEST);
		panel1.add(monPhu, BorderLayout.CENTER);
		panel1.add(options, BorderLayout.SOUTH);

		chiTiet.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	            char ch = e.getKeyChar();
	            if (new LogicLayer().checkAl(ch) == false)
	            	chiTiet.setText((chiTiet.getText()).substring(0, (chiTiet.getText()).length() - 1));
	        }
	    });

		soLuong.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyReleased(KeyEvent evt) {
	            if (new LogicLayer().checkIsNum(soLuong) == false)
	            	soLuong.setText("");
	        }
	    });

		panel2.setLayout(new BorderLayout());

		labels[0] = new JLabel("S\u00f4\u0301 l\u01b0\u01a1\u0323ng: ");
		labels[1] = new JLabel("Chi ti\u00ea\u0301t: ");

		leftContent = new JPanel();
		rightContent = new JPanel();
		leftContent.setLayout(new BorderLayout());
		rightContent.setLayout(new BorderLayout());
		leftContent.add(labels[0], BorderLayout.NORTH);
		leftContent.add(labels[1], BorderLayout.SOUTH);
		rightContent.add(soLuong, BorderLayout.NORTH);
		rightContent.add(chiTiet, BorderLayout.SOUTH);
		leftContent.setBackground(new Color (255, 255, 255));
		rightContent.setBackground(new Color (255, 255, 255));

		panel2.add(leftContent, BorderLayout.WEST);
		panel2.add(rightContent);

		panel3.setLayout(new FlowLayout());

		save = new JButton("Th\u00eam"); 
		cancel = new JButton("H\u0075\u0309y");
		save.setPreferredSize(new Dimension(75, 20));
		cancel.setPreferredSize(new Dimension(75, 20));
		save.setFocusable(false);
		cancel.setFocusable(false);

		panel3.add(save);
		panel3.add(cancel);


		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2);
		panel.add(panel3, BorderLayout.SOUTH);
	}

	public void changeOptions(String s)
	{
		options.removeAllItems();
		if (s == "MonPhu")
		{
			try 
            {
                try (Statement stmt = db.con.createStatement())
                { 
                    String query = "select tenmon from monphu where giaban > 0 and tinhtrang=1";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    	options.addItem(rs.getString("tenmon"));
                }          
            }
            catch (SQLException ea)
            {}
            chiTiet.setText("\u0054\u0068\u01b0\u01a1\u0300\u006e\u0067");
		}
		else if (s == "Pho")
		{
			try 
            {
                try (Statement stmt = db.con.createStatement())
                { 
                    String query = "select maco from topho where giaban > 0 and tinhtrang=1";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    	options.addItem(rs.getString("maco"));
                }          
            }
            catch (SQLException ea)
            {}
            chiTiet.setText("T\u0061\u0301i");
		}
	}

	public void resetFields()
	{
		chiTiet.setText("");
		soLuong.setText("");
	}

	public void resetFieldsPho()
	{
		chiTiet.setText("T\u0061\u0301i");
		soLuong.setText("");
	}

	public void resetFieldsMon()
	{
		chiTiet.setText("Th\u01b0\u01a1\u0300\u006e\u0067");
		soLuong.setText("");
	}
}