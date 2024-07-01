
package br.cefetmg.space.model.idao;

import br.cefetmg.space.model.dto.EquipeDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IEquipeDAO {
    void inserir(EquipeDTO equipe) throws PersistenciaException;

    boolean atualizar(EquipeDTO equipe) throws PersistenciaException;

    boolean delete(int idEquipe) throws PersistenciaException;

    List<EquipeDTO> listarTodos() throws PersistenciaException;
}
