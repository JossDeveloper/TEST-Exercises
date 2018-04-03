package pe.java.puma.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.java.puma.bean.EmpleadoBean;
import pe.java.puma.util.SqlServerDBConection;

public class EmpleadoDAO extends BaseDAO
{
	public String getFirmaEmpleado(int idEmpleado)
	{
		con = SqlServerDBConection.getConection();
		String nombreEmpleado = "";
		try
		{
			String sql =  "select e.Tratamiento + ' ' + e.Nombre + ' ' + e.Apellidos "
						+ "from tb_empleados e "
						+ "where e.IdEmpleado = ?;";		
			ps = con.prepareStatement(sql); 
			ps.setInt(1, idEmpleado);
			rs = ps.executeQuery();
			//get result
			if (rs.next()) 
			{
				nombreEmpleado = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConexion();
		}
		return nombreEmpleado;
	}
	
	public List<EmpleadoBean> getListaEmpleados()
	{
		con = SqlServerDBConection.getConection();
		List<EmpleadoBean> lista = new ArrayList<EmpleadoBean>();
		try
		{
			String sql =  "select e.IdEmpleado, e.Nombre, e.Apellidos, e.Cargo, e.Ciudad, e.Pais "
						+ "from tb_empleados e;";
			ps = con.prepareStatement(sql); 
			rs = ps.executeQuery();
			//get result
			while (rs.next())
			{
				EmpleadoBean emp = new EmpleadoBean();
				emp.setIdEmpleado(rs.getInt(1));
				emp.setNombre(rs.getString(2));
				emp.setApellido(rs.getString(3));
				emp.setCargo(rs.getString(4));
				emp.setCiudad(rs.getString(5));
				emp.setCountry(rs.getString(6));
				lista.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConexion();
		}
		return lista;
	}
	
	public boolean createNewEmpleado(EmpleadoBean newEmpleado)
	{
		con = SqlServerDBConection.getConection();
		boolean ok = false;
		try
		{
			String sql =  "insert into "
						+ "tb_empleados (IdEmpleado,Apellidos,Nombre,Cargo,Tratamiento,FechaNacimiento,Ciudad,Pais) "
						+ "values ((select MAX(IdEmpleado) + 1 from tb_empleados),?,?,?,?,?,?,?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, newEmpleado.getApellido());
			ps.setString(2, newEmpleado.getNombre());
			ps.setString(3, newEmpleado.getCargo());
			ps.setString(4, newEmpleado.getTratamiento());
			ps.setDate(5, new Date(newEmpleado.getNacimiento().getTime()));
			ps.setString(6, newEmpleado.getCiudad());
			ps.setString(7, newEmpleado.getCountry());
			//verify execute's result
			if(ps.executeUpdate() == 1)
			{
				ok = true;
				System.out.println("Creacion exitosa del nuevo Empleado");
			} else {
				System.err.println("Empleado no fue creado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConexion();
		}
		return ok;
	}
	
	public boolean updateEmpleado(EmpleadoBean empleado)
	{
		con = SqlServerDBConection.getConection();
		boolean ok = false;
		try
		{
			String sql =  "update tb_empleados "
						+ "set Apellidos= ?,Nombre= ?,Cargo= ?,Tratamiento= ?,FechaNacimiento= ?,Ciudad= ?,Pais= ? "
						+ "where IdEmpleado = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, empleado.getApellido());
			ps.setString(2, empleado.getNombre());
			ps.setString(3, empleado.getCargo());
			ps.setString(4, empleado.getTratamiento());
			ps.setDate(5, new Date(empleado.getNacimiento().getTime()));
			ps.setString(6, empleado.getCiudad());
			ps.setString(7, empleado.getCountry());
			ps.setInt(8, empleado.getIdEmpleado());
			//verify execute's result 
			if(ps.executeUpdate() == 1)
			{
				ok = true;
				System.out.println("Actualizacion exitosa del Empleado");
			} else {
				System.err.println("Empleado no fue actualizado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConexion();
		}
		return ok;
	}
	
	public boolean deleteEmpleado(int idEmpleado)
	{
		con = SqlServerDBConection.getConection();
		boolean ok = false;
		try
		{
			String sql =  "delete from tb_empleados where IdEmpleado = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEmpleado);
			//verify execute's result 
			if(ps.executeUpdate() == 1)
			{
				ok = true;
				System.out.println("Eliminacion exitosa del Empleado");
			} else {
				System.err.println("Empleado no fue eliminado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConexion();
		}
		return ok;
	}
	
	public EmpleadoBean getDatosEmpleado(int idEmpleado)
	{
		con = SqlServerDBConection.getConection();
		EmpleadoBean empleado = new EmpleadoBean();
		try
		{
			String sql =  "select IdEmpleado,Apellidos,Nombre,Cargo,Tratamiento,FechaNacimiento,Ciudad,Pais "  
						+ "from tb_empleados "
						+ "where IdEmpleado = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEmpleado);
			rs = ps.executeQuery();
			//get result
			if (rs.next()) 
			{
				empleado.setIdEmpleado(rs.getInt(1));
				empleado.setApellido(rs.getString(2));
				empleado.setNombre(rs.getString(3));
				empleado.setCargo(rs.getString(4));
				empleado.setTratamiento(rs.getString(5));
				empleado.setNacimiento(rs.getDate(6));
				empleado.setCiudad(rs.getString(7));
				empleado.setCountry(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConexion();
		}
		return empleado;
	}

}
