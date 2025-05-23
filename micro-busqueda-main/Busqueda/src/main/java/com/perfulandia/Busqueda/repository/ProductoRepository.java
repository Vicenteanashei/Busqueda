package com.perfulandia.Busqueda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfulandia.Busqueda.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    // Búsqueda por nombre (coincidencia parcial, ignorando mayúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    // Búsqueda por aroma (coincidencia parcial, ignorando mayúsculas)
    List<Producto> findByAromaContainingIgnoreCase(String aroma);
    // Búsqueda por marca (coincidencia parcial, ignorando mayúsculas)
    List<Producto> findByMarcaContainingIgnoreCase(String marca);
    // Búsqueda por mililitros mayor o igual a mlMin
    List<Producto> findByMlGreaterThanEqual(Double mlMin);
    // Búsqueda por mililitros menor o igual a mlMax
    List<Producto> findByMlLessThanEqual(Double mlMax);
    // Búsqueda por precio mayor o igual a precioMin
    List<Producto> findByPrecioGreaterThanEqual(Double precioMin);
    // Búsqueda por precio menor o igual a precioMax
    List<Producto> findByPrecioLessThanEqual(Double precioMax);
}
