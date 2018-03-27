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
			//to print vector's elements
			op.imprimirVector(inteiros, "random");
			//to print vector's elements in ascending order
			op.ordenarItemsVector(inteiros, 'A');
			//to print vector's elements in descending order
			op.ordenarItemsVector(inteiros, 'D');
			//to separate vector's elements in pairs and impairs
			op.separarItemsVector(inteiros);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("ERROR - Block catch");
		}
	}
	
}
