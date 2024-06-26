package com.william.wun.carrito.compra.carrito.repositories;

import org.springframework.data.repository.CrudRepository;

import com.william.wun.carrito.compra.carrito.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer>{
    
}
