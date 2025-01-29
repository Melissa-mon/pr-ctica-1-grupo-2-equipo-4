package gestorAplicacion.personal;

import gestorAplicacion.Servicios.Habitacion;

public class Conserje extends Empleado{

    public Conserje(String rol, String puesto) {
        super(rol, puesto);
    }

    @Override
    public String generarSaludo (String nombre, String rol){
        return "Hola, "+ nombre+ "soy un " + rol;
    }

    public void limpiarHabitacion(){

    }

    public void lavarRopa(){
        
    }

    public void limpiarHabitacion(Habitacion habitacion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}