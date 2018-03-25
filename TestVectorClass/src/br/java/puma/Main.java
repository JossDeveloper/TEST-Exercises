package br.java.puma;

import java.util.Vector;
import br.java.puma.util.OperacionesVector;

public class Main 
{
	protected static OperacionesVector op = new OperacionesVector();
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try
		{
			Vector<Integer> inteiros = new Vector<Integer>();
			int num; 
			//save random numbers into vector 
			for(int i = 0; i < 10; i++)
			{
				num = (int) (Math.random() * 100);
				inteiros.addElement(num);
			}
			op.imprimirVector(inteiros, "random");
			op.ordenarItemsVector(inteiros, 'A');
			op.ordenarItemsVector(inteiros, 'D');
			op.separarItemsVector(inteiros);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("ERROR - Block catch");
		}
	}
	
}
