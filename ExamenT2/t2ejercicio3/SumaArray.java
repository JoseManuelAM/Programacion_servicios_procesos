package t2ejercicio3;

import java.util.Scanner;
import java.util.Arrays;

public class SumaArray {

    public static void main(String[] args) {
    	Scanner leer = new Scanner(System.in);    
    	
        // Solicitar al usuario el tamaño del array
    	System.out.print("Introduzca la longitud del array: ");
        int longitud = leer.nextInt();
        leer.close();

        // Crear y asignar valores aleatorios al array
        int[] array = new int[longitud];
        for(int i = 0; i<array.length; i++) {
        	array[i] = (int)(Math.random()*100);
        	}
        System.out.print("Array generado: "+Arrays.toString(array)+"\n"); 

        // SUMA SECUENCIAL
        long inicioSecuencial = System.currentTimeMillis();
        int sumaSecuencial = 0;
        for (int i=0; i<array.length; i++) {
        	try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sumaSecuencial = sumaSecuencial+array[i];
        }
        long finSecuencial = System.currentTimeMillis();
        System.out.println("\nSuma secuencial: " + sumaSecuencial);
        System.out.println("Tiempo transcurrido secuencial: "+(finSecuencial-inicioSecuencial)+" ms");

        // SUMA MULTIHILO
        long inicioMultihilo = System.currentTimeMillis();
        // Crear las dos mitades iguales del array
        int[] array1 = Arrays.copyOfRange(array, 0, array.length/2);
        int[] array2 = Arrays.copyOfRange(array, array.length/2, array.length);
        SumaMitadThread hilo1 = new SumaMitadThread(array1);
        SumaMitadThread hilo2 = new SumaMitadThread(array2);
        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        // Esperar a que los hilos acaben
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Sumar las dos mitades del array
        int sumaMultihilo = hilo1.getSumaMitad() + hilo2.getSumaMitad();
        long finMultihilo = System.currentTimeMillis();
        System.out.println("\nSuma multihilo: "+sumaMultihilo);
        System.out.println("Tiempo transcurrido multihilo: "+(finMultihilo-inicioMultihilo)+" ms");
    }
}