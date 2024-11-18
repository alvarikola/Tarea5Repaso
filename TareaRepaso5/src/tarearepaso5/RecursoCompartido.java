package tarearepaso5;


public class RecursoCompartido {
    private int recursoDisponible;
    private final int limiteMaximo;

    public RecursoCompartido(int limiteMaximo) {
        this.recursoDisponible = 0; // Inicialmente vacío
        this.limiteMaximo = limiteMaximo;
    }

    // Método sincronizado para consumir recurso
    public synchronized void consumir(int cantidad, String nombreProceso) {
        while (recursoDisponible < cantidad) {
            System.out.printf("%s espera. Recurso disponible (%d) es menor que la cantidad necesaria (%d).\n",
                    nombreProceso, recursoDisponible, cantidad);
            try {
                wait(); // Espera hasta que el recurso sea suficiente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        recursoDisponible -= cantidad;
        System.out.printf("%s consumió %d. Recurso disponible: %d.\n", nombreProceso, cantidad, recursoDisponible);
        notifyAll(); // Notificar al suministrador que el recurso ha disminuido
    }

    // Método sincronizado para reponer recurso
    public synchronized void reponer(int cantidad) {
        while (recursoDisponible + cantidad > limiteMaximo) {
            System.out.printf("Suministrador espera. Recurso disponible (%d) + cantidad a reponer (%d) excede el límite (%d).\n",
                    recursoDisponible, cantidad, limiteMaximo);
            try {
                wait(); // Espera hasta que haya espacio disponible
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        recursoDisponible += cantidad;
        System.out.printf("Suministrador repuso %d. Recurso disponible: %d.\n", cantidad, recursoDisponible);
        notifyAll(); // Notificar a los consumidores que el recurso ha sido repuesto
    }
}
