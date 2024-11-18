
package br.cefetmg.space.model.idao;

import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IUsuarioDAO {
    void inserir(UsuarioDTO usuario) throws PersistenciaException;

    boolean atualizar(int idUsuario, UsuarioDTO usuario) throws PersistenciaException;

    void delete(int idUsuario) throws PersistenciaException;

    List<UsuarioDTO> listarTodos() throws PersistenciaException;
    
    UsuarioDTO procurarPorUserName(String user) throws PersistenciaException;
    
    UsuarioDTO procurarPorEmail(String email) throws PersistenciaException;
    
    UsuarioDTO procurarPorId(int id) throws PersistenciaException;
    
    UsuarioDTO validarlogin(String email, String senha) throws PersistenciaException;
    
    boolean validarlogin(UsuarioDTO usuario) throws PersistenciaException;
}
