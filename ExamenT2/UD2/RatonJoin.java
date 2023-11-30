package UD2;

import java.util.Scanner;

public class RatonJoin implements Runnable {
	String nombre;
	long tiempoAlimentacion;
	
	// Constructor
	public RatonJoin(String nombre, int tiempoAlimentacion) {
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
	}
	
	public void comer() {
		try {
			System.out.println("   |-> El ratón "+nombre+" ha comenzado a alimentarse.");
			Thread.sleep(tiempoAlimentacion*1000);
			System.out.println("   |<- El ratón "+nombre+" ha terminado de alimentarse.");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.comer();
	}

	public static void main(String[] args) {
		RatonJoin mickey = new RatonJoin("Mickey", 5);
		RatonJoin jerry = new RatonJoin("Jerry", 10);
		
		long tiempoInicio = 0, tiempoFin = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-> Inicio de la Ejecución del programa MULTIHILO");
		Thread hilo_mickey = new Thread(mickey);
		Thread hilo_jerry = new Thread(jerry);
		
		try {
			System.out.print("Indique por cuál de los ratones quiere esperar (1 Mickey | 2 Jerry) o no esperar (0): ");
			switch(sc.nextInt()) {
			case 1:
				tiempoInicio = System.nanoTime();
				hilo_mickey.start();
				hilo_jerry.start();
				hilo_mickey.join();
				break;
			case 2:
				tiempoInicio = System.nanoTime();
				hilo_mickey.start();
				hilo_jerry.start();
				hilo_jerry.join();
				break;
			default:
				tiempoInicio = System.nanoTime();
				System.out.println("No esperamos por ningún ratón ...");
			}
		} catch(InterruptedException e) {
			System.out.println(e);
		}
		tiempoFin = System.nanoTime();
		System.out.println("-> Fin de la Ejecución del hilo escogido. Tiempo transcurrido: "+(int)((tiempoFin-tiempoInicio)/1e9)+" segundos");
		System.out.println("-> Fin de la Ejecución del Programa MULTIHILO.");
	}

}
