package UD2;

public class RatonMultihilo implements Runnable {
	private String nombre;
	private long tiempoAlimentacion;
	
	//Constructor
	public RatonMultihilo(String nombre, int tiempoAlimentacion) {
		this.nombre=nombre;
		this.tiempoAlimentacion=tiempoAlimentacion;
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
		RatonMultihilo mickey = new RatonMultihilo("Mickey", 5);
		RatonMultihilo jerry = new RatonMultihilo("Jerry", 10);
		RatonMultihilo perez = new RatonMultihilo("Pérez", 2);
		long tiempoInicio, tiempoFin;
		System.out.println("-> Inicio de la Ejecución del programa MULTIHILO");
		tiempoInicio = System.nanoTime();
		
		Thread hilo_mikey = new Thread(mickey);
		Thread hilo_jerry = new Thread(jerry);
		Thread hilo_perez = new Thread(perez);
		
		tiempoFin = System.nanoTime();
		System.out.println("-> Fin de la Ejecución. Tiempo transcurrido: "+(int)((tiempoFin-tiempoInicio)/1e9)+" segundos");
	}

}