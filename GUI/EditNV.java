import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import java.sql.*;

public class EditNV 
{
	ConnectDB db = new ConnectDB();
	JPanel panel, subLeft, subRight, leftCon, rightCon, leftCon1, rightCon1, upCon, upCon1, bott, bott1, buttP, buttP1;
	JButton save, cancel, save1, cancel1;
	JRadioButton sang, toi, sang1, toi1;
	JComboBox tTrang, tTrang1;
	JTextField  gia, gia1, tenMon, tenMon1;
	JLabel []labels = new JLabel[10];
	JComboBox options1 = new JComboBox();

	public EditNV()
	{
		labels[0] = new JLabel("\u0048\u006f\u0323 \u0026 \u0054\u00ea\u006e: ");
		labels[1] = new JLabel("\u0048\u006f\u0323 \u0026 \u0054\u00ea\u006e: ");
		labels[2] = new JLabel("\u004c\u01b0\u01a1\u006e\u0067: ");
		labels[3] = new JLabel("\u004c\u01b0\u01a1\u006e\u0067: ");
		labels[4] = new JLabel("\u0054\u0069\u0300\u006e\u0068 \u0074\u0072\u0061\u0323\u006e\u0067: ");
		labels[5] = new JLabel("\u0054\u0069\u0300\u006e\u0068 \u0074\u0072\u0061\u0323\u006e\u0067: ");

		String []tt = {"\u0110\u0061\u006e\u0067 \u006c\u0061\u0300\u006d", "\u004e\u0067\u0068\u0069\u0309 \u0076\u0069\u00ea\u0323\u0063"};

		tTrang = new JComboBox(tt);
		tTrang1 = new JComboBox(tt);

		tTrang.setPreferredSize(new Dimension(30, 10));

		gia = new JTextField();
		gia1 = new JTextField();
		tenMon = new JTextField();
		tenMon1 = new JTextField();

		tenMon.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	            char ch = e.getKeyChar();
	            if(Character.isDigit(ch)){
	                tenMon.setText((tenMon.getText()).substring(0, (tenMon.getText()).length() - 1));
	                JOptionPane.showMessageDialog(null, "Enter Alphabet Only !");
	            }
	        }
	    });

		gia.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyReleased(KeyEvent evt) {
	            try {
	                long number = Long.parseLong(gia.getText());
	            } catch (Exception e) {
	                if (!(gia.getText().equals("")))
	                {
	                	JOptionPane.showMessageDialog(new JFrame(), "Only Numbers Allowed");
	                	gia.setText((gia.getText()).substring(0, (gia.getText()).length() - 1));
	                }
	            }
	        }
	    });

	    tenMon1.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	            char ch = e.getKeyChar();
	            if(Character.isDigit(ch)){
	                tenMon1.setText((tenMon1.getText()).substring(0, (tenMon1.getText()).length() - 1));
	                JOptionPane.showMessageDialog(null, "Enter Alphabet Only !");
	            }
	        }
	    });

		gia1.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyReleased(KeyEvent evt) {
	            try {
	                long number = Long.parseLong(gia1.getText());
	            } catch (Exception e) {
	                if (!(gia1.getText().equals("")))
	                {
	                	JOptionPane.showMessageDialog(new JFrame(), "Only Numbers Allowed");
	                	gia1.setText((gia1.getText()).substring(0, (gia1.getText()).length() - 1));
	                }
	            }
	        }
	    });

		gia.setPreferredSize(new Dimension(80, 20));
		tenMon.setPreferredSize(new Dimension(80, 20));

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(185, 0, 410, 250);
		panel.setLayout(new BorderLayout());

		subLeft = new JPanel();
		subRight = new JPanel();

		subLeft.setPreferredSize(new Dimension(180, 80));
		subLeft.setBorder(new TitledBorder(new TitledBorder(new LineBorder(Color.BLACK)), "\u0054\u0068\u00ea\u006d ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("San serif", Font.BOLD, 14), Color.BLACK));

		subRight.setPreferredSize(new Dimension(180, 250));
		subRight.setBorder(new TitledBorder(new TitledBorder(new LineBorder(Color.BLACK)), "\u0043\u0068\u0069\u0309\u006e\u0068 \u0073\u01b0\u0309\u0061 ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("San serif", Font.BOLD, 14), Color.BLACK));

		sang = new JRadioButton("\u0053\u0061\u0301\u006e\u0067"); 
		toi = new JRadioButton("\u0054\u00f4\u0301\u0069"); 
		sang1 = new JRadioButton("\u0053\u0061\u0301\u006e\u0067");  
		toi1 = new JRadioButton("\u0054\u00f4\u0301\u0069"); 

		ButtonGroup group = new ButtonGroup();
		group.add(sang);
		group.add(toi);

		ButtonGroup group1 = new ButtonGroup();
		group1.add(sang1);
		group1.add(toi1);

		upCon = new JPanel();
		upCon1 = new JPanel();
		upCon.setLayout(new BorderLayout());
		upCon1.setLayout(new BorderLayout());

		options1.setPreferredSize(new Dimension(80, 20));
		options1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                	changeField((e.getItem()).toString());
            }
        });

		upCon1.add(options1, BorderLayout.SOUTH);

		upCon.setPreferredSize(new Dimension(160, 50));
		upCon.add(sang, BorderLayout.WEST);
		upCon.add(toi, BorderLayout.EAST);


		upCon1.setPreferredSize(new Dimension(160, 50));
		upCon1.add(sang1, BorderLayout.WEST);
		upCon1.add(toi1, BorderLayout.EAST);

		bott = new JPanel();
		bott.setLayout(new BorderLayout());
		bott.setPreferredSize(new Dimension(160, 80));
		// bott.setBackground(Color.BLACK);
		leftCon = new JPanel();
		leftCon.setLayout(new BorderLayout());
		leftCon.add(labels[0], BorderLayout.NORTH);
		leftCon.add(labels[4]);
		leftCon.add(labels[2], BorderLayout.SOUTH);
		bott.add(leftCon, BorderLayout.WEST);

		rightCon = new JPanel();
		rightCon.setLayout(new BorderLayout());
		rightCon.add(tenMon, BorderLayout.NORTH);
		rightCon.add(tTrang);
		rightCon.add(gia, BorderLayout.SOUTH);
		bott.add(rightCon, BorderLayout.EAST);

		buttP = new JPanel();
		buttP.setPreferredSize(new Dimension(160, 80));
		buttP.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 40));
		save = new JButton("\u004c\u01b0\u0075");
		cancel = new JButton("\u0048\u0075\u0079\u0309");
		save.setPreferredSize(new Dimension(60, 30));
		cancel.setPreferredSize(new Dimension(60, 30));
		save.setFocusable(false);
		cancel.setFocusable(false);
		buttP.add(save);
		buttP.add(cancel);

		subLeft.add(upCon, BorderLayout.NORTH);
		subLeft.add(bott, BorderLayout.CENTER);
		subLeft.add(buttP, BorderLayout.SOUTH);
		// subLeft.setBackground(Color.BLUE);

		bott1 = new JPanel();
		bott1.setLayout(new BorderLayout());
		bott1.setPreferredSize(new Dimension(160, 80));
		// bott1.setBackground(Color.BLACK);
		leftCon1 = new JPanel();
		leftCon1.setLayout(new BorderLayout());
		leftCon1.add(labels[1], BorderLayout.NORTH);
		leftCon1.add(labels[5]);
		leftCon1.add(labels[3], BorderLayout.SOUTH);
		bott1.add(leftCon1, BorderLayout.WEST);

		rightCon1 = new JPanel();
		rightCon1.setLayout(new BorderLayout());
		rightCon1.add(tenMon1, BorderLayout.NORTH);
		rightCon1.add(tTrang1);
		rightCon1.add(gia1, BorderLayout.SOUTH);
		bott1.add(rightCon1, BorderLayout.EAST);

		buttP1 = new JPanel();
		buttP1.setPreferredSize(new Dimension(160, 80));
		buttP1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 40));
		save1 = new JButton("\u004c\u01b0\u0075");
		cancel1 = new JButton("\u0048\u0075\u0079\u0309");
		save1.setPreferredSize(new Dimension(60, 30));
		cancel1.setPreferredSize(new Dimension(60, 30));
		save1.setFocusable(false);
		cancel1.setFocusable(false);
		buttP1.add(save1);
		buttP1.add(cancel1);

		subRight.add(upCon1, BorderLayout.NORTH);
		subRight.add(bott1, BorderLayout.CENTER);
		subRight.add(buttP1, BorderLayout.SOUTH);	

		panel.add(subLeft, BorderLayout.WEST);
		panel.add(subRight, BorderLayout.EAST);

		sang.setSelected(true);
		sang1.setSelected(true);
		nvList();
	}

	public void refresh()
	{
		System.out.println("running");
		nvList();
		gia.setText("");
		tenMon.setText("");
	}

	public void refresh1()
	{
		nvList();
		gia1.setText("");
		tenMon1.setText("");
	}

	public void nvList()
	{
		options1.removeAllItems();
		options1.addItem("0: \u0043\u0068\u006f\u0323\u006e \u006e\u0068\u00e2\u006e \u0076\u0069\u00ea\u006e");
		try 
        {
            try (Statement stmt = db.con.createStatement())
            { 
                String query = "select manv, ten from nhanvien";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                	options1.addItem(rs.getString("manv") + ": " + rs.getString("ten"));
            }          
        }
        catch (SQLException ea)
        {}
	}

	public void changeField(String str)
	{
		String []array = str.split(":");
		if (!(array[0].equals("0")))
		{
			String query = "select ten, tinhtrang, luong, maca from nhanvien where manv=" + array[0];
			try 
	        {
	            try (Statement stmt = db.con.createStatement())
	            {
	                ResultSet rs = stmt.executeQuery(query);
	                while (rs.next())
	                {
	                	tenMon1.setText(rs.getString("ten"));
	                	double sal1 = Double.parseDouble(rs.getString("luong"));
	                	int sal = (int)sal1;
	                	gia1.setText(String.valueOf(sal));
	                	if (rs.getString("tinhtrang").equals("1"))
	                		tTrang1.setSelectedIndex(0);
	                	else
	                		tTrang1.setSelectedIndex(1);

	                	if (rs.getString("maca").equals("1"))
	                		sang1.setSelected(true);
	                	else
	                		toi1.setSelected(true);
	                }
	            }          
	        }
	        catch (SQLException ea)
	        {}
		}
	}
}