package com.perfulandia.Busqueda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.Busqueda.model.Producto;
import com.perfulandia.Busqueda.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> productos() {
        return productoRepository.findAll();
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }
}
