package br.cefetmg.space.controller;

import br.cefetmg.space.dao.UsuarioDAO;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.IUsuarioDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioController {

    IUsuarioDAO usuarioDAO;
    Usuario usuario;

    public Usuario login(String email, String senha) throws PersistenciaException {

        Usuario nv = new Usuario();
        Usuario usuario;
        nv.setEmail(email);
        nv.setSenha(senha);
        nv.setTelefone("tel");

        usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.validarlogin(nv)) {
            usuario = usuarioDAO.procurarPorEmail(email);
            return usuario;
        } else {
            return null;
        }
    }
    
    public String senhaHash(String senha){
        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
        return senhaHash;
    }

    public Usuario cadastrar(String email, String nome, String senha, String telefone) throws PersistenciaException {
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario();
        if (usuarioDAO.procurarPorEmail(email) == null) {
            usuario.setEmail(email);
            usuario.setNome(nome);
            usuario.setSenha(senhaHash(senha));
            usuario.setTelefone(telefone);
            return usuarioDAO.inserir(usuario);
        }
        return null;
    }
}
