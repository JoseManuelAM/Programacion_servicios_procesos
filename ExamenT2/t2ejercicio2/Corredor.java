package t2ejercicio2;

class Corredor extends Thread {
    public static volatile int distanciaCarrera;
    private static String ganador = null;

    private int distanciaRecorrida;
    private String nombre;
    private int edad;
    private String nacionalidad;

    // Constructor
    public Corredor(String nombre, int edad, String nacionalidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.distanciaRecorrida = 0;
    }
    
    @Override
    public void run() {
    	// Mientras no hayan recorrido la distancia total de la carrera, incrementar la distancia recorrida en 1 y mostrar la distancia que lleva cada corredor
        while (distanciaRecorrida<distanciaCarrera) {
            distanciaRecorrida++;
            System.out.println(Corredor.currentThread().getName()+": "+nombre+" ha recorrido "+distanciaRecorrida+" metros.");

            // El primero que llegue a la distancia recorrida será el ganador
            if (distanciaRecorrida>=distanciaCarrera && ganador == null) {
            	ganador = nombre;
            	System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            	System.out.println("+ "+ganador+", de "+edad+" años, viniendo desde "+nacionalidad+", ha ganado la carrera!");
            	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            	System.exit(0);
            }

            // Pequeño retraso entre prints
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}