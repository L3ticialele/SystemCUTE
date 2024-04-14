package br.cefetmg.space.model.idao;

import br.cefetmg.space.model.dto.LocalizacaoDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface ILocalizacaoDAO {
    void inserir(LocalizacaoDTO localizacao) throws PersistenciaException;

    boolean atualizar(LocalizacaoDTO localizacao) throws PersistenciaException;

    boolean delete(int idLocalizacao) throws PersistenciaException;

    List<LocalizacaoDTO> listarTodos() throws PersistenciaException;
}

