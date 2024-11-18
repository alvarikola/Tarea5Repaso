package tarearepaso5;


public class TareaRepaso5 {

    
    public static void main(String[] args) {
        // Crear el recurso compartido con un límite de 600 unidades
        RecursoCompartido recurso = new RecursoCompartido(600);

        // Crear y lanzar el hilo suministrador
        Thread suministrador = new Thread(new Suministrador(recurso));
        suministrador.start();

        // Crear y lanzar los 10 hilos consumidores
        for (int i = 1; i <= 10; i++) {
            new Thread(new ProcesoConsumidor(recurso, "Proceso-" + i)).start();
        }
    }   
}
