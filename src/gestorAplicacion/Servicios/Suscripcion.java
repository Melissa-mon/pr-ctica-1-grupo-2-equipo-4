package gestorAplicacion.Servicios;

public class Suscripcion{
    private String tipoSuscripcion;
    private float descuento;
    private int fichaCompensacion;
    private String calidadIngredientes;
    private boolean premioEspecial;

    public Suscripcion(int visitas) {
        if (visitas == 1) {
            this.tipoSuscripcion = "primera vez";
            this.descuento = 0.05f;
            this.fichaCompensacion = 20;
            this.calidadIngredientes = "media";
        } else if (visitas > 1 && visitas < 4) {
            this.tipoSuscripcion = "por defecto";
            this.descuento = 0.0f;
            this.fichaCompensacion = 10;
            this.calidadIngredientes = "baja";
        } else if (visitas > 3 && visitas < 6) {
            this.tipoSuscripcion = "Silver";
            this.descuento = 0.15f;
            this.fichaCompensacion = 50;
            this.calidadIngredientes = "alta";
        } else if (visitas > 5) {
            this.tipoSuscripcion = "Platinum";
            this.descuento = 0.25f;
            this.fichaCompensacion = 100;
            this.calidadIngredientes = "excelente";
        }
    }
    public Suscripcion(String vetado) {
        this.tipoSuscripcion = "vetado";
        // echarDelCasino() o algo por el estilo
    }
   
    public String getTipoSuscripcion() {
        return this.tipoSuscripcion;
    }

    public void setTipoSuscripcion(String tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public float getDescuento() {
        return this.descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public int getFichaCompensacion() {
        return this.fichaCompensacion;
    }

    public void setFichaCompensacion(int fichaCompensacion) {
        this.fichaCompensacion = fichaCompensacion;
    }

    public String getCalidadIngredientes() {
        return this.calidadIngredientes;
    }

    public void setCalidadIngredientes(String calidadIngredientes) {
        this.calidadIngredientes = calidadIngredientes;
    }

    //Metodos para ver si aplica premio especial
    public boolean isPremioEspecial(){
        return this.premioEspecial;
    }

    public void setPremioEspecial(boolean premioEspecial) {
        this.premioEspecial = premioEspecial;
    }

    String getNivel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    String getNivel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}