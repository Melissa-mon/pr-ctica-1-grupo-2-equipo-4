package gestorAplicacion.Servicios;

import java.util.Random;
import java.util.Scanner;

import gestorAplicacion.personal.Bartender;
import gestorAplicacion.personal.Cliente;
import gestorAplicacion.personal.Conserje;
import gestorAplicacion.Servicios.Suscripcion;

public class MenuHabitaciones {
    private Habitacion[][] menu;
    private String[] tipos = { "Estándar", "Suite", "Presidencial" };

    public MenuHabitaciones(Cliente cliente) {
        menu = new Habitacion[tipos.length][3];
        crearHabitaciones(cliente);
    }

    private void crearHabitaciones(Cliente cliente) {
        Random random = new Random();
        for (int i = 0; i < menu.length; i++) {
            for (int j = 0; j < menu[i].length; j++) {
                // Crear habitaciones con características aleatorias
                int numero = random.nextInt(100) + 1; // Número de habitación entre 1 y 100
                double precioBase = (i + 1) * 100; // Precio base por tipo de habitación
                String tipo = tipos[i];

                // Generar características aleatorias
                String vista = random.nextBoolean() ? "al mar" : "a la ciudad";
                String capacidad = random.nextBoolean() ? "Pequeña" : "Grande"; // Ejemplo de capacidades
                boolean servicioHabitacion = random.nextBoolean(); // Servicio a la habitación
                boolean sucia = false; // Estado de la habitación (limpia o sucia)
                Suscripcion suscripcionCliente = cliente.getSuscripcion();
                double descuento = suscripcionCliente.getDescuento();
                boolean ocupada = false; // Inicialmente, la habitación no está ocupada

                // Crear la habitación y agregarla a la matriz
                menu[i][j] = new Habitacion(numero, precioBase, tipo, vista, capacidad, servicioHabitacion, sucia,
                        descuento, ocupada);
            }
        }
    }

    public void mostrarMenu(Cliente cliente) {
        System.out.println("\n--- Menú de Habitaciones ---");
        // Obtener el nivel de suscripción del cliente
        Suscripcion suscripcionCliente = cliente.getSuscripcion();
        double descuento = suscripcionCliente.getDescuento(); // Obtener el descuento del cliente
        System.out.println(descuento);
        for (int i = 0; i < menu.length; i++) {
            System.out.println("\nTipo: " + tipos[i]);
            for (int j = 0; j < menu[i].length; j++) {
                if (menu[i][j] != null) {
                    Habitacion habitacion = menu[i][j];
                    double precioConDescuento = habitacion.getPrecioBase() * (1 - descuento); // Aplicar el
                                                                                              // descuento del
                                                                                              // cliente
                    System.out.println(suscripcionCliente.getNivel() + ": " + habitacion +
                            ", Precio (con descuento): $" + precioConDescuento);
                }
            }
        }
    }

    public void manejarEstadia(Cliente cliente, Habitacion habitacion, int nochesReservadas, Conserje conserje,
            Bartender bartender) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 1; i <= nochesReservadas; i++) {
            System.out.println("\n--- Día " + i + " de su estadía ---");

            // Incrementar el contador de noches ocupadas en la habitación
            habitacion.incrementarNochesOcupadas();
            habitacion.verificarEstadoSucia();
            // Mostrar el menú de opciones
            System.out.println("1. Solicitar servicio a la habitación");
            System.out.println("2. Solicitar limpieza de la habitación");
            System.out.println("3. Solicitar Auto");
            System.out.println("4. Salir del menú");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    Bartender bar = new Bartender("Bartender", "Habitacion");
                    bar.atenderCliente(Cliente, bartender);
                    break;
                case 2:
                    // Llamar al conserje para limpiar la habitación
                    conserje.limpiarHabitacion(Habitacion);
                    break;
                case 3:
                    Cliente.pagarCuenta();
                    break;
                case 4:
                    System.out.println("Saliendo del menú de opciones.");
                    return; // Salir del método
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
        System.out.println("Su estadia ha terminado.");
        cliente.pagarCuenta();
    }

    public void reservarHabitacion(Cliente cliente, Suscripcion[] suscripciones, Conserje conserje,
            Bartender bartender) {
        Scanner scanner = new Scanner(System.in);
        mostrarMenu(cliente);

        // Obtener el nivel de suscripción del cliente
        String nivelSuscripcionString = cliente.getSuscripcion().getNivel();
        int nivelSuscripcion = 0;

        // Determinar el índice del nivel de suscripción
        switch (nivelSuscripcionString) {
            case "Estándar":
                nivelSuscripcion = 0;
                break;
            case "Premium":
                nivelSuscripcion = 1;
                break;
            case "Primera vez":
                nivelSuscripcion = 2;
                break;
            default:
                System.out.println("Nivel de suscripción no válido.");
                return; // Salir del método si el nivel no es válido
        }

        System.out.print("Seleccione el tipo de habitación (0 = Estándar, 1 = Suite, 2 = Presidencial): ");
        int tipo = scanner.nextInt();

        // Validar las selecciones
        if (tipo < 0 || tipo >= menu.length) {
            System.out.println("Tipo de habitación no válido. Intente de nuevo.");
            return;
        }

        // Mostrar habitaciones disponibles de ese tipo
        System.out.println("\n--- Habitaciones Disponibles ---");
        for (int j = 0; j < menu[tipo].length; j++) {
            if (menu[tipo][j] != null) {
                Habitacion habitacion = menu[tipo][j];
                System.out.println("Número de habitación: " + habitacion.getNumero() +
                        ", " + habitacion);
            }
        }

        // Preguntar por el número de habitación
        System.out.print("Ingrese el número de la habitación que desea reservar: ");
        int numeroHabitacion = scanner.nextInt();

        // Buscar la habitación por número
        Habitacion habitacionReservada = null;
        for (int j = 0; j < menu[tipo].length; j++) {
            if (menu[tipo][j] != null && menu[tipo][j].getNumero() == numeroHabitacion) {
                habitacionReservada = menu[tipo][j];
                break;
            }
        }

        if (habitacionReservada != null && !habitacionReservada.isOcupada()) {
            System.out.print("Ingrese el número de noches a reservar: ");
            int noches = scanner.nextInt();
            habitacionReservada.setOcupada(true);
            cliente.agregarReserva(habitacionReservada, noches);
            System.out.println("Reserva realizada exitosamente para la habitación: " + habitacionReservada);

            manejarEstadia(cliente, habitacionReservada, noches, conserje, bartender);

        } else {
            System.out.println(
                    "No hay habitaciones disponibles para esta categoría o el número de habitación es incorrecto.");
        }
    }
}