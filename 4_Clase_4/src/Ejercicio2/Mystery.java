package Ejercicio2;

public class Mystery 
{
	public static int mystery(int a, int b) 
	{
		if (b==0) return 1;
		if (b%2==0) 
			return mystery(a+a, b/2);
		return mystery(a*a, b/2) * a;
	}
	
	public static void main(String[] args) 
	{
		System.out.println(mystery(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
	}

}
