package pe.java.puma.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlServerDBConection 
{
	public static Connection getConection()
	{
		Connection con=null;
		try 
		{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=Comercial2018;integratedSecurity=true;");
            System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException e) {
			System.out.println("ERROR"+ e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR"+ e.getMessage());
        }
		return con;
	}

}
