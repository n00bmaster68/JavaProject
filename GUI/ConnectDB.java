import java.sql.*;

public class ConnectDB
{
	String url = "jdbc:mysql://localhost/quan_li_hoa_don1";
	String user = "root";
	String pwd = "123456";
	Connection con;

	public ConnectDB()
	{
		try 
		{
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("connected");
		}
		catch (SQLException e)
		{
			System.out.println("not connected");
			System.out.println("sqle: " + e.getMessage());
			System.out.println("sqls: " + e.getSQLState());
			System.out.println("sqle: " + e.getErrorCode());
		}
	}

	public static void main(String[] args) {
		new ConnectDB();
	}
}