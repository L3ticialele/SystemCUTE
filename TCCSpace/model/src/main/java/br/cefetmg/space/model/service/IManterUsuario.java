
package br.cefetmg.space.model.service;

import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterUsuario {
     public void cadastrar(Usuario usuario) throws PersistenciaException;
    public boolean alterar(int idUsuario, Usuario usuario) throws PersistenciaException;
    public void excluir(int idUsuario) throws PersistenciaException;
    public List<Usuario> pesquisarTodos() throws PersistenciaException;
}
