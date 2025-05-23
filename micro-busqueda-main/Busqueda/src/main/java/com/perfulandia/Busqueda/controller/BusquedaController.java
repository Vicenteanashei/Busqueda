package com.perfulandia.Busqueda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.Busqueda.model.Busqueda;
import com.perfulandia.Busqueda.model.Producto;
import com.perfulandia.Busqueda.model.ResultadoBusqueda;
import com.perfulandia.Busqueda.service.BusquedaService;

@RestController
@RequestMapping("api/busqueda")
public class BusquedaController {
    @Autowired
    private BusquedaService busquedaService;

    @GetMapping("/todos")
    public ResponseEntity<List<Producto>> getAllProductos(){
        List<Producto> productos = busquedaService.productos();
        if(productos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @PostMapping("/buscar")
    public ResponseEntity<ResultadoBusqueda> buscarProductos(@RequestBody Busqueda busqueda) {
        ResultadoBusqueda resultado = busquedaService.buscarProductos(busqueda);
        if (resultado.getTotalResultados() == 0) {
            return new ResponseEntity<>(resultado, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) {
        Producto productoGuardado = busquedaService.save(producto);
        return new ResponseEntity<>(productoGuardado, HttpStatus.CREATED);
    }
}
