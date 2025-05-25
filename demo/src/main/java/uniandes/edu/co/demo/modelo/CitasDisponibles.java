package uniandes.edu.co.demo.modelo;

import java.util.Date;

public class CitasDisponibles {
    
    private Date fecha;
    private String medico;
    private String ips;
    private String servicio;

    public CitasDisponibles(Date fecha, String medico, String ips, String servicio) {
        this.fecha = fecha;
        this.medico = medico;
        this.ips = ips;
        this.servicio = servicio;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getMedico() {
        return medico;
    }
    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "CitasDisponibles [fecha=" + fecha + ", medico=" + medico + ", ips=" + ips + ", servicio=" + servicio
                + "]";
    }

}
