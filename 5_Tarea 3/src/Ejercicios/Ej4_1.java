package Ejercicios;

public class Ej4_1 {	
	//Todo empiesa en main(), imprime "No, I " despues llama a zoop(), en donde se llama a baffle() para que imprima "wug" y 
	//una vez impreso llama a ping(). En ping() se imprime newline ".", despues de esto se vuelve a zoop() donde se imprime "You wugga "
	//para despues llamar nuevamente a baffle() y el que imprime nuevamente "wug" y despues se llama ping el que imprime otra vez
	//newline ".". Despues de lo anterior se vuelve a main() en donde se imprime "I " para despues llamar a baffle(), en donde se imprime
	//"wug" y despues se llama a ping para que imprima newline "." y luego de esto termina el codigo y quedando asi:
	//No, I wug.
	//You wugga wug.
	//I wug.
	
	//No se hacer un stack diagram
	
	//Si llamamos a baffle() al final de ping() se creara un ciclo sin fin en el que se imprimiria:
	//No, I wug.
	//wug.
	//wug.
	//wug.
	//.. y asi infinitamente.	
}	
