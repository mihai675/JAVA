package Ejercicios;

import java.util.Scanner;

public class Ej3_4 
{
	private static Scanner in;

	public static void main(String[] args) 
	{
		int NumeroRandom = (int)(100*Math.random()) + 1;
		System.out.println("I'm thinking of a number between 1 and 100\n(including both). Can you guess what it is?");
		int Numero;
		
		in = new Scanner(System.in);
		System.out.print("Type a number: ");
	    Numero = in.nextInt();
		System.out.println("Your guess is: " + Numero);
		System.out.println("The number I was thinking of is: " + NumeroRandom);
		
		System.out.println("You were off by: " + Math.abs(Numero - NumeroRandom) + "\n");
		
	}
	
	
}