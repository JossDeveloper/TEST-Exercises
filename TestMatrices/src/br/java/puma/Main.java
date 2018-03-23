package br.java.puma;

import br.java.puma.util.OperacionesMatriz;

public class Main 
{
	protected static OperacionesMatriz op = new OperacionesMatriz();
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try 
		{
			String[][] matriz = {{"a","b","c","d"},
								 {"e","f","g","h"},
								 {"i","j","k","l"},
								 {"m","n","o","p"}};
			System.out.println("Matriz antes de rotar:");
			op.imprimirMatriz(matriz);
			
			if(op.validationMatriz(matriz))
			{
				String[][] matrizRot; 
				
				System.out.println("Matriz despues de rotar 90 grados:");
				matrizRot = op.rotateMatriz(matriz, 90);
				op.imprimirMatriz(matrizRot);
				
				System.out.println("Matriz despues de rotar 180 grados:");
				matrizRot = op.rotateMatriz(matriz, 180);
				op.imprimirMatriz(matrizRot);
				
				System.out.println("Matriz despues de rotar 270 grados:");
				matrizRot = op.rotateMatriz(matriz, 270);
				op.imprimirMatriz(matrizRot);
				
			} else {
				System.out.println("Matriz no es regular, no es cuadrada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("ERROR - Block catch");
		}

	}

}
