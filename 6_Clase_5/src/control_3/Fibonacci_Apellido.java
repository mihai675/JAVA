package control_3;

public class Fibonacci_Apellido 
{	
	public static long serieFibo(long numeroIngresado){
		  long retornado=0;
		  if(numeroIngresado == 0)
		  {
		   retornado=numeroIngresado;
		  }
		  if(numeroIngresado == 1)
		  {
		   retornado=numeroIngresado;
		  }
		  if(numeroIngresado > 1)
		  {
		   retornado=serieFibo(numeroIngresado-2)+serieFibo(numeroIngresado-1);
		  }
		  return retornado;
		 }
	
	public static void main(String[] args) //Si se ingresan numeros muy grandes, por ejemplo 60, no se ejecuta el programa, por ejemplo con 40
	                                       //resulto 102334155 pero con 50 ya no da resultado.
	{
			System.out.print(serieFibo(Integer.parseInt(args[0])));
	}
}
