
package br.cefetmg.space.model.service;

import br.cefetmg.space.dao.UsuarioDAO;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.IUsuarioDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

public class ManterUsuario implements IManterUsuario{
     private final IUsuarioDAO usuarioDAO;
     
     public ManterUsuario(){
         usuarioDAO = new UsuarioDAO();
     }
    
    @Override
    public void cadastrar(Usuario usuario) throws PersistenciaException {
        usuarioDAO.inserir(usuario);
    }
    
    @Override
    public boolean alterar(int idUsuario, Usuario usuario) throws PersistenciaException{
        boolean result = usuarioDAO.atualizar(idUsuario, usuario);
        return result;
    }

    @Override
    public void excluir(int idUsuario) throws PersistenciaException{
        usuarioDAO.delete(idUsuario);
    }

    @Override
    public List<Usuario> pesquisarTodos() throws PersistenciaException {
       List<Usuario> result = usuarioDAO.listarTodos();
       return result;
    }
}
