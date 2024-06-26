package com.william.wun.carrito.compra.carrito.services;

import java.util.List;
import java.util.Optional;

import com.william.wun.carrito.compra.carrito.entities.Producto;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Integer id);
    Optional<Producto> update(Integer id, Producto producto);
}
