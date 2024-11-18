package tarearepaso5;

import java.util.Random;

public class ProcesoConsumidor implements Runnable {
    private final RecursoCompartido recurso;
    private final String nombre;
    private final Random random = new Random();

    public ProcesoConsumidor(RecursoCompartido recurso, String nombre) {
        this.recurso = recurso;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            int cantidadNecesaria = 10 + random.nextInt(16); // Necesita entre 10 y 25
            try {
                Thread.sleep(50 + random.nextInt(151)); // Espera un tiempo antes de intentar consumir
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            recurso.consumir(cantidadNecesaria, nombre);

            try {
                Thread.sleep(100 + random.nextInt(401)); // Tiempo de actividad entre 100 y 500 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
