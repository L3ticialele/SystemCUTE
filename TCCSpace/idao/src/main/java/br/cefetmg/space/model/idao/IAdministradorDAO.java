
package br.cefetmg.space.model.idao;

import br.cefetmg.space.model.dto.AdministradorDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IAdministradorDAO {
    void inserir(AdministradorDTO adm) throws PersistenciaException;

    boolean atualizar(AdministradorDTO adm) throws PersistenciaException;

    boolean delete(int idAdm) throws PersistenciaException;

    List<AdministradorDTO> listarTodos() throws PersistenciaException;
}
