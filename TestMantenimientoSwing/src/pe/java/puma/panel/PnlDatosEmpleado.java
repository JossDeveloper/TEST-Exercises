package pe.java.puma.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import pe.java.puma.Main;
import pe.java.puma.bean.EmpleadoBean;
import pe.java.puma.gestor.GestionaEmpleadoService;
import pe.java.puma.util.JDatePicker;

public class PnlDatosEmpleado extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JLabel lblPnlEmpleado, lblIdEmpleadoUpdate, lblIdEmpleado; 
	private JLabel lblNombre, lblApellido, lblCargo, lblTratamiento, lblNacimiento, lblCiudad, lblCountry;
	private JTextField txtNombre, txtApellido, txtCargo, txtTratamiento, txtNacimiento, txtCiudad, txtCountry;
	private JButton btnCalendar, btnConfirmar, btnCancelar, btnLimpiar;
	private GestionaEmpleadoService empService = new GestionaEmpleadoService();
	private Main main;
	
	public PnlDatosEmpleado(Main m, EmpleadoBean empleado)
	{
		setOpaque(true);
		setLayout(null);
		setBackground(new Color(210, 230, 220));
		
		this.main = m;
		
		lblPnlEmpleado = new JLabel("", SwingConstants.CENTER);
		lblPnlEmpleado.setBounds(0, 0, 500, 40);
		lblPnlEmpleado.setOpaque(true);
		lblPnlEmpleado.setFont(new Font("Verdana", Font.BOLD, 30));
		lblPnlEmpleado.setForeground( Color.white );
		lblPnlEmpleado.setBackground(new Color(150, 0, 0));
		
		lblNombre = new JLabel("Nombre:", JLabel.RIGHT);
		lblNombre.setBounds(20, 100, 120, 20);
		this.add(lblNombre);
		lblApellido = new JLabel("Apellido:", JLabel.RIGHT);
		lblApellido.setBounds(20, 125, 120, 20);
		this.add(lblApellido);
		lblCargo = new JLabel("Cargo:", JLabel.RIGHT);
		lblCargo.setBounds(20, 150, 120, 20);
		this.add(lblCargo);
		lblTratamiento = new JLabel("Tratamiento:", JLabel.RIGHT);
		lblTratamiento.setBounds(20, 175, 120, 20);
		this.add(lblTratamiento);
		lblNacimiento = new JLabel("Fecha Nacimiento:", JLabel.RIGHT);
		lblNacimiento.setBounds(20, 200, 120, 20);
		this.add(lblNacimiento);
		lblCiudad = new JLabel("Ciudad:", JLabel.RIGHT);
		lblCiudad.setBounds(20, 225, 120, 20);
		this.add(lblCiudad);
		lblCountry = new JLabel("Pais:", JLabel.RIGHT);
		lblCountry.setBounds(20, 250, 120, 20);
		this.add(lblCountry);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(150, 100, 150, 20);
		this.add(txtNombre);
		txtApellido = new JTextField();
		txtApellido.setBounds(150, 125, 150, 20);
		this.add(txtApellido);
		txtCargo = new JTextField();
		txtCargo.setBounds(150, 150, 150, 20);
		this.add(txtCargo);
		txtTratamiento = new JTextField();
		txtTratamiento.setBounds(150, 175, 150, 20);
		this.add(txtTratamiento);
		txtNacimiento = new JTextField();
		txtNacimiento.setBounds(150, 200, 120, 20);
		txtNacimiento.setEditable(false);
		this.add(txtNacimiento);
		txtCiudad = new JTextField();
		txtCiudad.setBounds(150, 225, 150, 20);
		this.add(txtCiudad);
		txtCountry = new JTextField();
		txtCountry.setBounds(150, 250, 150, 20);
		this.add(txtCountry);
		
		if(empleado == null)
		{
			lblPnlEmpleado.setText("Nuevo Empleado");
		} else {
			lblPnlEmpleado.setText("Actualizar Empleado");
			lblIdEmpleadoUpdate = new JLabel("ID del Empleado:", JLabel.RIGHT);
			lblIdEmpleadoUpdate.setBounds(20, 60, 120, 20);
			this.add(lblIdEmpleadoUpdate);
			lblIdEmpleado = new JLabel(String.valueOf(empleado.getIdEmpleado()));
			lblIdEmpleado.setBounds(150, 60, 60, 20);
			this.add(lblIdEmpleado);
			txtNombre.setText(empleado.getNombre());
			txtApellido.setText(empleado.getApellido());
			txtCargo.setText(empleado.getCargo());
			txtTratamiento.setText(empleado.getTratamiento());
			txtNacimiento.setText(empleado.getNacimiento().toString());
			txtCiudad.setText(empleado.getCiudad());
			txtCountry.setText(empleado.getCountry());
		}
		this.add(lblPnlEmpleado);
		
		btnCalendar = new JButton("#");
		btnCalendar.setBounds(272, 200, 28, 20);
		btnCalendar.addActionListener(this);
		this.add(btnCalendar);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(320, 102, 140, 16);
		btnCancelar.setBackground(new Color(150, 150, 150));
		btnCancelar.setForeground( Color.white );
		btnCancelar.addActionListener(this);
		this.add(btnCancelar);
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(320, 127, 140, 16);
		btnConfirmar.setBackground(new Color(150, 150, 150));
		btnConfirmar.setForeground( Color.white );
		btnConfirmar.addActionListener(this);
		this.add(btnConfirmar);
		btnLimpiar = new JButton("Borrar Campos");
		btnLimpiar.setBounds(320, 152, 140, 16);
		btnLimpiar.setBackground(new Color(150, 150, 150));
		btnLimpiar.setForeground( Color.white );
		btnLimpiar.addActionListener(this);
		this.add(btnLimpiar);
		
	}
	
	private boolean validarFormulario()
	{
		for(int i = 0; this.getComponents().length > i; i++)
		{
            if(this.getComponents()[i] instanceof JTextField)
            {
            	String var = ((JTextField)this.getComponents()[i]).getText();
                if( var == null || var.equals(""))
                {
                	return false;
                }
            }
        }
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if( e.getSource() == btnCancelar )
		{
			accionCancelar();
		} else
		if( e.getSource() == btnConfirmar )
		{
			accionConfirmar();
		} else
		if( e.getSource() == btnLimpiar )
		{
			accionLimpiar();
		} else
		if( e.getSource() == btnCalendar )
		{
			accionCalendar();
		}
	}
	
	private void accionCancelar()
	{
		accionLimpiar();
		System.out.println("Cancelar Mantenimiento");
		this.setVisible(false);
		main.volverOpciones();
	}
	
	private void accionLimpiar()
	{
		for(int i = 0; this.getComponents().length > i; i++)
		{
            if(this.getComponents()[i] instanceof JTextField)
            {
                ((JTextField)this.getComponents()[i]).setText("");
            }
        }
	}
	
	private void accionConfirmar()
	{
		if(validarFormulario())
		{
			String mensaje = "";
			EmpleadoBean empleado = new EmpleadoBean();
			empleado.setNombre(txtNombre.getText());
			empleado.setApellido(txtApellido.getText());
			empleado.setCargo(txtCargo.getText());
			empleado.setTratamiento(txtTratamiento.getText());
			try 
			{
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(txtNacimiento.getText());
				empleado.setNacimiento(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			empleado.setCiudad(txtCiudad.getText());
			empleado.setCountry(txtCountry.getText());
			
			if(lblIdEmpleado == null)
			{
				System.out.println("Nuevo empleado");
				mensaje = empService.createNewEmpleado(empleado);
			} else {
				System.out.println("Actualizar empleado");
				empleado.setIdEmpleado(Integer.parseInt(lblIdEmpleado.getText()));
				mensaje = empService.updateEmpleado(empleado);
			}
			int input = JOptionPane.showConfirmDialog(null, mensaje, 
	                		"Mantenimiento de Empleados", JOptionPane.DEFAULT_OPTION);
			System.out.println(mensaje + " " + input);
			accionCancelar();
		} else {
			int input = JOptionPane.showConfirmDialog(null, "Debe completar todos los campos", 
            			"Mantenimiento de Empleados", JOptionPane.DEFAULT_OPTION);
			System.out.println("No se realiza accion " + input);
		}
		
	}
	
	private void accionCalendar()
	{
		System.out.println("llamando al JDatePicker");
		txtNacimiento.setText(new JDatePicker(main).setPickedDate());
	}

}
