package com.william.wun.carrito.compra.carrito.services;

import java.util.Optional;

import com.william.wun.carrito.compra.carrito.entities.Usuario;

public interface UsuarioService {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Integer id);
}
