package pe.java.puma;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import pe.java.puma.panel.PnlOpciones;

public class Main extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private PnlOpciones pnlOpciones;
	
	final int ancho = 800;
	final int alto  = 400;

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new Main("Pantalla Principal");
	}

	public Main(String title) throws HeadlessException 
	{
		this.setTitle(title);
		
		pnlOpciones = new PnlOpciones(this);
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((pantalla.width - this.getWidth())/3, (pantalla.height - this.getHeight())/3);
		
		this.setSize(ancho, alto);
		this.add(pnlOpciones);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void volverOpciones()
	{
		pnlOpciones.setVisible(true);
		pnlOpciones.listarEmpleados();
		this.setSize(ancho, alto);
	}
	
}
