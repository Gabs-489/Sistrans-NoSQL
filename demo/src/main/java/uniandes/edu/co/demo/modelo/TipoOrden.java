package uniandes.edu.co.demo.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoOrden {
    Servicio,
    Receta,
    SERVICIO,
    RECETA;

    @JsonCreator
    public static TipoOrden fromString(String value) {
        return TipoOrden.valueOf(value.toUpperCase());
    }
}