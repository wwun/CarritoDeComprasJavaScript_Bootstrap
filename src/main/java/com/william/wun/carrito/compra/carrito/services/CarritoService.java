package com.william.wun.carrito.compra.carrito.services;

import java.util.List;

import com.william.wun.carrito.compra.carrito.entities.Carrito;
import com.william.wun.carrito.compra.carrito.entities.ElementoDeCarrito;

public interface CarritoService {
    Carrito guardarCarrito(List<ElementoDeCarrito> elementoDeCarrito);
}
