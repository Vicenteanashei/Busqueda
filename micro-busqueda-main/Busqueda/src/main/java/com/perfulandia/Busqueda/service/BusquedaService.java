package com.perfulandia.Busqueda.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.Busqueda.model.Busqueda;
import com.perfulandia.Busqueda.model.Producto;
import com.perfulandia.Busqueda.model.ResultadoBusqueda;
import com.perfulandia.Busqueda.repository.ProductoRepository;


@Service
public class BusquedaService {
    @Autowired
    private ProductoRepository productoRepository;

    public ResultadoBusqueda buscarProductos(Busqueda busqueda) {
        // Validar rangos directamente en el objeto busqueda
        Double precioMin = busqueda.getPrecioMin();
        Double precioMax = busqueda.getPrecioMax();
        Double mlMin = busqueda.getMlMin();
        Double mlMax = busqueda.getMlMax();
        // Validar precioMin y precioMax
        if (precioMin != null && precioMax != null && precioMin > precioMax) {
            busqueda.setPrecioMin(precioMax);
            busqueda.setPrecioMax(precioMin);
        }
        // Validar mlMin y mlMax
        if (mlMin != null && mlMax != null && mlMin > mlMax) {
            busqueda.setMlMin(mlMax);
            busqueda.setMlMax(mlMin);
        }
        // Obtener todos los productos
        List<Producto> todosLosProductos = productoRepository.findAll();

        // Si no se especifica ningún filtro, devolver todos los productos
        if ((busqueda.getNombre() == null || busqueda.getNombre().isEmpty()) &&
            (busqueda.getAroma() == null || busqueda.getAroma().isEmpty()) &&
            (busqueda.getMarca() == null || busqueda.getMarca().isEmpty()) &&
            busqueda.getMlMin() == null && busqueda.getMlMax() == null &&
            busqueda.getPrecioMin() == null && busqueda.getPrecioMax() == null) {
            ResultadoBusqueda resultado = new ResultadoBusqueda();
            resultado.setProductos(todosLosProductos);
            resultado.setTotalResultados(todosLosProductos.size());
            return resultado;
        }
        // Usar un Set para evitar duplicados
        Set<Producto> productosFiltrados = new HashSet<>();

        // Filtro por nombre
        if (busqueda.getNombre() != null && !busqueda.getNombre().isEmpty()) {
            todosLosProductos.stream().filter(producto -> producto.getNombre().toLowerCase().contains(busqueda.getNombre().toLowerCase())).forEach(productosFiltrados::add);
        }
        // Filtro por aroma
        if (busqueda.getAroma() != null && !busqueda.getAroma().isEmpty()) {
            todosLosProductos.stream().filter(producto -> producto.getAroma().toLowerCase().contains(busqueda.getAroma().toLowerCase())).forEach(productosFiltrados::add);
        }
        // Filtro por marca
        if (busqueda.getMarca() != null && !busqueda.getMarca().isEmpty()) {
            todosLosProductos.stream().filter(producto -> producto.getMarca().toLowerCase().contains(busqueda.getMarca().toLowerCase())).forEach(productosFiltrados::add);
        }
        // Si no se aplicó ningún filtro de texto, usar todos los productos como base
        List<Producto> productosBase = productosFiltrados.isEmpty() ? todosLosProductos : productosFiltrados.stream().toList();

        // Aplicar filtros numéricos de forma acumulativa
        List<Producto> productosFinales = productosBase.stream().filter(producto -> {
                    boolean matches = true;
                    // Filtro por mlMin
                    if (busqueda.getMlMin() != null) {
                        matches = matches && producto.getMl() >= busqueda.getMlMin();
                    }
                    // Filtro por mlMax
                    if (busqueda.getMlMax() != null) {
                        matches = matches && producto.getMl() <= busqueda.getMlMax();
                    }
                    // Filtro por precioMin
                    if (busqueda.getPrecioMin() != null) {
                        matches = matches && producto.getPrecio() >= busqueda.getPrecioMin();
                    }
                    // Filtro por precioMax
                    if (busqueda.getPrecioMax() != null) {
                        matches = matches && producto.getPrecio() <= busqueda.getPrecioMax();
                    }
                    return matches;
                }).toList();
        // Crear el resultado
        ResultadoBusqueda resultado = new ResultadoBusqueda();
        resultado.setProductos(productosFinales);
        resultado.setTotalResultados(productosFinales.size());

        return resultado;
    }
    // Métodos adicionales para pruebas
    public List<Producto> productos() {
        return productoRepository.findAll();
    }
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
}
