package br.edu.unisep.cadastro.controller;

import br.edu.unisep.cadastro.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private static final String FILE_PATH = "usuarios.txt";
    private List<Usuario> usuarios;

    public UsuarioController() {
        usuarios = carregarUsuarios();
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }

    public void editarUsuario(Usuario usuarioExistente, String novoNome, String novoEmail, String novaSenha, String novoTipo) {
        usuarioExistente.setNome(novoNome);
        usuarioExistente.setEmail(novoEmail);
        usuarioExistente.setSenha(novaSenha);
        usuarioExistente.setTipo(novoTipo);
        salvarUsuarios();
    }

    private void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Usuario> carregarUsuarios() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
