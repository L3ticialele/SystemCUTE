
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dto.EquipeDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterEquipe {
    public void cadastrar(EquipeDTO equipe) throws PersistenciaException;
    public boolean alterar(EquipeDTO equipe) throws PersistenciaException;
    public boolean excluir(int idEquipe) throws PersistenciaException;
    public List<EquipeDTO> pesquisarTodos() throws PersistenciaException;
}
