package p3ab;

import java.util.ArrayList;

public class User {
	
	String name;
	int points;
	ArrayList<String> ruta;
	
	public User(String name) {
		this.name = name;
		this.ruta = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addObjetivo(String objetivo) {
		ruta.add(objetivo);
	}
	
	public void removeObjetivo() {
		ruta.remove(0);
	}
	
	public void puntos() {
		System.out.println(points);
	}
	
	public void checkObjetivo(String objetivo) {
		if(ruta.size() > 0)
			if(ruta.get(0).equals(objetivo)) {
				points = points + 1;
				System.out.println(name +" ACABAS DE GANAR UN PUNTO!");
				removeObjetivo();
			}
	}
}
