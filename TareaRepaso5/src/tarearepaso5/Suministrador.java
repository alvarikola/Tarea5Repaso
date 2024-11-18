package tarearepaso5;

import java.util.Random;

public class Suministrador implements Runnable {
    private final RecursoCompartido recurso;
    private final Random random = new Random();

    public Suministrador(RecursoCompartido recurso) {
        this.recurso = recurso;
    }

    @Override
    public void run() {
        while (true) {
            int cantidadReponer = 50 + random.nextInt(71); // Reponer entre 50 y 120
            try {
                Thread.sleep(1000); // Reponer cada 1 segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            recurso.reponer(cantidadReponer);
        }
    }
}
