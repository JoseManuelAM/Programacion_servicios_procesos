package t2e2SolProfesor;

import java.util.Scanner;

public class Corredor implements Runnable {
    private static int distanciaCarrera;
    private volatile static boolean carreraFinalizada = false;  //Variable static, de clase. Con volatile nos aseguramos de que es única

    private final String nombre;
    private final String nacionalidad;
    private int distanciaRecorrida = 0;

    private static String atletaGanador="";

    //Constructor
    public Corredor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    @Override
    public void run() {
//      El uso de this en el contexto de hilos también es adecuado y comúnmente utilizado.
//      Al igual que en el contexto de clases regulares, this en hilos se utiliza
//      para referenciar la instancia actual del objeto en el que se está ejecutando el hilo,
//      aunque no es estrictamente necesario.
            while ((this.distanciaRecorrida < distanciaCarrera) && !carreraFinalizada) {
                this.distanciaRecorrida += 1;
//              En general, la salida por consola dentro de un hilo pueden tener efectos impredecibles
//              ya que es posible que se esté realizando la salida a consola de otro hilo
//              Lo aconsejable es identificar de algún modo a qué hilo corresponde esa salida por consola
                System.out.println("|-> [" + this.nombre + "] ha recorrido " + this.distanciaRecorrida + " metros.");
                //¡Ojo, no poner sleep/paradas! Si le ponemos una parada de 1 segundo, por ejemplo,
                //los hilos se ejecutan en forma casi secuencial, porque al detener un hilo
                //damos tiempo a que todos los restantes hilos "entren" en el procesador
                //y lleguen a recorrer la distancia de carrera, e incluso, ¡¡ganan todos!!
                //Aun así, con variables de clase y volatile, se pueden dar problemas de sincronización
                if (this.distanciaRecorrida >= distanciaCarrera) {
                    carreraFinalizada = true;
                    atletaGanador += "\n-> !![" + this.nombre + "], de " + this.nacionalidad + ", ha cruzado la línea de meta!!";
                }
            }
    }

     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+---------------------------------------------------+");
        System.out.println("+ ¡Bienvenido a la carrera más apasionante del año! +");
        System.out.println("+---------------------------------------------------+");
        System.out.print("Ingrese la distancia a recorrer en la carrera (en metros): ");

        Corredor.distanciaCarrera = scanner.nextInt();

        System.out.println("+¡¡Comienza la carrera!!+");
        Corredor atleta1 = new Corredor("Carl Lewis", "USA");
        Corredor atleta2 = new Corredor("Fermin Cacho", "ESPAÑA");
        Corredor atleta3 = new Corredor("Mo Farat", "UK");
        Corredor atleta4 = new Corredor("Haile Gebrselassie", "ETIOPIA");
        Corredor atleta5 = new Corredor("John Doe", "USA");

        Thread hilo_atleta1 = new Thread(atleta1);
        Thread hilo_atleta2 = new Thread(atleta2);
        Thread hilo_atleta3 = new Thread(atleta3);
        Thread hilo_atleta4 = new Thread(atleta4);
        Thread hilo_atleta5 = new Thread(atleta5);

        hilo_atleta1.setName("Hilo Carl");
        hilo_atleta2.setName("Hilo Fermin");
        hilo_atleta3.setName("Hilo Mo");
        hilo_atleta4.setName("Hilo Haile");
        hilo_atleta5.setName("Hilo Doe");

        hilo_atleta1.start();
        hilo_atleta2.start();
        hilo_atleta3.start();
        hilo_atleta4.start();
        hilo_atleta5.start();

//      Espero a que los hilos terminen, es decir, sean conscientes de que carreraFinalizada = true
        try {
            hilo_atleta1.join();
            hilo_atleta2.join();
            hilo_atleta3.join();
            hilo_atleta4.join();
            hilo_atleta5.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Carrera terminada. ¡Tenemos un ganador!");
        System.out.println(Corredor.atletaGanador);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}