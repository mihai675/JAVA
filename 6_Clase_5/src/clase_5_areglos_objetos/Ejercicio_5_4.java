package clase_5_areglos_objetos;

public class Ejercicio_5_4 {
	
	public static String checkFermat(int a, int b, int c, int n) {
		
		long a2 = (long) Math.pow(a, n);
		long b2 = (long) Math.pow(b, n);
		long c2 = (long) Math.pow(c, n);
		
		if(a2 + b2 == c2 && n > 2)
			return "Holy smokes, Fermat was wrong!";
		else if (a2 + b2 == c2 && n <= 2)
			return "Yeah n is < 2!";
		else
			return "No, that doesn�t work";
	}
	
	public static void main(String[] args) 
	{		
		System.out.print(checkFermat(Integer.parseInt(args[0]), Integer.parseInt(args[1]) ,Integer.parseInt(args[2]), Integer.parseInt(args[3])));		
	}

}
