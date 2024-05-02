
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dto.AdministradorDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterAdministrador {
    public void cadastrar(AdministradorDTO adm) throws PersistenciaException;
    public boolean alterar(AdministradorDTO adm) throws PersistenciaException;
    public boolean excluir(int idAdm) throws PersistenciaException;
    public List<AdministradorDTO> pesquisarTodos() throws PersistenciaException;
}
