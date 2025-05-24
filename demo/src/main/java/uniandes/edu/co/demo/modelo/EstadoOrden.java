package uniandes.edu.co.demo.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EstadoOrden {
    Vigente,
    Completada,
    Cancelada,
    VIGENTE,
    COMPLETADA,
    CANCELADA;

    @JsonCreator
    public static EstadoOrden fromString(String value) {
        return EstadoOrden.valueOf(value.toUpperCase());
    }
  
}

