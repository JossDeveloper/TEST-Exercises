package pe.java.puma.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Administra el cierre de conecciones
 */

public class BaseDAO 
{
	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected CallableStatement cs = null;
	protected ResultSet rs = null;
	
	protected void closeConexion() 
	{		
		this.closeRS();
		this.closePS();
		this.closeCS();
		this.closeCON();
	}
	
	/**
	 * Cierra la conexión
	 */
	private void closeCON() 
	{
		if (null != con) 
		{
			try 
			{
				con.close();
				con = null;
			} catch (SQLException e) {
				System.err.println(e.fillInStackTrace());			
			} catch (Exception e) {
				System.err.println(e.fillInStackTrace());
			}
		}
	}

	/**
	 * @param ps
	 */
	private void closePS() 
	{
		if (null != ps) 
		{
			try 
			{
				ps.close();				
			} catch (SQLException e) {
				System.err.println(e.fillInStackTrace());
			}
		}
	}
	
	/**
	 * @param cs
	 */
	private void closeCS() 
	{
		if (null != cs) 
		{
			try 
			{
				cs.close();
			} catch (SQLException e) {
				System.err.println(e.fillInStackTrace());
			}
		}
	}

	/**
	 * @param rs
	 */
	private void closeRS() 
	{
		if (null != rs) 
		{
			try 
			{
				rs.close();
			} catch (SQLException e) {
				System.err.println(e.fillInStackTrace());
			}
		}
	}

}
