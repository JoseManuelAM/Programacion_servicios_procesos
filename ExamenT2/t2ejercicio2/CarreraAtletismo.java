package t2ejercicio2;

import java.util.Scanner;

public class CarreraAtletismo {
    public static void main(String[] args) {
    	Scanner leer = new Scanner(System.in);
    	
        // Solicitar la longitud de la carrera al usuario
    	System.out.println("Introduzca la longitud de la carrera en metros: ");
    	int longitud = leer.nextInt();
        Corredor.distanciaCarrera = longitud;

        // Crear corredores
        Corredor corredor1 = new Corredor("Juan", 18, "España");
        Corredor corredor2 = new Corredor("Anthony", 23, "Inglaterra");
        Corredor corredor3 = new Corredor("Gérard", 30, "Francia");
        Corredor corredor4 = new Corredor("Jose", 20, "España");
        Corredor corredor5 = new Corredor("Claire", 25, "EE.UU.");

        // Iniciar la carrera
        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor4.start();
        corredor5.start();
        
        leer.close();
    }
}