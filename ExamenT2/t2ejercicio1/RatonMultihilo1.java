package t2ejercicio1;

public class RatonMultihilo1 implements Runnable {
    private String nombre;
    private long tiempoAlimentacion;

    // Constructor
    public RatonMultihilo1(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void comer() {
        try {
            System.out.println("   |-> El ratón " + nombre + " ha comenzado a alimentarse.");
            Thread.sleep(tiempoAlimentacion * 1000);
            System.out.println("   |<- El ratón " + nombre + " ha terminado de alimentarse.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.comer();
    }

    public static void main(String[] args) {
        RatonMultihilo1 mickey = new RatonMultihilo1("Mickey", 5);
        RatonMultihilo1 jerry = new RatonMultihilo1("Jerry", 10);
        RatonMultihilo1 perez = new RatonMultihilo1("Pérez", 2);
        long tiempoInicio, tiempoFin;
        System.out.println("-> Inicio de la Ejecución del programa MULTIHILO");
        tiempoInicio = System.nanoTime();

        Thread hilo_mikey = new Thread(mickey);
        Thread hilo_jerry = new Thread(jerry);
        Thread hilo_perez = new Thread(perez);

        // Iniciar los hilos
        hilo_mikey.start();
        hilo_jerry.start();
        hilo_perez.start();

        // Esperar a que todos los hilos finalicen para poder calcular el tiempo transcurrido correctamente
        try {
            hilo_mikey.join();
            hilo_jerry.join();
            hilo_perez.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tiempoFin = System.nanoTime();
        System.out.println("-> Fin de la Ejecución. Tiempo transcurrido: " + (int) ((tiempoFin - tiempoInicio) / 1e9) + " segundos");
    }
}