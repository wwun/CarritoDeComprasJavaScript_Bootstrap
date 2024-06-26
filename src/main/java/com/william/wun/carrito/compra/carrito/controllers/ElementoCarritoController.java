package com.william.wun.carrito.compra.carrito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.wun.carrito.compra.carrito.entities.ElementoDeCarrito;
import com.william.wun.carrito.compra.carrito.services.ElementoDeCarritoService;

@RestController
@RequestMapping("api/elementosCarrito")
public class ElementoCarritoController {

    @Autowired
    ElementoDeCarritoService elementoDeCarritoService;

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody ElementoDeCarrito elementoDeCarrito){
        return ResponseEntity.status(HttpStatus.CREATED).body(elementoDeCarritoService.save(elementoDeCarrito));
    }
}
