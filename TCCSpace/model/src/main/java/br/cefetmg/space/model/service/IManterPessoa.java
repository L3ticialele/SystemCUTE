
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dto.PessoaDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterPessoa {
     public void cadastrar(PessoaDTO pessoa) throws PersistenciaException;
    public boolean alterar(PessoaDTO pessoa) throws PersistenciaException;
    public boolean excluir(int idPessoa) throws PersistenciaException;
    public List<PessoaDTO> pesquisarTodos() throws PersistenciaException;
}
