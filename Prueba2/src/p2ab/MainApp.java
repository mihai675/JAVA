package p2ab;

import java.util.Scanner;

public class MainApp {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		//CREAMOS EL ZOLOGICO
		Zoologico ZOO = new Zoologico("WinBoo");
		//CREAMOS LOS ANIMALES
		Especie LOBO = new Especie("1", "JUAL", "LOBO", "OBEJAS Y CIERVOS", "GRANDE", "ALASKA");
		Especie OBEJA = new Especie("2", "ALDS", "OBEJA", "PASTOS", "CHICO", "CHILE");
		Especie CIERVO = new Especie("3", "QVEX", "CIERVO", "PASTOS", "CHICO", "AGRENTINA");
		//CREAMOS LAS JAULAS
		Jaula JAULA1 = new Jaula("1", "2,2");
		Jaula JAULA2 = new Jaula("2", "5,8");
		//AGREGAMOS LOS ANIMALES A LAS JAULAS
		JAULA1.addAnimal(LOBO);
		JAULA2.addAnimal(OBEJA);
		JAULA2.addAnimal(CIERVO);
		//AGREGAMOS LAS JAULAS AL ZOLOGICO
		ZOO.addJaula(JAULA1);
		ZOO.addJaula(JAULA2);
		//CREAMOS UN TELEFONO CON UN SET DE CORDENADAS
		Smartphone telefono = new Smartphone("2,2");
		
		System.out.println("ESTAMOS SERCA DE UNA JAULA, DESEA AUDIO (1) O TEXTO (2)");
		//OBTENEMOS LAS COORDENADAS Y REVISAMOS Y ALGUNA JAULA ESTA CERCA
		int elec = in.nextInt();
		if(elec == 1)
			System.out.println("AUDIO");
		if(elec == 2)
			System.out.println("TEXTO");
		ZOO.getGPS(telefono.getCoor());
		
		//CAMBIAMOS LAS COORDENADAS (SE MOVIO) Y REVISAMOS POR OTRA JAULA
		telefono.setCoor("5,8");
		ZOO.getGPS(telefono.getCoor());
		
		
		
	}

}
