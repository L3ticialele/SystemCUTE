
package br.cefetmg.space.model.idao;

import br.cefetmg.space.model.dto.DadosDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IDadosDAO {
    void inserir(DadosDTO dados) throws PersistenciaException;

    boolean atualizar(DadosDTO Dados) throws PersistenciaException;

    boolean delete(int idDado) throws PersistenciaException;

    List<DadosDTO> listarTodos() throws PersistenciaException;
}

