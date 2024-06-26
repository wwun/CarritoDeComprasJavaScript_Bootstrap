package com.william.wun.carrito.compra.carrito.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.william.wun.carrito.compra.carrito.entities.Carrito;
import com.william.wun.carrito.compra.carrito.entities.ElementoDeCarrito;
import com.william.wun.carrito.compra.carrito.entities.Producto;
import com.william.wun.carrito.compra.carrito.entities.Usuario;
import com.william.wun.carrito.compra.carrito.repositories.CarritoRepository;
import com.william.wun.carrito.compra.carrito.repositories.ElementoDeCarritoRepository;
import com.william.wun.carrito.compra.carrito.repositories.ProductoRepository;

@Service
public class CarritoServiceImpl implements CarritoService{

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ElementoDeCarritoRepository elementoDeCarritoRepository;

    @Autowired
    ProductoService ps;

    @Override
    @Transactional
    public Carrito guardarCarrito(List<ElementoDeCarrito> elementoDeCarrito) {
        
        boolean existeYHayStock = true;
        double total = 0;

        for (ElementoDeCarrito elemento : elementoDeCarrito) {
            Optional<Producto> productoExiste = productoRepository.findById(elemento.getProducto().getId());
                if(productoExiste.isPresent()){
                    if(productoExiste.get().getStock()-elemento.getCantidad()<0){
                        existeYHayStock = false;
                    }
                }else{
                    existeYHayStock=false;
                }
        }

        if(existeYHayStock){
            for (ElementoDeCarrito elemento : elementoDeCarrito) {
                total = total + (elemento.getProducto().getPrecio() * elemento.getCantidad());
                Producto producto = elemento.getProducto();
                producto.setStock(producto.getStock()-elemento.getCantidad());
                ps.update(producto.getId(), producto);
                elementoDeCarritoRepository.save(elemento);
            }
        }

        Usuario usuario = new Usuario();
        Carrito carrito = new Carrito();

        carrito.setUsuario(usuario);
        carrito.setTotal(total);
        carrito.setFechaCreacion(LocalDateTime.now());

        return carritoRepository.save(carrito);
    }

}
