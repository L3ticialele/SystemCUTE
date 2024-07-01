
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterCubeSat {
    public void cadastrar(CubeSatDTO cube) throws PersistenciaException;
    public boolean alterar(CubeSatDTO cube) throws PersistenciaException;
    public boolean excluir(int cubeId) throws PersistenciaException;
    public List<CubeSatDTO> pesquisarTodos() throws PersistenciaException;
}
