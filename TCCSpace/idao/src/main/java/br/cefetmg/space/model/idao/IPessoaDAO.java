
package br.cefetmg.space.model.idao;

import br.cefetmg.space.model.dto.PessoaDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IPessoaDAO {
    void inserir(PessoaDTO pessoa) throws PersistenciaException;

    boolean atualizar(PessoaDTO pessoa) throws PersistenciaException;

    boolean delete(int idPessoa) throws PersistenciaException;

    List<PessoaDTO> listarTodos() throws PersistenciaException;
}
