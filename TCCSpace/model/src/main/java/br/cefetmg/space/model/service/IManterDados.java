
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dto.DadosDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;


public interface IManterDados {
    public void cadastrar(DadosDTO dados) throws PersistenciaException;
    public boolean alterar(DadosDTO dados) throws PersistenciaException;
    public boolean excluir(int idDados) throws PersistenciaException;
    public List<DadosDTO> pesquisarTodos() throws PersistenciaException;
}
