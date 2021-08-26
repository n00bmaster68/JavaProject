import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;
import java.sql.*;

public class EditMon 
{
	JPanel panel, subLeft, subRight, leftCon, rightCon, leftCon1, rightCon1, upCon, upCon1, bott, bott1, buttP, buttP1;
	JButton save, cancel, save1, cancel1;
	JRadioButton pho, monPhu, pho1, monPhu1;
	JComboBox options, options1, tTrang, tTrang1;
	JTextField  gia, gia1, tenMon, tenMon1, soThit, soThit1;
	JLabel []labels = new JLabel[10];
	ConnectDB db = new ConnectDB();
	String idE = "", id = "";

	public EditMon()
	{
		labels[0] = new JLabel("\u0054\u00ea\u006e\u002f\u0063\u01a1\u0303: ");
		labels[1] = new JLabel("\u0054\u00ea\u006e\u002f\u0063\u01a1\u0303: ");
		labels[2] = new JLabel("\u0047\u0069\u0061\u0301: ");
		labels[3] = new JLabel("\u0047\u0069\u0061\u0301: ");
		labels[4] = new JLabel("\u0054\u0069\u0300\u006e\u0068 \u0074\u0072\u0061\u0323\u006e\u0067: ");
		labels[5] = new JLabel("\u0054\u0069\u0300\u006e\u0068 \u0074\u0072\u0061\u0323\u006e\u0067: ");

		String []tt = {"\u0110\u0061\u006e\u0067 \u0062\u0061\u0301\u006e", "\u004b\u0068\u00f4\u006e\u0067 \u0062\u0061\u0301\u006e"};

		tTrang = new JComboBox(tt);
		tTrang1 = new JComboBox(tt);

		tTrang.setPreferredSize(new Dimension(30, 30));

		gia = new JTextField();
		gia1 = new JTextField();
		tenMon = new JTextField();
		tenMon1 = new JTextField();
		soThit = new JTextField();
		soThit1 = new JTextField();

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

	    soThit.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyReleased(KeyEvent evt) {
	            try {
	                long number = Long.parseLong(soThit.getText());
	            } catch (Exception e) {
	                if (!(soThit.getText().equals("")))
	                {
	                	JOptionPane.showMessageDialog(new JFrame(), "Only Numbers Allowed");
	                	soThit.setText((soThit.getText()).substring(0, (soThit.getText()).length() - 1));
	                }
	            }
	        }
	    });

	    soThit1.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyReleased(KeyEvent evt) {
	            try {
	                long number = Long.parseLong(soThit1.getText());
	            } catch (Exception e) {
	                if (!(soThit1.getText().equals("")))
	                {
	                	JOptionPane.showMessageDialog(new JFrame(), "Only Numbers Allowed");
	                	soThit1.setText((soThit1.getText()).substring(0, (soThit1.getText()).length() - 1));
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
		soThit.setPreferredSize(new Dimension(80, 20));
		soThit1.setPreferredSize(new Dimension(80, 20));

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

		pho = new JRadioButton("\u0050\u0068\u01a1\u0309"); 
		monPhu = new JRadioButton("\u004d\u006f\u0301\u006e \u0070\u0068\u0075\u0323"); 
		pho1 = new JRadioButton("\u0050\u0068\u01a1\u0309");  
		monPhu1 = new JRadioButton("\u004d\u006f\u0301\u006e \u0070\u0068\u0075\u0323"); 

		ButtonGroup gr = new ButtonGroup();
		gr.add(pho);
		gr.add(monPhu);;

		ButtonGroup gr1 = new ButtonGroup();
		gr1.add(pho1);
		gr1.add(monPhu1);;

		// String []data = {"test", "test1"};
		options1 = new JComboBox();
		options1.setPreferredSize(new Dimension(80, 20));
		changeOptions("Pho");
		options1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
            	if (pho1.isSelected())
                	changeFieldPho((e.getItem()).toString());
                else
                	changeFieldMP((e.getItem()).toString());
            }
        });
		changeFieldPho("\u004c\u01a1\u0301\u006e");
		upCon = new JPanel();
		upCon1 = new JPanel();
		upCon.setLayout(new BorderLayout());
		upCon1.setLayout(new BorderLayout());

		upCon.setPreferredSize(new Dimension(160, 50));
		upCon.add(pho, BorderLayout.WEST);
		upCon.add(monPhu, BorderLayout.EAST);

		upCon1.setPreferredSize(new Dimension(160, 50));
		upCon1.add(pho1, BorderLayout.WEST);
		upCon1.add(monPhu1, BorderLayout.EAST);
		upCon1.add(options1, BorderLayout.SOUTH);

		bott = new JPanel();
		bott.setLayout(new BorderLayout());
		bott.setPreferredSize(new Dimension(160, 90));
		// bott.setBackground(Color.BLACK);
		leftCon = new JPanel();
		JPanel panelX = new JPanel();
		panelX.setLayout(new BorderLayout());
		panelX.add(labels[4], BorderLayout.NORTH);
		panelX.add(new JLabel("\u0053\u00f4\u0301 \u0074\u0068\u0069\u0323\u0074: "));

		leftCon.setLayout(new BorderLayout());
		leftCon.add(labels[0], BorderLayout.NORTH);
		leftCon.add(panelX, BorderLayout.CENTER);
		leftCon.add(labels[2], BorderLayout.SOUTH);
		// leftCon.setBackground(Color.RED);
		bott.add(leftCon, BorderLayout.WEST);

		JPanel panelY = new JPanel();
		panelY.setLayout(new BorderLayout());
		panelY.add(tTrang, BorderLayout.NORTH);
		panelY.add(soThit);
		rightCon = new JPanel();
		rightCon.setLayout(new BorderLayout());
		rightCon.add(tenMon, BorderLayout.NORTH);
		rightCon.add(panelY);
		rightCon.add(gia, BorderLayout.SOUTH);
		bott.add(rightCon, BorderLayout.EAST);
		// bott.setBackground(Color.RED);

		buttP = new JPanel();
		// buttP.setBackground(Color.BLACK);
		buttP.setPreferredSize(new Dimension(160, 60));
		// buttP.setBackground(Color.BLUE);
		buttP.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 30));
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
		bott1.setPreferredSize(new Dimension(160, 85));
		leftCon1 = new JPanel();
		leftCon1.setLayout(new BorderLayout());
		JPanel panelX1 = new JPanel();
		panelX1.setLayout(new BorderLayout());
		panelX1.add(labels[5], BorderLayout.NORTH);
		panelX1.add(new JLabel("\u0053\u00f4\u0301 \u0074\u0068\u0069\u0323\u0074: "));
		leftCon1.add(labels[1], BorderLayout.NORTH);
		leftCon1.add(panelX1);
		leftCon1.add(labels[3], BorderLayout.SOUTH);
		bott1.add(leftCon1, BorderLayout.WEST);

		JPanel panelY1 = new JPanel();
		panelY1.setLayout(new BorderLayout());
		panelY1.add(tTrang1, BorderLayout.NORTH);
		panelY1.add(soThit1);
		rightCon1 = new JPanel();
		rightCon1.setLayout(new BorderLayout());
		rightCon1.add(tenMon1, BorderLayout.NORTH);
		rightCon1.add(panelY1);
		rightCon1.add(gia1, BorderLayout.SOUTH);
		bott1.add(rightCon1, BorderLayout.EAST);

		buttP1 = new JPanel();
		// buttP1.setBackground(Color.BLACK);
		buttP1.setPreferredSize(new Dimension(160, 70));
		// buttP1.setBackground(Color.BLUE);
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

		pho.setSelected(true);
		pho1.setSelected(true);
	}

	public void refresh()
	{
		gia.setText("");
		soThit.setText("");
		tenMon.setText("");
	}

	public void refresh1()
	{
		gia1.setText("");
		soThit1.setText("");
		tenMon1.setText("");
	}

	public void changeOptions(String s)
	{
		if (s == "MonPhu")
		{
			soThit1.setEnabled(false);
			options1.removeAllItems();
			try 
            {
                try (Statement stmt = db.con.createStatement())
                { 
                    String query = "select tenmon from monphu where giaban > 0";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    	options1.addItem(rs.getString("tenmon"));
                }          
            }
            catch (SQLException ea)
            {}
		}
		else if (s == "Pho")
		{
			options1.removeAllItems();
			soThit1.setEnabled(true);
			try 
            {
                try (Statement stmt = db.con.createStatement())
                { 
                    String query = "select maco from topho where giaban > 0";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    	options1.addItem(rs.getString("maco"));
                }          
            }
            catch (SQLException ea)
            {}
		}
	}

	public void changeFieldPho(String type)
	{
		tenMon1.setText(type);
		String query = "select matp, giaban, tinhtrang from topho where maco='" + type + "'";
		try 
        {
            try (Statement stmt = db.con.createStatement())
            {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {
                	double price1 = Double.parseDouble(rs.getString("giaban"));
                	int price = (int)price1;
                	gia1.setText(String.valueOf(price));
                	idE = rs.getString("matp");

                	if (rs.getString("tinhtrang").equals("1"))
                		tTrang1.setSelectedIndex(0);
                	else
                		tTrang1.setSelectedIndex(1);
                }
            }          
        }
        catch (SQLException ea)
        {}
	}

	public void changeFieldMP(String type)
	{
		tenMon1.setText(type);
		String query = "select giaban, mama, tinhtrang from monphu where tenmon='" + type + "'";
		try 
        {
            try (Statement stmt = db.con.createStatement())
            {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {
                	double price1 = Double.parseDouble(rs.getString("giaban"));
                	int price = (int)price1;
                	gia1.setText(String.valueOf(price));
                	idE = rs.getString("mama");
                	if (rs.getString("tinhtrang").equals("1"))
                		tTrang1.setSelectedIndex(0);
                	else
                		tTrang1.setSelectedIndex(1);
                }
            }          
        }
        catch (SQLException ea)
        {}
	}

	public void enableField(String op)
	{
		if (op.equals("1"))
			soThit.setEnabled(true);
		else 
			soThit.setEnabled(false);
	}
}