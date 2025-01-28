package gestorAplicacion.habitaciones;

import java.io.Serializable;

public class Habitacion implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador único para la serialización

    private int numero;
    private double precioBase;
    private String tipo;
    private String vista;
    private String capacidad;
    private boolean servicioHabitacion;
    private boolean sucia;
    private double descuento;
    private boolean ocupada;
    private int nochesOcupadas;

    public Habitacion(int numero, double precioBase, String tipo, String vista, String capacidad,
            boolean servicioHabitacion, boolean sucia, double descuento, boolean ocupada) {
        this.numero = numero;
        this.precioBase = precioBase;
        this.tipo = tipo;
        this.vista = vista;
        this.capacidad = capacidad;
        this.servicioHabitacion = servicioHabitacion;
        this.sucia = sucia;
        this.descuento = descuento;
        this.ocupada = ocupada;
        this.nochesOcupadas = 0;
    }

    public int getNumero() {
        return numero;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public String getTipo() {
        return tipo;
    }

    public String getVista() {
        return vista;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public boolean isServicioHabitacion() {
        return servicioHabitacion;
    }

    public boolean isSucia() {
        return sucia;
    }

    public double getDescuento() {
        return descuento;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", vista='" + vista + '\'' +
                ", capacidad='" + capacidad + '\'' +
                ", servicioHabitacion=" + servicioHabitacion +
                ", sucia=" + sucia +
                ", precioBase=" + precioBase +
                ", descuento=" + descuento +
                ", ocupada=" + ocupada +
                '}';
    }

    public double calcularPrecioConDescuento(double v) {
        return precioBase - (precioBase * descuento / 100);
    }

    public void limpiar() {
        sucia = false; // La habitación se limpia
    }

    public void verificarEstadoSucia() {
        // La habitación se considera sucia si su número es par y no es cero
        sucia = (nochesOcupadas % 2 == 0 && nochesOcupadas != 0);
    }

    public void incrementarNochesOcupadas() {
        nochesOcupadas++;

    }
}
