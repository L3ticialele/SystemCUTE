
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterUsuario {
     public void cadastrar(UsuarioDTO usuario) throws PersistenciaException;
    public boolean alterar(int idUsuario, UsuarioDTO usuario) throws PersistenciaException;
    public void excluir(int idUsuario) throws PersistenciaException;
    public List<UsuarioDTO> pesquisarTodos() throws PersistenciaException;
}
