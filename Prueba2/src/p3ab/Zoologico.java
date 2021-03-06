package p3ab;

import java.util.ArrayList;

public class Zoologico {

	String nombre;
	ArrayList<Jaula> jaulas;
	
	public Zoologico(String nombre) {
		this.nombre = nombre;
		this.jaulas = new ArrayList<Jaula>();
	}
	
	public Jaula getJaula(int i) {
		return jaulas.get(i);
	}
	
	public void addJaula(Jaula jaula) {
		jaulas.add(jaula);
	}
	
	public void removeJaula(int i) {
		jaulas.remove(i);
	}
	
	public int size() {
		return jaulas.size();
	}
	
	public void getGPS(String GPS) {
		for(int i = 0 ; i < jaulas.size() ; i++) {
			if(GPS.equals(jaulas.get(i).getCoord())) {
				jaulas.get(i).getDatos();
			}
		}
	}
	
	
}
