
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dto.LocalizacaoDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterLocalizacao {
    public void cadastrar(LocalizacaoDTO localizacao) throws PersistenciaException;
    public boolean alterar(LocalizacaoDTO localizacao) throws PersistenciaException;
    public boolean excluir(int idLocalizacao) throws PersistenciaException;
    public List<LocalizacaoDTO> pesquisarTodos() throws PersistenciaException;
}
