package com.william.wun.carrito.compra.carrito.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.william.wun.carrito.compra.carrito.entities.ElementoDeCarrito;
import com.william.wun.carrito.compra.carrito.repositories.ElementoDeCarritoRepository;

@Service
public class ElementoDeCarritoServiceImpl implements ElementoDeCarritoService{

    @Autowired
    ElementoDeCarritoRepository elementoDeCarritoRepository;

    @Override
    @Transactional
    public ElementoDeCarrito save(ElementoDeCarrito elementoDeCarrito) {
        return elementoDeCarritoRepository.save(elementoDeCarrito);
    }

}
