package pe.java.puma.gestor;

import java.util.List;
import pe.java.puma.bean.EmpleadoBean;
import pe.java.puma.dao.EmpleadoDAO;

public class GestionaEmpleadoService 
{
	EmpleadoDAO empleadoDAO = new EmpleadoDAO();
	
	public String getFirmaEmpleado(int idEmpleado)
	{
		String firmaEmpleado = empleadoDAO.getFirmaEmpleado(idEmpleado);
		return firmaEmpleado;
	}
	
	public List<EmpleadoBean> getListaEmpleados()
	{
		List<EmpleadoBean> listaEmpleados = empleadoDAO.getListaEmpleados();
		return listaEmpleados;
	}
	
	public String createNewEmpleado(EmpleadoBean newEmpleado)
	{
		boolean ok = empleadoDAO.createNewEmpleado(newEmpleado);
		if(ok) 
		{
			return "Creacion exitosa del nuevo Empleado";
		} else {
			return "Empleado no fue creado";
		}
	}
	
	public String updateEmpleado(EmpleadoBean empleado)
	{
		boolean ok = empleadoDAO.updateEmpleado(empleado);
		if(ok)
		{
			return "Actualizacion exitosa del Empleado ";
		} else {
			return "Empleado no fue actualizado";
		}
	}
	
	public String deleteEmpleado(int idEmpleado)
	{
		boolean ok = empleadoDAO.deleteEmpleado(idEmpleado);
		if(ok)
		{
			return "Eliminacion exitosa del Empleado ";
		} else {
			return "Empleado no fue eliminado";
		}
	}
	
	public EmpleadoBean getDatosEmpleado(int idEmpleado)
	{
		EmpleadoBean empleado = empleadoDAO.getDatosEmpleado(idEmpleado);
		return empleado;
	}
	
}
