package pe.java.puma.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class JDatePicker 
{
	private Calendar cal = new GregorianCalendar();
	private JPanel panel;
	private JDialog dialg;
	private JTable table;
	private JScrollPane scroll;
	private JLabel title; 
	private JButton prev, next;
	private DefaultTableModel model;
	
	private int year = Calendar.getInstance().get(Calendar.YEAR);
	private int month = Calendar.getInstance().get(Calendar.MONTH);
	private int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	
	public JDatePicker(JFrame parent)
	{
		String [] daysWeek = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	    
		dialg = new JDialog();
	    dialg.setSize(250,200);
	    dialg.setTitle("Swing Calendar");
	    dialg.setLayout(new BorderLayout());
	    
		title = new JLabel();
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		prev = new JButton("<<");
		prev.setSize(30, 10);
	    prev.addActionListener(new ActionListener() 
							    {
							      public void actionPerformed(ActionEvent ae) 
							      {
							        cal.add(Calendar.MONTH, -1);
							        updateMonth();
							      }
							    });
	    next = new JButton(">>");
	    next.setSize(30, 10);
	    next.addActionListener(new ActionListener() 
							    {
							      public void actionPerformed(ActionEvent ae) 
							      {
							        cal.add(Calendar.MONTH, +1);
							        updateMonth();
							      }
							    });
	    
	    model = new DefaultTableModel(null, daysWeek)
				    {
						private static final long serialVersionUID = 1L;
						public boolean isCellEditable(int row, int col)
						{
							return false;
						}
					};
	    table = new JTable(model);
	    table.addMouseListener
	    (new MouseAdapter()
    		{
	    		public void mouseReleased(MouseEvent me)
	    		{
	    			int row = table.rowAtPoint(me.getPoint());
	    			int col = table.columnAtPoint(me.getPoint());
	    			
	    			if (table.getValueAt(row, col) != null)
	    			{
	    				day = (int) table.getValueAt(row, col);
	    				System.out.println("dia="+day);
	    				month = cal.get(Calendar.MONTH);
	    				year = cal.get(Calendar.YEAR);
	    				dialg.dispose();
	    			}
	    		}
    		}
	    );
	    
	    scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    panel = new JPanel();
	    panel.setLayout(new BorderLayout());
	    panel.add(title, BorderLayout.CENTER);
	    panel.add(prev, BorderLayout.WEST);
	    panel.add(next, BorderLayout.EAST);
	    
	    dialg.add(panel, BorderLayout.NORTH);
	    dialg.add(scroll, BorderLayout.CENTER);
	    dialg.setLocationRelativeTo(parent);
	    dialg.setModal(true);
	    
	    this.updateMonth();
	    
	    dialg.setVisible(true);
	}
	
	public void updateMonth()
	{
		cal.set(Calendar.DAY_OF_MONTH, 1);
		 
	    String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
	    int year = cal.get(Calendar.YEAR);
	    int startDay = cal.get(Calendar.DAY_OF_WEEK);
	    int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
	    System.out.println("weeks=" + weeks);
	    int i = startDay - 1;
	    
	    if(startDay > 5) weeks += 1;
	    if(startDay == 1 && numberOfDays >= 29) weeks = 5;
	    if(startDay >= 2 && numberOfDays <= 29) weeks = 5;
	    if(startDay == 6 && numberOfDays == 31) weeks = 6;
	    if(startDay == 7 && numberOfDays >= 30) weeks = 6;
	    
	    title.setText(month + " " + year);
	    model.setRowCount(0);
	    model.setRowCount(weeks);
	    
	    for(int day = 1; day <= numberOfDays; day++)
	    {
	    	model.setValueAt(day, i/7 , i%7 );    
	    	i = i + 1;
	    }
	}
	
	public String setPickedDate() 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendario = Calendar.getInstance();
		calendario.set(year, month, day);
		return sdf.format(calendario.getTime());
	}

}
