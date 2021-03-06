package p1ab;

import java.util.ArrayList;

public class Zoologico {

	String nombre;
	ArrayList<Jaula> jaulas;
	ArrayList<Totem> totems;
	
	public Zoologico(String nombre) {
		this.nombre = nombre;
		this.jaulas = new ArrayList<Jaula>();
		this.totems = new ArrayList<Totem>();
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
	
	public void addTotem(Totem totem) {
		totems.add(totem);
	}
	
	public Totem getTotem(int i) {
		return totems.get(i);
	}
	
	public void getTotemInfoNFC(String NFC) {
		for(int i = 0 ; i < totems.size() ; i++) {
			if(NFC.equals(totems.get(i).getNFC())) {
				totems.get(i).getInfo("NFC");
			}
		}
	}
	
	public void getTotemInfoQR(String QR) {
		for(int i = 0 ; i < totems.size() ; i++) {
			if(QR.equals(totems.get(i).getQR())) {
				totems.get(i).getInfo("QR");
			}
		}
	}
	
	
}
