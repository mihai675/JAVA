package p1ab;

import java.util.ArrayList;

public class Jaula {
	
	String id;
	ArrayList<Especie> especies;
	
	public Jaula(String id) {
		this.id = id;
		this.especies = new ArrayList<Especie>();
	}
	
	public void addAnimal(Especie especie) {
		especies.add(especie);
	}
	
	public void getDatos() {
		for(int i = 0 ; i < especies.size() ; i++) {
			especies.get(i).showDatos();
		}
	}
	
	public int size() {
		return especies.size();
	}

}