package AvanzeUno;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;


public class InAndOutV3
{
	private static Scanner in;
	public static List<String> titularD = Stream.of("TENIS", "FOOTBALL", "NATACION", "HANDBALL").collect(Collectors.toList());
	public static List<String> titularN = Stream.of("ACCIDENTE", "AVISO", "REPORTAJE", "INTERNACIONAL").collect(Collectors.toList());
	public static List<String> feedO = new ArrayList<>();
	public static List<String> feedD = new ArrayList<>();

	public static void deporte(String estado)
	{
		Random random = new Random();
		if(Objects.equals("OCUPADO", estado)) 
		{
			feedO.add(titularN.get(random.nextInt(titularD.size())));
		}else
		{
			feedD.add(titularN.get(random.nextInt(titularD.size())));
		}
	}
	
	public static void noticia(String estado)
	{
		Random random = new Random();
		if(Objects.equals("OCUPADO", estado)) 
		{
			feedO.add(titularN.get(random.nextInt(titularN.size())));
		}else
		{
			feedD.add(titularN.get(random.nextInt(titularN.size())));
		}
	}
	
	public static void main(String[] args) 
	{
		String estado;
		in = new Scanner(System.in);
		System.out.print("ESTADO: ");
		estado = in.nextLine();
	
		for(int i = 0; i < 10; i++)
		{
			if(Math.random() < 0.5) {
				noticia(estado);
			}else{
				deporte(estado);
			}
		}
		
		if(Objects.equals("DESOCUPADO", estado))
			System.out.println(feedD);
		else 
		{
		System.out.print("ESTADO: ");
		estado = in.nextLine();		
		if(Objects.equals("DESOCUPADO", estado)) 
		{
			feedD = feedO;
			System.out.println(feedD);
		}
		}
		
	}
}