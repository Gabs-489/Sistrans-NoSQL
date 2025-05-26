package uniandes.edu.co.demo.modelo;

public class ServiciosSolicitados {
    
    private String servicio;
    private int solicitudes;

    public ServiciosSolicitados(String servicio, int solicitudes) {
        this.servicio = servicio;
        this.solicitudes = solicitudes;
    }

    public String getServicio() {
        return servicio;
    }
    
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(int solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public String toString() {
        return "ServiciosSolicitados [Servicio=" + servicio + ", Solicitudes=" + solicitudes + "]";
    }
}

