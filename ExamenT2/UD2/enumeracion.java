package UD2;

enum Color {
	RED, GREEN, BLUE; //Constantes de la enumeración
}

public class enumeracion {
	public static void main(String[] args) {
		//Uso de la enumeración
		Color miColor;
		miColor = Color.RED;
		
		//Comparación de valores de la enumeración
		if(miColor == Color.RED) {
			System.out.println("El color es rojo.");
		} else {
			System.out.println("El color no es rojo.");
		}
	}
}
