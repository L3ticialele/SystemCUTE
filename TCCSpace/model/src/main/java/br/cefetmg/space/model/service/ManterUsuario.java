
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public class ManterUsuario implements IManterUsuario{
     private final IUsuarioDAO usuarioDAO;
     
     public ManterUsuario(){
         usuarioDAO = new UsuarioDAO();
     }
    
    @Override
    public void cadastrar(UsuarioDTO usuario) throws PersistenciaException {
        usuarioDAO.inserir(usuario);
    }
    
    @Override
    public boolean alterar(UsuarioDTO usuario) throws PersistenciaException{
        boolean result = usuarioDAO.atualizar(usuario);
        return result;
    }

    @Override
    public boolean excluir(int idUsuario) throws PersistenciaException{
       boolean result = usuarioDAO.delete(idUsuario);
       return result;
    }

    @Override
    public List<UsuarioDTO> pesquisarTodos() throws PersistenciaException {
       List<UsuarioDTO> result = usuarioDAO.listarTodos();
       return result;
    }
}
