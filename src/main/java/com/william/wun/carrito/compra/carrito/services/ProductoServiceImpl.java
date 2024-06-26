package com.william.wun.carrito.compra.carrito.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.william.wun.carrito.compra.carrito.entities.Producto;
import com.william.wun.carrito.compra.carrito.repositories.ProductoRepository;



@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>)productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Producto> update(Integer id, Producto producto) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if(productoOptional.isPresent()){
            Producto productoDb = productoOptional.orElseThrow();
            productoDb.setId(producto.getId());
            productoDb.setNombre(producto.getNombre());
            productoDb.setImagenUrl(producto.getImagenUrl());
            productoDb.setDescripcion(producto.getDescripcion());
            productoDb.setPrecio(producto.getPrecio());
            productoDb.setStock(producto.getStock());
            return Optional.of(productoRepository.save(productoDb));
        }
        return productoOptional;
    }
    
}
