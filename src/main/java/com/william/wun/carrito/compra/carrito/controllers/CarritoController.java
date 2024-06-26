package com.william.wun.carrito.compra.carrito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.wun.carrito.compra.carrito.entities.Carrito;
import com.william.wun.carrito.compra.carrito.entities.ElementoDeCarrito;
import com.william.wun.carrito.compra.carrito.services.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    //Esta aplicación responde a cómo se ha construido el proyecto en javascript
    //En javascript el proyecto almacena todo en un carrito y lo guarda en la base de datos
    //No se ha construido validaciones para verificar el stock cada vez qe se agrega un producto al carrito
    //No se ha construido gestión de usuarios ni de productos
    //Por lo tanto, esta aplicación solo recibe el carrito, verifica el stock de cada producto del carrito, disminuye el stock y lo guarda, no verifica el stock cada vez qe se agrega un producto al carrito
    //Las mejoras se deben hacer en el futuro
    public ResponseEntity<?> guardar(@RequestBody List<ElementoDeCarrito> elementoDeCarrito){
        try{
            Carrito carritoGuardado = carritoService.guardarCarrito(elementoDeCarrito);
            return ResponseEntity.status(HttpStatus.CREATED).body(carritoGuardado);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el carrito: " + e.getMessage());
        }

        //Mi solución
        //Revisar si: el código que mostraste nunca tomará el camino fuera del if, es decir, nunca ejecutará return ResponseEntity.badRequest().build();. Esto se debe a cómo está estructurado el uso de Optional y la lógica de flujo
        /*
        Optional<Carrito> carritoOptional = Optional.of(carritoService.save(carrito));
        if(carritoOptional.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(carritoOptional.orElseThrow());
        return ResponseEntity.badRequest().build(); 
         */
        
        //chatgpt
        /* 
        try {
            Carrito savedCarrito = carritoService.save(carrito);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCarrito);
        } catch (Exception e) {
            // Manejar la excepción de manera adecuada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el carrito: " + e.getMessage());
        }
        */
        /*
        Explicación de la Mejora
        Simplificación: Se elimina el uso innecesario de Optional. Directamente se guarda el Carrito y se devuelve en la respuesta.
        Manejo de Excepciones: Se agrega un bloque try-catch para manejar posibles excepciones que puedan surgir durante la operación de guardado. Esto es crucial para una aplicación robusta y resistente a fallos.
        Respuesta Adecuada: Se devuelve HttpStatus.CREATED junto con el objeto Carrito guardado, lo cual es consistente con el propósito de la operación.
        Claridad: El código es más claro y fácil de entender sin el uso innecesario de Optional.

        Buenas Prácticas
        Validación de Entrada: Antes de llamar a carritoService.save(carrito), asegúrate de que carrito sea válido. Esto puede incluir verificaciones adicionales a nivel de campo, reglas de negocio, etc.
        Transacciones: Asegúrate de que la operación de guardado en carritoService.save() esté envuelta en una transacción para garantizar la atomicidad.
        Manejo de Errores: Considere manejar errores específicos en lugar de capturar todas las excepciones generales, lo que proporciona un mejor diagnóstico y control de flujo en situaciones de error.
        */
    }
}
