package com.william.wun.carrito.compra.carrito.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.william.wun.carrito.compra.carrito.entities.Usuario;



@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioService usuarioService;

    @Override
    @Transactional(readOnly=true)
    public Optional<Usuario> findById(Integer id) {
        return usuarioService.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioService.save(usuario);
    }

}
