package t2ejercicio3;

public class SumaMitadThread extends Thread {
    private int[] array;
    private int sumaMitad;

    // Constructor
    public SumaMitadThread(int[] array) {
        this.array = array;
        this.sumaMitad = 0;
    }

    @Override
    public void run() {
        for (int i=0; i<array.length; i++) {
        	try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sumaMitad = sumaMitad+array[i];
        }
    }

    // Obtener valor de la suma del array
    public int getSumaMitad() {
        return sumaMitad;
    }
}