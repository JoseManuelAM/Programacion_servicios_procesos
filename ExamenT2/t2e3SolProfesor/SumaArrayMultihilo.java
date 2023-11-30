package t2e3SolProfesor;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class SumaArrayMultihilo implements Runnable {
    private ArrayList<Integer> subArrayList;
    private final int inicio;
    private final int fin;
    private int resultadoParcial = 0;

    public SumaArrayMultihilo(ArrayList<Integer> subArrayList, int inicio, int fin) {
        this.subArrayList = subArrayList;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ". Inicio: " + inicio + " - Fin: " + (fin-1));
        for (int indice = inicio; indice < fin; indice++) {
            resultadoParcial += subArrayList.get(indice);
            try {
                Thread.sleep(1);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
        System.out.println("--> Resultado PARCIAL del " + Thread.currentThread().getName() + ": " + resultadoParcial);
    }

    public static void main(String[] args) {
        int elementosArreglo;
        int sumaArray=0;
        Scanner scannerArray = new Scanner(System.in);
        long tiempoInicio, tiempoFin;

        // Crear un ArrayList dinámico, de enteros
        ArrayList<Integer> arrayDinamico = new ArrayList<>();

        // Crear una instancia de la clase Random para generar valores aleatorios
        Random random = new Random();

        System.out.print("Introduzca el tamaño del array: ");
        elementosArreglo=scannerArray.nextInt();

        // Agregar números aleatorios al ArrayList
        for (int indice = 0; indice < elementosArreglo; indice++) {
            int numeroAleatorio = random.nextInt(10); // Valor aleatorio entre 0 y 999
            arrayDinamico.add(numeroAleatorio);
        }

        //Resultado secuencial
        tiempoInicio = System.nanoTime();
        for (int i = 0; i < elementosArreglo; i++) {
            sumaArray += arrayDinamico.get(i);
            try {
                Thread.sleep(1);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
        tiempoFin = System.nanoTime();

        System.out.println("------------------------------------------------");
        System.out.println("La SUMA por el método secuencial es: " + sumaArray);
        System.out.println("-> Tiempo transcurrido: " + (int) ((tiempoFin - tiempoInicio) / 1e6) + " milisegundos");
        System.out.println("------------------------------------------------");
        System.out.println();


        SumaArrayMultihilo calculador1 = new SumaArrayMultihilo(arrayDinamico,0,(int) (elementosArreglo/2));
        SumaArrayMultihilo calculador2 = new SumaArrayMultihilo(arrayDinamico,(elementosArreglo/2),elementosArreglo);

        Thread hilocalculador1 = new Thread(calculador1);
        hilocalculador1.setName("Hilo subArray 1");
        Thread hilocalculador2 = new Thread(calculador2);
        hilocalculador2.setName("Hilo subArray 2");
        System.out.println("------------------------------------------------");

        tiempoInicio = System.nanoTime();
        hilocalculador1.start();
        hilocalculador2.start();

        // Esperar a que todos los hilos terminen
        try {
            hilocalculador1.join();
            hilocalculador2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tiempoFin = System.nanoTime();
        // Sumar los resultados parciales de cada hilo
        System.out.println("La SUMA por el método multihilo es:: " + (calculador1.resultadoParcial + calculador2.resultadoParcial));
        //Nota la diferencia sin los paréntesis. Trata todos los elementos como cadenas
//        System.out.println("La suma total es: " + calculador1.resultadoParcial + calculador2.resultadoParcial);
        System.out.println("-> Tiempo transcurrido: " + (int) ((tiempoFin - tiempoInicio) / 1e6) + " milisegundos");
        System.out.println("------------------------------------------------");
    }
}
