import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;
import java.awt.event.*;
import javax.swing.border.TitledBorder;
import java.sql.*;

public class logInFrame extends JFrame implements ActionListener
{
    JPanel ipanel, ipanel2, panel, panel2, basePanel, imgPanel;
    JLabel lUser, lPass, img;
    JTextField tUser;
    JPasswordField tPass = new JPasswordField();
    JButton bSubmit;
    ConnectDB db;

    public logInFrame()
    {
        basePanel = new JPanel();
    	basePanel.setLayout(new BorderLayout());
    	basePanel.setSize(200, 400);

    	imgPanel = new JPanel();
    	imgPanel.setSize(200, 200);
    	this.setTitle("\u0110\u0103\u006e\u0067 \u006e\u0068\u00e2\u0323\u0070 \u0071\u0075\u0061\u0301\u006e \u0070\u0068\u01a1\u0309");
	// this.setLayout(new BorderLayout())
        panel = new JPanel();
        ipanel = new JPanel();
        ipanel2 = new JPanel();
        ipanel2.setLayout(new BorderLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "\u0110\u0103\u006e\u0067 \u006e\u0068\u00e2\u0323\u0070", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new Font("Arial", Font.BOLD, 14), 
                new Color(153, 102, 0));
        panel.setBorder(titledBorder);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setSize(200,30);
        ipanel.setLayout(new GridLayout(2,2,1,15));
        ipanel2.setLayout(new FlowLayout());
        ipanel.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
        ipanel2.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));

        lUser = new JLabel("\u0054\u00ea\u006e \u006e\u0067\u01b0\u01a1\u0300\u0069 \u0064\u0075\u0300\u006e\u0067:");
        lPass = new JLabel("\u004d\u00e2\u0323\u0074 \u006b\u0068\u00e2\u0309\u0075:");
        lUser.setFont(new Font("Arial", Font.BOLD, 13));
        lPass.setFont(new Font("Arial", Font.BOLD, 13));
        lUser.setForeground(new Color(153, 102, 0));
        lPass.setForeground(new Color(153, 102, 0));
        tUser = new JTextField();
        // tPass = new JTextField();
        tUser.setPreferredSize(new Dimension(150,25));
        tPass.setMaximumSize(new Dimension(150,25));
        bSubmit = new JButton("\u0110\u0103\u006e\u0067 \u006e\u0068\u00e2\u0323\u0070");
        bSubmit.setForeground(new Color(153, 102, 0));
        bSubmit.setBounds(0,0,50,20);
        bSubmit.setFocusable(false);


        ipanel.add(lUser);
        ipanel.add(tUser);
        ipanel.add(lPass);
        ipanel.add(tPass);
        ipanel2.add(bSubmit, BorderLayout.NORTH);

        ImageIcon image0 = new ImageIcon("phologin.png");
        img = new JLabel();
        img.setIcon(image0);

        imgPanel.add(img);

        panel.add(ipanel);
        panel.add(ipanel2);

        basePanel.add(panel, BorderLayout.EAST);
        basePanel.add(imgPanel, BorderLayout.WEST);

        bSubmit.addActionListener(this);

        this.add(basePanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("pho.png");
        this.setIconImage(image.getImage());
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == bSubmit)
        {
            System.out.println("clicked");
            if (tPass.getPassword().length == 0 || tUser.getText().equals(""))
                JOptionPane.showMessageDialog(new JFrame(), "WRONG INFO OR EMPTY", "Dialog", JOptionPane.ERROR_MESSAGE);
            else
            {
                db = new ConnectDB();
                String fName = "", ca = "", maca = "", manv = "";
                int flag = 0;
                try 
                {
                    try (Statement stmt = db.con.createStatement())
                    {
                        String pwd = new String(tPass.getPassword()); 
                        String query = "select manv, ten, maca from nhanvien where ten='" + tUser.getText() + "' and matkhau='" + pwd + "'";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next())
                        {
                            flag = 1;
                            fName = rs.getString("ten");
                            maca = rs.getString("maca");
                            manv = rs.getString("manv");
                            break;
                        }

                        if (flag == 1)
                        {
                            String query1 = "select tenca from ca where maca='" + maca + "'";
                            ResultSet rs1 = stmt.executeQuery(query1);
                            while (rs1.next())
                            {
                                 ca = rs1.getString("tenca");
                                 break;
                            } 
                            String []nArray = fName.split(" ");
                            new OrderGUI(manv, nArray[nArray.length - 1], ca); 
                            this.dispose();
                        }
                        else
                            JOptionPane.showMessageDialog(new JFrame(), "WRONG INFO OR EMPTY", "Dialog", JOptionPane.ERROR_MESSAGE);
                     }          
                }
                catch (SQLException ea)
                {
                    System.out.println("error");
                    System.out.println("sqle: " + ea.getMessage());
                    System.out.println("sqls: " + ea.getSQLState());
                    System.out.println("sqle: " + ea.getErrorCode());
                }
             }

        }
        
    }
}