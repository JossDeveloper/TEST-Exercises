package br.java.puma.util;

import java.util.Vector;

public class OperacionesVector 
{
	//to print in console the vector's integer elements
	public void imprimirVector(Vector<Integer> vector, String control)
	{		
		System.out.print("Elementos del vector, " + control + ": ");
		for (Integer integer : vector) 
		{
			System.out.print(integer + " / ");
		}	System.out.println();
	}
	
	//order vector's integer elements
	public void ordenarItemsVector(Vector<Integer> vector, char tipoOrden)
	{
		Integer elementoMax = Integer.MIN_VALUE;
		Integer elementoMin = Integer.MAX_VALUE;
		Integer elementoMaxAux = Integer.MAX_VALUE;;
		Integer elementoMinAux = Integer.MIN_VALUE;;
		
		Vector<Integer> vectorOrdenado = new Vector<Integer>();
		
		for (int i = 0; i < vector.size(); i++) 
		{
			switch (tipoOrden) 
			{
				case 'A':	//ascending order
					for (Integer integer : vector) 
					{
						if(integer < elementoMin && integer > elementoMinAux)
						{
							elementoMin = integer;
						}
					}
					vectorOrdenado.addElement(elementoMin);
					elementoMinAux = elementoMin;
					elementoMin = Integer.MAX_VALUE;
					break;
				case 'D':	//descending order
					for (Integer integer : vector) 
					{
						if(integer > elementoMax && integer < elementoMaxAux)
						{
							elementoMax = integer;
						}
					}
					vectorOrdenado.addElement(elementoMax);
					elementoMaxAux = elementoMax;
					elementoMax = Integer.MIN_VALUE;
					break;
			}
		}
		imprimirVector(vectorOrdenado, tipoOrden == 'A'? "orden asc" : "orden desc");
	}
	
	//separate vector's integer elements
	public void separarItemsVector(Vector<Integer> vector) 
	{
		Vector<Integer> vectorPar = new Vector<Integer>();
		Vector<Integer> vectorImpar = new Vector<Integer>();
		
		for (Integer integer : vector) 
		{
			if(integer % 2 == 0)
			{
				vectorPar.addElement(integer);
			} else {
				vectorImpar.addElement(integer);
			}
		}
		imprimirVector(vectorPar, "pares");
		imprimirVector(vectorImpar, "impares");
	}

}	//ending the class OperacionesVector
