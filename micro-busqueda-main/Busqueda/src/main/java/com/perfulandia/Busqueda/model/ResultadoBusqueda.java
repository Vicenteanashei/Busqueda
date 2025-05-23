package com.perfulandia.Busqueda.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ResultadoBusqueda {
    private List<Producto> productos;
    private int totalResultados;
}
