package br.java.puma.util;

public class OperacionesMatriz 
{
	//validation if the matrix was square (# columns = # rows)
	public boolean validationMatriz(String[][] matrix)
	{
		boolean val = true;
		int rows = matrix.length;
		for(int i = 0; i < rows; i++)
		{
			if(matrix[i].length != rows) val = false;
		}
		return val;
	}
	
	//rotation of the matrix
	public String[][] rotateMatriz(String[][] matrix, int graus)
	{
		int tamanho = matrix.length;
		String[][] novaMatrix = new String[tamanho][tamanho];
		
		for(int i = 0, j = tamanho - 1; i < tamanho && j >= 0; i++, j--)
		{
			for(int r = 0, s = tamanho -1; r < tamanho && s >= 0; r++, s--)
			{
				switch (graus) 
				{
					case  90:	
						novaMatrix[r][j] = matrix[i][r]; break;
					case 180:
						novaMatrix[i][r] = matrix[j][s]; break;
					case 270:
						novaMatrix[i][r] = matrix[r][j]; break;
				}
			}				
		}		
		return novaMatrix; 
	}
	
	//to print matrix in the console
	public void imprimirMatriz(String[][] matrix)
	{
		int rows = matrix.length;
		for (int i = 0; i < rows; i++)
		{
			for(int j = 0; j < rows; j++)
			{
				System.out.print("  " + matrix[i][j] + "  ");
			}	System.out.println();
		}
	}
	
} 	//ending the class OperacionesMatriz
