package com.perfulandia.Busqueda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Busqueda {
    private int idBusqueda;
    private String nombre;
    private String aroma;
    private String marca;
    private Double mlMin;
    private Double mlMax;
    private Double precioMin;
    private Double precioMax;
}
