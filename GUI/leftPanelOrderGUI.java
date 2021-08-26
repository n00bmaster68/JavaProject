import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class leftPanelOrderGUI {
    JPanel panel1;
    JPanel subPanel1;
    JPanel subPanel2;
    JPanel leftContent;
    JPanel rightContent;
    JLabel nameLabel;
    JLabel shiftLabel;
    JLabel name;
    JLabel shift;
    ButtonBan[] butt;
    ConnectDB db = new ConnectDB();

    public leftPanelOrderGUI(String string, String string2) {
        this.panel1 = new JPanel();
        this.panel1.setLayout(null);
        this.panel1.setBounds(0, 0, 250, 610);
        this.panel1.setLayout(new BorderLayout());
        this.subPanel1 = new JPanel();
        this.subPanel1.setBorder(new TitledBorder(new TitledBorder(new LineBorder(Color.BLACK)), "NH\u00c2N VI\u00caN ", 2, 0, new Font("San serif", 1, 14), Color.BLACK));
        this.subPanel1.setSize(250, 250);
        this.subPanel1.setLayout(new BorderLayout());
        this.subPanel1.setBackground(new Color(255, 255, 255));
        this.leftContent = new JPanel();
        this.rightContent = new JPanel();
        this.leftContent.setLayout(new BorderLayout());
        this.rightContent.setLayout(new BorderLayout());
        this.nameLabel = new JLabel();
        this.nameLabel.setBackground(new Color(255, 255, 255));
        this.nameLabel.setText("T\u00ean: ");
        this.nameLabel.setFont(new Font("Arial", 1, 13));
        this.shiftLabel = new JLabel();
        this.shiftLabel.setText("Ca: ");
        this.shiftLabel.setBackground(new Color(255, 255, 255));
        this.shiftLabel.setFont(new Font("Arial", 1, 13));
        this.name = new JLabel();
        this.name.setText(string);
        this.name.setFont(new Font("Arial", 0, 13));
        this.name.setBackground(new Color(255, 255, 255));
        this.shift = new JLabel();
        this.shift.setText(string2);
        this.shift.setFont(new Font("Arial", 0, 13));
        this.shift.setBackground(new Color(255, 255, 255));
        this.leftContent.add((Component)this.nameLabel, "North");
        this.leftContent.add((Component)this.shiftLabel, "South");
        this.leftContent.setBackground(new Color(255, 255, 255));
        this.rightContent.add((Component)this.name, "North");
        this.rightContent.add((Component)this.shift, "South");
        this.rightContent.setBackground(new Color(255, 255, 255));
        this.subPanel1.add((Component)this.leftContent, "West");
        this.subPanel1.add((Component)this.rightContent, "East");
        this.subPanel2 = new JPanel();
        this.subPanel2.setBorder(new TitledBorder(new TitledBorder(new LineBorder(Color.BLACK)), "HOA\u0301 \u0110\u01a0N BA\u0300N ", 2, 0, new Font("San serif", 1, 14), Color.BLACK));
        this.subPanel2.setBackground(new Color(255, 255, 255));
        this.subPanel2.setLayout(new GridLayout(25, 2, 3, 4));
        this.butt = new ButtonBan[50];
        for (int i = 0; i < 50; ++i) {
            this.butt[i] = new ButtonBan("Ba\u0300n " + String.valueOf(i + 1), i);
            this.butt[i].setBackground(new Color(173, 173, 133));
            this.subPanel2.add((Component)this.butt[i]);
        }
        try {
            String string3 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            try (Statement statement = this.db.con.createStatement();){
                String string4 = "select soban from hoadon where DATE(ngaylap)='" + string3 + "' and tinhtrang=0;";
                ResultSet resultSet = statement.executeQuery(string4);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("soban"));
                    this.butt[resultSet.getInt("soban") - 1].setBackground(Color.GREEN);
                }
            }
        }
        catch (SQLException sQLException) {
            System.out.println("error");
            System.out.println("sqle: " + sQLException.getMessage());
            System.out.println("sqls: " + sQLException.getSQLState());
            System.out.println("sqle: " + sQLException.getErrorCode());
        }
        this.panel1.setBackground(new Color(255, 255, 255));
        this.panel1.add(this.subPanel2);
        this.panel1.add((Component)this.subPanel1, "North");
    }
}