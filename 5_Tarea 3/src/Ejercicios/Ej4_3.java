package Ejercicios;

public class Ej4_3 
{
	public static void printAmerican(String dia, String mes, int diaNumero, int a�o)
	{
		System.out.println(dia + ", " + mes + " " + diaNumero + ", " + "20" + a�o);
	}
	
	public static void printEuropean(String dia, String mes, int diaNumero, int a�o)
	{
		System.out.println(dia + " " + diaNumero + " " + mes + " 20" + a�o);
	}
	
	public static void main(String[] args) 
	{
		String[] Meses = new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviempre","Diciembre"};
		//int[] Dias = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int EleccionFormato = Integer.parseInt(args[0]);          
		String NombreDia = args[1];
		int Dia = Integer.parseInt(args[2]);
		int Mes = Integer.parseInt(args[3]);
		int A�o = Integer.parseInt(args[4]);
		if(EleccionFormato == 1) 
		{
			printAmerican(NombreDia, Meses[Mes-1], Dia, A�o);
		}else if(EleccionFormato == 2) 
		{
			printEuropean(NombreDia, Meses[Mes-1], Dia, A�o);
		}
		//for(int i = Dia+1; i <= Dias[Mes-1]; i++)
		//	System.out.println(i); 
	}
}
