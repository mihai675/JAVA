package p1ab;

public class Especie extends Animal{
	String especie;
	String alimentacion;
	String crecimiento;
	String zonas;

	public Especie(String id, String nombre, String especie, String alimentacion, String crecimiento, String zonas) {
		super(id, nombre);
		this.especie = especie;
		this.alimentacion = alimentacion;
		this.crecimiento = crecimiento;
		this.zonas = zonas;
	}
	
	public void showDatos() {
		System.out.println(getNombre() + ": es un animal de la especie " + especie + ". Su alimentacion consiste principalmente en: " 
							+ alimentacion + ". A lo largo de su vida tiene las siguientes etapas de crecimiento " + crecimiento +
							" desarollandose principalmente en las siguientes zonas: " + zonas);
	}

}
