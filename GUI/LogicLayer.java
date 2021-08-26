import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class LogicLayer
{
	public static String unAccent(String s) 
   {
      String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
      Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
      return pattern.matcher(temp).replaceAll("");
   }

	public int getSoThit(String option)
	{
		ConnectDB db = new ConnectDB();
		String query = "select sothit from topho where maCO='" + option + "'";
		// System.out.println(query);
		try 
        {
            try (Statement stmt = db.con.createStatement())
            {
            	ResultSet rs1 = stmt.executeQuery(query);
            	
            	while(rs1.next())
            	{
            		// System.out.println("thit: " + String.valueOf(rs1.getInt("sothit")));
            		return rs1.getInt("sothit");
            	}
            }
        }
        catch(SQLException e){}
        return 0;
	}
	private int checkThit(String []option)
	{
		int count = 0;
		String []meat = {"\u0074\u0061\u0301\u0069", "\u006e\u0061\u0323\u006d", "\u0067\u00e2\u0300\u0075", "\u0067\u00e2\u006e", "\u0076\u0069\u00ea\u006e"};
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < option.length; j++)
				if (meat[i].equals(option[j].toLowerCase()))
					count += 1;
		return count;
	}

	public boolean checkDetail(String option, String detail)
	{

		String strArray[] = detail.split(" ");
		if (checkThit(strArray) <= getSoThit(option))
			return true;
		return false;
	}

	public boolean checkIsNum(JTextField soLuong)
	{
		try {
            long number = Long.parseLong(soLuong.getText());
            return true;
        } catch (Exception e) {
            if (!(soLuong.getText().equals("")))
            {
            	JOptionPane.showMessageDialog(new JFrame(), "Only Numbers Allowed");
            	return false;
            }
        }
        return true;
	}

	public boolean checkAl(char ch)
	{
		if(Character.isDigit(ch)){
            JOptionPane.showMessageDialog(null, "Enter Alphabet Only !");
            return false;
        }
        return true;
	}
}