package Clase5;

public class Ejercicio_5_6 
{
		public static void baffle(String blimp) {
			System.out.println(blimp); //6, value of blimp is "rattle"
			zippo("ping", -5); //7
		}

		public static void zippo(String quince, int flag) 
		{
			if (flag < 0) { //2, 8
				System.out.println(quince + " zoop"); //9
			}else{ //3
				System.out.println("ik"); //4
				baffle(quince); //5
				System.out.println("boo-wa-ha-ha"); //10, end
				//ik
				//rattle
				//ping zoop
				//boo-wa-ha-ha
			}
		}
		
		public static void main(String[] args) 
		{
			zippo("rattle", 13); //1
		}

}
