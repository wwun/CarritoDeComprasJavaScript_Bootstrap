package com.william.wun.carrito.compra.carrito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.wun.carrito.compra.carrito.entities.Producto;
import com.william.wun.carrito.compra.carrito.services.ProductoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listaDeProductos(){
        return productoService.findAll();
    }

    public ResponseEntity<?> obtenerProducto(@PathVariable Integer id){
        Optional<Producto> productoOptional = productoService.findById(id);
        if(productoOptional.isPresent())
            return ResponseEntity.ok(productoOptional.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable Integer id){
        Optional<Producto> productoOptional = productoService.update(id, producto);
        if(productoOptional.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(productoOptional.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    /*  //Otra manera de hacer obtenerProducto, obtenido de chatgpt y la diferencia está en que Utiliza productoOptional.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado")).
        //Lanza una excepción personalizada (ResourceNotFoundException) si el producto no se encuentra.
        //Se basa en un manejador de excepciones (normalmente un @ControllerAdvice) para devolver una respuesta HTTP adecuada (generalmente 404 Not Found) cuando se lanza ResourceNotFoundException
        //Es más concisa, pero depende de que el controlador tenga un manejo de excepciones configurado
    
    public ResponseEntity<Producto> getProducto(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoService.findById(id);
        return ResponseEntity.ok(productoOptional.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado")));
    }

    //Ejemplo de Manejador de Excepciones en otra clase    
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
     */

}
