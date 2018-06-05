package p3ab;

public class MainApp {
	
	public static void main(String[] args) {
		
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
		//CREAMOS UN TELEFONO CON UNA RUTA
		Smartphone telefono = new Smartphone("JUAN");
		telefono.addObjetivo("5,8");
		telefono.addObjetivo("2,2");
		
		telefono.checkObjetivo("5,8");
		telefono.checkObjetivo("2,2");
		
		telefono.puntos();
		
		Smartphone telefono2 = new Smartphone("JAIME");
		telefono2.addObjetivo("5,8");
		telefono2.addObjetivo("2,2");
		telefono2.addObjetivo("7,2");
		
		telefono2.checkObjetivo("5,8");
		telefono2.checkObjetivo("2,2");
		telefono2.checkObjetivo("7,2");
		
		telefono2.puntos();
		
		
		
	}

}
