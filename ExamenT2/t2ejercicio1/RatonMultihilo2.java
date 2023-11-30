package t2ejercicio1;

import java.util.ArrayList;

public class RatonMultihilo2 implements Runnable {
    private String nombre;
    private long tiempoAlimentacion;

    // Constructor
    public RatonMultihilo2(String nombre, int tiempoAlimentacion) {
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
        RatonMultihilo2 mickey = new RatonMultihilo2("Mickey", 5);
        RatonMultihilo2 jerry = new RatonMultihilo2("Jerry", 10);
        RatonMultihilo2 perez = new RatonMultihilo2("Pérez", 2);

        System.out.println("-> Inicio de la Ejecución del programa MULTIHILO");
        long tiempoInicio = System.nanoTime();

        Thread hilo_mikey = new Thread(mickey);
        Thread hilo_jerry = new Thread(jerry);
        Thread hilo_perez = new Thread(perez);

        hilo_mikey.start();
        hilo_jerry.start();
        hilo_perez.start();

        //ArrayList para almacenar los estados de hilo_mikey
        ArrayList<Thread.State> estadosHiloMickey = new ArrayList<>();

        while (hilo_mikey.isAlive()) {
            Thread.State estadoActual = hilo_mikey.getState();
            //Si el arraylist no contiene el estado, añadirlo
            if (!estadosHiloMickey.contains(estadoActual)) {
                estadosHiloMickey.add(estadoActual);
            }
        }

        // Muestra los estados recopilados en el ArrayList
        for (int i=0; i<estadosHiloMickey.size(); i++) {
            System.out.println("Estado "+i+" de hilo_mikey: "+estadosHiloMickey.get(i));
        }

        long tiempoFin = System.nanoTime();
        System.out.println("-> Fin de la Ejecución. Tiempo transcurrido: " + (int) ((tiempoFin - tiempoInicio) / 1e9) + " segundos");
    }
}
