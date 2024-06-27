package br.cefetmg.space.model.idao;

import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;


public interface ICubeSatDAO{
    void inserir(CubeSatDTO cube) throws PersistenciaException;

    boolean atualizar(CubeSatDTO cube) throws PersistenciaException;

    boolean delete(int cube) throws PersistenciaException;

    List<CubeSatDTO> listarTodos() throws PersistenciaException;
    
    CubeSatDTO procurarPorId(int id) throws PersistenciaException;
    
    CubeSatDTO procurarPorNome(String nomeC) throws PersistenciaException;
}
