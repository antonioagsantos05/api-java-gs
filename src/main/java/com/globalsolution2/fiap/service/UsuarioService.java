package com.globalsolution2.fiap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalsolution2.fiap.model.UsuarioModel;
import com.globalsolution2.fiap.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel createUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> createUsuarios(List<UsuarioModel> usuarios) {
        return usuarioRepository.saveAll(usuarios);
    }

    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioModel> updateUsuario(Long id, UsuarioModel usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNmUsuario(usuarioDetails.getNmUsuario());
            usuario.setNmLogin(usuarioDetails.getNmLogin());
            usuario.setNmSenha(usuarioDetails.getNmSenha());
            usuario.setNmEmail(usuarioDetails.getNmEmail());
            return usuarioRepository.save(usuario);
        });
    }

    public boolean deleteUsuario(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
