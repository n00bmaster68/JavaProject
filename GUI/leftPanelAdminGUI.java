import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;  
import java.util.*; 
import java.awt.event.*;

public class leftPanelAdminGUI
{
	JPanel panel1, subPanel1, subPanel2, subPanel3, leftContent, rightContent;
	JLabel nameLabel, shiftLabel, name, shift; 
	JButton hoaDon, dsNV, dsMon, quanLiMon, quanLiNV;

	public leftPanelAdminGUI()
	{
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 182, 250);
		panel1.setLayout(new BorderLayout());

		hoaDon = new JButton("\u004b\u0069\u00ea\u0309\u006d \u0068\u006f\u0061\u0301 \u0111\u01a1\u006e");

		quanLiNV = new JButton("\u0051\u0075\u0061\u0309\u006e \u006c\u0069\u0301 NV");
		quanLiMon = new JButton("\u0051\u0075\u0061\u0309\u006e \u006c\u0069\u0301 \u006d\u006f\u0301\u006e");
		
		dsNV = new JButton("\u0044\u0061\u006e\u0068 \u0073\u0061\u0301\u0063\u0068 \u004e\u0056");
		dsMon = new JButton("\u0044\u0061\u006e\u0068 \u0073\u0061\u0301\u0063\u0068 \u006d\u006f\u0301\u006e");

		hoaDon.setFocusable(false);
		hoaDon.setPreferredSize(new Dimension(132, 25));
		hoaDon.setFont(new Font("Arial", Font.BOLD, 13));

		quanLiNV.setFocusable(false);
		quanLiNV.setPreferredSize(new Dimension(132, 25));
		quanLiNV.setFont(new Font("Arial", Font.BOLD, 13));

		quanLiMon.setFocusable(false);
		quanLiMon.setPreferredSize(new Dimension(132, 25));
		quanLiMon.setFont(new Font("Arial", Font.BOLD, 13));

		dsMon.setFocusable(false);
		dsMon.setPreferredSize(new Dimension(132, 25));
		dsMon.setFont(new Font("Arial", Font.BOLD, 13));

		dsNV.setFocusable(false);
		dsNV.setPreferredSize(new Dimension(132, 25));
		dsNV.setFont(new Font("Arial", Font.BOLD, 13));

		subPanel1 = new JPanel();
		subPanel1.setBorder(new TitledBorder(new TitledBorder(new LineBorder(Color.BLACK)), "\u0048\u004f\u0041\u0301 \u0110\u01a0\u004e ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("San serif", Font.BOLD, 14), Color.BLACK));
		subPanel1.setLayout(new FlowLayout());
		subPanel1.add(hoaDon);

		subPanel2 = new JPanel();
		subPanel2.setBorder(new TitledBorder(new TitledBorder(new LineBorder(Color.BLACK)), "\u004e\u0048\u00c2\u004e \u0056\u0049\u00ca\u004e ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("San serif", Font.BOLD, 14), Color.BLACK));
		subPanel2.setLayout(new FlowLayout());
		subPanel2.setPreferredSize(new Dimension(10, 50));
		subPanel2.add(quanLiNV);
		subPanel2.add(dsNV);

		subPanel3 = new JPanel();
		subPanel3.setBorder(new TitledBorder(new TitledBorder(new LineBorder(Color.BLACK)), "\u004d\u004f\u0301\u004e \u0102\u004e ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("San serif", Font.BOLD, 14), Color.BLACK));
		subPanel3.setPreferredSize(new Dimension(100, 90));
		subPanel3.setLayout(new FlowLayout());
		subPanel3.add(quanLiMon);
		subPanel3.add(dsMon);

		panel1.add(subPanel2, BorderLayout.CENTER);
		panel1.add(subPanel3, BorderLayout.SOUTH);
		panel1.add(subPanel1, BorderLayout.NORTH);


	}
}