package pe.java.puma.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import pe.java.puma.Main;
import pe.java.puma.bean.EmpleadoBean;
import pe.java.puma.gestor.GestionaEmpleadoService;
import pe.java.puma.util.RenderTabla;

public class PnlOpciones extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	private JTable tblEmpleados;
	private JScrollPane scpEmpleados;
	private JLabel lblPnlOpciones; 
	private JButton btnAddEmpleado;
	private DefaultTableModel datos;
	private PnlDatosEmpleado pnlEmpleado;
	private GestionaEmpleadoService empService = new GestionaEmpleadoService();
	private Main main;
	
	public PnlOpciones(Main m)
	{
		setOpaque(true);
		setLayout(null);
		setBackground(new Color(210, 230, 220));
		
		main = m;
		
		lblPnlOpciones = new JLabel("Mantenimiento de Empleados", SwingConstants.CENTER);
		lblPnlOpciones.setBounds(0, 0, 800, 40);
		lblPnlOpciones.setOpaque(true);
		lblPnlOpciones.setFont(new Font("Verdana", Font.BOLD, 30));
 		lblPnlOpciones.setForeground( Color.white );
 		lblPnlOpciones.setBackground(new Color(150, 0, 0));
		this.add(lblPnlOpciones);
		
		btnAddEmpleado = new JButton("Nuevo Empleado");
		btnAddEmpleado.setEnabled(true);
		btnAddEmpleado.setBounds(600, 60, 150, 20);
		btnAddEmpleado.setBackground(new Color(150, 150, 150));
		btnAddEmpleado.setForeground( Color.white );
		btnAddEmpleado.addActionListener(this);
 		this.add(btnAddEmpleado);
		
		datos = new DefaultTableModel()
				{
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int col)
					{
						return false;
					}
				};
        datos.addColumn("ID");
        datos.addColumn("Nombre");
        datos.addColumn("Apellido");
        datos.addColumn("Cargo");
        datos.addColumn("Ciudad");
        datos.addColumn("Pais");
        datos.addColumn("Update");
        datos.addColumn("Delete");
        
        tblEmpleados = new JTable(datos);
        tblEmpleados.setDefaultRenderer(Object.class, new RenderTabla());
        centrarID();
                
        TableColumnModel columnModel = tblEmpleados.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(90);
        columnModel.getColumn(2).setPreferredWidth(90);
        columnModel.getColumn(3).setPreferredWidth(180);
        columnModel.getColumn(4).setPreferredWidth(90);
        columnModel.getColumn(5).setPreferredWidth(90);
        columnModel.getColumn(6).setPreferredWidth(60);
        columnModel.getColumn(7).setPreferredWidth(60);
        
        tblEmpleados.addMouseListener
        (new MouseAdapter() 
        	{
        		public void mouseReleased(MouseEvent me) 
        		{
        			if(tblEmpleados.getRowCount() > 0 && tblEmpleados.getColumnCount() > 0)
	                {
		                int row = tblEmpleados.rowAtPoint(me.getPoint());
		                System.out.println("You clicked at row " + row);
		                int col = tblEmpleados.columnAtPoint(me.getPoint());
		                System.out.println("You clicked at col " + col);
		                
		                Object value = tblEmpleados.getValueAt(row, col);
		                
		                if(value instanceof JButton)
		                {
		                	JButton btn = (JButton) value; 
		                	btn.doClick();
		                	int idEmpleado = (int) tblEmpleados.getValueAt(row, 0);
		                	String mensajeOperacion = "";
		                	//select option
		                	if(btn.getText().equals("D"))
		                	{
		                		System.out.println("Eliminar id=" + idEmpleado);
		                		
		                		int input = JOptionPane.showConfirmDialog(null, 
		                                "Eliminar Empleado ID=" + idEmpleado + "?", 
		                                "Mantenimiento de Empleados",
		                                JOptionPane.YES_NO_CANCEL_OPTION);
		                		if (input == 0)
		                		{
		                			mensajeOperacion = empService.deleteEmpleado(idEmpleado);
		                			System.out.println(mensajeOperacion);
		                			listarEmpleados();
		                		} 
		                	} else
		                	if(btn.getText().equals("U"))
		                	{
		                		System.out.println("Modificar id=" + tblEmpleados.getValueAt(row, 0));
		                		EmpleadoBean empleado = empService.getDatosEmpleado(idEmpleado);
		                		if (empleado != null)
		                		{
		                			ocultarPanel();
			            			pnlEmpleado = new PnlDatosEmpleado(main, empleado);
			            			main.add(pnlEmpleado);
			            			main.setSize(500, 400);
			            			pnlEmpleado.setVisible(true);
		                		}
		                	} 
		                }
	                }
        		}
			}
        );
        
        scpEmpleados = new JScrollPane(tblEmpleados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scpEmpleados.setBounds(50, 100, 700, 200);
        this.add(scpEmpleados);
        listarEmpleados();
	}

	public void listarEmpleados()
	{
		List<EmpleadoBean> listaEmpleados = empService.getListaEmpleados();
		
		JButton btnUpdateEmpleado = new JButton("U");
		JButton btnDeleteEmpleado = new JButton("D");
		
		while(datos.getRowCount() > 0) datos.removeRow(0);
		
		for (EmpleadoBean empleadoBean : listaEmpleados) 
		{
			Object rowData[] = new Object[datos.getColumnCount()];
			int i = 0;
			rowData[i++] = empleadoBean.getIdEmpleado();
			rowData[i++] = empleadoBean.getNombre();
			rowData[i++] = empleadoBean.getApellido();
			rowData[i++] = empleadoBean.getCargo();
			rowData[i++] = empleadoBean.getCiudad();
			rowData[i++] = empleadoBean.getCountry();
			rowData[i++] = btnUpdateEmpleado;
			rowData[i++] = btnDeleteEmpleado;
			datos.addRow(rowData);
		}
	}
	
	private void centrarID() 
	{
		 DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
         modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
         tblEmpleados.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
	}
	
	private void ocultarPanel()
	{
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if( e.getSource() == btnAddEmpleado )
		{
			System.out.println("Add Empleado");
			ocultarPanel();
			pnlEmpleado = new PnlDatosEmpleado(main, null);
			main.add(pnlEmpleado);
			main.setSize(500, 400);
			pnlEmpleado.setVisible(true);
		}
	}
	
}
